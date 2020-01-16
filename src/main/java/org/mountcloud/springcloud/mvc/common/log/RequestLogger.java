package org.mountcloud.springcloud.mvc.common.log;

import org.mountcloud.springproject.common.util.GsonUtil;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * 请求日志
 */
public class RequestLogger {

	@Override
	public String toString() {
		return GsonUtil.GsonString(this);
	}

	/**
	 * 写下一条日志
	 * 
	 * @param name      日志类型的名字
	 * @param method    请求方法
	 * @param url       请求的url地址
	 * @param request   request消息体的内容，如果是GET的话，就写GET
	 * @param response  返回的消息体的内容
	 * @param timeSpent 本次访问的消耗时间
	 */
	public RequestLogger(String name, String method, String url, Object request, Object response, long timeSpent,
			String caller, String httpCode) {

		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.createAt = new Date(Calendar.getInstance().getTimeInMillis());
		this.method = method.toUpperCase();
		this.url = url;
		this.request = request;
		this.response = response;
		this.timeSpent = timeSpent;
		this.caller = caller;
		this.httpCode = httpCode;
	}

	/**
	 * 本条日志的ID
	 */
	private String id;
	/**
	 * 日志的名称
	 */
	private String name;
	/**
	 * 日志产生的时间
	 */
	private Date createAt;
	/**
	 * 请求的http方法
	 */
	private String method;
	/**
	 * 请求的url地址
	 */
	private String url;
	/**
	 * 请求的消息体
	 */
	private Object request;
	/**
	 * 返回的消息体
	 */
	private Object response;
	/**
	 * 本次请求消耗的时长，单位毫秒
	 */
	private long timeSpent;
	/**
	 * 进行本次操作的UserId
	 */
	private String caller;

	/**
	 * http code
	 */
	private String httpCode;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Object getRequest() {
		return request;
	}

	public void setRequest(Object request) {
		this.request = request;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public long getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(long timeSpent) {
		this.timeSpent = timeSpent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public String getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(String httpCode) {
		this.httpCode = httpCode;
	}

}
