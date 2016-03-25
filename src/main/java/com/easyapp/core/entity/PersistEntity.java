package com.easyapp.core.entity;

import static java.util.stream.Collectors.joining;

import java.lang.reflect.Field;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.easyapp.core.data.BaseData;
import com.easyapp.core.event.PersistEvent;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@MappedSuperclass
abstract public class PersistEntity extends BaseData implements PersistEvent {
	@Id
	private String id;

	@Column(nullable = false)
	private String createUserId;

	@Column(nullable = false)
	@JsonSerialize(using = JsonLocalDateTimeSerializer.class)
	@JsonDeserialize(using = JsonLocalDateTimeDeserializer.class)
	private LocalDateTime createTimestamp;

	@Column(nullable = false)
	private String updateUserId;

	@Column(nullable = false)
	@JsonSerialize(using = JsonLocalDateTimeSerializer.class)
	@JsonDeserialize(using = JsonLocalDateTimeDeserializer.class)
	private LocalDateTime updateTimestamp;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(final String createUserId) {
		this.createUserId = createUserId != null ? createUserId.trim() : null;
	}

	public LocalDateTime getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(final LocalDateTime createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(final String updateUserId) {
		this.updateUserId = updateUserId != null ? updateUserId.trim() : null;
	}

	public LocalDateTime getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(final LocalDateTime updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	private String getFieldValue(final String fieldName) {
		String fieldValue = "";

		try {
			Field field = this.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			fieldValue = String.valueOf(field.get(this));
		} catch (NoSuchFieldException nsfe) {
			nsfe.printStackTrace();
		} catch (SecurityException se) {
			se.printStackTrace();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
		} catch (IllegalAccessException iae) {
			iae.printStackTrace();
		}

		return fieldValue;
	}

	@PrePersist
	public void beforeCreate() {
		if (getId() == null || getId().trim().length() <= 0) {
			List<String> uniqueKeyFields = uniqueKeyFields();
			if (uniqueKeyFields != null && !uniqueKeyFields.isEmpty()) {
				setId(uniqueKeyFields.stream().map(name -> getFieldValue(name)).collect(joining("-")));
			}
		}

		if (getCreateUserId() == null) {
			setCreateUserId("SYSTEM");
		}

		setCreateTimestamp(LocalDateTime.now(Clock.systemUTC()));

		setUpdateUserId(getCreateUserId());
		setUpdateTimestamp(getCreateTimestamp());
	}

	@PreUpdate
	public void beforeUpdate() {
		if (getUpdateUserId() == null) {
			setUpdateUserId("SYSTEM");
		}

		setUpdateTimestamp(LocalDateTime.now(Clock.systemUTC()));
	}

	@Override
	public int hashCode() {
		return getId() != null && getId().length() > 0 ? getId().hashCode() : super.hashCode();
	}

	@Override
	@PostPersist
	public void afterCreate() {
		// Do nothing;
	}

	@Override
	@PostUpdate
	public void afterUpdate() {
		// Do nothing;
	}

	@Override
	public void onChange(Object newValue) {
		// Do nothing;
	}

	abstract public List<String> uniqueKeyFields();
}