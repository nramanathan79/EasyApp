package com.easyapp.core.code;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"appName", "version"}))
public class AppVersion extends AppBaseCode {
	@NotNull
	private String appName;

	@NotNull
	private String version;

	@NotNull
	private Boolean locked = false;
	
	private String lockPassword;

	public String getAppName() {
		return appName;
	}

	public void setAppName(final String appName) {
		this.appName = appName;
	}

	public Boolean getLocked() {
		return locked;
	}

	private void setLocked(Boolean locked) {
		this.locked = locked;
	}

	private String getLockPassword() {
		return lockPassword;
	}

	private void setLockPassword(String lockPassword) {
		this.lockPassword = lockPassword;
	}

	public void lock(String password) throws SecurityException {
		if (password == null || password.trim().length() <= 0) {
			throw new SecurityException("Password may not be blank");
		}

		setLocked(true);
		setLockPassword(password.trim());
	}

	public void unlock(String password) throws SecurityException {
		if (password == null || password.trim().length() <= 0) {
			throw new SecurityException("Password may not be blank");
		}
		
		if (password.equals(getLockPassword())) {
			setLocked(false);
			setLockPassword(null);
		}
	}

	@Override
	public List<String> uniqueKeyFields() {
		return Arrays.asList("appName", "version");
	}
}