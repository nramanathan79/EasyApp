package com.easyapp.core.ui;

public class DataCell extends Cell {
	private Boolean showLabel;
	private Boolean labelOnTop;
	private InputField field;

	public Boolean getShowLabel() {
		return showLabel;
	}

	public void setShowLabel(final Boolean showLabel) {
		this.showLabel = showLabel;
	}

	public Boolean getLabelOnTop() {
		return labelOnTop;
	}

	public void setLabelOnTop(final Boolean labelOnTop) {
		this.labelOnTop = labelOnTop;
	}

	public InputField getField() {
		return field;
	}

	public void setField(final InputField field) {
		this.field = field;
	}
}
