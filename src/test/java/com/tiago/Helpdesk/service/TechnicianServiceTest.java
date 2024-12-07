package com.tiago.Helpdesk.service;

import com.tiago.Helpdesk.controller.dto.TechnicianDTO;
import com.tiago.Helpdesk.domain.Technician;
import com.tiago.Helpdesk.enums.Profile;
import com.tiago.Helpdesk.repository.TechnicianRepository;
import com.tiago.Helpdesk.service.exception.DatabaseException;
import com.tiago.Helpdesk.service.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TechnicianServiceTest {

    @InjectMocks
    private TechnicianService technicianService;

    @Mock
    private TechnicianRepository technicianRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    void findById_WhenTechnicianExists_ReturnsTechnician() {
        Integer id = 1;
        Technician technician = new Technician();
        technician.setId(id);
        technician.setName("John Doe");

        when(technicianRepository.findById(id)).thenReturn(Optional.of(technician));

        Technician result = technicianService.findById(id);

        assertNotNull(result);
        assertEquals(technician.getId(), result.getId());
        assertEquals(technician.getName(), result.getName());
        verify(technicianRepository, times(1)).findById(id);
    }

    @Test
    void findById_WhenTechnicianDoesNotExist_ThrowsResourceNotFoundException() {
        Integer id = 1;

        when(technicianRepository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            technicianService.findById(id);
        });

        assertEquals("Technician not found for ID: " + id, exception.getMessage());
        verify(technicianRepository, times(1)).findById(id);
    }

    @Test
    void findById_WhenIdIsInvalid_ThrowsIllegalArgumentException() {
        Integer invalidId = -1;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            technicianService.findById(invalidId);
        });

        assertEquals("Invalid ID: " + invalidId, exception.getMessage());
        verify(technicianRepository, never()).findById(any());
    }

    @Test
    void findAll_WhenTechniciansExist_ReturnsTechniciansList() {
        // Arrange
        Set<Integer> profiles1 = new HashSet<>();
        profiles1.add(Profile.TECHNICIAN.getId());

        Set<Integer> profiles2 = new HashSet<>();
        profiles2.add(Profile.ADMIN.getId());

        List<Technician> technicians = Arrays.asList(
                new Technician(1, "Pablo Neruda", "12345678900", "neruda@email.com", "password123", profiles1),
                new Technician(2, "Gabriela Mistral", "98765432100", "mistral@email.com", "password456", profiles2)
        );

        when(technicianRepository.findAll()).thenReturn(technicians);

        // Act
        List<Technician> result = technicianService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Pablo Neruda", result.get(0).getName());
        assertEquals("Gabriela Mistral", result.get(1).getName());
        verify(technicianRepository, times(1)).findAll();
    }

    @Test
    void findAll_WhenNoTechniciansExist_ReturnsEmptyList() {
        when(technicianRepository.findAll()).thenReturn(null);

        List<Technician> result = technicianService.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(technicianRepository, times(1)).findAll();
    }

    @Test
    void findAll_WhenDatabaseExceptionOccurs_ThrowsDatabaseException() {
        when(technicianRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        DatabaseException exception = assertThrows(DatabaseException.class, () -> {
            technicianService.findAll();
        });

        assertEquals("Failed to fetch technicians", exception.getMessage());
        verify(technicianRepository, times(1)).findAll();
    }

}