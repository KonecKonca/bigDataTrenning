create table outcome_objects
(
	id VARCHAR(256)
		constraint outcome_objects_pk
			primary key,
	name VARCHAR(256)
);

create table stops
(
	stop_id bigserial
		constraint stops_pk
			primary key,
	age_range VARCHAR(64),
	outcome VARCHAR(256),
	involved_person boolean,
	self_defined_ethnicity VARCHAR(512),
	gender VARCHAR(64),
	legislation VARCHAR(256),
	outcome_linked_to_object_of_search BOOLEAN,
	datetime bigint,
	removal_of_more_than_outer_clothing boolean,
	outcome_object_id bigint,
	location_id bigint
		constraint stops_locations_location_id_fk
			references locations,
	operation VARCHAR(256),
	officer_defined_ethnicity VARCHAR(64),
	type VARCHAR(64),
	operation_name VARCHAR(256),
	object_of_search VARCHAR(256)
);
alter table stops alter column outcome_object_id type VARCHAR(256) using outcome_object_id::VARCHAR(256);

alter table stops
	add constraint stops_outcome_objects_id_fk
		foreign key (outcome_object_id) references outcome_objects;

alter table stops alter column datetime type VARCHAR(64) using datetime::VARCHAR(64);

create unique index outcome_objects_id_name_uindex
	on outcome_objects (id, name);

create unique index stops_age_range_outcome_involved_person_self_defined_ethnicity_
  on stops (age_range, outcome, involved_person, self_defined_ethnicity, gender, legislation, datetime, officer_defined_ethnicity, type, object_of_search, outcome_object_id, location_id);
create unique index streets_street_id_name_uindex
	on streets (street_id, name);

create unique index locations_latitude_longtitude_street_id_uindex
	on locations (latitude, longtitude, street_id);

create unique index crimes_category_location_type_context_location_id_location_subtype_uindex
	on crimes (category, location_type, context, location_id, location_subtype);

alter table stops alter column datetime type timestamp using datetime::timestamp;
