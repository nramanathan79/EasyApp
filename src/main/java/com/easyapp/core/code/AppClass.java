package com.easyapp.core.code;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.easyapp.core.annotation.JsonStorage;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"appName", "version", "className"}))
public class AppClass extends AppBaseCode {
	@NotNull
	private String appName;

	@NotNull
	private String version;

	@NotNull
	private String className;

	@Transient
	@JsonStorage
	private List<String> parentClasses;

	public String getAppName() {
		return appName;
	}

	public void setAppName(final String appName) {
		this.appName = appName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(final String className) {
		this.className = className;
	}

	public List<String> getParentClass() {
		return parentClasses;
	}

	public void addParentClass(final String parentClass) {
		this.parentClasses.add(parentClass);
	}

	public void removeParentClass(final String parentClass) {
		this.parentClasses.remove(parentClass);
	}

	@Override
	public List<String> uniqueKeyFields() {
		return Arrays.asList("appName", "version", "className");
	}
}