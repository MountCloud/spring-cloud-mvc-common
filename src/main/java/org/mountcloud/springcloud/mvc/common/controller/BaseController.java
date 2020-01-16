package org.mountcloud.springcloud.mvc.common.controller;

import org.mountcloud.springcloud.mvc.common.service.BaseService;
import org.mountcloud.springproject.common.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
  * @author zhanghaishan
  * @version V1.0
  *
  * TODO: 总控制器，约束mvc中的controller
  * 2020/1/17.
  */
public class BaseController<E extends BaseEntity,S extends BaseService<E>> {
	
	@Autowired
	protected S service;
	
}
