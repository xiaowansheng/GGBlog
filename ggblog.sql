-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: ggblog
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `log_error`
--

DROP TABLE IF EXISTS `log_error`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `log_error` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '异常日志ID',
  `user_auth_id` int NOT NULL DEFAULT '0' COMMENT '操作用户ID',
  `user_name` varchar(30) NOT NULL COMMENT '操作用户名',
  `version` varchar(128) NOT NULL COMMENT '系统版本',
  `request_url` varchar(255) NOT NULL COMMENT '请求的地址',
  `request_method` varchar(10) NOT NULL COMMENT '请求方法',
  `request_param` longtext NOT NULL COMMENT '请求参数',
  `module` varchar(50) NOT NULL COMMENT '请求模块',
  `calling_method` varchar(255) NOT NULL COMMENT '调用方法',
  `error_name` varchar(255) NOT NULL COMMENT '异常名称',
  `error_message` text NOT NULL COMMENT '异常信息',
  `ip_address` varchar(128) NOT NULL COMMENT 'IP地址',
  `ip_source` varchar(150) NOT NULL COMMENT 'ip所在地',
  `device` varchar(50) NOT NULL DEFAULT '未知设备' COMMENT '设备名称',
  `browser` varchar(50) NOT NULL DEFAULT '未知浏览器' COMMENT '浏览器类型',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=230 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统异常错误日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `log_operation`
--

DROP TABLE IF EXISTS `log_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `log_operation` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '操作日志ID',
  `user_auth_id` int NOT NULL COMMENT '操作用户ID',
  `user_name` varchar(50) NOT NULL COMMENT '操作用户昵称',
  `version` varchar(128) NOT NULL COMMENT '项目版本',
  `request_url` varchar(255) NOT NULL COMMENT '请求地址',
  `module` varchar(50) NOT NULL COMMENT '操作模块',
  `calling_method` varchar(255) NOT NULL COMMENT '调用方法',
  `type` varchar(20) NOT NULL COMMENT '操作类型（新增、修改...）',
  `description` varchar(100) NOT NULL COMMENT '操作描述信息',
  `request_method` varchar(10) NOT NULL COMMENT '请求方法',
  `request_param` longtext NOT NULL COMMENT '请求参数',
  `response_data` longtext NOT NULL COMMENT '响应参数',
  `elapsed_time` bigint NOT NULL COMMENT '操作执行耗时毫秒数',
  `ip_address` varchar(128) NOT NULL COMMENT '请求IP',
  `ip_source` varchar(150) NOT NULL COMMENT 'ip归属地',
  `device` varchar(50) NOT NULL DEFAULT '未知设备' COMMENT '设备名称',
  `browser` varchar(50) NOT NULL DEFAULT '未知浏览器' COMMENT '浏览器名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否被删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=663 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `system_config`
--

DROP TABLE IF EXISTS `system_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_config` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '配置ID',
  `name` varchar(50) NOT NULL COMMENT '配置参数名',
  `label` varchar(50) NOT NULL COMMENT '参数名称',
  `value` varchar(8192) NOT NULL COMMENT '配置参数值',
  `description` varchar(50) DEFAULT NULL COMMENT '配置描述信息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='网站配置信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `system_menu`
--

DROP TABLE IF EXISTS `system_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `title` varchar(50) NOT NULL COMMENT '菜单标题',
  `icon` varchar(255) NOT NULL COMMENT '图标',
  `redirect` varchar(100) DEFAULT NULL COMMENT '路由重定向',
  `path` varchar(100) NOT NULL COMMENT '路由地址',
  `component` varchar(200) DEFAULT NULL COMMENT '组件路径',
  `hidden` tinyint(1) NOT NULL DEFAULT '0' COMMENT '隐藏菜单（0展示1隐藏）',
  `sort` tinyint NOT NULL DEFAULT '0' COMMENT '排序',
  `parent_id` int NOT NULL DEFAULT '0' COMMENT '父类ID',
  `perms` varchar(50) DEFAULT NULL COMMENT '权限标识',
  `description` varchar(100) DEFAULT NULL COMMENT '描述信息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单目录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `system_resource`
--

