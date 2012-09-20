DROP TABLE IF EXISTS T_PRJ_ST;

##项目状态表
CREATE TABLE T_PRJ_ST (
  ST_CD varchar(1) NOT NULL COMMENT '项目状态代号',
  DISP_NM varchar(30) NOT NULL COMMENT '显示名称',
  CD_DESC varchar(100) COMMENT '代号描述',
  DISP_SEQ_NBR int COMMENT '显示顺序',
  ACTV_FLG varchar(1) COMMENT '是否启用',
  CR_BY varchar(10) COMMENT '创建人',
  CR_DTTM date COMMENT '创建时间',
  LST_UPD_BY varchar(10) COMMENT '最近更新人',
  LST_UPD_DTTM date COMMENT '最近更新时间',
  LST_TX_ACTN varchar(1) COMMENT '最近执行操作',
  PRIMARY KEY (ST_CD)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO T_PRJ_ST
VALUES ('I' , '初始化','初始化', 1 , 'Y' , '2012072401', '2012-07-25', '2012072401', '2012-07-25', 'I');
INSERT INTO T_PRJ_ST
VALUES ('P' , '搜寻中','搜寻中', 2 , 'Y' , '2012072401', '2012-07-25', '2012072401', '2012-07-25', 'I');
INSERT INTO T_PRJ_ST
VALUES ('S' , '待定','待定', 3 , 'Y' , '2012072401', '2012-07-25', '2012072401', '2012-07-25', 'I');
INSERT INTO T_PRJ_ST
VALUES ('C' , '取消','取消', 4 , 'N' , '2012072401', '2012-07-25', '2012072401', '2012-07-25', 'I');
INSERT INTO T_PRJ_ST
VALUES ('F' , '停止/结束','停止/结束', 5 , 'Y' , '2012072401', '2012-07-25', '2012072401', '2012-07-25', 'I');
INSERT INTO T_PRJ_ST
VALUES ('E' , '过期','过期', 6 , 'N' , '2012072401', '2012-07-25', '2012072401', '2012-07-25', 'I');

