package com.easyapp.testapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.easyapp.core.code.AppField;
import com.easyapp.core.code.AppField.FieldInputType;
import com.easyapp.core.code.AppField.FieldMode;
import com.easyapp.core.code.AppField.FieldType;
import com.easyapp.core.ui.Column;
import com.easyapp.core.ui.DataCell;
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
		countryTable.setStyleClassesString("table table-striped table-bordered");
		
		Row headerRow = new Row();
		headerRow.addStyleClass("text-uppercase");
		
		countryTable.setHeaderRow(headerRow);
		
		AppField columnField = new AppField();
		columnField.setFieldName("countryName");
		columnField.setInputType(FieldInputType.Text);
		columnField.setLabel("Country");
		columnField.setPlaceholder("Country");
		columnField.setMode(FieldMode.Simple);
		columnField.setType(FieldType.String);
		columnField.setInputPattern("[A-Za-z ]+");
		columnField.setPatternMismatchMessage("Input may only contain words");
		columnField.setRequired(true);

		DataCell dataCell = new DataCell();
		dataCell.setField(columnField);
		
		Column dataColumn = new Column();
		dataColumn.setDataCell(dataCell);
		
		countryTable.addColumn(dataColumn);
		
		columnField = new AppField();
		columnField.setFieldName("isoAlpha2Code");
		columnField.setInputType(FieldInputType.Text);
		columnField.setLabel("Alpha-2 Code");
		columnField.setPlaceholder("Alpha-2 Code");
		columnField.setMode(FieldMode.Simple);
		columnField.setType(FieldType.String);
		columnField.setInputPattern("[A-Za-z]{2}");
		columnField.setPatternMismatchMessage("Input may only contain two alphabets");
		columnField.setMinLength(2);
		columnField.setMaxLength(2);
		columnField.setRequired(true);

		dataCell = new DataCell();
		dataCell.setField(columnField);
		dataCell.setCustomStyle("width:25px;");
		
		dataColumn = new Column();
		dataColumn.setDataCell(dataCell);
		
		countryTable.addColumn(dataColumn);

		columnField = new AppField();
		columnField.setFieldName("isoAlpha3Code");
		columnField.setInputType(FieldInputType.Text);
		columnField.setLabel("Alpha-3 Code");
		columnField.setPlaceholder("Alpha-3 Code");
		columnField.setMode(FieldMode.Simple);
		columnField.setType(FieldType.String);
		columnField.setInputPattern("[A-Za-z]{3}");
		columnField.setPatternMismatchMessage("Input may only contain three alphabets");
		columnField.setMinLength(3);
		columnField.setMaxLength(3);
		columnField.setRequired(true);
		dataCell.setCustomStyle("width:25px;");

		dataCell = new DataCell();
		dataCell.setField(columnField);
		
		dataColumn = new Column();
		dataColumn.setDataCell(dataCell);
		
		countryTable.addColumn(dataColumn);

		columnField = new AppField();
		columnField.setFieldName("isoNumericCode");
		columnField.setInputType(FieldInputType.Text);
		columnField.setLabel("Numeric Code");
		columnField.setPlaceholder("Numeric Code");
		columnField.setMode(FieldMode.Simple);
		columnField.setType(FieldType.String);
		columnField.setMinLength(3);
		columnField.setMaxLength(3);
		columnField.setInputPattern("[0-9]{3}");
		columnField.setPatternMismatchMessage("Input may only contain three-digit numbers");
		columnField.setRequired(true);

		dataCell = new DataCell();
		dataCell.setField(columnField);
		dataCell.setCustomStyle("width:25px;");
		
		dataColumn = new Column();
		dataColumn.setDataCell(dataCell);
		
		countryTable.addColumn(dataColumn);

		columnField = new AppField();
		columnField.setFieldName("callingCode");
		columnField.setInputType(FieldInputType.Number);
		columnField.setLabel("Phone Code");
		columnField.setPlaceholder("Phone Code");
		columnField.setMode(FieldMode.Simple);
		columnField.setType(FieldType.Short);
		columnField.setMinValue(1);
		columnField.setMaxValue(999);
		columnField.setRequired(true);

		dataCell = new DataCell();
		dataCell.setField(columnField);
		dataCell.setCustomStyle("width:25px;");

		dataColumn = new Column();
		dataColumn.setDataCell(dataCell);
		
		countryTable.addColumn(dataColumn);

		columnField = new AppField();
		columnField.setFieldName("currencyCode");
		columnField.setInputType(FieldInputType.Text);
		columnField.setLabel("Currency Code");
		columnField.setPlaceholder("Currency Code");
		columnField.setMode(FieldMode.Simple);
		columnField.setType(FieldType.String);
		columnField.setInputPattern("[A-Za-z]{3}");
		columnField.setPatternMismatchMessage("Input may only contain three alphabets");
		columnField.setMinLength(3);
		columnField.setMaxLength(3);
		columnField.setRequired(true);

		dataCell = new DataCell();
		dataCell.setField(columnField);
		dataCell.setCustomStyle("width:25px;");
		
		dataColumn = new Column();
		dataColumn.setDataCell(dataCell);
		
		countryTable.addColumn(dataColumn);

		columnField = new AppField();
		columnField.setFieldName("continent");
		columnField.setInputType(FieldInputType.Text);
		columnField.setLabel("Continent");
		columnField.setPlaceholder("Continent");
		columnField.setMode(FieldMode.Simple);
		columnField.setType(FieldType.String);
		columnField.setInputPattern("[A-Za-z ]+");
		columnField.setPatternMismatchMessage("Input may only contain words");
		columnField.setRequired(true);

		dataCell = new DataCell();
		dataCell.setField(columnField);
		
		dataColumn = new Column();
		dataColumn.setDataCell(dataCell);
		
		countryTable.addColumn(dataColumn);

		columnField = new AppField();
		columnField.setFieldName("capitalCity");
		columnField.setInputType(FieldInputType.Text);
		columnField.setLabel("Capital");
		columnField.setPlaceholder("Capital");
		columnField.setMode(FieldMode.Simple);
		columnField.setType(FieldType.String);
		columnField.setInputPattern("[A-Za-z ]+");
		columnField.setPatternMismatchMessage("Input may only contain words");

		dataCell = new DataCell();
		dataCell.setField(columnField);
		
		dataColumn = new Column();
		dataColumn.setDataCell(dataCell);
		
		countryTable.addColumn(dataColumn);

		columnField = new AppField();
		columnField.setFieldName("capitalCityTimeZone");
		columnField.setInputType(FieldInputType.Text);
		columnField.setLabel("Capital Time Zone");
		columnField.setPlaceholder("Capital Time Zone");
		columnField.setMode(FieldMode.Simple);
		columnField.setType(FieldType.String);
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
