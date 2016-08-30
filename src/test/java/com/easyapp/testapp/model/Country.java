package com.easyapp.testapp.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.easyapp.core.entity.PersistEntity;
import com.easyapp.core.model.PersistModel;
import com.easyapp.testapp.entity.CountryEntity;

@XmlRootElement
public class Country extends PersistModel {
	private String isoAlpha2Code;
	private String isoAlpha3Code;
	private String isoNumericCode;
	private Long callingCode;
	private String countryName;
	private String continent;
	private String capitalCity;
	private String currencyCode;
	private String capitalCityTimeZone;
	private Double capitalCityLatitude;
	private Double capitalCityLongitude;

	public String getIsoAlpha2Code() {
		return isoAlpha2Code;
	}

	public void setIsoAlpha2Code(final String isoAlpha2Code) {
		this.isoAlpha2Code = isoAlpha2Code;
	}

	public String getIsoAlpha3Code() {
		return isoAlpha3Code;
	}

	public void setIsoAlpha3Code(final String isoAlpha3Code) {
		this.isoAlpha3Code = isoAlpha3Code;
	}

	public String getIsoNumericCode() {
		return isoNumericCode;
	}

	public void setIsoNumericCode(final String isoNumericCode) {
		this.isoNumericCode = isoNumericCode;
	}

	public Long getCallingCode() {
		return callingCode;
	}

	public void setCallingCode(final Long callingCode) {
		this.callingCode = callingCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(final String countryName) {
		this.countryName = countryName;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(final String continent) {
		this.continent = continent;
	}

	public String getCapitalCity() {
		return capitalCity;
	}

	public void setCapitalCity(final String capitalCity) {
		this.capitalCity = capitalCity;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(final String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCapitalCityTimeZone() {
		return capitalCityTimeZone;
	}

	public void setCapitalCityTimeZone(final String capitalCityTimeZone) {
		this.capitalCityTimeZone = capitalCityTimeZone;
	}

	public Double getCapitalCityLatitude() {
		return capitalCityLatitude;
	}

	public void setCapitalCityLatitude(final Double capitalCityLatitude) {
		this.capitalCityLatitude = capitalCityLatitude;
	}

	public Double getCapitalCityLongitude() {
		return capitalCityLongitude;
	}

	public void setCapitalCityLongitude(final Double capitalCityLongitude) {
		this.capitalCityLongitude = capitalCityLongitude;
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
			setCapitalCityLatitude(countryEntity.getCapitalCityLatitude());
			setCapitalCityLongitude(countryEntity.getCapitalCityLongitude());
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
		countryEntity.setCapitalCityLatitude(getCapitalCityLatitude());
		countryEntity.setCapitalCityLongitude(getCapitalCityLongitude());

		return (T) countryEntity;
	}
}
