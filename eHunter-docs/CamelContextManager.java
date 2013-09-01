package com.citi.factory.camel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.citi.factory.camel.context.F2CamelContext;
import com.citi.factory.dao.F2CamelConfigDAO;
import com.citi.factory.dao.F2TemplateDAO;
import com.citi.factory.data.def.F2CamelConfig;
import com.citi.factory.data.def.F2Template;

public class CamelContextManager {
	
	private Map<Integer,F2CamelContext> ctxColl = new HashMap<Integer,F2CamelContext>();
	
	public void startup(boolean async) {
		List<F2CamelConfig> configs = F2CamelConfigDAO.getConfigures();
		
		if(configs != null && configs.size() != 0){
			for(final F2CamelConfig cfg : configs){
				if(async){
					new Thread(new Runnable(){

						public void run() {
							startContext(cfg);
						}
						
					}).start();
				}else {
					startContext(cfg);
				}
			}
		}else {
			System.out.println("[startup] - no any config in data base");
		}
	}
	
	public void shutdown(){
		
	}
	
	public void startContext(F2CamelConfig cfg) {
		try {
			F2CamelContext ctx = new F2CamelContext();
			ctx.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addContext(){
		
	}
	
	public void removeContext(){
		
	}
	
	public F2CamelContext getContext(){
		return null;
	}
	
	public void initRoutes(int ctxid) throws Exception{
		List<F2Template> tpls = F2TemplateDAO.getF2TemplatesBySrcID();
		
		if(tpls != null && tpls.size() != 0){
			for(F2Template tpl : tpls){
				addRoute(tpl);
			}
		}
	}
	
	public void addRoute(F2Template tpl) throws Exception{
		int ctxid = tpl.getCtxID();
		
		F2CamelContext ctx = ctxColl.get(ctxid);
		
		if(ctx==null) startContext(F2CamelConfigDAO.getConfigure(ctxid));
		
		ctx.doRoute();
	}
	
	public void updateRoute(){
		
	}
	
	public void removeRoute(){
		
	}
}
