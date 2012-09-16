package com.pccw.ehunter.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExportSQLUtil {
	public static void exportAllSql(final String batFile,final String command) throws IOException, InterruptedException{
		System.out.println("batFile : "+batFile);
		System.out.println("command : "+command);
		
		createBat(batFile, command);
		
//		Process process = Runtime.getRuntime().exec("cmd.exe /C " + batFile);
//		// kick off stderr  
//		StreamGobbler errorGobbler = new StreamGobbler(process.getErrorStream(), "ERROR"); 
//		errorGobbler.start();  
    
//		// kick off stdout  
//		StreamGobbler outGobbler = new StreamGobbler(process.getInputStream(), "STDOUT");  
//		outGobbler.start();
		
		Thread thread = new Thread(){
			@Override
			public void run() {
				super.run();
				try {
					Runtime.getRuntime().exec("cmd.exe /C " + batFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		thread.start();
		Thread.sleep(1000);
	}
	
	private static File createFile(String fileName) throws IOException{
		File file = new File(fileName);
		if(!file.exists()){
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		return file;
	}
	
	public static void createBat(String batFile,String command) throws IOException{
		  File file = createFile(batFile);
          FileOutputStream fos = new FileOutputStream(file);
          StringBuffer sb = new StringBuffer();
          sb.append("@echo off\r\n");
          sb.append("echo exporting the SQL , pls wait......\r\n");
          sb.append(command+"\r\n");
          sb.append("echo done ! \r\n");
          sb.append("echo. & pause \r\n");
          fos.write(sb.toString().getBytes("UTF-8"));
          fos.close();
	}
}
