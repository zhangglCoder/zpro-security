package cn.zpro.security.core.config;

import cn.zpro.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhanggl
 */
@Configuration
@EnableConfigurationProperties(value = SecurityProperties.class)
public class SecurityCoreConfig {


}