DROP TABLE IF EXISTS `system_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_resource` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '资源菜单ID',
  `name` varchar(50) NOT NULL COMMENT '资源名称',
  `request_method` varchar(10) DEFAULT NULL COMMENT '请求方法',
  `path` varchar(255) DEFAULT NULL COMMENT '资源访问路径',
  `open` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否开放资源（1开放0不开放）',
  `parent_id` int NOT NULL DEFAULT '0' COMMENT '父类ID',
  `perms` varchar(50) DEFAULT NULL COMMENT '权限标识',
  `description` varchar(100) DEFAULT NULL COMMENT '资源描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资源菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_album`
--

DROP TABLE IF EXISTS `t_album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_album` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '相册ID',
  `user_auth_id` int NOT NULL COMMENT '相册创建人账号ID',
  `name` varchar(50) NOT NULL COMMENT '相册名称',
  `description` varchar(200) NOT NULL COMMENT '相册描述',
  `cover` varchar(255) DEFAULT NULL COMMENT '相册封面',
  `status` varchar(20) NOT NULL COMMENT '相册状态（1公开2私密）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='相册';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_article`
--

DROP TABLE IF EXISTS `t_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_article` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '文章编号',
  `user_auth_id` int NOT NULL COMMENT '作者ID（与账号表关联）',
  `category_id` int DEFAULT NULL COMMENT '分类ID',
  `title` varchar(150) NOT NULL COMMENT '文章标题',
  `cover` varchar(512) DEFAULT NULL COMMENT '文章封面（需赋默认值）',
  `content` longtext NOT NULL COMMENT '文章内容',
  `type` varchar(20) NOT NULL COMMENT '文章类型（1原创2转载3翻译4引用）',
  `original_author` varchar(30) DEFAULT NULL COMMENT '原文作者',
  `original_title` varchar(50) DEFAULT NULL COMMENT '原文标题',
  `original_url` varchar(512) DEFAULT NULL COMMENT '原文链接',
  `note` varchar(300) DEFAULT NULL COMMENT '文章备注信息',
  `top` tinyint(1) NOT NULL DEFAULT '0' COMMENT '文章是否置顶显示',
  `status` varchar(20) NOT NULL COMMENT '文章状态（1展示2私密3评论可见）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='博客文章';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_article_tag`
--

DROP TABLE IF EXISTS `t_article_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_article_tag` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '文章和标签关联ID',
  `article_id` int NOT NULL COMMENT '文章ID',
  `tag_id` int NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章对应标签';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_category`
--

DROP TABLE IF EXISTS `t_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_category` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `name` varchar(30) NOT NULL COMMENT '类别名',
  `description` varchar(100) DEFAULT NULL COMMENT '分类描述',
  `hidden` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否隐藏该分类',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否被删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章类别';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_comment`
--

DROP TABLE IF EXISTS `t_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_comment` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `user_auth_id` int DEFAULT NULL COMMENT '评论人账号',
  `topic_type` varchar(20) NOT NULL COMMENT '评论类型（1文章2说说3写作4友联）',
  `topic_id` int DEFAULT NULL COMMENT '评论的主题ID（文章ID说说ID...）',
  `content` text NOT NULL COMMENT '评论内容',
  `images` varchar(2048) DEFAULT '[]' COMMENT '图片',
  `ip_address` varchar(50) NOT NULL COMMENT 'ip地址',
  `ip_source` varchar(100) NOT NULL COMMENT 'ip所在地',
  `device` varchar(50) NOT NULL DEFAULT '未知设备' COMMENT '使用设备',
  `browser` varchar(50) NOT NULL DEFAULT '未知浏览器' COMMENT '使用浏览器',
  `point` point DEFAULT NULL COMMENT '坐标',
  `location` varchar(100) DEFAULT NULL COMMENT '坐标所在位置',
  `root_id` int NOT NULL DEFAULT '0' COMMENT '根评论ID',
  `parent_id` int NOT NULL DEFAULT '0',
  `type` varchar(20) NOT NULL COMMENT '评论类型(1登录评论2游客评论3匿名评论)',
  `nickname` varchar(30) DEFAULT NULL COMMENT '游客别名',
  `email` varchar(128) DEFAULT NULL COMMENT '游客邮箱',
  `qq` varchar(15) DEFAULT NULL COMMENT '游客QQ号',
  `hidden` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否隐藏评论',
  `review` tinyint(1) NOT NULL DEFAULT '0' COMMENT '审核情况（0未知1通过-1未通过）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_friend`
