package org.mountcloud.springcloud.mvc.common.handler;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mountcloud.springproject.common.util.LoggerUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author zhanghaishan
 * @version V1.0
 * TODO: 这个就是扩展mapping注册的类
 * 2020年1月6日.
 */
public class ControllerApiRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
	
	/**
	 * 是否打印mapping
	 */
	private boolean logMappingPath = true;
	
	public ControllerApiRequestMappingHandlerMapping(boolean logMappingPath) {
		this.logMappingPath = logMappingPath;
	}

	/**
	 * 提供mappinginfo，这里提供的mappinginfo就是最终注册的mapping
	 * @param method 是哪个方法前来注册
	 * @param handlerType 是哪个类前来注册
	 * @return 注册结果
	 */
	@Override
	protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
		RequestMappingInfo mappingInfo = super.getMappingForMethod(method, handlerType);
		mappingInfo = appendParentRequestMapping(handlerType,mappingInfo);
		logMapping(mappingInfo);
		return mappingInfo;
	}
	
	/**
	 * 添加夫类的mapping
	 * @param handlerType 注册的类
	 * @param mappingInfo mappinginfo
	 * @return 结果
	 */
	protected RequestMappingInfo appendParentRequestMapping(Class<?> handlerType,RequestMappingInfo mappingInfo) {
		if(mappingInfo==null) {
			return mappingInfo;
		}
		//需要添加的路径
		List<String> paths = new ArrayList<String>();
		
		//获取interface里的配置
		Class<?>[] interfaces = handlerType.getInterfaces();
		List<String> interfacesPath = getRequestMappingValues(interfaces);
		if(interfacesPath.size()>0) {
			paths.addAll(interfacesPath);
		}
		
		//获取父类
		Class<?> superClass = handlerType.getSuperclass();
		if(superClass==null) {
			return mappingInfo;
		}
		RequestMapping parentRequestMapping = superClass.getAnnotation(RequestMapping.class);
		if(parentRequestMapping!=null&&parentRequestMapping.value().length>0) {
			paths.addAll(new ArrayList<String>(Arrays.asList(parentRequestMapping.value())));
		}
		
		//最后添加
		if(paths.size()>0) {
			String[] pathArray = new String[paths.size()];
			pathArray = paths.toArray(pathArray);
			//使用path工具向前追加夫类的path
			mappingInfo = RequestMappingInfo.paths(pathArray).build().combine(mappingInfo);
		}
		return appendParentRequestMapping(superClass,mappingInfo);
	}
	
	/**
	 * 根据class来获取path
	 * @param classes class列表
	 * @return 路径
	 */
	private List<String> getRequestMappingValues(Class<?> ...classes) {
		List<String> results = new ArrayList<String>();
		for(int i=0;i<classes.length;i++) {
			Class<?> tempClass = classes[i];
			RequestMapping parentRequestMapping = tempClass.getAnnotation(RequestMapping.class);
			if(parentRequestMapping!=null&&parentRequestMapping.value().length>0) {
				results.addAll(new ArrayList<String>(Arrays.asList(parentRequestMapping.value())));
			}
		}
		return results;
	}
	
	/**
	 * 由于spring boot2不打印mapping了，不习惯，就自己打印一下，但是有些系统mapping也不打印，有空再研究怎么打印
	 * @param info mapinginfo
	 */
	private void logMapping(RequestMappingInfo info) {
		if(!logMappingPath||info==null) {
			return;
		}
		//此处等价Logger.info
		LoggerUtil.getLogger(ControllerApiRequestMappingHandlerMapping.class).info("mapping path:"+info.toString());
	}
}
