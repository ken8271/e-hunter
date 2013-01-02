package com.pccw.ehunter.utility;

import java.io.File;
import java.net.ConnectException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.pccw.ehunter.thread.ErrorStreamProcessThread;
import com.pccw.ehunter.thread.InputStreamProcessThread;

public class DocumentTranslatorUtility {
	
	private static final Logger logger = LoggerFactory.getLogger(DocumentTranslatorUtility.class);
	
	public static int convertPdf2Swf(String command) {
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
		}
		
		return 0;
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

	public static File convertOffice2Pdf(int openOfficePort, String inputPath,String outputPath) {
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
			throw new RuntimeException("Convert office to pdf is wrong!", e);
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
}
