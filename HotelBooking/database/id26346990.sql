-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: id26346990
-- ------------------------------------------------------
-- Server version	5.6.21-log

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

create database id26346990;
use id26346990;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `cityid` varchar(255) NOT NULL,
  `cityname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cityid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES ('0001','Melbourne'),('0002','Sydney'),('0003','Perth');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel` (
  `hotelid` varchar(255) NOT NULL,
  `cityid` varchar(255) DEFAULT NULL,
  `hotelname` varchar(255) DEFAULT NULL,
  `priceA` decimal(10,2) DEFAULT '0.00',
  `priceB` decimal(10,2) DEFAULT '0.00',
  `priceC` decimal(10,2) DEFAULT '0.00',
  `port` int(11) DEFAULT NULL,
  PRIMARY KEY (`hotelid`),
  KEY `cityid_idx` (`cityid`),
  CONSTRAINT `cityid` FOREIGN KEY (`cityid`) REFERENCES `city` (`cityid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES ('0001','0001','Hilton',289.99,333.00,754.00,18892),('0002','0002','Chevron',115.00,222.00,785.00,18893),('0003','0003','Regent',118.00,222.00,785.00,18894),('0004','0001','Chevron',112.24,222.00,666.00,18895),('0005','0001','Regent',121.50,212.00,785.00,18896);
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `orderid` varchar(255) NOT NULL,
  `userid` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `cityid` varchar(255) DEFAULT NULL,
  `cityname` varchar(255) DEFAULT NULL,
  `hotelid` varchar(255) DEFAULT NULL,
  `hotelname` varchar(255) DEFAULT NULL,
  `roomid` varchar(255) DEFAULT NULL,
  `indate` varchar(2) DEFAULT NULL,
  `outdate` varchar(2) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `card` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`orderid`),
  KEY `userid_idx` (`userid`),
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES ('101031011427357620945','10103101','bai','0002','Sydney','0002','Chevron','0001','1','5','89898989','78954612'),('101031011427365255121','10103101','baijun','0003','Perth','0003','Regent','0003','1','3','18662479896','1234567890123456'),('101031011427365628311','10103101','dabai','0002','Sydney','0002','Chevron','0002','12','25','18920700952','500381199012021639'),('101031011427365841533','10103101','baijun','0003','Perth','0003','Regent','0001','25','29','15024121202','101210121'),('101031011427715822220','10103101','bb','0001','Melbourne','0001','Hilton','0002','12','13','123','123'),('101031011427724400356','10103101','baibai','0001','Melbourne','0001','Hilton','0009','1','2','3','4'),('101031011427780647812','10103101','nn','0002','Sydney','0002','Chevron','0003','1','2','3','4'),('1231427799593815','123','bbb','0001','Melbourne','0001','Hilton','0006','12','13','123','123'),('1234561427782297767','123456','baibai','0001','Melbourne','0001','Hilton','0005','1','3','18662479896','123456789'),('1234567891427807899212','123456789','a','0001','Melbourne','0001','Hilton','0001','a','a','a','a'),('263469901427718723565','26346990','bb','0003','Perth','0003','Regent','0003','1','3','111','111');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `hotelid` varchar(255) NOT NULL,
  `roomid` varchar(255) NOT NULL,
  `type` varchar(2) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`roomid`,`hotelid`),
  KEY `hotelid` (`hotelid`),
  CONSTRAINT `hotelid` FOREIGN KEY (`hotelid`) REFERENCES `hotel` (`hotelid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES ('0001','0001','A','0'),('0002','0001','A','1'),('0003','0001','A','1'),('0004','0001','A','1'),('0005','0001','A','1'),('0001','0002','B','0'),('0002','0002','B','1'),('0003','0002','B','1'),('0004','0002','A','1'),('0005','0002','A','1'),('0001','0003','C','1'),('0002','0003','C','0'),('0003','0003','C','0'),('0004','0003','A','1'),('0005','0003','A','1'),('0001','0004','A','1'),('0002','0004','A','1'),('0003','0004','A','1'),('0004','0004','A','1'),('0005','0004','A','1'),('0001','0005','B','0'),('0002','0005','A','1'),('0003','0005','A','1'),('0004','0005','B','1'),('0005','0005','B','1'),('0001','0006','C','0'),('0002','0006','A','1'),('0003','0006','A','1'),('0004','0006','B','1'),('0005','0006','B','1'),('0001','0007','A','1'),('0002','0007','A','1'),('0003','0007','A','1'),('0004','0007','B','1'),('0005','0007','B','1'),('0001','0008','B','1'),('0002','0008','A','1'),('0003','0008','A','1'),('0004','0008','B','1'),('0005','0008','B','1'),('0001','0009','C','0'),('0002','0009','A','1'),('0003','0009','B','1'),('0004','0009','C','1'),('0005','0009','C','1'),('0001','0010','A','1'),('0002','0010','B','1'),('0003','0010','B','1'),('0004','0010','C','1'),('0005','0010','C','1'),('0001','0011','B','1'),('0002','0011','B','1'),('0003','0011','B','1'),('0004','0011','C','1'),('0005','0011','C','1'),('0001','0012','C','1'),('0002','0012','B','1'),('0003','0012','B','1'),('0004','0012','C','1'),('0005','0012','C','1'),('0001','0013','A','1'),('0002','0013','B','1'),('0003','0013','B','1'),('0005','0013','A','1'),('0001','0014','B','1'),('0002','0014','B','1'),('0003','0014','C','1'),('0005','0014','B','1'),('0001','0015','C','1'),('0002','0015','B','1'),('0003','0015','C','1'),('0005','0015','C','1'),('0001','0016','A','1'),('0002','0016','C','1'),('0003','0016','C','1'),('0001','0017','B','1'),('0002','0017','C','1'),('0003','0017','C','1'),('0001','0018','C','1'),('0002','0018','C','1'),('0003','0018','C','1'),('0001','0019','A','1'),('0002','0019','C','1'),('0001','0020','B','1'),('0002','0020','C','1'),('0001','0021','C','1'),('0002','0021','C','1'),('0001','0022','A','1'),('0001','0023','B','1'),('0001','0024','C','1'),('0001','0025','A','1'),('0001','0026','B','1'),('0001','0027','C','1'),('0001','0028','A','1'),('0001','0029','B','1'),('0001','0030','C','1');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userid` varchar(255) NOT NULL,
  `psw` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('10103101','121122'),('10103102','121122'),('111','111'),('111111','111111'),('11111111','11111111'),('121122','121122'),('123','123'),('12345','12345'),('123456','123456'),('123456789','123456789'),('220143223','121122'),('26346990','121122'),('514331629','121122'),('785554157','121122'),('fit5183a1','121122');
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

-- Dump completed on 2015-04-02 22:12:05
