/*
Navicat MySQL Data Transfer

Source Server         : aos
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : poolman

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2017-06-07 14:37:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for business
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `position` varchar(100) DEFAULT NULL,
  `status` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='店名';

-- ----------------------------
-- Records of business
-- ----------------------------
INSERT INTO `business` VALUES ('1', '贝克汉堡', '15047910024', '15047910024@163.com', '第一食堂三楼', '1');
INSERT INTO `business` VALUES ('2', '旋转小火锅', '18647112942', '18647112942@163.com', '第一食堂二楼', '1');
INSERT INTO `business` VALUES ('3', '黄焖鸡米饭', '18647139248', '18647139248@163.com', '第一食堂三楼', '1');

-- ----------------------------
-- Table structure for dish
-- ----------------------------
DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `dishName` varchar(100) DEFAULT NULL,
  `flag_r` varchar(2) DEFAULT NULL,
  `flag_h` varchar(2) DEFAULT NULL,
  `type` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dish
-- ----------------------------
INSERT INTO `dish` VALUES ('1', '水煮肉片', '2', '2', '0');
INSERT INTO `dish` VALUES ('2', '宫保鸡丁', '2', '1', '1');
INSERT INTO `dish` VALUES ('3', '家常小炒肉', '2', '1', '1');
INSERT INTO `dish` VALUES ('4', '干煸菜花', '0', '1', '1');
INSERT INTO `dish` VALUES ('5', '青椒鸡蛋', '0', '1', '0');
INSERT INTO `dish` VALUES ('6', '土豆片', '0', '0', '0');
INSERT INTO `dish` VALUES ('7', '酱爆肉丁', '2', '1', '0');
INSERT INTO `dish` VALUES ('8', '麻辣香锅', '1', '2', '0');
INSERT INTO `dish` VALUES ('9', '红烧肉', '2', '2', '0');
INSERT INTO `dish` VALUES ('10', '酸辣白菜', '0', '2', '0');
INSERT INTO `dish` VALUES ('11', '蒜苔肉末', '1', '1', '0');
INSERT INTO `dish` VALUES ('12', '青椒胡萝卜丝炒肉', '1', '1', '0');
INSERT INTO `dish` VALUES ('13', '茄子豆角', '0', '0', '0');
INSERT INTO `dish` VALUES ('14', '木耳炒肉', '1', '1', '0');
INSERT INTO `dish` VALUES ('15', '麻婆豆腐', '0', '2', '0');
INSERT INTO `dish` VALUES ('16', '肉炒腐竹', '1', '0', '0');
INSERT INTO `dish` VALUES ('17', '新疆大盘鸡', '2', '2', '0');
INSERT INTO `dish` VALUES ('18', '过油肉土豆片', '2', '1', '0');
INSERT INTO `dish` VALUES ('19', '干煸豆角', '0', '1', '0');
INSERT INTO `dish` VALUES ('20', '尖椒土豆片', '0', '2', '0');
INSERT INTO `dish` VALUES ('21', '蛋炒饭', '0', '0', '0');
INSERT INTO `dish` VALUES ('22', '虎皮尖椒', '0', '2', '0');
INSERT INTO `dish` VALUES ('23', '蒜蓉油麦菜', '0', '1', '0');
INSERT INTO `dish` VALUES ('24', '黄焖鸡', '2', '0', '0');
INSERT INTO `dish` VALUES ('25', '黄瓜鸡丁', '1', '0', '0');
INSERT INTO `dish` VALUES ('26', '锅包肉', '2', '0', '0');
INSERT INTO `dish` VALUES ('27', '京酱肉丝', '2', '0', '1');
INSERT INTO `dish` VALUES ('28', '鱼香肉丝', '2', '0', '0');
INSERT INTO `dish` VALUES ('29', '红烧豆腐', '0', '0', '0');
INSERT INTO `dish` VALUES ('30', '糖醋排骨', '2', '0', '0');
INSERT INTO `dish` VALUES ('31', '鱼香茄条', '1', '1', '0');
INSERT INTO `dish` VALUES ('32', '糖醋里脊', '1', '0', '0');
INSERT INTO `dish` VALUES ('33', '红烧狮子头', '2', '0', '0');
INSERT INTO `dish` VALUES ('34', '地三鲜', '2', '1', '0');
INSERT INTO `dish` VALUES ('35', '香菇鸡块', '2', '1', '0');
INSERT INTO `dish` VALUES ('36', '糖醋鲤鱼', '2', '1', '0');
INSERT INTO `dish` VALUES ('37', '地三鲜', '2', '1', '0');
INSERT INTO `dish` VALUES ('38', '香菇鸡块', '2', '1', '0');
INSERT INTO `dish` VALUES ('39', '糖醋鲤鱼', '2', '1', '0');
INSERT INTO `dish` VALUES ('40', '地三鲜', '2', '1', '0');
INSERT INTO `dish` VALUES ('41', '香菇鸡块', '2', '1', '0');
INSERT INTO `dish` VALUES ('42', '糖醋鲤鱼', '2', '1', '0');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(40) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `content` text,
  `favorite` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '内蒙古财经大学菜单1', null, null, null, null);

-- ----------------------------
-- Table structure for sales
-- ----------------------------
DROP TABLE IF EXISTS `sales`;
CREATE TABLE `sales` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dishId` int(20) DEFAULT NULL,
  `businessId` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sales
-- ----------------------------

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuId` varchar(255) DEFAULT NULL,
  `schoolName` varchar(255) DEFAULT NULL,
  `schoolOpition` varchar(255) DEFAULT NULL,
  `creater` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES ('1', '1', null, null, null, null);

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` int(80) NOT NULL AUTO_INCREMENT,
  `userName` varchar(30) DEFAULT NULL,
  `passWord` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `isDel` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('1', 'admin', 'admin', 'admin', '18647841943@163.com', '1');

-- ----------------------------
-- Table structure for user_
-- ----------------------------
DROP TABLE IF EXISTS `user_`;
CREATE TABLE `user_` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `userName` varchar(100) DEFAULT NULL,
  `passWord` varchar(255) DEFAULT NULL,
  `gender` varchar(8) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `height` double(10,1) DEFAULT NULL,
  `weight` double(10,1) DEFAULT NULL,
  `weiteng` int(2) DEFAULT NULL,
  `kouqiangky` int(2) DEFAULT NULL,
  `yayingcx` int(2) DEFAULT NULL,
  `jianfei` int(2) DEFAULT NULL,
  `status` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_
-- ----------------------------
INSERT INTO `user_` VALUES ('1', 'admin', '123456', '男', '18647841944', '180.0', '65.0', '0', '0', '0', '0', '1');
INSERT INTO `user_` VALUES ('2', 'alice', '123456', null, null, null, null, null, null, null, null, '1');
INSERT INTO `user_` VALUES ('3', 'cat', '123456', null, null, null, null, null, null, null, null, null);
INSERT INTO `user_` VALUES ('4', 'cass', '123456', null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for user_menu
-- ----------------------------
DROP TABLE IF EXISTS `user_menu`;
CREATE TABLE `user_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) DEFAULT NULL,
  `menuId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_menu
-- ----------------------------
