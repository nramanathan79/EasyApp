package com.easyapp.core.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.easyapp.core.entity.PersistEntity;
import com.easyapp.core.model.PersistModel;
import com.easyapp.core.repository.PersistEntityRepository;

@Transactional
public class PersistModelServiceImpl<T extends PersistModel> extends PersistModelService<T> {
	public PersistModelServiceImpl(Class<T> modelClass,
			PersistEntityRepository<? extends PersistEntity> persistEntityRepository) {
		super(modelClass, persistEntityRepository);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<T> findAll() {
		return persistEntityRepository.findAll().stream()
				.map(persistEntity -> PersistModel.buildModelFromEntity(persistEntity, modelClass)).collect(toList());
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public T findOne(final String id) {
		return PersistModel.buildModelFromEntity(persistEntityRepository.findOne(id), modelClass);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public T create(final T record) {
		// If the record is not supplied, we cannot create it.
		if (record == null) {
			return null;
		}
		
		// If record already exists, we cannot create one.
		if (record.getId() != null) {
			return null;
		}
		
		PersistEntity savedEntity = persistEntityRepository.save(record.mapTo());
		return PersistModel.buildModelFromEntity(savedEntity, modelClass);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public T update(final T record) {
		// If the record is not supplied, we cannot create it.
		if (record == null) {
			return null;
		}
		
		PersistEntity existingRecord = persistEntityRepository.findOne(record.getId());

		// If record doesn't already exist, we cannot update one.
		if (existingRecord == null) {
			return null;
		}
		
		PersistEntity savedEntity = persistEntityRepository.save(record.mapTo().merge(existingRecord));
		return PersistModel.buildModelFromEntity(savedEntity, modelClass);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(final String id) {
		persistEntityRepository.delete(id);
	}
}
