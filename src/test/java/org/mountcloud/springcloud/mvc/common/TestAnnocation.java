package org.mountcloud.springcloud.mvc.common;

import org.junit.Test;
import org.mountcloud.springcloud.mvc.common.controller.ApiController;
import org.mountcloud.springcloud.mvc.common.controller.ApiControllerInterface;
import org.mountcloud.springproject.common.util.GsonUtil;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhanghaishan
 * @version V1.0
 * org.mountcloud.mvc.common
 * TODO:
 * 2020年1月6日.
 */
public class TestAnnocation {

	@Test
	public void test() {
		RequestMapping requestMapping = ApiController.class.getAnnotation(RequestMapping.class);
		System.out.println("name:"+requestMapping.name());
		System.out.println("path:"+GsonUtil.GsonString(requestMapping.path()));
		System.out.println("value:"+GsonUtil.GsonString(requestMapping.value()));
	}
	
	@Test
	public void testSB() {
		StringBuffer sb = new StringBuffer();
//		sb.insert(0, "1");
//		sb.insert(0, "2345");
		System.out.println(sb.toString());
	}
	
	@Test
	public void testInterfaceAnnocation() {
		Class<?>[] interfaces = TestInterfaceImpl.class.getInterfaces();
		for(int i=0;i<interfaces.length;i++) {
			RequestMapping requestMapping = interfaces[i].getAnnotation(RequestMapping.class);
			if(requestMapping!=null) {
				System.out.println("name:"+requestMapping.name());
				System.out.println("path:"+GsonUtil.GsonString(requestMapping.path()));
				System.out.println("value:"+GsonUtil.GsonString(requestMapping.value()));
			}else {
				System.out.println("is null");
			}
			
		}
	}
	
	class TestInterfaceImpl implements ApiControllerInterface{
		
	}
}
