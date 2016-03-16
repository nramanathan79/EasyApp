package com.easyapp.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.easyapp.core.model.Country;
import com.easyapp.core.repository.CountryRepository;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {
	@Autowired
	private CountryRepository countryRepository;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Country> findAll() {
		return countryRepository.findAll();
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Country findOne(final String id) {
		return countryRepository.findOne(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Country create(final Country country) {
		// If country already exists, we cannot create one.
		if (country.getId() != null) {
			return null;
		}

		return countryRepository.save(country);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Country update(final Country country) {
		Country existingCountry = findOne(country.getId());

		// If country doesn't already exist, we cannot update one.
		if (existingCountry == null) {
			return null;
		}

		return countryRepository.save(country);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(final String id) {
		countryRepository.delete(id);
	}
}
