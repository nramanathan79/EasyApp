package com.easyapp.core.ui;

public class InputField extends BaseUI {
	public enum InputType {
		Text, Password, TextArea, RadioButton, CheckBox, Dropdown, ComboBox, Integer, Decimal, Amount, Date, DateTime, Phone, Email, URL, Search, Color, Range
	};
	
	private String label;
	private String name;
	private Boolean required;
	private InputType inputType;  
	private String stepInterval;
	private String inputPattern;
	private String patternMismatchMessage;
	private String placeholder;
	private Boolean trim;
	private Integer minLength;
	private Integer maxLength;
	private Double minValue;
	private Double maxValue;
	
	public InputField() {
		inputType = InputType.Text;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(final String label) {
		this.label = label;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
	
	public Boolean getRequired() {
		return required;
	}
	
	public void setRequired(final Boolean required) {
		this.required = required;
	}
	
	public InputType getInputType() {
		return inputType;
	}
	
	public void setInputType(final InputType inputType) {
		this.inputType = inputType;
	}
	
	public String getStepInterval() {
		return stepInterval;
	}
	
	public void setStepInterval(final String stepInterval) {
		this.stepInterval = stepInterval;
	}
	
	public void setStepInterval(final Integer stepInterval) {
		this.stepInterval = String.valueOf(stepInterval);
	}
	
	public void setStepInterval(final Double stepInterval) {
		this.stepInterval = String.valueOf(stepInterval);
	}
	
	public String getInputPattern() {
		return inputPattern;
	}
	
	public void setInputPattern(final String inputPattern) {
		this.inputPattern = inputPattern;
	}
	
	public String getPatternMismatchMessage() {
		return patternMismatchMessage;
	}
	
	public void setPatternMismatchMessage(final String patternMismatchMessage) {
		this.patternMismatchMessage = patternMismatchMessage;
	}
	
	public String getPlaceholder() {
		return placeholder;
	}
	
	public void setPlaceholder(final String placeholder) {
		this.placeholder = placeholder;
	}
	
	public Boolean getTrim() {
		return trim;
	}
	
	public void setTrim(final Boolean trim) {
		this.trim = trim;
	}
	
	public Integer getMinLength() {
		return minLength;
	}
	
	public void setMinLength(final Integer minLength) {
		this.minLength = minLength;
	}
	
	public Integer getMaxLength() {
		return maxLength;
	}
	
	public void setMaxLength(final Integer maxLength) {
		this.maxLength = maxLength;
	}
	
	public Double getMinValue() {
		return minValue;
	}
	
	public void setMinValue(final Double minValue) {
		this.minValue = minValue;
	}
	
	public Double getMaxValue() {
		return maxValue;
	}
	
	public void setMaxValue(final Double maxValue) {
		this.maxValue = maxValue;
	}
}
