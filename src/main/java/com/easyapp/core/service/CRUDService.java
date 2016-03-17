package com.easyapp.core.service;

import java.util.List;

import com.easyapp.core.model.PersistModel;

public interface CRUDService<T extends PersistModel> {
	List<T> findAll();

	T findOne(final String id);

	T create(final T record);

	T update(final T record);

	void delete(final String id);
}
