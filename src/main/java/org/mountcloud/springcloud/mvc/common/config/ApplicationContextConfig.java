package org.mountcloud.springcloud.mvc.common.config;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author zhanghaishan
 * @version V1.0
 * TODO: ApplicationContext的config，主要是用于获取ApplicationContext
 * 2018/3/16.
 */
@Component
public class ApplicationContextConfig implements ApplicationContextAware {
    private static ApplicationContext applicationContextOnece;
    private static ApplicationContextConfig applicationContextConfig;
    
    @Value("${spring.application.name}")
    private String applicationName;
    
    public String getApplicationName() {
    	return this.applicationName;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(applicationContextOnece == null) {
            applicationContextOnece = applicationContext;
            applicationContextConfig = this;
        }
    }

    /**
     * 获取ApplicationContext
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContextOnece;
    }

    /**
     * 获取自己的单例
     * @return 单例
     */
    public static ApplicationContextConfig getInstance() {
    	return applicationContextConfig;
    }

    /**
     * 根据类型获取bean，空不报错
     * @param cls class
     * @param <T> 类型
     * @return bean
     */
    public static <T> T getBean(Class<T> cls){
        T result = null;
        try{
            result = getApplicationContext().getBean(cls);
        }catch (Exception e){
        }
        return result;
    }

    /**
     * 根据名字类型获取bean
     * @param name 名字
     * @param cls class
     * @param <T> 类型
     * @return bean
     */
    public static <T> T getBean(String name,Class<T> cls){
        T result = null;
        try{
            result = getApplicationContext().getBean(name,cls);
        }catch (Exception e){
        }
        return result;
    }
    
}
