INSERT INTO account (id_account, ref_account, name, first_name, password, email, active) VALUES 
(1,'b771e029-f968-44a0-9655-5d17f6461ad2','toto1212','Georgesfsdfsf2222222222222','$2a$10$D6sEryXxXe5qPjE7zrJMQOncRh62e3QdSArvmfe.kA5rRBeNsUHg6','pass@incub.fr', 1),
(2,'7fc5ca19-445d-4744-88ef-098e7eee6167','Blum','Léon','$2a$10$jRZmPgJP5BewWnzKQdvlweFgESGT86RZ8vDQfP2Y1TpyltTN.ufBu','leon.blum@incub.fr', 1),
(3,'02f61887-411e-4903-9758-5d4e3ba564d3','Giscard d''Estaing','Valéry','$2a$10$AVICGRXYZmW76jpyoPLXWe5DmRPt1WTqwREJ6LbGUxbjD5.vbolLm','valery.giscard@incub.fr', 1),
(4,'e0c001f3-c58a-432b-aa11-83bc27257c45','Chirac','Jacques','$2a$10$7gALjfOx7G9xyKGFFRqzOOLbDNJ1vWsAraBXTPwsT0x5GBHfWSyJO','jacques.chirac@incub.fr', 1),
(5,'bdc7bdc7-bc04-48c2-a9bc-a8e3d2040ec5','De Gaulle','Charles','$2a$10$WnkfRoqqxsy5viZcYZ5eZ.kuaNFZ/O5A00iP4Nd5FXWyFICr.e2cG','lionel.jospin@incub.fr', 1),
(6,'69b49538-7890-4191-b5b7-0d1cd19a4869','De Gaulle','Charles','$2a$10$E9RR7Hng/wFtaSW2.hqwXeY5dXAW5d0g0q264MA0/fZeyjoWM0s5W','charles.degaulle@incub.fr', 1),
(7,'d4435387-14ed-4b07-95ae-c1e5c78ed51e','Pompidou','Georges','$2a$10$bGaLzAl6YVQiqB1W0R5.EOSiIcUUwgqQJW9Q8wQG0vbh6Sv5ze41S','georges.pompidou@incub.fr', 1),
(8,'e52b22aa-b8de-4013-adbf-55969e21c4c6','Cresson','Edith','$2a$10$lISpEWv5x2Ezr.wIYql9FuesHGMTeWtEQQF4LyWWn3SfnlR2orRZy','edith.cresson@incub.fr', 1),
(19,'e90b5128-9b5a-46f7-b9a5-4eb508b416d3','admin','admin','$2a$10$akMnLUezDzKZddrrHc5Wleej8rWcrU1MIzne6OZW476zevicuUbYW','admin@incub.fr', 1),
(31,'9526a6d4-5b6d-444e-9817-ce4ba1da6db2','client','client','$2a$10$yN3.Qn.3JolGhD/e7t3ToePFlRUz.2Q1rrhwDnYnOMuaxH9jA2PBS','client@incub.fr', 1),
(35,'f35a6ded-c6d2-494a-bc25-a3f7362699f5','ibrahim','client','$2a$10$7338DdfFJ4I/cLxo4hyT7.4TsddpEi8ayGD/q3u9fKU2RG00.cPrG','ibra@incub.fr', 1);

INSERT INTO role VALUES 
(1,'ROLE_CLIENT'),
(2,'ROLE_CUSTOMER'),
(3,'ROLE_ADMIN');

INSERT INTO account_role VALUES 
(1,1),
(3,1),
(4,1),
(31,1),
(35,1),
(1,2),
(2,2),
(5,3),
(6,3),
(7,3),
(8,3),
(19,3);

INSERT INTO address VALUES 
(1,'avenue du mannekenpis','Belgique',12234,NULL),
(2,'17 rue du','France',59000,NULL),
(3,'53 square friant','Amiens',80000,NULL),
(4,'21 rue saint leu','Amiens',80000,NULL),
(5,'10 rue des motiver','Arras',62000,NULL);

INSERT INTO category VALUES 
(1,'Audio',NULL),
(2,'video',NULL),
(3,'Smartphone',NULL),
(4,'Connecté',NULL);

