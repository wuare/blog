/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1-3306
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : blog2

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 01/09/2021 17:22:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_addr
-- ----------------------------
DROP TABLE IF EXISTS `t_addr`;
CREATE TABLE `t_addr`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `articleid` int NULL DEFAULT NULL,
  `commentid` int NULL DEFAULT NULL,
  `replyid` int NULL DEFAULT NULL,
  `messageid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_addr
-- ----------------------------
INSERT INTO `t_addr` VALUES (1, '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1, 'admin', 'admin');

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `date` datetime NULL DEFAULT NULL,
  `lookNum` int NULL DEFAULT NULL,
  `likeNum` int NULL DEFAULT NULL,
  `imgUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hotState` int NULL DEFAULT NULL,
  `cid` int NULL DEFAULT NULL,
  `uid` int NULL DEFAULT NULL,
  `summary` varchar(3000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES (3, '测试', 'root', '<p>男孩儿当你感觉到自己累，自己压力大的时候，首先要反省一下自己，既然拼不了爹，就要努力拼自己。</p><p>女孩儿当你感觉自己委屈的时候努力回忆一下，自己是不是当初的选择是错误的。</p><p>虽然有些路不能回头，但是我们可以通过自己的调试，让自己脚下的路平坦一些。</p><p>婚姻是需要双方共同经营的，同时也是互利共赢的。很少有人把婚后的生活过得用如胶似漆来形容。如果你没有，也没有什么，学会像一个人那样生活。</p><p>要少抱怨把自己活成太阳，用你的光去照耀别人，但不要试图去改变别人，因为你能改变的只有你自己。</p><p>要学会沟通，不要一聊天就变成吵架和冷战。控制好自己的情绪，毕竟我们互不相欠。</p><p>其实男孩儿啊，你不结婚你也一样要努力生活！只要生活就会遇到工作上的种种烦恼。</p><p>作为一个女孩儿，不是结了婚就需要依赖别人，你该有的独立还要继续保持，因为越到最后你才发现所谓的安全感都是自己给自己的。</p><p><br/><br/>作者：感冒的梵高<br/>链接：https://www.jianshu.com/p/ea7a5021f6dc<br/>来源：简书<br/>著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。</p><p><br/></p>', '2021-09-01 17:08:13', 12, 1, '/static/articleimg/C047F5DBE3E840B1BD7CB222513E9BEE.jpg', 1, 1, 2, '男孩儿当你感觉到自己累，自己压力大的时候，首先要反省一下自己，既然拼不了爹，就要努力拼自己。女孩儿当你感觉自己委屈的时候努力回忆一下，自己是不是当初的选择是错误的。虽然有些路不能回头，但是我们可以通过自己的调试，让自己脚下的路平坦一些。婚姻是需要双方共同经营的，同时也是...');

-- ----------------------------
-- Table structure for t_article_addr
-- ----------------------------
DROP TABLE IF EXISTS `t_article_addr`;
CREATE TABLE `t_article_addr`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `articleid` int NULL DEFAULT NULL,
  `addrid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_article_addr
-- ----------------------------
INSERT INTO `t_article_addr` VALUES (1, 3, 1);

-- ----------------------------
-- Table structure for t_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_article_tag`;
CREATE TABLE `t_article_tag`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `tid` int NULL DEFAULT NULL,
  `aid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_article_tag
-- ----------------------------
INSERT INTO `t_article_tag` VALUES (1, 1, 3);

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `descrip` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES (1, '技术类', '技术类');

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `date` datetime NULL DEFAULT NULL,
  `aid` int NULL DEFAULT NULL,
  `warnNum` int NULL DEFAULT NULL,
  `likeNum` int NULL DEFAULT NULL,
  `uid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES (1, 'a', '2021-09-01 17:18:11', 3, 0, 0, NULL);
INSERT INTO `t_comment` VALUES (2, 'a', '2021-09-01 17:18:15', 3, 0, 0, NULL);
INSERT INTO `t_comment` VALUES (3, 'a', '2021-09-01 17:18:19', 3, 0, 0, NULL);

-- ----------------------------
-- Table structure for t_img
-- ----------------------------
DROP TABLE IF EXISTS `t_img`;
CREATE TABLE `t_img`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_img
-- ----------------------------
INSERT INTO `t_img` VALUES (1, '/static/userimg/B601D562B70B40A587B390C582810646.jpg');

-- ----------------------------
-- Table structure for t_link
-- ----------------------------
DROP TABLE IF EXISTS `t_link`;
CREATE TABLE `t_link`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `href` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_link
-- ----------------------------
INSERT INTO `t_link` VALUES (1, '老张的哲学', 'https://neters.club', 1);

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `uid` int NULL DEFAULT NULL,
  `warnNum` int NULL DEFAULT NULL,
  `likeNum` int NULL DEFAULT NULL,
  `date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_message
-- ----------------------------
INSERT INTO `t_message` VALUES (2, 'say something', 2, 0, 0, '2021-09-01 17:11:36');

-- ----------------------------
-- Table structure for t_reply
-- ----------------------------
DROP TABLE IF EXISTS `t_reply`;
CREATE TABLE `t_reply`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `date` datetime NULL DEFAULT NULL,
  `warnNum` int NULL DEFAULT NULL,
  `likeNum` int NULL DEFAULT NULL,
  `uid` int NULL DEFAULT NULL,
  `cid` int NULL DEFAULT NULL,
  `pid` int NULL DEFAULT NULL,
  `mid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_reply
-- ----------------------------
INSERT INTO `t_reply` VALUES (1, 'hello world', '2021-09-01 17:11:44', 0, 0, 2, NULL, NULL, 2);

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `descrip` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `aid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES (1, '情感', NULL, NULL);
INSERT INTO `t_tag` VALUES (2, 'aaaa', NULL, NULL);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `firstname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lastname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pubname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `site` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `descrip` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'wuare', '96e79218965eb72c92a549dd5a330112', NULL, 1, NULL, NULL, 'wuare', 'wuare', 'wuare', 'wuare', NULL, NULL);
INSERT INTO `t_user` VALUES (2, 'root', '96e79218965eb72c92a549dd5a330112', NULL, 2, NULL, '/static/userimg/466ECCAB3F3B44DAA9D476C81621C4FB.jpg', 'root', 'root', 'root', 'root', '', '');

SET FOREIGN_KEY_CHECKS = 1;
