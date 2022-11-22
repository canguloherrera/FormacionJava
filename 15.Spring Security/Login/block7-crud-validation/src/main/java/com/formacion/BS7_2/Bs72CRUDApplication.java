package com.formacion.BS7_2;

import com.formacion.BS7_2.person.application.services.PersonService;
import com.formacion.BS7_2.person.infraestructure.dto.input.PersonInputDto;
import com.formacion.BS7_2.role.application.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;


@SpringBootApplication

public class Bs72CRUDApplication implements CommandLineRunner {
	@Autowired
	PersonService personService;

	@Autowired
	RoleService roleService;

	PersonInputDto personInputDto;

	public static void main(String[] args) {
		SpringApplication.run(Bs72CRUDApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {





	}
}
