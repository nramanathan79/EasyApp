package com.easyapp.core.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.easyapp.core.entity.PersistEntity;
import com.easyapp.core.model.PersistModel;
import com.easyapp.core.repository.PersistEntityRepository;
import com.easyapp.core.service.PersistModelService;
import com.easyapp.core.service.PersistModelServiceImpl;

abstract public class PersistModelRestController<T extends PersistModel> {
	private PersistModelService<T> persistModelService;

	public PersistModelRestController(Class<T> modelClass,
			PersistEntityRepository<? extends PersistEntity> persistEntityRepository) {
		persistModelService = new PersistModelServiceImpl<>(modelClass, persistEntityRepository);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<T>> findAll() {
		List<T> records = persistModelService.findAll();

		if (records == null || records.size() <= 0) {
			return new ResponseEntity<List<T>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<T>>(records, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<T> findOne(@PathVariable("id") final String id) {
		Optional<T> record = persistModelService.findOne(id);

		if (record == null) {
			return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<T>(record.get(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<T> createUsingPost(@RequestBody @Valid T record) {
		Optional<T> recordCreated = persistModelService.create(record);

		if (recordCreated == null || !recordCreated.isPresent()) {
			return new ResponseEntity<T>(HttpStatus.CONFLICT);
		}

		return new ResponseEntity<T>(recordCreated.get(), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<T> createUsingPut(@RequestBody @Valid T record) {
		return createUsingPost(record);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<T> updateUsingPost(@RequestBody @Valid T record) {
		return updateUsingPut(record);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<T> updateUsingPut(@RequestBody @Valid T record) {
		Optional<T> recordUpdated = persistModelService.update(record);

		if (recordUpdated == null || !recordUpdated.isPresent()) {
			return new ResponseEntity<T>(HttpStatus.CONFLICT);
		}

		return new ResponseEntity<T>(recordUpdated.get(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<T> delete(@PathVariable("id") final String id) {
		persistModelService.delete(id);
		return new ResponseEntity<T>(HttpStatus.OK);
	}
}
