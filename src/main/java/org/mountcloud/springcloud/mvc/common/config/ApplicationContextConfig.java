package org.mountcloud.springcloud.mvc.common.config;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author zhanghaishan
 * @version V1.0
 * @Package com.ugirls.graphql.config
 * @Description: TODO
 * @date 2018/3/16.
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

    public static ApplicationContext getApplicationContext(){
        return applicationContextOnece;
    }
    
    public static ApplicationContextConfig getInstance() {
    	return applicationContextConfig;
    }

    public static <T> T getBean(Class<T> cls){
        T result = null;
        try{
            result = getApplicationContext().getBean(cls);
        }catch (Exception e){
        }
        return result;
    }

    public static <T> T getBean(String name,Class<T> cls){
        T result = null;
        try{
            result = getApplicationContext().getBean(name,cls);
        }catch (Exception e){
        }
        return result;
    }
    
}
