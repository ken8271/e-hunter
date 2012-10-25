package com.pccw.ehunter.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class DocumentTranslatorUtility {

	public static int convertPdf2Swf(String sourcePath, String destPath,
			String fileName) {
		Runtime r = null;
		Process process = null;
		try {
			File dest = new File(destPath);
			if (!dest.exists())
				dest.mkdirs();
			System.out.println("--------1--------");
			File source = new File(sourcePath);
			if (!source.exists())
				return 0;
			System.out.println("--------2--------");
			String command = "C:/Program Files/SWFTools/pdf2swf.exe  -t \""
					+ sourcePath
					+ "\" -o  \""
					+ destPath
					+ "/test.swf\" -s flashversion=9 -s languagedir=D:/development-tools/xpdf/xpdf-chinese-simplified ";
			System.out.println(command);
			r = Runtime.getRuntime();
			process = r.exec(command);
			System.out.println("--------3--------");
			InputStream is2 = process.getErrorStream();
			BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
			StringBuilder buf = new StringBuilder(); // 保存输出结果流
			String line = null;
			while ((line = br2.readLine()) != null)
				buf.append(line); // 循环等待ffmpeg进程结束
			System.out.println("输出结果为：" + buf);
			System.out.println("--------4--------");
			while (br2.readLine() != null)
				;

			try {
				process.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("--------5--------");
			return process.exitValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"Execute the command of convert pdf to swf is wrong!", e);
		}
	}

	public static File convertOffice2Pdf(int openOfficePort, String inputPath,
			String outputPath) {
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

	public static void main(String[] args) {
		String sourcePath = "D:/RHCE教程.pdf";
		String destPath = "D:/test";
		String fileName = "Javssa.swf";
		try {
			convertPdf2Swf(sourcePath, destPath, fileName);

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("error");
		}
		System.out.println("success");

	}
}
