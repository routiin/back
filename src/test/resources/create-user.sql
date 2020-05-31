delete from USER;

insert into user(id, login, email, first_name, last_name, image_url, score, provider, provider_id, status) values
(1, "Frodo", "fr@email.com", "Frodo", "F", "image", 20, "local", "0", "ACTIVE" );

insert into user(id, login, email, first_name, last_name, image_url, score, provider, provider_id, status) values
(2, "Aragon", "ar@email.com", "Aragon", "A", "image", 14, "local", "0", "DELETED" );

insert into user(id, login, email, first_name, last_name, image_url, score, provider, provider_id, status) values
(3, "Legolas", "le@email.com", "Legolas", "L", "image", 94, "local", "0", "ACTIVE" );

insert into user(id, login, email, first_name, last_name, image_url, score, provider, provider_id, status) values
(4, "Gollum", "go@email.com", "Gollum", "", "image", 500, "local", "0", "ACTIVE" );
