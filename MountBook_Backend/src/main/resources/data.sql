INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO role (name) VALUES ('ROLE_MODERATOR');
INSERT INTO role (name) VALUES ('ROLE_USER');
INSERT INTO role (name) VALUES ('ROLE_HOST');

INSERT INTO users (email, password,username) VALUES ('ginopasticcino@email.com', 'PasswordSegretissima', 'ginoPasticcino33');
INSERT INTO users (email, password,username) VALUES ('pippofranco@email.com', 'PasswordSegretissima', 'pippoFranco99');
INSERT INTO users (email, password,username) VALUES ('cicciobello@email.com', 'PasswordSegretissima', 'CiccioBellissimo01');
INSERT INTO users (email, password,username) VALUES ('ajejebrazorf@email.com', 'PasswordSegretissima', 'AjejeBrazorf');

INSERT INTO user_role (user_id, role_id) VALUES (1,3);
INSERT INTO user_role (user_id, role_id) VALUES (2,3);
INSERT INTO user_role (user_id, role_id) VALUES (2,2);
INSERT INTO user_role (user_id, role_id) VALUES (3,3);
INSERT INTO user_role (user_id, role_id) VALUES (3,2);
INSERT INTO user_role (user_id, role_id) VALUES (4,3);
INSERT INTO user_role (user_id, role_id) VALUES (4,2);
INSERT INTO user_role (user_id, role_id) VALUES (4,1);

INSERT INTO shelter (address, altidude, close, email, name, open, telephone_number, web_site, description) VALUES ('via in cima alla montegna più alta', 2567, 2022/05/30, 'montainrefuge@email.com','il rifugio della motagna', 2022/01/01,1123456789,'www.rifugio.it','un rifugio montano alla vecchia maniera con tutti i confort moderni');
INSERT INTO shelter (address, altidude, close, email, name, open, telephone_number, web_site) VALUES ('via poco trafficata', 2567, 2022/09/30, 'rifugiocalmamente@email.com','rifugio Calmamente', 2022/04/01, 1123456789,'www.rifugio.it', 'un luogo di pace e serenità lontano dallo stress cittadino');
INSERT INTO shelter (address, altidude, close, email, name, open, telephone_number, web_site) VALUES ('corso intorno al lago', 2567, 2022/12/31, 'lakeshelter@email.com','lake resort', 2022/01/01, 1123456789,'www.rifugio.it', 'un rifugio a due passi dal lago');

INSERT INTO room (beds, description, price, shelter_id) VALUES (2, 'room 101', 25.6, 1);
INSERT INTO room (beds, description, price, shelter_id) VALUES (4, 'room 202', 46.8, 1);
INSERT INTO room (beds, description, price, shelter_id) VALUES (6, 'room 303', 102.1, 1);

INSERT INTO room (beds, description, price, shelter_id) VALUES (4, 'room 101', 60.6, 2);
INSERT INTO room (beds, description, price, shelter_id) VALUES (4, 'room 102', 60.6, 2);
INSERT INTO room (beds, description, price, shelter_id) VALUES (2, 'room 201', 45.9, 2);
INSERT INTO room (beds, description, price, shelter_id) VALUES (2, 'room 202', 45.9, 2);

INSERT INTO room (beds, description, price, shelter_id) VALUES (10, 'room 101', 90.0, 3);
INSERT INTO room (beds, description, price, shelter_id) VALUES (6, 'room 101', 60.5, 3);
INSERT INTO room (beds, description, price, shelter_id) VALUES (4, 'room 101', 40.6, 3);
INSERT INTO room (beds, description, price, shelter_id) VALUES (4, 'room 101', 40.6, 3);

INSERT INTO service (name) VALUES ('wi-fi');
INSERT INTO service (name) VALUES ('attrezzatura sci');
INSERT INTO service (name) VALUES ('servizio auto');
