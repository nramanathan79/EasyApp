package com.easyapp.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.easyapp.core.model.PersistModel;
import com.easyapp.core.repository.PersistModelRepository;

@Service
@Transactional
public class PersistModelServiceImpl<T extends PersistModel> extends PersistModelService<T> {
	@Autowired
	public PersistModelServiceImpl(PersistModelRepository<T> persistModelRepository) {
		super(persistModelRepository);
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<T> findAll() {
		return persistModelRepository.findAll();
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public T findOne(final String id) {
		return persistModelRepository.findOne(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public T create(final T record) {
		// If record already exists, we cannot create one.
		if (record.getId() != null) {
			return null;
		}

		return persistModelRepository.save(record);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public T update(final T record) {
		T existingRecord = findOne(record.getId());

		// If record doesn't already exist, we cannot update one.
		if (existingRecord == null) {
			return null;
		}

		return persistModelRepository.save(record);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(final String id) {
		persistModelRepository.delete(id);
	}
}
