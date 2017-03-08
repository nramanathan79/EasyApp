package com.easyapp.core.repository;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class OffsetDateTimeAttributeConverter implements AttributeConverter<OffsetDateTime, Timestamp> {
	@Override
	public Timestamp convertToDatabaseColumn(final OffsetDateTime offsetDateTime) {
		return (offsetDateTime == null ? null : Timestamp.valueOf(offsetDateTime.toLocalDateTime()));
	}

	@Override
	public OffsetDateTime convertToEntityAttribute(final Timestamp sqlTimestamp) {
		return (sqlTimestamp == null ? null : OffsetDateTime.of(sqlTimestamp.toLocalDateTime(), ZoneOffset.ofHours(0)));
	}
}