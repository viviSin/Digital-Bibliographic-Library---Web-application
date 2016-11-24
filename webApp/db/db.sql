-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: comp9321ass2
-- ------------------------------------------------------
-- Server version	5.7.15-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `_write`
--

DROP TABLE IF EXISTS `_write`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_write` (
  `Items_id` int(11) NOT NULL,
  `Authors_id` int(11) NOT NULL,
  KEY `fk_write_Items1_idx` (`Items_id`),
  KEY `fk_write_Authors1_idx` (`Authors_id`),
  CONSTRAINT `fk_write_Authors1` FOREIGN KEY (`Authors_id`) REFERENCES `authors` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_write_Items1` FOREIGN KEY (`Items_id`) REFERENCES `items` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_write`
--

LOCK TABLES `_write` WRITE;
/*!40000 ALTER TABLE `_write` DISABLE KEYS */;
/*!40000 ALTER TABLE `_write` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(145) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT 'Anonymous',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ban`
--

DROP TABLE IF EXISTS `ban`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ban` (
  `Admins_id` int(11) NOT NULL,
  `Users_id` int(11) NOT NULL,
  UNIQUE KEY `fk_ban_Users1_idx` (`Users_id`),
  KEY `fk_ban_Admins1_idx` (`Admins_id`),
  CONSTRAINT `fk_ban_Admins1` FOREIGN KEY (`Admins_id`) REFERENCES `admins` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ban_Users1` FOREIGN KEY (`Users_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ban`
--

LOCK TABLES `ban` WRITE;
/*!40000 ALTER TABLE `ban` DISABLE KEYS */;
/*!40000 ALTER TABLE `ban` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `publtype` varchar(45) DEFAULT NULL,
  `publyear` int(11) DEFAULT NULL,
  `title` varchar(45) NOT NULL,
  `venues` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `onsaleitems`
--

DROP TABLE IF EXISTS `onsaleitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `onsaleitems` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Items_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `Users_id` int(11) NOT NULL,
  `isPaused` tinyint(4) NOT NULL DEFAULT '1',
  `mdate` datetime NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ShoppingCartItems_Items1_idx` (`Items_id`),
  KEY `fk_ShoppingCartItems_Users1_idx` (`Users_id`),
  CONSTRAINT `fk_ShoppingCartItems_Items1` FOREIGN KEY (`Items_id`) REFERENCES `items` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ShoppingCartItems_Users1` FOREIGN KEY (`Users_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `onsaleitems`
--

LOCK TABLES `onsaleitems` WRITE;
/*!40000 ALTER TABLE `onsaleitems` DISABLE KEYS */;
/*!40000 ALTER TABLE `onsaleitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderitems`
--

DROP TABLE IF EXISTS `orderitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderitems` (
  `quantity` int(11) NOT NULL,
  `Items_id` int(11) NOT NULL,
  `Orders_id` int(11) NOT NULL,
  `Seller_id` int(11) NOT NULL,
  `Users_id` int(11) NOT NULL,
  KEY `fk_OrderItems_Items1_idx` (`Items_id`),
  KEY `fk_OrderItems_Order_id1_idx` (`Orders_id`),
  KEY `fk_OrderItems_Users1_idx` (`Users_id`),
  CONSTRAINT `fk_OrderItems_Items1` FOREIGN KEY (`Items_id`) REFERENCES `items` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderItems_Order_id1` FOREIGN KEY (`Orders_id`) REFERENCES `orders` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderItems_Users1` FOREIGN KEY (`Users_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitems`
--

LOCK TABLES `orderitems` WRITE;
/*!40000 ALTER TABLE `orderitems` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Users_id` int(11) NOT NULL,
  `mdate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Users_has_Items_Users2_idx` (`Users_id`),
  CONSTRAINT `fk_Users_has_Items_Users2` FOREIGN KEY (`Users_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `removedscitems`
--

DROP TABLE IF EXISTS `removedscitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `removedscitems` (
  `adate` datetime NOT NULL,
  `Items_id` int(11) NOT NULL,
  `Users_id` int(11) NOT NULL,
  `quantitiy` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rdate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_RemovedSCItems_Users1_idx` (`Users_id`),
  CONSTRAINT `fk_RemovedSCItems_Users1` FOREIGN KEY (`Users_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `removedscitems`
--

LOCK TABLES `removedscitems` WRITE;
/*!40000 ALTER TABLE `removedscitems` DISABLE KEYS */;
/*!40000 ALTER TABLE `removedscitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppingcartitems`
--

DROP TABLE IF EXISTS `shoppingcartitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shoppingcartitems` (
  `Items_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `Users_id` int(11) NOT NULL,
  `mdate` datetime DEFAULT NULL,
  `OnSaleItems_id` int(11) DEFAULT NULL,
  KEY `fk_ShoppingCartItems_Items1_idx` (`Items_id`),
  KEY `fk_ShoppingCartItems_Users1_idx` (`Users_id`),
  CONSTRAINT `fk_ShoppingCartItems_Items10` FOREIGN KEY (`Items_id`) REFERENCES `items` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ShoppingCartItems_Users10` FOREIGN KEY (`Users_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppingcartitems`
--

LOCK TABLES `shoppingcartitems` WRITE;
/*!40000 ALTER TABLE `shoppingcartitems` DISABLE KEYS */;
/*!40000 ALTER TABLE `shoppingcartitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(145) NOT NULL,
  `yearOfBirth` int(11) DEFAULT NULL,
  `address` varchar(245) DEFAULT NULL,
  `creditcard` varchar(45) DEFAULT NULL,
  `hash` varchar(45) NOT NULL,
  `isVerified` tinyint(4) NOT NULL DEFAULT '1',
  `isBanned` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`,`password`),
  UNIQUE KEY `hash_UNIQUE` (`hash`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-29 14:46:37
