CREATE DATABASE IF NOT EXISTS ehunter DEFAULT CHARACTER SET utf8;

DROP TABLE IF EXISTS T_TLNT_EDU_EXP;
DROP TABLE IF EXISTS T_TLNT_JOB_EXP;
DROP TABLE IF EXISTS T_TLNT_TRN_EXP;
DROP TABLE IF EXISTS T_TLNT_LAN_ABLT;
DROP TABLE IF EXISTS T_TLNT_PRO_SKL;
DROP TABLE IF EXISTS T_TLNT_PRJ_EXP;
DROP TABLE IF EXISTS T_TLNT_CERT;
DROP TABLE IF EXISTS T_SENR_TLNT_ATCH_INF;
DROP TABLE IF EXISTS T_TLNT_EXAM_HST;
DROP TABLE IF EXISTS T_TLNT_INTV_HST;
DROP TABLE IF EXISTS T_TLNT_CONT_HST;
DROP TABLE IF EXISTS T_PRJ_POST_SALRY_CMPS;
DROP TABLE IF EXISTS T_PRJ_POST_SCTY_WELF;
DROP TABLE IF EXISTS T_PRJ_POST_ANUL_WELF;
DROP TABLE IF EXISTS T_PRJ_POST_LAN_REQ;
DROP TABLE IF EXISTS T_PRJ_POST_KEY_WD;
DROP TABLE IF EXISTS T_PRJ_POST_DESC;
DROP TABLE IF EXISTS T_PRJ_TLNT_ASGN;
DROP TABLE IF EXISTS T_CUST_RP;
DROP TABLE IF EXISTS T_CUST_CONT_HST;
DROP TABLE IF EXISTS T_PRJ;
DROP TABLE IF EXISTS T_INT_USR_ROLE_ASGN;
DROP TABLE IF EXISTS T_INT_USR;
DROP TABLE IF EXISTS T_INT_ROLE;
DROP TABLE IF EXISTS T_CONT_SUB_TY;
DROP TABLE IF EXISTS T_CONT_TOP_TY;
DROP TABLE IF EXISTS T_CUST_GRDE;
DROP TABLE IF EXISTS T_CUST_STAT;
DROP TABLE IF EXISTS T_CO_TY;
DROP TABLE IF EXISTS T_CO_SZ;
DROP TABLE IF EXISTS T_IND_TY_SUB;
DROP TABLE IF EXISTS T_IND_TY;
DROP TABLE IF EXISTS T_POST_TY_SUB;
DROP TABLE IF EXISTS T_POST_TY;
DROP TABLE IF EXISTS T_DGRE;
DROP TABLE IF EXISTS T_TLNT_SRC;
DROP TABLE IF EXISTS T_LAN_TY;
DROP TABLE IF EXISTS T_SKL_TY;
DROP TABLE IF EXISTS T_SKL_LEVL;
DROP TABLE IF EXISTS T_CERT_TY;
DROP TABLE IF EXISTS T_SUBJ;
DROP TABLE IF EXISTS T_SALRY_TY;
DROP TABLE IF EXISTS T_SCTY_WELF;
DROP TABLE IF EXISTS T_LV_WELF;
DROP TABLE IF EXISTS T_ANUL_WELF;
DROP TABLE IF EXISTS T_CD_TBL_LST;
DROP TABLE IF EXISTS T_SYS_PARM;
DROP TABLE IF EXISTS T_SEQ_NBR_LST;
DROP TABLE IF EXISTS T_CUST_CO;
DROP TABLE IF EXISTS T_CUST_GP;
DROP TABLE IF EXISTS T_TLNT_BS_INF;

##客户集团
CREATE TABLE T_CUST_GP (
  SYS_REF_GP varchar(9) NOT NULL COMMENT '客户集团编号(YYMMDD+3位SEQENCE)',
  GP_SHRT_NM varchar(20) NOT NULL UNIQUE COMMENT '集团简称',
  GP_NM varchar(50) NOT NULL UNIQUE COMMENT '集团官方全称',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_GP)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

