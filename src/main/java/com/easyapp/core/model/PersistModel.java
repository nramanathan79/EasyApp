package com.easyapp.core.model;

import java.time.LocalDateTime;
import java.util.Optional;

import com.easyapp.core.data.BaseData;
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

	abstract protected void mapFrom(final PersistEntity persistEntity);

	abstract protected <T extends PersistEntity> T mapTo();

	@SuppressWarnings(value = { "unchecked" })
	public static <T extends PersistModel> Optional<T> buildModelFromEntity(final PersistEntity persistEntity,
			final Class<T> modelClass) {
		Optional<T> persistModel = Optional.empty();

		if (persistEntity != null) {
			try {
				PersistModel model = modelClass.newInstance();

				model.setId(persistEntity.getId());
				model.setCreateUserId(persistEntity.getCreateUserId());
				model.setCreateTimestamp(persistEntity.getCreateTimestamp());
				model.setUpdateUserId(persistEntity.getUpdateUserId());
				model.setUpdateTimestamp(persistEntity.getUpdateTimestamp());

				model.mapFrom(persistEntity);

				persistModel = Optional.of((T) model);
			} catch (InstantiationException ie) {
				ie.printStackTrace();
			} catch (IllegalAccessException iae) {
				iae.printStackTrace();
			}
		}

		return persistModel;
	}

	@Override
	public void onChange(Object newValue) {
		// Do nothing.
	}

	public <T extends PersistEntity> T buildEntityFromModelAndMerge(final PersistEntity existingRecord) {
		T newRecord = mapTo();

		if (existingRecord != null) {
			newRecord.setId(existingRecord.getId());
			newRecord.setCreateUserId(existingRecord.getCreateUserId());
			newRecord.setCreateTimestamp(existingRecord.getCreateTimestamp());
		}

		return newRecord;
	}

	public <T extends PersistEntity> T buildEntityFromModel() {
		return buildEntityFromModelAndMerge(null);
	}
}
