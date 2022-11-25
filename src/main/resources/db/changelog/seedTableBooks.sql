--liquibase formatted sql

--changeset mishinyura:2
insert into books(title, enabled) values ('Title_liquibase', false);

--rollback DROP TABLE books;
