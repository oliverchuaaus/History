drop table Unit cascade constraints;
drop sequence SEQ_UNIT;
create table Unit (UNIT_CODE number(19,0) not null, UNIT_NAME varchar2(255 char), SUPER_UNIT_CODE number(19,0), primary key (UNIT_CODE));
alter table Unit add constraint FK284DA4768DFBFF foreign key (SUPER_UNIT_CODE) references Unit;
create sequence SEQ_UNIT;
