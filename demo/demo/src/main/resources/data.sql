truncate table item;
truncate table lookup;
insert into item (name, price,category)
values('iPhone 14',799,'Cell Phones');

insert into item (name, price,category)
values('iPhone 14 Plus',899,'Cell Phones');

insert into item (name, price,category)
values('iPhone 14 Pro',999,'Cell Phones');

insert into item (name, price,category)
values('iPhone 14 Pro Max',1099,'Cell Phones');

insert into item (name, price,category)
values('iPad 9',329,'Tablet');

insert into item (name, price,category)
values('iPad 10',449,'Tablet');

insert into item (name, price,category)
values('iPad mini',499,'Tablet');

insert into item (name, price,category)
values('iPad Air',599,'Tablet');

insert into item (name, price,category)
values('iPad Pro 11',799,'Tablet');

insert into item (name, price,category)
values('iPad Pro 12.9',1099,'Tablet');

insert into item (name, price,category)
values('Apple Watch SE',249,'Watch');

insert into item (name, price,category)
values('Apple Watch 8',399,'Watch');

insert into item (name, price,category)
values('Apple Watch 8 ultra',799,'Watch');


insert into lookup (id, type, name, value)
values(1, 'ITEM_MESSAGE', 'ITEM_CREATED', 'Successfully Created Item');

insert into lookup (id, type, name, value)
values(2, 'ITEM_MESSAGE', 'ITEM_NOT_FOUND', 'Cannot Find Item');

insert into lookup (id, type, name, value)
values(3, 'ITEM_MESSAGE', 'ITEM_DELETED', 'Item is Deleted');