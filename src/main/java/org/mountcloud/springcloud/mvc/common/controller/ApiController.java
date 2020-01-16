package org.mountcloud.springcloud.mvc.common.controller;

import org.mountcloud.springcloud.mvc.common.config.APIConfig;
import org.mountcloud.springcloud.mvc.common.service.BaseService;
import org.mountcloud.springproject.common.entity.BaseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhanghaishan
 * @version V1.0
 * TODO: api控制器，mapping是APIConfig.API_PATH，通过继承方式给mapping加前缀，继承此类的控制器，会给控制器加上前缀，比如 /test 控制器继承此类后，路径为/api/test
 * 2020年1月6日.
 */
@RequestMapping(APIConfig.API_PATH)
public class ApiController<E extends BaseEntity,S extends BaseService<E>> extends BaseController<E,S>{

}
