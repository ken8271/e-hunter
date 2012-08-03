package com.pccw.ehunter.helper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class JsonHelper {
	
	public static String toJsonString(Object o){
		if(o == null){
			return "[{}]";
		}
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append("[{");
			Field[] fields = o.getClass().getDeclaredFields();
			for(int i=0 ; i<fields.length ; i++){
				Field field = fields[i];
				
				if("serialVersionUID".equals(field.getName())){
					continue ;
				}
				
				String methodName = "";
				if(field.getClass().equals(boolean.class) || field.getClass().equals(Boolean.class)){
					methodName = "is" + field.getName().substring(0,1).toUpperCase() + field.getName().substring(1);
				}else {
					methodName = "get" + field.getName().substring(0,1).toUpperCase() + field.getName().substring(1);
				}
				Method method = o.getClass().getMethod(methodName, new Class[]{});
				Object obj = method.invoke(o, new Class[]{});
				buffer.append("\"" + field.getName() + "\"");
				buffer.append(":");
				if(field.getClass().equals(String.class)){
					buffer.append("\"" + ((String)obj).trim() + "\"");
				}else{
					buffer.append("\"" + obj + "\"");
				}
				if(i != fields.length-1){					
					buffer.append(",");
				}
			}
			buffer.append("}]");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
}
