package cn.org.polaris.admin;

import org.springframework.beans.factory.annotation.Autowired;

import cn.org.polaris.helper.MessageHelper;

public class BaseController {

	@Autowired
	protected MessageHelper messageHelper;
	
}