##客户公司
CREATE TABLE T_CUST_CO (
  SYS_REF_CUST varchar(6) NOT NULL COMMENT '客户编号(C+5位序列号)',
  CO_NM varchar(50) NOT NULL UNIQUE COMMENT '客户名称',
  CO_SHRT_NM varchar(20) UNIQUE COMMENT '客户简称',
  CUST_GRDE varchar(2) COMMENT '客户等级',
  CUST_STAT varchar(3) COMMENT '客户状态',
  OFCL_SITE varchar(50) COMMENT '官方网站',
  CO_TEL_EXCHG varchar(12) COMMENT '公司总机',
  CO_TY varchar(2) COMMENT '公司性质',
  CO_SZ varchar(3) COMMENT '公司规模',
  GP_FLAG varchar(1) COMMENT '是否集团客户',
  SYS_REF_GP varchar(9) COMMENT '集团编号',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_CUST),
  CONSTRAINT T_CUST_CO_FK_GP FOREIGN KEY (SYS_REF_GP) REFERENCES T_CUST_GP(SYS_REF_GP)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##客户公司联系人
CREATE TABLE T_CUST_RP (
  SYS_REF_RP varchar(9) NOT NULL COMMENT '联系人编号(YYMMDD+3位SEQUENCE)',
  SYS_REF_CUST varchar(6) NOT NULL COMMENT '客户公司编号',
  RP_NM varchar(30) NOT NULL COMMENT '联系人姓名',
  RP_POST_TY varchar(3) COMMENT '联系人职位类型',
  RP_POST varchar(50) COMMENT '联系人职位',
  RP_TEL varchar(12) NOT NULL COMMENT '联系人电话',
  RP_EMAIL varchar(50) NOT NULL COMMENT '联系人邮箱',
  RP_STAT varchar(2) NOT NULL COMMENT '联系人状态(在职IS/离职OS)',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_RP),
  CONSTRAINT T_CUST_RP_FK_CO FOREIGN KEY (SYS_REF_CUST) REFERENCES T_CUST_CO(SYS_REF_CUST)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##猎头项目
CREATE TABLE T_PRJ (
  SYS_REF_PRJ varchar(9) NOT NULL COMMENT '项目编号(P+8位序列号)',
  PRJ_NM varchar(50) NOT NULL COMMENT '项目名称',
  SYS_REF_CUST varchar(6) COMMENT '客户编号',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_PRJ),
  CONSTRAINT T_PRJ_FK_CO FOREIGN KEY (SYS_REF_CUST) REFERENCES T_CUST_CO(SYS_REF_CUST)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##客户联系记录
CREATE TABLE T_CUST_CONT_HST (
  SYS_REF_CONT_HST varchar(9) NOT NULL COMMENT '联系记录编号(YYMMDD+3位SEQUENCE)',
  SYS_REF_CUST varchar(6) NOT NULL COMMENT '客户编号',
  SYS_REF_PRJ varchar(9) NOT NULL COMMENT '项目编号',
  CONT_TY varchar(3) NOT NULL COMMENT '联系类型',
  CONT_REC varchar(4000) COMMENT '联系记录',
  CONT_ADVSR varchar(10) COMMENT '联系顾问',
  CONT_RMRK varchar(300) COMMENT '联系备注',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_CONT_HST),
  CONSTRAINT T_CUST_CONT_HST_FK_CO FOREIGN KEY (SYS_REF_CUST) REFERENCES T_CUST_CO(SYS_REF_CUST),
  CONSTRAINT T_CUST_CONT_HST_FK_PJ FOREIGN KEY (SYS_REF_PRJ) REFERENCES T_PRJ(SYS_REF_PRJ)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##人才基本信息
CREATE TABLE T_TLNT_BS_INF (
  SYS_REF_TLNT varchar(9) NOT NULL COMMENT '人才编号(T+8位SEQUENCE)',
  CNM varchar(30) NOT NULL COMMENT '中文名',
  ENM varchar(30) COMMENT '英文名',
  SEX varchar(1) COMMENT '性别',
  MAR_STAT varchar(2) COMMENT '婚姻状况',
  BRTH_DTTM date COMMENT '出生日期',
  NTV_PLCE varchar(30) COMMENT '籍贯',
  NW_LV_PLCE varchar(300) COMMENT '现居地',
  ONC_LV_PLCE varchar(300) COMMENT '曾居地',
  HM_TEL_NBR varchar(12) COMMENT '家庭电话',
  CO_TEL_NBR varchar(16) COMMENT '公司电话',
  PRI_TEL_NBR1 varchar(12) UNIQUE COMMENT '手机1',
  PRI_TEL_NBR2 varchar(12) UNIQUE COMMENT '手机2',
  PRI_TEL_NBR3 varchar(12) UNIQUE COMMENT '手机3',
  EMAIL varchar(30) UNIQUE COMMENT '邮箱',
  HM_ADDR varchar(300) COMMENT '家庭地址',
  HGST_DGRE varchar(3) COMMENT '最高学历',
  SELF_EVAL varchar(300) COMMENT '自我评价',
  TLNT_SRC varchar(3) COMMENT '人才来源',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_TLNT)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##人才教育经历
