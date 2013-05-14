package cn.org.polaris.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import cn.org.polaris.dto.MessageDTO;
import cn.org.polaris.error.ErrorBean;
import cn.org.polaris.error.Errors;
import cn.org.polaris.utility.StringUtils;

@Component("messageHelper")
public class MessageHelper {
	
	@Autowired
	private MessageSource messageSource;
	
	private String getMessage(String code , String[] args){
		Locale locale =  LocaleContextHolder.getLocale();
		return messageSource.getMessage(code, args, locale);
	}
	
	private MessageDTO convert2Message(ErrorBean error){
		MessageDTO msgDto = new MessageDTO();
		
		String msgStr = getMessage(error.getCode() , error.getArgs());
		
		if(StringUtils.isEmpty(msgStr)){
			msgStr = error.getDefaultMsg();
		}
		
		msgDto.setCode(error.getCode());
		msgDto.setMessage(msgStr);
		
		return msgDto;
	}
	
	public List<MessageDTO> convert2Messages(Errors errors){
		List<MessageDTO> messages = new ArrayList<MessageDTO>();
		
		for(ErrorBean e : errors.getErrors()){
			messages.add(convert2Message(e));
		}
		
		return messages;
	}
	
	public List<MessageDTO> convert2Messages(Exception e){
		List<MessageDTO> messages = new ArrayList<MessageDTO>();
		
		MessageDTO msg = new MessageDTO();
		
		msg.setMessage(e.getMessage());
		
		messages.add(msg);
		
		return messages;
	}
	
	public List<MessageDTO> convert2Messages(String msgStr){
		List<MessageDTO> messages = new ArrayList<MessageDTO>();
		
		MessageDTO msg = new MessageDTO();
		
		msg.setMessage(msgStr);
		
		messages.add(msg);
		
		return messages;
	}
	
	public List<MessageDTO> convert2Messages(String code , String[] args){
		List<MessageDTO> messages = new ArrayList<MessageDTO>();
		
		MessageDTO msg = new MessageDTO();
		
		String msgStr = getMessage(code , args);
		
		msg.setCode(code);
		msg.setMessage(msgStr);
		
		messages.add(msg);
		
		return messages;
	}
}
