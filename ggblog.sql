-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: ggblog2
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
  `user_auth_id` int NOT NULL COMMENT '操作用户ID',
  `user_name` varchar(30) NOT NULL COMMENT '操作用户名',
  `version` varchar(128) NOT NULL COMMENT '系统版本',
  `request_url` varchar(255) NOT NULL COMMENT '请求地址',
  `request_method` varchar(10) NOT NULL DEFAULT 'GET' COMMENT '请求参数',
  `request_param` longtext NOT NULL COMMENT '请求参数',
  `module` varchar(50) NOT NULL,
  `calling_method` varchar(255) NOT NULL COMMENT '调用方法',
  `error_name` varchar(255) NOT NULL COMMENT '异常名称',
  `error_message` text NOT NULL COMMENT '异常信息',
  `ip_address` varchar(128) NOT NULL COMMENT 'IP地址',
  `ip_source` varchar(150) NOT NULL COMMENT 'ip所在地',
  `device` varchar(50) NOT NULL DEFAULT '未知设备',
  `browser` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统异常错误日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_error`
--

LOCK TABLES `log_error` WRITE;
/*!40000 ALTER TABLE `log_error` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_error` ENABLE KEYS */;
UNLOCK TABLES;

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
  `module` varchar(20) NOT NULL COMMENT '操作模块',
  `calling_method` varchar(255) NOT NULL COMMENT '调用的方法',
  `type` varchar(20) NOT NULL COMMENT '操作类型（新增、修改...）',
  `description` varchar(100) NOT NULL COMMENT '操作描述信息',
  `request_method` varchar(10) NOT NULL COMMENT '请求方法',
  `request_param` longtext NOT NULL COMMENT '请求参数',
  `response_data` longtext NOT NULL COMMENT '响应参数',
  `elapsed_time` bigint NOT NULL COMMENT '操作执行耗时毫秒数',
  `ip_address` varchar(128) NOT NULL COMMENT '请求IP',
  `ip_source` varchar(150) NOT NULL COMMENT 'ip归属地',
  `device` varchar(50) NOT NULL DEFAULT '未知设备',
  `browser` varchar(50) NOT NULL DEFAULT '未知浏览器',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否被删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_operation`
--

LOCK TABLES `log_operation` WRITE;
/*!40000 ALTER TABLE `log_operation` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_config`
--

DROP TABLE IF EXISTS `system_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_config` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '配置ID',
  `name` varchar(50) NOT NULL COMMENT '配置参数名',
  `label` varchar(50) NOT NULL COMMENT '配置参数显示名称',
  `value` varchar(8192) NOT NULL COMMENT '配置参数值',
  `description` varchar(50) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='网站配置信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_config`
--

