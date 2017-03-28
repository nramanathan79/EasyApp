package com.easyapp.testapp.entity;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.easyapp.core.entity.PersistEntity;

@XmlRootElement
@Entity
@Table(name = "city")
public class CityEntity extends PersistEntity {
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(unique = true)
	private Long cityId;

	@NotNull
	private String cityName;

	@NotNull
	private String countryCode;

	private Boolean isCapital;

	private Double latitude;

	private Double longitude;

	private Long population;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private CountryEntity country;

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(final Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(final String cityName) {
		this.cityName = cityName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	public Boolean getIsCapital() {
		return isCapital;
	}

	public void setIsCapital(final Boolean isCapital) {
		this.isCapital = isCapital;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(final Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(final Double longitude) {
		this.longitude = longitude;
	}

	public Long getPopulation() {
		return population;
	}

	public void setPopulation(final Long population) {
		this.population = population;
	}

	public CountryEntity getCountry() {
		return country;
	}

	public void setCountry(final CountryEntity country) {
		this.country = country;
	}

	@Override
	public List<String> uniqueKeyFields() {
		return Arrays.asList("cityId");
	}
}
