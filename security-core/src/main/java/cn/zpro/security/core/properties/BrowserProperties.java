package cn.zpro.security.core.properties;

/**
 * 关于浏览器配置类
 * @author zhanggl
 */
public class BrowserProperties {

    private String loginUrl = "/standard_login.html";

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }
}
