package com.easyapp.core.service;

import org.springframework.stereotype.Service;

import com.easyapp.core.entity.PersistEntity;
import com.easyapp.core.model.PersistModel;
import com.easyapp.core.repository.PersistEntityRepository;

@Service
abstract public class PersistModelService<T extends PersistModel> implements CRUDService<T> {
	protected Class<T> modelClass; 
	protected PersistEntityRepository<? extends PersistEntity> persistEntityRepository;
	
	public PersistModelService(Class<T> modelClass, PersistEntityRepository<? extends PersistEntity> persistEntityRepository) {
		this.modelClass = modelClass;
		this.persistEntityRepository = persistEntityRepository;
	}
}
