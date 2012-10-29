package com.pccw.ehunter.helper;

import java.io.File;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.pccw.ehunter.utility.DocumentTranslatorUtility;
import com.pccw.ehunter.utility.StringUtils;

@Component("fileConvertor")
public class BatchUploadFileConvertor {
	
	private String xpdfPath ;
	
	private String pdf2swfPath ;

	@Autowired
	@Qualifier("xmlProcessorConfig")
	public void setProperties(Properties xmlProcessorConfig){
		xpdfPath = xmlProcessorConfig.getProperty("convert.toSwf.xpdf");
		pdf2swfPath = xmlProcessorConfig.getProperty("convert.toSwf.pdf2swf");
	}
	
	public void convertPdf2Swf(String srcPath , String destPath){
		String command = getPdf2swfCommand(srcPath, destPath);
		DocumentTranslatorUtility.convertPdf2Swf(command);
	}
	
	private String getPdf2swfCommand(String sourcePath , String destPath){
		File source = new File(sourcePath);
		if(!source.exists()) return StringUtils.EMPTY_STRING;
		
		File dest = new File(destPath);
		if(!dest.exists()) dest.mkdirs();
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(pdf2swfPath);
		buffer.append(" -t " + sourcePath);
		buffer.append(" -o " + destPath);
		buffer.append(" -s flashversion=9 ");
		buffer.append(" -s languagedir=" + xpdfPath);
		
		return buffer.toString();
	}
}
