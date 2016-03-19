package com.easyapp.core.code;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"version", "appName"}))
public class AppVersion extends AppBaseCode {
	@NotNull
	private String version;

	@NotNull
	private String appName;

	@NotNull
	private Boolean locked = false;
	
	private String lockPassword;

	public String getVersion() {
		return version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

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
}