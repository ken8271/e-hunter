package com.pccw.ehunter.batch;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class CurriculumVitaeClearTask implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("++++++++++++++++++++++++++++++++++");
	}

}
