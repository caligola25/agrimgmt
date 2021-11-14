-- Factory DB setup and creation

-- Create the administrator user
CREATE USER Administrator WITH
    LOGIN
    SUPERUSER
    INHERIT
    CREATEDB
    CREATEROLE
    NOREPLICATION
    PASSWORD 'admin';

-- Create the DB
CREATE DATABASE agrimgmt OWNER Administrator ENCODING = 'UTF8';

-- Connect to the DB
\c agrimgmt

-- Define UUID function
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create a new schema
DROP SCHEMA IF EXISTS Factory CASCADE;
CREATE SCHEMA Factory;

CREATE DOMAIN Factory.USRNAME AS VARCHAR(100)
    CONSTRAINT properusername CHECK (((VALUE)::text ~* '[A-Za-z0-9.]{8,}'::text));

CREATE DOMAIN Factory.PSWD AS VARCHAR(50)
    CONSTRAINT properpassword CHECK (((VALUE)::text ~* '[A-Za-z0-9._%$&!,]{8,}'::text));

CREATE DOMAIN Factory.OPERATIONS AS SMALLINT
    CONSTRAINT properoperations CHECK (((VALUE >= 0) AND (VALUE <= 10)) OR NULL)
    DEFAULT (NULL);

CREATE DOMAIN Factory.COUNTRY AS VARCHAR(100)
    CONSTRAINT propercountry CHECK (((VALUE)::text ~* '[A-Za-zèòàì,]'::text));

CREATE DOMAIN Factory.CITY AS VARCHAR(100)
    CONSTRAINT propercity CHECK (((VALUE)::text ~* '[A-Za-zèòàì,]'::text));

CREATE DOMAIN Factory.STREET AS VARCHAR(100)
    CONSTRAINT properstreet CHECK (((VALUE)::text ~* '[A-Za-z0-9 \/ èòàì,]'::text));

CREATE DOMAIN Factory.POSINT AS SMALLINT
    CONSTRAINT propervalue CHECK (VALUE >= 0)
    DEFAULT (0);

-- Crate new data type
CREATE TYPE Factory.ROLE AS ENUM (
    'Administrator', 
    'Warehouse worker',
    'Production line worker',
    'Accountant',
    'Designer',
    'Production planner'
);

CREATE TYPE Factory.PHASE_STATUS AS ENUM (
    'queued',
    'running',
    'completed'
);

CREATE TYPE Factory.ITEM_STATUS AS ENUM (
    'in_construction',
    'stored',
    'shipped'
);

CREATE TYPE Factory.PRODUCT_ORDER_STATUS AS ENUM (
    'in_production',
    'completed',
    'shipped',
    'cancelled',
    'not_paid',
    'paid'
);

CREATE TYPE Factory.MATERIAL_ORDER_STATUS AS ENUM (
    'completed',
    'received'
);

CREATE TYPE Factory.FIXED_COST_TYPE AS ENUM (
    'electrical_bill',
    'gas_bill',
    'water_bill',
    'rent',
    'tax'
);

-- Table creation
CREATE TABLE Factory.Employee (
    employee_id UUID,
    employee_name VARCHAR(20) NOT NULL,
    employee_surname VARCHAR(20) NOT NULL,
    employee_role Factory.ROLE NOT NULL,
    salary NUMERIC(7, 2) NOT NULL,
    operations Factory.OPERATIONS,
    PRIMARY KEY (employee_id)
);

CREATE TABLE Factory.Product (
    product_id UUID,
    product_name VARCHAR(50) NOT NULL,
    price NUMERIC(8, 2) NOT NULL,
    available BOOLEAN NOT NULL,
    PRIMARY KEY (product_id)
);

CREATE TABLE Factory.Customer (
    customer_id UUID,
    customer_name VARCHAR(50) NOT NULL,
    country Factory.COUNTRY NOT NULL,
    city Factory.CITY,
    street Factory.STREET,
    PRIMARY KEY (customer_id)
);

CREATE TABLE Factory.Supplier (
    supplier_name VARCHAR(50),
    country Factory.COUNTRY,
    PRIMARY KEY (supplier_name)
);

CREATE TABLE Factory.Raw_material (
    material_id UUID,
    material_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (material_id)
);

