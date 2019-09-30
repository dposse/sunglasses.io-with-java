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

