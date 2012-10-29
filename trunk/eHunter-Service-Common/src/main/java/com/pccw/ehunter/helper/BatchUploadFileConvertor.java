package com.pccw.ehunter.helper;

import java.io.File;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.pccw.ehunter.constant.CommonConstant;
import com.pccw.ehunter.utility.DocumentTranslatorUtility;
import com.pccw.ehunter.utility.StringUtils;

@Component("fileConvertor")
public class BatchUploadFileConvertor {
	
	private String xpdfPath ;
	
	private String pdf2swfPath ;
	
	private String uploadDirectory;
	
	private String swfDirectory;

	@Autowired
	@Qualifier("xmlProcessorConfig")
	public void setProperties(Properties xmlProcessorConfig){
		xpdfPath = xmlProcessorConfig.getProperty("convert.toSwf.xpdf");
		pdf2swfPath = xmlProcessorConfig.getProperty("convert.toSwf.pdf2swf");
		uploadDirectory = xmlProcessorConfig.getProperty("resume.path.upload");
		swfDirectory = xmlProcessorConfig.getProperty("resume.path.swf");
	}
	
	public void convertPdf2Swf(String fileName){
		String command = getPdf2swfCommand(uploadDirectory, swfDirectory, fileName.substring(0, fileName.indexOf(".")));
		DocumentTranslatorUtility.convertPdf2Swf(command);
	}
	
	private String getPdf2swfCommand(String sourcePath , String destPath , String fileName){
		File source = new File(sourcePath);
		if(!source.exists()) return StringUtils.EMPTY_STRING;
		
		File dest = new File(destPath);
		if(!dest.exists()) dest.mkdirs();
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(pdf2swfPath);
		buffer.append(" -t " + sourcePath + File.separator + fileName + CommonConstant.SUFFIX_PDF);
		buffer.append(" -o " + destPath + File.separator + fileName + CommonConstant.SUFFIX_SWF);
		buffer.append(" -s flashversion=9 ");
		buffer.append(" -s languagedir=" + xpdfPath);
		
		return buffer.toString();
	}
}