CREATE TABLE T_TLNT_EDU_EXP (
  SYS_REF_TLNT varchar(9) NOT NULL COMMENT '人才编号',
  EXP_SEQ_NBR int(2) NOT NULL COMMENT '经历序号',
  BEGN_DTTM date NOT NULL COMMENT '开始时间',
  GRDUAT_DTTM date COMMENT '毕业时间',
  SCHOOL varchar(50) COMMENT '学校',
  MAJOR varchar(50) COMMENT '专业',
  DEGREE varchar(3) COMMENT '学位',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_TLNT,EXP_SEQ_NBR),
  CONSTRAINT T_EDU_EXP_FK_TLNT FOREIGN KEY (SYS_REF_TLNT) REFERENCES T_TLNT_BS_INF(SYS_REF_TLNT)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##人才工作经历
CREATE TABLE T_TLNT_JOB_EXP (
  SYS_REF_TLNT varchar(9) NOT NULL COMMENT '人才编号',
  EXP_SEQ_NBR int(2) NOT NULL COMMENT '经历序号',
  BEGN_DTTM date NOT NULL COMMENT '开始时间',
  LEV_DTTM date COMMENT '离职时间',
  CO_NM varchar(50) COMMENT '公司名称',
  CO_TY varchar(2) COMMENT '企业性质',
  CO_SCOP varchar(3) COMMENT '企业规模',
  IND_TY varchar(3) COMMENT '行业类别',
  DEPT_NM varchar(50) COMMENT '部门名称',
  POST_TY varchar(3) COMMENT '职位类别',
  POST_NM varchar(50) COMMENT '职位名称',
  PRE_TAX_SALARY int COMMENT '税前月薪',
  POST_DESC varchar(300) COMMENT '职位描述',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_TLNT,EXP_SEQ_NBR),
  CONSTRAINT T_JOB_EXP_FK_TLNT FOREIGN KEY (SYS_REF_TLNT) REFERENCES T_TLNT_BS_INF(SYS_REF_TLNT)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##人才培训经历
CREATE TABLE T_TLNT_TRN_EXP (
  SYS_REF_TLNT varchar(9) NOT NULL COMMENT '人才编号',
  EXP_SEQ_NBR int(2) NOT NULL COMMENT '经历序号',
  BEGN_DTTM date NOT NULL COMMENT '开始时间',
  END_DTTM date COMMENT '结束时间',
  TRN_ORG varchar(50) NOT NULL COMMENT '培训机构',
  TRN_ADDR varchar(300) COMMENT '培训地点',
  TRN_COUS varchar(50) COMMENT '培训课程',
  GAIN_CERT varchar(50) COMMENT '获得证书',
  TRN_DESC varchar(100) COMMENT '培训描述',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_TLNT,EXP_SEQ_NBR),
  CONSTRAINT T_TRN_EXP_FK_TLNT FOREIGN KEY (SYS_REF_TLNT) REFERENCES T_TLNT_BS_INF(SYS_REF_TLNT)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##人才语言能力
CREATE TABLE T_TLNT_LAN_ABLT (
  SYS_REF_TLNT varchar(9) NOT NULL COMMENT '人才编号',
  ABLT_SEQ_NBR int(2) NOT NULL COMMENT '语言能力序号',
  LAN_TY varchar(3) NOT NULL COMMENT '语言类型',
  RW_ABLT varchar(3) COMMENT '读写能力',
  LS_ABLT varchar(3) COMMENT '听说能力',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_TLNT,ABLT_SEQ_NBR),
  CONSTRAINT T_LAN_ABLT_FK_TLNT FOREIGN KEY (SYS_REF_TLNT) REFERENCES T_TLNT_BS_INF(SYS_REF_TLNT)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##人才专业技能
CREATE TABLE T_TLNT_PRO_SKL (
  SYS_REF_TLNT varchar(9) NOT NULL COMMENT '人才编号',
  SKL_SEQ_NBR int(2) NOT NULL COMMENT '技能序号',
  SKL_TY varchar(3) COMMENT '技能类型',
  USE_DURTN int(2) COMMENT '使用时间',
  CNTL_LEVL varchar(3) COMMENT '掌握程度',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_TLNT,SKL_SEQ_NBR),
  CONSTRAINT T_RPO_TECH_FK_TLNT FOREIGN KEY (SYS_REF_TLNT) REFERENCES T_TLNT_BS_INF(SYS_REF_TLNT)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##人才项目经验
