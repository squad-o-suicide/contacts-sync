/*
Navicat MySQL Data Transfer

Source Server         : 本地测试
Source Server Version : 50724
Source Host           : 192.168.3.10:3306
Source Database       : contacts-sync

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2018-12-07 19:05:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avatar` text,
  `last_name` varchar(10) DEFAULT NULL,
  `first_name` varchar(10) DEFAULT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
