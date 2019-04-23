-- Creating users table
create table users
(
  user_id bigserial not null
    constraint users_pk
      primary key,
  login VARCHAR(40) not null,
  password VARCHAR(40) not null
);
create unique index users_login_uindex
  on users (login);

-- creating tours table
create table tours
(
	tour_id bigserial not null
		constraint tours_pk
			primary key,
	photo bytea,
	date bigint,
	duration int,
	description varchar(1024),
	cost double precision,
	tour_type bigint,
	hotel_id bigint,
	country_id bigint
);

-- creating hotels table
create table hotels
(
	hotel_id bigserial not null
		constraint hotels_pk
			primary key,
	name varchar(40),
	stars smallint,
	website varchar(256),
	latitude double precision,
	longtitude double precision,
	feature_id bigint
);

-- creating reviews table
create table reviews
(
	review_id bigserial not null,
	date bigint,
	text VARCHAR(1024),
	column_4 int,
	user_id bigint,
	tour_id bigint
);
create unique index reviews_review_id_uindex
	on reviews (review_id);
alter table reviews
	add constraint reviews_pk
		primary key (review_id);

-- creating features table
create table features
(
	feature_id bigserial not null,
	value varchar(40)
);
create unique index features_feature_id_uindex
	on features (feature_id);
create unique index features_value_uindex
	on features (value);
alter table features
	add constraint features_pk
		primary key (feature_id);

-- creating tour_tupes table
create table tour_tupes
(
	tour_tupe_id bigserial not null,
	value varchar(40)
);
create unique index tour_tupes_tour_tupe_id_uindex
	on tour_tupes (tour_tupe_id);
create unique index tour_tupes_value_uindex
	on tour_tupes (value);
alter table tour_tupes
	add constraint tour_tupes_pk
		primary key (tour_tupe_id);

-- creating countries table
create table countries
(
	country_id bigserial not null,
	value varchar(40)
);
create unique index countries_country_id_uindex
	on countries (country_id);
create unique index countries_value_uindex
	on countries (value);
alter table countries
	add constraint countries_pk
		primary key (country_id);

-- creating userTours table (many to many)
create table userTours
(
	user_id bigint
		constraint userTours_users_user_id_fk
			references users,
	tour_id bigint
		constraint userTours_tours_tour_id_fk
			references tours,
	constraint userTours_pk
		primary key (user_id, tour_id)
);

-- adding foreign keys
-- reviews
alter table reviews
	add constraint reviews_tours_tour_id_fk
		foreign key (tour_id) references tours;
alter table reviews
	add constraint reviews_users_user_id_fk
		foreign key (user_id) references users;

-- tours
alter table tours
	add constraint tours_countries_country_id_fk
		foreign key (country_id) references countries;
alter table tours
	add constraint tours_hotels_hotel_id_fk
		foreign key (hotel_id) references hotels;
alter table tours
	add constraint tours_tour_tupes_tour_tupe_id_fk
		foreign key (tour_type) references tour_tupes;

-- hotels
alter table hotels
	add constraint hotels_features_feature_id_fk
		foreign key (feature_id) references features;














































