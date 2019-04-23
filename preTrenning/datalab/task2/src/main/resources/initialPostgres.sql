--CREATE DATABASE data1;

create table streets
(
	street_id bigserial
		constraint streets_pk
			primary key,
	name varchar(256)
);

create table outcomestatuses
(
	status_id bigserial
		constraint outcomestatuses_pk
			primary key,
	category varchar(40),
	status_date bigint
);

create table locations
(
	location_id bigserial
		constraint locations_pk
			primary key,
	latitude decimal,
	longtitude decimal,
	street_id bigint
		constraint locations_streets_street_id_fk
			references streets
);

create table crimes
(
	crime_id bigserial
		constraint crimes_pk
			primary key,
	location_type varchar(30),
	location_id bigint
		constraint crimes_locations_location_id_fk
			references locations,
	context varchar(256),
	outcome_status_id bigint
		constraint crimes_outcomestatuses_status_id_fk
			references outcomestatuses,
	persistent_id varchar(256),
	location_subtype varchar(256),
	month bigint,
	category varchar(40)
);
alter table crimes alter column crime_id drop default;
drop sequence crimes_crime_id_seq;

create unique index outcomestatuses_category_status_date_uindex
	on outcomestatuses (category, status_date);









