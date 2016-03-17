package com.easyapp.core.service;

import com.easyapp.core.model.PersistModel;
import com.easyapp.core.repository.PersistModelRepository;

abstract public class PersistModelService<T extends PersistModel> implements CRUDService<T> {
	protected PersistModelRepository<T> persistModelRepository;
	
	public PersistModelService(PersistModelRepository<T> persistModelRepository) {
		this.persistModelRepository = persistModelRepository;
	}
}
