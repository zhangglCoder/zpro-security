# zrpo-security 统一安全框架
### 描述：目的是想用spring的技术栈封装一套企业级统一安全框架，解决统一登录授权可重用的中间件





## 1.1	功能简介

- 统一登录(支持浏览器和App)
- 集成单点登录
- 集成自定义授权
- 集成验证码扩展
- 集成分布式环缓存
- 集成第三方登录（`QQ/微信`）




## 1.2 使用框架

- spring-platform
- spring-boot
- spring-security
- spring-social
- spring-thymeleaf
- spring-session



## 1.3 快速开始

### 1.3.1 配置文件

application.properties 文件

```properties
#zpro-security配置
zpro.security.browser.loginUrl = /login.html
```



### 1.3.2 自定义接口

```java
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
```



spring-security 返回对象

```json
{
  password: null,
  username: "zhanggl",
  authorities: [
    {
      authority: "admin"
    }
  ],
  accountNonExpired: true,
  accountNonLocked: true,
  credentialsNonExpired: true,
  enabled: true
}
```

### 1.3.3 具体操作请查看Demo模块







