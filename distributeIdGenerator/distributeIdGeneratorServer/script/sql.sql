CREATE TABLE `id_info` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `biz` varchar(225) NOT NULL DEFAULT '',
  `certificate` varchar(512) DEFAULT NULL,
  `step` int DEFAULT NULL,
  `max` bigint DEFAULT NULL,
  `form` int DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_uni_biz` (`biz`),
  KEY `idx_add_time` (`add_time`),
  KEY `idx_update_time` (`update_time`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;