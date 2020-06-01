delete from card_template;
delete from card;

insert into card_template(id, description, title, status, created_date) values
            (1, 'trip to Mordor', 'Destroy the ring', 'ACTIVE', '2020-05-31 00:00:10');

insert into card(id, start_date, card_template_id, user_id) values
    (1, '2020-05-31 23:21:10', 1, 1);