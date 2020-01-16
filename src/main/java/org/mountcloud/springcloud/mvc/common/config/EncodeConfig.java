package org.mountcloud.springcloud.mvc.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author zhanghaishan
 * @version V1.0
 * org.mountcloud.mvc.common.config
 * TODO:
 * 2020年1月6日.
 */
@Configuration
public class EncodeConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