LOCK TABLES `system_config` WRITE;
/*!40000 ALTER TABLE `system_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_config` ENABLE KEYS */;
UNLOCK TABLES;

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
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单目录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_menu`
--

LOCK TABLES `system_menu` WRITE;
/*!40000 ALTER TABLE `system_menu` DISABLE KEYS */;
INSERT INTO `system_menu` VALUES (1,'test','测试菜单','fa:align-justify','','/test/test1','',1,0,0,NULL,'','2023-08-28 11:19:30','2023-08-30 14:49:21',0),(2,'test11','测试菜单11','ep:arrow-up','','/test11','test/test11/index',1,0,1,NULL,'','2023-08-28 11:21:00','2023-09-10 01:02:25',1),(3,'Permission','权限管理','ep:lollipop','/permission/role','/permission','',0,6,0,NULL,'','2023-08-28 14:37:47','2023-09-08 09:48:17',0),(4,'Role','角色管理','ep:stamp','','/permission/role','permission/role/index',0,1,3,NULL,'','2023-08-28 14:39:27','2023-09-04 15:43:03',0),(5,'Menu','菜单管理','ep:grid','','/permission/menu','permission/menu/index',0,2,3,NULL,'','2023-08-28 14:51:19','2023-09-04 15:43:15',0),(6,'Resource','资源管理','ep:position','','/permission/resource','permission/resource/index',0,3,3,NULL,'','2023-08-28 14:53:08','2023-09-04 15:43:23',0),(7,'Log','日志管理','ep:tickets','/log/operation','/log','',0,8,0,NULL,'','2023-08-28 19:31:15','2023-08-28 19:31:15',0),(8,'OperationLog','操作日志','ep:office-building','','/log/operation','log/operation/index',0,1,7,NULL,'','2023-08-28 19:32:24','2023-09-04 15:44:36',0),(9,'ErrorLog','异常日志','ep:failed','','/log/error','log/error/index',0,2,7,NULL,'','2023-08-28 19:33:23','2023-09-04 15:44:50',0),(10,'SystemSetting','系统设置','ep:setting','/system/mainpage','/system','',0,7,0,NULL,'','2023-08-29 15:44:23','2023-09-04 15:45:42',0),(11,'MainPage','主页菜单','ep:house','','/system/mainpage','settings/mainpage/index',0,1,10,NULL,'','2023-08-29 15:47:42','2023-09-04 15:43:57',0),(12,'WebsiteInformation','网站信息','ep:info-filled','','/system/website','settings/website/index',0,2,10,NULL,'','2023-08-30 00:06:35','2023-09-04 15:44:03',0),(13,'Author','作者信息','fa:user-circle','','/system/author','settings/author/index',0,3,10,NULL,'','2023-08-30 00:10:47','2023-09-04 15:44:09',0),(14,'Friend','友链管理','fa-solid:user-friends','','/system/friend','settings/friend/list/index',0,4,10,NULL,'','2023-08-30 00:11:43','2023-09-04 15:44:24',0),(15,'Config','网站配置','ep:set-up','','/system/config','settings/config/index',0,5,10,NULL,'','2023-08-30 00:12:52','2023-09-04 15:44:17',0),(16,'About','关于作者','fa-solid:umbrella','','/system/about','settings/about/index',0,6,10,NULL,'','2023-08-30 00:20:45','2023-09-04 15:44:28',0),(17,'User','用户管理','fa-solid:house-user','/user/list','/user','',0,5,0,NULL,'','2023-08-30 22:49:37','2023-09-08 09:48:08',0),(18,'UserList','用户列表','fa-solid:users','','/user/list','user/list/index',0,1,17,NULL,'','2023-08-30 22:57:33','2023-08-30 22:57:33',0),(19,'LoginLog','登录日志','fa-solid:blog','','/user/login/log','user/log/index',0,2,17,NULL,'','2023-08-30 22:59:26','2023-09-04 15:42:40',0),(20,'Message','消息管理','ep:message-box','/message/comment','/message','',0,3,0,NULL,'','2023-08-30 23:00:19','2023-09-08 09:47:49',0),(21,'Comment','评论信息','ep:comment','','/message/comment','message/comment/index',0,1,20,NULL,'','2023-08-30 23:01:18','2023-09-04 15:43:37',0),(22,'Guestbook','留言信息','ep:message','','/message/guestbook','message/guestbook/index',0,2,20,NULL,'','2023-08-30 23:04:53','2023-09-07 16:14:08',0),(23,'Album','相册管理','fa-solid:photo-video','','/album','',0,4,0,NULL,'','2023-08-30 23:07:13','2023-09-08 09:48:02',0),(24,'AlbumList','相册列表','ep:picture-filled','','/album/list','album/list/index',0,1,23,NULL,'','2023-08-30 23:08:03','2023-08-30 23:08:03',0),(25,'RecycleBin','回收站','ep:milk-tea','','/recyclebin','album/recycle/index',1,2,23,NULL,'','2023-08-30 23:10:35','2023-09-07 10:10:11',0),(26,'Talk','说说管理','ep:tickets','','/talk','',0,2,0,NULL,'','2023-08-30 23:12:26','2023-08-30 23:12:26',0),(27,'TalkPublish','发布说说','fa-solid:smile-beam','','/talk/publish','talk/publish/index',0,1,26,NULL,'','2023-08-30 23:14:46','2023-09-04 14:26:27',0),(28,'TalkList','说说列表','fa:list-ol','','/talk/list','talk/list/index',0,2,26,NULL,'','2023-08-30 23:15:31','2023-08-30 23:15:31',0),(29,'Article','文章管理','fa:wpforms','','/article','',0,1,0,NULL,'','2023-08-30 23:17:40','2023-08-30 23:17:40',0),(30,'Markdown发布','Markdown','fa-solid:pencil-ruler','','/article/markdown','article/markdown/index',0,1,29,NULL,'','2023-08-30 23:20:01','2023-09-17 18:37:38',0),(31,'ArticleList','文章列表','fa-solid:list-alt','','/article/list','article/list/index',0,3,29,NULL,'','2023-08-30 23:20:45','2023-08-30 23:20:45',0),(32,'Category','分类','ep:grid','','/article/category','article/category/index',0,4,29,NULL,'','2023-08-30 23:22:31','2023-09-04 20:56:01',0),(33,'Tag','标签','ep:price-tag','','/article/tag','article/tag/index',0,5,29,NULL,'','2023-08-30 23:23:09','2023-08-30 23:23:09',0),(34,'PersonalCenter','个人中心','fa:user-secret','/person/center','/person','',0,9,0,NULL,'','2023-08-30 23:26:02','2023-08-30 23:26:02',0),(35,'PersonCenter','个人中心','fa:user-secret','','/person/center','person/center/index',0,99,34,NULL,'','2023-08-31 00:22:01','2023-09-07 00:21:25',0),(36,'SystemAbout','关于','ep:info-filled','/sys/about','/sys','',1,10,0,NULL,'','2023-08-31 00:25:28','2023-09-07 14:54:23',0),(37,'AboutWebsite','关于','ep:info-filled','','/sys/about','about/index',0,99,36,NULL,'','2023-08-31 00:26:23','2023-09-07 00:32:44',0),(38,'Pictures','相册照片','ep:picture-filled','','/album/pictures/:id','album/picture/index',1,0,23,NULL,'','2023-09-01 16:46:08','2023-09-04 15:41:53',0),(39,'TalkEdit','编辑说说','ep:edit','','/talk/edit/:id','talk/publish/index',1,0,26,NULL,'','2023-09-03 22:59:38','2023-09-03 22:59:38',0),(40,'ArticleEdit','文章编辑','ep:edit','','/article/edit/:id','article/markdown/index',1,0,29,NULL,'','2023-09-06 00:39:51','2023-09-06 00:39:51',0),(41,'DevelopTool','开发工具','fa:connectdevelop','','/develop','',0,9,0,NULL,'','2023-09-13 22:39:24','2023-09-13 22:39:24',0),(42,'Cropping','图片剪切','fa:crop','','/develop/cropping','develop/cropping/index.vue',0,1,41,NULL,'','2023-09-13 22:40:30','2023-09-13 22:40:30',0),(43,'JsonEditor','JSON编辑器','ep:edit-pen','','/develop/jsonEditor','develop/json-editor/index.vue',0,2,41,NULL,'','2023-09-13 22:52:34','2023-09-13 23:02:16',0);
/*!40000 ALTER TABLE `system_menu` ENABLE KEYS */;
UNLOCK TABLES;

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
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资源菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_resource`
--

