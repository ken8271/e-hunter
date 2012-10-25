package com.pccw.ehunter.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputStreamProcessThread extends Thread{
	
	private static final Logger logger = LoggerFactory.getLogger(InputStreamProcessThread.class);
	
	private Process process ;

	public InputStreamProcessThread(Process process) {
		super();
		this.process = process;
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}
	
	@Override
	public void run() {
		if (process == null) {
			logger.info("process is null, can't deal with it!");
			return;
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		try {
			while (reader.readLine() != null);
		} catch (IOException e) {
			logger.error(">>>> Exception Catched(InputStreamProcessThread) : " + e.getMessage());
		}
	}
}