CREATE TABLE T_TLNT_PRJ_EXP (
  SYS_REF_TLNT varchar(9) NOT NULL COMMENT '人才编号',
  EXP_SEQ_NBR int(2) NOT NULL COMMENT '经验序号',
  BEG_DTTM date NOT NULL COMMENT '开始时间',
  END_DTTM date COMMENT '结束时间',
  PRJ_NM varchar(50) COMMENT '项目名称',
  PRJ_DUTY varchar(50) COMMENT '项目职责',
  PRJ_DESC varchar(300) COMMENT '项目描述',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_TLNT,EXP_SEQ_NBR),
  CONSTRAINT T_PRJ_EXP_FK_TLNT FOREIGN KEY (SYS_REF_TLNT) REFERENCES T_TLNT_BS_INF(SYS_REF_TLNT)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##人才证书
CREATE TABLE T_TLNT_CERT (
  SYS_REF_TLNT varchar(9) NOT NULL COMMENT '人才编号',
  CERT_SEQ_NBR int(2) NOT NULL COMMENT '证书序号',
  CERT_TY varchar(3) COMMENT '证书类型',
  GAIN_DTTM date COMMENT '获得时间',
  SCORE varchar(4) COMMENT '分数',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_TLNT,CERT_SEQ_NBR),
  CONSTRAINT T_CERT_FK_TLNT FOREIGN KEY (SYS_REF_TLNT) REFERENCES T_TLNT_BS_INF(SYS_REF_TLNT)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##高级人才附加信息
CREATE TABLE T_SENR_TLNT_ATCH_INF (
  SYS_REF_TLNT varchar(9) NOT NULL COMMENT '人才编号',
  BS_SALARY int COMMENT '基本年薪',
  ANUL_BNUS int COMMENT '年度奖金',
  ALLOWANCE int COMMENT '补贴/津贴',
  STOK_NBR varchar(10) COMMENT '股票',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_TLNT),
  CONSTRAINT T_SENR_ATCH_FK_TLNT FOREIGN KEY (SYS_REF_TLNT) REFERENCES T_TLNT_BS_INF(SYS_REF_TLNT)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##人才联系记录
CREATE TABLE T_TLNT_CONT_HST (
  SYS_REF_CONT_HST varchar(9) NOT NULL COMMENT '联系记录编号(YYMMDD+3位SEQUENCE)',
  SYS_REF_TLNT varchar(9) NOT NULL COMMENT '人才编号',
  SYS_REF_PRJ varchar(9) NOT NULL COMMENT '项目编号',
  CONT_TY varchar(3)  NOT NULL COMMENT '联系类型',
  CONT_REC varchar(4000) COMMENT '联系记录',
  CONT_ADVSR varchar(10) COMMENT '联系顾问',
  CONT_RMRK varchar(300) COMMENT '联系备注',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_CONT_HST),
  CONSTRAINT T_TLNT_CONT_FK_TLNT FOREIGN KEY (SYS_REF_TLNT) REFERENCES T_TLNT_BS_INF(SYS_REF_TLNT),
  CONSTRAINT T_TLNT_CONT_FK_PRJ FOREIGN KEY (SYS_REF_PRJ) REFERENCES T_PRJ(SYS_REF_PRJ)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##人才笔试记录
CREATE TABLE T_TLNT_EXAM_HST (
  SYS_REF_CONT_HST varchar(9) NOT NULL COMMENT '联系记录编号',
  EXAM_DTTM date NOT NULL COMMENT '笔试时间',
  EXAM_ADDR varchar(300) COMMENT '笔试地点',
  EXAM_SCORE int(3) COMMENT '笔试成绩',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_CONT_HST),
  CONSTRAINT T_EXAM_HST_FK_CONT FOREIGN KEY (SYS_REF_CONT_HST) REFERENCES T_TLNT_CONT_HST(SYS_REF_CONT_HST)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##人才面试记录
CREATE TABLE T_TLNT_INTV_HST (
  SYS_REF_CONT_HST varchar(9) NOT NULL COMMENT '联系记录编号',
  INTVR_NM varchar(30) COMMENT '面试官姓名',
  INTVR_POST varchar(50) COMMENT '面试官职位',
  INTV_DTTM date NOT NULL COMMENT '面试时间',
  INTV_ADDR varchar(300) COMMENT '面试地点',
  INTV_SCORE int NOT NULL COMMENT '面试成绩',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_CONT_HST),
  CONSTRAINT T_INTV_HST_FK_CONT FOREIGN KEY (SYS_REF_CONT_HST) REFERENCES T_TLNT_CONT_HST(SYS_REF_CONT_HST)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##项目职位描述
