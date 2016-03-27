package com.easyapp.core.ui;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Table extends BaseUI {
	public enum PaginationType {
		PreviousNext, Numeric5, Numeric10, PageXOfY, Progressive
	};

	public enum DataResource {
		api, list
	};

	private Boolean enableSearch;
	private Boolean enableColumnSort;
	private Boolean enableMultiColumnSort;
	private Boolean enableColumnFilter;
	private Boolean enablePagination;
	private Optional<PaginationType> paginationType;
	private Optional<Short> recordsPerPage;
	private DataResource dataResource;
	private Boolean enableDataEdit;
	private Optional<String> dataEditApiName;
	private Boolean enableRowNum;
	private List<Column> columns;
	private Optional<Row> headerRow;
	private Optional<Row> dataRow;

	public Table() {
		setEnableSearch(false);
		setEnableColumnSort(false);
		setEnableMultiColumnSort(false);
		setEnableColumnFilter(false);
		setEnableRowNum(false);
		setEnableDataEdit(false);
		setEnablePagination(false);
	}

	public Boolean getEnableSearch() {
		return enableSearch;
	}

	public void setEnableSearch(final Boolean enableSearch) {
		this.enableSearch = enableSearch;
	}

	public Boolean getEnableColumnSort() {
		return enableColumnSort;
	}

	public void setEnableColumnSort(final Boolean enableColumnSort) {
		this.enableColumnSort = enableColumnSort;
	}

	public Boolean getEnableMultiColumnSort() {
		return enableMultiColumnSort;
	}

	public void setEnableMultiColumnSort(final Boolean enableMultiColumnSort) {
		this.enableMultiColumnSort = enableMultiColumnSort;
	}

	public Boolean getEnableColumnFilter() {
		return enableColumnFilter;
	}

	public void setEnableColumnFilter(final Boolean enableColumnFilter) {
		this.enableColumnFilter = enableColumnFilter;
	}

	public Boolean getEnablePagination() {
		return enablePagination;
	}

	public void setEnablePagination(final Boolean enablePagination) {
		this.enablePagination = enablePagination;
	}

	public Optional<PaginationType> getPaginationType() {
		return paginationType;
	}

	public void setPaginationType(Optional<PaginationType> paginationType) {
		this.paginationType = paginationType;
	}

	public void setPaginationType(final PaginationType paginationType) {
		this.paginationType = Optional.ofNullable(paginationType);
	}

	public Optional<Short> getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(Optional<Short> recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public void setRecordsPerPage(final Short recordsPerPage) {
		this.recordsPerPage = Optional.ofNullable(recordsPerPage);
	}

	public DataResource getDataResource() {
		return dataResource;
	}

	public void setDataResource(DataResource dataResource) {
		this.dataResource = dataResource;
	}

	public Boolean getEnableDataEdit() {
		return enableDataEdit;
	}

	public void setEnableDataEdit(final Boolean enableDataEdit) {
		this.enableDataEdit = enableDataEdit;
	}

	public Optional<String> getDataEditApiName() {
		return dataEditApiName;
	}

	public void setDataEditApiName(Optional<String> dataEditApiName) {
		this.dataEditApiName = dataEditApiName;
	}

	public void setDataEditApiName(final String dataEditApiName) {
		this.dataEditApiName = Optional.ofNullable(dataEditApiName);
	}

	public Boolean getEnableRowNum() {
		return enableRowNum;
	}

	public void setEnableRowNum(final Boolean enableRowNum) {
		this.enableRowNum = enableRowNum;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(final List<Column> columns) {
		this.columns = columns;
	}

	public void addColumn(final Column column) {
		if (column != null) {
			if (this.columns == null) {
				this.columns = new LinkedList<>();
			}

			this.columns.add(column);
		}
	}

	public void removeColumn(final int columnIndex) {
		if (columnIndex > 0 && this.columns != null && this.columns.size() >= columnIndex) {
			this.columns.remove(columnIndex - 1);
		}
	}

	public Optional<Row> getHeaderRow() {
		return headerRow;
	}

	public void setHeaderRow(Optional<Row> headerRow) {
		this.headerRow = headerRow;
	}

	public void setHeaderRow(Row headerRow) {
		this.headerRow = Optional.ofNullable(headerRow);
	}

	public Optional<Row> getDataRow() {
		return dataRow;
	}

	public void setDataRow(Optional<Row> dataRow) {
		this.dataRow = dataRow;
	}

	public void setDataRow(Row dataRow) {
		this.dataRow = Optional.ofNullable(dataRow);
	}
}
