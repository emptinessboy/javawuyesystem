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

 Date: 20/11/2020 23:17:23
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
INSERT INTO `members` VALUES ('2009000001', '李明', '男', '计算机系', '2020-11-20 11:20:31', 100);
INSERT INTO `members` VALUES ('2009000002', '赵伟', '女', '信息系', '1989-06-09 00:00:00', 100);
INSERT INTO `members` VALUES ('2009000004', '张立', '男', '信息系', '1989-06-07 00:00:00', 100);
INSERT INTO `members` VALUES ('2009000005', '李明', '男', '管理系', '1987-04-03 00:00:00', 90);
INSERT INTO `members` VALUES ('2009000006', '张小悔', '女', '信息系', '1986-01-03 00:00:00', 90);
INSERT INTO `members` VALUES ('2009000007', '封小文', '女', '管理系', '1988-07-07 00:00:00', 90);
INSERT INTO `members` VALUES ('2009000008', '冯晓文', '男', '文传系', '1987-05-06 00:00:00', 90);
INSERT INTO `members` VALUES ('2009000009', '孙红梅', '女', '英语系', '1990-09-10 00:00:00', 90);
INSERT INTO `members` VALUES ('2009000010', '沈三明', '男', '文传系', '1986-06-06 00:00:00', 80);
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
INSERT INTO `members` VALUES ('2010000009', '李小超', '男', '文传系', '1990-06-06 00:00:00', 99);
INSERT INTO `members` VALUES ('2010000010', '陈志豪', '男', '计算机系', '1991-04-05 00:00:00', 33);
INSERT INTO `members` VALUES ('2011000001', '楼小亮', '男', '管理系', '1989-05-03 00:00:00', 45);
INSERT INTO `members` VALUES ('2011000002', '胡晓帆', '男', '宁波市，鄞州区，xx小区，xx栋，888室', '2020-11-20 14:05:22', 268);
INSERT INTO `members` VALUES ('2011000005', '刘呵呵', '男', '宁波市，鄞州区，xx小区，xx栋，11室', '2020-11-20 14:12:48', 157);
INSERT INTO `members` VALUES ('2011000006', '魏建楠', '女', '666', '2020-11-20 15:38:38', 2);
INSERT INTO `members` VALUES ('2011000007', '刘思远', '女', '山东分公司d', '2020-11-20 15:38:48', 1);

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
INSERT INTO `mpass` VALUES ('2011000002', '860c56f28eecd24e1170d56d6feccedd');
INSERT INTO `mpass` VALUES ('2011000005', '913c6ddb803fe570449cf880674dd109');
INSERT INTO `mpass` VALUES ('2011000006', '860c56f28eecd24e1170d56d6feccedd');
INSERT INTO `mpass` VALUES ('2011000007', '529e1fb35450b6f2dd613f7dbd8f7017');

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
INSERT INTO `service` VALUES (1, '楼道清理', 50.00, '帮助业主清理单元楼楼道卫生保洁', 1);

SET FOREIGN_KEY_CHECKS = 1;
