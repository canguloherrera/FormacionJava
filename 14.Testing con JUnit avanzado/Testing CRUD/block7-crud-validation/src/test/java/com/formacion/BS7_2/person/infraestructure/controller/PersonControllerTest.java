package com.formacion.BS7_2.person.infraestructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formacion.BS7_2.exception.EntityNotFoundException;
import com.formacion.BS7_2.exception.UnprocessableEntityException;
import com.formacion.BS7_2.person.application.services.PersonService;
import com.formacion.BS7_2.person.application.services.servicesImpl.PersonServiceImpl;
import com.formacion.BS7_2.person.domain.model.Person;
import com.formacion.BS7_2.person.infraestructure.dto.input.PersonInputDto;
import com.formacion.BS7_2.person.infraestructure.dto.output.PersonOutputDto;
import com.formacion.BS7_2.person.infraestructure.dto.repository.PersonDaoRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = PersonController.class)

@WebMvcTest
class PersonControllerTest {

    @Autowired
    private PersonController personController;

    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private PersonService personService;

    Person person = null;
    Person person2 = null;
    PersonInputDto personInputDto = null;


    @BeforeEach
    void setUp() {
        personInputDto = new PersonInputDto(1, "cah119", "as123", "carlos",
                "angulo", "ca@bosonit.com", "ca@hotmail.com", "madrid",
                true, new Date(), "http://localhost:8080/ejemplo", null);

        person = new Person(personInputDto);
        person2 = new Person(personInputDto);
    }

    @Test
    void addPerson() throws Exception {

       Mockito.when(personService.addUser(ArgumentMatchers.any())).thenReturn(new PersonOutputDto(person));
       String json = mapper.writeValueAsString(personInputDto);

       MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/person/addPerson")
               .contentType(MediaType.APPLICATION_JSON).content(json);
       MockMvcBuilders.standaloneSetup(personController)
               .build()
               .perform(requestBuilder)
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
               .andExpect(MockMvcResultMatchers.jsonPath("$.password",Matchers.equalTo("as123")))
               .andExpect(MockMvcResultMatchers.jsonPath("name",Matchers.equalTo("carlos")))
               .andExpect(MockMvcResultMatchers.jsonPath("$.surname",Matchers.equalTo("angulo")))
               .andExpect(MockMvcResultMatchers.jsonPath("$.company_email",Matchers.equalTo("ca@bosonit.com")))
               .andExpect(MockMvcResultMatchers.jsonPath("$.username",Matchers.equalTo("cah119")));


    }




   @Test
   void deletePerson() throws Exception {


    }

    @Test
    void personListTest() throws Exception {
        //give
        List<PersonOutputDto> personOutputDtoList = new ArrayList<>();
        Person person1 = new Person(personInputDto);
        person1.setId_person(1);
        person1.setName("jose");
        PersonOutputDto personOutputDto = new PersonOutputDto(person1);
        personOutputDtoList.add(personOutputDto);
        //when
        Mockito.when(personService.findALl()).thenReturn(personOutputDtoList);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person/personList");
       // requestBuilder.characterEncoding("Encoding");
        //then
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name",Matchers.equalTo("jose")))
                .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.hasSize(1)
                ));


    }






    @Test
    void showByIdTest() throws Exception {
        //give
        Integer id=1;
        personService.addUser(personInputDto);

        when(personService.findById(id)).thenReturn(new PersonOutputDto(person));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person/person/{id}",1);
        //then
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.equalTo("carlos")));

    }

    @Test
    void showByIdPersonNotFound() throws Exception {


    }

    @Test
    void updatePerson() {
    }

    @Test
    void showByName() {
    }
}