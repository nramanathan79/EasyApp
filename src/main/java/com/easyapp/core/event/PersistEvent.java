package com.easyapp.core.event;

public interface PersistEvent extends Event {
	void beforeCreate();
	void beforeUpdate();
	void afterCreate();
	void afterUpdate();
}
