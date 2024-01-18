package com.codingdojo.dojosandninjas.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codingdojo.dojosandninjas.models.Dojo;
import com.codingdojo.dojosandninjas.models.Ninja;
import com.codingdojo.dojosandninjas.services.DojoService;
import com.codingdojo.dojosandninjas.services.NinjaService;

@RestController
public class MainApi {
	private final NinjaService ninjaService;
	private final DojoService dojoService;
	
	public MainApi(NinjaService ninjaService, DojoService dojoService) {
		this.dojoService = dojoService;
		this.ninjaService = ninjaService;
	}
	// Testing getting all from each table
	@GetMapping("/api/dojos")
	public List<Dojo> dojos(){
		return dojoService.getAllDojos();
	}
	@GetMapping("/api/ninjas")
	public List<Ninja> ninjas(){
		return ninjaService.getAllNinjas();
	}
	
	// Testing getting specific row from each table
	@GetMapping("/api/dojos/{id}")
	public Dojo showDojo(@PathVariable("id") Long id) {
		Dojo dojo = dojoService.getDojoById(id);
		return dojo;
	}
	@GetMapping("/api/ninjas/{id}")
	public Ninja showNinja(@PathVariable("id") Long id) {
		Ninja ninja = ninjaService.getNinjaById(id);
		return ninja;
	}
	
	// Testing sending post requests to db
	@PostMapping("/api/dojos")
	public Dojo createDojo(@RequestParam(value="name") String name) {
		Dojo dojo = new Dojo(name);
		return dojoService.createDojo(dojo);
	}
	@PostMapping("/api/ninjas")
	public Ninja createNinja(@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "lastName") String lastName, 
			@RequestParam(value = "age") int age) {
		Ninja ninja = new Ninja(firstName, lastName, age);
		return ninjaService.createNinja(ninja);
	}
	
	// Deleting from api
	@DeleteMapping("/api/dojos/{id}")
	public void deleteDojo(@PathVariable("id") Long id) {
		dojoService.deleteDojo(id);
	}
	@DeleteMapping("/api/ninjas/{id}")
	public void deleteNinja(@PathVariable("id") Long id) {
		ninjaService.deleteNinja(id);
	}
}








