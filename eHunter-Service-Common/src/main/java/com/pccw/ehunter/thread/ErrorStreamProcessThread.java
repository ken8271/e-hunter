package com.pccw.ehunter.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pccw.ehunter.utility.StringUtils;

public class ErrorStreamProcessThread extends Thread{
	
	private Logger logger = LoggerFactory.getLogger(ErrorStreamProcessThread.class);
	
	private Process process;

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}
	
	@Override
	public void run() {
		try {
			if (process == null) {
				logger.info("process is null, can't deal with it!");
				return;
			}
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			
			while (true) {
				String text = null;
				if (process == null) {
					logger.debug("process is complete!");
					break;
				}
				
				while((text = reader.readLine()) != null) logger.error(text);
			}
			
			StringBuilder buffer = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) buffer.append(line); 
			
			if(!StringUtils.isEmpty(line)){
				logger.error(line);
			}
			
			while (reader.readLine() != null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(">>>> Exception Catched(ErrorStreamProcessThread) : " + e.getMessage());
		}
	}
}
