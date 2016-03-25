package com.easyapp.core.service;

import java.util.List;
import java.util.Optional;

import com.easyapp.core.model.PersistModel;

public interface CRUDService<T extends PersistModel> {
	List<T> findAll();

	Optional<T> findOne(final String id);

	Optional<T> create(final T record);

	Optional<T> update(final T record);

	void delete(final String id);
}
