INSERT INTO role (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO role (id, name) VALUES (2, 'ROLE_MODERATOR');
INSERT INTO role (id, name) VALUES (3, 'ROLE_USER');
INSERT INTO role (id, name) VALUES (4, 'ROLE_HOST');

INSERT INTO users (id, email, password,username) VALUES (1, 'ginopasticcino@email.com', '$2a$10$rQWQVQzeZGLE7BB9Vgv5LuaH1SW.S43eJLnx3H80C3sHNlFXU8xZ6', 'ginoPasticcino33');
INSERT INTO users (id, email, password,username) VALUES (2, 'pippofranco@email.com', '$2a$10$rQWQVQzeZGLE7BB9Vgv5LuaH1SW.S43eJLnx3H80C3sHNlFXU8xZ6', 'pippoFranco99');
INSERT INTO users (id, email, password,username) VALUES (3, 'cicciobello@email.com', '$2a$10$rQWQVQzeZGLE7BB9Vgv5LuaH1SW.S43eJLnx3H80C3sHNlFXU8xZ6', 'CiccioBellissimo01');
INSERT INTO users (id, email, password,username) VALUES (4, 'ajejebrazorf@email.com', '$2a$10$rQWQVQzeZGLE7BB9Vgv5LuaH1SW.S43eJLnx3H80C3sHNlFXU8xZ6', 'AjejeBrazorf');
INSERT INTO users (id, email, password,username) VALUES (5, 'fasino45@mail.com', '$2a$10$rQWQVQzeZGLE7BB9Vgv5LuaH1SW.S43eJLnx3H80C3sHNlFXU8xZ6', 'PierFrancesco45');

INSERT INTO user_roles (user_id, role_id) VALUES (1,3);
INSERT INTO user_roles (user_id, role_id) VALUES (2,3);
INSERT INTO user_roles (user_id, role_id) VALUES (2,2);
INSERT INTO user_roles (user_id, role_id) VALUES (3,3);
INSERT INTO user_roles (user_id, role_id) VALUES (3,2);
INSERT INTO user_roles (user_id, role_id) VALUES (4,3);
INSERT INTO user_roles (user_id, role_id) VALUES (4,2);
INSERT INTO user_roles (user_id, role_id) VALUES (4,1);
INSERT INTO user_roles (user_id, role_id) VALUES (5,3);
INSERT INTO user_roles (user_id, role_id) VALUES (5,4);

INSERT INTO shelter (id, address, max_num_bed, price, altitude, longitude, latitude, close, email, name, open, telephone_number, web_site, description) VALUES (1, 'via in cima alla montegna più alta', 20, 21, 2567, 145.654, -56.9822, '2022-05-30', 'montainrefuge@email.com','il rifugio della motagna', '2022-01-01', 1123456789,'www.rifugio.it','un rifugio montano alla vecchia maniera con tutti i confort moderni');
INSERT INTO shelter (id, address, max_num_bed, price, altitude, longitude, latitude, close, email, name, open, telephone_number, web_site, description) VALUES (2, 'via poco trafficata', 2567, -96.214, 20, 38, 65.985, '2022-09-30', 'rifugiocalmamente@email.com','rifugio Calmamente', '2022-04-01', 1123456789,'www.rifugio.it', 'un luogo di pace e serenità lontano dallo stress cittadino');
INSERT INTO shelter (id, address, max_num_bed, price, altitude, longitude, latitude, close, email, name, open, telephone_number, web_site, description) VALUES (3, 'corso intorno al lago', 2567, 176.369, 19, 20, 55.5486, '2022-12-31', 'lakeshelter@email.com','lake resort', '2022-01-01', 1123456789,'www.rifugio.it', 'un rifugio a due passi dal lago');

INSERT INTO service (id, shelter_id, wifi, equipment, car) VALUES (1,1,true,true, false);
INSERT INTO service (id, shelter_id, wifi, equipment, car) VALUES (2,2,false ,false , true );
INSERT INTO service (id, shelter_id, wifi, equipment, car) VALUES (3,3,true,true, true);
