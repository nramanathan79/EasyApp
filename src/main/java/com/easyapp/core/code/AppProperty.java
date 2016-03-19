package com.easyapp.core.code;

import java.util.ArrayList;
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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"className", "propertyName", "appName", "version"}))
public class AppProperty extends AppBaseCode {
	public enum PropertyMode {Simple, Object, List, Set, Map};
	public enum PropertyType {Character, Boolean, String, Byte, Short, Integer, Long, Float, Double, LocalDate, LocalDateTime};
	public enum PropertyInputType {Text, Password, TextArea, RadioButton, CheckBox, Dropdown, ComboBox, Number, Amount, Date, DateTime, Phone, Email, URL, Search, Color, Range};
	
	@NotNull
	@JsonStorage
	private String className;
	
	@NotNull
	private String propertyName;
	
	@NotNull
	private String propertyLabel;
	
	@NotNull
	@JsonStorage
	private String appName;

	@NotNull
	private String version;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private PropertyMode propertyMode;
	
	@Enumerated(EnumType.STRING)
	private PropertyType propertyType;
	
	private String propertyObjectType;
	
	@Enumerated(EnumType.STRING)
	private PropertyInputType propertyInputType;
	
	private Integer propertyMinLength;
	
	private Integer propertyMaxLength;
	
	private Integer propertyMinValue;
	
	private Integer PropertyMaxValue;
	
	private Integer propertyStepInterval;
	
	private String propertyInputPattern; 

	private Boolean propertyRequired;
	
	private Boolean propertyPersist;
	
	@Transient
	private String propertyValue;
	
	@Transient
	@JsonStorage
	private List<Pair<String, String>> propertyValidValues;

	public String getClassName() {
		return className;
	}

	public void setClassName(final String className) {
		this.className = className;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(final String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyLabel() {
		return propertyLabel;
	}

	public void setPropertyLabel(final String propertyLabel) {
		this.propertyLabel = propertyLabel;
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

	public PropertyMode getPropertyMode() {
		return propertyMode;
	}

	public void setPropertyMode(final PropertyMode propertyMode) {
		this.propertyMode = propertyMode;
	}

	public PropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(final PropertyType propertyType) {
		this.propertyType = propertyType;
	}

	public String getPropertyObjectType() {
		return propertyObjectType;
	}

	public void setPropertyObjectType(final String propertyObjectType) {
		this.propertyObjectType = propertyObjectType;
	}

	public PropertyInputType getPropertyInputType() {
		return propertyInputType;
	}

	public void setPropertyInputType(final PropertyInputType propertyInputType) {
		this.propertyInputType = propertyInputType;
	}

	public Integer getPropertyMinLength() {
		return propertyMinLength;
	}

	public void setPropertyMinLength(final Integer propertyMinLength) {
		this.propertyMinLength = propertyMinLength;
	}

	public Integer getPropertyMaxLength() {
		return propertyMaxLength;
	}

	public void setPropertyMaxLength(final Integer propertyMaxLength) {
		this.propertyMaxLength = propertyMaxLength;
	}

	public Integer getPropertyMinValue() {
		return propertyMinValue;
	}

	public void setPropertyMinValue(final Integer propertyMinValue) {
		this.propertyMinValue = propertyMinValue;
	}

	public Integer getPropertyMaxValue() {
		return PropertyMaxValue;
	}

	public void setPropertyMaxValue(final Integer propertyMaxValue) {
		PropertyMaxValue = propertyMaxValue;
	}

	public Integer getPropertyStepInterval() {
		return propertyStepInterval;
	}

	public void setPropertyStepInterval(final Integer propertyStepInterval) {
		this.propertyStepInterval = propertyStepInterval;
	}

	public String getPropertyInputPattern() {
		return propertyInputPattern;
	}

	public void setPropertyInputPattern(final String propertyInputPattern) {
		this.propertyInputPattern = propertyInputPattern;
	}

	public Boolean getPropertyRequired() {
		return propertyRequired;
	}

	public void setPropertyRequired(final Boolean propertyRequired) {
		this.propertyRequired = propertyRequired;
	}

	public Boolean getPropertyPersist() {
		return propertyPersist;
	}

	public void setPropertyPersist(final Boolean propertyPersist) {
		this.propertyPersist = propertyPersist;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(final String propertyValue) {
		this.propertyValue = propertyValue;
	}

	public List<Pair<String, String>> getPropertyValidValues() {
		return propertyValidValues;
	}

	public void addPropertyValidValue(Pair<String, String> propertyValidValue) {
		if (this.propertyValidValues == null) {
			this.propertyValidValues = new ArrayList<>();
		}

		this.propertyValidValues.add(propertyValidValue);
	}
}