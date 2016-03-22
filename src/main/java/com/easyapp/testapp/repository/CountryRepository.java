package com.easyapp.testapp.repository;

import org.springframework.stereotype.Repository;

import com.easyapp.core.repository.PersistEntityRepository;
import com.easyapp.testapp.entity.CountryEntity;

@Repository
public interface CountryRepository extends PersistEntityRepository<CountryEntity> {
}