CREATE TABLE T_PRJ_POST_DESC (
  SYS_REF_POST varchar(9) NOT NULL COMMENT '项目职位编号(YYMMDD+3位SEQUENCE)',
  SYS_REF_PRJ varchar(9) NOT NULL COMMENT '项目编号',
  POST_NM varchar(30) NOT NULL COMMENT '职位名称',
  POST_TY varchar(3) NOT NULL COMMENT '职位分类',
  POST_DEPT varchar(50) COMMENT '所属部门',
  PEPT_TO varchar(50) COMMENT '汇报对象',
  UDLNG_NBR int COMMENT '下属人数',
  EXPR_DT date COMMENT '截至日期',
  YRLY_SALRY int COMMENT '职位年薪',
  DUTY_DESC varchar(4000) NOT NULL COMMENT '职责描述',
  AGE_REQ_FR int COMMENT '年龄要求from',
  AGE_REQ_TO int COMMENT '年龄要求to',
  SEX_REQ varchar(1) COMMENT '性别要求',
  SUBJ_REQ varchar(3) COMMENT '专业要求',
  JOB_EXP_REQ int COMMENT '工作年限要求',
  EDU_REQ varchar(3) COMMENT '学历要求',
  FULL_TM_EDU_FLG varchar(1) COMMENT '是否全日制统招',
  OFER_REQ_DESC varchar(4000) COMMENT '任职资格说明',
  REMK varchar(300) COMMENT '补充说明',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_POST),
  CONSTRAINT T_PRJ_POST_FK_CONT FOREIGN KEY (SYS_REF_PRJ) REFERENCES T_PRJ(SYS_REF_PRJ)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##职位薪资构成
CREATE TABLE T_PRJ_POST_SALRY_CMPS (
  SYS_REF_POST varchar(9) NOT NULL COMMENT '项目职位编号',
  SEQ_NBR int NOT NULL COMMENT '薪资序号',
  SALRY_TY varchar(3) NOT NULL COMMENT '薪资类型',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_POST,SEQ_NBR),
  CONSTRAINT T_PRJ_POST_SALRY_FK_CONT FOREIGN KEY (SYS_REF_POST) REFERENCES T_PRJ_POST_DESC(SYS_REF_POST)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##职位社会福利
CREATE TABLE T_PRJ_POST_SCTY_WELF (
  SYS_REF_POST varchar(9) NOT NULL COMMENT '项目职位编号',
  SEQ_NBR int NOT NULL COMMENT '福利序号',
  WELF_TY varchar(3) NOT NULL COMMENT '社会福利类型',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_POST,SEQ_NBR),
  CONSTRAINT T_PRJ_POST_SCTY_WELF_FK_CONT FOREIGN KEY (SYS_REF_POST) REFERENCES T_PRJ_POST_DESC(SYS_REF_POST)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##职位年假福利
CREATE TABLE T_PRJ_POST_ANUL_WELF (
  SYS_REF_POST varchar(9) NOT NULL COMMENT '项目职位编号',
  SEQ_NBR int NOT NULL COMMENT '福利序号',
  ANUL_WELF_TY varchar(3) NOT NULL COMMENT '年假福利类型',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_POST,SEQ_NBR),
  CONSTRAINT T_PRJ_POST_ANUL_WELF_FK_CONT FOREIGN KEY (SYS_REF_POST) REFERENCES T_PRJ_POST_DESC(SYS_REF_POST)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##职位语言要求
CREATE TABLE T_PRJ_POST_LAN_REQ (
  SYS_REF_POST varchar(9) NOT NULL COMMENT '项目职位编号',
  SEQ_NBR int NOT NULL COMMENT '语言序号',
  LAN_TY varchar(3) NOT NULL COMMENT '语言类型',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_POST,SEQ_NBR),
  CONSTRAINT T_PRJ_POST_LAN_REQ_FK_CONT FOREIGN KEY (SYS_REF_POST) REFERENCES T_PRJ_POST_DESC(SYS_REF_POST)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##职位关键词
CREATE TABLE T_PRJ_POST_KEY_WD (
  SYS_REF_POST varchar(9) NOT NULL COMMENT '项目职位编号',
  SEQ_NBR int NOT NULL COMMENT '语言序号',
  KEY_WD_CNT varchar(20) NOT NULL COMMENT '关键词内容',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_POST,SEQ_NBR),
  CONSTRAINT T_PRJ_POST_KEY_WD_FK_CONT FOREIGN KEY (SYS_REF_POST) REFERENCES T_PRJ_POST_DESC(SYS_REF_POST)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##项目人才分配
