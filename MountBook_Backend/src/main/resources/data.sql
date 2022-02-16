INSERT INTO role (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO role (id, name) VALUES (2, 'ROLE_MODERATOR');
INSERT INTO role (id, name) VALUES (3, 'ROLE_USER');
INSERT INTO role (id, name) VALUES (4, 'ROLE_HOST');
/*password*/
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

INSERT INTO shelter (id, name, address, email, web_site, description, max_num_bed, price, altitude, longitude, latitude, open, close, telephone_number) VALUES (1, 'il rifugio della motagna',	'via in cima alla montegna più alta',	'montainrefuge@email.com',		'www.rifugio.it',	'un rifugio montano alla vecchia maniera con tutti i confort moderni',	20, 21.60, 1896, 145.654, -56.9822, '2022-01-01', '2022-05-30', 1123456789);
INSERT INTO shelter (id, name, address, email, web_site, description, max_num_bed, price, altitude, longitude, latitude, open, close, telephone_number) VALUES (2, 'rifugio Calmamente',		'via poco trafficata',					'rifugiocalmamente@email.com',	'www.rifugio.it',	'un luogo di pace e serenità lontano dallo stress cittadino',			15, 35.80, 2068, -96.214, 65.985, '2022-04-01', '2022-09-30', 1123456789);
INSERT INTO shelter (id, name, address, email, web_site, description, max_num_bed, price, altitude, longitude, latitude, open, close, telephone_number) VALUES (3, 'lake resort',				'corso intorno al lago',				'lakeshelter@email.com',		'www.rifugio.it',	'un rifugio a due passi dal lago',										40, 40.10, 1569, 176.369, 55.5486, '2022-01-01', '2022-12-31', 1123456789);

INSERT INTO service (id, shelter_id, wifi, equipment, car) VALUES (1,1,true,true, false);
INSERT INTO service (id, shelter_id, wifi, equipment, car) VALUES (2,2,false ,false , true );
INSERT INTO service (id, shelter_id, wifi, equipment, car) VALUES (3,3,true,true, true);