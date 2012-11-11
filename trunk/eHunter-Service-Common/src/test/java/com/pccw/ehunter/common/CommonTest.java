package com.pccw.ehunter.common;

import java.io.File;

import org.junit.Test;

public class CommonTest {
	private final String DB_URL = "localhost";
	private final String DB_NAME = "eHunter";
	private final String DB_USER = "root";
	private final String DB_PASSWORD = "Zshm19851128";

	private final String EX_SQL_DISC = "D:";
	private final String EX_SQL_DIR = "eHunter";
	private final String EX_SQL_FILE = "eHunter.sql";
	private final String EX_SQL_BAT_FILE = "exportEHunter.bat";
	private final String EX_SQL_URI = EX_SQL_DISC + File.separator + EX_SQL_DIR + File.separator + EX_SQL_FILE;
	private final String EX_SQL_BAT_URI = EX_SQL_DISC + File.separator + EX_SQL_DIR + File.separator + EX_SQL_BAT_FILE;
	private final String EX_SQL_CMD = "mysqldump -h"+DB_URL+" -u"+DB_USER+" -p"+DB_PASSWORD+" " + DB_NAME + " > "+EX_SQL_URI;
	
	@Test
	public void exportSQL(){
		try {
//			ExportSQLUtil.createBat(EX_SQL_BAT_URI, EX_SQL_CMD);
			ExportSQLUtil.exportAllSql(EX_SQL_BAT_URI,EX_SQL_CMD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
