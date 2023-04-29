package com.craapp.craapp;

import com.craapp.craapp.entities.Appuser;
import com.craapp.craapp.repositories.AppuserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;

@SpringBootApplication (scanBasePackages = "com.craapp.craapp.*")
public class CraappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CraappApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner  (AppuserRepository users , PasswordEncoder passwordEncoder ) {

		return args -> {
			users.save(new Appuser("user",
					passwordEncoder.encode("password"),
					"alaeeddine",
					"charrik",
					"00869068068068",
					"alaeeddine.charrik@gmail.com",
					true,
					"ROLE_USER"));
			users.save(new Appuser("admin",
					passwordEncoder.encode("password"),
					"alaeeee",
					"ch",
					"99689686",
					"alaeeddine.charrik.pv@gmail.com",
					true,
					"ROLE_USER,ROLE_ADMIN"));
		};
	}



}






