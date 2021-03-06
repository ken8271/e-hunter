DROP TABLE IF EXISTS T_CUST_CONT_HST;

##客户联系记录
CREATE TABLE T_CUST_CONT_HST (
  SYS_REF_CONT_HST varchar(9) NOT NULL COMMENT '联系记录编号(YYMMDD+3位SEQUENCE)',
  SYS_REF_CUST varchar(6) NOT NULL COMMENT '客户编号',
  SYS_REF_RP varchar(9) NOT NULL COMMENT '联系人编号',
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
  CONSTRAINT T_CUST_CONT_HST_FK_RP FOREIGN KEY (SYS_REF_RP) REFERENCES T_CUST_RP(SYS_REF_RP),
  CONSTRAINT T_CUST_CONT_HST_FK_AD FOREIGN KEY (CONT_ADVSR) REFERENCES T_INT_USR(USR_REC_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;