package org.mountcloud.springcloud.mvc.common.config;

import org.mountcloud.springcloud.mvc.common.handler.ControllerApiRequestMappingHandlerMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author zhanghaishan
 * @version V1.0
 * org.mountcloud.mvc.common.config
 * TODO:
 * 2020年1月6日.
 */
@Configuration
//这个注解很重要啊，不写不生效
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebMvcConfiguration implements WebMvcRegistrations{
	
	//是否打印mapping？2.0不自己打印了，可以通过mapping.showpath配置控制
	@Value("${logging.show-mapping:true}")
	private boolean showMappingPath;
	
	@Override
	public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
		return new ControllerApiRequestMappingHandlerMapping(showMappingPath);
	}

}
