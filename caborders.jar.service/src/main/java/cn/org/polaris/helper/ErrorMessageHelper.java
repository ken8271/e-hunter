package cn.org.polaris.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import cn.org.polaris.error.BuisnessError;
import cn.org.polaris.error.Errors;

@Component("errorMessageHelper")
public class ErrorMessageHelper {

	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	private String getMessage(String code , Object[] args){
		Locale locale =  LocaleContextHolder.getLocale();
		return messageSource.getMessage(code, args, locale);
	}
	
	public List<String> convert2Strings(Errors errors){
		List<String> messages = new ArrayList<String>();
		
		for(BuisnessError e : errors.getErrors()){
			messages.add(getMessage(e.getCode() , e.getArgs()));
		}
		
		return messages;
	}
	
	public List<String> convert2Strings(String msg){
		List<String> messages = new ArrayList<String>();
		
		messages.add(msg);
		
		return messages;
	}
	
	public List<String> convert2Strings(Exception e){
		List<String> messages = new ArrayList<String>();
		
		messages.add(e.getMessage());
		
		return messages;
	}
}
