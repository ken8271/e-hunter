CREATE DATABASE IF NOT EXISTS ehunter DEFAULT CHARACTER SET utf8;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `t_anul_welf`
-- ----------------------------
DROP TABLE IF EXISTS `t_anul_welf`;
CREATE TABLE `t_anul_welf` (
  `TY_CD` varchar(3) NOT NULL COMMENT '类型代号',
  `DISP_NM` varchar(50) NOT NULL COMMENT '显示名称',
  `CD_DESC` varchar(100) DEFAULT NULL COMMENT '代号描述',
  `DISP_SEQ_NBR` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ACTV_FLG` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`TY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_bcup_hst`
-- ----------------------------
DROP TABLE IF EXISTS `t_bcup_hst`;
CREATE TABLE `t_bcup_hst` (
  `BCUP_HST_ID` varchar(9) NOT NULL COMMENT '备份历史ID',
  `BCUP_NM` varchar(200) NOT NULL COMMENT '备份文件名',
  `BCUP_TM` datetime NOT NULL COMMENT '备份时间',
  `BCUP_CHNL` varchar(1) NOT NULL COMMENT '备份渠道',
  `BCUP_BY` varchar(10) DEFAULT NULL COMMENT '备份人',
  PRIMARY KEY (`BCUP_HST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_cddt_st`
-- ----------------------------
DROP TABLE IF EXISTS `t_cddt_st`;
CREATE TABLE `t_cddt_st` (
  `ST_CD` varchar(3) NOT NULL COMMENT '候选人状态代号',
  `DISP_NM` varchar(30) NOT NULL COMMENT '显示名称',
  `CD_DESC` varchar(100) DEFAULT NULL COMMENT '代号描述',
  `DISP_SEQ_NBR` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ACTV_FLG` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`ST_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_cd_tbl_lst`
-- ----------------------------
DROP TABLE IF EXISTS `t_cd_tbl_lst`;
CREATE TABLE `t_cd_tbl_lst` (
  `CD_TBL_ID` varchar(5) NOT NULL COMMENT 'Code Table ID',
  `CD_TBL_NM` varchar(50) DEFAULT NULL COMMENT 'Code Table Name',
  `CD_TBL_DESC` varchar(100) DEFAULT NULL COMMENT 'Description Of Code Table',
  `CD_TBL_MAP` varchar(50) NOT NULL COMMENT 'Map of code table and db table',
  `VIEW_URL` varchar(100) DEFAULT NULL,
  `ACTV_FLG` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`CD_TBL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_cert_ty`
-- ----------------------------
DROP TABLE IF EXISTS `t_cert_ty`;
CREATE TABLE `t_cert_ty` (
  `TY_CD` varchar(3) NOT NULL COMMENT '证书类型代号',
  `DISP_NM` varchar(50) NOT NULL COMMENT '显示名称',
  `CD_DESC` varchar(100) DEFAULT NULL COMMENT '代号描述',
  `DISP_SEQ_NBR` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ACTV_FLG` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`TY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_city`
-- ----------------------------
DROP TABLE IF EXISTS `t_city`;
CREATE TABLE `t_city` (
  `CITY_CD` varchar(3) NOT NULL COMMENT '市编号',
  `PRVC_CD` varchar(3) NOT NULL COMMENT '省编号',
  `DISP_NM` varchar(30) NOT NULL COMMENT '地级市名称',
  `CD_DESC` varchar(30) DEFAULT NULL COMMENT '代号描述',
  `DISP_SEQ_NBR` int(11) DEFAULT NULL COMMENT '显示序号',
  `ACTV_FLG` varchar(1) NOT NULL COMMENT '是否可用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`CITY_CD`),
  KEY `T_CITY_PRVC_FK` (`PRVC_CD`),
  CONSTRAINT `T_CITY_PRVC_FK` FOREIGN KEY (`PRVC_CD`) REFERENCES `t_prvc` (`PRVC_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_co_sz`
-- ----------------------------
DROP TABLE IF EXISTS `t_co_sz`;
CREATE TABLE `t_co_sz` (
  `CO_SZ_ID` varchar(3) NOT NULL DEFAULT '' COMMENT '企业规模编号',
  `DISP_NM` varchar(30) NOT NULL COMMENT '显示名称',
  `CD_DESC` varchar(100) DEFAULT NULL COMMENT '代号描述',
  `DISP_SEQ_NBR` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ACTV_FLG` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`CO_SZ_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_co_ty`
-- ----------------------------
DROP TABLE IF EXISTS `t_co_ty`;
CREATE TABLE `t_co_ty` (
  `TY_CD` varchar(2) NOT NULL COMMENT '企业性质代号',
  `DISP_NM` varchar(30) NOT NULL COMMENT '显示名称',
  `CD_DESC` varchar(100) DEFAULT NULL COMMENT '代号描述',
  `DISP_SEQ_NBR` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ACTV_FLG` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`TY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_cust_co`
-- ----------------------------
DROP TABLE IF EXISTS `t_cust_co`;
CREATE TABLE `t_cust_co` (
  `SYS_REF_CUST` varchar(6) NOT NULL COMMENT '客户编号(C+5位序列号)',
  `CO_NM` varchar(50) NOT NULL COMMENT '客户名称',
  `CO_SHRT_NM` varchar(20) DEFAULT NULL COMMENT '客户简称',
  `CUST_GRDE` varchar(2) DEFAULT NULL COMMENT '客户等级',
  `CUST_STAT` varchar(3) DEFAULT NULL COMMENT '客户状态',
  `OFCL_SITE` varchar(50) DEFAULT NULL COMMENT '官方网站',
  `CO_TEL_EXCHG` varchar(13) DEFAULT NULL COMMENT '公司总机',
  `CO_TY` varchar(2) DEFAULT NULL COMMENT '公司性质',
  `CO_SZ` varchar(3) DEFAULT NULL COMMENT '公司规模',
  `GP_FLAG` varchar(3) DEFAULT NULL COMMENT '是否集团客户',
  `SYS_REF_GP` varchar(9) DEFAULT NULL COMMENT '集团编号',
  `CUST_DESC` varchar(4000) DEFAULT NULL,
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_CUST`),
  UNIQUE KEY `CO_NM` (`CO_NM`),
  UNIQUE KEY `CO_SHRT_NM` (`CO_SHRT_NM`),
  KEY `T_CUST_CO_FK_GP` (`SYS_REF_GP`),
  CONSTRAINT `T_CUST_CO_FK_GP` FOREIGN KEY (`SYS_REF_GP`) REFERENCES `t_cust_gp` (`SYS_REF_GP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_cust_cont_hst`
-- ----------------------------
DROP TABLE IF EXISTS `t_cust_cont_hst`;
CREATE TABLE `t_cust_cont_hst` (
  `SYS_REF_CONT_HST` varchar(9) NOT NULL COMMENT '联系记录编号(YYMMDD+3位SEQUENCE)',
  `SYS_REF_CUST` varchar(6) NOT NULL COMMENT '客户编号',
  `SYS_REF_RP` varchar(9) NOT NULL COMMENT '联系人编号',
  `CONT_REC` varchar(4000) DEFAULT NULL COMMENT '联系记录',
  `CONT_ADVSR` varchar(10) DEFAULT NULL COMMENT '联系顾问',
  `CONT_RMRK` varchar(300) DEFAULT NULL COMMENT '联系备注',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_CONT_HST`),
  KEY `T_CUST_CONT_HST_FK_CO` (`SYS_REF_CUST`),
  KEY `T_CUST_CONT_HST_FK_RP` (`SYS_REF_RP`),
  KEY `T_CUST_CONT_HST_FK_AD` (`CONT_ADVSR`),
  CONSTRAINT `T_CUST_CONT_HST_FK_AD` FOREIGN KEY (`CONT_ADVSR`) REFERENCES `t_int_usr` (`USR_REC_ID`),
  CONSTRAINT `T_CUST_CONT_HST_FK_CO` FOREIGN KEY (`SYS_REF_CUST`) REFERENCES `t_cust_co` (`SYS_REF_CUST`),
  CONSTRAINT `T_CUST_CONT_HST_FK_RP` FOREIGN KEY (`SYS_REF_RP`) REFERENCES `t_cust_rp` (`SYS_REF_RP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_cust_gp`
-- ----------------------------
DROP TABLE IF EXISTS `t_cust_gp`;
CREATE TABLE `t_cust_gp` (
  `SYS_REF_GP` varchar(9) NOT NULL COMMENT '客户集团编号(YYMMDD+3位SEQENCE)',
  `GP_SHRT_NM` varchar(20) NOT NULL COMMENT '集团简称',
  `GP_NM` varchar(50) NOT NULL COMMENT '集团官方全称',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_GP`),
  UNIQUE KEY `GP_SHRT_NM` (`GP_SHRT_NM`),
  UNIQUE KEY `GP_NM` (`GP_NM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_cust_grde`
-- ----------------------------
DROP TABLE IF EXISTS `t_cust_grde`;
CREATE TABLE `t_cust_grde` (
  `GEDE_CD` varchar(2) NOT NULL COMMENT '客户等级编号',
  `DISP_NM` varchar(30) NOT NULL COMMENT '等级显示名称',
  `CD_DESC` varchar(100) DEFAULT NULL COMMENT '客户等级描述',
  `DISP_SEQ_NBR` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ACTV_FLG` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`GEDE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_cust_rp`
-- ----------------------------
DROP TABLE IF EXISTS `t_cust_rp`;
CREATE TABLE `t_cust_rp` (
  `SYS_REF_RP` varchar(9) NOT NULL COMMENT '联系人编号(YYMMDD+3位SEQUENCE)',
  `SYS_REF_CUST` varchar(6) DEFAULT NULL COMMENT '客户编号',
  `RP_NM` varchar(30) NOT NULL COMMENT '联系人姓名',
  `RP_POST_TY` varchar(3) DEFAULT NULL COMMENT '联系人职位类型',
  `RP_POST` varchar(50) DEFAULT NULL COMMENT '联系人职位',
  `RP_TEL` varchar(50) NOT NULL COMMENT '联系人电话',
  `RP_EMAIL` varchar(50) NOT NULL COMMENT '联系人邮箱',
  `RP_STAT` varchar(2) NOT NULL COMMENT '联系人状态(在职IS/离职OS)',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_RP`),
  KEY `T_CUST_RP_FK_CO` (`SYS_REF_CUST`),
  CONSTRAINT `T_CUST_RP_FK_CO` FOREIGN KEY (`SYS_REF_CUST`) REFERENCES `t_cust_co` (`SYS_REF_CUST`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_cust_stat`
-- ----------------------------
DROP TABLE IF EXISTS `t_cust_stat`;
CREATE TABLE `t_cust_stat` (
  `STAT_CD` varchar(3) NOT NULL COMMENT '客户状态编号',
  `DISP_NM` varchar(30) NOT NULL COMMENT '状态显示名称',
  `CD_DESC` varchar(100) DEFAULT NULL COMMENT '状态描述',
  `DISP_SEQ_NBR` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ACTV_FLG` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`STAT_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_dgre`
-- ----------------------------
DROP TABLE IF EXISTS `t_dgre`;
CREATE TABLE `t_dgre` (
  `DGRE_CD` varchar(3) NOT NULL COMMENT '学历代号',
  `DISP_NM` varchar(50) NOT NULL COMMENT '显示名称',
  `CD_DESC` varchar(100) DEFAULT NULL COMMENT '代号描述',
  `DISP_SEQ_NBR` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ACTV_FLG` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`DGRE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_ind_ty`
-- ----------------------------
DROP TABLE IF EXISTS `t_ind_ty`;
CREATE TABLE `t_ind_ty` (
  `TY_CD` varchar(3) NOT NULL COMMENT '行业代号',
  `DISP_NM` varchar(50) NOT NULL COMMENT '显示名称',
  `CD_DESC` varchar(100) DEFAULT NULL COMMENT '代号描述',
  `DISP_SEQ_NBR` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ACTV_FLG` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`TY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_ind_ty_sub`
-- ----------------------------
DROP TABLE IF EXISTS `t_ind_ty_sub`;
CREATE TABLE `t_ind_ty_sub` (
  `TY_CD` varchar(3) NOT NULL COMMENT '行业代号',
  `TOP_TY` varchar(3) NOT NULL COMMENT '上级行业代号',
  `DISP_NM` varchar(50) NOT NULL COMMENT '显示名称',
  `CD_DESC` varchar(100) DEFAULT NULL COMMENT '代号描述',
  `DISP_SEQ_NBR` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ACTV_FLG` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`TY_CD`),
  KEY `T_IND_TY_FK` (`TOP_TY`),
  CONSTRAINT `T_IND_TY_FK` FOREIGN KEY (`TOP_TY`) REFERENCES `t_ind_ty` (`TY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_info_centr`
-- ----------------------------
DROP TABLE IF EXISTS `t_info_centr`;
CREATE TABLE `t_info_centr` (
  `SYS_REF_INFO` varchar(9) NOT NULL COMMENT '资讯ID',
  `INFO_TITL` varchar(100) NOT NULL COMMENT '资讯名称',
  `INFO_CTNT` text NOT NULL COMMENT '信息内容',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_INFO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_int_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_int_role`;
CREATE TABLE `t_int_role` (
  `SYS_REF_ROLE` varchar(3) NOT NULL COMMENT '角色编号',
  `ROLE_ID` varchar(20) NOT NULL COMMENT '角色ID',
  `ROLE_NM` varchar(50) NOT NULL COMMENT '角色名称',
  `ROLE_DESC` varchar(300) DEFAULT NULL COMMENT '角色描述',
  `ROLE_STAT` varchar(2) DEFAULT NULL COMMENT '角色状态',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_ROLE`),
  UNIQUE KEY `ROLE_ID` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_int_usr`
-- ----------------------------
DROP TABLE IF EXISTS `t_int_usr`;
CREATE TABLE `t_int_usr` (
  `USR_REC_ID` varchar(10) NOT NULL COMMENT '用户编号',
  `LOGIN_ID` varchar(20) NOT NULL COMMENT '登录名',
  `PWD` varchar(50) NOT NULL COMMENT '密码',
  `STAF_NBR` varchar(10) DEFAULT NULL COMMENT '员工号',
  `ACCT_ST` varchar(2) DEFAULT NULL COMMENT '账户状态',
  `ENM` varchar(30) DEFAULT NULL COMMENT '英文名',
  `CNM` varchar(30) DEFAULT NULL COMMENT '中文名',
  `PWD_EXPR_FLG` varchar(1) DEFAULT NULL COMMENT '密码过期标志',
  `CO_TEL_NBR` varchar(15) DEFAULT NULL COMMENT '公司电话',
  `PR_TEL_NBR` varchar(12) DEFAULT NULL COMMENT '手机号码',
  `EMAIL` varchar(50) NOT NULL COMMENT '邮箱',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`USR_REC_ID`),
  UNIQUE KEY `LOGIN_ID` (`LOGIN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_int_usr_role_asgn`
-- ----------------------------
DROP TABLE IF EXISTS `t_int_usr_role_asgn`;
CREATE TABLE `t_int_usr_role_asgn` (
  `USR_REC_ID` varchar(10) NOT NULL COMMENT '用户编号',
  `SYS_REF_ROLE` varchar(3) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`USR_REC_ID`,`SYS_REF_ROLE`),
  KEY `T_ROLE_ASGN_ROLE` (`SYS_REF_ROLE`),
  CONSTRAINT `T_ROLE_ASGN_ROLE` FOREIGN KEY (`SYS_REF_ROLE`) REFERENCES `t_int_role` (`SYS_REF_ROLE`),
  CONSTRAINT `T_ROLE_ASGN_USR` FOREIGN KEY (`USR_REC_ID`) REFERENCES `t_int_usr` (`USR_REC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_lan_ty`
-- ----------------------------
DROP TABLE IF EXISTS `t_lan_ty`;
CREATE TABLE `t_lan_ty` (
  `TY_CD` varchar(3) NOT NULL COMMENT '语言类型编号',
  `DISP_NM` varchar(50) NOT NULL COMMENT '显示名称',
  `CD_DESC` varchar(100) DEFAULT NULL COMMENT '代号描述',
  `DISP_SEQ_NBR` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ACTV_FLG` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`TY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_lv_welf`
-- ----------------------------
DROP TABLE IF EXISTS `t_lv_welf`;
CREATE TABLE `t_lv_welf` (
  `TY_CD` varchar(3) NOT NULL COMMENT '类型代号',
  `DISP_NM` varchar(50) NOT NULL COMMENT '显示名称',
  `CD_DESC` varchar(100) DEFAULT NULL COMMENT '代号描述',
  `DISP_SEQ_NBR` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ACTV_FLG` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`TY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_post_ty`
-- ----------------------------
DROP TABLE IF EXISTS `t_post_ty`;
CREATE TABLE `t_post_ty` (
  `TY_CD` varchar(3) NOT NULL COMMENT '职位类型代号',
  `DISP_NM` varchar(50) NOT NULL COMMENT '显示名称',
  `CD_DESC` varchar(100) DEFAULT NULL COMMENT '代号描述',
  `DISP_SEQ_NBR` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ACTV_FLG` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`TY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_post_ty_sub`
-- ----------------------------
DROP TABLE IF EXISTS `t_post_ty_sub`;
CREATE TABLE `t_post_ty_sub` (
  `TY_CD` varchar(3) NOT NULL COMMENT '职位类型详细代号',
  `TOP_TY` varchar(3) NOT NULL COMMENT '上级职位类型代号',
  `DISP_NM` varchar(50) NOT NULL COMMENT '显示名称',
  `CD_DESC` varchar(100) DEFAULT NULL COMMENT '代号描述',
  `DISP_SEQ_NBR` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ACTV_FLG` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`TY_CD`),
  KEY `T_POST_TY_FK` (`TOP_TY`),
  CONSTRAINT `T_POST_TY_FK` FOREIGN KEY (`TOP_TY`) REFERENCES `t_post_ty` (`TY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_prj`
-- ----------------------------
DROP TABLE IF EXISTS `t_prj`;
CREATE TABLE `t_prj` (
  `SYS_REF_PRJ` varchar(9) NOT NULL COMMENT '项目编号(P+8位序列号)',
  `PRJ_NM` varchar(50) NOT NULL COMMENT '项目名称',
  `SYS_REF_CUST` varchar(6) DEFAULT NULL COMMENT '客户编号',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  `PRJ_ST` varchar(3) DEFAULT NULL COMMENT '项目状态',
  `PRJ_ADVSR` varchar(10) DEFAULT NULL COMMENT '项目负责人',
  PRIMARY KEY (`SYS_REF_PRJ`),
  KEY `T_PRJ_FK_CO` (`SYS_REF_CUST`),
  CONSTRAINT `T_PRJ_FK_CO` FOREIGN KEY (`SYS_REF_CUST`) REFERENCES `t_cust_co` (`SYS_REF_CUST`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_prj_post_desc`
-- ----------------------------
DROP TABLE IF EXISTS `t_prj_post_desc`;
CREATE TABLE `t_prj_post_desc` (
  `SYS_REF_POST` varchar(9) NOT NULL COMMENT '项目职位编号',
  `EXPT_TLNT_NBR` int(4) DEFAULT NULL,
  `POST_NM` varchar(30) NOT NULL COMMENT '职位名称',
  `POST_TY` varchar(3) NOT NULL COMMENT '职位分类',
  `POST_DEPT` varchar(50) DEFAULT NULL COMMENT '所属部门',
  `PEPT_TO` varchar(50) DEFAULT NULL COMMENT '汇报对象',
  `UDLNG_NBR` int(11) DEFAULT NULL COMMENT '下属人数',
  `WK_CTS` varchar(30) DEFAULT NULL COMMENT '工作地点',
  `EXPR_DT` date DEFAULT NULL COMMENT '截至日期',
  `YRLY_SALRY_FR` int(11) DEFAULT NULL COMMENT '职位年薪from',
  `YRLY_SALRY_TO` int(11) DEFAULT NULL COMMENT '职位年薪to',
  `SALRY_CMPS` varchar(20) DEFAULT NULL COMMENT '薪资构成',
  `SCTY_WELF` varchar(20) DEFAULT NULL COMMENT '社会福利',
  `RSDTL_WELF` varchar(20) DEFAULT NULL COMMENT '居住福利',
  `ANUL_LVE_WELF` varchar(20) DEFAULT NULL COMMENT '年假福利',
  `DUTY_DESC` varchar(4000) NOT NULL COMMENT '职责描述',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_POST`),
  CONSTRAINT `T_PRJ_POST_DESC_FK_PRJ` FOREIGN KEY (`SYS_REF_POST`) REFERENCES `t_prj` (`SYS_REF_PRJ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_prj_post_key_wd`
-- ----------------------------
DROP TABLE IF EXISTS `t_prj_post_key_wd`;

-- ----------------------------
--  Table structure for `t_prj_post_req`
-- ----------------------------
DROP TABLE IF EXISTS `t_prj_post_req`;
CREATE TABLE `t_prj_post_req` (
  `SYS_REF_POST` varchar(9) NOT NULL COMMENT '项目职位编号',
  `AG_FR` int(11) DEFAULT NULL COMMENT '年龄要求from',
  `AG_TO` int(11) DEFAULT NULL COMMENT '年龄要求to',
  `GNDR_REQ` varchar(1) DEFAULT NULL COMMENT '性别要求',
  `SUBJ_REQ` varchar(3) DEFAULT NULL COMMENT '专业要求',
  `JOB_EXP_REQ` int(11) DEFAULT NULL COMMENT '工作年限要求',
  `EDU_REQ` varchar(3) DEFAULT NULL COMMENT '学历要求',
  `FL_TM_EDU_FLG` varchar(1) DEFAULT NULL COMMENT '是否全日制统招',
  `LAN_REQ` varchar(30) DEFAULT NULL COMMENT '语言要求',
  `OFER_REQ_DESC` varchar(4000) DEFAULT NULL COMMENT '任职资格说明',
  `EXPT_INDS` varchar(20) DEFAULT NULL COMMENT '期望行业',
  `REMK` varchar(300) DEFAULT NULL COMMENT '补充说明',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_POST`),
  CONSTRAINT `T_PRJ_POST_REQ_FK_PRJ` FOREIGN KEY (`SYS_REF_POST`) REFERENCES `t_prj` (`SYS_REF_PRJ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_prj_st`
-- ----------------------------
DROP TABLE IF EXISTS `t_prj_st`;
CREATE TABLE `t_prj_st` (
  `ST_CD` varchar(1) NOT NULL COMMENT '项目状态代号',
  `DISP_NM` varchar(30) NOT NULL COMMENT '显示名称',
  `CD_DESC` varchar(100) DEFAULT NULL COMMENT '代号描述',
  `DISP_SEQ_NBR` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ACTV_FLG` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`ST_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_prj_tlnt_lib`
-- ----------------------------
DROP TABLE IF EXISTS `t_prj_tlnt_lib`;
CREATE TABLE `t_prj_tlnt_lib` (
  `SYS_REF_PRJ` varchar(9) NOT NULL COMMENT '项目编号',
  `SYS_REF_TLNT` varchar(9) NOT NULL COMMENT '人才编号',
  `TLNT_ST` varchar(3) DEFAULT NULL COMMENT '候选人才状态',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_PRJ`,`SYS_REF_TLNT`),
  KEY `T_PRJ_TLNT_ASGN_FK_TLNT` (`SYS_REF_TLNT`),
  CONSTRAINT `T_PRJ_TLNT_ASGN_FK_PRJ` FOREIGN KEY (`SYS_REF_PRJ`) REFERENCES `t_prj` (`SYS_REF_PRJ`),
  CONSTRAINT `T_PRJ_TLNT_ASGN_FK_TLNT` FOREIGN KEY (`SYS_REF_TLNT`) REFERENCES `t_tlnt_bs_inf` (`SYS_REF_TLNT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_prvc`
-- ----------------------------
DROP TABLE IF EXISTS `t_prvc`;
CREATE TABLE `t_prvc` (
  `PRVC_CD` varchar(3) NOT NULL COMMENT '省编号',
  `DISP_NM` varchar(30) NOT NULL COMMENT '省名称',
  `CD_DESC` varchar(30) DEFAULT NULL COMMENT '代号描述',
  `DISP_SEQ_NBR` int(11) NOT NULL COMMENT '显示序号',
  `ACTV_FLG` varchar(1) NOT NULL COMMENT '是否可用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`PRVC_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_rlesd_postn`
-- ----------------------------
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

-- ----------------------------
--  Table structure for `t_salry_ty`
-- ----------------------------
DROP TABLE IF EXISTS `t_salry_ty`;
CREATE TABLE `t_salry_ty` (
  `TY_CD` varchar(3) NOT NULL COMMENT '类型代号',
  `DISP_NM` varchar(50) NOT NULL COMMENT '显示名称',
  `CD_DESC` varchar(100) DEFAULT NULL COMMENT '代号描述',
  `DISP_SEQ_NBR` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ACTV_FLG` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`TY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_scty_welf`
-- ----------------------------
DROP TABLE IF EXISTS `t_scty_welf`;
CREATE TABLE `t_scty_welf` (
  `TY_CD` varchar(3) NOT NULL COMMENT '类型代号',
  `DISP_NM` varchar(50) NOT NULL COMMENT '显示名称',
  `CD_DESC` varchar(100) DEFAULT NULL COMMENT '代号描述',
  `DISP_SEQ_NBR` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ACTV_FLG` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`TY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_senr_tlnt_atch_inf`
-- ----------------------------
DROP TABLE IF EXISTS `t_senr_tlnt_atch_inf`;

-- ----------------------------
--  Table structure for `t_seq_nbr_lst`
-- ----------------------------
DROP TABLE IF EXISTS `t_seq_nbr_lst`;
CREATE TABLE `t_seq_nbr_lst` (
  `SEQ_NM` varchar(30) NOT NULL COMMENT 'Sequence Name',
  `SEQ_VAL` int(11) NOT NULL COMMENT 'Sequence Value',
  `SEQ_DESC` varchar(100) DEFAULT NULL COMMENT 'Description Of Sequence',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SEQ_NM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_skl_levl`
-- ----------------------------
DROP TABLE IF EXISTS `t_skl_levl`;
CREATE TABLE `t_skl_levl` (
  `LEVL_CD` varchar(3) NOT NULL COMMENT 'level代号',
  `DISP_NM` varchar(50) NOT NULL COMMENT '显示名称',
  `CD_DESC` varchar(100) DEFAULT NULL COMMENT '代号描述',
  `DISP_SEQ_NBR` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ACTV_FLG` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`LEVL_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_skl_ty`
-- ----------------------------
DROP TABLE IF EXISTS `t_skl_ty`;
CREATE TABLE `t_skl_ty` (
  `TY_CD` varchar(3) NOT NULL COMMENT '技能类型代号',
  `DISP_NM` varchar(50) NOT NULL COMMENT '显示名称',
  `CD_DESC` varchar(100) DEFAULT NULL COMMENT '代号描述',
  `DISP_SEQ_NBR` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ACTV_FLG` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`TY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_subj`
-- ----------------------------
DROP TABLE IF EXISTS `t_subj`;
CREATE TABLE `t_subj` (
  `SUBJ_CD` varchar(3) NOT NULL COMMENT '专业代号',
  `SUBJ_TY` varchar(3) NOT NULL COMMENT '专业类型',
  `DISP_NM` varchar(50) NOT NULL COMMENT '显示名称',
  `CD_DESC` varchar(100) DEFAULT NULL COMMENT '代号描述',
  `DISP_SEQ_NBR` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ACTV_FLG` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SUBJ_CD`),
  KEY `T_SUBJ_FK_TY` (`SUBJ_TY`),
  CONSTRAINT `T_SUBJ_FK_TY` FOREIGN KEY (`SUBJ_TY`) REFERENCES `t_subj_ty` (`TY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_subj_ty`
-- ----------------------------
DROP TABLE IF EXISTS `t_subj_ty`;
CREATE TABLE `t_subj_ty` (
  `TY_CD` varchar(3) NOT NULL COMMENT '类型代号',
  `DISP_NM` varchar(50) NOT NULL COMMENT '显示名称',
  `CD_DESC` varchar(100) DEFAULT NULL COMMENT '代号描述',
  `DISP_SEQ_NBR` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ACTV_FLG` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`TY_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_sys_parm`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_parm`;
CREATE TABLE `t_sys_parm` (
  `P_KEY` varchar(35) NOT NULL COMMENT 'Key',
  `VAL_TY` varchar(1) DEFAULT NULL COMMENT 'Value Type',
  `VAL` varchar(100) DEFAULT NULL COMMENT 'Value',
  `PARM_DESC` varchar(100) DEFAULT NULL COMMENT 'Description Of Parameter',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`P_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_tlnt_bs_inf`
-- ----------------------------
DROP TABLE IF EXISTS `t_tlnt_bs_inf`;
CREATE TABLE `t_tlnt_bs_inf` (
  `SYS_REF_TLNT` varchar(9) NOT NULL COMMENT '人才编号(T+8位SEQUENCE)',
  `CNM` varchar(30) NOT NULL COMMENT '中文名',
  `ENM` varchar(30) DEFAULT NULL COMMENT '英文名',
  `SEX` varchar(1) DEFAULT NULL COMMENT '性别',
  `MAR_STAT` varchar(2) DEFAULT NULL COMMENT '婚姻状况',
  `BRTH_DTTM` date DEFAULT NULL COMMENT '出生日期',
  `NTV_PLCE` varchar(30) DEFAULT NULL COMMENT '籍贯',
  `NW_LV_PLCE` varchar(300) DEFAULT NULL COMMENT '现居地',
  `ONC_LV_PLCE` varchar(300) DEFAULT NULL COMMENT '曾居地',
  `HM_TEL_NBR` varchar(50) DEFAULT NULL COMMENT '家庭电话',
  `CO_TEL_NBR` varchar(50) DEFAULT NULL COMMENT '公司电话',
  `PRI_TEL_NBR1` varchar(50) DEFAULT NULL COMMENT '手机1',
  `PRI_TEL_NBR2` varchar(50) DEFAULT NULL COMMENT '手机2',
  `PRI_TEL_NBR3` varchar(50) DEFAULT NULL COMMENT '手机3',
  `QQ_NBR` varchar(15) DEFAULT NULL,
  `MSN` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `HM_ADDR` varchar(300) DEFAULT NULL COMMENT '家庭地址',
  `HGST_DGRE` varchar(3) DEFAULT NULL COMMENT '最高学历',
  `SELF_EVAL` varchar(300) DEFAULT NULL COMMENT '自我评价',
  `TLNT_SRC` varchar(3) DEFAULT NULL COMMENT '人才来源',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_TLNT`),
  UNIQUE KEY `PRI_TEL_NBR1` (`PRI_TEL_NBR1`),
  UNIQUE KEY `PRI_TEL_NBR2` (`PRI_TEL_NBR2`),
  UNIQUE KEY `PRI_TEL_NBR3` (`PRI_TEL_NBR3`),
  UNIQUE KEY `EMAIL` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_tlnt_cert`
-- ----------------------------
DROP TABLE IF EXISTS `t_tlnt_cert`;
CREATE TABLE `t_tlnt_cert` (
  `SYS_REF_RSUM` varchar(12) NOT NULL COMMENT '简历编号',
  `CERT_SEQ_NBR` int(2) NOT NULL COMMENT '证书序号',
  `CERT_NM` varchar(50) DEFAULT NULL COMMENT '证书名称',
  `GAIN_DTTM` date DEFAULT NULL COMMENT '获得时间',
  `SCORE` varchar(4) DEFAULT NULL COMMENT '分数',
  `CERT_DESC` varchar(1000) DEFAULT NULL,
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_RSUM`,`CERT_SEQ_NBR`),
  CONSTRAINT `T_CERT_FK_RSUM` FOREIGN KEY (`SYS_REF_RSUM`) REFERENCES `t_tlnt_rsum` (`SYS_REF_RSUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_tlnt_cont_hst`
-- ----------------------------
DROP TABLE IF EXISTS `t_tlnt_cont_hst`;
CREATE TABLE `t_tlnt_cont_hst` (
  `SYS_REF_CONT_HST` varchar(9) NOT NULL COMMENT '联系记录编号(YYMMDD+3位SEQUENCE)',
  `SYS_REF_TLNT` varchar(9) NOT NULL COMMENT '人才编号',
  `SYS_REF_PRJ` varchar(9) NOT NULL COMMENT '项目编号',
  `CONT_TY` varchar(3) NOT NULL COMMENT '联系类型',
  `CONT_REC` varchar(4000) DEFAULT NULL COMMENT '联系记录',
  `CONT_ADVSR` varchar(10) DEFAULT NULL COMMENT '联系顾问',
  `CONT_RMRK` varchar(300) DEFAULT NULL COMMENT '联系备注',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_CONT_HST`),
  KEY `T_TLNT_CONT_FK_TLNT` (`SYS_REF_TLNT`),
  KEY `T_TLNT_CONT_FK_PRJ` (`SYS_REF_PRJ`),
  CONSTRAINT `T_TLNT_CONT_FK_PRJ` FOREIGN KEY (`SYS_REF_PRJ`) REFERENCES `t_prj` (`SYS_REF_PRJ`),
  CONSTRAINT `T_TLNT_CONT_FK_TLNT` FOREIGN KEY (`SYS_REF_TLNT`) REFERENCES `t_tlnt_bs_inf` (`SYS_REF_TLNT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_tlnt_edu_exp`
-- ----------------------------
DROP TABLE IF EXISTS `t_tlnt_edu_exp`;
CREATE TABLE `t_tlnt_edu_exp` (
  `SYS_REF_RSUM` varchar(12) NOT NULL COMMENT '简历编号',
  `EXP_SEQ_NBR` int(2) NOT NULL COMMENT '经历序号',
  `BEGN_DTTM` date NOT NULL COMMENT '开始时间',
  `GRDUAT_DTTM` date DEFAULT NULL COMMENT '毕业时间',
  `SCHOOL` varchar(50) DEFAULT NULL COMMENT '学校',
  `MAJOR` varchar(50) DEFAULT NULL COMMENT '专业',
  `DEGREE` varchar(3) DEFAULT NULL COMMENT '学位',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_RSUM`,`EXP_SEQ_NBR`),
  CONSTRAINT `T_EDU_EXP_FK_RSUM` FOREIGN KEY (`SYS_REF_RSUM`) REFERENCES `t_tlnt_rsum` (`SYS_REF_RSUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_tlnt_emp_hst`
-- ----------------------------
DROP TABLE IF EXISTS `t_tlnt_emp_hst`;
CREATE TABLE `t_tlnt_emp_hst` (
  `SYS_REF_TLNT` varchar(9) NOT NULL COMMENT '人才编号',
  `SEQ_NBR` int(3) NOT NULL COMMENT '经历序号',
  `BEGN_DTTM` date NOT NULL COMMENT '开始时间',
  `LEV_DTTM` date DEFAULT NULL COMMENT '离职时间',
  `CO_NM` varchar(50) DEFAULT NULL COMMENT '公司名称',
  `IND_TY` varchar(3) DEFAULT NULL COMMENT '行业类别',
  `POST_TY` varchar(20) DEFAULT NULL COMMENT '所任职位',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_TLNT`,`SEQ_NBR`),
  CONSTRAINT `T_TLNT_EMP_HST_FK_TLNT` FOREIGN KEY (`SYS_REF_TLNT`) REFERENCES `t_tlnt_bs_inf` (`SYS_REF_TLNT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_tlnt_exam_hst`
-- ----------------------------
DROP TABLE IF EXISTS `t_tlnt_exam_hst`;
CREATE TABLE `t_tlnt_exam_hst` (
  `SYS_REF_CONT_HST` varchar(9) NOT NULL COMMENT '联系记录编号',
  `EXAM_DTTM` date NOT NULL COMMENT '笔试时间',
  `EXAM_ADDR` varchar(300) DEFAULT NULL COMMENT '笔试地点',
  `EXAM_SCORE` int(3) DEFAULT NULL COMMENT '笔试成绩',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_CONT_HST`),
  CONSTRAINT `T_EXAM_HST_FK_CONT` FOREIGN KEY (`SYS_REF_CONT_HST`) REFERENCES `t_tlnt_cont_hst` (`SYS_REF_CONT_HST`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_tlnt_intv_hst`
-- ----------------------------
DROP TABLE IF EXISTS `t_tlnt_intv_hst`;
CREATE TABLE `t_tlnt_intv_hst` (
  `SYS_REF_CONT_HST` varchar(9) NOT NULL COMMENT '联系记录编号',
  `INTVR_NM` varchar(30) DEFAULT NULL COMMENT '面试官姓名',
  `INTVR_POST` varchar(50) DEFAULT NULL COMMENT '面试官职位',
  `INTV_DTTM` date NOT NULL COMMENT '面试时间',
  `INTV_ADDR` varchar(300) DEFAULT NULL COMMENT '面试地点',
  `INTV_SCORE` int(11) NOT NULL COMMENT '面试成绩',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_CONT_HST`),
  CONSTRAINT `T_INTV_HST_FK_CONT` FOREIGN KEY (`SYS_REF_CONT_HST`) REFERENCES `t_tlnt_cont_hst` (`SYS_REF_CONT_HST`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_tlnt_jb_int`
-- ----------------------------
DROP TABLE IF EXISTS `t_tlnt_jb_int`;
CREATE TABLE `t_tlnt_jb_int` (
  `SYS_REF_RSUM` varchar(12) NOT NULL COMMENT '简历编号',
  `JB_TY` varchar(2) NOT NULL COMMENT '期望工作性质(FT-全职/PT-兼职/IS-实习)',
  `EXP_ADDR` varchar(50) NOT NULL COMMENT '期望工作地点',
  `EXP_POST` varchar(3) NOT NULL COMMENT '期望职业',
  `EXP_IND` varchar(3) NOT NULL COMMENT '期望行业',
  `EXP_SALRY` varchar(10) NOT NULL COMMENT '期望月薪',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_RSUM`),
  CONSTRAINT `T_JB_INT_RSUM_FK` FOREIGN KEY (`SYS_REF_RSUM`) REFERENCES `t_tlnt_rsum` (`SYS_REF_RSUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_tlnt_job_exp`
-- ----------------------------
DROP TABLE IF EXISTS `t_tlnt_job_exp`;
CREATE TABLE `t_tlnt_job_exp` (
  `SYS_REF_RSUM` varchar(12) NOT NULL COMMENT '简历编号',
  `EXP_SEQ_NBR` int(2) NOT NULL COMMENT '经历序号',
  `BEGN_DTTM` date NOT NULL COMMENT '开始时间',
  `LEV_DTTM` date DEFAULT NULL COMMENT '离职时间',
  `CO_NM` varchar(50) DEFAULT NULL COMMENT '公司名称',
  `CO_TY` varchar(2) DEFAULT NULL COMMENT '企业性质',
  `CO_SCOP` varchar(3) DEFAULT NULL COMMENT '企业规模',
  `IND_TY` varchar(3) DEFAULT NULL COMMENT '行业类别',
  `DEPT_NM` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `POST_TY` varchar(3) DEFAULT NULL COMMENT '职位类别',
  `POST_NM` varchar(50) DEFAULT NULL COMMENT '职位名称',
  `PRE_TAX_SALARY` int(11) DEFAULT NULL COMMENT '税前月薪',
  `POST_DESC` varchar(300) DEFAULT NULL COMMENT '职位描述',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_RSUM`,`EXP_SEQ_NBR`),
  CONSTRAINT `T_JOB_EXP_FK_RSUM` FOREIGN KEY (`SYS_REF_RSUM`) REFERENCES `t_tlnt_rsum` (`SYS_REF_RSUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_tlnt_lan_ablt`
-- ----------------------------
DROP TABLE IF EXISTS `t_tlnt_lan_ablt`;
CREATE TABLE `t_tlnt_lan_ablt` (
  `SYS_REF_RSUM` varchar(12) NOT NULL COMMENT '简历编号',
  `ABLT_SEQ_NBR` int(2) NOT NULL COMMENT '语言能力序号',
  `LAN_TY` varchar(3) NOT NULL COMMENT '语言类型',
  `RW_ABLT` varchar(3) DEFAULT NULL COMMENT '读写能力',
  `LS_ABLT` varchar(3) DEFAULT NULL COMMENT '听说能力',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_RSUM`,`ABLT_SEQ_NBR`),
  CONSTRAINT `T_LAN_ABLT_FK_RSUM` FOREIGN KEY (`SYS_REF_RSUM`) REFERENCES `t_tlnt_rsum` (`SYS_REF_RSUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_tlnt_prj_exp`
-- ----------------------------
DROP TABLE IF EXISTS `t_tlnt_prj_exp`;
CREATE TABLE `t_tlnt_prj_exp` (
  `SYS_REF_RSUM` varchar(12) NOT NULL COMMENT '简历编号',
  `EXP_SEQ_NBR` int(2) NOT NULL COMMENT '经验序号',
  `BEG_DTTM` date NOT NULL COMMENT '开始时间',
  `END_DTTM` date DEFAULT NULL COMMENT '结束时间',
  `PRJ_NM` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `PRJ_DUTY` varchar(300) DEFAULT NULL COMMENT '项目职责',
  `PRJ_DESC` varchar(1000) DEFAULT NULL COMMENT '项目描述',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_RSUM`,`EXP_SEQ_NBR`),
  CONSTRAINT `T_PRJ_EXP_FK_RSUM` FOREIGN KEY (`SYS_REF_RSUM`) REFERENCES `t_tlnt_rsum` (`SYS_REF_RSUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_tlnt_pro_skl`
-- ----------------------------
DROP TABLE IF EXISTS `t_tlnt_pro_skl`;
CREATE TABLE `t_tlnt_pro_skl` (
  `SYS_REF_RSUM` varchar(12) NOT NULL COMMENT '简历编号',
  `SKL_SEQ_NBR` int(2) NOT NULL COMMENT '技能序号',
  `SKL_NM` varchar(50) DEFAULT NULL,
  `SKL_TY` varchar(3) DEFAULT NULL COMMENT '技能类型',
  `USE_DURTN` int(2) DEFAULT NULL COMMENT '使用时间',
  `CNTL_LEVL` varchar(3) DEFAULT NULL COMMENT '掌握程度',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_RSUM`,`SKL_SEQ_NBR`),
  CONSTRAINT `T_RPO_TECH_FK_RSUM` FOREIGN KEY (`SYS_REF_RSUM`) REFERENCES `t_tlnt_rsum` (`SYS_REF_RSUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_tlnt_rsum`
-- ----------------------------
DROP TABLE IF EXISTS `t_tlnt_rsum`;
CREATE TABLE `t_tlnt_rsum` (
  `SYS_REF_RSUM` varchar(12) NOT NULL COMMENT '人才简历编号(R+人才编号+3位序列号)',
  `SYS_REF_TLNT` varchar(9) DEFAULT NULL,
  `RSUM_SEQ_NBR` int(3) DEFAULT NULL,
  `RSUM_NM` varchar(50) NOT NULL COMMENT '简历名称',
  `RSUM_LAN` varchar(2) NOT NULL COMMENT '简历语言版本',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_RSUM`),
  KEY `T_TLNT_RSUM_TLNT_FK` (`SYS_REF_TLNT`),
  CONSTRAINT `T_TLNT_RSUM_TLNT_FK` FOREIGN KEY (`SYS_REF_TLNT`) REFERENCES `t_tlnt_bs_inf` (`SYS_REF_TLNT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_tlnt_slf_evlutn`
-- ----------------------------
DROP TABLE IF EXISTS `t_tlnt_slf_evlutn`;
CREATE TABLE `t_tlnt_slf_evlutn` (
  `SYS_REF_RSUM` varchar(12) NOT NULL COMMENT '简历编号',
  `CNT` varchar(1000) DEFAULT NULL COMMENT '内容',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_RSUM`),
  CONSTRAINT `T_SLF_EVLUTN_RSUM_FK` FOREIGN KEY (`SYS_REF_RSUM`) REFERENCES `t_tlnt_rsum` (`SYS_REF_RSUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_tlnt_src`
-- ----------------------------
DROP TABLE IF EXISTS `t_tlnt_src`;
CREATE TABLE `t_tlnt_src` (
  `SRC_ID` varchar(3) NOT NULL DEFAULT '' COMMENT '人才来源编号',
  `DISP_NM` varchar(50) NOT NULL COMMENT '显示名称',
  `OFCL_SITE` varchar(50) DEFAULT NULL COMMENT '官方网站',
  `DISP_SEQ_NBR` int(11) DEFAULT NULL COMMENT '显示顺序',
  `ACTV_FLG` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SRC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_tlnt_trn_exp`
-- ----------------------------
DROP TABLE IF EXISTS `t_tlnt_trn_exp`;
CREATE TABLE `t_tlnt_trn_exp` (
  `SYS_REF_RSUM` varchar(12) NOT NULL COMMENT '简历编号',
  `EXP_SEQ_NBR` int(2) NOT NULL COMMENT '经历序号',
  `BEGN_DTTM` date NOT NULL COMMENT '开始时间',
  `END_DTTM` date DEFAULT NULL COMMENT '结束时间',
  `TRN_ORG` varchar(50) NOT NULL COMMENT '培训机构',
  `TRN_ADDR` varchar(300) DEFAULT NULL COMMENT '培训地点',
  `TRN_COUS` varchar(50) DEFAULT NULL COMMENT '培训课程',
  `GAIN_CERT` varchar(50) DEFAULT NULL COMMENT '获得证书',
  `TRN_DESC` varchar(100) DEFAULT NULL COMMENT '培训描述',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_RSUM`,`EXP_SEQ_NBR`),
  CONSTRAINT `T_TRN_EXP_FK_RSUM` FOREIGN KEY (`SYS_REF_RSUM`) REFERENCES `t_tlnt_rsum` (`SYS_REF_RSUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_upld_cv`
-- ----------------------------
DROP TABLE IF EXISTS `t_upld_cv`;
CREATE TABLE `t_upld_cv` (
  `SYS_REF_CV` varchar(9) NOT NULL COMMENT '简历编号(YYMMDD+3位SEQUENCE)',
  `SYS_REF_TLNT` varchar(9) NOT NULL COMMENT '人才编号',
  `CV_NM` varchar(50) NOT NULL COMMENT '简历名称',
  `CV_LAN` varchar(2) NOT NULL COMMENT '简历语言',
  `UPLD_LCTN` varchar(200) DEFAULT NULL COMMENT '简历上传相对路径',
  `SWF_LCTN` varchar(200) DEFAULT NULL COMMENT 'SWF相对路径',
  `ATCH_SZ` varchar(10) DEFAULT NULL COMMENT '文件大小',
  `ENCRY_FLG` varchar(1) DEFAULT NULL COMMENT '是否已加密',
  `CONVT_FLG` varchar(1) DEFAULT NULL COMMENT '是否已转换',
  `CR_BY` varchar(10) DEFAULT NULL COMMENT '创建人',
  `CR_DTTM` date DEFAULT NULL COMMENT '创建时间',
  `LST_UPD_BY` varchar(10) DEFAULT NULL COMMENT '最近更新人',
  `LST_UPD_DTTM` date DEFAULT NULL COMMENT '最近更新时间',
  `LST_TX_ACTN` varchar(1) DEFAULT NULL COMMENT '最近执行操作',
  PRIMARY KEY (`SYS_REF_CV`),
  KEY `T_ATCH_UPLD_CV_FK_TLNT` (`SYS_REF_TLNT`),
  CONSTRAINT `T_ATCH_UPLD_CV_FK_TLNT` FOREIGN KEY (`SYS_REF_TLNT`) REFERENCES `t_tlnt_bs_inf` (`SYS_REF_TLNT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_usr_tx_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_usr_tx_log`;
CREATE TABLE `t_usr_tx_log` (
  `TX_LOG_ID` varchar(9) NOT NULL COMMENT '日志ID',
  `USR_REC_ID` varchar(10) NOT NULL COMMENT '用户编号',
  `TX_DTTM` datetime NOT NULL COMMENT '操作时间',
  `FUNC` varchar(1) NOT NULL COMMENT '操作模块',
  `TX_MSG` varchar(200) DEFAULT NULL COMMENT '操作记录',
  PRIMARY KEY (`TX_LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

