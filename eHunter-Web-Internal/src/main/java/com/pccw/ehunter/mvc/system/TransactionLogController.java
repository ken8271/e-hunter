package com.pccw.ehunter.mvc.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pccw.ehunter.mvc.BaseController;

@Controller
public class TransactionLogController extends BaseController{

	@RequestMapping("/system/initTransactionlogSearch.do")
	public ModelAndView initTransactionlogSearch(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("system/listOfTransactionlog");
		return mv;
	}
}