LOCK TABLES `system_resource` WRITE;
/*!40000 ALTER TABLE `system_resource` DISABLE KEYS */;
INSERT INTO `system_resource` VALUES (1,'test','','/TEST',1,0,NULL,NULL,'2023-08-22 00:00:00','2023-09-16 06:09:09',1),(2,'用户账号模块',NULL,'/user/auth',0,0,NULL,'','2023-08-29 00:01:24','2023-09-10 00:20:13',0),(3,'用户信息模块',NULL,'/user/info',0,0,NULL,'','2023-08-29 00:01:49','2023-08-29 00:01:49',0),(4,'系统菜单模块',NULL,'/system/menu',0,0,NULL,'','2023-08-29 00:02:31','2023-08-29 00:02:31',0),(5,'用户登录','POST','/user/auth/login',1,2,NULL,'','2023-08-29 00:11:13','2023-08-29 00:11:13',0),(6,'用户注销','GET','/user/auth/logout',0,2,NULL,'','2023-09-10 00:21:13','2023-09-10 00:21:13',0),(7,'刷新Token','GET','/user/auth/refresh/token',0,2,NULL,'','2023-09-10 00:27:57','2023-09-10 00:27:57',0),(8,'分页获取用户列表','GET','/user/auth/page',0,2,NULL,'','2023-09-10 00:28:24','2023-09-10 00:28:24',0),(9,'获取所有登录类型','GET','/user/auth/login/type',0,2,NULL,'','2023-09-10 00:29:28','2023-09-10 00:45:16',0),(10,'修改用户信息','PUT','/user/auth',0,2,NULL,'','2023-09-10 00:30:07','2023-09-10 00:41:42',0),(11,'添加新的用户','POST','/user/auth',0,2,NULL,'','2023-09-10 00:30:26','2023-09-10 00:30:26',0),(12,'修改用户禁用状态','PUT','/user/auth/status',0,2,NULL,'','2023-09-10 00:30:49','2023-09-10 00:30:49',0),(13,'获取当前用户信息','GET','/user/auth',0,2,NULL,'','2023-09-10 00:43:14','2023-09-10 00:43:14',0),(14,'修改当前用户密码','PUT','/user/auth/password',0,2,NULL,'','2023-09-10 00:43:49','2023-09-10 00:43:49',0),(15,'获取当前用户信息','GET','/user/info',0,3,NULL,'','2023-09-10 00:44:10','2023-09-10 00:44:10',0),(16,'修改当前用户信息','PUT','/user/info/person',0,3,NULL,'','2023-09-10 00:44:33','2023-09-10 00:44:33',0),(17,'系统资源模块',NULL,'/system/resource',0,0,NULL,'','2023-09-10 00:50:32','2023-09-10 00:50:32',0),(18,'角色信息模块',NULL,'/role',0,0,NULL,'','2023-09-10 00:50:49','2023-09-10 00:50:49',0),(19,'获取所有角色信息','GET','/role/list',0,18,NULL,'','2023-09-10 00:51:15','2023-09-10 00:51:15',0),(20,'获取所有角色关键信息','GET','/role/simple/list',0,18,NULL,'','2023-09-10 00:51:57','2023-09-10 00:51:57',0),(21,'添加角色信息','POST','/role',0,18,NULL,'','2023-09-10 00:52:19','2023-09-10 00:52:19',0),(22,'修改角色信息','PUT','/role',0,18,NULL,'','2023-09-10 00:52:37','2023-09-10 00:52:37',0),(23,'修改角色禁用状态','PUT','/role/status',0,18,NULL,'','2023-09-10 00:52:54','2023-09-10 00:52:54',0),(24,'删除指定角色信息','DELETE','/role/*',0,18,NULL,'','2023-09-10 00:53:14','2023-09-10 00:53:14',0),(25,'角色菜单模块',NULL,'/role/menu',0,0,NULL,'','2023-09-10 00:53:56','2023-09-10 00:53:56',0),(26,'获取指定角色的所有菜单权限','GET','/role/menu/list/*',0,25,NULL,'','2023-09-10 00:54:29','2023-09-10 00:56:37',0),(27,'给指定角色添加菜单权限','PUT','/role/menu/batch',0,25,NULL,'','2023-09-10 00:55:10','2023-09-10 00:55:10',0),(28,'角色资源模块',NULL,'/role/resource',0,0,NULL,'','2023-09-10 00:55:37','2023-09-10 00:55:37',0),(29,'获取指定角色的所有资源权限','GET','/role/resource/list/*',0,28,NULL,'','2023-09-10 00:55:57','2023-09-10 00:56:50',0),(30,'给指定角色添加资源权限','PUT','/role/resource/batch',0,28,NULL,'','2023-09-10 00:56:24','2023-09-10 00:56:24',0),(31,'获取所有的菜单树结构信息','GET','/system/menu/tree',0,4,NULL,'','2023-09-10 00:57:34','2023-09-10 00:57:34',0),(32,'添加系统菜单信息','POST','/system/menu',0,4,NULL,'','2023-09-10 00:57:55','2023-09-10 00:57:55',0),(33,'修改系统菜单信息','PUT','/system/menu',0,4,NULL,'','2023-09-10 00:58:13','2023-09-10 00:58:13',0),(34,'删除指定菜单信息','DELETE','/system/menu/*',0,4,NULL,'','2023-09-10 00:58:49','2023-09-10 00:58:49',0),(35,'修改菜单开放状态','PUT','/system/menu/status',0,4,NULL,'','2023-09-10 01:04:53','2023-09-10 01:04:53',0),(36,'获取简单的菜单树结构信息','GET','/system/menu/simple/tree',0,4,NULL,'','2023-09-10 01:05:27','2023-09-10 01:05:27',0),(37,'获取资源树结构信息','GET','/system/resource/tree',0,17,NULL,'','2023-09-10 01:06:44','2023-09-10 01:06:44',0),(38,'添加资源信息','POST','/system/resource',0,17,NULL,'','2023-09-10 01:07:08','2023-09-10 01:07:08',0),(39,'修改资源信息','PUT','/system/resource',0,17,NULL,'','2023-09-10 01:07:25','2023-09-10 01:07:25',0),(40,'删除指定资源信息','DELETE','/system/resource/*',0,17,NULL,'','2023-09-10 01:07:53','2023-09-10 01:07:53',0),(41,'修改资源开放状态','PUT','/system/resource/status',0,17,NULL,'','2023-09-10 01:08:16','2023-09-10 01:08:16',0),(42,'获取简单的资源树结构数据','GET','/system/resource/simple/tree',0,17,NULL,'','2023-09-10 01:10:32','2023-09-10 01:10:32',0),(43,'文章信息模块',NULL,'/article',0,0,NULL,'','2023-09-10 01:15:27','2023-09-10 01:15:27',0),(44,'分页获取文章信息','GET','/article/page',0,43,NULL,'','2023-09-10 01:15:45','2023-09-10 01:15:45',0),(45,'根据ID获取文章信息','GET','/article/*',0,43,NULL,'','2023-09-10 01:16:21','2023-09-10 01:16:21',0),(46,'获取所有文章类型','GET','/article/type',0,43,NULL,'','2023-09-10 01:16:36','2023-09-10 01:16:36',0),(47,'修改文章信息','PUT','/article',0,43,NULL,'','2023-09-10 01:17:14','2023-09-10 01:17:14',0),(48,'添加文章信息','POST','/article',0,43,NULL,'','2023-09-10 01:17:27','2023-11-27 23:21:09',0),(49,'保存文字草稿','POST','/article/draft',0,43,NULL,'','2023-09-10 01:17:49','2023-09-10 01:17:49',0),(50,'简单修改文章基本信息','PUT','/article/simple',0,43,NULL,'','2023-09-10 01:18:10','2023-09-10 01:18:10',0),(51,'修改文章置顶状态','PUT','/article/top',0,43,NULL,'','2023-09-10 01:18:30','2023-09-10 01:18:30',0),(52,'删除指定文章信息','DELETE','/article/*',0,43,NULL,'','2023-09-10 01:18:53','2023-09-10 01:18:53',0),(53,'文章分类模块',NULL,'/category',0,0,NULL,'','2023-09-10 01:19:22','2023-09-10 01:19:22',0),(54,'分页获取文章分类信息','GET','/category/detail/page',0,53,NULL,'','2023-09-10 01:19:39','2023-09-10 01:19:39',0),(55,'添加文章分类信息','POST','/category',0,53,NULL,'','2023-09-10 01:20:09','2023-09-10 01:20:09',0),(56,'删除指定分类信息','DELETE','/category/*',0,53,NULL,'','2023-09-10 01:20:28','2023-09-10 01:22:02',0),(57,'修改分类隐藏状态','PUT','/category/status',0,53,NULL,'','2023-09-10 01:21:34','2023-09-10 01:21:34',0),(58,'获取所有的简单分类数据','GET','/category/simple/list',0,53,NULL,'','2023-09-10 01:22:29','2023-09-10 01:22:29',0),(59,'文章标签模块',NULL,'/tag',0,0,NULL,'','2023-09-10 01:23:37','2023-09-10 01:23:37',0),(60,'分页获取标签信息','GET','/tag/page',0,59,NULL,'','2023-09-10 01:23:51','2023-09-10 01:23:51',0),(61,'添加标签信息','POST','/tag',0,59,NULL,'','2023-09-10 01:24:19','2023-09-10 01:24:19',0),(62,'修改标签信息','PUT','/tag',0,59,NULL,'','2023-09-10 01:24:28','2023-09-10 01:24:28',0),(63,'删除指定标签信息','DELETE','/tag/*',0,59,NULL,'','2023-09-10 01:24:49','2023-09-10 01:24:49',0),(64,'修改标签隐藏状态','PUT','/tag/status',0,59,NULL,'','2023-09-10 01:25:02','2023-09-10 01:25:02',0),(65,'获取所有标签的简单信息','GET','/tag/simple/list',0,59,NULL,'','2023-09-10 01:25:19','2023-09-10 01:25:19',0),(66,'说说日志模块',NULL,'/talk',0,0,NULL,'','2023-09-10 01:25:47','2023-09-10 01:25:47',0),(67,'分页获取说说信息','GET','/talk/page',0,66,NULL,'','2023-09-10 01:26:02','2023-09-10 01:26:02',0),(68,'添加说说信息','POST','/talk',0,66,NULL,'','2023-09-10 01:26:25','2023-09-10 01:26:25',0),(69,'修改说说信息','PUT','/talk',0,66,NULL,'','2023-09-10 01:26:35','2023-09-10 01:26:35',0),(70,'删除指定说说信息','DELETE','/talk/*',0,66,NULL,'','2023-09-10 01:26:51','2023-09-10 01:26:51',0),(71,'根据ID查找指定说说信息','GET','/talk/*',0,66,NULL,'','2023-09-10 01:27:25','2023-09-10 01:27:25',0),(72,'个人相册模块',NULL,'/album',0,0,NULL,'','2023-09-10 01:28:04','2023-09-10 01:28:04',0),(73,'分页获取相册信息','GET','/album/page',0,72,NULL,'','2023-09-10 01:28:19','2023-09-10 01:28:19',0),(74,'添加相册信息','POST','/album',0,72,NULL,'','2023-09-10 01:28:34','2023-09-10 01:28:34',0),(75,'修改相册信息','PUT','/album',0,72,NULL,'','2023-09-10 01:28:45','2023-09-10 01:28:45',0),(76,'删除指定相册信息','DELETE','/album/*',0,72,NULL,'','2023-09-10 01:28:59','2023-09-10 01:28:59',0),(77,'相册图片模块',NULL,'/picture',0,0,NULL,'','2023-09-10 01:29:58','2023-09-10 01:29:58',0),(78,'分页获取图片信息','GET','/picture/page',0,77,NULL,'','2023-09-10 01:30:16','2023-09-10 01:30:16',0),(79,'添加图片信息','POST','/picture',0,77,NULL,'','2023-09-10 01:30:51','2023-09-10 01:30:51',0),(80,'批量添加图片信息','POST','/picture/batch',0,77,NULL,'','2023-09-10 01:31:12','2023-09-10 01:31:12',0),(81,'修改图片信息','PUT','/picture',0,77,NULL,'','2023-09-10 01:31:29','2023-09-10 01:31:29',0),(82,'删除指定图片信息','DELETE','/picture/*',0,77,NULL,'','2023-09-10 01:32:03','2023-09-10 01:32:03',0),(83,'资源上传模块',NULL,'/upload',0,0,NULL,'','2023-09-10 01:32:33','2023-09-10 01:32:33',0),(84,'获取阿里云OSS上传凭证','GET','/upload/oss/access/info/*',0,83,NULL,'','2023-09-10 01:33:06','2023-09-10 01:33:06',0),(85,'评论信息模块',NULL,'/comment',0,0,NULL,'','2023-09-10 01:34:01','2023-09-10 01:34:01',0),(86,'分页获取评论信息','GET','/comment/page',0,85,NULL,'','2023-09-10 01:34:14','2023-09-10 01:34:14',0),(87,'修改评论状态信息','PUT','/comment',0,85,NULL,'','2023-09-10 01:34:45','2023-09-10 01:34:45',0),(88,'删除指定评论信息','DELETE','/comment/*',0,85,NULL,'','2023-09-10 01:35:12','2023-09-10 01:35:12',0),(89,'获取所有话题类型','GET','/comment/topic/type',0,85,NULL,'','2023-09-10 01:35:31','2023-09-10 01:35:31',0),(90,'批量删除评论信息','DELETE','/comment/batch',0,85,NULL,'','2023-09-10 01:35:47','2023-09-10 01:35:47',0),(91,'留言信息模块',NULL,'/guestbook',0,0,NULL,'','2023-09-10 01:36:24','2023-09-10 01:36:24',0),(92,'分页获取留言信息','GET','/guestbook/page',0,91,NULL,'','2023-09-10 01:36:35','2023-09-10 01:36:35',0),(93,'修改留言状态信息','PUT','/guestbook',0,91,NULL,'','2023-09-10 01:36:54','2023-09-10 01:36:54',0),(94,'删除指定留言信息','DELETE','/guestbook/*',0,91,NULL,'','2023-09-10 01:37:13','2023-09-10 01:37:13',0),(95,'批量删除留言信息','DELETE','/guestbook/batch',0,91,NULL,'','2023-09-10 01:37:30','2023-09-10 01:37:30',0),(96,'公共接口模块',NULL,'/common',0,0,NULL,'','2023-09-10 01:38:56','2023-09-10 01:38:56',0),(97,'获取所有的内容状态信息','GET','/common/content/status',0,96,NULL,'','2023-09-10 01:39:15','2023-09-10 01:39:15',0),(98,'获取所有的用户类型信息','GET','/common/user/type',0,96,NULL,'','2023-09-10 01:39:35','2023-09-10 01:39:35',0),(99,'友链信息模块',NULL,'/friend',0,0,NULL,'','2023-09-10 01:40:19','2023-09-10 01:40:19',0),(100,'分页获取友链信息','GET','/friend/page',0,99,NULL,'','2023-09-10 01:40:34','2023-09-10 01:40:34',0),(101,'添加友链信息','POST','/friend',0,99,NULL,'','2023-09-10 01:41:04','2023-09-10 01:41:04',0),(102,'修改友链信息','PUT','/friend',0,99,NULL,'','2023-09-10 01:41:25','2023-09-10 01:41:25',0),(103,'修改友链状态','PUT','/friend/status',0,99,NULL,'','2023-09-10 01:41:40','2023-09-10 01:41:40',0),(104,'删除指定友链信息','DELETE','/friend/*',0,99,NULL,'','2023-09-10 01:42:14','2023-09-10 01:42:14',0),(105,'获取指定角色的菜单路由','GET','/role/menu/routes',0,25,NULL,'','2023-09-10 01:43:10','2023-09-10 01:43:10',0),(106,'网站配置模块',NULL,'/website',0,0,NULL,'','2023-09-10 01:43:45','2023-09-10 01:43:45',0),(107,'修改网站配置信息','PUT','/website',0,106,NULL,'','2023-09-10 01:44:01','2023-09-10 01:44:01',0),(108,'获取主页页面页面封面','GET','/website/config/covers',0,106,NULL,'','2023-09-10 01:44:28','2023-09-10 01:44:28',0),(109,'获取网站信息配置','GET','/website/config/information',0,106,NULL,'','2023-09-10 01:44:45','2023-09-10 01:48:10',0),(110,'获取作者信息配置','GET','/website/config/author/info',0,106,NULL,'','2023-09-10 01:44:58','2023-09-10 01:48:14',0),(111,'获取作者联系方式','GET','/website/config/author/contact',0,106,NULL,'','2023-09-10 01:45:22','2023-09-10 01:45:22',0),(112,'获取第三方登录配置','GET','/website/config/login/thirdparty',0,106,NULL,'','2023-09-10 01:45:43','2023-09-10 01:45:43',0),(113,'获取组件功能配置','GET','/website/config/component',0,106,NULL,'','2023-09-10 01:46:23','2023-09-10 01:46:23',0),(114,'获取隐私安全配置','GET','/website/config/privacy',0,106,NULL,'','2023-09-10 01:46:38','2023-09-10 01:46:38',0),(115,'获取系统通知配置','GET','/website/config/notice',0,106,NULL,'','2023-09-10 01:46:53','2023-09-10 01:46:53',0),(116,'获取默认图片头像配置','GET','/website/config/avatar',0,106,NULL,'','2023-09-10 01:47:32','2023-09-10 01:47:32',0),(117,'获取打赏信息配置','GET','/website/config/reward',0,106,NULL,'','2023-09-10 01:47:53','2023-09-10 01:47:53',0),(118,'获取关于作者信息','GET','/website/config/about',0,106,NULL,'','2023-09-10 01:48:25','2023-09-10 01:48:25',0),(119,'登录日志模块',NULL,'/log/login',0,0,NULL,'','2023-09-10 01:49:37','2023-09-10 01:49:37',0),(120,'分页获取用户登录日志','GET','/log/login/page',0,119,NULL,'','2023-09-10 01:49:58','2023-09-10 01:49:58',0),(121,'操作日志模块',NULL,'/log/operation',0,0,NULL,'','2023-09-10 01:52:52','2023-09-10 01:52:52',0),(122,'分页查询操作日志信息','GET','/log/operation/page',0,121,NULL,'','2023-09-10 01:53:04','2023-09-10 01:54:06',0),(123,'异常日志模块',NULL,'/log/error',0,0,NULL,'','2023-09-10 01:53:36','2023-09-10 01:53:36',0),(124,'分页查询异常日志信息','GET','/log/error/page',0,123,NULL,'','2023-09-10 01:53:52','2023-09-10 01:53:52',0),(125,'接口文档模块',NULL,'/',0,0,NULL,'','2023-09-10 15:57:58','2023-09-10 16:45:16',0),(126,'接口文档api','GET','/swagger-ui/**',1,125,NULL,'','2023-09-10 15:58:31','2023-09-10 16:13:33',0),(127,'接口文档简单路径','GET','/document',1,125,NULL,'','2023-09-10 16:07:47','2023-09-10 16:07:47',0),(128,'接口api资源','GET','/swagger-resources/**',1,125,NULL,'','2023-09-10 16:12:08','2023-09-10 16:12:08',0),(129,'接口页面图标','GET','/favicon.ico',1,125,NULL,'','2023-09-10 16:12:25','2023-09-10 16:12:25',0),(130,'api详情文档','GET','/v3/api-docs/**',1,125,NULL,'','2023-09-10 16:13:00','2023-09-10 16:13:00',0),(131,'Swagger页面','GET','/**/*.html',1,125,NULL,'','2023-09-10 16:14:22','2023-09-10 16:14:22',0),(132,'Swagger样式','GET','/**/*.css',1,125,NULL,'','2023-09-10 16:14:37','2023-09-10 16:14:37',0),(133,'Swagger脚本','GET','/**/*.js',1,125,NULL,'','2023-09-10 16:14:58','2023-09-10 16:14:58',0),(134,'接口文档','GET','/doc.html',1,125,NULL,'','2023-09-10 16:36:33','2023-09-10 16:43:03',1),(135,'查询文章说说相关数据统计','GET','/article/statistics/number',0,43,NULL,'','2023-09-10 17:18:04','2023-09-10 17:18:04',0),(136,'根据时间区间查询每天文章数量统计','GET','/article/statistics/count',0,43,NULL,'','2023-09-10 20:43:24','2023-09-10 21:09:42',0),(137,'根据时间区间查询说说数量','GET','/talk/statistics/count',0,66,NULL,'','2023-09-10 22:02:54','2023-09-10 22:02:54',0),(138,'查询分类的对应文章数量统计','GET','/category/statistics',0,53,NULL,'','2023-09-11 00:15:57','2023-09-11 00:15:57',0),(139,'获取图片来源类型','GET','/picture/type',0,77,NULL,'','2023-09-14 23:03:55','2023-09-14 23:03:55',0),(140,'测试模块',NULL,'/',0,0,NULL,'','2023-09-16 06:09:58','2023-09-16 06:10:01',1),(141,'测试',NULL,'、',0,0,NULL,'','2023-09-16 06:12:06','2023-09-16 06:12:10',1),(142,'测试2',NULL,'1',0,0,NULL,'','2023-09-16 06:12:25','2023-09-16 06:13:01',1),(143,'测试2',NULL,'1',0,0,NULL,'','2023-09-16 06:14:11','2023-09-16 06:14:15',1),(144,'从',NULL,'1',0,0,NULL,'','2023-09-16 06:15:19','2023-09-16 06:15:29',1),(145,'啊啊啊',NULL,'1',0,0,NULL,'','2023-09-16 06:20:10','2023-09-16 06:20:15',1),(146,'111',NULL,'1',0,0,NULL,'','2023-09-16 06:21:24','2023-09-16 06:21:28',1),(181,'用户分页获取相册信息','GET','/album/user/page',1,72,NULL,'','2023-11-27 23:48:03','2023-11-28 00:20:17',0),(182,'用户根据相册id获取详细数据','GET','/album/detail/*',1,72,NULL,'','2023-11-27 23:48:53','2023-11-27 23:48:53',0),(183,'用户获取文章、说说等数量统计','GET','/article/user/statistics/number',1,43,NULL,'','2023-11-27 23:50:36','2023-11-27 23:50:52',0),(184,'用户分页获取文章信息','GET','/article/user/page',1,43,NULL,'','2023-11-27 23:51:12','2023-11-27 23:51:12',0),(185,'用户获取归档信息','GET','/article/user/archive',1,43,NULL,'','2023-11-27 23:51:36','2023-11-27 23:51:36',0),(186,'用户根据文章id获取详细信息','GET','/article/detail/*',1,43,NULL,'','2023-11-27 23:51:59','2023-11-27 23:51:59',0),(187,'用户获取所有分类数据','GET','/category/user/list',1,53,NULL,'','2023-11-27 23:52:32','2023-11-27 23:52:32',0),(188,'用户根据分类id查询分类信息','GET','/category/detail/*',1,53,NULL,'','2023-11-27 23:59:11','2023-11-27 23:59:11',0),(189,'用户分页获取评论信息','GET','/comment/info/page',1,85,NULL,'','2023-11-28 00:00:09','2023-11-28 00:00:09',0),(190,'用户、游客添加评论信息','POST','/comment',1,85,NULL,'','2023-11-28 00:00:33','2023-11-28 00:01:02',0),(191,'用户获取网站配置列表','GET','/website/list',1,106,NULL,'','2023-11-28 00:04:48','2023-11-28 00:04:48',0),(192,'用户获取关于作者信息','GET','/website/about',1,106,NULL,'','2023-11-28 00:05:46','2023-11-28 00:05:46',0),(193,'用户分页获取友链信息','GET','/friend/user/page',1,99,NULL,'','2023-11-28 00:06:13','2023-11-28 00:06:13',0),(194,'用户分页获取留言信息','GET','/guestbook/user/page',1,91,NULL,'','2023-11-28 00:06:54','2023-11-28 00:06:54',0),(195,'用户添加留言信息','POST','/guestbook',1,91,NULL,'','2023-11-28 00:07:17','2023-11-28 00:07:17',0),(196,'用户分页获取相册照片信息','GET','/picture/user/page',1,77,NULL,'','2023-11-28 00:07:47','2023-11-28 00:07:47',0),(197,'用户获取全部标签信息','GET','/tag/user/list',1,59,NULL,'','2023-11-28 00:08:18','2023-11-28 00:08:18',0),(198,'用户根据标签id查询标签信息','GET','/tag/detail/*',1,59,NULL,'','2023-11-28 00:10:20','2023-11-28 00:10:20',0),(199,'用户分页查询说说信息','GET','/talk/user/page',1,66,NULL,'','2023-11-28 00:10:49','2023-11-28 00:10:49',0),(200,'用户根据说说id查询详细信息','GET','/talk/detail/*',1,66,NULL,'','2023-11-28 00:11:11','2023-11-28 00:11:11',0);
/*!40000 ALTER TABLE `system_resource` ENABLE KEYS */;
UNLOCK TABLES;

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
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='相册';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_album`
--

LOCK TABLES `t_album` WRITE;
/*!40000 ALTER TABLE `t_album` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_article`
--

