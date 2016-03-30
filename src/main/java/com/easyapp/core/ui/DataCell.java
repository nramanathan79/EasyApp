package com.easyapp.core.ui;

import java.util.Optional;

public class DataCell extends Cell {
	public enum LabelPosition { top, left, bottom, right };
	
	private Boolean showLabel;
	private LabelPosition labelPosition;
	private Optional<String> label;
	private InputField field;

	public Boolean getShowLabel() {
		return showLabel;
	}

	public void setShowLabel(final Boolean showLabel) {
		this.showLabel = showLabel;
	}
	
	public LabelPosition getLabelPosition() {
		return labelPosition;
	}

	public void setLabelPosition(LabelPosition labelPosition) {
		this.labelPosition = labelPosition;
	}

	public Optional<String> getLabel() {
		return label;
	}

	public void setLabel(final Optional<String> label) {
		this.label = label;
	}

	public void setLabel(final String label) {
		this.label = Optional.ofNullable(label);
	}

	public InputField getField() {
		return field;
	}

	public void setField(final InputField field) {
		this.field = field;
	}
}
