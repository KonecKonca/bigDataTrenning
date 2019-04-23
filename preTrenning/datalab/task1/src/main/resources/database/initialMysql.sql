create table outcomeStatuses
(
	status_id bigint auto_increment,
	category VARCHAR(40) null,
	status_date long null,
	constraint outcomeStatuses_pk
		primary key (status_id)
);
alter table outcomestatuses modify status_date bigint null;
create table streets
(
	street_id bigint auto_increment,
	name VARCHAR(30) null,
	constraint streets_pk
		primary key (street_id)
);
alter table streets modify name varchar(256) null;
create table locations
(
	location_id bigint auto_increment,
	latitude double null,
	longtitude double null,
	street_id bigint null,
	constraint locations_pk
		primary key (location_id),
	constraint locations_streets_street_id_fk
		foreign key (location_id) references streets (street_id)
);
create table crimeDtos
(
	crime_id bigint auto_increment,
	location_type VARCHAR(30) null,
	location_id bigint null,
	context VARCHAR(256) null,
	outcome_status_id bigint null,
	persistent_id VARCHAR(256) null,
	location_subtype VARCHAR(256) null,
	month VARCHAR(30) null,
	constraint crimes_pk
		primary key (crime_id),
	constraint crimes_locations_location_id_fk
		foreign key (location_id) references locations (location_id),
	constraint crimes_outcomestatuses_status_id_fk
		foreign key (outcome_status_id) references outcomestatuses (status_id)
);
alter table crimes
	add category varchar(40) null;
alter table crimes modify month bigint null;





