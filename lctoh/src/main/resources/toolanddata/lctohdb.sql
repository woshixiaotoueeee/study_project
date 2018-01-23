/*
Navicat MySQL Data Transfer

Source Server         : 管理员
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : lctohdb

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-12-20 18:07:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `address_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '地址识别码ID',
  `address_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT '地址联系人',
  `address_province` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '地址省份',
  `address_city` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '地址城市',
  `address_info` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '地址详细信息',
  `address_phone` varchar(16) COLLATE utf8_unicode_ci NOT NULL COMMENT '联系方式',
  `address_latitude` decimal(14,9) NOT NULL COMMENT '纬度',
  `address_longitude` decimal(14,9) NOT NULL COMMENT '经度',
  `address_update_time` datetime NOT NULL COMMENT '最近更新时间',
  `address_state_id` int(11) NOT NULL COMMENT '地址状态码',
  `address_customer_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '所属用户',
  PRIMARY KEY (`address_id`),
  KEY `address_state` (`address_state_id`),
  KEY `address_customer` (`address_customer_id`),
  CONSTRAINT `address_customer` FOREIGN KEY (`address_customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `address_state` FOREIGN KEY (`address_state_id`) REFERENCES `state` (`state_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of address
-- ----------------------------

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '管理员识别码',
  `admin_update_time` datetime NOT NULL COMMENT '最近更新时间',
  `admin_state_id` int(11) NOT NULL COMMENT '状态码',
  `admin_user_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户关联',
  PRIMARY KEY (`admin_id`),
  KEY `admin_state` (`admin_state_id`),
  KEY `admin_user` (`admin_user_id`),
  CONSTRAINT `admin_state` FOREIGN KEY (`admin_state_id`) REFERENCES `state` (`state_id`),
  CONSTRAINT `admin_user` FOREIGN KEY (`admin_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `city_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '城市唯一识别码',
  `city_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '城市名',
  `city_update_time` datetime NOT NULL COMMENT '最近更新时间',
  `city_state_id` int(11) NOT NULL COMMENT '状态码',
  `city_province_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '所属城市ID',
  PRIMARY KEY (`city_id`),
  KEY `city_state` (`city_state_id`),
  KEY `city_province` (`city_province_id`),
  CONSTRAINT `city_province` FOREIGN KEY (`city_province_id`) REFERENCES `province` (`province_id`),
  CONSTRAINT `city_state` FOREIGN KEY (`city_state_id`) REFERENCES `state` (`state_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES ('1', '北京市', '2017-12-08 23:24:25', '50001', '10001');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `customer_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '客户为唯一识别码',
  `customer_nickname` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '昵称',
  `customer_portrait` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '头像',
  `customer_balance` double(16,3) NOT NULL COMMENT '账户余额',
  `customer_update_time` datetime NOT NULL COMMENT '最近修改时间',
  `customer_state_id` int(11) NOT NULL COMMENT '账号状态码',
  `customer_user_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '联系user表',
  PRIMARY KEY (`customer_id`,`customer_user_id`),
  KEY `customer_state` (`customer_state_id`),
  KEY `customer_user` (`customer_user_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `customer_state` FOREIGN KEY (`customer_state_id`) REFERENCES `state` (`state_id`),
  CONSTRAINT `customer_user` FOREIGN KEY (`customer_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('123456', '琴殇', 'customer/image.jpg', '100.000', '2017-12-03 17:50:10', '20002', '123456');
INSERT INTO `customer` VALUES ('12345678901234567890123456789012', '天问', 'customer/image.jpg', '100.000', '2017-12-03 17:52:43', '20004', '12345678901234567890123456789012');

-- ----------------------------
-- Table structure for dish
-- ----------------------------
DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish` (
  `dish_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '菜肴识别ID',
  `dish_name` varchar(120) COLLATE utf8_unicode_ci NOT NULL COMMENT '菜肴名字',
  `dish_price` double(10,2) NOT NULL COMMENT '价格',
  `dish_image` varchar(120) COLLATE utf8_unicode_ci NOT NULL COMMENT '菜肴图片',
  `dish_update_time` datetime NOT NULL COMMENT '最近更新时间',
  `dish_intro` text COLLATE utf8_unicode_ci NOT NULL COMMENT '菜肴简介',
  `dish_quantity` int(11) NOT NULL COMMENT '库存量',
  `dish_state_id` int(11) NOT NULL COMMENT '状态码',
  `dish_category_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '所属分类',
  PRIMARY KEY (`dish_id`),
  KEY `dish_state` (`dish_state_id`),
  KEY `dish_category` (`dish_category_id`),
  CONSTRAINT `dish_category` FOREIGN KEY (`dish_category_id`) REFERENCES `dish_category` (`dish_category_id`),
  CONSTRAINT `dish_state` FOREIGN KEY (`dish_state_id`) REFERENCES `state` (`state_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of dish
-- ----------------------------

-- ----------------------------
-- Table structure for dish_category
-- ----------------------------
DROP TABLE IF EXISTS `dish_category`;
CREATE TABLE `dish_category` (
  `dish_category_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '分类识别ID',
  `dish_category_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '分类名',
  `dish_category_state_id` int(11) NOT NULL COMMENT '状态码',
  `dish_category_update_time` datetime NOT NULL COMMENT '最近更新时间',
  `dish_category_restaurant_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '分类所属店家',
  PRIMARY KEY (`dish_category_id`),
  KEY `dish_category_state` (`dish_category_state_id`),
  KEY `dish_category_restaurant` (`dish_category_restaurant_id`),
  CONSTRAINT `dish_category_restaurant` FOREIGN KEY (`dish_category_restaurant_id`) REFERENCES `restaurant` (`restaurant_id`),
  CONSTRAINT `dish_category_state` FOREIGN KEY (`dish_category_state_id`) REFERENCES `state` (`state_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of dish_category
-- ----------------------------

-- ----------------------------
-- Table structure for harvest_address
-- ----------------------------
DROP TABLE IF EXISTS `harvest_address`;
CREATE TABLE `harvest_address` (
  `harvest_address_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '地址识别码ID',
  `harvest_address_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT '地址联系人',
  `harvest_address_province` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '地址省份',
  `harvest_address_city` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '地址城市',
  `harvest_address_info` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '地址详细信息',
  `harvest_address_latitude` decimal(14,9) NOT NULL COMMENT '纬度',
  `harvest_address_longitude` decimal(14,9) NOT NULL COMMENT '经度',
  `harvest_address_update_time` datetime NOT NULL COMMENT '最近更新时间',
  `harvest_address_state_id` int(11) NOT NULL COMMENT '地址状态码',
  `harvest_address_order_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '所属订单',
  PRIMARY KEY (`harvest_address_id`),
  KEY `address_state` (`harvest_address_state_id`) USING BTREE,
  KEY `address_customer` (`harvest_address_order_id`) USING BTREE,
  CONSTRAINT `harvest_address_ibfk_2` FOREIGN KEY (`harvest_address_state_id`) REFERENCES `state` (`state_id`),
  CONSTRAINT `harvest_address_order` FOREIGN KEY (`harvest_address_order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of harvest_address
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '订单识别码',
  `order_creat_time` datetime NOT NULL COMMENT '订单创建时间',
  `order_price` decimal(10,2) NOT NULL COMMENT '订单总价',
  `order_update_tiame` datetime NOT NULL COMMENT '最近更新时间',
  `order_delivery_time` datetime DEFAULT NULL COMMENT '订单完成时间',
  `order_state_id` int(11) NOT NULL COMMENT '订单状态码',
  `order_customer_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '所属用户',
  `order_restaurant_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `order_state` (`order_state_id`),
  KEY `order_restaurant` (`order_restaurant_id`),
  KEY `order_customer` (`order_customer_id`),
  CONSTRAINT `order_customer` FOREIGN KEY (`order_customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `order_restaurant` FOREIGN KEY (`order_restaurant_id`) REFERENCES `restaurant` (`restaurant_id`),
  CONSTRAINT `order_state` FOREIGN KEY (`order_state_id`) REFERENCES `state` (`state_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `order_item_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '订单条目识别码',
  `order_item_sum` decimal(10,2) NOT NULL COMMENT '条目总价',
  `order_item_count` int(11) NOT NULL COMMENT '条目商品数量',
  `order_item_order_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '所属订单',
  `order_item_dish_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '条目所含菜肴',
  `order_item_update_time` datetime NOT NULL COMMENT '最近更新时间',
  `order_item_state_id` int(11) NOT NULL COMMENT '状态码',
  PRIMARY KEY (`order_item_id`),
  KEY `order_item_state` (`order_item_state_id`),
  KEY `order_item_order` (`order_item_order_id`),
  KEY `order_item_dish` (`order_item_dish_id`),
  CONSTRAINT `order_item_dish` FOREIGN KEY (`order_item_dish_id`) REFERENCES `dish` (`dish_id`),
  CONSTRAINT `order_item_order` FOREIGN KEY (`order_item_order_id`) REFERENCES `order` (`order_id`),
  CONSTRAINT `order_item_state` FOREIGN KEY (`order_item_state_id`) REFERENCES `state` (`state_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order_item
-- ----------------------------

-- ----------------------------
-- Table structure for province
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `province_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '省份唯一识别码',
  `province_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '省份名',
  `province_update_time` datetime NOT NULL COMMENT '最近修改时间',
  `province_state_id` int(11) NOT NULL COMMENT '状态码',
  PRIMARY KEY (`province_id`),
  KEY `province_state` (`province_state_id`),
  CONSTRAINT `province_state` FOREIGN KEY (`province_state_id`) REFERENCES `state` (`state_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of province
-- ----------------------------
INSERT INTO `province` VALUES ('10001', '北京', '2017-12-03 18:22:36', '40001');
INSERT INTO `province` VALUES ('10002', '江西省', '2017-12-03 18:23:36', '40002');

-- ----------------------------
-- Table structure for restaurant
-- ----------------------------
DROP TABLE IF EXISTS `restaurant`;
CREATE TABLE `restaurant` (
  `restaurant_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '店家识别码',
  `restaurant_name` varchar(60) COLLATE utf8_unicode_ci NOT NULL COMMENT '店家名',
  `restaurant_phone` varchar(16) COLLATE utf8_unicode_ci NOT NULL COMMENT '店家联系电话',
  `restaurant_image` varchar(120) COLLATE utf8_unicode_ci NOT NULL COMMENT '店家图片',
  `restaurant_longitude` decimal(14,9) NOT NULL COMMENT '经度',
  `restaurant_latitude` decimal(14,9) NOT NULL COMMENT '维度',
  `restaurant_notice` text COLLATE utf8_unicode_ci NOT NULL COMMENT '公告',
  `restaurant_distance` decimal(16,3) DEFAULT '0.000' COMMENT '距离',
  `restaurant_update_time` datetime NOT NULL COMMENT '最近修改时间',
  `restaurant_state_id` int(11) NOT NULL COMMENT '状态码',
  `restaurant_user_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '所属用户ID',
  `restaurant_category_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '所属分类ID',
  `restaurant_city_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '所属城市ID',
  PRIMARY KEY (`restaurant_id`),
  KEY `restaurant_state` (`restaurant_state_id`),
  KEY `restaurant_user` (`restaurant_user_id`),
  KEY `restaurant_category` (`restaurant_category_id`),
  KEY `restaurant_city` (`restaurant_city_id`),
  CONSTRAINT `restaurant_category` FOREIGN KEY (`restaurant_category_id`) REFERENCES `restaurant_category` (`restaurant_category_id`),
  CONSTRAINT `restaurant_city` FOREIGN KEY (`restaurant_city_id`) REFERENCES `city` (`city_id`),
  CONSTRAINT `restaurant_state` FOREIGN KEY (`restaurant_state_id`) REFERENCES `state` (`state_id`),
  CONSTRAINT `restaurant_user` FOREIGN KEY (`restaurant_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of restaurant
-- ----------------------------

-- ----------------------------
-- Table structure for restaurant_category
-- ----------------------------
DROP TABLE IF EXISTS `restaurant_category`;
CREATE TABLE `restaurant_category` (
  `restaurant_category_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '店家分类识别码',
  `restaurant_category_name` varchar(60) COLLATE utf8_unicode_ci NOT NULL COMMENT '店家分类名',
  `restaurant_category_update_time` datetime NOT NULL COMMENT '店家分类信息最近更新时间',
  `restaurant_category_state_id` int(11) NOT NULL COMMENT '店家分类状态码',
  PRIMARY KEY (`restaurant_category_id`),
  KEY `restaurant_category_state` (`restaurant_category_state_id`),
  CONSTRAINT `restaurant_category_state` FOREIGN KEY (`restaurant_category_state_id`) REFERENCES `state` (`state_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of restaurant_category
-- ----------------------------
INSERT INTO `restaurant_category` VALUES ('1', '盖浇饭', '2017-12-08 23:35:52', '30001');

-- ----------------------------
-- Table structure for rider
-- ----------------------------
DROP TABLE IF EXISTS `rider`;
CREATE TABLE `rider` (
  `rider_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '骑手识别码',
  `rider_longitude` decimal(14,9) NOT NULL COMMENT '经度',
  `rider_latitude` decimal(14,9) NOT NULL COMMENT '维度',
  `rider_update_time` datetime NOT NULL COMMENT '最近更新时间',
  `rider_user_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '对应用户识别码',
  `rider_state_id` int(11) NOT NULL,
  PRIMARY KEY (`rider_id`),
  KEY `rider_user` (`rider_user_id`),
  KEY `rider_state` (`rider_state_id`),
  CONSTRAINT `rider_state` FOREIGN KEY (`rider_state_id`) REFERENCES `state` (`state_id`),
  CONSTRAINT `rider_user` FOREIGN KEY (`rider_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of rider
-- ----------------------------

-- ----------------------------
-- Table structure for state
-- ----------------------------
DROP TABLE IF EXISTS `state`;
CREATE TABLE `state` (
  `state_id` int(11) NOT NULL COMMENT '信息编码',
  `state_info` varchar(120) COLLATE utf8_unicode_ci NOT NULL COMMENT '信息编码介绍',
  `state_update_time` datetime NOT NULL COMMENT '该条记录最近的修改时间',
  PRIMARY KEY (`state_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of state
-- ----------------------------
INSERT INTO `state` VALUES ('10000', 'admin', '2017-12-14 14:26:35');
INSERT INTO `state` VALUES ('10001', '未激活', '2017-12-03 16:47:01');
INSERT INTO `state` VALUES ('10002', '已激活正在使用', '2017-12-03 16:47:20');
INSERT INTO `state` VALUES ('10003', '锁定（封号）', '2017-12-03 16:48:03');
INSERT INTO `state` VALUES ('10004', '注销（可理解为删号）', '2017-12-03 16:48:36');
INSERT INTO `state` VALUES ('20000', 'customer', '2017-12-03 17:21:23');
INSERT INTO `state` VALUES ('20001', '未激活', '2017-12-14 11:56:00');
INSERT INTO `state` VALUES ('20002', '正在使用', '2017-12-03 17:22:38');
INSERT INTO `state` VALUES ('20003', '锁定（封号）', '2017-12-03 17:23:39');
INSERT INTO `state` VALUES ('20004', '注销（删号）', '2017-12-03 17:25:37');
INSERT INTO `state` VALUES ('30000', 'restaurant_category', '2017-12-08 23:34:12');
INSERT INTO `state` VALUES ('30001', '可用', '2017-12-08 23:34:49');
INSERT INTO `state` VALUES ('30002', '不可用', '2017-12-08 23:35:02');
INSERT INTO `state` VALUES ('40000', 'province', '2017-12-03 17:59:29');
INSERT INTO `state` VALUES ('40001', '开放', '2017-12-03 17:59:43');
INSERT INTO `state` VALUES ('40002', '未开放', '2017-12-03 18:00:01');
INSERT INTO `state` VALUES ('50000', 'city', '2017-12-03 18:06:04');
INSERT INTO `state` VALUES ('50001', '开放', '2017-12-03 18:06:34');
INSERT INTO `state` VALUES ('50002', '未开放', '2017-12-03 18:06:50');
INSERT INTO `state` VALUES ('60000', 'address', '2017-12-09 00:01:16');
INSERT INTO `state` VALUES ('60001', '已删除地址', '2017-12-09 00:01:45');
INSERT INTO `state` VALUES ('60002', '默认地址', '2017-12-09 00:02:58');
INSERT INTO `state` VALUES ('60003', '正在使用', '2017-12-09 00:03:27');
INSERT INTO `state` VALUES ('70000', 'restaurant', '2017-12-09 00:29:27');
INSERT INTO `state` VALUES ('70001', '已禁用', '2017-12-09 00:29:49');
INSERT INTO `state` VALUES ('70002', '正在营业', '2017-12-09 00:30:44');
INSERT INTO `state` VALUES ('70003', '休息中', '2017-12-09 00:30:59');
INSERT INTO `state` VALUES ('80000', 'dish_category', '2017-12-09 20:03:33');
INSERT INTO `state` VALUES ('80001', '正在使用', '2017-12-09 20:04:01');
INSERT INTO `state` VALUES ('80002', '已停用', '2017-12-09 20:04:18');
INSERT INTO `state` VALUES ('90000', 'dish', '2017-12-09 22:47:34');
INSERT INTO `state` VALUES ('90001', '已删除', '2017-12-09 22:48:14');
INSERT INTO `state` VALUES ('90002', '有库存', '2017-12-09 22:49:06');
INSERT INTO `state` VALUES ('90003', '无库存', '2017-12-09 22:49:19');
INSERT INTO `state` VALUES ('100000', 'order', '2017-12-10 00:27:36');
INSERT INTO `state` VALUES ('100001', '已下单未付款', '2017-12-10 00:28:31');
INSERT INTO `state` VALUES ('100002', '已付款未发货', '2017-12-10 00:28:56');
INSERT INTO `state` VALUES ('100003', '配送中', '2017-12-10 00:29:41');
INSERT INTO `state` VALUES ('100004', '完成订单', '2017-12-10 00:30:14');
INSERT INTO `state` VALUES ('100005', '已删除订单', '2017-12-10 00:30:43');
INSERT INTO `state` VALUES ('110000', 'harvest_address', '2017-12-10 00:36:59');
INSERT INTO `state` VALUES ('110001', '正在使用', '2017-12-10 00:37:24');
INSERT INTO `state` VALUES ('110002', '已删除', '2017-12-10 00:37:48');
INSERT INTO `state` VALUES ('120000', 'order_item', '2017-12-10 00:46:44');
INSERT INTO `state` VALUES ('120001', '正在使用', '2017-12-10 00:49:07');
INSERT INTO `state` VALUES ('120002', '已删除', '2017-12-10 00:48:37');
INSERT INTO `state` VALUES ('130000', 'rider', '2017-12-17 18:18:28');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户唯一识别码',
  `user_account` varchar(12) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户账号',
  `user_password` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户密码',
  `user_email` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT '邮箱',
  `user_phone` varchar(16) COLLATE utf8_unicode_ci NOT NULL COMMENT '联系方式',
  `user_update_time` datetime NOT NULL COMMENT '最近用户信息更新时间',
  `user_code` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户激活码码',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1111111111111111111111111111', '100001', '100001', '1234@qq.com', '12345', '2017-12-14 16:46:48', '11');
INSERT INTO `user` VALUES ('121212121', '100003', '100003', 'qwer@qq.com', '12121', '2017-12-03 17:19:51', '121212121');
INSERT INTO `user` VALUES ('123456', '1000001', '100001', '1234@qq.com', '12345', '2017-12-14 16:46:48', '11');
INSERT INTO `user` VALUES ('123456123', '100002', '100002', '21212@qq.com', '1212121', '2017-12-03 17:19:05', '212121');
INSERT INTO `user` VALUES ('12345678901234567890123456789012', '123456', '123456', '123@qq.com', '12345678901', '2017-12-03 17:16:38', 'qwertyuioppoiuytrewqqwertyuioppo');
