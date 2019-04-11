package com.oauth.sys.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import javax.sql.DataSource;

/**
 * client信息通过jdbc去查询数据库验证，（注释部分是通过Redis方法去验证）
 *
 * @Author: Stsy
 * @Date: 2018/12/3 16:38
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurerAdapterConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private Environment env;

//    @Autowired
//    private TokenStoreConfig tokenStore;

    // 使用最基本的InMemoryTokenStore生成token

    private TokenStore tokenStore = new InMemoryTokenStore();

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceConfig userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    //令牌失效时间
    public int accessTokenValiditySeconds = 36000;

    //刷新令牌失效时间
    public int refreshTokenValiditySeconds = 36000;

    //是否可以重用刷新令牌
    public boolean isReuseRefreshToken = true;

    //是否支持刷新令牌
    public boolean isSupportRefreshToken = true;


    /**
     * 用来配置客户端详情服务（ClientDetailsService），
     * 客户端详情信息在这里进行初始化，
     * 你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //  secret密码配置从 Spring Security 5.0开始必须以 {加密方式}+加密后的密码 这种格式填写
        //  当前版本5新增支持加密方式：
        //  bcrypt - BCryptPasswordEncoder (Also used for encoding)
        //  ldap - LdapShaPasswordEncoder
        //  MD4 - Md4PasswordEncoder
        //  MD5 - new MessageDigestPasswordEncoder("MD5")
        //  noop - NoOpPasswordEncoder
        //  pbkdf2 - Pbkdf2PasswordEncoder
        //  scrypt - SCryptPasswordEncoder
        //  SHA-1 - new MessageDigestPasswordEncoder("SHA-1")
        //  SHA-256 - new MessageDigestPasswordEncoder("SHA-256")
        //  sha256 - StandardPasswordEncoder
        clients
                .inMemory()
                .withClient("client")
                .secret(passwordEncoder.encode("secret"))
                .authorizedGrantTypes("authorization_code", "password", "refresh_token")
                .scopes("all")
                .accessTokenValiditySeconds(60 * 10)
                .refreshTokenValiditySeconds(60 * 10);
        //  clients.jdbc(dataSource);
    }

    /**
     * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        //配置TokenServices参数
        DefaultTokenServices tokenServices = new DefaultTokenServices();

        tokenServices.setReuseRefreshToken(isReuseRefreshToken);
        tokenServices.setSupportRefreshToken(isSupportRefreshToken);
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setAccessTokenValiditySeconds(accessTokenValiditySeconds);
        tokenServices.setRefreshTokenValiditySeconds(refreshTokenValiditySeconds);
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());

        endpoints
//                .tokenServices(tokenServices)
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);

    }


    /**
     * 用来配置令牌端点(Token Endpoint)的安全约束.
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
    }


}