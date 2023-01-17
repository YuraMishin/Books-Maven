--liquibase formatted sql

--changeset mishinyura:2
insert into books(title, enabled)
values ('Title_liquibase1', false),
       ('Title_liquibase2', false),
       ('Title_liquibase3', false),
       ('Title_liquibase4', false);

--rollback DROP TABLE books;
