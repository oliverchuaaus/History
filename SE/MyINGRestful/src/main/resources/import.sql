--morning
insert into transaction (transactionID, customerID, transaction_Date, transaction_Amount, transaction_type) values ('11111','12345', to_date('2018/05/31 11:00:00AM', 'yyyy/mm/dd hh:mi:ssam'), 123.21, 'Withdrawal');
insert into transaction (transactionID, customerID, transaction_Date, transaction_Amount, transaction_type) values ('11112','12345', to_date('2018/05/01 09:00:00AM', 'yyyy/mm/dd hh:mi:ssam'), 331.34, 'Deposit');
insert into transaction (transactionID, customerID, transaction_Date, transaction_Amount, transaction_type) values ('11113','12345', to_date('2018/05/01 09:00:00PM', 'yyyy/mm/dd hh:mi:ssam'), 1.34, 'Deposit');

--afternoon
insert into transaction (transactionID, customerID, transaction_Date, transaction_Amount, transaction_type) values ('11114','12346', to_date('2018/05/31 11:00:00PM', 'yyyy/mm/dd hh:mi:ssam'), 123.21, 'Withdrawal');
insert into transaction (transactionID, customerID, transaction_Date, transaction_Amount, transaction_type) values ('11115','12346', to_date('2018/05/01 09:00:00PM', 'yyyy/mm/dd hh:mi:ssam'), 331.34, 'Deposit');
insert into transaction (transactionID, customerID, transaction_Date, transaction_Amount, transaction_type) values ('11116','12346', to_date('2018/05/01 09:00:00AM', 'yyyy/mm/dd hh:mi:ssam'), 1.34, 'Deposit');

--big spender
insert into transaction (transactionID, customerID, transaction_Date, transaction_Amount, transaction_type) values ('11117','12347', to_date('2018/05/01 11:00:00PM', 'yyyy/mm/dd hh:mi:ssam'), 234.21, 'Withdrawal');
insert into transaction (transactionID, customerID, transaction_Date, transaction_Amount, transaction_type) values ('11118','12347', to_date('2018/05/31 09:00:00PM', 'yyyy/mm/dd hh:mi:ssam'), 31.34, 'Deposit');
insert into transaction (transactionID, customerID, transaction_Date, transaction_Amount, transaction_type) values ('11119','12347', to_date('2018/05/31 09:00:00AM', 'yyyy/mm/dd hh:mi:ssam'), 31.34, 'Deposit');

--potential saver
insert into transaction (transactionID, customerID, transaction_Date, transaction_Amount, transaction_type) values ('11120','12348', to_date('2018/05/31 11:00:00PM', 'yyyy/mm/dd hh:mi:ssam'), 134.21, 'Withdrawal');
insert into transaction (transactionID, customerID, transaction_Date, transaction_Amount, transaction_type) values ('11121','12348', to_date('2018/05/31 09:00:00PM', 'yyyy/mm/dd hh:mi:ssam'), 331.34, 'Deposit');
insert into transaction (transactionID, customerID, transaction_Date, transaction_Amount, transaction_type) values ('11122','12348', to_date('2018/05/31 09:00:00AM', 'yyyy/mm/dd hh:mi:ssam'), 331.34, 'Deposit');

--big ticket spender
insert into transaction (transactionID, customerID, transaction_Date, transaction_Amount, transaction_type) values ('11123','12349', to_date('2018/05/31 11:00:00PM', 'yyyy/mm/dd hh:mi:ssam'), 1134.21, 'Withdrawal');

--fast spender
insert into transaction (transactionID, customerID, transaction_Date, transaction_Amount, transaction_type) values ('11124','12350', to_date('2018/05/01 11:00:00PM', 'yyyy/mm/dd hh:mi:ssam'), 999.21, 'Deposit');
insert into transaction (transactionID, customerID, transaction_Date, transaction_Amount, transaction_type) values ('11125','12350', to_date('2018/05/06 11:00:00PM', 'yyyy/mm/dd hh:mi:ssam'), 780.21, 'Withdrawal');


--potential loan
insert into transaction (transactionID, customerID, transaction_Date, transaction_Amount, transaction_type) values ('11126','12351', to_date('2018/05/01 11:00:00PM', 'yyyy/mm/dd hh:mi:ssam'), 999.21, 'Deposit');
insert into transaction (transactionID, customerID, transaction_Date, transaction_Amount, transaction_type) values ('11127','12351', to_date('2018/05/06 11:00:00PM', 'yyyy/mm/dd hh:mi:ssam'), 900.21, 'Withdrawal');

