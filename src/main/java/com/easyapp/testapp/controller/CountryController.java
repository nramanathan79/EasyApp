package com.easyapp.testapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.easyapp.core.ui.Column;
import com.easyapp.core.ui.DataCell;
import com.easyapp.core.ui.InputField;
import com.easyapp.core.ui.InputField.InputType;
import com.easyapp.core.ui.Row;
import com.easyapp.core.ui.Table;
import com.easyapp.core.ui.Table.DataResource;

@Controller
public class CountryController {
	@RequestMapping(value = "/countries")
	public ModelAndView countries() {
		Table countryTable = new Table();

		countryTable.setEnableSearch(true);
		countryTable.setEnableColumnSort(true);
		countryTable.setEnableMultiColumnSort(true);
		countryTable.setEnableColumnFilter(true);
		countryTable.setEnableRowNum(true);
		countryTable.setEnableDataEdit(true);
		countryTable.setDataEditApiName("countries");
		countryTable.setDataResource(DataResource.api);
		countryTable.setStyleClassesString("table table-striped table-bordered bottom-5");

		Row headerRow = new Row();
		headerRow.addStyleClass("text-uppercase");

		countryTable.setHeaderRow(headerRow);

		InputField columnField = new InputField();
		columnField.setName("countryName");
		columnField.setLabel("Country");
		columnField.setPlaceholder("Country");
		columnField.setInputPattern("[A-Za-z ]+");
		columnField.setPatternMismatchMessage("Input may only contain words");
		columnField.setRequired(true);

		DataCell dataCell = new DataCell();
		dataCell.setField(columnField);

		Column dataColumn = new Column();
		dataColumn.setDataCell(dataCell);

		countryTable.addColumn(dataColumn);

		columnField = new InputField();
		columnField.setName("isoAlpha2Code");
		columnField.setLabel("Alpha-2 Code");
		columnField.setPlaceholder("Alpha-2 Code");
		columnField.setInputPattern("[A-Za-z]{2}");
		columnField.setPatternMismatchMessage("Input may only contain two alphabets");
		columnField.setMinLength(2);
		columnField.setMaxLength(2);
		columnField.setRequired(true);

		dataCell = new DataCell();
		dataCell.setField(columnField);
		dataCell.setCustomStyle("width:30px;");

		dataColumn = new Column();
		dataColumn.setDataCell(dataCell);

		countryTable.addColumn(dataColumn);

		columnField = new InputField();
		columnField.setName("isoAlpha3Code");
		columnField.setLabel("Alpha-3 Code");
		columnField.setPlaceholder("Alpha-3 Code");
		columnField.setInputPattern("[A-Za-z]{3}");
		columnField.setPatternMismatchMessage("Input may only contain three alphabets");
		columnField.setMinLength(3);
		columnField.setMaxLength(3);
		columnField.setRequired(true);

		dataCell = new DataCell();
		dataCell.setField(columnField);
		dataCell.setCustomStyle("width:45px;");

		dataColumn = new Column();
		dataColumn.setDataCell(dataCell);

		countryTable.addColumn(dataColumn);

		columnField = new InputField();
		columnField.setName("isoNumericCode");
		columnField.setLabel("Numeric Code");
		columnField.setPlaceholder("Numeric Code");
		columnField.setMinLength(3);
		columnField.setMaxLength(3);
		columnField.setInputPattern("[0-9]{3}");
		columnField.setPatternMismatchMessage("Input may only contain three-digit numbers");
		columnField.setRequired(true);

		dataCell = new DataCell();
		dataCell.setField(columnField);
		dataCell.setCustomStyle("width:45px;");

		dataColumn = new Column();
		dataColumn.setDataCell(dataCell);

		countryTable.addColumn(dataColumn);

		columnField = new InputField();
		columnField.setName("callingCode");
		columnField.setInputType(InputType.Number);
		columnField.setLabel("Phone Code");
		columnField.setPlaceholder("Phone Code");
		columnField.setMinValue(1);
		columnField.setMaxValue(999);
		columnField.setRequired(true);

		dataCell = new DataCell();
		dataCell.setField(columnField);
		dataCell.addStyleClass("text-right");
		dataCell.setCustomStyle("width:45px;");

		dataColumn = new Column();
		dataColumn.setDataCell(dataCell);

		countryTable.addColumn(dataColumn);

		columnField = new InputField();
		columnField.setName("currencyCode");
		columnField.setLabel("Currency Code");
		columnField.setPlaceholder("Currency Code");
		columnField.setInputPattern("[A-Za-z]{3}");
		columnField.setPatternMismatchMessage("Input may only contain three alphabets");
		columnField.setMinLength(3);
		columnField.setMaxLength(3);
		columnField.setRequired(true);

		dataCell = new DataCell();
		dataCell.setField(columnField);
		dataCell.setCustomStyle("width:45px;");

		dataColumn = new Column();
		dataColumn.setDataCell(dataCell);

		countryTable.addColumn(dataColumn);

		columnField = new InputField();
		columnField.setName("continent");
		columnField.setLabel("Continent");
		columnField.setPlaceholder("Continent");
		columnField.setInputPattern("[A-Za-z ]+");
		columnField.setPatternMismatchMessage("Input may only contain words");
		columnField.setRequired(true);

		dataCell = new DataCell();
		dataCell.setField(columnField);

		dataColumn = new Column();
		dataColumn.setDataCell(dataCell);

		countryTable.addColumn(dataColumn);

		columnField = new InputField();
		columnField.setName("capitalCity");
		columnField.setLabel("Capital");
		columnField.setPlaceholder("Capital");
		columnField.setInputPattern("[A-Za-z ]+");
		columnField.setPatternMismatchMessage("Input may only contain words");

		dataCell = new DataCell();
		dataCell.setField(columnField);

		dataColumn = new Column();
		dataColumn.setDataCell(dataCell);

		countryTable.addColumn(dataColumn);

		columnField = new InputField();
		columnField.setName("capitalCityTimeZone");
		columnField.setLabel("Capital Time Zone");
		columnField.setPlaceholder("Capital Time Zone");
		columnField.setInputPattern("[A-Za-z/_]+");
		columnField.setPatternMismatchMessage("Input may only contain words separated by _ or / with no spaces");

		dataCell = new DataCell();
		dataCell.setField(columnField);

		dataColumn = new Column();
		dataColumn.setDataCell(dataCell);

		countryTable.addColumn(dataColumn);

		ModelAndView modelAndView = new ModelAndView("countries");
		modelAndView.addObject("dataTable", countryTable);

		return modelAndView;
	}
}
