
insert into "school" ("school_number", "school_name") values (1, 'Факультет 1');
insert into "school" ("school_number", "school_name") values (2, 'Факультет 2');
insert into "school" ("school_number", "school_name") values (3, 'Факультет 3');
insert into "school" ("school_number", "school_name") values (4, 'Факультет 4');
insert into "school" ("school_number", "school_name") values (5, 'Факультет 5');


insert into "batch" ("batch_id", "batch_number", "batch_school_id") values (1,'111',1);
insert into "batch" ("batch_id", "batch_number", "batch_school_id") values (2,'112',1);
insert into "batch" ("batch_id", "batch_number", "batch_school_id") values (3,'113',1);
insert into "batch" ("batch_id", "batch_number", "batch_school_id") values (4,'121',1);
insert into "batch" ("batch_id", "batch_number", "batch_school_id") values (5,'122',1);
insert into "batch" ("batch_id", "batch_number", "batch_school_id") values (6,'123',1);
insert into "batch" ("batch_id", "batch_number", "batch_school_id") values (7,'411',4);
insert into "batch" ("batch_id", "batch_number", "batch_school_id") values (8,'412',4);
insert into "batch" ("batch_id", "batch_number", "batch_school_id") values (9,'413',4);
insert into "batch" ("batch_id", "batch_number", "batch_school_id") values (10,'421',4);
insert into "batch" ("batch_id", "batch_number", "batch_school_id") values (11,'422',4);
insert into "batch" ("batch_id", "batch_number", "batch_school_id") values (12,'423',4);
                        

--login guest password hello
insert into "user"("login", "pass_hash") values ('guest','$2a$10$6mf3CesQx9eRGB4B3sjr8e1eSr5cYO/zt87bwYVdA4O8rmjDMDdHO');
