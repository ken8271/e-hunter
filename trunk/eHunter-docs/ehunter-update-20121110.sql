DROP TABLE IF EXISTS T_BCUP_HST;

##操作日志记录表
CREATE TABLE T_BCUP_HST (
  BCUP_HST_ID varchar(9) NOT NULL COMMENT '备份历史ID',
  BCUP_NM varchar(200) NOT NULL COMMENT '备份文件名',
  BCUP_TM datetime NOT NULL COMMENT '备份时间',
  BCUP_CHNL varchar(1) NOT NULL COMMENT '备份渠道',
  BCUP_BY varchar(10) COMMENT '备份人',
  PRIMARY KEY (BCUP_HST_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO T_SEQ_NBR_LST
VALUES ('SEQ_BCUP_HST' , 1 ,'备份历史编号' , '2012072401', '2012-07-25', '2012072401', '2012-07-25', 'I');
