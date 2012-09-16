DROP TABLE IF EXISTS T_PRJ_TLNT_ASGN;
DROP TABLE IF EXISTS T_PRJ_TLNT_LIB;

##项目人才库
CREATE TABLE T_PRJ_TLNT_LIB (
  SYS_REF_PRJ varchar(9) NOT NULL COMMENT '项目编号',
  SYS_REF_TLNT varchar(9) NOT NULL COMMENT '人才编号',
  TLNT_ST varchar(2) COMMENT '候选人才状态',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (SYS_REF_PRJ , SYS_REF_TLNT),
  CONSTRAINT T_PRJ_TLNT_ASGN_FK_PRJ FOREIGN KEY (SYS_REF_PRJ) REFERENCES T_PRJ(SYS_REF_PRJ),
  CONSTRAINT T_PRJ_TLNT_ASGN_FK_TLNT FOREIGN KEY (SYS_REF_TLNT) REFERENCES T_TLNT_BS_INF(SYS_REF_TLNT)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

