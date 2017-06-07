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

 Date: 06/07/2017 21:58:00 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `acl_class`
-- ----------------------------
DROP TABLE IF EXISTS `acl_class`;
CREATE TABLE `acl_class` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CLASS` varchar(100) NOT NULL COMMENT 'The fully qualified name of the domain object',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNIQUE_UK_2` (`CLASS`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `acl_class`
-- ----------------------------
BEGIN;
INSERT INTO `acl_class` VALUES ('1', 'org.krams.tutorial.domain.AdminPost'), ('2', 'org.krams.tutorial.domain.PersonalPost'), ('3', 'org.krams.tutorial.domain.PublicPost');
COMMIT;

-- ----------------------------
--  Table structure for `acl_entry`
-- ----------------------------
DROP TABLE IF EXISTS `acl_entry`;
CREATE TABLE `acl_entry` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACL_OBJECT_IDENTITY` bigint(20) NOT NULL COMMENT 'Refers to the id field in the acl_object_identity table',
  `ACE_ORDER` int(11) NOT NULL COMMENT 'Refers to the ordering of the access control entries',
  `SID` bigint(20) NOT NULL COMMENT 'Refers to the id field in the acl_sid table',
  `MASK` int(11) NOT NULL COMMENT 'A bitwise mask to indicate the permissions. A value of 1 is equivalent to READ permission, 2 for WRITE, and so forth.',
  `GRANTING` tinyint(1) NOT NULL COMMENT 'A flag to indicate whether the mask should be interpreted as granting access or deny access',
  `AUDIT_SUCCESS` tinyint(1) NOT NULL COMMENT 'A flag to indicate whether to audit a successful permission',
  `AUDIT_FAILURE` tinyint(1) NOT NULL COMMENT 'A flag to indicate whether to audit a failed permissi',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNIQUE_UK_4` (`ACL_OBJECT_IDENTITY`,`ACE_ORDER`),
  KEY `SID` (`SID`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `acl_entry`
-- ----------------------------
BEGIN;
INSERT INTO `acl_entry` VALUES ('1', '1', '1', '1', '1', '1', '1', '1'), ('2', '2', '1', '1', '1', '1', '1', '1'), ('3', '3', '1', '1', '1', '1', '1', '1'), ('4', '1', '2', '1', '2', '1', '1', '1'), ('5', '2', '2', '1', '2', '1', '1', '1'), ('6', '3', '2', '1', '2', '1', '1', '1'), ('7', '4', '1', '1', '1', '1', '1', '1'), ('8', '5', '1', '1', '1', '1', '1', '1'), ('9', '6', '1', '1', '1', '1', '1', '1'), ('10', '7', '1', '1', '1', '1', '1', '1'), ('11', '8', '1', '1', '1', '1', '1', '1'), ('12', '9', '1', '1', '1', '1', '1', '1'), ('13', '7', '2', '1', '2', '1', '1', '1'), ('14', '8', '2', '1', '2', '1', '1', '1'), ('15', '9', '2', '1', '2', '1', '1', '1'), ('28', '4', '3', '2', '1', '1', '1', '1'), ('29', '5', '3', '2', '1', '1', '1', '1'), ('30', '6', '3', '2', '1', '1', '1', '1'), ('31', '4', '4', '2', '2', '1', '1', '1'), ('32', '5', '4', '2', '2', '1', '1', '1'), ('33', '6', '4', '2', '2', '1', '1', '1'), ('34', '7', '3', '2', '1', '1', '1', '1'), ('35', '8', '3', '2', '1', '1', '1', '1'), ('36', '9', '3', '2', '1', '1', '1', '1'), ('37', '7', '4', '2', '2', '1', '1', '1'), ('38', '8', '4', '2', '2', '1', '1', '1'), ('39', '9', '4', '2', '2', '1', '1', '1'), ('40', '7', '5', '3', '1', '1', '1', '1'), ('41', '8', '5', '3', '1', '1', '1', '1'), ('42', '9', '5', '3', '1', '1', '1', '1');
COMMIT;

-- ----------------------------
--  Table structure for `acl_object_identity`
-- ----------------------------
DROP TABLE IF EXISTS `acl_object_identity`;
CREATE TABLE `acl_object_identity` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `OBJECT_ID_CLASS` bigint(20) NOT NULL COMMENT 'Refers to the id field in the acl_class. This is a reference to the fully qualified name of the class',
  `OBJECT_ID_IDENTITY` bigint(20) NOT NULL COMMENT 'Refers to the primary id of the domain object. The id is assigned from another database: the Bulletin database (See the Bulletin Database below). Every domain object in the application needs to have a unique id.',
  `PARENT_OBJECT` bigint(20) DEFAULT NULL COMMENT 'Refers to the id of the parent object if existing',
  `OWNER_SID` bigint(20) DEFAULT NULL COMMENT 'Refers to the id field in the acl_sid. This is a reference to the username or role',
  `ENTRIES_INHERITING` tinyint(1) NOT NULL COMMENT 'A flag to indicate whether the object has inherited entries',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNIQUE_UK_3` (`OBJECT_ID_CLASS`,`OBJECT_ID_IDENTITY`),
  KEY `OWNER_SID` (`OWNER_SID`),
  KEY `PARENT_OBJECT` (`PARENT_OBJECT`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `acl_object_identity`
-- ----------------------------
BEGIN;
INSERT INTO `acl_object_identity` VALUES ('1', '1', '1', null, '1', '0'), ('2', '1', '2', null, '1', '0'), ('3', '1', '3', null, '1', '0'), ('4', '2', '1', null, '1', '0'), ('5', '2', '2', null, '1', '0'), ('6', '2', '3', null, '1', '0'), ('7', '3', '1', null, '1', '0'), ('8', '3', '2', null, '1', '0'), ('9', '3', '3', null, '1', '0');
COMMIT;

-- ----------------------------
--  Table structure for `acl_sid`
-- ----------------------------
DROP TABLE IF EXISTS `acl_sid`;
CREATE TABLE `acl_sid` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PRINCIPAL` tinyint(1) NOT NULL COMMENT 'A flag to indicate if the sid field is a username or a role',
  `SID` varchar(100) NOT NULL COMMENT 'The actual username (ie. john) or role (ie. ROLE_ADMIN)',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNIQUE_UK_1` (`PRINCIPAL`,`SID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `acl_sid`
-- ----------------------------
BEGIN;
INSERT INTO `acl_sid` VALUES ('2', '1', 'jane'), ('1', '1', 'john'), ('3', '1', 'mike');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
