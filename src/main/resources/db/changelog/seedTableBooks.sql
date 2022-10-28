--liquibase formatted sql

--changeset mishinyura:2
insert into books(title) values ('Title_liquibase');

--rollback DROP TABLE books;
