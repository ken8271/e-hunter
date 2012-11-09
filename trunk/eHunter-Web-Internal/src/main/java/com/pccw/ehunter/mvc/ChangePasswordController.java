package com.pccw.ehunter.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.constant.ModuleIndicator;
import com.pccw.ehunter.constant.SessionAttributeConstant;
import com.pccw.ehunter.dto.ChangePasswordDTO;
import com.pccw.ehunter.service.InternalUserManagementService;
import com.pccw.ehunter.utility.RedirectViewExt;
import com.pccw.ehunter.utility.SecurityUtils;
import com.pccw.ehunter.validator.ChangePasswordValidator;

@Controller
public class ChangePasswordController extends BaseController {
	
	@Autowired
	private InternalUserManagementService internalUserService;
	
	@Autowired
	private ChangePasswordValidator changePasswordValidator;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@RequestMapping("/initChangePassword.do")
	public ModelAndView initChangePassword(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("changePassword/changePassword");
		
		mv.addObject(SessionAttributeConstant.CHANGE_PASSWORD_DTO, new ChangePasswordDTO());
		return mv;
	}
	
	@RequestMapping("/changePassword.do")
	public ModelAndView changePassword(HttpServletRequest request , @ModelAttribute(SessionAttributeConstant.CHANGE_PASSWORD_DTO)ChangePasswordDTO changePasswordDto , BindingResult errors){
		ModelAndView mv = new ModelAndView(new RedirectViewExt("/completeChangePassword.do", true));
		
		changePasswordValidator.validate(changePasswordDto, errors);
		
		if(errors.hasErrors()){
			mv = new ModelAndView("changePassword/changePassword");
			mv.addObject(SessionAttributeConstant.CHANGE_PASSWORD_DTO, changePasswordDto);
			return mv;
		}
		
		internalUserService.changePassword(SecurityUtils.getUserRecId(), changePasswordDto.getNewPassword());
		
        String userName = SecurityUtils.getUserName();
        SecurityContextHolder.clearContext();
        
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, changePasswordDto.getNewPassword());
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
         request.getSession(false).setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,SecurityContextHolder.getContext());
         
         transactionLogService.logTransaction(ModuleIndicator.SYSTEM, getMessage("tx.log.system.user.changepassword"));
		return mv;
	}
	
	@RequestMapping("/completeChangePassword.do")
	public ModelAndView completeChangePassword(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("changePassword/changePasswordSuccess");
		request.getSession(false).removeAttribute(SessionAttributeConstant.CHANGE_PASSWORD_DTO);
		return mv;
	}
}
