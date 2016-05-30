insert into product(name, description) values('Product1', 'This is product 1');
insert into product(name, description) values('Product2', 'This is product 2');

insert into account(username, password, enable, expired, locked, credentialexpired) values('user', 'thang', FALSE , FALSE , FALSE , FALSE );
insert into account(username, password, enable, expired, locked, credentialexpired) values('operation', 'operation', FALSE , FALSE , FALSE , FALSE );

insert into role(id, code, label) VALUES (1, 'ROLE_USER', 'User');
insert into role(id, code, label) VALUES (2, 'ROLE_ADMIN', 'Admin');
insert into role(id, code, label) VALUES (3, 'ROLE_SYSADMIN', 'System Admin');

insert into AccountRole(accountId, roleId) SELECT a.id, r.id from Account a, Role r WHERE a.username = 'user' and r.id = 1;
insert into AccountRole(accountId, roleId) SELECT a.id, r.id from Account a, Role r WHERE a.username = 'operation' and r.id = 3;