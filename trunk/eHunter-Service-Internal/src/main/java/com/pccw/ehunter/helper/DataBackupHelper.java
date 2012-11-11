package com.pccw.ehunter.helper;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pccw.ehunter.thread.BackupProcessThread;
import com.pccw.ehunter.thread.ErrorStreamProcessThread;
import com.pccw.ehunter.utility.StringUtils;

@Component("dataBackupHelper")
public class DataBackupHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(DataBackupHelper.class);
	
	private String dbPath;
	
	private String userName;
	
	private String pwd;
	
	private String backupPath;
	
	private String dbName;
	
	@Autowired
	public void setProperties(Properties xmlProcessorConfig){
		dbPath = xmlProcessorConfig.getProperty("database.mysql.path") ;
		userName = xmlProcessorConfig.getProperty("jdbc.username") ;
		pwd = xmlProcessorConfig.getProperty("jdbc.password");
		backupPath = xmlProcessorConfig.getProperty("data.backup.path");	
		String dbUrl =  xmlProcessorConfig.getProperty("jdbc.url");
		if(!StringUtils.isEmpty(dbUrl)){
			dbName = dbUrl.substring(dbUrl.lastIndexOf("/")+1 , dbUrl.lastIndexOf("?"));
		}
	}

	public int handleDataBackup(String fileName) throws Exception{
		
		prepareTargetFile(fileName);
		
		String command = getBackupCommand();
		
		return backup(command, backupPath + File.separator + fileName) ;
	}
	
	private void prepareTargetFile(String fileName) throws IOException {		
		File backupDir = new File(backupPath);
		if(!backupDir.exists()){
			backupDir.mkdirs();
		}
		
		File backupfile = new File(backupPath + File.separator + fileName);
		if(!backupfile.exists()){
			backupfile.createNewFile();
		}
	}

	private int backup(String command , String targetPath){
		Runtime r = null;
		Process process = null;
		try {			
			r = Runtime.getRuntime();
			process = r.exec(command);
			
			new BackupProcessThread(process , targetPath).start();
			new ErrorStreamProcessThread(process).start();

			process.waitFor();
			
			return process.exitValue();
		} catch (Exception e) {
			logger.error(">>>>> Exception Catched(backup) : " + e.getMessage());
		}
		
		return -1;
	}
	
	private String getBackupCommand(){
		StringBuffer command = new StringBuffer();
		
		command.append(dbPath);
		command.append(File.separator + "mysqldump.exe ");
		command.append(" -u" + userName);
		command.append(" -p" + pwd);
		command.append(" " + dbName);
		
		return command.toString();
	}
}
