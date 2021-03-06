DROP TABLE IF EXISTS T_TLNT_EMP_HST;

##人才职业经历(概况)
CREATE TABLE T_TLNT_EMP_HST (
  SYS_REF_TLNT varchar(9) NOT NULL COMMENT '人才编号',
  SEQ_NBR int(3) NOT NULL COMMENT '经历序号',
  BEGN_DTTM date NOT NULL COMMENT '开始时间',
  LEV_DTTM date COMMENT '离职时间',
  CO_NM varchar(50) COMMENT '公司名称',
  IND_TY varchar(3) COMMENT '行业类别',
  POST_TY varchar(20) COMMENT '所任职位',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_TLNT,SEQ_NBR),
  CONSTRAINT T_TLNT_EMP_HST_FK_TLNT FOREIGN KEY (SYS_REF_TLNT) REFERENCES T_TLNT_BS_INF(SYS_REF_TLNT)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;