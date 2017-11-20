package cn.zpro.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 统一安全配置类
 * @author zhanggl
 */
@ConfigurationProperties(prefix = "zpro.security")
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();


    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
