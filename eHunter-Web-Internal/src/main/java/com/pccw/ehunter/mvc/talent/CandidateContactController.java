package com.pccw.ehunter.mvc.talent;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.mvc.BaseController;

@Controller
@SessionAttributes
public class CandidateContactController extends BaseController{
	
	@RequestMapping("/talent/viewCandidateContactHistory.do")
	public ModelAndView viewCandidateContactHistory(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("talent/candidateContactHistoryView");
		return mv;
	}
	
	@RequestMapping("/talent/viewCandidateContactHistoryDetail.do")
	public ModelAndView viewCandidateContactHistoryDetail(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("talent/candidateContactHistoryDetail");
		return mv;
	}
}
