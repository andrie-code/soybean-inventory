-- MySQL dump 10.13  Distrib 5.7.14, for Win64 (x86_64)
--
-- Host: localhost    Database: prototype
-- ------------------------------------------------------
-- Server version	5.7.14

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
-- Table structure for table `billings`
--

DROP TABLE IF EXISTS `billings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `billings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tanggal` datetime DEFAULT NULL,
  `invoice` varchar(15) DEFAULT NULL,
  `perusahaan` varchar(15) DEFAULT NULL,
  `produk` varchar(15) DEFAULT NULL,
  `tonase` int(10) DEFAULT NULL,
  `harga` decimal(10,2) DEFAULT NULL,
  `total` decimal(15,2) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `pembayaran` decimal(15,2) DEFAULT NULL,
  `tgl` datetime DEFAULT NULL,
  `balance` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billings`
--

LOCK TABLES `billings` WRITE;
/*!40000 ALTER TABLE `billings` DISABLE KEYS */;
/*!40000 ALTER TABLE `billings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giro`
--

DROP TABLE IF EXISTS `giro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `giro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `receive_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `company_from_name` varchar(50) NOT NULL,
  `account_to_number` varchar(40) NOT NULL,
  `giro_number` varchar(15) NOT NULL,
  `deposit_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `amount` decimal(9,2) NOT NULL,
  `signed_off` int(11) NOT NULL,
  `receipt_received` int(11) NOT NULL,
  `completed` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giro`
--

LOCK TABLES `giro` WRITE;
/*!40000 ALTER TABLE `giro` DISABLE KEYS */;
/*!40000 ALTER TABLE `giro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giro_list`
--

DROP TABLE IF EXISTS `giro_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `giro_list` (
  `id` int(11) NOT NULL,
  `tanggal` datetime DEFAULT NULL,
  `no_giro` varchar(15) DEFAULT NULL,
  `bank` varchar(15) DEFAULT NULL,
  `perusahaan` varchar(15) DEFAULT NULL,
  `jumlah` decimal(11,2) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `jatuh_tempo` datetime DEFAULT NULL,
  `tgl_cair` datetime DEFAULT NULL,
  `jumlah_Cair` decimal(11,2) DEFAULT NULL,
  `balance` decimal(11,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giro_list`
--

LOCK TABLES `giro_list` WRITE;
/*!40000 ALTER TABLE `giro_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `giro_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_list`
--

DROP TABLE IF EXISTS `sales_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales_list` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `jenis_sales` varchar(15) DEFAULT NULL,
  `barang` varchar(30) DEFAULT NULL,
  `invoice` int(11) DEFAULT NULL,
  `customer` varchar(30) DEFAULT NULL,
  `harga` decimal(11,2) DEFAULT NULL,
  `tonase` int(11) DEFAULT NULL,
  `total` decimal(15,2) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `bank` varchar(15) DEFAULT NULL,
  `pembayaran` decimal(15,2) DEFAULT NULL,
  `tgl_bayar` datetime DEFAULT NULL,
  `tgl_kirim` datetime DEFAULT NULL,
  `truk` varchar(30) DEFAULT NULL,
  `balance` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_list`
--

LOCK TABLES `sales_list` WRITE;
/*!40000 ALTER TABLE `sales_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `soybean`
--

DROP TABLE IF EXISTS `soybean`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `soybean` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `serial_number` int(8) NOT NULL,
  `name` varchar(30) NOT NULL,
  `price` double DEFAULT NULL,
  `kgs` double DEFAULT NULL,
  `checked` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `soybean`
--

LOCK TABLES `soybean` WRITE;
/*!40000 ALTER TABLE `soybean` DISABLE KEYS */;
INSERT INTO `soybean` VALUES (1,1234,'classic',25,1000000,NULL),(2,1235,'red',30,4500000,NULL),(3,1245,'imported',40,5700000,NULL),(4,1256,'pure',20,8700000,NULL),(5,1300,'mixed',50,12000000,NULL),(12,1100,'B name',450,30,'\0'),(13,1,'1',1,1,'\0'),(14,2,'2',2,2,'\0'),(15,3,'3',3,3,'\0'),(17,30000,'Alvin',90,120,'\0'),(18,4,'4',4,4,'\0'),(19,9,'9',9,9,'\0'),(21,12,'12',12,12,'\0'),(22,1000,'Andrie',5555,12,'\0'),(23,4,'4',4,4,'\0'),(24,45,'45',45,45,'\0'),(25,1,'1',1,1,'\0');
/*!40000 ALTER TABLE `soybean` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','password');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-19 13:59:17
