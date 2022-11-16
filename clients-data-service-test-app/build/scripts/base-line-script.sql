-- Удаление старой схемы и пользователя
DROP SCHEMA IF EXISTS clients_data CASCADE;
DROP USER IF EXISTS clients_data;
-- Создание пользователя, схемы и предоставление прав
CREATE USER clients_data WITH PASSWORD 'clients_data';
CREATE SCHEMA clients_data;
GRANT USAGE ON SCHEMA clients_data TO clients_data;
ALTER DEFAULT PRIVILEGES IN SCHEMA clients_data GRANT ALL ON TABLES TO clients_data;
ALTER DEFAULT PRIVILEGES IN SCHEMA clients_data GRANT ALL ON SEQUENCES TO clients_data;
--Таблица TestEntry
CREATE TABLE clients_data.ENTITY
(
  ID         BIGSERIAL NOT NULL,
  NAME       VARCHAR(255) NOT NULL,
  PRIMARY KEY(ID)
);

CREATE TABLE clients_data.CLIENTS (
    ID                      BIGSERIAL NOT NULL,
    NAME                    VARCHAR(255) NOT NULL,
    SURNAME                 VARCHAR(255) NOT NULL,
    IS_ACTIVE               BOOLEAN,
    PRIMARY KEY (ID)
);

CREATE TABLE clients_data.PRODUCTS (
   ID                      BIGSERIAL NOT NULL,
   NUMBER                  VARCHAR(60) NOT NULL,
   PRODUCT_TYPE            VARCHAR(60) NOT NULL,
   CLIENT_ID               BIGSERIAL NOT NULL,
   PRIMARY KEY (ID),
   FOREIGN KEY (CLIENT_ID) REFERENCES clients_data.CLIENTS (ID) ON DELETE CASCADE
);