package com.easyapp.testapp.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easyapp.core.restcontroller.PersistModelRestController;
import com.easyapp.testapp.model.Country;
import com.easyapp.testapp.repository.CountryRepository;

@RestController
@RequestMapping(value = "/api/countries")
public class CountryRestController extends PersistModelRestController<Country> {
	@Autowired
	CountryRestController(CountryRepository countryRepository) {
		super(countryRepository);
	}
}
