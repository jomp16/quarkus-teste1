CREATE TABLE clients(
    id bigint not null primary key,
    name varchar not null unique,
    cnpj varchar(14) not null unique check (length(cnpj) >= 11)
);