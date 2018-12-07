/*
Navicat MySQL Data Transfer

Source Server         : 本地测试
Source Server Version : 50724
Source Host           : 192.168.3.10:3306
Source Database       : contacts-sync

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2018-12-07 19:05:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for other
-- ----------------------------
DROP TABLE IF EXISTS `other`;
CREATE TABLE `other` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  `cid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
