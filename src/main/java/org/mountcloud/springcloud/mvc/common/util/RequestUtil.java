package org.mountcloud.springcloud.mvc.common.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * @author zhanghaishan
 * @version V1.0
 * TODO 请求工具
 * 2018/2/8.
 */
public class RequestUtil {

    private static Logger log = LoggerFactory.getLogger(RequestUtil.class);

    /**
     * 把头打印出来
     * @param logger 日志
     * @param request 请求
     */
    public static void printHeads(Logger logger, HttpServletRequest request) {

        if (logger != null && request != null) {
            @SuppressWarnings("rawtypes")
			Enumeration enu = request.getHeaderNames();//取得全部头信息
            while (enu.hasMoreElements()) {//以此取出头信息
                String headerName = (String) enu.nextElement();
                String headerValue = request.getHeader(headerName);//取出头信息内容

                logger.debug("http header "+headerName+"="+headerValue);
            }
        }
    }

    /**
     * 内容URL加密
     * @param content 内容
     * @return 结果
     */
    public static String urlEncode(String content){
        String resutl = content;
        try {
            resutl = URLEncoder.encode(content,"utf-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
        return resutl;
    }

    /***
     * 把request里的请求封装为map
     * @param request 请求
     * @return 结果
     */
    public static HashMap<String, String> getRequestContent(HttpServletRequest request) {
        HashMap<String, String> content = new HashMap<String, String>();
        Enumeration<String> rnames = request.getParameterNames();
        for (Enumeration<String> e = rnames; e.hasMoreElements();) {
            Object obj = e.nextElement();
            String thisName = obj.toString();
            String thisValue = request.getParameter(thisName);
            content.put(thisName, thisValue);
        }

        return content;
    }

    /**
     * 获取客户端的IP地址，主要是用于获取x-forwarded-for
     * @param request 请求
     * @return 结果
     */
    public static String getRemoteIp(HttpServletRequest request){
        String remoteIp ="";
        String ip = request.getHeader("x-forwarded-for");
        if(ip != null) {
            remoteIp = ip;
        }else {
            remoteIp = "";
        }
        return remoteIp;
    }

}
