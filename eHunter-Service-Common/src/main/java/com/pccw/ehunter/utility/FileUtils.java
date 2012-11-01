package com.pccw.ehunter.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

public class FileUtils {
	
	protected static final int BUFFER_SIZE = 4096;
	
	public static final String PDF_FILE_EXT = ".pdf";
	
	public static final String WORD_FILE_EXT = ".doc";
	
	public static final String SWF_FILE_EXT = ".swf";
	
	public static boolean isPdfFile(String fileName){
		return fileName.endsWith(PDF_FILE_EXT);
	}
	
	public static boolean isWordFile(String fileName) {
		return fileName.endsWith(WORD_FILE_EXT);
	}

	public static void appendFile(File input, File output) throws IOException {
		copyFile(input, output, true);
	}

	public static void appendFile(File input, String content) throws IOException {
		FileWriter oFW = null;
		BufferedWriter oBW = null;
		PrintWriter out = null;
		File parentD = input.getParentFile();
		if (!parentD.exists()) {
			boolean mkD = parentD.mkdirs();
			if (!mkD) {
				throw new IOException("Unable to make parent diretory,"
						+ parentD);
			}
		}
		try {
			oFW = new FileWriter(input, true);
			oBW = new BufferedWriter(oFW);
			out = new PrintWriter(oBW, true);
			out.println(content);
		} catch (IOException ioe) {
			throw ioe;
		} finally {
			if (out != null)
				out.close();
			if (oBW != null)
				oBW.close();
			if (oFW != null)
				oFW.close();
		}
	}

	public static void concatFile(File input1, File input2, File output)
			throws IOException {
		copyFile(input1, output);
		appendFile(input2, output);
	}

	public static void copyDir(File srcDir, File destDir) throws IOException {
		if (srcDir.isDirectory()) {
			if (destDir.exists() != true) {
				boolean mkD = destDir.mkdirs();
				if (!mkD) {
					throw new IOException("Unable to make destDir," + destDir);
				}
			}
			String list[] = srcDir.list();
			for (int i = 0; i < list.length; i++) {
				String src = srcDir.getAbsolutePath()
						+ System.getProperty("file.separator") + list[i];
				String dest = destDir.getAbsolutePath()
						+ System.getProperty("file.separator") + list[i];
				copyDir(new File(src), new File(dest));
			}
		}
	}

	public static void copyFile(File input, File output) throws IOException {
		copyFile(input, output, false);
	}

	private static void copyFile(File input, File output, boolean append)
			throws IOException {
		FileInputStream in = new FileInputStream(input);
		FileOutputStream out = new FileOutputStream(output.getPath(), append);
		byte[] buffer = new byte[BUFFER_SIZE];
		int numRead = in.read(buffer);
		while (numRead > 0) {
			out.write(buffer, 0, numRead);
			numRead = in.read(buffer);
		}
		out.close();
		in.close();
	}
	
	public static void write2Disk(String directory , String fileName , InputStream in , boolean append) throws IOException{
		FileOutputStream out = null;
		
		try {		
			File dir = new File(directory);
			
			if(!dir.exists()){
				dir.mkdirs();
			}
			
			out = new FileOutputStream(directory + File.separator + fileName , append);
			
			byte[] buffer = new byte[BUFFER_SIZE];
			
			int numRead = in.read(buffer);
			while (numRead > 0) {
				out.write(buffer, 0, numRead);
				numRead = in.read(buffer);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if(out != null) out.close();
			if(in != null) in.close();
		}
	}

	public static void write2Disk(File outFile, InputStream in , boolean append) throws IOException {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(outFile , append);
			
			byte[] buffer = new byte[BUFFER_SIZE];
			
			int numRead = in.read(buffer);
			while (numRead > 0) {
				out.write(buffer, 0, numRead);
				numRead = in.read(buffer);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if(out != null) out.close();
			if(in != null) in.close();
		}
	}
	
	public static void readfile(File src , OutputStream out) throws IOException{
		FileInputStream in = null;
		try {			
			byte[] buffer = new byte[BUFFER_SIZE];
			in = new FileInputStream(src);
			
			int length = 0;
			while((length = in.read(buffer)) > 0){
				out.write(buffer, 0, length);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if(out != null){
				out.close();
			}
			if(in != null){
				in.close();
			}
		}
	}
	
	public static String getExtension(String nameOrPath){
		if(nameOrPath.indexOf(StringUtils.DOT) != -1){
			return nameOrPath.substring(nameOrPath.lastIndexOf(StringUtils.DOT));
		}
		
		return StringUtils.EMPTY_STRING;
	}
	
	public static String replaceFileNameWithUUID(String extension){
		return UUID.randomUUID().toString() + extension;
	}
	
}
