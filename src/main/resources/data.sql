-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: shopping_online_incub
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id_account` int NOT NULL AUTO_INCREMENT,
  `ref_account` varchar(250) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `password` varchar(250) NOT NULL,
  `email` varchar(100) NOT NULL,
  `reset_token` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_account`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'b771e029-f968-44a0-9655-5d17f6461ad2','toto1212','Georgesfsdfsf2222222222222','$2a$10$D6sEryXxXe5qPjE7zrJMQOncRh62e3QdSArvmfe.kA5rRBeNsUHg6','pass@incub.fr',NULL),(2,'7fc5ca19-445d-4744-88ef-098e7eee6167','Blum','Léon','$2a$10$jRZmPgJP5BewWnzKQdvlweFgESGT86RZ8vDQfP2Y1TpyltTN.ufBu','leon.blum@incub.fr',NULL),(3,'02f61887-411e-4903-9758-5d4e3ba564d3','Giscard d\'Estaing','Valéry','$2a$10$AVICGRXYZmW76jpyoPLXWe5DmRPt1WTqwREJ6LbGUxbjD5.vbolLm','valery.giscard@incub.fr',NULL),(4,'e0c001f3-c58a-432b-aa11-83bc27257c45','Chirac','Jacques','$2a$10$7gALjfOx7G9xyKGFFRqzOOLbDNJ1vWsAraBXTPwsT0x5GBHfWSyJO','jacques.chirac@incub.fr',NULL),(5,'bdc7bdc7-bc04-48c2-a9bc-a8e3d2040ec5','De Gaulle','Charles','$2a$10$WnkfRoqqxsy5viZcYZ5eZ.kuaNFZ/O5A00iP4Nd5FXWyFICr.e2cG','lionel.jospin@incub.fr',NULL),(6,'69b49538-7890-4191-b5b7-0d1cd19a4869','De Gaulle','Charles','$2a$10$E9RR7Hng/wFtaSW2.hqwXeY5dXAW5d0g0q264MA0/fZeyjoWM0s5W','charles.degaulle@incub.fr',NULL),(7,'d4435387-14ed-4b07-95ae-c1e5c78ed51e','Pompidou','Georges','$2a$10$bGaLzAl6YVQiqB1W0R5.EOSiIcUUwgqQJW9Q8wQG0vbh6Sv5ze41S','georges.pompidou@incub.fr',NULL),(8,'e52b22aa-b8de-4013-adbf-55969e21c4c6','Cresson','Edith','$2a$10$lISpEWv5x2Ezr.wIYql9FuesHGMTeWtEQQF4LyWWn3SfnlR2orRZy','edith.cresson@incub.fr',NULL),(19,'e90b5128-9b5a-46f7-b9a5-4eb508b416d3','admin','admin','$2a$10$akMnLUezDzKZddrrHc5Wleej8rWcrU1MIzne6OZW476zevicuUbYW','admin@incub.fr',NULL),(31,'9526a6d4-5b6d-444e-9817-ce4ba1da6db2','client','client','$2a$10$yN3.Qn.3JolGhD/e7t3ToePFlRUz.2Q1rrhwDnYnOMuaxH9jA2PBS','client@incub.fr',NULL),(35,'f35a6ded-c6d2-494a-bc25-a3f7362699f5','ibrahim','client','$2a$10$7338DdfFJ4I/cLxo4hyT7.4TsddpEi8ayGD/q3u9fKU2RG00.cPrG','ibra@incub.fr',NULL);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_role`
--

DROP TABLE IF EXISTS `account_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_role` (
  `id_account` int NOT NULL,
  `id_role` int NOT NULL,
  PRIMARY KEY (`id_account`,`id_role`),
  KEY `id_role` (`id_role`),
  CONSTRAINT `account_role_ibfk_1` FOREIGN KEY (`id_account`) REFERENCES `account` (`id_account`),
  CONSTRAINT `account_role_ibfk_2` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_role`
--

