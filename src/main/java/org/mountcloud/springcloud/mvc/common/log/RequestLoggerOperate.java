/**
 * 
 */
package org.mountcloud.springcloud.mvc.common.log;

/**
 * @author zhanghaishan
 * @version V1.0
 * TODO: 日志操作类，可以通过注册的方式，扩展filter功能，比如实现这个接口，然后使用@Component注册进去
 * 2020年1月15日.
 */
public interface RequestLoggerOperate {
	
	public void operator(RequestLogger requestLogger);

}