INSERT INTO product VALUES 
(1,'1bb7aa77-28bc-4642-8448-6aaf3a2c69f7','Ipad Air 4 ',70.3,50,4,'Tablette Apple dernière génération',1),
(4,NULL,'Pc Lenovo ',299,15,2,'Pc portable haute performance',1),
(6,NULL,'Iphone 14 Pro',999,15,3,'Smarthone Apple dernière génération',1),
(7,NULL,'Ear Pods',250,10,4,'Oreillette Huawei ',1),
(8,NULL,'Télé Samsung',699,15,1,'Tv Samsung connecté 180',1),
(32,'1ebe1cac-ba0f-47e3-87ca-6c7cb79bfa73','Ibarhim',50,15,2,'jeu de foot',1),
(37,'4c7ab177-e409-43ec-931d-c9b561c59e4c','Développeur Java - Junior',3,4,2,'Jeu de science Fiction ',1),
(40,'dad5c217-ad41-42aa-8141-d0e6febc0010','Tv sony',250,14,2,'Tv Oled Playstore',1),
(41,'19748c84-3ffc-475a-abb4-58bbc9716aff','Tv sony',250,14,2,'Tv Oled Playstore',1),
(42,'62b2820e-e34f-4423-85cf-129af636d7ce','Tv sony',250,14,2,'Tv Oled Playstore',1),
(43,'9ab9772a-d126-4198-bca0-e892733621e6','Tv sony',250,14,2,'Tv Oled Playstore',1),
(44,'9738c8c0-421f-4bc4-8821-1b704033b62d','Tv sony',250,14,2,'Tv Oled Playstore',1),
(45,'382f9b57-9c64-44f8-8d98-98383ba97fff','Tv sony',250,14,2,'Tv Oled Playstore',1),
(46,'9ab65024-4f15-4464-977b-a95cfc873d58','Tv sony',250,14,2,'Tv Oled Playstore',1),
(47,'29bde38c-ad96-449a-8b59-0a3f482df3b5','Tv sony',250,14,2,'Tv Oled Playstore',1),
(48,'3d08c249-c0d8-4d33-a80b-a3eafd66d05b','Tv Thomson',250,14,2,'Tv Oled Playstore 23',1);

INSERT INTO picture VALUES 
(1,'asterixlegaulois.jpg',NULL,1),
(5,'homersimpson.jpg',NULL,6),
(6,'donaldduck.jpg',NULL,7),
(7,'scatcat.jpg',NULL,8);

INSERT INTO ordered VALUES 
(1,NULL,'2025-11-13','CREATED','2025-10-20',2,1,2),
(2,NULL,'2005-05-24','DELIVERED','2005-06-05',1,4,1),
(3,NULL,'2022-01-27','DELIVERED','2022-10-04',2,1,2),
(4,NULL,'2022-02-02','PENDING','2022-02-28',5,3,1),
(5,NULL,'2023-03-18','CANCELLED','2024-03-30',5,2,6),
(6,NULL,'2023-02-22','DELIVERED','2023-03-22',4,5,3),
(7,NULL,'2021-04-15','PAID','2021-05-13',3,3,5),
(8,NULL,'2024-04-28','SHIPPED','2024-04-30',1,1,3),
(9,NULL,'2023-09-22','CREATED','2023-10-10',2,4,5),
(10,'ff99e56a-44bd-4403-9cbf-78f25c8c0b28','2025-11-13','CREATED','2025-10-20',2,2,2),
(11,'a744e2c4-936f-462f-b8d8-31d284f12515','2025-11-13','CREATED','2025-10-20',2,2,2),
(12,'efdc3e29-aa1b-4494-8a31-ae46e52468e7','2025-11-13','CREATED','2025-10-20',2,2,2);

INSERT INTO order_details VALUES 
(1,1,10,70.3),
(1,8,50,70.3),
(4,1,36,20),
(4,2,50,20),
(4,3,25,20),
(7,1,20,13.25);

INSERT INTO invoice VALUES 
(1,NULL,'avoir battle','2005-06-05',1),
(2,NULL,'Aventure-shop','2022-10-04',3),
(3,NULL,'titok','2022-02-02',4),
(4,NULL,'Univ blackfriday','2023-03-22',5),
(5,NULL,'Go-shop','2024-04-28',7),
(6,NULL,'Anger','2023-10-10',6);
