package com.group8.accountsystem;

import com.group8.accountsystem.Model.User;
import com.group8.accountsystem.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication // Lägger även till @Configuration, @EnableAutoConfiguration @ComponentScan
public class AccountSystemApplication extends SpringBootServletInitializer {

	private static final Logger log = LoggerFactory.getLogger(AccountSystemApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccountSystemApplication.class, args);
	}

	@Bean // För att testa några crud-operationer
	public CommandLineRunner demo(UserRepository repository) {
		return args -> {
			log.info("TESTA DATABASEN");
			log.info("");

			repository.save(new User("Carpe", "Diem", "abc123", "email@email.com"));
		};
	}
}
