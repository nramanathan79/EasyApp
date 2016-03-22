package com.easyapp.core.code;

import static java.util.stream.Collectors.joining;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.easyapp.core.annotation.JsonStorage;
import com.easyapp.core.entity.PersistEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

@MappedSuperclass
abstract public class AppBaseCode extends PersistEntity {
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
	private Stream<Field> getJsonStorageFieldStream() {
		Class objClass = this.getClass();
		List<Field> fields = new ArrayList<>();

		while (objClass != null && AppBaseCode.class.isAssignableFrom(objClass)) {
			fields.addAll(Arrays.asList(objClass.getDeclaredFields()));
			objClass = objClass.getSuperclass();
		}

		return fields.isEmpty() ? null : fields.stream().filter(f -> f.isAnnotationPresent(JsonStorage.class));
	}

	private void setJsonStorage() {
		Stream<Field> jsonStorageFieldStream = getJsonStorageFieldStream();

		if (jsonStorageFieldStream != null) {
			this.jsonStorage = "{" + jsonStorageFieldStream.map(f -> getJson(f)).collect(joining(",")) + "}";
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
			Stream<Field> jsonStorageFieldStream = getJsonStorageFieldStream();

			if (jsonStorageFieldStream != null) {
				AppBaseCode baseCode = (AppBaseCode) fromJson(getJsonStorage(), this.getClass());
				jsonStorageFieldStream.forEach(f -> mapField(f, baseCode));
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
