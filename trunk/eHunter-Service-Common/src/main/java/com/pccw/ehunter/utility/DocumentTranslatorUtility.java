package com.pccw.ehunter.utility;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.pccw.ehunter.thread.ErrorStreamProcessThread;
import com.pccw.ehunter.thread.InputStreamProcessThread;

public class DocumentTranslatorUtility {
	
	private static final Logger logger = LoggerFactory.getLogger(DocumentTranslatorUtility.class);
	
	public static int convertPdf2Swf(String command) throws Exception{
		Runtime r = null;
		Process process = null;
		
		try {			
			r = Runtime.getRuntime();
			process = r.exec(command);
			
			new InputStreamProcessThread(process).start();
			new ErrorStreamProcessThread(process).start();

			process.waitFor();
			
			return process.exitValue();
		} catch (Exception e) {
			logger.error(">>>>> Exception Catched(convertPdf2swf) : " + e.getMessage());
			throw e;
		}
	}
	
	public static int convertHtml2Pdf(String command) throws Exception{
		Runtime r = null;
		Process process = null;
		try {			
			r = Runtime.getRuntime();
			process = r.exec(command);
			
			new InputStreamProcessThread(process).start();
			new ErrorStreamProcessThread(process).start();

			process.waitFor();
			
			return process.exitValue();
		} catch (Exception e) {
			logger.error(">>>>> Exception Catched(convertHtml2Pdf) : " + e.getMessage());
			throw e;
		}
	}

	public static File convertOffice2Pdf(int openOfficePort, String inputPath,String outputPath)  throws Exception{
		OpenOfficeConnection connection = null;
		File inputFile = null;
		File outputFile = null;
		DocumentConverter converter = null;
		try {
			connection = new SocketOpenOfficeConnection(openOfficePort);
			inputFile = new File(inputPath);
			outputFile = new File(outputPath);

			connection.connect();

			converter = new OpenOfficeDocumentConverter(connection);

			converter.convert(inputFile, outputFile);

			return outputFile;
		} catch (ConnectException e) {
			e.printStackTrace();
			throw e;
		} finally {
			colseConnect(connection);
		}
	}

	private static void colseConnect(OpenOfficeConnection connection) {
		if (connection != null && connection.isConnected()) {
			connection.disconnect();
			connection = null;
		}
	}
	
	public static void convertTxt2Pdf(String txtPath , String pdfPath) throws Exception{
		Document document = null;
		InputStream is = null;
		BufferedReader reader = null;
		try {
			is = new FileInputStream(txtPath);
			reader = new BufferedReader(new InputStreamReader(is , FileUtils.getEncoding(txtPath)));
			
			document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
			
			BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", false);
			
			Font fontChinese = new Font(bfChinese, 12, Font.NORMAL, Color.BLACK); 
			
			document.open();
			
			String line=reader.readLine();
			while(line!=null){
				Paragraph pg = new Paragraph(line,fontChinese);
				document.add(pg);
				line=reader.readLine();
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			document.close();
			reader.close();
			is.close();			
		}	
	}
}
