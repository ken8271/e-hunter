package com.pccw.ehunter.helper;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface ExcelHelper {
	public void createExcel(OutputStream out , List<String> titles);
	public List<String[]> parseExcel(InputStream in);
}