DROP TABLE IF EXISTS `t_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_article` (
  `id` int NOT NULL AUTO_INCREMENT,
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
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='博客文章';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_article`
--

LOCK TABLES `t_article` WRITE;
/*!40000 ALTER TABLE `t_article` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_article` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章对应标签';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_article_tag`
--

LOCK TABLES `t_article_tag` WRITE;
/*!40000 ALTER TABLE `t_article_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_article_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_category`
--

DROP TABLE IF EXISTS `t_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_category` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `name` varchar(30) NOT NULL COMMENT '分类名称',
  `description` varchar(100) DEFAULT NULL,
  `hidden` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否隐藏该分类',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否被删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章类别';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_category`
--

LOCK TABLES `t_category` WRITE;
/*!40000 ALTER TABLE `t_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_category` ENABLE KEYS */;
UNLOCK TABLES;

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
  `images` varchar(2048) DEFAULT '[]',
  `ip_address` varchar(50) NOT NULL COMMENT 'ip地址',
  `ip_source` varchar(100) NOT NULL COMMENT 'ip所在地',
  `device` varchar(50) NOT NULL DEFAULT '未知设备' COMMENT '使用设备',
  `browser` varchar(50) NOT NULL DEFAULT '未知浏览器' COMMENT '使用的浏览器',
  `point` point DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `root_id` int NOT NULL DEFAULT '0' COMMENT '根评论id',
  `parent_id` int NOT NULL DEFAULT '0' COMMENT '回复的评论ID',
  `type` varchar(20) NOT NULL COMMENT '评论类型(1登录评论2游客评论3匿名评论)',
  `nickname` varchar(30) DEFAULT NULL COMMENT '游客别名',
  `email` varchar(128) DEFAULT NULL COMMENT '游客邮箱',
  `qq` varchar(15) DEFAULT NULL COMMENT '游客QQ号',
  `hidden` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否隐藏评论',
  `review` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否审核评论再显示',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_comment`
--

LOCK TABLES `t_comment` WRITE;
/*!40000 ALTER TABLE `t_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_friend`
--

DROP TABLE IF EXISTS `t_friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_friend` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '友情链接ID',
  `userAuthId` int NOT NULL,
  `name` varchar(30) NOT NULL COMMENT '网站名称',
  `icon` varchar(256) NOT NULL COMMENT '网站图标链接',
  `url` varchar(255) NOT NULL COMMENT '网站地址',
  `author` varchar(30) NOT NULL DEFAULT '【未填写】' COMMENT '网站作者',
  `introduction` varchar(200) NOT NULL COMMENT '网站介绍',
  `review` tinyint(1) NOT NULL DEFAULT '0',
  `hidden` tinyint(1) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='友情链接';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_friend`
--

LOCK TABLES `t_friend` WRITE;
/*!40000 ALTER TABLE `t_friend` DISABLE KEYS */;
INSERT INTO `t_friend` VALUES (1,0,'汍笙的博客','https://wbxnl.com/logo.png','http://wbxnl.com','汍笙','不知道说点啥~',0,0,'2022-09-08 02:15:14','2023-03-14 10:22:33',0);
/*!40000 ALTER TABLE `t_friend` ENABLE KEYS */;
UNLOCK TABLES;

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
  `images` varchar(2048) DEFAULT '[]',
  `ip_address` varchar(50) NOT NULL COMMENT 'ip地址',
  `ip_source` varchar(100) NOT NULL COMMENT 'ip所在地',
  `device` varchar(50) NOT NULL DEFAULT '未知设备' COMMENT '使用设备',
  `browser` varchar(50) NOT NULL DEFAULT '未知浏览器' COMMENT '使用的浏览器',
  `point` point DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `type` varchar(20) NOT NULL COMMENT '留言类型(1登录留言2游客留言3匿名留言)',
  `nickname` varchar(30) DEFAULT NULL COMMENT '游客别名',
  `email` varchar(100) DEFAULT NULL COMMENT '游客邮箱',
  `qq` varchar(15) DEFAULT NULL COMMENT '游客QQ号',
  `hidden` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否隐藏留言',
  `review` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否需要审核',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='留言簿';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_guestbook`
--

LOCK TABLES `t_guestbook` WRITE;
/*!40000 ALTER TABLE `t_guestbook` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_guestbook` ENABLE KEYS */;
UNLOCK TABLES;

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
  `browser` varchar(50) NOT NULL DEFAULT '未知浏览器' COMMENT '浏览器信息',
  `point` point DEFAULT NULL COMMENT '坐标',
  `location` varchar(100) DEFAULT NULL COMMENT '位置',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='登录日志，记录用户的登录信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_login_log`
--

LOCK TABLES `t_login_log` WRITE;
/*!40000 ALTER TABLE `t_login_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_login_log` ENABLE KEYS */;
UNLOCK TABLES;

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
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='访问量';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_page_view`
--

LOCK TABLES `t_page_view` WRITE;
/*!40000 ALTER TABLE `t_page_view` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_page_view` ENABLE KEYS */;
UNLOCK TABLES;

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
  `url` varchar(512) NOT NULL COMMENT '图片地址',
  `source` varchar(20) DEFAULT NULL COMMENT '图片来源（0未知1原创2二创3转载）',
  `status` varchar(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='图片';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_picture`
--

LOCK TABLES `t_picture` WRITE;
/*!40000 ALTER TABLE `t_picture` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_picture` ENABLE KEYS */;
UNLOCK TABLES;

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
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_label_UNIQUE` (`label`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (1,'管理员','admin','对整个网站拥有全部操作权限！！！',0,'2022-09-08 21:00:04','2022-09-17 00:40:30',0),(2,'测试','test','测试项目功能',0,'2022-09-09 00:31:31','2022-09-18 23:33:58',0),(5,'用户','user','普通的博客用户',0,'2022-09-16 23:46:54','2023-03-11 02:39:12',0);
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=2249 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_menu`
--

LOCK TABLES `t_role_menu` WRITE;
/*!40000 ALTER TABLE `t_role_menu` DISABLE KEYS */;
INSERT INTO `t_role_menu` VALUES (245,1,40),(246,1,30),(247,1,31),(248,1,32),(249,1,33),(250,1,39),(251,1,27),(252,1,28),(253,1,21),(254,1,22),(255,1,38),(256,1,24),(257,1,25),(258,1,18),(259,1,19),(260,1,4),(261,1,5),(262,1,6),(263,1,11),(264,1,12),(265,1,13),(266,1,14),(267,1,15),(268,1,16),(269,1,8),(270,1,9),(271,1,35),(272,1,42),(273,1,43),(274,1,37),(2219,2,40),(2220,2,30),(2221,2,31),(2222,2,32),(2223,2,33),(2224,2,39),(2225,2,27),(2226,2,28),(2227,2,21),(2228,2,22),(2229,2,38),(2230,2,24),(2231,2,25),(2232,2,18),(2233,2,19),(2234,2,4),(2235,2,5),(2236,2,6),(2237,2,11),(2238,2,12),(2239,2,13),(2240,2,14),(2241,2,15),(2242,2,16),(2243,2,8),(2244,2,9),(2245,2,35),(2246,2,37),(2247,5,35),(2248,5,37);
/*!40000 ALTER TABLE `t_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=5389 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色资源';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_resource`
--

LOCK TABLES `t_role_resource` WRITE;
/*!40000 ALTER TABLE `t_role_resource` DISABLE KEYS */;
INSERT INTO `t_role_resource` VALUES (625,1,5),(626,1,6),(627,1,7),(628,1,8),(629,1,9),(630,1,10),(631,1,11),(632,1,12),(633,1,13),(634,1,14),(635,1,15),(636,1,16),(637,1,31),(638,1,32),(639,1,33),(640,1,34),(641,1,35),(642,1,36),(643,1,37),(644,1,38),(645,1,39),(646,1,40),(647,1,41),(648,1,42),(649,1,19),(650,1,20),(651,1,21),(652,1,22),(653,1,23),(654,1,24),(655,1,26),(656,1,27),(657,1,105),(658,1,29),(659,1,30),(660,1,44),(661,1,45),(662,1,46),(663,1,47),(664,1,48),(665,1,49),(666,1,50),(667,1,51),(668,1,52),(669,1,135),(670,1,136),(671,1,54),(672,1,55),(673,1,56),(674,1,57),(675,1,58),(676,1,138),(677,1,60),(678,1,61),(679,1,62),(680,1,63),(681,1,64),(682,1,65),(683,1,67),(684,1,68),(685,1,69),(686,1,70),(687,1,71),(688,1,137),(689,1,73),(690,1,74),(691,1,75),(692,1,76),(693,1,78),(694,1,79),(695,1,80),(696,1,81),(697,1,82),(698,1,139),(699,1,84),(700,1,86),(701,1,87),(702,1,88),(703,1,89),(704,1,90),(705,1,92),(706,1,93),(707,1,94),(708,1,95),(709,1,97),(710,1,98),(711,1,100),(712,1,101),(713,1,102),(714,1,103),(715,1,104),(716,1,107),(717,1,108),(718,1,109),(719,1,110),(720,1,111),(721,1,112),(722,1,113),(723,1,114),(724,1,115),(725,1,116),(726,1,117),(727,1,118),(728,1,120),(729,1,122),(730,1,124),(5277,5,5),(5278,5,6),(5279,5,7),(5280,5,13),(5281,5,14),(5282,5,15),(5283,5,16),(5337,2,5),(5338,2,6),(5339,2,7),(5340,2,8),(5341,2,9),(5342,2,13),(5343,2,15),(5344,2,31),(5345,2,36),(5346,2,37),(5347,2,42),(5348,2,19),(5349,2,20),(5350,2,26),(5351,2,105),(5352,2,29),(5353,2,44),(5354,2,45),(5355,2,46),(5356,2,135),(5357,2,136),(5358,2,54),(5359,2,58),(5360,2,138),(5361,2,60),(5362,2,65),(5363,2,67),(5364,2,71),(5365,2,137),(5366,2,73),(5367,2,78),(5368,2,139),(5369,2,86),(5370,2,89),(5371,2,92),(5372,2,97),(5373,2,98),(5374,2,100),(5375,2,108),(5376,2,109),(5377,2,110),(5378,2,111),(5379,2,112),(5380,2,113),(5381,2,114),(5382,2,115),(5383,2,116),(5384,2,117),(5385,2,118),(5386,2,120),(5387,2,122),(5388,2,124);
/*!40000 ALTER TABLE `t_role_resource` ENABLE KEYS */;
UNLOCK TABLES;

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
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章标签';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_tag`
--

LOCK TABLES `t_tag` WRITE;
/*!40000 ALTER TABLE `t_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_tag` ENABLE KEYS */;
UNLOCK TABLES;

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
  `ip_source` varchar(100) NOT NULL COMMENT 'ip来源',
  `device` varchar(50) NOT NULL DEFAULT '未知设备' COMMENT '使用设备',
  `browser` varchar(50) NOT NULL DEFAULT '未知浏览器' COMMENT '使用浏览器',
  `point` point DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='说说';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_talk`
--

LOCK TABLES `t_talk` WRITE;
/*!40000 ALTER TABLE `t_talk` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_talk` ENABLE KEYS */;
UNLOCK TABLES;

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
  `third_party_id` varchar(128) DEFAULT NULL,
  `third_party_profile` varchar(512) DEFAULT NULL,
  `disable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户是否禁用',
  `ip_address_signup` varchar(50) NOT NULL COMMENT '注册时ip',
  `ip_source_signup` varchar(100) NOT NULL COMMENT '注册ip所在地',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3 COMMENT='用户账号';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_auth`
--

LOCK TABLES `t_user_auth` WRITE;
/*!40000 ALTER TABLE `t_user_auth` DISABLE KEYS */;
INSERT INTO `t_user_auth` VALUES (2,3,'test','$2a$10$jFMatm80S3xjMp11kiUHbOHZs57pZjzMSNLfxYnd1WxflT3Y9w1N2','1',NULL,NULL,0,'0:0:0:0:0:0:0:1','','2022-09-09 01:59:12','2023-09-17 18:50:09',0),(3,4,'admin','$2a$10$G.FLSiqA4nuK7zoPxNZF7.eiT.r5LRYtC8AgUbNLBFlC4isXy8Sr2','1',NULL,NULL,0,'192.168.1.2','本地局域网','2022-09-16 01:35:56','2023-09-13 02:47:14',0);
/*!40000 ALTER TABLE `t_user_auth` ENABLE KEYS */;
UNLOCK TABLES;

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
  `signature` varchar(100) DEFAULT NULL,
  `website` varchar(100) DEFAULT NULL COMMENT '个人网站',
  `introduction` varchar(250) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3 COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_info`
--

LOCK TABLES `t_user_info` WRITE;
/*!40000 ALTER TABLE `t_user_info` DISABLE KEYS */;
INSERT INTO `t_user_info` VALUES (3,'xiaowansheng@foxmail.com',NULL,'测试','',NULL,'http://wbxnl.com','无介绍123456789101112131415','2022-09-09 01:59:12','2023-03-11 04:58:05',0),(4,'xiaowansheng@foxmail.com','123456','小汍笙','','111222','http://wbxnl.com','哈哈哈无介绍','2022-09-16 01:35:56','2023-11-24 18:00:33',0);
/*!40000 ALTER TABLE `t_user_info` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户账号对应角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `t_user_role` WRITE;
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
INSERT INTO `t_user_role` VALUES (33,2,2),(38,3,1);
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_visitor`
--

DROP TABLE IF EXISTS `t_visitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_visitor` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '访客信息ID',
  `uuid` varchar(256) NOT NULL,
  `view_type` varchar(20) NOT NULL COMMENT 'ip地址',
  `view_id` int DEFAULT NULL COMMENT 'ip所在地',
  `ip_address` varchar(100) NOT NULL COMMENT '访问用户的账号ID',
  `ip_source` varchar(100) NOT NULL COMMENT '访问用户的名称',
  `device` varchar(50) NOT NULL DEFAULT '未知设备',
  `browser` varchar(50) NOT NULL DEFAULT '未知浏览器',
  `point` point DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid_UNIQUE` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='访客信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_visitor`
--

LOCK TABLES `t_visitor` WRITE;
/*!40000 ALTER TABLE `t_visitor` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_visitor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-28  1:50:14
