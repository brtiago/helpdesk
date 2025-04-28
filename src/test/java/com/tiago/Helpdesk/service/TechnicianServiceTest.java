package com.tiago.Helpdesk.service;

import com.tiago.Helpdesk.controller.dto.TechnicianDTO;
import com.tiago.Helpdesk.domain.Technician;
import com.tiago.Helpdesk.enums.Profile;
import com.tiago.Helpdesk.repository.TechnicianRepository;
import com.tiago.Helpdesk.service.exception.DatabaseException;
import com.tiago.Helpdesk.service.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TechnicianServiceTest {

    @InjectMocks
    private TechnicianService technicianService;

    @Mock
    private TechnicianRepository technicianRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }


    private Technician createTechnician(Integer id, String name, Profile profile) {
        return new Technician.Builder()
                .withId(id)
                .withName(name)
                .withCpf("00000000000")
                .withEmail(name.toLowerCase() + "@email.com")
                .withPassword("password")
                .withProfiles(Set.of(profile.getId()))
                .build();
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
        List<Technician> technicians = Arrays.asList(
                createTechnician(1, "Pablo Neruda", Profile.TECHNICIAN),
                createTechnician(2, "Gabriela Mistral", Profile.ADMIN)
        );

        when(technicianRepository.findAll()).thenReturn(technicians);

        // Act
        List<Technician> result = technicianService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Pablo Neruda", result.get(0).getName());
        assertEquals("Gabriela Mistral", result.get(1).getName());
        verify(technicianRepository).findAll();
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