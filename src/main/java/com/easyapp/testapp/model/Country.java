package com.easyapp.testapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.easyapp.core.model.PersistModel;

@XmlRootElement
@Entity
public class Country extends PersistModel {
	@NotNull
	@Column(unique = true)
	private String isoAlpha2Code;

	@NotNull
	@Column(unique = true)
	private String isoAlpha3Code;
	
	@NotNull
	@Column(unique = true)
	private Integer isoNumericCode;
	
	@NotNull
	private Integer callingCode;

	@NotNull
	private String countryName;
	
	@NotNull
	private String continent;
	
	private String capitalCity;

	@NotNull
	private String currencyCode;
	
	private String capitalCityTimeZone;

	public String getIsoAlpha2Code() {
		return isoAlpha2Code;
	}

	public void setIsoAlpha2Code(final String isoAlpha2Code) {
		this.isoAlpha2Code = isoAlpha2Code != null ? isoAlpha2Code.trim().toUpperCase() : null;
	}

	public String getIsoAlpha3Code() {
		return isoAlpha3Code;
	}

	public void setIsoAlpha3Code(final String isoAlpha3Code) {
		this.isoAlpha3Code = isoAlpha3Code != null ? isoAlpha3Code.trim().toUpperCase() : null;
	}

	public Integer getIsoNumericCode() {
		return isoNumericCode;
	}

	public void setIsoNumericCode(final Integer isoNumericCode) {
		this.isoNumericCode = isoNumericCode;
	}

	public Integer getCallingCode() {
		return callingCode;
	}

	public void setCallingCode(final Integer callingCode) {
		this.callingCode = callingCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(final String countryName) {
		this.countryName = countryName != null ? countryName.trim() : null;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(final String continent) {
		this.continent = continent != null ? continent.trim() : null;
	}

	public String getCapitalCity() {
		return capitalCity;
	}

	public void setCapitalCity(final String capitalCity) {
		this.capitalCity = capitalCity != null ? capitalCity.trim() : null;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(final String currencyCode) {
		this.currencyCode = currencyCode != null ? currencyCode.trim().toUpperCase() : null;
	}

	public String getCapitalCityTimeZone() {
		return capitalCityTimeZone;
	}

	public void setCapitalCityTimeZone(final String capitalCityTimeZone) {
		this.capitalCityTimeZone = capitalCityTimeZone != null ? capitalCityTimeZone.trim() : null;
	}
}
