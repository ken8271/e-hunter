CREATE DATABASE IF NOT EXISTS ehunter DEFAULT CHARACTER SET utf8;

DROP TABLE IF EXISTS T_INFO_CENTR;
DROP TABLE IF EXISTS T_RLESD_POSTN;

##资讯中心
CREATE TABLE T_INFO_CENTR (
  SYS_REF_INFO varchar(9) NOT NULL COMMENT '资讯ID',
  INFO_TITL varchar(100) NOT NULL COMMENT '资讯名称',
  INFO_CTNT text NOT NULL COMMENT '信息内容',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_INFO)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

##已发布职位
CREATE TABLE T_RLESD_POSTN (
  SYS_REF_POSTN varchar(9) NOT NULL COMMENT '职位编号',
  POSTN_TITL varchar(100) NOT NULL COMMENT '职位名称',
  POSTN_CTNT text NOT NULL COMMENT '职位内容',
  WK_PRVC varchar(3) COMMENT '工作省份',
  WK_CTY varchar(3) COMMENT '工作城市',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_POSTN)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;