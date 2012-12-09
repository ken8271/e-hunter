package com.pccw.ehunter.utility;

import java.io.FileInputStream;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MicrosoftOfficeUtils {
	private static final Logger logger = LoggerFactory.getLogger(MicrosoftOfficeUtils.class);
	
	public static String parseWord(String path) throws Exception{
		FileInputStream is = null;
		try {  
			is = new FileInputStream(path);  
			
			return new WordExtractor(is).getText();
		} catch (Exception e) {  
			logger.error(">>>>> exception catched(parseWord) , error message : " + e.getMessage());
			throw e;
		}  finally {
			if(is != null){
				is.close();
				is = null;
			}
		}
	}
}
