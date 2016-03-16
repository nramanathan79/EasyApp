package com.easyapp.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Country extends PersistModel {
	@Column(nullable = false, unique = true)
	private String isoAlpha2Code;

	@Column(nullable = false, unique = true)
	private String isoAlpha3Code;
	
	@Column(nullable = false, unique = true)
	private int isoNumericCode;
	
	@Column(nullable = false)
	private int callingCode;

	@Column(nullable = false)
	private String countryName;
	
	@Column(nullable = false)
	private String continent;
	
	private String capitalCity;

	@Column(nullable = false)
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

	public int getIsoNumericCode() {
		return isoNumericCode;
	}

	public void setIsoNumericCode(final int isoNumericCode) {
		this.isoNumericCode = isoNumericCode;
	}

	public int getCallingCode() {
		return callingCode;
	}

	public void setCallingCode(final int callingCode) {
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
