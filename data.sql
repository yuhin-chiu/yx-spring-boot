-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.18-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 test 的数据库结构
CREATE DATABASE IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `test`;

-- 导出  表 test.whs_activities 结构
DROP TABLE IF EXISTS `whs_activities`;
CREATE TABLE IF NOT EXISTS `whs_activities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL COMMENT '新闻标题',
  `url` varchar(50) DEFAULT NULL COMMENT '新闻链接',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0为显示，1为过期',
  `create_time` bigint(20) NOT NULL COMMENT '添加图片时间',
  `content` text NOT NULL COMMENT '新闻内容',
  `author` varchar(50) DEFAULT '管理员' COMMENT '发布者',
  `browses` bigint(20) DEFAULT '0' COMMENT '浏览量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='activities';

-- 正在导出表  test.whs_activities 的数据：~4 rows (大约)
DELETE FROM `whs_activities`;
/*!40000 ALTER TABLE `whs_activities` DISABLE KEYS */;
INSERT INTO `whs_activities` (`id`, `title`, `url`, `status`, `create_time`, `content`, `author`, `browses`) VALUES
	(1, '你好', NULL, 0, 1500803198, 'haohaohaoahoahaoahoahohaohaohfoadhfjihdsakfjksdf', '管理员', 0),
	(3, '你好', NULL, 0, 1500803589, 'haohaohaoahoahaoahoahohaohaohfoadhfjihdsakfjksdf', '管理员', 0),
	(4, '你好', NULL, 0, 1500803753, 'haohaohaoahoahaoahoahohaohaohfoadhfjihdsakfjksdf', '管理员', 0),
	(5, '你好2', NULL, 0, 1500803765, 'haohaohaoahoahaoahoahohaohaohfoadhfjihdsakfjksdf', '管理员', 0);
/*!40000 ALTER TABLE `whs_activities` ENABLE KEYS */;

-- 导出  表 test.whs_audience 结构
DROP TABLE IF EXISTS `whs_audience`;
CREATE TABLE IF NOT EXISTS `whs_audience` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '观众名字',
  `type` tinyint(4) DEFAULT NULL COMMENT '观众类型',
  `image` varchar(50) DEFAULT NULL COMMENT '观众照片',
  `phone` varchar(50) NOT NULL COMMENT '观众电话',
  `company` varchar(50) NOT NULL COMMENT '所属公司',
  `tele` varchar(50) DEFAULT NULL COMMENT '办公电话',
  `mail` varchar(50) DEFAULT NULL COMMENT '电子邮件',
  `address` varchar(50) DEFAULT '0' COMMENT '地址',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0表示未处理',
  `apply_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='观众表';

-- 正在导出表  test.whs_audience 的数据：~2 rows (大约)
DELETE FROM `whs_audience`;
/*!40000 ALTER TABLE `whs_audience` DISABLE KEYS */;
INSERT INTO `whs_audience` (`id`, `name`, `type`, `image`, `phone`, `company`, `tele`, `mail`, `address`, `status`, `apply_time`) VALUES
	(6, 'yuxuanjiao', NULL, NULL, '123456', '第一公司', NULL, NULL, '0', 0, 1500790552),
	(7, '观众yxjiao', NULL, NULL, '1405555555', '第二公司', '88888888', '4555@123.com', '0', 0, 1500795251);
/*!40000 ALTER TABLE `whs_audience` ENABLE KEYS */;

-- 导出  表 test.whs_company 结构
DROP TABLE IF EXISTS `whs_company`;
CREATE TABLE IF NOT EXISTS `whs_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '展商名字',
  `type` tinyint(4) DEFAULT NULL COMMENT '展商类型',
  `image` varchar(50) DEFAULT NULL COMMENT '展商图片',
  `url` text COMMENT '友链',
  `principal` varchar(50) NOT NULL COMMENT '负责人名字',
  `phone` varchar(50) NOT NULL COMMENT '负责人电话',
  `tele` varchar(50) DEFAULT NULL COMMENT '办公电话',
  `mail` varchar(50) DEFAULT NULL COMMENT '电子邮件',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '展商状态推荐与否',
  `address` varchar(50) DEFAULT '0' COMMENT '地址',
  `apply_time` int(11) NOT NULL DEFAULT '1500790555',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='展商表';

-- 正在导出表  test.whs_company 的数据：~1 rows (大约)
DELETE FROM `whs_company`;
/*!40000 ALTER TABLE `whs_company` DISABLE KEYS */;
INSERT INTO `whs_company` (`id`, `name`, `type`, `image`, `url`, `principal`, `phone`, `tele`, `mail`, `status`, `address`, `apply_time`) VALUES
	(1, 'gongsi', 1, '6', '555555', 'yx', '1', NULL, NULL, 1, '0', 1500790555),
	(2, '6666', NULL, NULL, NULL, 'yxjiao', '7777', NULL, NULL, 0, '0', 1500790555);
/*!40000 ALTER TABLE `whs_company` ENABLE KEYS */;

-- 导出  表 test.whs_company_apply 结构
DROP TABLE IF EXISTS `whs_company_apply`;
CREATE TABLE IF NOT EXISTS `whs_company_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '展商名字',
  `principal` varchar(50) NOT NULL COMMENT '负责人名字',
  `phone` varchar(50) NOT NULL COMMENT '负责人电话',
  `tele` varchar(50) DEFAULT NULL COMMENT '办公电话',
  `mail` varchar(50) DEFAULT NULL COMMENT '电子邮件',
  `address` varchar(50) DEFAULT NULL,
  `apply_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='展商表';

-- 正在导出表  test.whs_company_apply 的数据：~2 rows (大约)
DELETE FROM `whs_company_apply`;
/*!40000 ALTER TABLE `whs_company_apply` DISABLE KEYS */;
INSERT INTO `whs_company_apply` (`id`, `name`, `principal`, `phone`, `tele`, `mail`, `address`, `apply_time`) VALUES
	(2, '第一公司', 'yuxuanjiao', '123456', NULL, NULL, NULL, 1500790555),
	(3, '第一公司', 'yuxuanjiao', '140123456', NULL, NULL, NULL, 1500795201);
/*!40000 ALTER TABLE `whs_company_apply` ENABLE KEYS */;

-- 导出  表 test.whs_history 结构
DROP TABLE IF EXISTS `whs_history`;
CREATE TABLE IF NOT EXISTS `whs_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='往期回顾';

