ALTER TABLE t_cust_co
MODIFY COLUMN GP_FLAG  varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '�Ƿ��ſͻ�' AFTER CO_SZ;

ALTER TABLE t_prj
ADD COLUMN PRJ_ST  varchar(3) NULL COMMENT '��Ŀ״̬' AFTER LST_TX_ACTN,
ADD COLUMN PRJ_ADVSR  varchar(10) NULL COMMENT '��Ŀ������' AFTER PRJ_ST;