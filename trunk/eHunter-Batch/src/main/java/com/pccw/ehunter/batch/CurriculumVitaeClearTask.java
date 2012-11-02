package com.pccw.ehunter.batch;

import java.io.File;
import java.util.Properties;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

public class CurriculumVitaeClearTask implements Job{
	
	private String targetPath;

	@Override
	public void execute(JobExecutionContext jobContext) throws JobExecutionException {
		JobDataMap jobDataMap = jobContext.getJobDetail().getJobDataMap();
		
		ApplicationContext ctx = (ApplicationContext)jobDataMap.get("applicationContext");
		Properties xmlProcessConfig = (Properties)ctx.getBean("xmlProcessorConfig");
		targetPath = xmlProcessConfig.getProperty("ehunter.webapp.path") + xmlProcessConfig.getProperty("cv.temp.relative.path");
		
		File directory = new File(targetPath);
		
		if(directory.exists()){
			File[] files = directory.listFiles();
			
			if(files != null && files.length != 0){
				for(File tmp : files){
					tmp.delete();
				}
			}
		}
	}

}
