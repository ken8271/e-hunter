DROP TABLE IF EXISTS T_USR_TX_LOG;

##操作日志记录表
CREATE TABLE T_USR_TX_LOG (
  TX_LOG_ID varchar(9) NOT NULL COMMENT '日志ID',
  USR_REC_ID varchar(10) NOT NULL COMMENT '用户编号',
  TX_DTTM datetime NOT NULL COMMENT '操作时间',
  FUNC varchar(1) NOT NULL COMMENT '操作模块',
  TX_MSG varchar(200) COMMENT '操作记录',
  PRIMARY KEY (TX_LOG_ID),
  CONSTRAINT T_USR_TX_LOG_FK_USR FOREIGN KEY (USR_REC_ID) REFERENCES T_INT_USR(USR_REC_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO T_SEQ_NBR_LST
VALUES ('SEQ_TX_LOG' , 1 ,'日志编号' , '2012072401', '2012-07-25', '2012072401', '2012-07-25', 'I');
