CREATE DATABASE  IF NOT EXISTS `propco` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `propco`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: propco
-- ------------------------------------------------------
-- Server version	5.1.50-community

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `activityID` int(11) NOT NULL,
  `serviceRequestID` int(11) NOT NULL,
  `location` varchar(100) DEFAULT NULL,
  `notes` mediumtext,
  `status` varchar(45) DEFAULT NULL,
  `creationDateTime` datetime DEFAULT NULL,
  `updateDateTime` datetime DEFAULT NULL,
  `updatedByUser` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`activityID`),
  KEY `serviceRequestFK_idx` (`serviceRequestID`),
  CONSTRAINT `serviceRequestFK` FOREIGN KEY (`serviceRequestID`) REFERENCES `service_request` (`serviceRequestID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billing`
--

DROP TABLE IF EXISTS `billing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `billing` (
  `billingID` int(11) NOT NULL,
  `billingName` varchar(50) NOT NULL,
  `billingAlias` varchar(50) DEFAULT NULL,
  `billingAddress` varchar(60) NOT NULL,
  `billingCity` varchar(30) NOT NULL,
  `billingProvince` varchar(2) NOT NULL,
  `billingPostalCode` varchar(7) NOT NULL,
  `billingContactName` varchar(20) NOT NULL,
  `billingPrimaryPhone` varchar(12) NOT NULL,
  `billingExt` varchar(5) NOT NULL,
  `billingSecondaryPhone` varchar(12) DEFAULT NULL,
  `billingExt2` varchar(5) DEFAULT NULL,
  `billingFax` varchar(12) DEFAULT NULL,
  `billingEmailAddress` varchar(30) DEFAULT NULL,
  `tax` varchar(3) DEFAULT NULL,
  `taxRate` varchar(5) DEFAULT NULL,
  `creationDate` date NOT NULL,
  `updateDate` date NOT NULL,
  `updatedByUser` tinyint(4) NOT NULL,
  PRIMARY KEY (`billingID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billing`
--

LOCK TABLES `billing` WRITE;
/*!40000 ALTER TABLE `billing` DISABLE KEYS */;
/*!40000 ALTER TABLE `billing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bundle`
--

DROP TABLE IF EXISTS `bundle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bundle` (
  `bundleID` int(11) NOT NULL,
  `bundleName` varchar(30) NOT NULL,
  PRIMARY KEY (`bundleID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bundle`
--

LOCK TABLES `bundle` WRITE;
/*!40000 ALTER TABLE `bundle` DISABLE KEYS */;
/*!40000 ALTER TABLE `bundle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customerID` int(11) NOT NULL,
  `bundleID` int(11) NOT NULL,
  `billingID` int(11) NOT NULL,
  `customerName` varchar(50) NOT NULL,
  `notes` text,
  `address` varchar(50) NOT NULL,
  `addressNotes` varchar(30) DEFAULT NULL,
  `city` varchar(30) NOT NULL,
  `province` varchar(2) NOT NULL,
  `postalCode` varchar(7) NOT NULL,
  `contactName` varchar(20) NOT NULL,
  `primaryPhone` varchar(12) NOT NULL,
  `ext` varchar(5) NOT NULL,
  `secondaryPhone` varchar(12) DEFAULT NULL,
  `ext2` varchar(5) DEFAULT NULL,
  `other` varchar(30) DEFAULT NULL,
  `fax` varchar(12) DEFAULT NULL,
  `emailAddress` varchar(30) DEFAULT NULL,
  `creationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updatedByUser` tinyint(4) NOT NULL,
  PRIMARY KEY (`customerID`),
  KEY `CustomerBundleFK_idx` (`bundleID`),
  KEY `CustomerBillingFK_idx` (`billingID`),
  CONSTRAINT `CustomerBillingFK` FOREIGN KEY (`billingID`) REFERENCES `billing` (`billingID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `CustomerBundleFK` FOREIGN KEY (`bundleID`) REFERENCES `bundle` (`bundleID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `departmentID` int(11) NOT NULL,
  `billingID` int(11) NOT NULL,
  `departmentName` varchar(50) NOT NULL,
  `contractNr` varchar(15) NOT NULL,
  `creationDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `updatedByUser` tinyint(4) NOT NULL,
  PRIMARY KEY (`departmentID`),
  KEY `DepartmentBilling_idx` (`billingID`),
  CONSTRAINT `DepartmentBilling` FOREIGN KEY (`billingID`) REFERENCES `billing` (`billingID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_request`
--

DROP TABLE IF EXISTS `service_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service_request` (
  `serviceRequestID` int(11) NOT NULL,
  `customerID` int(11) NOT NULL,
  `purchaseOrderNumber` varchar(45) DEFAULT NULL,
  `invoiceFrequency` varchar(45) NOT NULL,
  `serviceType` varchar(45) NOT NULL,
  `paymentType` varchar(45) NOT NULL,
  `schedule` varchar(45) NOT NULL,
  `serviceDate` date NOT NULL,
  `creationDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `updatedByUser` varchar(45) NOT NULL,
  PRIMARY KEY (`serviceRequestID`),
  KEY `ServiceRequestCustomerFK_idx` (`customerID`),
  CONSTRAINT `ServiceRequestCustomerFK` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_request`
--

LOCK TABLES `service_request` WRITE;
/*!40000 ALTER TABLE `service_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `service_request` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-17 18:28:13
