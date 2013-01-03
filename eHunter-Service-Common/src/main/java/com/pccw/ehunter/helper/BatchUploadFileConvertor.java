package com.pccw.ehunter.helper;

import java.io.File;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.pccw.ehunter.utility.DocumentTranslatorUtility;
import com.pccw.ehunter.utility.FileUtils;
import com.pccw.ehunter.utility.StringUtils;

@Component("fileConvertor")
public class BatchUploadFileConvertor {
	
	private static final Logger logger = LoggerFactory.getLogger(BatchUploadFileConvertor.class);
	
	private String xpdfPath ;
	
	private String pdf2swfPath ;
	
	private String html2PdfPath;
	
	private String tempDirectory;
	
	private String openOfficePort;

	@Autowired
	@Qualifier("xmlProcessorConfig")
	public void setProperties(Properties xmlProcessorConfig){
		xpdfPath = xmlProcessorConfig.getProperty("convert.toSwf.xpdf");
		pdf2swfPath = xmlProcessorConfig.getProperty("convert.toSwf.pdf2swf");
		tempDirectory = xmlProcessorConfig.getProperty("resume.path.temp");
		openOfficePort = xmlProcessorConfig.getProperty("openoffice.port");
		
		html2PdfPath = xmlProcessorConfig.getProperty("convert.toPdf.path");
	}
	
	public void convertPdf2Swf(String srcPath , String destPath) throws Exception{
		String command = getPdf2swfCommand(srcPath, destPath);
		DocumentTranslatorUtility.convertPdf2Swf(command);
	}
	
	private String getPdf2swfCommand(String sourcePath , String destPath){
		File source = new File(sourcePath);
		if(!source.exists()) return StringUtils.EMPTY_STRING;
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(pdf2swfPath);
		buffer.append(" -t " + sourcePath);
		buffer.append(" -o " + destPath);
		buffer.append(" -s flashversion=9 ");
		buffer.append(" -s languagedir=" + xpdfPath);
		
		logger.debug(">>>>>PDF2SWF Command : " + buffer.toString());
		return buffer.toString();
	}
	
	public void convertOffice2Swf(String srcPath , String destPath) throws Exception{
		File temp = new File(tempDirectory);
		if(!temp.exists()){
			temp.mkdirs();
		}
		
		String tempPath = tempDirectory + File.separator + FileUtils.replaceFileNameWithUUID(FileUtils.PDF_FILE_EXT);
		
		DocumentTranslatorUtility.convertOffice2Pdf(Integer.parseInt(openOfficePort), srcPath , tempPath);
		
		File file = new File(tempPath);
		if(file.exists()){
			convertPdf2Swf(tempPath, destPath);
		}
		
		if(file.exists()){
			file.delete();			
		}
	}
	
	public void convertHtml2Pdf(String inputPath , String outputPath) throws Exception{
		String command = getHtml2PdfCommand(inputPath, outputPath);
		DocumentTranslatorUtility.convertHtml2Pdf(command);
	}
	
	private String getHtml2PdfCommand(String sourcePath , String destPath){
		File source = new File(sourcePath);
		if(!source.exists()) return StringUtils.EMPTY_STRING;
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(html2PdfPath);
		buffer.append(" \"" + sourcePath + "\" ");
		buffer.append(" \"" + destPath + "\" ");
		
		logger.debug(">>>>>HTML2PDF Command : " + buffer.toString());
		return buffer.toString();
	}
	
	public void convertTxt2Swf(String txtPath , String swfPath) throws Exception{
		File temp = new File(tempDirectory);
		if(!temp.exists()){
			temp.mkdirs();
		}
		
		String tempPath = tempDirectory + File.separator + FileUtils.replaceFileNameWithUUID(FileUtils.PDF_FILE_EXT);
		
		DocumentTranslatorUtility.convertTxt2Pdf(txtPath , tempPath);
		
		File file = new File(tempPath);
		if(file.exists()){
			convertPdf2Swf(tempPath, swfPath);
		}
		
		if(file.exists()){
			file.delete();			
		}
	}
}
