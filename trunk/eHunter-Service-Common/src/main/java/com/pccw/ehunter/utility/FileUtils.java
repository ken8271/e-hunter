package com.pccw.ehunter.utility;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class FileUtils {
	
	protected static final int BUFFER_SIZE = 4096;
	
	public static final String PDF_FILE_EXT = ".pdf";
	
	public static final String WORD_FILE_EXT = ".doc";
	
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
	
	public static void write2Disk(String directory , String fileName , InputStream in)throws IOException{
		write2Disk(directory, fileName, in, false);
	}
	
	public static void write2Disk(String directory , String fileName , InputStream in , boolean append) throws IOException{
		File dir = new File(directory);
		
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		FileOutputStream out = new FileOutputStream(directory + File.separator + fileName , append);
		
		byte[] buffer = new byte[BUFFER_SIZE];
		
		int numRead = in.read(buffer);
		while (numRead > 0) {
			out.write(buffer, 0, numRead);
			numRead = in.read(buffer);
		}
		out.close();
		in.close();
	}

	public static void write2Disk(File inFile, String content)
			throws IOException {
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(inFile)));
			dos.writeBytes(content);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw ioe;
		} finally {
			if (dos != null)
				dos.close();
		}
	}
}
