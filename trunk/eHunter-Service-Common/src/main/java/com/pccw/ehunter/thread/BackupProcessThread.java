package com.pccw.ehunter.thread;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackupProcessThread extends InputStreamProcessThread{
private static final Logger logger = LoggerFactory.getLogger(BackupProcessThread.class);
	
	private String targetPath;

	public BackupProcessThread(Process process , String targetPath) {
		super(process);
		this.targetPath = targetPath;
	}
	
	@Override
	public void run() {
		if (getProcess() == null) {
			logger.info("process is null, can't deal with it!");
			return;
		}
		
		BufferedReader reader = null;
		OutputStreamWriter writer = null;
		try {
			
			reader = new BufferedReader(new InputStreamReader(getProcess().getInputStream(), "UTF-8"));

			StringBuffer buffer = new StringBuffer();

			String inStr = null;
            while ((inStr = reader.readLine()) != null) {     
                buffer.append(inStr + "\r\n");     
            }     
                   
            writer = new OutputStreamWriter(new FileOutputStream(targetPath), "utf8");     
         
            writer.write(buffer.toString());     
            writer.flush();     
		} catch (IOException e) {
			logger.error(">>>> Exception Catched(BackupProcessThread) : " + e.getMessage());
		} finally {
			closeIOStream(reader, writer);
		}
	}
	
	private void closeIOStream(BufferedReader reader , OutputStreamWriter writer){
		try {
			if(reader != null){
				reader.close();
			}
			
			if(writer != null){
				writer.close();
			}
			
			reader = null;
			writer = null;
		} catch (Exception e) {
			logger.error(">>>>> Exception Catched (closeIOSteam) : " + e.getMessage());
		}
	}

}
