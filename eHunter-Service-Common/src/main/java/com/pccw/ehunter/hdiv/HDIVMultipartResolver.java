package com.pccw.ehunter.hdiv;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hdiv.config.multipart.IMultipartConfig;
import org.hdiv.filter.RequestWrapper;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

/**
 * Servlet-based MultipartResolver implementation for
 * <a href="http://jakarta.apache.org/commons/fileupload">Jakarta Commons FileUpload</a>
 * 1.1 or higher and HDIV for Spring MVC.
 *
 * <p>Provides maxUploadSize, maxInMemorySize, and defaultEncoding settings as
 * bean properties (inherited from CommonsFileUploadSupport). See respective
 * ServletFileUpload / DiskFileItemFactory properties (sizeMax, sizeThreshold,
 * headerEncoding) for details in terms of defaults and accepted values.
 *
 * <p>Saves temporary files to the servlet container's temporary directory.
 * Needs to be initialized <i>either</i> by an application context <i>or</i>
 * via the constructor that takes a ServletContext (for standalone usage).
 *
 * <p><b>NOTE:</b> As of Spring 2.0, this multipart resolver requires
 * Commons FileUpload 1.1 or higher. The implementation does not use
 * any deprecated FileUpload 1.0 API anymore, to be compatible with future
 * Commons FileUpload releases.
 *
 * @author Beck Lu
 * @see #CommonsMultipartResolver(ServletContext)
 * @see CommonsMultipartFile
 * @see org.springframework.web.portlet.multipart.PortletMultipartResolver
 * @see org.apache.commons.fileupload.servlet.ServletFileUpload
 * @see org.apache.commons.fileupload.disk.DiskFileItemFactory
 */
public class HDIVMultipartResolver extends CommonsMultipartResolver{
private boolean resolveLazily = false;
	
	@SuppressWarnings("unchecked")
	public MultipartHttpServletRequest resolveMultipart(HttpServletRequest request) throws MultipartException {
		
		if (request instanceof RequestWrapper) {
			
			final RequestWrapper requestWrapper = (RequestWrapper) request;
			
			Exception multipartException = (Exception) request.getAttribute(IMultipartConfig.FILEUPLOAD_EXCEPTION);
			if (multipartException != null) {
				
				if (multipartException instanceof MaxUploadSizeExceededException) {
					throw (MaxUploadSizeExceededException) multipartException;
				} else {
					throw new MultipartException("Could not parse multipart servlet request", multipartException);					
				} 			
			}
			
			if (this.resolveLazily) {
				return new DefaultMultipartHttpServletRequest(requestWrapper) {
					protected void initializeMultipart() {
						setMultipartFiles(parseMultiPart(requestWrapper.getFileElements()));
						setMultipartParameters(requestWrapper.getTextElements());
					}
				};
			} else {			
				MultipartParsingResult parsingResult = 
					new MultipartParsingResult(parseMultiPart(requestWrapper.getFileElements()), requestWrapper.getTextElements());
	
				return new DefaultMultipartHttpServletRequest(request, parsingResult.getMultipartFiles(), parsingResult
						.getMultipartParameters());
			}
		} else {
			return defaultResolveMultipart(request);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MultipartHttpServletRequest defaultResolveMultipart(HttpServletRequest request) throws MultipartException {
		
		String encoding = determineEncoding(request);
		FileUpload fileUpload = prepareFileUpload(encoding);
		try {
			List fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);
			MultipartParsingResult parsingResult = parseFileItems(fileItems, encoding);
			return new DefaultMultipartHttpServletRequest(
					request, parsingResult.getMultipartFiles(), parsingResult.getMultipartParameters());
		}
		catch (FileUploadBase.SizeLimitExceededException ex) {
			throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), ex);
		}
		catch (FileUploadException ex) {
			throw new MultipartException("Could not parse multipart servlet request", ex);
		}
	}	

	/**
	 * Set whether to resolve the multipart request lazily at the time of
	 * file or parameter access.
	 * <p>Default is "false", resolving the multipart elements immediately, throwing
	 * corresponding exceptions at the time of the {@link #resolveMultipart} call.
	 * Switch this to "true" for lazy multipart parsing, throwing parse exceptions
	 * once the application attempts to obtain multipart files or parameters.
	 */
	public void setResolveLazily(boolean resolveLazily) {
		this.resolveLazily = resolveLazily;
	}	
	
	private MultiValueMap<String, MultipartFile> parseMultiPart(Map<String, CommonsMultipartFile> fileItems){
		MultiValueMap<String, MultipartFile> multipartFiles = new LinkedMultiValueMap<String, MultipartFile>();
		
		if(!CollectionUtils.isEmpty(fileItems)){
			CommonsMultipartFile file= null;
			Iterator<String> iterator = fileItems.keySet().iterator();
			
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				file = fileItems.get(key);
				multipartFiles.add(file.getName(), file);
			}
		}
		return multipartFiles;
	}
}
