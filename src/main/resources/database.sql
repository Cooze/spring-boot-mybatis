

CREATE database school;

create table school (id int primary key auto_increment, name varchar(20), no varchar(50), rate int);

insert into school (name, no, rate) values ('xxx希望小学', '111001', 1);
insert into school (name, no, rate) values ('ccc希望小学', '111002', 1);



CREATE database student;

create table student (id int primary key auto_increment, name varchar(20), clazz varchar(50), des varchar(50));

insert into student (name, clazz, des) values ('张三', '4-1班', '三好学生');
insert into student (name, clazz, des) values ('李四', '4-2班', '三好学生');



create database city;
drop table if exists city;

create table city (id int primary key auto_increment, name varchar(20), state varchar(50), country varchar(50));

insert into city (name, state, country) values ('北京', '北京', '中国'),('深圳', '广东', '中国');


select * from student;