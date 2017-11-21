package cn.zpro.security.browser.config;

import cn.zpro.security.browser.support.ResponesMsg;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhanggl处理身份验证成功的回调函数
 */
@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private RequestCache requestCache = new HttpSessionRequestCache();

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest!=null){
            String redirectUrl = savedRequest.getRedirectUrl();
            logger.info("访问页面地址{}",redirectUrl);
            if (null != redirectUrl) {
                if (StringUtils.endsWithIgnoreCase(redirectUrl, ".html")) {
                    super.onAuthenticationSuccess(request, response, authentication);
                }
                //返回JSON
                String valueAsString = objectMapper.writeValueAsString(new ResponesMsg(authentication.getPrincipal()));
                response.setHeader("Content-Type","text/html; charset=utf-8");
                response.getWriter().write(valueAsString);
            }
        }else {
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }

}