CREATE TABLE T_PRJ_TLNT_ASGN (
  SYS_REF_PRJ varchar(9) NOT NULL COMMENT '项目编号',
  SYS_REF_TLNT varchar(9) NOT NULL COMMENT '人才编号',
  PRIMARY KEY (SYS_REF_PRJ,SYS_REF_TLNT),
  CONSTRAINT T_PRJ_TLNT_ASGN_FK_PRJ FOREIGN KEY (SYS_REF_PRJ) REFERENCES T_PRJ(SYS_REF_PRJ),
  CONSTRAINT T_PRJ_TLNT_ASGN_FK_TLNT FOREIGN KEY (SYS_REF_TLNT) REFERENCES T_TLNT_BS_INF(SYS_REF_TLNT)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##系统内部用户
CREATE TABLE T_INT_USR (
  USR_REC_ID varchar(10) NOT NULL COMMENT '用户编号',
  LOGIN_ID varchar(20) NOT NULL UNIQUE COMMENT '登录名',
  PWD varchar(50) NOT NULL COMMENT '密码',
  STAF_NBR varchar(10) COMMENT '员工号',
  ACCT_ST varchar(2) COMMENT '账户状态',
  ENM varchar(30) COMMENT '英文名',
  CNM varchar(30) COMMENT '中文名',
  PWD_EXPR_FLG varchar(1) COMMENT '密码过期标志',
  CO_TEL_NBR varchar(15) COMMENT '公司电话',
  PR_TEL_NBR varchar(12) COMMENT '手机号码',
  EMAIL varchar(50) NOT NULL COMMENT '邮箱',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (USR_REC_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##系统内部用户角色
CREATE TABLE T_INT_ROLE (
  SYS_REF_ROLE varchar(3) NOT NULL COMMENT '角色编号',
  ROLE_ID varchar(20) NOT NULL UNIQUE COMMENT '角色ID',
  ROLE_NM varchar(50) NOT NULL COMMENT '角色名称',
  ROLE_DESC varchar(300) COMMENT '角色描述',
  ROLE_STAT varchar(2) COMMENT '角色状态',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_ROLE)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##系统内部用户角色分配
CREATE TABLE T_INT_USR_ROLE_ASGN (
  USR_REC_ID varchar(10) NOT NULL COMMENT '用户编号',
  SYS_REF_ROLE varchar(3) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (USR_REC_ID,SYS_REF_ROLE),
  CONSTRAINT T_ROLE_ASGN_USR FOREIGN KEY (USR_REC_ID) REFERENCES T_INT_USR(USR_REC_ID),
  CONSTRAINT T_ROLE_ASGN_ROLE FOREIGN KEY (SYS_REF_ROLE) REFERENCES T_INT_ROLE(SYS_REF_ROLE)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_CONT_TOP_TY
CREATE TABLE T_CONT_TOP_TY (
  SYS_REF_TOP_TY varchar(3) NOT NULL COMMENT '联系类型编号',
  CONT_TGT varchar(1) NOT NULL COMMENT '联系目标',
  CONT_TY_NM varchar(50) NOT NULL COMMENT '联系类型名称',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_TOP_TY)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_CONT_SUB_TY
CREATE TABLE T_CONT_SUB_TY (
  SYS_REF_TY varchar(3) NOT NULL COMMENT '联系类型编号',
  TOP_TY varchar(3) NOT NULL COMMENT '上级类型',
  CONT_TY_NM varchar(50) NOT NULL COMMENT '联系类型名称',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_TY)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_CUST_GRDE
CREATE TABLE T_CUST_GRDE (
  GEDE_CD varchar(2) NOT NULL COMMENT '客户等级编号',
  DISP_NM varchar(30) NOT NULL COMMENT '等级显示名称',
  CD_DESC varchar(100) COMMENT '客户等级描述',
  DISP_SEQ_NBR int COMMENT '显示顺序',
  ACTV_FLG varchar(1) COMMENT '是否启用',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (GEDE_CD)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_CUST_STAT
CREATE TABLE T_CUST_STAT (
  STAT_CD varchar(3) NOT NULL COMMENT '客户状态编号',
  DISP_NM varchar(30) NOT NULL COMMENT '状态显示名称',
  CD_DESC varchar(100) COMMENT '状态描述',
  DISP_SEQ_NBR int COMMENT '显示顺序',
  ACTV_FLG varchar(1) COMMENT '是否启用',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (STAT_CD)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_CO_TY
CREATE TABLE T_CO_TY (
  TY_CD varchar(2) NOT NULL COMMENT '企业性质代号',
  DISP_NM varchar(30) NOT NULL COMMENT '显示名称',
  CD_DESC varchar(100) COMMENT '代号描述',
  DISP_SEQ_NBR int COMMENT '显示顺序',
  ACTV_FLG varchar(1) COMMENT '是否启用',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (TY_CD)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_CO_SZ
CREATE TABLE T_CO_SZ (
  CO_SZ_ID varchar(3) COMMENT '企业规模编号',
  DISP_NM varchar(30) NOT NULL COMMENT '显示名称',
  CD_DESC varchar(100) COMMENT '代号描述',
  DISP_SEQ_NBR int COMMENT '显示顺序',
  ACTV_FLG varchar(1) COMMENT '是否启用',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (CO_SZ_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_IND_TY
CREATE TABLE T_IND_TY (
  TY_CD varchar(3) NOT NULL COMMENT '行业代号',
  DISP_NM varchar(50) NOT NULL COMMENT '显示名称',
  CD_DESC varchar(100) COMMENT '代号描述',
  DISP_SEQ_NBR int COMMENT '显示顺序',
  ACTV_FLG varchar(1) COMMENT '是否启用',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (TY_CD)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_IND_TY_SUB
CREATE TABLE T_IND_TY_SUB (
  TY_CD varchar(3) NOT NULL COMMENT '行业代号',
  TOP_TY varchar(3) NOT NULL COMMENT '上级行业代号',
  DISP_NM varchar(50) NOT NULL COMMENT '显示名称',
  CD_DESC varchar(100) COMMENT '代号描述',
  DISP_SEQ_NBR int COMMENT '显示顺序',
  ACTV_FLG varchar(1) COMMENT '是否启用',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (TY_CD),
  CONSTRAINT T_IND_TY_FK FOREIGN KEY (TOP_TY) REFERENCES T_IND_TY(TY_CD)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_POST_TY
CREATE TABLE T_POST_TY (
  TY_CD varchar(3) NOT NULL COMMENT '职位类型代号',
  DISP_NM varchar(50) NOT NULL COMMENT '显示名称',
  CD_DESC varchar(100) COMMENT '代号描述',
  DISP_SEQ_NBR int COMMENT '显示顺序',
  ACTV_FLG varchar(1) COMMENT '是否启用',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (TY_CD)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_POST_TY_SUB
CREATE TABLE T_POST_TY_SUB (
  TY_CD varchar(3) NOT NULL COMMENT '职位类型详细代号',
  TOP_TY varchar(3) NOT NULL COMMENT '上级职位类型代号',
  DISP_NM varchar(50) NOT NULL COMMENT '显示名称',
  CD_DESC varchar(100) COMMENT '代号描述',
  DISP_SEQ_NBR int COMMENT '显示顺序',
  ACTV_FLG varchar(1) COMMENT '是否启用',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (TY_CD),
  CONSTRAINT T_POST_TY_FK FOREIGN KEY (TOP_TY) REFERENCES T_POST_TY(TY_CD)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_DGRE
CREATE TABLE T_DGRE (
  DGRE_CD varchar(3) NOT NULL COMMENT '学历代号',
  DISP_NM varchar(50) NOT NULL COMMENT '显示名称',
  CD_DESC varchar(100) COMMENT '代号描述',
  DISP_SEQ_NBR int COMMENT '显示顺序',
  ACTV_FLG varchar(1) COMMENT '是否启用',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (DGRE_CD)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_TLNT_SRC
CREATE TABLE T_TLNT_SRC (
  SRC_ID varchar(3) COMMENT '人才来源编号',
  DISP_NM varchar(50) NOT NULL COMMENT '显示名称',
  OFCL_SITE varchar(50) COMMENT '官方网站',
  DISP_SEQ_NBR int COMMENT '显示顺序',
  ACTV_FLG varchar(1) COMMENT '是否启用',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SRC_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_LAN_TY
CREATE TABLE T_LAN_TY (
  TY_CD varchar(3) NOT NULL COMMENT '语言类型编号',
  DISP_NM varchar(50) NOT NULL COMMENT '显示名称',
  CD_DESC varchar(100) COMMENT '代号描述',
  DISP_SEQ_NBR int COMMENT '显示顺序',
  ACTV_FLG varchar(1) COMMENT '是否启用',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (TY_CD)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_SKL_TY
CREATE TABLE T_SKL_TY (
  TY_CD varchar(3) NOT NULL COMMENT '技能类型代号',
  DISP_NM varchar(50) NOT NULL COMMENT '显示名称',
  CD_DESC varchar(100) COMMENT '代号描述',
  DISP_SEQ_NBR int COMMENT '显示顺序',
  ACTV_FLG varchar(1) COMMENT '是否启用',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (TY_CD)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_SKL_LEVL
CREATE TABLE T_SKL_LEVL (
  LEVL_CD varchar(3) NOT NULL COMMENT 'level代号',
  DISP_NM varchar(50) NOT NULL COMMENT '显示名称',
  CD_DESC varchar(100) COMMENT '代号描述',
  DISP_SEQ_NBR int COMMENT '显示顺序',
  ACTV_FLG varchar(1) COMMENT '是否启用',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (LEVL_CD)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_CERT_TY
CREATE TABLE T_CERT_TY (
  TY_CD varchar(3) NOT NULL COMMENT '证书类型代号',
  DISP_NM varchar(50) NOT NULL COMMENT '显示名称',
  CD_DESC varchar(100) COMMENT '代号描述',
  DISP_SEQ_NBR int COMMENT '显示顺序',
  ACTV_FLG varchar(1) COMMENT '是否启用',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (TY_CD)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_SUBJ
CREATE TABLE T_SUBJ (
  SUBJ_CD varchar(3) NOT NULL COMMENT '专业代号',
  DISP_NM varchar(50) NOT NULL COMMENT '显示名称',
  CD_DESC varchar(100) COMMENT '代号描述',
  DISP_SEQ_NBR int COMMENT '显示顺序',
  ACTV_FLG varchar(1) COMMENT '是否启用',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SUBJ_CD)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_SALRY_TY
CREATE TABLE T_SALRY_TY (
  TY_CD varchar(3) NOT NULL COMMENT '类型代号',
  DISP_NM varchar(50) NOT NULL COMMENT '显示名称',
  CD_DESC varchar(100) COMMENT '代号描述',
  DISP_SEQ_NBR int COMMENT '显示顺序',
  ACTV_FLG varchar(1) COMMENT '是否启用',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (TY_CD)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_SCTY_WELF
CREATE TABLE T_SCTY_WELF (
  TY_CD varchar(3) NOT NULL COMMENT '类型代号',
  DISP_NM varchar(50) NOT NULL COMMENT '显示名称',
  CD_DESC varchar(100) COMMENT '代号描述',
  DISP_SEQ_NBR int COMMENT '显示顺序',
  ACTV_FLG varchar(1) COMMENT '是否启用',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (TY_CD)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_LV_WELF
CREATE TABLE T_LV_WELF (
  TY_CD varchar(3) NOT NULL COMMENT '类型代号',
  DISP_NM varchar(50) NOT NULL COMMENT '显示名称',
  CD_DESC varchar(100) COMMENT '代号描述',
  DISP_SEQ_NBR int COMMENT '显示顺序',
  ACTV_FLG varchar(1) COMMENT '是否启用',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (TY_CD)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_ANUL_WELF
CREATE TABLE T_ANUL_WELF (
  TY_CD varchar(3) NOT NULL COMMENT '类型代号',
  DISP_NM varchar(50) NOT NULL COMMENT '显示名称',
  CD_DESC varchar(100) COMMENT '代号描述',
  DISP_SEQ_NBR int COMMENT '显示顺序',
  ACTV_FLG varchar(1) COMMENT '是否启用',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (TY_CD)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_CD_TBL_LST
CREATE TABLE T_CD_TBL_LST (
  CD_TBL_ID varchar(5) NOT NULL COMMENT 'Code Table ID',
  CD_TBL_NM varchar(50) COMMENT 'Code Table Name',
  CD_TBL_DESC varchar(100) COMMENT 'Description Of Code Table',
  CD_TBL_MAP varchar(50) NOT NULL COMMENT 'Map of code table and db table',
  ACTV_FLG varchar(1) COMMENT '是否启用',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (CD_TBL_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_SYS_PARM
CREATE TABLE T_SYS_PARM (
  P_KEY varchar(35) NOT NULL COMMENT 'Key',
  VAL_TY varchar(1) COMMENT 'Value Type',
  VAL varchar(100) COMMENT 'Value',
  PARM_DESC varchar(100) COMMENT 'Description Of Parameter',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (P_KEY)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

##T_SEQ_NBR_LST
CREATE TABLE T_SEQ_NBR_LST (
  SEQ_NM varchar(30) NOT NULL COMMENT 'Sequence Name',
  SEQ_VAL int NOT NULL COMMENT 'Sequence Value',
  SEQ_DESC varchar(100) COMMENT 'Description Of Sequence',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SEQ_NM)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
