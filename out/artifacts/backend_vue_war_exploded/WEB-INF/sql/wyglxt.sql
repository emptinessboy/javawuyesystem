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

 Date: 19/11/2020 22:14:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `cno` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cpno` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ccredit` float NULL DEFAULT NULL,
  PRIMARY KEY (`cno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '数据库', '5', 4);
INSERT INTO `course` VALUES ('2', '数学', '0', 2);
INSERT INTO `course` VALUES ('3', '信息系统', '1', 4);
INSERT INTO `course` VALUES ('4', '操作系统', '6', 3);
INSERT INTO `course` VALUES ('5', '数据机构', '7', 2);
INSERT INTO `course` VALUES ('6', 'C语言', '6', 0);
INSERT INTO `course` VALUES ('7', 'PASCAL语言', '6', 4);
INSERT INTO `course` VALUES ('8', '大数据处理', '2', 3);

-- ----------------------------
-- Table structure for members
-- ----------------------------
DROP TABLE IF EXISTS `members`;
CREATE TABLE `members`  (
  `cno` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cname` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `csex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `caddress` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cregtime` datetime(0) NULL DEFAULT NULL,
  `cmoney` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`cno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of members
-- ----------------------------
INSERT INTO `members` VALUES ('2009000001', '李明', '男', '计算机系', '1988-07-09 00:00:00', 100);
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

-- ----------------------------
-- Table structure for mm
-- ----------------------------
DROP TABLE IF EXISTS `mm`;
CREATE TABLE `mm`  (
  `user1` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password1` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sno` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mm
-- ----------------------------
INSERT INTO `mm` VALUES ('qq', '125', '2009000001');
INSERT INTO `mm` VALUES ('aa', '123', '2009000002');
INSERT INTO `mm` VALUES ('mm', '223', '2009000003');
INSERT INTO `mm` VALUES ('rr', '666', '2009000004');
INSERT INTO `mm` VALUES ('nn', '557', '2009000008');
INSERT INTO `mm` VALUES ('ff', '558', '2009000009');
INSERT INTO `mm` VALUES ('ee', 'e12', '200900001O');
INSERT INTO `mm` VALUES ('jj', '555', '2009000005');
INSERT INTO `mm` VALUES ('hh', '233', '2009000006');
INSERT INTO `mm` VALUES ('bb', '333', '2009000007');

-- ----------------------------
-- Table structure for sc
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc`  (
  `sno` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cno` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grade` float NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc
-- ----------------------------
INSERT INTO `sc` VALUES ('2009000001', '1', 92);
INSERT INTO `sc` VALUES ('2009000002', '2', 90);
INSERT INTO `sc` VALUES ('2009000003', '1', 78);
INSERT INTO `sc` VALUES ('2009000004', '1', 90);
INSERT INTO `sc` VALUES ('2009000005', '1', 80);
INSERT INTO `sc` VALUES ('2009000006', '2', 80);
INSERT INTO `sc` VALUES ('2009000009', '3', 93);
INSERT INTO `sc` VALUES ('2009000010', '4', 68);
INSERT INTO `sc` VALUES ('2009000011', '3', 60);
INSERT INTO `sc` VALUES ('2009000012', '2', 80);
INSERT INTO `sc` VALUES ('2009000013', '4', 89);
INSERT INTO `sc` VALUES ('2009000014', '3', 80);
INSERT INTO `sc` VALUES ('2009000015', '4', 65);
INSERT INTO `sc` VALUES ('2009000016', '1', 72);
INSERT INTO `sc` VALUES ('2009000017', '2', 89);
INSERT INTO `sc` VALUES ('2009000018', '3', 81);
INSERT INTO `sc` VALUES ('2009000019', '2', 68);
INSERT INTO `sc` VALUES ('2009000020', '3', 93);
INSERT INTO `sc` VALUES ('2010000001', '2', 87);
INSERT INTO `sc` VALUES ('2010000002', '4', 80);
INSERT INTO `sc` VALUES ('2010000003', '5', 65);
INSERT INTO `sc` VALUES ('2010000004', '6', 72);
INSERT INTO `sc` VALUES ('2010000005', '7', 89);
INSERT INTO `sc` VALUES ('2010000006', '8', 81);
INSERT INTO `sc` VALUES ('2010000007', '3', 60);
INSERT INTO `sc` VALUES ('2010000008', '2', 80);
INSERT INTO `sc` VALUES ('2010000009', '4', 89);
INSERT INTO `sc` VALUES ('2010000010', '3', 80);
INSERT INTO `sc` VALUES ('2011000001', '4', 65);
INSERT INTO `sc` VALUES ('2011000033', '1', 72);
INSERT INTO `sc` VALUES ('2009000001', '2', 85);
INSERT INTO `sc` VALUES ('2009000001', '3', 88);
INSERT INTO `sc` VALUES ('2009000002', '3', 80);
INSERT INTO `sc` VALUES ('2009000003', '2', 80);
INSERT INTO `sc` VALUES ('2009000004', '4', 60);
INSERT INTO `sc` VALUES ('2009000005', '3', 89);
INSERT INTO `sc` VALUES ('2009000009', '2', 79);
INSERT INTO `sc` VALUES ('2009000001', '4', 80);
INSERT INTO `sc` VALUES ('2009000001', '5', 65);
INSERT INTO `sc` VALUES ('2009000001', '6', 72);
INSERT INTO `sc` VALUES ('2009000001', '7', 89);
INSERT INTO `sc` VALUES ('2009000001', '8', 81);
INSERT INTO `sc` VALUES ('2012011077', '1', 71);
INSERT INTO `sc` VALUES ('2012011077', '2', 66);

SET FOREIGN_KEY_CHECKS = 1;
