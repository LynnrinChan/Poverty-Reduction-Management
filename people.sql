/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : people

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 08/12/2021 09:14:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for people
-- ----------------------------
DROP TABLE IF EXISTS `people`;
CREATE TABLE `people` (
  `id` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `homepeople` varchar(255) DEFAULT NULL,
  `healthstatus` varchar(255) DEFAULT NULL,
  `edustatus` varchar(255) DEFAULT NULL,
  `whypoor` varchar(255) DEFAULT NULL,
  `adddate` varchar(255) DEFAULT NULL,
  `loginid` varchar(255) DEFAULT NULL,
  `poorusername` varchar(255) DEFAULT NULL,
  `poorpassword` varchar(255) DEFAULT NULL,
  `poorprojectid` varchar(255) DEFAULT NULL,
  `poormark` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of people
-- ----------------------------
BEGIN;
INSERT INTO `people` VALUES ('1', '湖南长沙', '3', '正常', '未知', '因病致贫', '2021-12-05', '1', 'user1', '123456', '1', 'true');
INSERT INTO `people` VALUES ('2', '湖南长沙', '3', '正常', '未知', '因病致贫', '2021-12-05', '1', 'user2', '123456', '1', 'true');
INSERT INTO `people` VALUES ('3', '湖南长沙', '4', '正常', '未知', '因病致贫', '2021-12-05', '1', 'user3', '123456', '1', 'true');
INSERT INTO `people` VALUES ('4', '湖南怀化', '3', '正常', '未知', '因病致贫', '2021-12-05', '1', 'user4', '123456', '1', 'true');
INSERT INTO `people` VALUES ('5', '湖南长沙', '2', '正常', '未知', '因病致贫', '2021-12-05', '1', 'user5', '123456', '1', 'true');
INSERT INTO `people` VALUES ('6', '湖南株洲', '1', '正常', '未知', '因病致贫', '2021-12-05', '1', 'user6', '123456', '1', 'true');
INSERT INTO `people` VALUES ('7', '湖南邵阳', '4', '正常', '未知', '因病致贫', '2021-12-05', '1', 'user7', '123456', '1', 'true');
INSERT INTO `people` VALUES ('8', '湖南吉首', '5', '正常', '未知', '因病致贫', '2021-12-05', '1', 'user8', '123456', '1', 'true');
COMMIT;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `projectid` varchar(255) DEFAULT NULL,
  `projectname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of project
-- ----------------------------
BEGIN;
INSERT INTO `project` VALUES ('1', '扶贫项目1');
INSERT INTO `project` VALUES ('2', '扶贫项目2');
INSERT INTO `project` VALUES ('3', '扶贫项目3');
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `type` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('baolong24', '645514fb6f4c63f46dc6075802b80bea', 1);
INSERT INTO `users` VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3', 2);
INSERT INTO `users` VALUES ('user', 'ee11cbb19052e40b07aac0ca060c23ee', 3);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
