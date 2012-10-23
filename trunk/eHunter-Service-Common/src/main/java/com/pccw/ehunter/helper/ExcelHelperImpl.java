package com.pccw.ehunter.helper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pccw.ehunter.utility.StringUtils;

@Component("excel2003Helper")
public class ExcelHelperImpl implements ExcelHelper{
	
	private Logger logger = LoggerFactory.getLogger(ExcelHelperImpl.class);

	@Override
	public void createExcel(OutputStream out, List<String> titles) {
		HSSFWorkbook book = null;
		HSSFSheet sheet = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		
		try {
			book = new HSSFWorkbook();
			
			sheet = book.createSheet();
			
			row = sheet.createRow((short)0);
			
			for(int iCol = 0 ; iCol<titles.size() ; iCol++){
				cell = row.createCell(iCol);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(titles.get(iCol));
			}
			
			book.write(out);
		} catch (IOException e) {
			logger.error(">>>>> exception catched (createExcel) : " + e.getMessage());
		}
	}

	@Override
	public List<String[]> parseExcel(InputStream in) {
		HSSFWorkbook book = null;
		HSSFSheet sheet = null;
		HSSFRow row = null;
		List<String[]> result = null;
		String[] rowData = null;
		try {
			result = new ArrayList<String[]>();
			
			book = new HSSFWorkbook(in);
			
			sheet = book.getSheetAt(0);
			
			row = sheet.getRow(0);
			
			int cols = row.getPhysicalNumberOfCells();
			int rows = sheet.getPhysicalNumberOfRows();
			
			for(int i = 1 ; i<rows ; i++){
				rowData = new String[cols];
				row = sheet.getRow(i);
				for(int j=0 ; j<cols ; j++){
					rowData[j] = getCellValue(row.getCell(j));
				}
				result.add(rowData);
			}
			
		} catch (IOException e) {
			logger.error(">>>>> exception catched (parseExcel) : " + e.getMessage());
		}
		
		return result;
	}
	
	private String getCellValue(HSSFCell cell){
		switch (cell.getCellType()) {
		   case HSSFCell.CELL_TYPE_STRING: return cell.getStringCellValue();
		   case HSSFCell.CELL_TYPE_NUMERIC: return Double.toString(cell.getNumericCellValue());
		   case HSSFCell.CELL_TYPE_BOOLEAN: return Boolean.toString(cell.getBooleanCellValue());
		   default:return StringUtils.EMPTY_STRING;
		}
	}

}