LOCK TABLES `account_role` WRITE;
/*!40000 ALTER TABLE `account_role` DISABLE KEYS */;
INSERT INTO `account_role` VALUES (1,1),(3,1),(4,1),(31,1),(35,1),(1,2),(2,2),(5,3),(6,3),(7,3),(8,3),(19,3);
/*!40000 ALTER TABLE `account_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id_address` int NOT NULL AUTO_INCREMENT,
  `street` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `postal_code` int NOT NULL,
  `statut_adress` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_address`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'avenue du mannekenpis','Belgique',12234,NULL),(2,'17 rue du','France',59000,NULL),(3,'53 square friant','Amiens',80000,NULL),(4,'21 rue saint leu','Amiens',80000,NULL),(5,'10 rue des motiver','Arras',62000,NULL);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id_category` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `picture_url` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id_category`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Audio',NULL),(2,'video',NULL),(3,'Smartphone',NULL),(4,'Connecté',NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact` (
  `id_contact` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `message` varchar(300) NOT NULL,
  PRIMARY KEY (`id_contact`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `id_invoice` int NOT NULL AUTO_INCREMENT,
  `ref_invoice` varchar(250) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `billing_date` date NOT NULL,
  `id_ordered` int NOT NULL,
  PRIMARY KEY (`id_invoice`),
  KEY `id_ordered` (`id_ordered`),
  CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`id_ordered`) REFERENCES `ordered` (`id_ordered`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,NULL,'avoir battle','2005-06-05',1),(2,NULL,'Aventure-shop','2022-10-04',3),(3,NULL,'titok','2022-02-02',4),(4,NULL,'Univ blackfriday','2023-03-22',5),(5,NULL,'Go-shop','2024-04-28',7),(6,NULL,'Anger','2023-10-10',6);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `id_product` int NOT NULL,
  `id_ordered` int NOT NULL,
  `amount` int NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id_product`,`id_ordered`),
  KEY `id_ordered` (`id_ordered`),
  CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`),
  CONSTRAINT `order_details_ibfk_2` FOREIGN KEY (`id_ordered`) REFERENCES `ordered` (`id_ordered`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` VALUES (1,1,10,70.3),(1,8,50,70.3),(4,1,36,20),(4,2,50,20),(4,3,25,20),(7,1,20,13.25);
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordered`
--

DROP TABLE IF EXISTS `ordered`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordered` (
  `id_ordered` int NOT NULL AUTO_INCREMENT,
  `ref_ordered` varchar(250) DEFAULT NULL,
  `ordered_date` date NOT NULL,
  `order_status` varchar(50) NOT NULL,
  `delivery_ordered` date NOT NULL,
  `id_adress_delivery` int NOT NULL,
  `id_adress_invoiced` int NOT NULL,
  `id_account` int NOT NULL,
  PRIMARY KEY (`id_ordered`),
  KEY `id_adress_delivery` (`id_adress_delivery`),
  KEY `id_adress_invoiced` (`id_adress_invoiced`),
  KEY `id_account` (`id_account`),
  CONSTRAINT `ordered_ibfk_1` FOREIGN KEY (`id_adress_delivery`) REFERENCES `address` (`id_address`),
  CONSTRAINT `ordered_ibfk_2` FOREIGN KEY (`id_adress_invoiced`) REFERENCES `address` (`id_address`),
  CONSTRAINT `ordered_ibfk_3` FOREIGN KEY (`id_account`) REFERENCES `account` (`id_account`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordered`
--

LOCK TABLES `ordered` WRITE;
/*!40000 ALTER TABLE `ordered` DISABLE KEYS */;
INSERT INTO `ordered` VALUES (1,NULL,'2025-11-13','CREATED','2025-10-20',2,1,2),(2,NULL,'2005-05-24','DELIVERED','2005-06-05',1,4,1),(3,NULL,'2022-01-27','DELIVERED','2022-10-04',2,1,2),(4,NULL,'2022-02-02','PENDING','2022-02-28',5,3,1),(5,NULL,'2023-03-18','CANCELLED','2024-03-30',5,2,6),(6,NULL,'2023-02-22','DELIVERED','2023-03-22',4,5,3),(7,NULL,'2021-04-15','PAID','2021-05-13',3,3,5),(8,NULL,'2024-04-28','SHIPPED','2024-04-30',1,1,3),(9,NULL,'2023-09-22','CREATED','2023-10-10',2,4,5),(10,'ff99e56a-44bd-4403-9cbf-78f25c8c0b28','2025-11-13','CREATED','2025-10-20',2,2,2),(11,'a744e2c4-936f-462f-b8d8-31d284f12515','2025-11-13','CREATED','2025-10-20',2,2,2),(12,'efdc3e29-aa1b-4494-8a31-ae46e52468e7','2025-11-13','CREATED','2025-10-20',2,2,2);
/*!40000 ALTER TABLE `ordered` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `picture`
--

DROP TABLE IF EXISTS `picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `picture` (
  `id_picture` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `url` varchar(300) DEFAULT NULL,
  `id_product` int NOT NULL,
  PRIMARY KEY (`id_picture`),
  KEY `id_product` (`id_product`),
  CONSTRAINT `picture_ibfk_1` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture`
--

LOCK TABLES `picture` WRITE;
/*!40000 ALTER TABLE `picture` DISABLE KEYS */;
INSERT INTO `picture` VALUES (1,'asterixlegaulois.jpg',NULL,1),(5,'homersimpson.jpg',NULL,6),(6,'donaldduck.jpg',NULL,7),(7,'scatcat.jpg',NULL,8);
/*!40000 ALTER TABLE `picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id_product` int NOT NULL AUTO_INCREMENT,
  `ref_product` varchar(100) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `price_ttc` double NOT NULL,
  `product_inventory` int NOT NULL,
  `id_category` int NOT NULL,
  `product_description` varchar(350) DEFAULT NULL,
  `present` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id_product`),
  KEY `id_category` (`id_category`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'1bb7aa77-28bc-4642-8448-6aaf3a2c69f7','Ipad Air 4 ',70.3,50,4,'Tablette Apple dernière génération',1),(4,NULL,'Pc Lenovo ',299,15,2,'Pc portable haute performance',1),(6,NULL,'Iphone 14 Pro',999,15,3,'Smarthone Apple dernière génération',1),(7,NULL,'Ear Pods',250,10,4,'Oreillette Huawei ',1),(8,NULL,'Télé Samsung',699,15,1,'Tv Samsung connecté 180\' ',1),(32,'1ebe1cac-ba0f-47e3-87ca-6c7cb79bfa73','Ibarhim',50,15,2,'jeu de foot',1),(37,'4c7ab177-e409-43ec-931d-c9b561c59e4c','Développeur Java - Junior',3,4,2,'Jeu de science Fiction ',1),(40,'dad5c217-ad41-42aa-8141-d0e6febc0010','Tv sony',250,14,2,'Tv Oled Playstore',1),(41,'19748c84-3ffc-475a-abb4-58bbc9716aff','Tv sony',250,14,2,'Tv Oled Playstore',1),(42,'62b2820e-e34f-4423-85cf-129af636d7ce','Tv sony',250,14,2,'Tv Oled Playstore',1),(43,'9ab9772a-d126-4198-bca0-e892733621e6','Tv sony',250,14,2,'Tv Oled Playstore',1),(44,'9738c8c0-421f-4bc4-8821-1b704033b62d','Tv sony',250,14,2,'Tv Oled Playstore',1),(45,'382f9b57-9c64-44f8-8d98-98383ba97fff','Tv sony',250,14,2,'Tv Oled Playstore',1),(46,'9ab65024-4f15-4464-977b-a95cfc873d58','Tv sony',250,14,2,'Tv Oled Playstore',1),(47,'29bde38c-ad96-449a-8b59-0a3f482df3b5','Tv sony',250,14,2,'Tv Oled Playstore',1),(48,'3d08c249-c0d8-4d33-a80b-a3eafd66d05b','Tv Thomson',250,14,2,'Tv Oled Playstore 23',1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id_role` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_CLIENT'),(2,'ROLE_CUSTOMER'),(3,'ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uuid`
--

DROP TABLE IF EXISTS `uuid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uuid` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uuid_generate` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uuid`
--

LOCK TABLES `uuid` WRITE;
/*!40000 ALTER TABLE `uuid` DISABLE KEYS */;
/*!40000 ALTER TABLE `uuid` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-08 10:59:38
