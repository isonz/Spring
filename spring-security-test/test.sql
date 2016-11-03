/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2016-11-03 18:41:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `passwd` varchar(12) NOT NULL DEFAULT '' COMMENT '用户密码',
  `nickname` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名字',
  `phoneno` varchar(32) NOT NULL DEFAULT '' COMMENT '电话号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'admin', 'admin', '');
INSERT INTO `t_admin` VALUES ('4', '123456', 'test', '');
INSERT INTO `t_admin` VALUES ('5', '111111', '111111', '');

-- ----------------------------
-- Table structure for t_group
-- ----------------------------
DROP TABLE IF EXISTS `t_group`;
CREATE TABLE `t_group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `groupname` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_group
-- ----------------------------
INSERT INTO `t_group` VALUES ('1', 'Administrator');
INSERT INTO `t_group` VALUES ('2', '影片维护');

-- ----------------------------
-- Table structure for t_group_role
-- ----------------------------
DROP TABLE IF EXISTS `t_group_role`;
CREATE TABLE `t_group_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `groupid` bigint(20) unsigned NOT NULL,
  `roleid` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `groupid2` (`groupid`,`roleid`),
  KEY `roleid` (`roleid`),
  CONSTRAINT `t_group_role_ibfk_1` FOREIGN KEY (`groupid`) REFERENCES `t_group` (`id`),
  CONSTRAINT `t_group_role_ibfk_2` FOREIGN KEY (`roleid`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_group_role
-- ----------------------------
INSERT INTO `t_group_role` VALUES ('1', '1', '1');
INSERT INTO `t_group_role` VALUES ('2', '2', '2');
INSERT INTO `t_group_role` VALUES ('4', '2', '4');

-- ----------------------------
-- Table structure for t_group_user
-- ----------------------------
DROP TABLE IF EXISTS `t_group_user`;
CREATE TABLE `t_group_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) unsigned NOT NULL,
  `groupid` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  KEY `groupid` (`groupid`),
  CONSTRAINT `t_group_user_ibfk_2` FOREIGN KEY (`groupid`) REFERENCES `t_group` (`id`),
  CONSTRAINT `t_group_user_ibfk_3` FOREIGN KEY (`userid`) REFERENCES `t_admin` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_group_user
-- ----------------------------
INSERT INTO `t_group_user` VALUES ('1', '1', '1');
INSERT INTO `t_group_user` VALUES ('2', '4', '2');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `role` varchar(40) NOT NULL DEFAULT '',
  `descpt` varchar(40) NOT NULL DEFAULT '' COMMENT '角色描述',
  `category` varchar(40) NOT NULL DEFAULT '' COMMENT '分类',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'ROLE_ADMIN', '系统管理员', '系统管理员');
INSERT INTO `t_role` VALUES ('2', 'ROLE_UPDATE_FILM', '修改', '影片管理');
INSERT INTO `t_role` VALUES ('3', 'ROLE_DELETE_FILM', '删除', '影片管理');
INSERT INTO `t_role` VALUES ('4', 'ROLE_ADD_FILM', '添加', '影片管理');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) unsigned NOT NULL,
  `roleid` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  KEY `roleid` (`roleid`),
  CONSTRAINT `t_user_role_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `t_admin` (`id`),
  CONSTRAINT `t_user_role_ibfk_2` FOREIGN KEY (`roleid`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