CREATE TABLE Factory.Report (
    report_date DATE,
    document_file BYTEA NOT NULL,
    PRIMARY KEY (report_date)
);

CREATE TABLE Factory.Credential (
    username Factory.USRNAME,
    passwrd Factory.PSWD NOT NULL,
    employee_id UUID,
    PRIMARY KEY (username, employee_id),
    FOREIGN KEY (employee_id) REFERENCES Factory.Employee(employee_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Factory.Define_2 (
    employee_id UUID,
    report_date DATE,
    PRIMARY KEY (employee_id, report_date),
    FOREIGN KEY (employee_id) REFERENCES Factory.Employee(employee_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (report_date) REFERENCES Factory.Report(report_date) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Factory.Process (
    process_id UUID,
    process_name VARCHAR(50),
    sequence_number Factory.POSINT,
    estimated_time INTERVAL NOT NULL,
    product_id UUID,
    material_id UUID,
    quantity Factory.POSINT NOT NULL,
    PRIMARY KEY (process_id),
    FOREIGN KEY (product_id) REFERENCES Factory.Product(product_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (material_id) REFERENCES Factory.Raw_material(material_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Factory.Product_order (
    product_order_id UUID,
    product_order_date DATE NOT NULL,
    order_status Factory.PRODUCT_ORDER_STATUS NOT NULL,
    price NUMERIC(8, 2) NOT NULL,
    customer_id UUID NOT NULL,
    report_date DATE,
    PRIMARY KEY (product_order_id),
    FOREIGN KEY (report_date) REFERENCES Factory.Report(report_date) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (customer_id) REFERENCES Factory.Customer(customer_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Factory.Item (
    item_id UUID,
    item_status Factory.ITEM_STATUS NOT NULL,
    product_order_id UUID NOT NULL,
    product_id UUID NOT NULL,
    PRIMARY KEY (item_id),
    FOREIGN KEY (product_order_id) REFERENCES Factory.Product_order(product_order_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (product_id) REFERENCES Factory.Product(product_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Factory.Production_phase (
    phase_id UUID,
    phase_status Factory.PHASE_STATUS NOT NULL,
    actual_time INTERVAL,
    item_id UUID NOT NULL,
    process_id UUID NOT NULL,
    employee_id UUID NOT NULL,
    PRIMARY KEY (phase_id),
    FOREIGN KEY (item_id) REFERENCES Factory.Item(item_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (process_id) REFERENCES Factory.Process(process_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (employee_id) REFERENCES Factory.Employee(employee_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Factory.Material_order (
    material_order_id UUID,
    price NUMERIC(8, 2) NOT NULL,
    material_order_date DATE NOT NULL,
    order_status Factory.MATERIAL_ORDER_STATUS NOT NULL,
    report_date DATE,
    PRIMARY KEY (material_order_id),
    FOREIGN KEY (report_date) REFERENCES Factory.Report(report_date) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Factory.Supplying (
    supplying_id UUID,
    material_id UUID NOT NULL,
    quantity Factory.POSINT NOT NULL,
    material_order_id UUID NOT NULL,
    supplier_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (supplying_id),
    FOREIGN KEY (material_id) REFERENCES Factory.Raw_material(material_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (material_order_id) REFERENCES Factory.Material_order(material_order_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (supplier_name) REFERENCES Factory.Supplier(supplier_name) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Factory.Fixed_cost (
    fixed_cost_date DATE,
    fixed_cost_type Factory.FIXED_COST_TYPE,
    price NUMERIC(8, 2) NOT NULL,
    report_date DATE,
    PRIMARY KEY (fixed_cost_date, fixed_cost_type),
    FOREIGN KEY (report_date) REFERENCES Factory.Report(report_date) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Create different roles to access the database via web-app
CREATE ROLE readonly WITH LOGIN PASSWORD 'agrm_r';
GRANT CONNECT ON DATABASE agrimgmt TO readonly;
GRANT USAGE ON SCHEMA Factory TO readonly;
GRANT SELECT ON ALL TABLES IN SCHEMA Factory TO readonly;

CREATE ROLE readwrite WITH LOGIN PASSWORD 'agrm_rw';
GRANT CONNECT ON DATABASE agrimgmt TO readwrite;
GRANT USAGE ON SCHEMA Factory TO readwrite;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA Factory TO readwrite;