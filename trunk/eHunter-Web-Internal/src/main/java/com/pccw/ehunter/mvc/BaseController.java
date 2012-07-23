package com.pccw.ehunter.mvc;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;

public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected ResourceBundleMessageSource messageSource;
	
	public String getMessage(String code , Object[] args , Locale locale){
		return messageSource.getMessage(code, args, locale);
	}
	
	public String getMessage(String code , Locale locale){
		return messageSource.getMessage(code, null, locale);
	}
}
