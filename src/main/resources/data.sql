insert into category (id, category_name, description) values (101, 'Cars', 'Cars');
insert into category (id, category_name, description) values (102, 'Planes', 'Planes');
insert into category (id, category_name, description) values (103, 'Trucks', 'Trucks');
insert into category (id, category_name, description) values (104, 'Boats', 'Boats');
insert into category (id, category_name, description) values (105, 'Rockets', 'Rockets');

insert into products (PRODUCT_NAME , DESCRIPTION ,IMAGE_PATH ,UNIT_PRICE ,CATEGORY_ID )
values('Convertible Car', 'This convertible car is fast! The engine is powered by a neutrino based battery (not included).Power it up and let it go!', 'carconvert.png', 22.5, 101);
insert into products (PRODUCT_NAME , DESCRIPTION ,IMAGE_PATH ,UNIT_PRICE ,CATEGORY_ID )
values('Old-time Car', 'There''s nothing old about this toy car, except it''s looks. Compatible with other old toy cars.', 'carearly.png', 15.95, 101);
insert into products (PRODUCT_NAME , DESCRIPTION ,IMAGE_PATH ,UNIT_PRICE ,CATEGORY_ID )
values('Fast Car', 'Yes this car is fast, but it also floats in water.', 'carfast.png', 32.99, 101);
insert into products (PRODUCT_NAME , DESCRIPTION ,IMAGE_PATH ,UNIT_PRICE ,CATEGORY_ID )
values('Super Fast Car', 'Use this super fast car to entertain guests. Lights and doors work!', 'carfaster.png', 8.95, 101);
insert into products (PRODUCT_NAME , DESCRIPTION ,IMAGE_PATH ,UNIT_PRICE ,CATEGORY_ID )
values('Old Style Racer', 'This old style racer can fly (with user assistance). Gravity controls flight duration.No batteries required.', 'carracer.png', 34.95, 101);
insert into products (PRODUCT_NAME , DESCRIPTION ,IMAGE_PATH ,UNIT_PRICE ,CATEGORY_ID )
values('Ace Plane', 'Authentic airplane toy. Features realistic color and details.', 'planeace.png', 95, 102);
insert into products (PRODUCT_NAME , DESCRIPTION ,IMAGE_PATH ,UNIT_PRICE ,CATEGORY_ID )
values('Glider', 'This fun glider is made from real balsa wood. Some assembly required.', 'planeglider.png', 4.95, 102);
insert into products (PRODUCT_NAME , DESCRIPTION ,IMAGE_PATH ,UNIT_PRICE ,CATEGORY_ID )
values('Paper Plane', 'This paper plane is like no other paper plane. Some folding required.', 'planepaper.png', 2.95, 102);
insert into products (PRODUCT_NAME , DESCRIPTION ,IMAGE_PATH ,UNIT_PRICE ,CATEGORY_ID )
values('Propeller Plane', 'Rubber band powered plane features two wheels.', 'planeprop.png', 32.95, 102);
insert into products (PRODUCT_NAME , DESCRIPTION ,IMAGE_PATH ,UNIT_PRICE ,CATEGORY_ID )
values('Early Truck', 'This toy truck has a real gas powered engine. Requires regular tune ups.', 'truckearly.png', 15, 103);
insert into products (PRODUCT_NAME , DESCRIPTION ,IMAGE_PATH ,UNIT_PRICE ,CATEGORY_ID )
values('Fire Truck', 'You will have endless fun with this one quarter sized fire truck.', 'truckfire.png', 26, 103);
insert into products (PRODUCT_NAME , DESCRIPTION ,IMAGE_PATH ,UNIT_PRICE ,CATEGORY_ID )
values('Big Truck', 'This fun toy truck can be used to tow other trucks that are not as big.', 'truckbig.png', 29, 103);
insert into products (PRODUCT_NAME , DESCRIPTION ,IMAGE_PATH ,UNIT_PRICE ,CATEGORY_ID )
values('Big Ship', 'Is it a boat or a ship. Let this floating vehicle decide by using its artifically intelligent computer brain!', 'boatbig.png', 95, 104);
insert into products (PRODUCT_NAME , DESCRIPTION ,IMAGE_PATH ,UNIT_PRICE ,CATEGORY_ID )
values('Paper Boat', 'Floating fun for all! This toy boat can be assembled in seconds. Floats for minutes!Some folding required.', 'boatpaper.png', 4.95, 104);
insert into products (PRODUCT_NAME , DESCRIPTION ,IMAGE_PATH ,UNIT_PRICE ,CATEGORY_ID )
values('Sail Boat', 'Put this fun toy sail boat in the water and let it go!', 'boatsail.png', 42.95, 104);
insert into products (PRODUCT_NAME , DESCRIPTION ,IMAGE_PATH ,UNIT_PRICE ,CATEGORY_ID )
values('Rocket', 'This fun rocket will travel up to a height of 200 feet.', 'rocket.png', 122.95, 105);

insert into CARTITEMS  (ID ,CART_ID ,CREATED ,QUANTITY ,PRODUCT_ID ) values('testcart01', 'testcart', CURDATE ( ), 1, 1);
insert into CARTITEMS  (ID ,CART_ID ,CREATED ,QUANTITY ,PRODUCT_ID ) values('testcart02', 'testcart', CURDATE ( ), 5, 2);

