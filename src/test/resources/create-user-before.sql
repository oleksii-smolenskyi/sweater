delete from user_role;
delete from usr;

insert into usr (id, active, password, username) values
(1, true, '$2a$08$waTKqd5CDsDEXmTUgbmPxOfpx4uRBdfBTJoiI4PpbBJnkyPMSK6Om', 'test_usr1'),
(2, true, '$2a$08$waTKqd5CDsDEXmTUgbmPxOfpx4uRBdfBTJoiI4PpbBJnkyPMSK6Om', 'test_usr2');

insert into user_role (user_id, roles) values
(1, 'USER'), (1, 'ADMIN'),
(2, 'USER');