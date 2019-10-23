-- delete all rows in db tables, resets auto increment
truncate table brand;
truncate table product;

--Brand information from brands.json
insert into brand
values(1, 'Oakley');

insert into brand
values(2, 'Ray Ban');

insert into brand
values(3, 'Levis');

insert into brand
values(4, 'DKNY');

insert into brand
values(5, 'Burberry');

--Product information from products.json
-- leaving out array of imageUrls for now as sql db's shouldn't insert arrays
insert into product
values(1, 1, 'Superglasses', 'The best glasses in the world', 150);

insert into product
values(2, 1, 'Black Sunglasses', 'The best glasses in the world', 100);

insert into product
values(3, 1, 'Brown Sunglasses', 'The best glasses in the world', 50);

insert into product
values(4, 2, 'Better glasses', 'The best glasses in the world', 1500);

insert into product
values(5, 2, 'Glasses', 'The most normal glasses in the world', 150);

insert into product
values(6, 3, 'glas', 'Pretty awful glasses', 10);

insert into product
values(7, 3, 'QDogs Glasses', 'They bark', 1500);

insert into product
values(8, 4, 'Coke cans', 'The thickest glasses in the world', 110);

insert into product
values(9, 4, 'Sugar', 'The sweetest glasses in the world', 125);

insert into product
values(10, 5, 'Peanut Butter', 'The stickiest glasses in the world', 103);

insert into product
values(11, 5, 'Habanero', 'The spiciest glasses in the world', 153);