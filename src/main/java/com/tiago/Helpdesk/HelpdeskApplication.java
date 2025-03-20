package com.tiago.Helpdesk;

import com.tiago.Helpdesk.domain.Technician;
import com.tiago.Helpdesk.domain.Ticket;
import com.tiago.Helpdesk.domain.User;
import com.tiago.Helpdesk.enums.Priority;
import com.tiago.Helpdesk.enums.Profile;
import com.tiago.Helpdesk.enums.Status;
import com.tiago.Helpdesk.repository.TechnicianRepository;
import com.tiago.Helpdesk.repository.TicketRepository;
import com.tiago.Helpdesk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Autowired
	private TechnicianRepository technicianRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public void run(String... args) throws Exception {
		Technician technician = new Technician(null, "Tiago", "012345679", "email@mail.com", "789");
		technician.addProfiles(Profile.ADMIN);

		User user = new User(null, "José", "6546219654", "email2@mail.com", "senha123");
		Ticket ticket = new Ticket(null, Priority.LOW, Status.OPEN, "Computador não liga", "Computador não estã ligando", technician, user);

		technicianRepository.saveAll(Arrays.asList(technician));
		userRepository.saveAll(Arrays.asList(user));
		ticketRepository.saveAll(Arrays.asList(ticket));
	}

}
