package com.easyapp.core.ui;

import java.util.Optional;

public class HeaderCell extends Cell {
	private Optional<String> label;
	
	public Optional<String> getLabel() {
		return label;
	}

	public void setLabel(final Optional<String> label) {
		this.label = label;
	}

	public void setLabel(final String label) {
		this.label = Optional.ofNullable(label);
	}
}
