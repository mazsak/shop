FROM postgres:lastest

RUN psql -U postgres

RUN create user shop with password 'shop' CREATEUSER

RUN create database shop

RUN create database shop-test