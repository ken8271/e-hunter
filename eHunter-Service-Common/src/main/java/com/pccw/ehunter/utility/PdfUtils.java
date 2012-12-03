package com.pccw.ehunter.utility;

import java.io.FileInputStream;

import org.pdfbox.pdfparser.PDFParser;
import org.pdfbox.util.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PdfUtils {
	private static final Logger logger = LoggerFactory.getLogger(PdfUtils.class);
	
	public static String parsePdf(String path) throws Exception{
		FileInputStream is = null;
		PDFParser pdfParser = null;
		PDFTextStripper stripper = null;
		try {
			is = new FileInputStream(path);
			pdfParser = new PDFParser(is);
			
			pdfParser.parse();
			
			stripper = new PDFTextStripper();
			
			return stripper.getText(pdfParser.getPDDocument()).trim();
		} catch (Exception e) {
			logger.error(">>>>>Exception Catched(parsePdf),error message : " + e.getMessage());
		} finally  {
			if(is != null){
				is.close();
				is = null;
			}
		}
		
		return StringUtils.EMPTY_STRING;
	}
	
}
