/*
 Navicat Premium Data Transfer

 Source Server         : 本地链接
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : lagou

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 22/02/2023 20:24:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lagou_auth_code
-- ----------------------------
DROP TABLE IF EXISTS `lagou_auth_code`;
CREATE TABLE `lagou_auth_code`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '⾃增主键',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `code` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '验证码',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `expiretime` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for lagou_token
-- ----------------------------
DROP TABLE IF EXISTS `lagou_token`;
CREATE TABLE `lagou_token`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '⾃增主键',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱地址',
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '令牌',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for r_resume
-- ----------------------------
DROP TABLE IF EXISTS `r_resume`;
CREATE TABLE `r_resume`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出生日期',
  `workYear` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作年限',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目前状态',
  `resumeName` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简历名称',
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `headPic` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `isDel` int NULL DEFAULT NULL COMMENT '是否删除 默认值0-未删除 1-已删除',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '简历更新时间',
  `userId` int NULL DEFAULT NULL COMMENT '用户ID',
  `isDefault` int NULL DEFAULT NULL COMMENT '是否为默认简历 0-默认 1-非默认',
  `highestEducation` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '最高学历',
  `deliverNearByConfirm` int NULL DEFAULT 0 COMMENT '投递附件简历确认 0-需要确认 1-不需要确认',
  `refuseCount` int NOT NULL DEFAULT 0 COMMENT '简历被拒绝次数',
  `markCanInterviewCount` int NOT NULL DEFAULT 0 COMMENT '被标记为可面试次数',
  `haveNoticeInterCount` int NOT NULL DEFAULT 0 COMMENT '已通知面试次数',
  `oneWord` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '一句话介绍自己',
  `liveCity` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '居住城市',
  `resumeScore` int NULL DEFAULT NULL COMMENT '简历得分',
  `userIdentity` int NULL DEFAULT 0 COMMENT '用户身份1-学生 2-工人',
  `isOpenResume` int NULL DEFAULT 3 COMMENT '人才搜索-开放简历 0-关闭，1-打开，2-简历未达到投放标准被动关闭 3-从未设置过开放简历',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2195387 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
