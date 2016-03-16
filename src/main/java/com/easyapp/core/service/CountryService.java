package com.easyapp.core.service;

import java.util.List;

import com.easyapp.core.model.Country;

public interface CountryService {
	public List<Country> findAll();

	public Country findOne(final String id);

	public Country create(final Country country);
	
	public Country update(final Country country);
	
	public void delete(final String id);
}
