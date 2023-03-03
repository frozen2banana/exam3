drop database if exists acme_bank;

create database acme_bank;

use acme_bank

create table accounts (
    account_id VARCHAR(10) not null,
    name VARCHAR(50) not null,
    balance DECIMAL(10, 2) not null,
    PRIMARY KEY (account_id)
);

LOAD DATA INFILE 'data.csv' INTO TABLE accounts
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
;
