package org.mountcloud.springcloud.mvc.common.mq;

import org.mountcloud.springcloud.mvc.common.config.ApplicationContextConfig;

public class BaseMq {
	
	
	public static String getLocalName(String name) {
		String result = ApplicationContextConfig.getInstance().getApplicationName()+"."+name;
		return result;
	}
	
	public static String getSendName(String projectName,String method) {
		return projectName +"." + method;
	}
	

}