-- 正在导出表  test.whs_history 的数据：~1 rows (大约)
DELETE FROM `whs_history`;
/*!40000 ALTER TABLE `whs_history` DISABLE KEYS */;
INSERT INTO `whs_history` (`id`, `name`, `content`) VALUES
	(1, '你好', 'haohaohaoahoahaoahoahohaohaohfoadhfjihdsakfjksdf'),
	(2, '你好3', 'haohaohaoahoahaoahoahohaohaohfoadhfjihdsakfjksdf');
/*!40000 ALTER TABLE `whs_history` ENABLE KEYS */;

-- 导出  表 test.whs_homepage 结构
DROP TABLE IF EXISTS `whs_homepage`;
CREATE TABLE IF NOT EXISTS `whs_homepage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `logo` varchar(50) DEFAULT NULL,
  `introduction` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在导出表  test.whs_homepage 的数据：~2 rows (大约)
DELETE FROM `whs_homepage`;
/*!40000 ALTER TABLE `whs_homepage` DISABLE KEYS */;
INSERT INTO `whs_homepage` (`id`, `time`, `address`, `logo`, `introduction`) VALUES
	(1, '2017年9月15日', '武汉国际博览中心', 'keykeykey', '中国已经中国已经中国已经中国已经中国已经中国已经中国已经中国已经中国已经中国已经Now that you’ve set up the project and build system, you can create your web service.\r\n\r\nBegin the process by thinking about service interactions.\r\n\r\nThe service will handle GET requests for /greeting, optionally with a name parameter in the query string. The GET request should return a 200 OK response with JSON in the body that represents a greeting. It should look something like this:'),
	(2, '2017年9月15日', '武汉国际博览中心', 'keykeykey', '中国已经中国已经中国已经中国已经中国已经中国已经中国已经中国已经中国已经中国已经Now that you’ve set up the project and build system, you can create your web service.\r\n\r\nBegin the process by thinking about service interactions.\r\n\r\nThe service will handle GET requests for /greeting, optionally with a name parameter in the query string. The GET request should return a 200 OK response with JSON in the body that represents a greeting. It should look something like this:');
/*!40000 ALTER TABLE `whs_homepage` ENABLE KEYS */;

-- 导出  表 test.whs_media 结构
DROP TABLE IF EXISTS `whs_media`;
CREATE TABLE IF NOT EXISTS `whs_media` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '媒体名字',
  `type` tinyint(4) NOT NULL COMMENT '媒体类型',
  `image` varchar(50) DEFAULT NULL COMMENT '媒体图片',
  `url` text COMMENT '友链',
  `principal` varchar(50) NOT NULL COMMENT '负责人名字',
  `phone` varchar(50) NOT NULL COMMENT '负责人电话',
  `tele` varchar(50) DEFAULT NULL COMMENT '办公电话',
  `mail` varchar(50) DEFAULT NULL COMMENT '电子邮件',
  `address` varchar(50) DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `apply_time` int(11) NOT NULL DEFAULT '1500790555',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='媒体表';

-- 正在导出表  test.whs_media 的数据：~0 rows (大约)
DELETE FROM `whs_media`;
/*!40000 ALTER TABLE `whs_media` DISABLE KEYS */;
INSERT INTO `whs_media` (`id`, `name`, `type`, `image`, `url`, `principal`, `phone`, `tele`, `mail`, `address`, `status`, `apply_time`) VALUES
	(1, '媒体', 1, '1', '1', '啊', '11', NULL, NULL, NULL, 1, 1500790555);
/*!40000 ALTER TABLE `whs_media` ENABLE KEYS */;

-- 导出  表 test.whs_media_apply 结构
DROP TABLE IF EXISTS `whs_media_apply`;
CREATE TABLE IF NOT EXISTS `whs_media_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '媒体名字',
  `phone` varchar(50) NOT NULL COMMENT '负责人电话',
  `principal` varchar(50) NOT NULL COMMENT '负责人名字',
  `tele` varchar(50) DEFAULT NULL COMMENT '办公电话',
  `mail` varchar(50) DEFAULT NULL COMMENT '电子邮件',
  `address` varchar(50) DEFAULT NULL,
  `apply_time` bigint(20) NOT NULL COMMENT '申请时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='媒体申请表';

