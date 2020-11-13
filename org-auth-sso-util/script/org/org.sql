-- 树形组织查询   递归查询 MySQL 5只能用视图实现 以下是MySQL8的用法
WITH RECURSIVE td AS (
    SELECT * FROM org_group WHERE id = 193
    UNION ALL
    SELECT c.* FROM org_group c, td WHERE c.id=td.parent_id
)SELECT * FROM td ORDER BY td.id;




# Dump of table org_group   组织表
# ------------------------------------------------------------

DROP TABLE IF EXISTS `org_group`;

CREATE TABLE `org_group` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `name` varchar(16) NOT NULL DEFAULT '' COMMENT '组织节点名称',
  `sale_type` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '类型',
  `leader` int unsigned NOT NULL DEFAULT '0' COMMENT '小组领导',
  `status` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '状态:1-正常,2-封停',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '软删除,0是有效,1是删除',
  `ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `sale_director_id` int unsigned NOT NULL DEFAULT '0' COMMENT '总监ID',
  `parent_id` int DEFAULT '0' COMMENT '上层id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工组织节点表';


CREATE TABLE `org_member` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `name` varchar(16) NOT NULL DEFAULT '' COMMENT '员工名称',
  `email` varchar(32) NOT NULL DEFAULT '' COMMENT '公司邮箱',
  `type` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '类型',
  `group_id` int unsigned NOT NULL DEFAULT '0' COMMENT '小组ID',
  `status` tinyint unsigned NOT NULL COMMENT '状态（1-有效，2-无效）',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '软删除,0是有效,1是删除',
  `ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `level` tinyint NOT NULL DEFAULT '0' COMMENT '角色，0销售1组长2总监',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_email` (`email`),
  KEY `ix_group_id` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8 COMMENT='员工表';