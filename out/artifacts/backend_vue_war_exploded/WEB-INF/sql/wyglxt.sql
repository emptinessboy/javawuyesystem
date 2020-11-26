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

 Date: 26/11/2020 17:41:35
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
  PRIMARY KEY (`cno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of members
-- ----------------------------
INSERT INTO `members` VALUES ('2009000001', '李明', '女', '计算机系', '2020-12-01 19:20:31', 50);
INSERT INTO `members` VALUES ('2009000002', '赵伟', '女', '信息系', '1989-06-09 00:00:00', 50);
INSERT INTO `members` VALUES ('2009000004', '张立', '男', '信息系', '1989-06-07 00:00:00', 101);
INSERT INTO `members` VALUES ('2009000005', '李明', '男', '管理系', '1987-04-03 00:00:00', 90);
INSERT INTO `members` VALUES ('2009000006', '张小悔', '女', '信息系', '1986-01-03 00:00:00', 90);
INSERT INTO `members` VALUES ('2009000007', '封小文', '女', '管理系', '1988-07-07 08:00:00', 90);
INSERT INTO `members` VALUES ('2009000008', '冯晓文', '男', '文传系', '1987-05-06 00:00:00', 90);
INSERT INTO `members` VALUES ('2009000009', '孙红梅', '女', '英语系', '1990-09-10 00:00:00', 90);
INSERT INTO `members` VALUES ('2009000010', '沈三明', '男', '文传系', '1986-06-06 00:00:00', 0);
INSERT INTO `members` VALUES ('2009000011', '志吴扬', '男', '计算机系', '1987-04-05 00:00:00', 110);
INSERT INTO `members` VALUES ('2009000012', '徐艳霞', '女', '英语系', '1990-09-10 00:00:00', 120);
INSERT INTO `members` VALUES ('2009000013', '陈峰子明', '男', '管理系', '1989-05-03 00:00:00', 130);
INSERT INTO `members` VALUES ('2009000014', '俞飞', '男', '英语系', '1986-07-04 00:00:00', 70);
INSERT INTO `members` VALUES ('2009000015', '扬菲', '女', '信息系', '1987-03-08 00:00:00', 90);
INSERT INTO `members` VALUES ('2009000017', '魏玲领', '女', '文传系', '1989-04-04 00:00:00', 200);
INSERT INTO `members` VALUES ('2009000018', '倪琳', '女', '文传系', '1987-09-03 00:00:00', 300);
INSERT INTO `members` VALUES ('2009000019', '许永', '男', '计算机系', '1986-01-09 00:00:00', 500);
INSERT INTO `members` VALUES ('2009000020', '屈炎炎', '女', '英语系', '1990-08-08 00:00:00', 100);
INSERT INTO `members` VALUES ('2010000001', '孙炜炜', '女', '信息系', '1989-06-09 00:00:00', 300);
INSERT INTO `members` VALUES ('2010000002', '夏炀小波', '女', '管理系', '1990-04-06 00:00:00', 330);
INSERT INTO `members` VALUES ('2010000003', '陈涛', '男', '信息系', '1989-06-07 00:00:00', 320);
INSERT INTO `members` VALUES ('2010000004', '李恺毅', '男', '管理系', '1987-04-03 00:00:00', 310);
INSERT INTO `members` VALUES ('2010000005', '斯涛', '女', '管理系', '1986-01-03 00:00:00', 150);
INSERT INTO `members` VALUES ('2010000006', '范军鉴', '女', '管理系', '1988-07-07 00:00:00', 160);
INSERT INTO `members` VALUES ('2010000007', '刘然', '男', '文传系', '1987-05-06 00:00:00', 170);
INSERT INTO `members` VALUES ('2010000008', '吴乐乐', '女', '英语系', '1990-07-03 00:00:00', 180);
INSERT INTO `members` VALUES ('2010000009', '李小超', '男', '文传系', '1990-06-06 00:00:00', 49);
INSERT INTO `members` VALUES ('2010000010', '陈志豪', '男', '计算机系', '1991-04-05 00:00:00', 33);
INSERT INTO `members` VALUES ('2011000001', '楼小亮', '男', '管理系', '1989-05-03 00:00:00', 45);
INSERT INTO `members` VALUES ('2011000002', '胡晓帆', '男', '宁波市，鄞州区，xx小区，xx栋，888室', '2020-11-20 14:05:22', 168);
INSERT INTO `members` VALUES ('2011000005', '刘呵呵', '男', '宁波市，鄞州区，xx小区，xx栋，11室', '2020-11-20 14:12:48', 57);
INSERT INTO `members` VALUES ('2011000006', '魏建楠', '女', '666', '2020-11-20 15:38:38', 2);
INSERT INTO `members` VALUES ('2011000007', '测试', '男', '帆帆帆帆帆帆帆帆', '2020-11-23 10:47:32', 0);

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
INSERT INTO `mpass` VALUES ('2009000001', '798afc6251a846ce3f14c36a9ccdc669');
INSERT INTO `mpass` VALUES ('2011000002', '860c56f28eecd24e1170d56d6feccedd');
INSERT INTO `mpass` VALUES ('2011000005', '913c6ddb803fe570449cf880674dd109');
INSERT INTO `mpass` VALUES ('2011000006', '860c56f28eecd24e1170d56d6feccedd');
INSERT INTO `mpass` VALUES ('2011000007', '554259450aac4e5d0de0db9a668d04e7');

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (16, 2009000001, 8, '2020-11-26 15:27:11.000000', 1, 2);

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
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (1, '2020-11-22 10:52:26.000000', 2009000002, 6, 'pay', 1, 1, 50.00);
INSERT INTO `record` VALUES (20, '2020-11-22 20:58:09.000000', 2011000002, 1, 'pay', 2, 1, 100.00);
INSERT INTO `record` VALUES (21, '2020-11-22 21:03:54.000000', 2011000005, 3, 'pay', 2, 6, 100.00);
INSERT INTO `record` VALUES (22, '2020-11-23 10:51:13.000000', 2009000001, NULL, 'income', 1, NULL, 40.00);
INSERT INTO `record` VALUES (23, '2020-11-23 10:51:44.000000', 2009000001, NULL, 'income', 1, NULL, 1.00);
INSERT INTO `record` VALUES (24, '2020-11-23 10:52:58.000000', 2009000001, NULL, 'income', 1, NULL, 1.00);
INSERT INTO `record` VALUES (25, '2020-11-23 10:53:03.000000', 2009000004, NULL, 'income', 1, NULL, 1.00);
INSERT INTO `record` VALUES (26, '2020-11-23 17:31:40.000000', 2009000001, NULL, 'income', 1, NULL, 1.00);
INSERT INTO `record` VALUES (27, '2020-11-24 17:45:47.000000', 2009000001, 6, 'pay', 1, 1, 50.00);
INSERT INTO `record` VALUES (28, '2020-11-25 20:12:13.000000', 2010000009, 3, 'pay', 1, 1, 50.00);
INSERT INTO `record` VALUES (29, '2020-11-26 09:54:22.000000', 2009000001, 2, 'pay', 1, 1, 200.00);
INSERT INTO `record` VALUES (30, '2020-11-26 10:22:04.000000', 2009000001, 2, 'pay', 1, 1, 200.00);
INSERT INTO `record` VALUES (31, '2020-11-26 10:31:55.000000', 2009000001, 2, 'pay', 1, 1, 200.00);
INSERT INTO `record` VALUES (32, '2020-11-26 14:52:13.000000', 2009000001, NULL, 'income', 1, NULL, 400.00);
INSERT INTO `record` VALUES (33, '2020-11-26 14:52:19.000000', 2009000001, 2, 'pay', 1, 1, 200.00);
INSERT INTO `record` VALUES (34, '2020-11-26 15:07:25.000000', 2009000001, 4, 'pay', 1, 1, 100.00);
INSERT INTO `record` VALUES (35, '2020-11-26 15:09:09.000000', 2009000001, 8, 'pay', 1, 1, 50.00);
INSERT INTO `record` VALUES (36, '2020-11-26 15:11:41.000000', 2009000001, 7, 'pay', 1, 1, 50.00);
INSERT INTO `record` VALUES (37, '2020-11-26 15:24:38.000000', 2009000001, 8, 'pay', 1, 1, 50.00);

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
INSERT INTO `service` VALUES (1, '楼道清理', 50.00, '帮助业主清理单元楼楼道卫生保洁', 10);
INSERT INTO `service` VALUES (2, '上门开锁', 200.00, '专业的开人员，帮助业主开锁。（本服务需要业主实名登记）', 4);
INSERT INTO `service` VALUES (3, '电路修理', 50.00, '上门更换保险丝 / 空气开关 / 漏电保护器等', 4);
INSERT INTO `service` VALUES (4, '水路维修', 100.00, '室内水管更换，水龙头，花洒，洗衣机安装', 2);
INSERT INTO `service` VALUES (5, '水管疏通', 50.00, '马桶，下水道堵塞清理疏通', 1);
INSERT INTO `service` VALUES (6, '水费代扣缴', 50.00, '使用物业费余额充值水费', 2);
INSERT INTO `service` VALUES (7, '电费代扣缴', 50.00, '使用物业费缴纳电费', 2);
INSERT INTO `service` VALUES (8, '燃气费代扣缴', 50.00, '使用物业费缴纳煤气费', 2);

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
INSERT INTO `staff` VALUES (1, '胡晓帆', '男', 260, '798afc6251a846ce3f14c36a9ccdc669', 1);
INSERT INTO `staff` VALUES (2, '王帅帅', '男', 10, '913c6ddb803fe570449cf880674dd109', 0);
INSERT INTO `staff` VALUES (3, '刘拖地', '女', 6, '913c6ddb803fe570449cf880674dd109', 0);
INSERT INTO `staff` VALUES (4, '陆刷锅', '男', 5, '913c6ddb803fe570449cf880674dd109', 0);
INSERT INTO `staff` VALUES (5, '魏算账', '男', 2, '913c6ddb803fe570449cf880674dd109', 0);
INSERT INTO `staff` VALUES (6, '李水电', '男', 55, '913c6ddb803fe570449cf880674dd109', 0);
INSERT INTO `staff` VALUES (7, '赵锤子', '男', 8, '913c6ddb803fe570449cf880674dd109', 0);

SET FOREIGN_KEY_CHECKS = 1;
