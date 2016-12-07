delete from PRATTLE;
delete from PRATTLER;

Insert into PRATTLER (PRATTLER_ID,EMAIL,FULL_NAME,PASSWORD,USERNAME) values (1,'1@1.com','Name','password','username');
Insert into PRATTLER (PRATTLER_ID,EMAIL,FULL_NAME,PASSWORD,USERNAME) values (2,'bruce@mail.com','Bruce Wayne','bruce','bruce');
Insert into PRATTLER (PRATTLER_ID,EMAIL,FULL_NAME,PASSWORD,USERNAME) values (3,'toffer@mail.com','Toffer Chua','toffer','toffer');

Insert into PRATTLE (PRATTLE_ID,POSTED_DATE,PRATTLE_TEXT,PRATTLER_ID) values (100,to_date('03/JAN/15 11:03:48','DD/MON/RR HH24:MI:SS'),'Hello World',1);
Insert into PRATTLE (PRATTLE_ID,POSTED_DATE,PRATTLE_TEXT,PRATTLER_ID) values (101,to_date('01/JAN/15 11:03:48','DD/MON/RR HH24:MI:SS'),'Hola Mundo',1);
Insert into PRATTLE (PRATTLE_ID,POSTED_DATE,PRATTLE_TEXT,PRATTLER_ID) values (102,to_date('01/JAN/15 11:03:48','DD/MON/RR HH24:MI:SS'),'I''m Batman',2);

Insert into PRATTLE (PRATTLE_ID,POSTED_DATE,PRATTLE_TEXT,PRATTLER_ID) values (103,to_date('01/JAN/15 11:03:48','DD/MON/RR HH24:MI:SS'),'Tungak',1);
Insert into PRATTLE (PRATTLE_ID,POSTED_DATE,PRATTLE_TEXT,PRATTLER_ID) values (104,to_date('01/JAN/15 11:03:48','DD/MON/RR HH24:MI:SS'),'Pongak',2);

commit;