package com.easyapp.testapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CountryController {
	@RequestMapping(value = "/countries")
	public ModelAndView countries() {
		return new ModelAndView("countries");
	}
}
