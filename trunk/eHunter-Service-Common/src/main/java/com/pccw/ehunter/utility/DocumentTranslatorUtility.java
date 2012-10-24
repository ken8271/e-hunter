package com.pccw.ehunter.utility;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class DocumentTranslatorUtility {
	
	public static void convertPdf2Swf(String command){
		Runtime r = null;
		Process process = null;
		try {
			r = Runtime.getRuntime();
			process = r.exec(command);
			process.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Execute the command of convert pdf to swf is wrong!", e);
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new RuntimeException("Execute the command of convert pdf to swf is wrong!", e);
		}
	}
	
	public static File convertOffice2Pdf(int openOfficePort, String inputPath, String outputPath) {
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
	
	private static void colseConnect(OpenOfficeConnection connection){
		if (connection != null && connection.isConnected()) {
			connection.disconnect();
			connection = null;
		}
	}
}
