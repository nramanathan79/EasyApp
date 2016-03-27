package com.easyapp.core.ui;

import java.util.Optional;

public class Cell extends BaseUI {
	private Optional<Short> rowSpan;
	private Optional<Short> colSpan;

	public Optional<Short> getRowSpan() {
		return rowSpan;
	}

	public void setRowSpan(final Optional<Short> rowSpan) {
		this.rowSpan = rowSpan;
	}

	public void setRowSpan(final Short rowSpan) {
		this.rowSpan = Optional.ofNullable(rowSpan);
	}

	public Optional<Short> getColSpan() {
		return colSpan;
	}

	public void setColSpan(final Optional<Short> colSpan) {
		this.colSpan = colSpan;
	}

	public void setColSpan(final Short colSpan) {
		this.colSpan = Optional.ofNullable(colSpan);
	}
}
