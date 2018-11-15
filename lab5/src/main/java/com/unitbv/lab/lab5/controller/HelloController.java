package com.unitbv.lab.lab5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.unitbv.lab.lab5.persistence.model.Person;
import com.unitbv.lab.lab5.persistence.repository.PersonRepository;



@RestController
@RequestMapping(value = "/tema2")
public class HelloController
{     
	@Autowired
	PersonRepository personRepository;
	
    @RequestMapping(value = "/{name}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String sayHello(@PathVariable String name) {
		return "Hello, " + name;
	}
    
    @RequestMapping(value="/greeting", method=RequestMethod.GET)
	public String getFullgreeting(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
		return "Hello, " + firstName + " " + lastName + "!";
	}

    //Return Persons list.
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findPersons() {
		return this.personRepository.findAll();
	}

    //Find Person by id.
	@RequestMapping(value = "/search/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findPerson(@PathVariable Integer id) {
		return this.personRepository.findOne(id);
	}
	
	//Create Person.
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Person createPerson(@RequestBody Person person) {
		return this.personRepository.save(person);
	}
	
	//Edit Person.
	@RequestMapping(value = "/edit", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Person editPerson(@RequestBody Person person) {
		return this.personRepository.save(person);
	}
	
	//Delete Person by id.
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deletePerson(@PathVariable Integer id) {
		this.personRepository.delete(id);
	}
}
