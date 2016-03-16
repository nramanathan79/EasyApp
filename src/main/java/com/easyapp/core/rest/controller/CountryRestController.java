package com.easyapp.core.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.easyapp.core.model.Country;
import com.easyapp.core.service.CountryService;

@RestController
public class CountryRestController {
	@Autowired
	private CountryService countryService;
	
	@RequestMapping(value = "/api/countries", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Country>> findAll() {
		List<Country> countries = countryService.findAll();

		if (countries == null || countries.size() <= 0) {
			return new ResponseEntity<List<Country>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Country>>(countries, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/countries/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Country> findOne(@PathVariable("id") final String id) {
		Country country = countryService.findOne(id);

		if (country == null) {
			return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Country>(country, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/countries", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Country> create(@RequestBody @Valid Country country) {
		Country countryCreated = countryService.create(country);

		if (countryCreated == null) {
			return new ResponseEntity<Country>(HttpStatus.CONFLICT);
		}

		return new ResponseEntity<Country>(countryCreated, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/api/countries/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Country> update(@RequestBody @Valid Country country) {
		Country countryUpdated = countryService.update(country);

		if (countryUpdated == null) {
			return new ResponseEntity<Country>(HttpStatus.CONFLICT);
		}

		return new ResponseEntity<Country>(countryUpdated, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/countries/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Country> delete(@PathVariable("id") final String id) {
		countryService.delete(id);
		return new ResponseEntity<Country>(HttpStatus.OK);
	}
}
