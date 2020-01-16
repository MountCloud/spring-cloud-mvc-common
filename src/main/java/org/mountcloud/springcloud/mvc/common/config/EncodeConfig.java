package org.mountcloud.springcloud.mvc.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author zhanghaishan
 * @version V1.0
 * TODO: 加密配置
 * 2020年1月6日.
 */
@Configuration
public class EncodeConfig {

	/**
	 * 密码加密器
	 * @return 密码加密器
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
