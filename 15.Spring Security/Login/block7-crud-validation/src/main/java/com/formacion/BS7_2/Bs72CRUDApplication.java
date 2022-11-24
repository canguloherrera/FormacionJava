package com.formacion.BS7_2;

import com.formacion.BS7_2.person.application.services.PersonService;
import com.formacion.BS7_2.person.domain.model.Person;
import com.formacion.BS7_2.person.infraestructure.dto.input.PersonInputDto;
import com.formacion.BS7_2.person.infraestructure.dto.repository.PersonDaoRepository;
import com.formacion.BS7_2.role.application.RoleService;
import com.formacion.BS7_2.role.application.RoleServiceImpl;
import com.formacion.BS7_2.role.domain.Role;
import com.formacion.BS7_2.role.infraestructure.dto.RoleInputDto;
import com.formacion.BS7_2.role.infraestructure.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;


@SpringBootApplication

public class Bs72CRUDApplication implements CommandLineRunner {
	@Autowired
	PersonDaoRepository personRepo;

	@Autowired
	RoleRepository roleRepo;






	public Bs72CRUDApplication() throws Exception {
	}

	public static void main(String[] args) {
		SpringApplication.run(Bs72CRUDApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		PersonInputDto personInputDto = new PersonInputDto(
				"cah119",
				"1234",
				"carlos",
				"angulo",
				"test1@bosonit.com",
				"test2@loquesea.com",
				"madrid",
				true,
				new Date(),
				"http://example.com/img.jpg",
				null,
				new ArrayList<>(),
				false);


		Role role = new Role();
		Person person = new Person(personInputDto);
		role.setName("USER");
		roleRepo.save(role);
		person.getRoles().add(role);
		personRepo.save(person);






	}
}
