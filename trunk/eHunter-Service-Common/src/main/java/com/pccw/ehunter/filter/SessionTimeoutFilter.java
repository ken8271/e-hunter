package com.pccw.ehunter.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pccw.ehunter.exception.SessionTimeoutException;
import com.pccw.ehunter.utility.StringUtils;

public class SessionTimeoutFilter extends OncePerRequestFilter {

	private Logger logger = LoggerFactory.getLogger(SessionTimeoutFilter.class);

	private String byPassUrl;

	private String checkUserObject;

	private String sessionAttributeToCheck;

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getByPassUrl() {
		return byPassUrl;
	}

	public void setByPassUrl(String byPassUrl) {
		this.byPassUrl = byPassUrl;
	}

	public String getCheckUserObject() {
		return checkUserObject;
	}

	public void setCheckUserObject(String checkUserObject) {
		this.checkUserObject = checkUserObject;
	}

	public String getSessionAttributeToCheck() {
		return sessionAttributeToCheck;
	}

	public void setSessionAttributeToCheck(String sessionAttributeToCheck) {
		this.sessionAttributeToCheck = sessionAttributeToCheck;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String[] byPassUrls = null;
		boolean isByPassUrl = false;
		HttpSession session = null;
		
		String url = request.getRequestURL().toString();
		
		if(!StringUtils.isEmpty(byPassUrl)){
			byPassUrls = StringUtils.tokenize(byPassUrl, StringUtils.COMMA);
			for(String str : byPassUrls){
				if(url.indexOf(str.trim()) != -1){
					isByPassUrl = true;
					break;
				}
			}
		}
		
		if(!isByPassUrl){
			session = request.getSession(false);
			
			if(null != session){
				if(isSessionValid(request)){
					//check for user object
					if("true".equalsIgnoreCase(checkUserObject)){
						if(session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY) == null){
							logger.debug("session timeout detected...");
							throw new SessionTimeoutException("Session Timeout");
						}
					}
				}else {
					throw new SessionTimeoutException("Session Timeout");
				}
			}else {
				logger.warn("session timeout detected...");
				throw new SessionTimeoutException("Session Timeout");
			}
		}
		
		try {
			filterChain.doFilter(request, response);
		} catch (IllegalStateException e) {
			logger.error(e.getMessage());
			if(e.getMessage().indexOf("getAttribute: Session already invalidated")!=-1){
				throw new SessionTimeoutException("Session Timeout");
			}
		}
	}
	
	private boolean isSessionValid(HttpServletRequest request){
		try {
			if(request.getSession(false) != null){
				Object o = request.getSession(false).getAttribute(this.sessionAttributeToCheck);
				if(!StringUtils.isEmpty(sessionAttributeToCheck)){
					if(o == null){
						logger.warn("Since null object found in session attribute, current session is invalid!");
						return false;
					}
				}
				logger.debug("Session is valid!");
			}		
		} catch (IllegalStateException e) {
			logger.warn("Session is invalidated!");
			return false;
		}
		return true;
	}

}
