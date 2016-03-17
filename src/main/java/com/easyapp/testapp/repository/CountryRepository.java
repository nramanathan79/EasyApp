package com.easyapp.testapp.repository;

import org.springframework.stereotype.Repository;

import com.easyapp.core.repository.PersistModelRepository;
import com.easyapp.testapp.model.Country;

@Repository
public interface CountryRepository extends PersistModelRepository<Country> {
}
