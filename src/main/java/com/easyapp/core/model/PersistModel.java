package com.easyapp.core.model;

import java.time.Clock;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@MappedSuperclass
abstract public class PersistModel extends BaseModel {
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@GeneratedValue(generator = "system-uuid")
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

	@PrePersist
	public void beforeCreate() {
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
}