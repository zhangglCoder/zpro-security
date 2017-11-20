package cn.zpro.security.browser.controller;

import cn.zpro.security.browser.support.ResponesMsg;
import cn.zpro.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhanggl
 */
@RestController
public class BrowserSecurityController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 用于获取spring-security 缓存访问的URL
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * spring 跳转工具类
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 当需要身份验证的时候跳转的地址
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResponesMsg authenticationRequire(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取引发跳转之前缓存起来的Url
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if(savedRequest != null){
            String redirectUrl = savedRequest.getRedirectUrl();
            logger.info("缓存的URL地址{}",redirectUrl);
            if(StringUtils.endsWithIgnoreCase(redirectUrl,".html")){
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginUrl());
                return null;
            }
        }
        return new ResponesMsg("访问地址需要服务端身份验证,请引导到登录页面");
    }

    @GetMapping("standard_login")
    public ModelAndView standardLogin(ModelAndView view) throws IOException {
        System.out.println("standard_login");
        view.setViewName("standard_login");
        return view;
    }
}