-- 正在导出表  test.whs_media_apply 的数据：~30 rows (大约)
DELETE FROM `whs_media_apply`;
/*!40000 ALTER TABLE `whs_media_apply` DISABLE KEYS */;
INSERT INTO `whs_media_apply` (`id`, `name`, `phone`, `principal`, `tele`, `mail`, `address`, `apply_time`) VALUES
	(2, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(3, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(4, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(5, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(6, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790544),
	(7, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(8, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(9, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(10, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(11, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(12, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(13, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(14, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(15, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(16, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(17, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(18, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(19, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(20, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(21, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(22, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(23, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(24, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(25, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(26, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(27, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(28, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(29, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(30, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790532),
	(31, '第一媒体', '1405555555', '焦宇轩', '88888888', '4555@123.com', NULL, 1500790555);
/*!40000 ALTER TABLE `whs_media_apply` ENABLE KEYS */;

-- 导出  表 test.whs_news 结构
DROP TABLE IF EXISTS `whs_news`;
CREATE TABLE IF NOT EXISTS `whs_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `browses` bigint(20) DEFAULT '0',
  `title` varchar(50) NOT NULL COMMENT '新闻标题',
  `url` varchar(50) DEFAULT NULL COMMENT '新闻链接',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0为显示，1为过期',
  `create_time` bigint(20) NOT NULL COMMENT '添加图片时间',
  `content` text NOT NULL COMMENT '新闻内容',
  `author` varchar(50) DEFAULT '管理员' COMMENT '发布者',
  `parent` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='news';

-- 正在导出表  test.whs_news 的数据：~10 rows (大约)
DELETE FROM `whs_news`;
/*!40000 ALTER TABLE `whs_news` DISABLE KEYS */;
INSERT INTO `whs_news` (`id`, `browses`, `title`, `url`, `status`, `create_time`, `content`, `author`, `parent`) VALUES
	(1, 0, '第一条新闻', 'url', 0, 1500790552, '45484848adfadf3333333', '管理员', 0),
	(2, 0, '第二条新闻', 'url', 1, 1500790552, '45484848adfadf3333333', '管理员', 1),
	(3, 0, '你好3', 'url', 1, 1500790552, 'haohaohaoahoahaoahoahohaohaohfoadhfjihdsakfjksdf', '管理员', 0),
	(4, 0, '第四条新闻', 'url', 0, 1500790552, '45484848adfadf3333333', '管理员', 1),
	(5, 0, '第二条新闻', 'url', 0, 1500790552, '45484848adfadf3333333', '管理员', 0),
	(6, 0, '第二条新闻', 'url', 0, 1500790552, '45484848adfadf3333333', '管理员', 2),
	(7, 0, '第二条新闻', 'url', 0, 1500790552, '45484848adfadf3333333', '管理员', 0),
	(9, 0, '你好', NULL, 0, 1500799663, 'haohaohaoahoahaoahoahohaohaohfoadhfjihdsakfjksdf', '管理员', 0),
	(10, 0, '你好', NULL, 0, 1500804168, 'haohaohaoahoahaoahoahohaohaohfoadhfjihdsakfjksdf', '管理员', 0),
	(11, 0, '你好', NULL, 0, 1500804253, 'haohaohaoahoahaoahoahohaohaohfoadhfjihdsakfjksdf', '管理员', 0),
	(12, 0, '你好4', NULL, 0, 1500902541, 'haohaohaoahoahaoahoahohaohaohfoadhfjihdsakfjksdf', '管理员', 0);
/*!40000 ALTER TABLE `whs_news` ENABLE KEYS */;

-- 导出  表 test.whs_sliders 结构
DROP TABLE IF EXISTS `whs_sliders`;
CREATE TABLE IF NOT EXISTS `whs_sliders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `img_key` varchar(50) NOT NULL COMMENT '获取图片位置的key',
  `title` varchar(50) NOT NULL COMMENT '图片titile',
  `url` varchar(50) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0为显示，1为过期',
  `create_time` bigint(20) NOT NULL COMMENT '添加图片时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='首页sliders';

-- 正在导出表  test.whs_sliders 的数据：~2 rows (大约)
DELETE FROM `whs_sliders`;
/*!40000 ALTER TABLE `whs_sliders` DISABLE KEYS */;
INSERT INTO `whs_sliders` (`id`, `img_key`, `title`, `url`, `status`, `create_time`) VALUES
	(1, '898908901890', '第一张图片', 'www.baidu.com', 0, 15454545),
	(2, '485484848', '第二张图片', 'www.163.com', 0, 4554415);
/*!40000 ALTER TABLE `whs_sliders` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
