package com.pccw.ehunter.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;

import org.hdiv.listener.InitListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.pccw.ehunter.constant.ModuleIndicator;
import com.pccw.ehunter.service.TransactionLogService;
import com.pccw.ehunter.utility.StringUtils;

public class HDIVInitListener extends InitListener {

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		String curr = (String)event.getSession().getAttribute(event.getSession().getId());
		if(!StringUtils.isEmpty(curr)){
			ServletContext context =  event.getSession().getServletContext();
			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
			TransactionLogService txService = (TransactionLogService)ctx.getBean("transactionLogService");
			ResourceBundleMessageSource messageSource = (ResourceBundleMessageSource)ctx.getBean("messageSource");
			txService.logTransaction(ModuleIndicator.SYSTEM, messageSource.getMessage("tx.log.logout", null, LocaleContextHolder.getLocale()) , curr);
		}
		super.sessionDestroyed(event);
	}

}