--

DROP TABLE IF EXISTS `t_friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_friend` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '友情链接ID',
  `userAuthId` int NOT NULL COMMENT '提交的用户编号',
  `name` varchar(30) NOT NULL COMMENT '网站名称',
  `icon` varchar(255) NOT NULL COMMENT '网站图标链接',
  `url` varchar(255) NOT NULL COMMENT '网站地址',
  `author` varchar(30) NOT NULL DEFAULT '【未填写】' COMMENT '网站作者',
  `introduction` varchar(200) NOT NULL COMMENT '网站介绍',
  `review` tinyint(1) NOT NULL DEFAULT '0' COMMENT '审核状态（0待审核1通过-1未通过）',
  `hidden` tinyint(1) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='友情链接';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_guestbook`
--

DROP TABLE IF EXISTS `t_guestbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_guestbook` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '留言ID',
  `user_auth_id` int DEFAULT NULL COMMENT '留言账号ID',
  `content` varchar(1024) NOT NULL COMMENT '留言内容',
  `images` varchar(2048) DEFAULT '[]' COMMENT '图片',
  `ip_address` varchar(50) NOT NULL COMMENT 'ip地址',
  `ip_source` varchar(100) NOT NULL COMMENT 'ip所在地',
  `device` varchar(50) NOT NULL DEFAULT '未知设备' COMMENT '使用设备',
  `browser` varchar(50) NOT NULL DEFAULT '未知浏览器' COMMENT '使用浏览器',
  `point` point DEFAULT NULL COMMENT '坐标',
  `location` varchar(100) DEFAULT NULL COMMENT '位置',
  `type` varchar(20) NOT NULL COMMENT '留言类型(1登录留言2游客留言3匿名留言)',
  `nickname` varchar(30) DEFAULT NULL COMMENT '游客别名',
  `email` varchar(100) DEFAULT NULL COMMENT '游客邮箱',
  `qq` varchar(15) DEFAULT NULL COMMENT '游客QQ号',
  `hidden` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否隐藏留言',
  `review` tinyint(1) NOT NULL DEFAULT '0' COMMENT '审核状态（0未审核）',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='留言簿';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_login_log`
--

DROP TABLE IF EXISTS `t_login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_login_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_auth_id` int NOT NULL COMMENT '用户账号',
  `ip_address` varchar(50) NOT NULL COMMENT 'ip地址',
  `ip_source` varchar(100) NOT NULL COMMENT 'ip来源',
  `device` varchar(50) NOT NULL DEFAULT '未知设备' COMMENT '设备名称',
  `browser` varchar(45) NOT NULL COMMENT '浏览器类型',
  `point` point DEFAULT NULL COMMENT '坐标',
  `location` varchar(100) DEFAULT NULL COMMENT '坐标所在位置',
  `create_time` datetime NOT NULL COMMENT '登录时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='登录日志，记录用户的登录信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_page_view`
--

DROP TABLE IF EXISTS `t_page_view`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_page_view` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '访问量ID',
  `count` bigint NOT NULL DEFAULT '0' COMMENT '访问量',
  `view_type` varchar(20) NOT NULL COMMENT '访问量类型（1网站访问量2博客文章访问量3说说访问量4写作文章访问量5相册访问量6友链访问量....）',
  `view_id` int DEFAULT NULL COMMENT '不同访问类型的对应表ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='访问量';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_picture`
--

