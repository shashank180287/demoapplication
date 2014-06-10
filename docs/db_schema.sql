CREATE DATABASE stockmanager;

\c stockmanager;

CREATE TABLE  USER_ROLE (
	role_id integer primary key,
	role_name varchar(50),
	role_permissions varchar(50)
);

INSERT INTO USER_ROLE VALUES (1, 'admin', 'Stock Admin');
INSERT INTO USER_ROLE VALUES (2, 'customer', 'Customer/Vendor');
INSERT INTO USER_ROLE VALUES (3, 'employee', 'Stock Employee');

CREATE TABLE  USER_LOGIN_DETAILS (
	username varchar(50) primary key,
	password varchar(50),
	role_id integer,
	FOREIGN KEY (role_id) REFERENCES USER_ROLE(role_id)
);

INSERT INTO USER_LOGIN_DETAILS VALUES ('admin', 'admin', 1);
INSERT INTO USER_LOGIN_DETAILS VALUES ('customer1', 'customer1', 2);
INSERT INTO USER_LOGIN_DETAILS VALUES ('employee1', 'employee1', 3);
	

CREATE TABLE USER_ACCESS_HISTORY (
	id integer primary key,
	username varchar(50),
	start_time date,
	end_time date,
	FOREIGN KEY (username) REFERENCES USER_LOGIN_DETAILS(username)
);	
	

CREATE TABLE PRODUCT_CATEGORY (
	category_id integer primary key,
	category_name varchar(50),
	category_description varchar(100)
);

CREATE TABLE VENDOR_CATEGORY (
	vendor_category_id integer primary key,
	vendor_category_name varchar(50),
	vendor_category_desc varchar(100)
);
	
CREATE TABLE TRANSECTION_MODE (
	channel_id integer primary key,
	channel_name varchar(50),
	channel_description varchar(100)
);	

CREATE TABLE CUSTOMER_RECORD (
	customer_code varchar(50) primary key,
	first_name varchar(50),
	last_name varchar(100),
	mobile_number integer(15),
	email varchar(50),
	gender varchar(10),
	contact_address varchar(255),
	created	date,
	website varchar(50),
	description varchar(100),
	username varchar(50),
	FOREIGN KEY (username) REFERENCES USER_LOGIN_DETAILS(username)
);

CREATE TABLE VENDOR_RECORD (
	vendor_code varchar(50) primary key,
	vendor_category_id integer,
	name varchar(100),
	mobile_number integer(15),
	email varchar(50),
	title varchar(50),
	contact_address varchar(255),
	permanent_address	varchar(255),
	website varchar(50),
	description varchar(100),
	username varchar(50),
	FOREIGN KEY (username) REFERENCES USER_LOGIN_DETAILS(username),
	FOREIGN KEY (vendor_category_id) REFERENCES VENDOR_CATEGORY(vendor_category_id)
);

CREATE TABLE PRODUCT_RECORD (
	product_code varchar(50) primary key,
	product_category_id integer,
	name varchar(100),
	description varchar(255),
	unit_price float,
	bulk_price float,
	order_count integer,
	vendor_code varchar(50),
	created	date,
	FOREIGN KEY (product_category_id) REFERENCES PRODUCT_CATEGORY(category_id),
	FOREIGN KEY (vendor_code) REFERENCES VENDOR_RECORD(vendor_code)
);

	
CREATE TABLE EMPLOYEES_RECORDS (
	employee_code varchar(50) primary key,
	first_name varchar(100),
	last_name varchar(100),
	mobile_number integer(15),
	gender varchar(10),
	qualification varchar(50),
	profession varchar(50),
	age	integer,
	marital_status varchar(20),
	job_description varchar(100),
	manager_code varchar(50),
	email varchar(50),
	employeed date,
	job_title varchar(50),
	contact_address varchar(255),
	username varchar(50),
	reference_1 varchar(100),
	reference_2 varchar(100),
	reference_3 varchar(100),
	FOREIGN KEY (username) REFERENCES USER_LOGIN_DETAILS(username),
	FOREIGN KEY (manager_code) REFERENCES EMPLOYEES_RECORDS(employee_code)
);	
	

CREATE TABLE EMPLOYEES_EXPERIENCE (
	id integer primary key,
	employee_code varchar(50),
	job_title varchar(100),
	company_name varchar(100),
	start_date date,
	end_date date,
	FOREIGN KEY (employee_code) REFERENCES EMPLOYEES_RECORDS(employee_code)
);	

CREATE TABLE PRODUCT_SUPPLY (
	supply_code varchar(50) primary key,
	product_code varchar(50),
	total_items integer,
	total_supplied integer,
	current_stock integer,
	current_item_count integer,
	FOREIGN KEY (product_code) REFERENCES PRODUCT_RECORD(product_code)
);	

CREATE TABLE SALES_RECORDS (
	sales_code varchar(50) primary key,
	product_code varchar(50),
	no_of_items integer,
	sales_title varchar(20),
	sales_desc varchar(255),
	sales_amount float,
	confirm_amount float,
	channel_id integer,
	customer_code varchar(50),
	employee_code varchar(50),
	comments varchar(255),
	created date,
	FOREIGN KEY (product_code) REFERENCES PRODUCT_RECORD(product_code),
	FOREIGN KEY (customer_code) REFERENCES CUSTOMER_RECORD(customer_code),
	FOREIGN KEY (employee_code) REFERENCES EMPLOYEES_RECORDS(employee_code)
);	

CREATE TABLE RECORD_KEEPING (
	record_id integer primary key,
	type varchar(50),
	tag varchar(255),
	comment varchar(255)
);	
