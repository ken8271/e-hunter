package com.pccw.ehunter.batch;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.constant.DateFormatConstant;
import com.pccw.ehunter.dto.DataBackupHistoryDTO;
import com.pccw.ehunter.helper.DataBackupHelper;
import com.pccw.ehunter.service.DataBackupHistoryService;
import com.pccw.ehunter.utility.DateUtils;
import com.pccw.ehunter.utility.FileUtils;

public class DataBackupTask implements Job{
	
	private static final Logger logger = LoggerFactory.getLogger(DataBackupTask.class);

	@Override
	public void execute(JobExecutionContext jobContext) throws JobExecutionException {
		JobDataMap jobDataMap = jobContext.getJobDetail().getJobDataMap();
		
		logger.info(">>>>> data backup begin ... ... ");
		
		ApplicationContext ctx = null;
		DataBackupHistoryService dataBackupHistoryService = null;
		DataBackupHelper helper = null;
		try {
			ctx = (ApplicationContext)jobDataMap.get("applicationContext");
			dataBackupHistoryService = (DataBackupHistoryService)ctx.getBean("dataBackupHistoryService");
			helper = (DataBackupHelper)ctx.getBean("dataBackupHelper");
			
			String backupFileName = FileUtils.replaceFileNameWithUUID(DateUtils.formatDateTime(DateFormatConstant.DATE_YYYYMMDD, new Date()), FileUtils.SQL_FILE_EXT);
			if(helper.handleDataBackup(backupFileName) != -1){
				logger.info(">>>>> data backup successfully ... ... ");
				DataBackupHistoryDTO dto = new DataBackupHistoryDTO();
				dto.setFileName(backupFileName);
				dto.setBackupDttm(new Date());
				dto.setBackupChannel(CommonConstant.BACKUP_CHANNEL_OF_BATCH_JOB);
				dto.setBackupBy(CommonConstant.ANOYMOUS_INTERNAL_USER);
				dataBackupHistoryService.saveBackupHistory(dto);
			}
			
		} catch (Exception e) {
			logger.error(">>>>> Exception catched when backup system data , error message : " + e.getMessage());
		}
		
		logger.info(">>>>> data backup end ... ... ");
	}
}
