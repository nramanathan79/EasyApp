package com.easyapp.core.ui;

import java.util.Optional;

public class Column {
	private Optional<HeaderCell> headerCell;
	private DataCell dataCell;

	public Optional<HeaderCell> getHeaderCell() {
		return headerCell;
	}

	public void setHeaderCell(final Optional<HeaderCell> headerCell) {
		this.headerCell = headerCell;
	}

	public void setHeaderCell(final HeaderCell headerCell) {
		this.headerCell = Optional.ofNullable(headerCell);
	}

	public DataCell getDataCell() {
		return dataCell;
	}

	public void setDataCell(final DataCell dataCell) {
		this.dataCell = dataCell;
	}
}
