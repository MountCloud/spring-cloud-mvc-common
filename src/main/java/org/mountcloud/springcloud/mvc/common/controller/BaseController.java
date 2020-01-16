package org.mountcloud.springcloud.mvc.common.controller;

import org.mountcloud.cfgver.common.entity.BaseEntity;
import org.mountcloud.springcloud.mvc.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController<E extends BaseEntity,S extends BaseService<E>> {
	
	@Autowired
	protected S service;
	
}
