create table if not exists box (id bigserial not null, number integer, owner_id bigint, primary key (id));
create table if not exists offer (id bigserial not null, cost integer, discount_max integer, discount_min integer, hour_of_start varchar(255), is_confirmed boolean, is_booked boolean, is_offer_realized boolean, name varchar(255), box_id bigint, primary key (id));
create table if not exists person_app (id bigserial not null, created_at timestamp(6), created_by varchar(255), deleted_at timestamp(6), can_give_discount boolean, removed boolean, name varchar(255), password varchar(255), role varchar, primary key (id));
alter table if exists box add constraint FK2avdjabodnx5xkfta7ppdd2bd foreign key (owner_id) references person_app;
alter table if exists offer add constraint FKmdwe8bqhx1rcwj8xwlxdve2k0 foreign key (box_id) references box;
insert into person_app (name, role, created_at, created_by, deleted_at,
                        can_give_discount, removed, password)
values ('admin', 'ROLE_ADMIN', CURRENT_TIMESTAMP, null, NULL, TRUE, FALSE, 'password');
insert into box (number, owner_id) values (1, 1);
insert into offer (cost, discount_max, discount_min, hour_of_start, is_confirmed, is_booked, is_offer_realized, name, box_id)
values (100, 10, 0, '08:00', TRUE, TRUE,  FALSE, 'offer1', 1);
insert into offer (cost, discount_max, discount_min, hour_of_start, is_confirmed, is_booked, is_offer_realized, name, box_id)
values (100, 10, 0, '09:00', FALSE, FALSE,  FALSE, 'offer1', 1);