ALTER TABLE t_cust_co
MODIFY COLUMN CO_TEL_EXCHG  varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司总机' AFTER OFCL_SITE;

INSERT INTO T_TLNT_SRC
VALUES ('001','前程无忧','www.51job.com', 1 , 'Y' , '2012072401', '2012-07-25', '2012072401', '2012-07-25', 'I');
INSERT INTO T_TLNT_SRC
VALUES ('002','智联招聘','www.zhaopin.com', 2 , 'Y' , '2012072401', '2012-07-25', '2012072401', '2012-07-25', 'I');
INSERT INTO T_TLNT_SRC
VALUES ('003','中华英才网','www.chinahr.com', 3 , 'Y' , '2012072401', '2012-07-25', '2012072401', '2012-07-25', 'I');

COMMIT;