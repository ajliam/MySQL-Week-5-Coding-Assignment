create database if not exists movies;

use movies;

drop table if exists members;
drop tables if exists movies;

create table movies(
    id int(10) not null auto_increment,
    name varchar(50) not null,
    primary key(id)
);

create table members (
    in int(10) not null auto_increment,
    first_name varchar(25) not null,
    last_name varchar(25) not null,
    movie_id int(10) not null,
    primary key(id),
    foreign key(team_id) references movies(id)
);