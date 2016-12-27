/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : myssh

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2016-11-01 00:37:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `parent_id` int(10) unsigned DEFAULT NULL COMMENT '父级ID',
  PRIMARY KEY (`id`),
  KEY `FKpo40eieof9vp6499qcs7ep9et` (`parent_id`),
  CONSTRAINT `FKpo40eieof9vp6499qcs7ep9et` FOREIGN KEY (`parent_id`) REFERENCES `dept` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('1', '电商部', null);
INSERT INTO `dept` VALUES ('2', '市场部', null);
INSERT INTO `dept` VALUES ('3', '服务部', null);
INSERT INTO `dept` VALUES ('4', '品牌部', null);
INSERT INTO `dept` VALUES ('5', '总经办', null);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `create_at` int(10) unsigned DEFAULT '0' COMMENT '时间戳',
  `date` date DEFAULT NULL,
  `days` float(10,2) DEFAULT '0.00',
  `msg` longtext,
  `name` varchar(20) NOT NULL,
  `update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ip` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('37', '1472724499', '2016-09-01', '100.01', '发生发哈市地方', '哈哈', '2016-09-01 18:08:19', '127.0.0.1');
INSERT INTO `message` VALUES ('84', '1482726919', '2016-09-01', '100.01', 'asdf', 'ytybbbbbbbbbbfffffff', '2016-09-01 18:48:39', '192.168.75.112');
INSERT INTO `message` VALUES ('90', '0', null, '0.00', '杭州起码科技有限公司杭州起码科技有限公司', '杭州起码科技有限公司', null, null);
INSERT INTO `message` VALUES ('91', '0', null, '0.00', 'uuuuurrrrffff', '杭州起码fff', null, null);
INSERT INTO `message` VALUES ('92', '0', null, '0.00', 'ff', 'uuuuu让人啊啊啊', null, null);
INSERT INTO `message` VALUES ('95', '0', null, '0.00', 'fffffffffffffffff', 'サンヤ (三亜)を体験しよう!', '2016-10-25 22:55:13', null);
INSERT INTO `message` VALUES ('99', '0', null, '0.00', 'asdfasdf', 'asdfasf', null, null);
INSERT INTO `message` VALUES ('100', '0', null, '0.00', 'fffffffffffffffffffff', 'astt', null, null);
INSERT INTO `message` VALUES ('101', '0', null, '0.00', 'dfgsdfgsdfgccccccccccc', 'bbbbcccccccccccccccc', null, null);
INSERT INTO `message` VALUES ('102', '0', null, '0.00', 'asdfasdfasdf', 'asdfaasdfasdf', null, null);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL COMMENT '角色代码',
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'dept_manger', '部门经理');
INSERT INTO `role` VALUES ('2', 'zongjingli', '总经理');
INSERT INTO `role` VALUES ('3', 'dongshizhang', '董事长');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `openid` varchar(30) DEFAULT NULL COMMENT '微信OPENID',
  `username` varchar(20) DEFAULT NULL,
  `userid` varchar(20) DEFAULT NULL COMMENT '微信企业号里的userid',
  `valid` bit(1) DEFAULT NULL COMMENT '用户是否有效T,F',
  `dept_id` int(10) unsigned DEFAULT NULL COMMENT '部门ID，外键',
  PRIMARY KEY (`id`),
  KEY `FK5rwmryny6jthaaxkogownknqp` (`dept_id`),
  CONSTRAINT `FK5rwmryny6jthaaxkogownknqp` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'openid_ison', 'username_ison', 'userid_ison', '', '1');
INSERT INTO `user` VALUES ('2', 'katie', '13632252275', 'katie', '', '1');
INSERT INTO `user` VALUES ('3', '张三丰', '张三丰', '张无忌', '', '1');
INSERT INTO `user` VALUES ('4', '张三丰', '张三丰不能重复', '张无忌', '', '3');
INSERT INTO `user` VALUES ('5', '张三丰', '张三丰还是不能重复', '张无忌', '', '4');
INSERT INTO `user` VALUES ('6', 'katie', '也是不能重复', 'katie', '', '2');
INSERT INTO `user` VALUES ('7', 'katie', '什么？不可以？', 'katie顶顶顶顶顶', '', '3');
INSERT INTO `user` VALUES ('12', 'misswu', 'misswu', 'misswu', '', '5');
INSERT INTO `user` VALUES ('14', '张三丰', 'zxskigg', 'ison', '', '1');
INSERT INTO `user` VALUES ('16', '张三丰', 'isonz@qq.com', '张无忌', '', '3');
INSERT INTO `user` VALUES ('17', '是吗', '阿山的发生', '撒旦发', '', '2');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `users_id` int(10) unsigned NOT NULL,
  `roles_id` int(10) unsigned NOT NULL,
  KEY `FKeog8p06nu33ihk13roqnrp1y6` (`roles_id`),
  KEY `FKip2yfnw7nx55paaa4i18j7moj` (`users_id`),
  CONSTRAINT `FKeog8p06nu33ihk13roqnrp1y6` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKip2yfnw7nx55paaa4i18j7moj` FOREIGN KEY (`users_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '2');
INSERT INTO `user_role` VALUES ('12', '3');
INSERT INTO `user_role` VALUES ('14', '1');
INSERT INTO `user_role` VALUES ('7', '2');
INSERT INTO `user_role` VALUES ('7', '3');
INSERT INTO `user_role` VALUES ('16', '1');
INSERT INTO `user_role` VALUES ('16', '3');
INSERT INTO `user_role` VALUES ('4', '2');
INSERT INTO `user_role` VALUES ('5', '1');
INSERT INTO `user_role` VALUES ('5', '2');
INSERT INTO `user_role` VALUES ('6', '2');
INSERT INTO `user_role` VALUES ('3', '1');
INSERT INTO `user_role` VALUES ('2', '2');
INSERT INTO `user_role` VALUES ('17', '1');

-- ----------------------------
-- Table structure for user_roles1
-- ----------------------------
DROP TABLE IF EXISTS `user_roles1`;
CREATE TABLE `user_roles1` (
  `users1_id` int(10) unsigned NOT NULL,
  `roles1_id` int(10) unsigned NOT NULL,
  KEY `FKeog8p06nu33ihk13roqnrp1y6` (`roles1_id`),
  KEY `FKip2yfnw7nx55paaa4i18j7moj` (`users1_id`),
  CONSTRAINT `user_roles1_ibfk_1` FOREIGN KEY (`roles1_id`) REFERENCES `role` (`id`),
  CONSTRAINT `user_roles1_ibfk_2` FOREIGN KEY (`users1_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_roles1
-- ----------------------------
INSERT INTO `user_roles1` VALUES ('1', '1');
