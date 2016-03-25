package com.easyapp.testapp.model;

import com.easyapp.core.entity.PersistEntity;
import com.easyapp.core.model.PersistModel;
import com.easyapp.testapp.entity.CountryEntity;

public class Country extends PersistModel {
	private String isoAlpha2Code;
	private String isoAlpha3Code;
	private Integer isoNumericCode;
	private Integer callingCode;
	private String countryName;
	private String continent;
	private String capitalCity;
	private String currencyCode;
	private String capitalCityTimeZone;

	public String getIsoAlpha2Code() {
		return isoAlpha2Code;
	}

	public void setIsoAlpha2Code(String isoAlpha2Code) {
		this.isoAlpha2Code = isoAlpha2Code;
	}

	public String getIsoAlpha3Code() {
		return isoAlpha3Code;
	}

	public void setIsoAlpha3Code(String isoAlpha3Code) {
		this.isoAlpha3Code = isoAlpha3Code;
	}

	public Integer getIsoNumericCode() {
		return isoNumericCode;
	}

	public void setIsoNumericCode(Integer isoNumericCode) {
		this.isoNumericCode = isoNumericCode;
	}

	public Integer getCallingCode() {
		return callingCode;
	}

	public void setCallingCode(Integer callingCode) {
		this.callingCode = callingCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getCapitalCity() {
		return capitalCity;
	}

	public void setCapitalCity(String capitalCity) {
		this.capitalCity = capitalCity;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCapitalCityTimeZone() {
		return capitalCityTimeZone;
	}

	public void setCapitalCityTimeZone(String capitalCityTimeZone) {
		this.capitalCityTimeZone = capitalCityTimeZone;
	}

	@Override
	protected void mapFrom(final PersistEntity persistEntity) {
		if (persistEntity instanceof CountryEntity) {
			CountryEntity countryEntity = (CountryEntity) persistEntity;

			setIsoAlpha2Code(countryEntity.getIsoAlpha2Code());
			setIsoAlpha3Code(countryEntity.getIsoAlpha3Code());
			setIsoNumericCode(countryEntity.getIsoNumericCode());
			setCallingCode(countryEntity.getCallingCode());
			setCountryName(countryEntity.getCountryName());
			setContinent(countryEntity.getContinent());
			setCapitalCity(countryEntity.getCapitalCity());
			setCurrencyCode(countryEntity.getCurrencyCode());
			setCapitalCityTimeZone(countryEntity.getCapitalCityTimeZone());
		}
	}

	@Override
	@SuppressWarnings(value = { "unchecked" })
	protected <T extends PersistEntity> T mapTo() {
		CountryEntity countryEntity = new CountryEntity();

		countryEntity.setIsoAlpha2Code(getIsoAlpha2Code());
		countryEntity.setIsoAlpha3Code(getIsoAlpha3Code());
		countryEntity.setIsoNumericCode(getIsoNumericCode());
		countryEntity.setCallingCode(getCallingCode());
		countryEntity.setCountryName(getCountryName());
		countryEntity.setContinent(getContinent());
		countryEntity.setCapitalCity(getCapitalCity());
		countryEntity.setCurrencyCode(getCurrencyCode());
		countryEntity.setCapitalCityTimeZone(getCapitalCityTimeZone());

		return (T) countryEntity;
	}
}
