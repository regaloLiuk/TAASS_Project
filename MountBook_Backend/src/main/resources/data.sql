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

INSERT INTO structure (id, name, address, email, web_site, image, description, max_num_bed, price, altitude, longitude, latitude, open, close, telephone_number, type) VALUES (1, 'il rifugio della motagna',	    'via in cima alla montegna più alta',	'montainrefuge@email.com',		    'www.rifugio.it', 'https://www.dolomiti.org/storage/13024/_A9R2481_Bandion.jpg',	                                                                    'un rifugio montano alla vecchia maniera con tutti i confort moderni',	20, 21.60, 1896, 145.654, -56.9822, '2022-01-01', '2100-12-31', 1123456789,0);
INSERT INTO structure (id, name, address, email, web_site, image, description, max_num_bed, price, altitude, longitude, latitude, open, close, telephone_number, type) VALUES (2, 'rifugio Calmamente',		        'via poco trafficata',					'rifugiocalmamente@email.com',	    'www.rifugio.it', 'https://www.ilovevaldinon.it/wp-content/uploads/2019/04/rifugi-del-brenta-rifugio-agostini-andreas-tamanini-ilovevaldinon-2.jpg',    'un luogo di pace e serenità lontano dallo stress cittadino',			15, 35.80, 2068, -96.214, 65.985, '2022-01-01', '2100-12-31', 1123456789,0);
INSERT INTO structure (id, name, address, email, web_site, image, description, max_num_bed, price, altitude, longitude, latitude, open, close, telephone_number, type) VALUES (3, 'lake resort',				    'corso intorno al lago',				'lakeshelter@email.com',		    'www.rifugio.it', 'https://www.pinetahotels.it/blog/wp-content/uploads/2018/06/rifugi-trentino.jpg',	                                                'un rifugio a due passi dal lago',										40, 40.10, 1569, 176.369, 55.5486, '2022-01-01', '2100-12-31', 1123456789,0);
INSERT INTO structure (id, name, address, email, web_site, image, description, max_num_bed, price, altitude, longitude, latitude, open, close, telephone_number, type) VALUES (4, 'bivacco su in montagna',	        'monte bianco',			            	'bivaccosuinmontagna@email.com',    'www.bivacco.it', 'https://www.touringclub.it/sites/default/files/styles/gallery_full/public/immagini_georiferite/locatelli3.png?itok=jD0l1ljC',	    'bivacco in cima alla montagna più alta',								8,    0,   2586, 146.687, -32.751, '2022-01-01', '2100-12-31', 1123456789,1);
INSERT INTO structure (id, name, address, email, web_site, image, description, max_num_bed, price, altitude, longitude, latitude, open, close, telephone_number, type) VALUES (5, 'bivacco montano molto bello',    'monte rosa',  		            	    'bivaccomoltobello@email.com',      'www.bivacco.it', 'https://www.ilgazzettino.it/photos/MED_HIGH/64/59/5786459_2145_immagine_2021_02_23_174744.jpg',	                                    'bivacco bello sulla montagna bella',					    			5,    0,   3000, -55.672, 56.456, '2022-01-01', '2100-12-31', 1123456789,1);
INSERT INTO structure (id, name, address, email, web_site, image, description, max_num_bed, price, altitude, longitude, latitude, open, close, telephone_number, type) VALUES (6, 'bivacco grande e comodo',	    'monte nero',			            	'bivaccogrande@email.com',          'www.bivacco.it', 'https://www.montagnenostre.net/montagnenostre/wp-content/uploads/2014/06/RIf_CapannaRegMargherita.jpg',	                            'bivacco grande e accogliente',							            	12,   0,   3621, 35.687, -52.453, '2022-01-01', '2100-12-31', 1123456789,1);

INSERT INTO service (id, structure_id, wifi, equipment, car) VALUES (1,1,true,true, false);
INSERT INTO service (id, structure_id, wifi, equipment, car) VALUES (2,2,false ,false , true );
INSERT INTO service (id, structure_id, wifi, equipment, car) VALUES (3,3,true,true, true);

INSERT INTO reservation (id, user_id, structure_id, guests, first_day, last_day) VALUES (1, 1, 2, 4, '2020-05-06', '2020-05-12');
INSERT INTO reservation (id, user_id, structure_id, guests, first_day, last_day) VALUES (2, 1, 3, 2, '2021-12-10', '2021-12-20');
INSERT INTO reservation (id, user_id, structure_id, guests, first_day, last_day) VALUES (3, 2, 4, 4, '2020-01-20', '2020-01-22');
INSERT INTO reservation (id, user_id, structure_id, guests, first_day, last_day) VALUES (4, 2, 5, 6, '2020-06-03', '2020-06-10');
INSERT INTO reservation (id, user_id, structure_id, guests, first_day, last_day) VALUES (5, 2, 1, 4, '2022-10-12', '2022-10-15');

INSERT INTO comment (id, clear, food, location, ospitality, service, structure_id, user_id) VALUES (1, true, true, true, true, true, 2, 1);
INSERT INTO comment (id, clear, food, location, ospitality, service, structure_id, user_id) VALUES (2, true, false, true, false, true, 3, 1);
INSERT INTO comment (id, clear, food, location, ospitality, service, structure_id, user_id) VALUES (3, true, true, true, true, true, 1, 2);
INSERT INTO comment (id, clear, food, location, ospitality, service, structure_id, user_id) VALUES (4, true, false, true, true, true, 5, 2);
INSERT INTO comment (id, clear, food, location, ospitality, service, structure_id, user_id) VALUES (5, true, true, true, false, true, 4, 2);
