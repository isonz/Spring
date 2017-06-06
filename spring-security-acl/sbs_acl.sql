/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50505
 Source Host           : localhost
 Source Database       : sbs_acl

 Target Server Type    : MySQL
 Target Server Version : 50505
 File Encoding         : utf-8

 Date: 06/05/2017 18:43:14 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `acl_class`
-- ----------------------------
DROP TABLE IF EXISTS `acl_class`;
CREATE TABLE `acl_class` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CLASS` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNIQUE_UK_2` (`CLASS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `acl_entry`
-- ----------------------------
DROP TABLE IF EXISTS `acl_entry`;
CREATE TABLE `acl_entry` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACL_OBJECT_IDENTITY` bigint(20) NOT NULL,
  `ACE_ORDER` int(11) NOT NULL,
  `SID` bigint(20) NOT NULL,
  `MASK` int(11) NOT NULL,
  `GRANTING` tinyint(1) NOT NULL,
  `AUDIT_SUCCESS` tinyint(1) NOT NULL,
  `AUDIT_FAILURE` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNIQUE_UK_4` (`ACL_OBJECT_IDENTITY`,`ACE_ORDER`),
  KEY `SID` (`SID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `acl_object_identity`
-- ----------------------------
DROP TABLE IF EXISTS `acl_object_identity`;
CREATE TABLE `acl_object_identity` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `OBJECT_ID_CLASS` bigint(20) NOT NULL,
  `OBJECT_ID_IDENTITY` bigint(20) NOT NULL,
  `PARENT_OBJECT` bigint(20) DEFAULT NULL,
  `OWNER_SID` bigint(20) DEFAULT NULL,
  `ENTRIES_INHERITING` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNIQUE_UK_3` (`OBJECT_ID_CLASS`,`OBJECT_ID_IDENTITY`),
  KEY `OWNER_SID` (`OWNER_SID`),
  KEY `PARENT_OBJECT` (`PARENT_OBJECT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `acl_sid`
-- ----------------------------
DROP TABLE IF EXISTS `acl_sid`;
CREATE TABLE `acl_sid` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PRINCIPAL` tinyint(1) NOT NULL,
  `SID` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNIQUE_UK_1` (`PRINCIPAL`,`SID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
