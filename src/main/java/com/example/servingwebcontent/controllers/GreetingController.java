package com.example.servingwebcontent.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

	/**
	 * Mapping for the "/greeting" endpoint.
	 * <p>
	 * The endpoint expects a "name" parameter, which is optional and defaults to "World".
	 * <p>
	 * The endpoint returns a "greeting" view, and adds a "name" attribute to the model.
	 *
	 * @param name the name of the person to greet
	 * @param model the model that will be passed to the view
	 * @return the name of the view to render
	 */
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

}
