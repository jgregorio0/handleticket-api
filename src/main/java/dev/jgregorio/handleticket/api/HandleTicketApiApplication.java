package dev.jgregorio.handleticket.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class HandleTicketApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HandleTicketApiApplication.class, args);
	}
}
