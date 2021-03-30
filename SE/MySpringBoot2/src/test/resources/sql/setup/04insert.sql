insert into occupation (id, occupation) values (1,'Programmer');

insert into hobby (id, hobby) values (1,'Dancing');
insert into hobby (id, hobby) values (2,'Hiking');

INSERT INTO	employee (id, birthday, first_name,	last_name,	gender,	occupation_id) VALUES(1, NULL, NULL, NULL, NULL, NULL );
INSERT INTO	employee (id, birthday, first_name,	last_name,	gender,	occupation_id) VALUES(2,'1977-06-20','Oliver','Chua','M', NULL );
INSERT INTO	employee (id, birthday, first_name,	last_name,	gender,	occupation_id) VALUES(3,'1977-06-20','Oliver','Chua','M',1);

INSERT INTO employee_hobby (employee_id, hobby_id) VALUES(3, 1);
INSERT INTO employee_hobby (employee_id, hobby_id) VALUES(3, 2);

INSERT INTO address (id, postcode, street, suburb, unit_number, employee_id) VALUES(3, '2131', NULL, NULL, NULL, NULL );

INSERT INTO personal_detail (employee_id, detail, required_detail) VALUES(3, NULL, 'detail');
INSERT INTO professional_detail (employee_id, detail) VALUES(3, 'detail');

