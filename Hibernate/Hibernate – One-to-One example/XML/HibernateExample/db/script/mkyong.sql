-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.36-community

--
-- Create schema mkyong
--

CREATE DATABASE IF NOT EXISTS mkyong;
USE mkyong;

--
-- Definition of table `stock`
--

DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `STOCK_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `STOCK_CODE` varchar(10) NOT NULL,
  `STOCK_NAME` varchar(20) NOT NULL,
  PRIMARY KEY (`STOCK_ID`) USING BTREE,
  UNIQUE KEY `UNI_STOCK_NAME` (`STOCK_NAME`),
  UNIQUE KEY `UNI_STOCK_ID` (`STOCK_CODE`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;


--
-- Definition of table `stock_detail`
--
DROP TABLE IF EXISTS `mkyong`.`stock_detail`;
CREATE TABLE  `mkyong`.`stock_detail` (
  `STOCK_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `COMP_NAME` varchar(100) NOT NULL,
  `COMP_DESC` varchar(255) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `LISTED_DATE` date NOT NULL,
  PRIMARY KEY (`STOCK_ID`) USING BTREE,
  CONSTRAINT `FK_STOCK_ID` FOREIGN KEY (`STOCK_ID`) REFERENCES `stock` (`STOCK_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8;

