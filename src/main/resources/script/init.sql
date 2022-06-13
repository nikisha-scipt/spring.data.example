create table if not exists product (
    id bigserial primary key,
    title varchar(30) not null,
    price int not null
);

insert into product(title,price) values ('orange', 40), ('banana', 66),('apple', 77),('yogurt', 11),('cucumbers', 111),('tomatoes', 55),('kiwi', 88),('onions', 98),('chips', 34),('potatoes', 55),('carrots', 62),('cabbage', 23),('greens', 44),('radishes', 65),('nuts', 72),('bread', 13),('milk', 14),('kefir', 17),('strudel', 22),('sausage', 45) on conflict (title) do nothing;