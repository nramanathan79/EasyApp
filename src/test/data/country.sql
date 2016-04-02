-- Table: country

DROP TABLE country;

CREATE TABLE country
(
  id text NOT NULL,
  createuserid text NOT NULL,
  createtimestamp timestamp without time zone NOT NULL,
  updateuserid text NOT NULL,
  updatetimestamp timestamp without time zone NOT NULL,
  isoalpha2code text NOT NULL,
  isoalpha3code text NOT NULL,
  isonumericcode text NOT NULL,
  callingcode bigint,
  countryname text NOT NULL,
  continent text NOT NULL,
  capitalcity text,
  currencycode text,
  capitalcitytimezone text,
  capitalcitylatitude numeric,
  capitalcitylongitude numeric,
  CONSTRAINT pk_country PRIMARY KEY (id),
  CONSTRAINT uk1_country UNIQUE (isoalpha2code),
  CONSTRAINT uk2_country UNIQUE (isoalpha3code),
  CONSTRAINT uk3_country UNIQUE (isonumericcode)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE country
  OWNER TO "EasyApp";
GRANT ALL ON TABLE country TO public;
GRANT ALL ON TABLE country TO "EasyApp";
