package com.pccw.ehunter.utility;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hdiv.web.servlet.view.RedirectViewHDIV;

import com.pccw.ehunter.constant.CommonConstant;


public class RedirectViewExt extends RedirectViewHDIV{
	
	public RedirectViewExt(String url, boolean contextRelative) {
		super(url, contextRelative);
	}
	
	@Override
	protected void sendRedirect(HttpServletRequest request,HttpServletResponse response, String targetUrl, boolean http10Compatible)throws IOException {
		if(CommonConstant.ENABLE_HDIV){
			super.sendRedirect(request, response, targetUrl, http10Compatible);
		}else{
			if (http10Compatible) {
				// Always send status code 302.
				response.sendRedirect(response.encodeRedirectURL(targetUrl));
			}
			else {
				// Correct HTTP status code is 303, in particular for POST requests.
				response.setStatus(303);
				response.setHeader("Location", response.encodeRedirectURL(targetUrl));
			}
		}
	}
}
