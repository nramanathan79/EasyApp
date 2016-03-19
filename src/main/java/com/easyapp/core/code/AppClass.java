package com.easyapp.core.code;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.easyapp.core.annotation.JsonStorage;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"className", "appName", "version"}))
public class AppClass extends AppBaseCode {
	@NotNull
	private String className;

	@NotNull
	private String appName;

	@NotNull
	private String version;

	@Transient
	@JsonStorage
	private List<String> parentClasses;

	public String getClassName() {
		return className;
	}

	public void setClassName(final String className) {
		this.className = className;
	}

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

	public List<String> getParentClass() {
		return parentClasses;
	}

	public void addParentClass(final String parentClass) {
		this.parentClasses.add(parentClass);
	}
}