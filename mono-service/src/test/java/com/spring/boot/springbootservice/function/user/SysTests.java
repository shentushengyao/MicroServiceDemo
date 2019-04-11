package com.spring.boot.springbootservice.function.user;

import com.saiyo.framework.FrameworkApplication;
import com.saiyo.framework.function.CommonTests;
import com.saiyo.framework.sys.persist.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = FrameworkApplication.class)
@AutoConfigureMockMvc
@Slf4j
public class SysTests extends CommonTests {

    private final static String user_save_adult = "/user/adult";
    private final static String user_save_children = "/user/children";
    private final static String role_save = "/role";
    private final static String user_role_save = "/user/role";
    private final static String role_resource_save = "/role/resource";
    private final static String resource_save = "/resource";

    @Test
    public void initTest() throws Exception {
        //准备请求url  不用带ip、端口、项目名称等 直接写接口的映射地址就可以了
        SysUser user1 = new SysUser("admin", "admin", "ADMIN", 18, true);
//        SysUser user2 = new SysUser("GUEST", "GUEST", "GUEST", 17, true);
//        SysUser user3 = new SysUser("TEST", "TEST", "TEST", 17, true);
        SysRole role1 = new SysRole("ADMIN");
//        SysRole role2 = new SysRole("GUEST");
        SysUserRoleKey userRole1 = new SysUserRoleKey(1L,1L);
//        SysUserRoleKey userRole2 = new SysUserRoleKey(2L,2L);
//        SysUserRoleKey userRole3 = new SysUserRoleKey(3L,2L);
        SysRoleResourceKey roleResource = new SysRoleResourceKey(1L,1L);
        SysResource resource1 = new SysResource("/user/*");

        /* 构建request 发送请求GET请求
         * MockMvcRequestBuilders 中有很多 请求方式。像get、post、put、delete等等
         */
        String accessToken = obtainAccessToken();
        System.err.println("access_token=" + accessToken);

        executePost(user_save_adult, user1, accessToken);
//        executePost(user_save_children, user2, accessToken);
//        executePost(user_save_children, user3, accessToken);
        executePost(role_save, role1, accessToken);
//        executePost(role_save, role2, accessToken);
        executePost(user_role_save, userRole1, accessToken);
//        executePost(user_role_save, userRole2, accessToken);
//        executePost(user_role_save, userRole3, accessToken);

    }


}
