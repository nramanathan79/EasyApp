package com.easyapp.core.model;

import java.time.Clock;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

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
	private String createUser;

	@Column(nullable = false)
	@JsonSerialize(using = JsonLocalDateTimeSerializer.class)
	@JsonDeserialize(using = JsonLocalDateTimeDeserializer.class)
	private LocalDateTime createTimestamp;

	@Column(nullable = false)
	private String updateUser;

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

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(final String createUser) {
		this.createUser = createUser != null ? createUser.trim() : null;
	}

	public LocalDateTime getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(final LocalDateTime createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(final String updateUser) {
		this.updateUser = updateUser != null ? updateUser.trim() : null;
	}

	public LocalDateTime getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(final LocalDateTime updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	@PrePersist
	public void setAudit() {
		if (getCreateUser() == null) {
			setCreateUser("SYSTEM");
		}

		if (getCreateTimestamp() == null) {
			setCreateTimestamp(LocalDateTime.now(Clock.systemUTC()));
		}

		if (getUpdateUser() == null) {
			setUpdateUser("SYSTEM");
		}

		setUpdateTimestamp(LocalDateTime.now(Clock.systemUTC()));
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}
}