package com.easyapp.core.code;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.easyapp.core.model.PersistModel;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"className", "version"}))
public class AppClass extends PersistModel {
	@NotNull
	private String className;

	@NotNull
	private String version;

	private String parentClass;

	public String getClassName() {
		return className;
	}

	public void setClassName(final String className) {
		this.className = className;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public String getParentClass() {
		return parentClass;
	}

	public void setParentClass(final String parentClass) {
		this.parentClass = parentClass;
	}
}