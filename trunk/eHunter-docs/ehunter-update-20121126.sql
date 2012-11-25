ALTER TABLE `t_cd_tbl_lst` ADD COLUMN `VIEW_URL`  varchar(100) NULL AFTER `CD_TBL_MAP`;

UPDATE T_CD_TBL_LST SET VIEW_URL='/system/codetable/talentSourceManagement.do' WHERE CD_TBL_ID = '00012';

COMMIT;