package cn.zpro.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 *
 * @author zhanggl
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 自定义用户查询
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String encode = passwordEncoder.encode("123123");
        logger.info("数据库加密{}",encode);
        //此处应该链接数据库查询用户信息
        return new User("zhanggl",encode,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
