package com.codingdojo.dojosandninjas.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.dojosandninjas.models.Dojo;
import com.codingdojo.dojosandninjas.models.Ninja;
import com.codingdojo.dojosandninjas.services.DojoService;
import com.codingdojo.dojosandninjas.services.NinjaService;

import jakarta.validation.Valid;

@Controller
public class MainController {
	private final NinjaService ninjaService;
	private final DojoService dojoService;
	
	public MainController(NinjaService ninjaService, DojoService dojoService) {
		this.dojoService = dojoService;
		this.ninjaService = ninjaService;
	}
	
	// Get Mappings
	@GetMapping("/dojos/new")
	public String index(@ModelAttribute("dojo") Dojo dojo) {
		return "dojos.jsp";
	}
	
	@GetMapping("/ninjas/new")
	public String newNinja(@ModelAttribute("ninja") Ninja ninja, Model model) {
		List<Dojo> allDojos = dojoService.getAllDojos();
		model.addAttribute("dojos", allDojos);
		return "ninjas.jsp";
	}
	
	@GetMapping("/dojos/{id}")
	public String viewDojo(@PathVariable("id") Long id, Model model) {
		Dojo dojo = dojoService.getDojoById(id);
		model.addAttribute("dojo", dojo);
		return "viewDojo.jsp";
	}
	
	// Post Mappings
	@PostMapping("/dojos/new")
	public String createDojo(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result) {
		if (result.hasErrors()) {
			return "new.jsp";
		}else {
			dojoService.createDojo(dojo);
			return "redirect:/dojos/new";
		}
	}
	
	@PostMapping("/ninjas/new")
	public String createNinja(@Valid @ModelAttribute("ninja") Ninja ninja, BindingResult result) {
		if (result.hasErrors()) {
			return "ninjas.jsp";
		}else {
			ninjaService.createNinja(ninja);
			return "redirect:/dojos/new";
		}
	}
}
