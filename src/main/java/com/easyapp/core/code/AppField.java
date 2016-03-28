package com.easyapp.core.code;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.easyapp.core.annotation.JsonStorage;
import com.easyapp.core.util.Pair;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "appName", "version", "className", "fieldName" }))
public class AppField extends AppBaseCode {
	public enum FieldMode {
		Simple, Object, List, Set, Map
	};

	public enum FieldType {
		Character, Boolean, String, Byte, Short, Integer, Long, Float, Double, LocalDate, LocalDateTime
	};

	@NotNull
	private String appName;

	@NotNull
	private String version;

	@NotNull
	private String className;

	@NotNull
	private String fieldName;

	@NotNull
	private String label;

	@NotNull
	@Enumerated(EnumType.STRING)
	private FieldMode mode;

	@Enumerated(EnumType.STRING)
	private FieldType type;

	private String objectType;

	private Integer maxLength;

	private Boolean required;

	private Boolean persist;

	@Transient
	private Object value;

	@Transient
	@JsonStorage
	private List<Pair<String, String>> validValues;

	public AppField() {
		mode = FieldMode.Simple;
		type = FieldType.String;
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

	public String getClassName() {
		return className;
	}

	public void setClassName(final String className) {
		this.className = className;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(final String fieldName) {
		this.fieldName = fieldName;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(final String label) {
		this.label = label;
	}

	public FieldMode getMode() {
		return mode;
	}

	public void setMode(final FieldMode mode) {
		this.mode = mode;
	}

	public FieldType getType() {
		return type;
	}

	public void setType(final FieldType type) {
		this.type = type;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(final String objectType) {
		this.objectType = objectType;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(final Integer maxLength) {
		this.maxLength = maxLength;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(final Boolean required) {
		this.required = required;
	}

	public Boolean getPersist() {
		return persist;
	}

	public void setPersist(final Boolean persist) {
		this.persist = persist;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(final Character value) {
		this.value = new Character(value);
	}

	public void setValue(final Boolean value) {
		this.value = new Boolean(value);
	}

	public void setValue(final String value) {
		this.value = new String(value);
	}

	public void setValue(final Byte value) {
		this.value = new Byte(value);
	}

	public void setValue(final Short value) {
		this.value = new Short(value);
	}

	public void setValue(final Integer value) {
		this.value = new Integer(value);
	}

	public void setValue(final Long value) {
		this.value = new Long(value);
	}

	public void setValue(final Float value) {
		this.value = new Float(value);
	}

	public void setValue(final Double value) {
		this.value = new Double(value);
	}

	public void setValue(final LocalDate value) {
		this.value = LocalDate.of(value.getYear(), value.getMonthValue(), value.getDayOfMonth());
	}

	public void setValue(final LocalDateTime value) {
		this.value = LocalDateTime.of(value.toLocalDate(), value.toLocalTime());
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public List<Pair<String, String>> getValidValues() {
		return validValues;
	}

	public void setValidValues(List<Pair<String, String>> validValues) {
		this.validValues = validValues;
	}

	public void addValidValue(final Pair<String, String> validValue) {
		if (this.validValues == null) {
			this.validValues = new ArrayList<>();
		}

		this.validValues.add(validValue);
	}

	public void removeValidValue(final Pair<String, String> validValue) {
		if (this.validValues != null) {
			this.validValues.remove(validValue);

			if (this.validValues.isEmpty()) {
				this.validValues = null;
			}
		}
	}

	@Override
	public List<String> uniqueKeyFields() {
		return Arrays.asList("appName", "version", "className", "fieldName");
	}
}