/*
 Navicat Premium Data Transfer

 Source Server         : user
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : school_blog

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 07/12/2021 09:26:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_admin
-- ----------------------------
DROP TABLE IF EXISTS `blog_admin`;
CREATE TABLE `blog_admin`  (
  `a_id` int(20) NOT NULL AUTO_INCREMENT,
  `a_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `a_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`a_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_admin
-- ----------------------------
INSERT INTO `blog_admin` VALUES (1, 'admin', '7fef6171469e80d32c0559f88b377245');

-- ----------------------------
-- Table structure for blog_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article`  (
  `at_id` int(50) NOT NULL AUTO_INCREMENT,
  `u_id` int(20) NOT NULL,
  `at_date` datetime(0) NOT NULL,
  `at_title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `at_abstract` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `at_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `at_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`at_id`) USING BTREE,
  INDEX `u_id`(`u_id`) USING BTREE,
  CONSTRAINT `u_id` FOREIGN KEY (`u_id`) REFERENCES `blog_user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_article
-- ----------------------------
INSERT INTO `blog_article` VALUES (3, 1, '2021-12-03 14:39:38', '测试用例文章', '这是一篇测试用例', '这是测试的内容', '测试文章');
INSERT INTO `blog_article` VALUES (4, 1, '2021-12-03 14:56:03', '测试用例文章', '这是一篇测试用例', '这是测试的内容', '测试文章');
INSERT INTO `blog_article` VALUES (5, 1, '2021-12-03 14:56:54', '测试用例文章', '这是一篇测试用例', '这是测试的内容', '测试文章');
INSERT INTO `blog_article` VALUES (6, 2, '2021-12-03 14:57:23', '测试用例文章', '这是一篇测试用例', '这是测试的内容', '测试文章');
INSERT INTO `blog_article` VALUES (7, 2, '2021-12-03 14:57:34', '测试用例文章', '这是一篇测试用例', '这是测试的内容', '测试文章');
INSERT INTO `blog_article` VALUES (8, 2, '2021-12-03 14:58:21', '测试用例文章', '这是一篇测试用例', '无论在学生时代还是职场生活，但凡遇到数学问题，总是让一部分人很挠头。有时候很羡慕那些数学好的学霸，他们总能轻轻松松搞定一切数学问题，更重要的是还能将复杂的问题用最简单的话讲解清楚。而王赟就是这样一位学霸。', '测试文章');
INSERT INTO `blog_article` VALUES (9, 2, '2021-12-03 14:59:10', '测试用例文章', '这是一篇测试用例', '第一次认识王赟老师是在知乎上，当时看到他的问答，觉得清晰受用。后来发现他居然在图灵出书了，小编甚是激动呀！\n\n翻了两天，终于把这本书都看完了。虽然里面部分题目的知识点已经忘得差不多了，但是有些题目看到王老师的解题思路，忍不住拍大腿：“居然还能这么解！”看书过程中还不停地点头，“原来是这样”“我咋就没想到”“不错不错这次会了”......就这样沉醉了两天，看到最后一页的时候竟然有点忧伤，因为后边没有了，也确实没看够。\n\n就是这样一本小书，不那么厚，但是内容绝对会让你意犹未尽，并且不断发出“柳岸花明又一村”的感叹。如果你对数学有兴趣不妨来跟着作者的思路一起研究，保证你收获满满。\n\n如此有意思的一本书，我们来近距离了解一下吧~，但凡遇到数学问题，总是让一部分人很挠头。有时候很羡慕那些数学好的学霸，他们总能轻轻松松搞定一切数学问题，更重要的是还能将复杂的问题用最简单的话讲解清楚。而王赟就是这样一位学霸。', '测试文章');
INSERT INTO `blog_article` VALUES (10, 3, '2021-12-03 14:59:35', '测试用例文章', '这是一篇测试用例', '第一次认识王赟老师是在知乎上，当时看到他的问答，觉得清晰受用。后来发现他居然在图灵出书了，小编甚是激动呀！\n\n翻了两天，终于把这本书都看完了。虽然里面部分题目的知识点已经忘得差不多了，但是有些题目看到王老师的解题思路，忍不住拍大腿：“居然还能这么解！”看书过程中还不停地点头，“原来是这样”“我咋就没想到”“不错不错这次会了”......就这样沉醉了两天，看到最后一页的时候竟然有点忧伤，因为后边没有了，也确实没看够。\n\n就是这样一本小书，不那么厚，但是内容绝对会让你意犹未尽，并且不断发出“柳岸花明又一村”的感叹。如果你对数学有兴趣不妨来跟着作者的思路一起研究，保证你收获满满。\n\n如此有意思的一本书，我们来近距离了解一下吧~，但凡遇到数学问题，总是让一部分人很挠头。有时候很羡慕那些数学好的学霸，他们总能轻轻松松搞定一切数学问题，更重要的是还能将复杂的问题用最简单的话讲解清楚。而王赟就是这样一位学霸。', '测试文章');
INSERT INTO `blog_article` VALUES (11, 4, '2021-12-03 14:59:46', '测试用例文章', '这是一篇测试用例', '第一次认识王赟老师是在知乎上，当时看到他的问答，觉得清晰受用。后来发现他居然在图灵出书了，小编甚是激动呀！\n\n翻了两天，终于把这本书都看完了。虽然里面部分题目的知识点已经忘得差不多了，但是有些题目看到王老师的解题思路，忍不住拍大腿：“居然还能这么解！”看书过程中还不停地点头，“原来是这样”“我咋就没想到”“不错不错这次会了”......就这样沉醉了两天，看到最后一页的时候竟然有点忧伤，因为后边没有了，也确实没看够。\n\n就是这样一本小书，不那么厚，但是内容绝对会让你意犹未尽，并且不断发出“柳岸花明又一村”的感叹。如果你对数学有兴趣不妨来跟着作者的思路一起研究，保证你收获满满。\n\n如此有意思的一本书，我们来近距离了解一下吧~，但凡遇到数学问题，总是让一部分人很挠头。有时候很羡慕那些数学好的学霸，他们总能轻轻松松搞定一切数学问题，更重要的是还能将复杂的问题用最简单的话讲解清楚。而王赟就是这样一位学霸。', '测试文章');
INSERT INTO `blog_article` VALUES (12, 5, '2021-12-03 15:00:35', '测试用例文章', '这是一篇测试用例', '第一次认识王赟老师是在知乎上，当时看到他的问答，觉得清晰受用。后来发现他居然在图灵出书了，小编甚是激动呀！\n\n翻了两天，终于把这本书都看完了。虽然里面部分题目的知识点已经忘得差不多了，但是有些题目看到王老师的解题思路，忍不住拍大腿：“居然还能这么解！”看书过程中还不停地点头，“原来是这样”“我咋就没想到”“不错不错这次会了”......就这样沉醉了两天，看到最后一页的时候竟然有点忧伤，因为后边没有了，也确实没看够。\n\n就是这样一本小书，不那么厚，但是内容绝对会让你意犹未尽，并且不断发出“柳岸花明又一村”的感叹。如果你对数学有兴趣不妨来跟着作者的思路一起研究，保证你收获满满。\n\n如此有意思的一本书，我们来近距离了解一下吧~，但凡遇到数学问题，总是让一部分人很挠头。有时候很羡慕那些数学好的学霸，他们总能轻轻松松搞定一切数学问题，更重要的是还能将复杂的问题用最简单的话讲解清楚。而王赟就是这样一位学霸。', '测试文章');
INSERT INTO `blog_article` VALUES (13, 6, '2021-12-03 15:00:52', '测试用例文章', '这是一篇测试用例', '第一次认识王赟老师是在知乎上，当时看到他的问答，觉得清晰受用。后来发现他居然在图灵出书了，小编甚是激动呀！\n\n翻了两天，终于把这本书都看完了。虽然里面部分题目的知识点已经忘得差不多了，但是有些题目看到王老师的解题思路，忍不住拍大腿：“居然还能这么解！”看书过程中还不停地点头，“原来是这样”“我咋就没想到”“不错不错这次会了”......就这样沉醉了两天，看到最后一页的时候竟然有点忧伤，因为后边没有了，也确实没看够。\n\n就是这样一本小书，不那么厚，但是内容绝对会让你意犹未尽，并且不断发出“柳岸花明又一村”的感叹。如果你对数学有兴趣不妨来跟着作者的思路一起研究，保证你收获满满。\n\n如此有意思的一本书，我们来近距离了解一下吧~，但凡遇到数学问题，总是让一部分人很挠头。有时候很羡慕那些数学好的学霸，他们总能轻轻松松搞定一切数学问题，更重要的是还能将复杂的问题用最简单的话讲解清楚。而王赟就是这样一位学霸。', '测试文章');
INSERT INTO `blog_article` VALUES (14, 7, '2021-12-03 15:01:06', '测试用例文章', '这是一篇测试用例', '第一次认识王赟老师是在知乎上，当时看到他的问答，觉得清晰受用。后来发现他居然在图灵出书了，小编甚是激动呀！\n\n翻了两天，终于把这本书都看完了。虽然里面部分题目的知识点已经忘得差不多了，但是有些题目看到王老师的解题思路，忍不住拍大腿：“居然还能这么解！”看书过程中还不停地点头，“原来是这样”“我咋就没想到”“不错不错这次会了”......就这样沉醉了两天，看到最后一页的时候竟然有点忧伤，因为后边没有了，也确实没看够。\n\n就是这样一本小书，不那么厚，但是内容绝对会让你意犹未尽，并且不断发出“柳岸花明又一村”的感叹。如果你对数学有兴趣不妨来跟着作者的思路一起研究，保证你收获满满。\n\n如此有意思的一本书，我们来近距离了解一下吧~，但凡遇到数学问题，总是让一部分人很挠头。有时候很羡慕那些数学好的学霸，他们总能轻轻松松搞定一切数学问题，更重要的是还能将复杂的问题用最简单的话讲解清楚。而王赟就是这样一位学霸。', '测试文章');
INSERT INTO `blog_article` VALUES (15, 8, '2021-12-03 15:01:19', '测试用例文章', '这是一篇测试用例', '第一次认识王赟老师是在知乎上，当时看到他的问答，觉得清晰受用。后来发现他居然在图灵出书了，小编甚是激动呀！\n\n翻了两天，终于把这本书都看完了。虽然里面部分题目的知识点已经忘得差不多了，但是有些题目看到王老师的解题思路，忍不住拍大腿：“居然还能这么解！”看书过程中还不停地点头，“原来是这样”“我咋就没想到”“不错不错这次会了”......就这样沉醉了两天，看到最后一页的时候竟然有点忧伤，因为后边没有了，也确实没看够。\n\n就是这样一本小书，不那么厚，但是内容绝对会让你意犹未尽，并且不断发出“柳岸花明又一村”的感叹。如果你对数学有兴趣不妨来跟着作者的思路一起研究，保证你收获满满。\n\n如此有意思的一本书，我们来近距离了解一下吧~，但凡遇到数学问题，总是让一部分人很挠头。有时候很羡慕那些数学好的学霸，他们总能轻轻松松搞定一切数学问题，更重要的是还能将复杂的问题用最简单的话讲解清楚。而王赟就是这样一位学霸。', '测试文章');
INSERT INTO `blog_article` VALUES (16, 9, '2021-12-03 15:01:33', '测试用例文章', '这是一篇测试用例', '第一次认识王赟老师是在知乎上，当时看到他的问答，觉得清晰受用。后来发现他居然在图灵出书了，小编甚是激动呀！\n\n翻了两天，终于把这本书都看完了。虽然里面部分题目的知识点已经忘得差不多了，但是有些题目看到王老师的解题思路，忍不住拍大腿：“居然还能这么解！”看书过程中还不停地点头，“原来是这样”“我咋就没想到”“不错不错这次会了”......就这样沉醉了两天，看到最后一页的时候竟然有点忧伤，因为后边没有了，也确实没看够。\n\n就是这样一本小书，不那么厚，但是内容绝对会让你意犹未尽，并且不断发出“柳岸花明又一村”的感叹。如果你对数学有兴趣不妨来跟着作者的思路一起研究，保证你收获满满。\n\n如此有意思的一本书，我们来近距离了解一下吧~，但凡遇到数学问题，总是让一部分人很挠头。有时候很羡慕那些数学好的学霸，他们总能轻轻松松搞定一切数学问题，更重要的是还能将复杂的问题用最简单的话讲解清楚。而王赟就是这样一位学霸。', '测试文章');

-- ----------------------------
-- Table structure for blog_article_info
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_info`;
CREATE TABLE `blog_article_info`  (
  `at_id` int(50) NOT NULL,
  `at_like_u_id` int(20) NOT NULL,
  `at_collection_u_id` int(20) NOT NULL,
  INDEX `at_info`(`at_id`, `at_like_u_id`, `at_collection_u_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_article_info
-- ----------------------------
INSERT INTO `blog_article_info` VALUES (1, 1, 1);
INSERT INTO `blog_article_info` VALUES (2, 1, 3);
INSERT INTO `blog_article_info` VALUES (2, 3, 3);
INSERT INTO `blog_article_info` VALUES (2, 3, 3);
INSERT INTO `blog_article_info` VALUES (3, 1, 3);
INSERT INTO `blog_article_info` VALUES (4, 1, 1);
INSERT INTO `blog_article_info` VALUES (5, 1, 1);
INSERT INTO `blog_article_info` VALUES (5, 1, 1);
INSERT INTO `blog_article_info` VALUES (5, 1, 1);
INSERT INTO `blog_article_info` VALUES (5, 2, 5);
INSERT INTO `blog_article_info` VALUES (5, 3, 3);
INSERT INTO `blog_article_info` VALUES (6, 1, 1);

-- ----------------------------
-- Table structure for blog_comments
-- ----------------------------
DROP TABLE IF EXISTS `blog_comments`;
CREATE TABLE `blog_comments`  (
  `c_id` int(20) NOT NULL AUTO_INCREMENT,
  `at_id` int(50) NOT NULL,
  `c_date` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `c_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`c_id`) USING BTREE,
  INDEX `at_id`(`at_id`) USING BTREE,
  CONSTRAINT `at_id` FOREIGN KEY (`at_id`) REFERENCES `blog_article` (`at_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blog_img
-- ----------------------------
DROP TABLE IF EXISTS `blog_img`;
CREATE TABLE `blog_img`  (
  `i_id` int(255) NOT NULL,
  `i_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `u_id` int(50) NOT NULL,
  PRIMARY KEY (`i_id`) USING BTREE,
  INDEX `u_id`(`u_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blog_user
-- ----------------------------
DROP TABLE IF EXISTS `blog_user`;
CREATE TABLE `blog_user`  (
  `u_id` int(20) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `u_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `u_sex` varchar(4) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `u_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `u_email` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `u_head_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`u_id`) USING BTREE,
  INDEX `u_find`(`u_id`, `u_phone`, `u_email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_user
-- ----------------------------
INSERT INTO `blog_user` VALUES (1, '刘晓虎', '3d9188577cc9bfe9291ac66b5cc872b7', '男', '19881661160', '1736124626@qq.com', NULL);
INSERT INTO `blog_user` VALUES (2, '兰茂豪', '3d9188577cc9bfe9291ac66b5cc872b7', '男', '12345678910', '1234567891@qq.com', NULL);
INSERT INTO `blog_user` VALUES (3, '谭海蓉', '3d9188577cc9bfe9291ac66b5cc872b7', '女', '12345678910', '1234567891@qq.com', NULL);
INSERT INTO `blog_user` VALUES (4, '田晓', '3d9188577cc9bfe9291ac66b5cc872b7', '男', '12346578910', '1234567891@qq.com', NULL);
INSERT INTO `blog_user` VALUES (5, '谈偲瑶', '3d9188577cc9bfe9291ac66b5cc872b7', '女', '12345698710', NULL, NULL);
INSERT INTO `blog_user` VALUES (6, '何谦', '3d9188577cc9bfe9291ac66b5cc872b7', '男', '12346578910', NULL, NULL);
INSERT INTO `blog_user` VALUES (7, '程开荣', '3d9188577cc9bfe9291ac66b5cc872b7', '男', '12234567891', NULL, NULL);
INSERT INTO `blog_user` VALUES (8, '蒋健全', '3d9188577cc9bfe9291ac66b5cc872b7', '男', '12346578915', NULL, NULL);
INSERT INTO `blog_user` VALUES (9, '段治孝', '3d9188577cc9bfe9291ac66b5cc872b7', '男', '12345678915', NULL, NULL);
INSERT INTO `blog_user` VALUES (10, '刘晓虎', '3d9188577cc9bfe9291ac66b5cc872b7', '男', '19881661160', '1736124626@qq.com', NULL);

SET FOREIGN_KEY_CHECKS = 1;
