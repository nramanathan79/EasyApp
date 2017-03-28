package com.easyapp.testapp.entity;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Where;

import com.easyapp.core.entity.PersistEntity;

@XmlRootElement
@Entity
@Table(name = "country")
public class CountryEntity extends PersistEntity {
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(unique = true)
	private String isoAlpha2Code;

	@NotNull
	@Column(unique = true)
	private String isoAlpha3Code;

	@NotNull
	@Column(unique = true)
	private String isoNumericCode;

	@NotNull
	private String countryName;

	@NotNull
	private String continent;

	private Long callingCode;

	private String currencyCode;
	
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Where(clause = "is_capital is true")
	private List<CityEntity> cities;

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

	public String getIsoNumericCode() {
		return isoNumericCode;
	}

	public void setIsoNumericCode(final String isoNumericCode) {
		this.isoNumericCode = isoNumericCode;
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

	public Long getCallingCode() {
		return callingCode;
	}

	public void setCallingCode(final Long callingCode) {
		this.callingCode = callingCode;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(final String currencyCode) {
		this.currencyCode = currencyCode != null ? currencyCode.trim().toUpperCase() : null;
	}

	public List<CityEntity> getCities() {
		return cities;
	}

	public void setCities(final List<CityEntity> cities) {
		this.cities = cities;
	}

	@Override
	public List<String> uniqueKeyFields() {
		return Arrays.asList("isoAlpha2Code");
	}
}
