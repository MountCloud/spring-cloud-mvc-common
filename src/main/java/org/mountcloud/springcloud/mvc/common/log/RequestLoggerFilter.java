package org.mountcloud.springcloud.mvc.common.log;


import com.github.isrsal.logging.RequestWrapper;
import com.github.isrsal.logging.ResponseWrapper;


import org.apache.commons.lang.StringUtils;
import org.mountcloud.springcloud.mvc.common.config.ApplicationContextConfig;
import org.mountcloud.springcloud.mvc.common.util.RequestUtil;
import org.mountcloud.springproject.common.util.GZipUtils;
import org.mountcloud.springproject.common.util.GsonUtil;
import org.mountcloud.springproject.common.util.LoggerUtil;
import org.mountcloud.springproject.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


/**
  * @author zhanghaishan
  * @version V1.0
  *
  * TODO: 日志filter，用于打印所有的请求日志，可以通过配置进行关闭
  * 2020/1/17.
  */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestLoggerFilter implements Filter {
	
	@Value("${logging.show-requestlog:true}")
	private boolean showLog;
	
	@SuppressWarnings("unused")
	private final static Logger log = LoggerFactory.getLogger(RequestLoggerFilter.class);


	/**
	 * 初始化
	 * @param filterConfig 配置
	 * @throws ServletException 异常
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	/**
	 * 打印日志
	 * @param req 请求
	 * @param resp 响应
	 * @param chain 下一个filter
	 * @throws IOException ex
	 * @throws ServletException ex
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		if(!showLog) {
			return;
		}
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpServletRequest request = (HttpServletRequest) req;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");
		long start = Calendar.getInstance().getTimeInMillis();
		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {


			RequestWrapper requestWrapper = new RequestWrapper(0L,request);
			ResponseWrapper responseWrapper = new ResponseWrapper(0L,response);

			chain.doFilter(requestWrapper, responseWrapper);

			try{
				if(!request.getRequestURL().toString().endsWith("/health")) {
					long end = Calendar.getInstance().getTimeInMillis();

					Object requestContentObj = null;
					Object responseContentObj = null;

					byte[] requestBytes = requestWrapper.toByteArray();
					byte[] responseBytes = responseWrapper.toByteArray();

					String contentEncoding = response.getHeader("Content-Encoding");
					if(!StringUtils.isEmpty(contentEncoding)&&contentEncoding.equals("gzip")){
						try{
							responseBytes = GZipUtils.decompress(responseBytes);
						}catch (Exception e){
							log.info("GZip decode fail!!");
						}
					}

					String requestCotnent = StringUtil.byteToString(requestBytes);
					String responseContent = StringUtil.byteToString(responseBytes);

					@SuppressWarnings("rawtypes")
					HashMap requestMap = null;
					if(requestCotnent!=null&&requestCotnent.length()>0){
						try{

							requestMap = GsonUtil.GsonToBean(requestCotnent,HashMap.class);
						}catch (Exception e){
						}
					}else{
						requestMap = RequestUtil.getRequestContent(request);
					}
					if(requestMap!=null){
						requestContentObj = requestMap;
					}else{
						requestContentObj = requestCotnent;
					}

					@SuppressWarnings("rawtypes")
					HashMap responseMap = null;
					if(responseContent!=null&&responseContent.length()>0){
						try{
							responseMap = GsonUtil.GsonToBean(responseContent,HashMap.class);
						}catch (Exception e){
						}
					}
					if(responseMap!=null){
						responseContentObj = responseMap;
					}else{
						responseContentObj = responseContent;
					}

					String ip = RequestUtil.getRemoteIp(request);
					
					RequestLogger requestLogger = null;

					if(responseMap!=null){
						requestLogger = new RequestLogger("FirstFilter-Log",request.getMethod(),request.getRequestURI(),requestContentObj,responseContentObj,end-start,ip,StringUtil.toString(response.getStatus()));
					}else{
						requestLogger = new RequestLogger("FirstFilter-Log",request.getMethod(),request.getRequestURI(),requestCotnent,responseContent,end-start,ip,StringUtil.toString(response.getStatus()));
					}
					
					LoggerUtil.getLogger(RequestLoggerFilter.class).info(this.toString());
					
					Map<String, RequestLoggerOperate> operates = ApplicationContextConfig.getApplicationContext().getBeansOfType(RequestLoggerOperate.class);
					if(operates!=null&&operates.size()>0) {
						for(String name : operates.keySet()) {
							RequestLoggerOperate requestLoggerOperate = operates.get(name);
							try {
								requestLoggerOperate.operator(requestLogger);
							} catch (Exception e) {
							}
						}
					}
				}
			}catch (Exception e){
				
			}

		}

	}

	@Override
	public void destroy() {

	}
}
