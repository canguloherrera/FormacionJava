package com.formacion.BS7_2.person.infraestructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formacion.BS7_2.exception.EntityNotFoundException;
import com.formacion.BS7_2.person.application.services.PersonService;
import com.formacion.BS7_2.person.domain.model.Person;
import com.formacion.BS7_2.person.infraestructure.dto.input.PersonInputDto;
import com.formacion.BS7_2.person.infraestructure.dto.output.PersonOutputDto;
import com.formacion.BS7_2.person.infraestructure.dto.repository.PersonDaoRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ContextConfiguration(classes = {PersonController.class})
@ExtendWith(MockitoExtension.class)
@WebMvcTest
class PersonControllerTest {

    @InjectMocks
    private PersonController personController;


    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private PersonService personService;
    @MockBean
    private PersonDaoRepository personDaoRepository;


    Person person = null;
    Person person2 = null;
    PersonInputDto personInputDto = null;

    PersonOutputDto personOutputDto = null;


    @BeforeEach
    void setUp() {
        personInputDto = new PersonInputDto(1, "cah119", "as123", "carlos",
                "angulo", "ca@bosonit.com", "ca@hotmail.com", "madrid",
                true, new Date(), "http://localhost:8080/ejemplo", null);

        person = new Person(personInputDto);
        person2 = new Person(personInputDto);
        personOutputDto = new PersonOutputDto(person);

    }

    @Test
    void shouldAddPersonTest() throws Exception {

        Mockito.when(personService.addUser(ArgumentMatchers.any())).thenReturn(new PersonOutputDto(person));
        String json = mapper.writeValueAsString(personInputDto);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/person/addPerson")
                .contentType(MediaType.APPLICATION_JSON).content(json);
        MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", Matchers.equalTo("as123")))
                .andExpect(MockMvcResultMatchers.jsonPath("name", Matchers.equalTo("carlos")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname", Matchers.equalTo("angulo")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.company_email", Matchers.equalTo("ca@bosonit.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.equalTo("cah119")))
                .andDo(print());


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

        //then
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.equalTo("jose")))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)
                ));


    }

    @Test
    void personListNotFound() throws Exception {
        List<PersonOutputDto> personList = new ArrayList<>();
        when(personService.findALl()).thenThrow(EntityNotFoundException.class);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person/personList");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound()).andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));


    }


    @Test
    void ShouldShowByIdTest() throws Exception {
        Integer userId = 1;
        person.setId_person(1);
        PersonOutputDto ofResult = new PersonOutputDto(person);
        //
        when(personService.findById(userId)).thenReturn(ofResult);
        //when
        mockMvc.perform(get("/person/person/{id}", userId))
                .andExpect(status().isOk()).andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.equalTo("cah119")))
                .andDo(print());


    }

    @Test
    void showByIdPersonNotFound() throws Exception {


    }


    @Test
    void testDeletePerson() throws Exception {
        when(personService.deleteUser((Integer) org.mockito.Mockito.any())).thenReturn("Delete User");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/person/delete/{id}", 1);
        MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("User deleted"));
    }

    @Test
    void ShouldDeletePersonWithException() throws Exception{
        when(personService.deleteUser((Integer) any())).thenThrow(EntityNotFoundException.class);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/person/delete/{id}", 1);
        MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updatePerson() {
    }

    @Test
    void showByName() {
    }
}