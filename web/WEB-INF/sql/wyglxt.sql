/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : wyglxt

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 28/11/2020 12:56:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for members
-- ----------------------------
DROP TABLE IF EXISTS `members`;
CREATE TABLE `members`  (
  `cno` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cname` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `csex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `caddress` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cregtime` datetime(0) NULL DEFAULT NULL,
  `cmoney` int(0) NULL DEFAULT NULL,
  `cphone` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`cno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of members
-- ----------------------------
INSERT INTO `members` VALUES ('10000', '胡晓帆', '男', 'xx小区，xx栋，888室', '2020-11-21 14:05:22', 318, '13777063302');
INSERT INTO `members` VALUES ('10001', '李明', '女', 'xx小区，xx栋，68室', '2020-12-02 19:20:31', 0, '13123123123');
INSERT INTO `members` VALUES ('10002', '赵伟', '女', 'xx小区，xx栋，65室', '1989-06-09 16:00:00', 50, '13123123123');
INSERT INTO `members` VALUES ('10003', '张立', '男', 'xx小区，xx栋，333室', '1989-06-07 16:00:00', 101, '13123123123');
INSERT INTO `members` VALUES ('10004', '李明', '男', 'xx小区，yy栋，45室', '1987-04-03 16:00:00', 90, '13123123123');
INSERT INTO `members` VALUES ('10005', '张小悔', '女', 'xx小区，yy栋，995室', '1986-01-03 16:00:00', 90, '13123123123');
INSERT INTO `members` VALUES ('10006', '封小文', '女', 'xx小区，yy栋，435室', '1988-07-08 00:00:00', 90, '13123123123');
INSERT INTO `members` VALUES ('10007', '冯晓文', '男', 'xx小区，yy栋，233室', '1987-05-06 16:00:00', 90, '13123123123');
INSERT INTO `members` VALUES ('10008', '孙红梅', '女', 'xx小区，yy栋，33室', '1990-09-10 16:00:00', 90, '13123123123');
INSERT INTO `members` VALUES ('10009', '沈三明', '男', 'xx小区，yy栋，22室', '1986-06-06 16:00:00', 0, '13123123123');
INSERT INTO `members` VALUES ('10010', '志吴扬', '男', 'xx小区，yy栋，11室', '1987-04-05 16:00:00', 110, '13123123123');
INSERT INTO `members` VALUES ('10011', '徐艳霞', '女', 'xx小区，yy栋，415室', '1990-09-10 16:00:00', 120, '13123123123');
INSERT INTO `members` VALUES ('10012', '陈峰子明', '男', 'xx小区，yy栋，321室', '1989-05-03 16:00:00', 130, '13123123123');
INSERT INTO `members` VALUES ('10013', '俞飞', '男', 'xx小区，zz栋，301室', '1986-07-04 16:00:00', 70, '13123123123');
INSERT INTO `members` VALUES ('10014', '扬菲', '女', 'xx小区，zz栋，311室', '1987-03-08 16:00:00', 90, '13123123123');
INSERT INTO `members` VALUES ('10015', '魏玲领', '女', 'xx小区，zz栋，321室', '1989-04-04 16:00:00', 200, '13123123123');
INSERT INTO `members` VALUES ('10016', '倪琳', '女', 'xx小区，zz栋，221室', '1987-09-03 16:00:00', 300, '13123123123');
INSERT INTO `members` VALUES ('10017', '许永', '男', 'xx小区，zz栋，121室', '1986-01-09 16:00:00', 500, '13123123123');
INSERT INTO `members` VALUES ('10018', '屈炎炎', '女', 'xx小区，zz栋，521室', '1990-08-08 16:00:00', 100, '13123123123');
INSERT INTO `members` VALUES ('10020', '孙炜炜', '女', 'xx小区，zz栋，233室', '1989-06-09 16:00:00', 300, '13123123123');
INSERT INTO `members` VALUES ('10030', '夏炀小波', '女', 'xx小区，zz栋，828室', '1990-04-06 16:00:00', 330, '13123123123');
INSERT INTO `members` VALUES ('10031', '陈涛', '男', 'xx小区，zz栋，992室', '1989-06-07 16:00:00', 320, '13123123123');
INSERT INTO `members` VALUES ('10032', '李恺毅', '男', 'xx小区，zz栋，101室', '1987-04-03 16:00:00', 310, '13123123123');
INSERT INTO `members` VALUES ('10033', '斯涛', '女', 'xx小区，zz栋，303室', '1986-01-03 16:00:00', 150, '13123123123');
INSERT INTO `members` VALUES ('10034', '范军鉴', '男', 'xx小区，zz栋，404室', '1988-07-14 00:00:00', 160, '13123123123');
INSERT INTO `members` VALUES ('10035', '刘然', '男', 'xx小区，xx栋，505室', '1987-05-06 16:00:00', 170, '13123123123');
INSERT INTO `members` VALUES ('10036', '吴乐乐', '女', 'xx小区，xx栋，605室', '1990-07-03 16:00:00', 180, '13123123123');
INSERT INTO `members` VALUES ('10037', '李小超', '男', 'xx小区，xx栋，705室', '1990-06-06 16:00:00', 49, '13123123123');
INSERT INTO `members` VALUES ('10038', '陈志豪', '男', 'xx小区，xx栋，805室', '1991-04-05 16:00:00', 33, '13123123123');
INSERT INTO `members` VALUES ('10039', '楼小亮', '男', 'xx小区，xx栋，905室', '1989-05-03 16:00:00', 45, '13123123123');

-- ----------------------------
-- Table structure for mpass
-- ----------------------------
DROP TABLE IF EXISTS `mpass`;
CREATE TABLE `mpass`  (
  `cno` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `cpass` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`cno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mpass
-- ----------------------------
INSERT INTO `mpass` VALUES ('10000', 'e600f9dd04366d5ef43b68f5c3773f61');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `cno` int(0) NULL DEFAULT NULL,
  `sid` int(0) NULL DEFAULT NULL,
  `date` datetime(6) NULL DEFAULT NULL,
  `status` tinyint(1) NULL DEFAULT 1,
  `staff` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) NULL DEFAULT NULL,
  `cno` int(0) NULL DEFAULT NULL,
  `sid` int(0) NULL DEFAULT NULL,
  `method` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `times` int(0) NULL DEFAULT NULL,
  `staff` int(0) NULL DEFAULT NULL,
  `money` double(5, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (1, '2020-11-22 10:52:26.000000', 10006, 6, 'pay', 1, 1, 50.00);
INSERT INTO `record` VALUES (20, '2020-11-22 20:58:09.000000', 10001, 1, 'pay', 2, 1, 100.00);
INSERT INTO `record` VALUES (21, '2020-11-22 21:03:54.000000', 10007, 3, 'pay', 2, 6, 100.00);
INSERT INTO `record` VALUES (22, '2020-11-23 10:51:13.000000', 10023, NULL, 'income', 1, NULL, 40.00);
INSERT INTO `record` VALUES (23, '2020-11-23 10:51:44.000000', 10011, NULL, 'income', 1, NULL, 1.00);
INSERT INTO `record` VALUES (24, '2020-11-23 10:52:58.000000', 10022, NULL, 'income', 1, NULL, 1.00);
INSERT INTO `record` VALUES (25, '2020-11-23 10:53:03.000000', 10008, NULL, 'income', 1, NULL, 1.00);
INSERT INTO `record` VALUES (26, '2020-11-23 17:31:40.000000', 10009, NULL, 'income', 1, NULL, 1.00);
INSERT INTO `record` VALUES (27, '2020-11-24 17:45:47.000000', 10013, 6, 'pay', 1, 1, 50.00);
INSERT INTO `record` VALUES (29, '2020-11-26 09:54:22.000000', 10001, 2, 'pay', 1, 1, 200.00);
INSERT INTO `record` VALUES (34, '2020-11-26 15:07:25.000000', 10000, 4, 'pay', 1, 1, 100.00);
INSERT INTO `record` VALUES (37, '2020-11-26 15:24:38.000000', 10002, 8, 'pay', 1, 1, 50.00);
INSERT INTO `record` VALUES (39, '2020-11-26 20:51:24.000000', 10000, NULL, 'income', 1, NULL, 100.00);
INSERT INTO `record` VALUES (43, '2020-11-28 12:53:20.000000', 10000, 3, 'pay', 1, 1, 50.00);
INSERT INTO `record` VALUES (44, '2020-11-28 12:53:45.000000', 10000, NULL, 'income', 1, NULL, 100.00);

-- ----------------------------
-- Table structure for service
-- ----------------------------
DROP TABLE IF EXISTS `service`;
CREATE TABLE `service`  (
  `sid` int(0) NOT NULL,
  `sname` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sprice` double(10, 2) NULL DEFAULT NULL,
  `sdesc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `stime` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of service
-- ----------------------------
INSERT INTO `service` VALUES (1, '楼道清理', 50.00, '帮助业主清理单元楼楼道卫生保洁', 12);
INSERT INTO `service` VALUES (2, '上门开锁', 200.00, '专业的开人员，帮助业主开锁。（本服务需要业主实名登记）', 4);
INSERT INTO `service` VALUES (3, '电路修理', 50.00, '上门更换保险丝 / 空气开关 / 漏电保护器等', 5);
INSERT INTO `service` VALUES (4, '水路维修', 100.00, '室内水管更换，水龙头，花洒，洗衣机安装', 2);
INSERT INTO `service` VALUES (5, '水管疏通', 50.00, '马桶，下水道堵塞清理疏通', 1);
INSERT INTO `service` VALUES (6, '水费代扣缴', 50.00, '使用物业费余额充值水费', 2);
INSERT INTO `service` VALUES (7, '电费代扣缴', 50.00, '使用物业费缴纳电费', 2);
INSERT INTO `service` VALUES (8, '燃气费代扣缴', 50.00, '使用物业费缴纳煤气费', 3);

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `eid` int(0) NOT NULL AUTO_INCREMENT,
  `ename` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `esex` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `escore` int(0) NULL DEFAULT 0,
  `epass` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `isadmin` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`eid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (1, '胡晓帆', '男', 280, 'e600f9dd04366d5ef43b68f5c3773f61', 1);
INSERT INTO `staff` VALUES (2, '王帅帅', '男', 10, 'e600f9dd04366d5ef43b68f5c3773f61', 0);
INSERT INTO `staff` VALUES (3, '刘拖地', '女', 6, '913c6ddb803fe570449cf880674dd109', 0);
INSERT INTO `staff` VALUES (4, '陆刷锅', '男', 5, '913c6ddb803fe570449cf880674dd109', 0);
INSERT INTO `staff` VALUES (5, '魏算账', '男', 2, '913c6ddb803fe570449cf880674dd109', 0);
INSERT INTO `staff` VALUES (6, '李水电', '男', 55, '913c6ddb803fe570449cf880674dd109', 0);
INSERT INTO `staff` VALUES (7, '赵锤子', '男', 8, '913c6ddb803fe570449cf880674dd109', 0);
INSERT INTO `staff` VALUES (8, '何聪聪', '男', 0, 'b0cd9776fe1dc57f158024f72258a8d8', 0);

SET FOREIGN_KEY_CHECKS = 1;