DROP TABLE IF EXISTS `t_picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_picture` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '图片ID',
  `user_auth_id` int NOT NULL COMMENT '图片上传人账号ID',
  `album_id` int NOT NULL COMMENT '相册ID',
  `name` varchar(100) NOT NULL COMMENT '图片名称',
  `description` varchar(100) DEFAULT NULL COMMENT '图片描述',
  `url` varchar(512) DEFAULT NULL COMMENT '图片地址',
  `source` varchar(20) DEFAULT NULL COMMENT '图片来源（0未知1原创2二创3转载）',
  `status` varchar(20) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='图片';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户角色ID',
  `name` varchar(20) NOT NULL COMMENT '角色名称',
  `label` varchar(20) NOT NULL COMMENT '角色标签',
  `description` varchar(100) DEFAULT NULL COMMENT '角色详情介绍',
  `disable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_label` (`label`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_role_menu`
--

DROP TABLE IF EXISTS `t_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role_menu` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '角色菜单ID',
  `role_id` int NOT NULL COMMENT '角色ID',
  `menu_id` int NOT NULL COMMENT '系统菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=275 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_role_resource`
--

DROP TABLE IF EXISTS `t_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role_resource` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '角色资源ID',
  `role_id` int NOT NULL COMMENT '角色ID',
  `resource_id` int NOT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=786 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色资源';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_tag`
--

DROP TABLE IF EXISTS `t_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_tag` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '文章标签ID',
  `name` varchar(30) NOT NULL COMMENT '标签名称',
  `description` varchar(100) DEFAULT NULL,
  `hidden` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否隐藏该标签',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章标签';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_talk`
--

DROP TABLE IF EXISTS `t_talk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_talk` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '说说ID',
  `user_auth_id` int NOT NULL COMMENT '发表人账号ID',
  `content` varchar(2048) NOT NULL COMMENT '说说内容',
  `images` varchar(4096) NOT NULL DEFAULT '[]' COMMENT '图片',
  `status` varchar(20) NOT NULL COMMENT '状态（1显示2私密）',
  `top` tinyint(1) NOT NULL DEFAULT '0' COMMENT '置顶（1置顶0不置顶）',
  `ip_address` varchar(100) NOT NULL COMMENT 'ip地址',
  `ip_source` varchar(100) NOT NULL COMMENT 'ip所在地',
  `device` varchar(50) NOT NULL DEFAULT '未知设备' COMMENT '使用设备',
  `browser` varchar(50) NOT NULL DEFAULT '未知浏览器' COMMENT '使用浏览器',
  `point` point DEFAULT NULL COMMENT '坐标',
  `location` varchar(100) DEFAULT NULL COMMENT '位置',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='说说';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_user_auth`
--

DROP TABLE IF EXISTS `t_user_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_auth` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户账号ID',
  `user_info_id` int NOT NULL COMMENT '用户信息ID',
  `username` varchar(50) NOT NULL COMMENT '登录用户名（一般是用户信息的邮箱）',
  `password` varchar(128) NOT NULL,
  `login_type` varchar(20) NOT NULL COMMENT '登录方式（如邮箱、QQ）',
  `third_party_id` varchar(128) DEFAULT NULL COMMENT '第三方登录id',
  `third_party_profile` varchar(512) DEFAULT NULL COMMENT '第三方登录信息',
  `disable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户是否禁用',
  `ip_address_signup` varchar(50) NOT NULL COMMENT '注册时ip',
  `ip_source_signup` varchar(100) NOT NULL COMMENT '注册ip所在地',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COMMENT='用户账号';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_user_info`
--

DROP TABLE IF EXISTS `t_user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `qq` varchar(11) DEFAULT NULL,
  `nickname` varchar(50) NOT NULL DEFAULT '【无名氏】' COMMENT '用户名',
  `avatar` varchar(250) DEFAULT NULL COMMENT '个人头像',
  `signature` varchar(100) DEFAULT NULL COMMENT '个人签名',
  `website` varchar(100) DEFAULT NULL COMMENT '个人网站',
  `introduction` varchar(250) DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_user_role`
--

DROP TABLE IF EXISTS `t_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户账号角色ID',
  `user_auth_id` int NOT NULL COMMENT '用户账号ID',
  `role_id` int NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户账号对应角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_visitor`
--

DROP TABLE IF EXISTS `t_visitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_visitor` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '访客信息ID',
  `uuid` varchar(256) NOT NULL COMMENT '标识唯一的访客',
  `view_type` varchar(20) NOT NULL COMMENT '访问页面类型',
  `view_id` int DEFAULT NULL COMMENT '访问内容id',
  `ip_address` varchar(100) NOT NULL COMMENT 'ip地址',
  `ip_source` varchar(100) NOT NULL COMMENT 'ip所在地',
  `device` varchar(50) NOT NULL DEFAULT '未知设备' COMMENT '设备名称',
  `browser` varchar(50) NOT NULL DEFAULT '未知浏览器' COMMENT '浏览器名称',
  `point` point DEFAULT NULL COMMENT '坐标',
  `location` varchar(100) DEFAULT NULL COMMENT '位置',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid_UNIQUE` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='访客信息';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-15  3:12:46
