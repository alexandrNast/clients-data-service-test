INSERT INTO clients_data.CLIENTS(name, surname, is_active)
VALUES ('Chip', 'Dale', null);

INSERT INTO clients_data.CLIENTS(name, surname, is_active)
VALUES ('Tom', 'Jerry', false);

INSERT INTO clients_data.CLIENTS(name, surname, is_active)
VALUES ('Timon', 'Pumba', true);

INSERT INTO clients_data.CLIENTS(name, surname, is_active)
VALUES ('Timon2', 'Pumba2', true);

INSERT INTO clients_data.PRODUCTS(NUMBER, PRODUCT_TYPE, CLIENT_ID)
VALUES ('123123123123', 'credit', 1);
INSERT INTO clients_data.PRODUCTS(NUMBER, PRODUCT_TYPE, CLIENT_ID)
VALUES ('78965', 'card', 1);
INSERT INTO clients_data.PRODUCTS(NUMBER, PRODUCT_TYPE, CLIENT_ID)
VALUES ('56465465', 'card', 2);
INSERT INTO clients_data.PRODUCTS(NUMBER, PRODUCT_TYPE, CLIENT_ID)
VALUES ('63645565465', 'card', 2);
INSERT INTO clients_data.PRODUCTS(NUMBER, PRODUCT_TYPE, CLIENT_ID)
VALUES ('63645565465', 'credit', 3);