DROP TABLE IF EXISTS `t_rlesd_postn`;
CREATE TABLE `t_rlesd_postn` (
  `SYS_REF_POSTN` varchar(9) NOT NULL COMMENT '职位编号',
  `POSTN_TITL` varchar(100) NOT NULL COMMENT '职位名称',
  `POSTN_CTNT` text NOT NULL COMMENT '职位内容',
  `WK_CTY` varchar(50) DEFAULT NULL COMMENT '工作城市',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_POSTN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_usr_tx_log`;
CREATE TABLE `t_usr_tx_log` (
  `TX_LOG_ID` varchar(9) NOT NULL COMMENT '日志ID',
  `USR_REC_ID` varchar(10) NOT NULL COMMENT '用户编号',
  `TX_DTTM` datetime NOT NULL COMMENT '操作时间',
  `FUNC` varchar(1) NOT NULL COMMENT '操作模块',
  `TX_MSG` varchar(200) DEFAULT NULL COMMENT '操作记录',
  PRIMARY KEY (`TX_LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;