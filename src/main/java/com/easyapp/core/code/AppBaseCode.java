package com.easyapp.core.code;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.easyapp.core.annotation.JsonStorage;
import com.easyapp.core.model.PersistModel;
import com.fasterxml.jackson.core.JsonProcessingException;

@MappedSuperclass
public class AppBaseCode extends PersistModel {
	private String jsonStorage;

	public String getJsonStorage() {
		return jsonStorage;
	}

	private Object getFieldValue(Field field, AppBaseCode baseCode) {
		Object value = null;

		try {
			field.setAccessible(true);
			value = field.get(baseCode);
		} catch (IllegalAccessException iae) {

		}

		return value;
	}

	private String getJson(Field field) {
		String json = "\"" + field.getName() + "\":";

		try {
			json += jsonMapper.writeValueAsString(getFieldValue(field, this));
		} catch (JsonProcessingException jpe) {
			jpe.printStackTrace();
		}

		return json;
	}

	@SuppressWarnings(value = { "rawtypes" })
	private List<Field> getJsonStorageFields() {
		Class objClass = this.getClass();
		List<Field> fields = new ArrayList<>();

		while (objClass != null && AppBaseCode.class.isAssignableFrom(objClass)) {
			fields.addAll(Arrays.asList(objClass.getDeclaredFields()));
			objClass = objClass.getSuperclass();
		}

		return fields.stream().filter(f -> f.isAnnotationPresent(JsonStorage.class)).collect(toList());
	}

	private void setJsonStorage() {
		List<Field> jsonFields = getJsonStorageFields();

		if (!jsonFields.isEmpty()) {
			this.jsonStorage = "{" + jsonFields.stream().map(f -> getJson(f)).collect(joining(",")) + "}";
		}
	}

	private void mapField(Field field, final AppBaseCode baseCode) {
		try {
			field.set(this, getFieldValue(field, baseCode));
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
		} catch (IllegalAccessException iae) {
			iae.printStackTrace();
		}
	}

	private void restoreFromJson() {
		if (getJsonStorage() != null && getJsonStorage().length() > 0) {
			List<Field> jsonStorageFields = getJsonStorageFields();

			if (!jsonStorageFields.isEmpty()) {
				AppBaseCode baseCode = (AppBaseCode) fromJson(getJsonStorage(), this.getClass());
				jsonStorageFields.stream().forEach(f -> mapField(f, baseCode));
			}
		}
	}

	private void clearJsonStorage() {
		this.jsonStorage = null;
	}

	@Override
	@PrePersist
	public void beforeCreate() {
		super.beforeCreate();
		setJsonStorage();
	}

	@Override
	@PreUpdate
	public void beforeUpdate() {
		super.beforeUpdate();
		setJsonStorage();
	}

	@PostPersist
	public void afterCreate() {
		clearJsonStorage();
	}

	@PostUpdate
	public void afterUpdate() {
		clearJsonStorage();
	}

	@PostLoad
	public void afterLoad() {
		restoreFromJson();
		clearJsonStorage();
	}
}
