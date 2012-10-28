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
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class FileUtils {

	/**
	 * 
	 * File util class
	 * 
	 * @author administrator
	 * @since Jul 6, 2009
	 * @version 1.0
	 */
	protected static final int BUFFER_SIZE = 4096;

	/**
	 * Append input to the output file
	 * 
	 * @param input
	 *            File input
	 * @param output
	 *            Output file
	 * @throws IOException
	 */
	public static void appendFile(File input, File output) throws IOException {
		copyFile(input, output, true);
	}

	/**
	 * Append content to the inFile
	 * 
	 * @param inFile
	 *            File to be append on
	 * @param content
	 *            String content to append
	 * @throws IOException
	 */
	public static void appendFile(File inFile, String content)
			throws IOException {
		FileWriter oFW = null;
		BufferedWriter oBW = null;
		PrintWriter out = null;
		File parentD = inFile.getParentFile();
		if (!parentD.exists()) {
			boolean mkD = parentD.mkdirs();
			if (!mkD) {
				throw new IOException("Unable to make parent diretory,"
						+ parentD);
			}
		}
		try {
			oFW = new FileWriter(inFile, true);
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

	/**
	 * Concat input1 & input2 and the generate the output file
	 * 
	 * @param input1
	 *            First file
	 * @param input2
	 *            Second file
	 * @param output
	 *            Write the output to this file
	 * @throws IOException
	 */
	public static void concatFile(File input1, File input2, File output)
			throws IOException {
		copyFile(input1, output);
		appendFile(input2, output);
	}

	/**
	 * Copy only the content of a directory into another directory.
	 * 
	 * @param srcPath
	 *            the source directory
	 * @param destinationPath
	 *            the destination directory
	 */
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

	/**
	 * Copy input file to the output
	 * 
	 * @param input
	 *            Input
	 * @param output
	 *            Output
	 * @throws IOException
	 */
	public static void copyFile(File input, File output) throws IOException {
		copyFile(input, output, false);
	}

	/**
	 * Write content to the inFile
	 * 
	 * @param inFile
	 *            File to write upon
	 * @param content
	 *            String content
	 * @throws IOException
	 */
	public static void writeFile(File inFile, String content)
			throws IOException {
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(new BufferedOutputStream(
					new FileOutputStream(inFile)));
			dos.writeBytes(content);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw ioe;
		} finally {
			if (dos != null)
				dos.close();
		}
	}

	/**
	 * Write map entries to the file
	 * 
	 * @param <T>
	 *            Key
	 * @param <E>
	 *            Value
	 * @param map
	 *            Write the map entries to the file
	 * @param inFile
	 *            File to write upon
	 * @throws IOException
	 */
	public static <T, E> void writeFile(Map<T, E> map, File inFile)
			throws IOException {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		PrintWriter out = null;
		try {
			fos = new FileOutputStream(inFile);
			bos = new BufferedOutputStream(fos);
			out = new PrintWriter(bos);
			Iterator<Entry<T, E>> entryI = map.entrySet().iterator();
			Entry<T, E> entry;
			while (entryI.hasNext()) {
				entry = entryI.next();
				out.println(entry.getKey() + "=" + entry.getValue());
			}
			out.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw ioe;
		} finally {
			try {
				if (out != null)
					out.close();
				if (bos != null)
					bos.close();
				if (fos != null)
					fos.close();
			} catch (IOException ioe) {
			}
		}
	}

	/**
	 * Copy input file to the output
	 * 
	 * @param input
	 *            Input
	 * @param output
	 *            Output
	 * @param append
	 *            true if append
	 * @throws IOException
	 */
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
	
	public static void write2Disk(String path , String fileName , InputStream in) throws IOException{
		File dir = new File(path);
		
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		FileOutputStream out = new FileOutputStream(path + File.separator + fileName , false);
		
		byte[] buffer = new byte[BUFFER_SIZE];
		
		int numRead = in.read(buffer);
		while (numRead > 0) {
			out.write(buffer, 0, numRead);
			numRead = in.read(buffer);
		}
		out.close();
		in.close();
	}

}
