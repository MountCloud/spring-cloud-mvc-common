package org.mountcloud.springcloud.mvc.common.controller;

import org.mountcloud.cfgver.common.entity.BaseEntity;
import org.mountcloud.springcloud.mvc.common.config.APIConfig;
import org.mountcloud.springcloud.mvc.common.service.BaseService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhanghaishan
 * @version V1.0
 * org.mountcloud.mvc.common.controller
 * TODO:
 * 2020年1月6日.
 */
@RequestMapping(APIConfig.API_PATH)
public class ApiController<E extends BaseEntity,S extends BaseService<E>> extends BaseController<E,S>{

}
