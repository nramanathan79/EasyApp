package com.easyapp.core.model;

import java.time.LocalDateTime;

import com.easyapp.core.BaseData;
import com.easyapp.core.entity.PersistEntity;

abstract public class PersistModel extends BaseData {
	private String id;

	private String createUserId;

	private LocalDateTime createTimestamp;

	private String updateUserId;

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

	public static <T extends PersistModel> T buildModelFromEntity(PersistEntity persistEntity, Class<T> modelClass) {
		T persistModel = null;

		try {
			persistModel = modelClass.newInstance();
			persistModel.mapFrom(persistEntity);
		} catch (InstantiationException ie) {
			ie.printStackTrace();
		} catch (IllegalAccessException iae) {
			iae.printStackTrace();
		}

		return persistModel;
	}

	abstract public void mapFrom(PersistEntity persistEntity);

	abstract public <T extends PersistEntity> T mapTo();
}
