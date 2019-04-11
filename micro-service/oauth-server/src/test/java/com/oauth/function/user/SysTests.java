package com.oauth.function.user;


import com.oauth.OauthServerApplication;
import com.oauth.function.CommonTests;
import com.oauth.sys.persist.model.SysResource;
import com.oauth.sys.persist.model.SysRole;
import com.oauth.sys.persist.model.SysUser;
import com.oauth.sys.persist.model.SysUserRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@AutoConfigureWebMvc
@SpringBootTest(classes = OauthServerApplication.class)
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
        SysRole role1 = new SysRole("ADMIN","SYSTEM_ADMIN");
//        SysRole role2 = new SysRole("GUEST");
        SysUserRole userRole1 = new SysUserRole(1L,1L);
//        SysUserRoleKey userRole2 = new SysUserRoleKey(2L,2L);
//        SysUserRoleKey userRole3 = new SysUserRoleKey(3L,2L);
//        SysRoleResourceKey roleResource = new SysRoleResourceKey(1L,1L);
        SysResource resource1 = new SysResource("/user/*","user_all");

        /* 构建request 发送请求GET请求
         * MockMvcRequestBuilders 中有很多 请求方式。像get、post、put、delete等等
         */
        String accessToken = obtainAccessToken();
        System.err.println("access_token=" + accessToken);

        execute(post(user_save_adult), user1, accessToken);
//        executePost(user_save_children, user2, accessToken);
//        executePost(user_save_children, user3, accessToken);
        execute(post(role_save), role1, accessToken);
//        executePost(role_save, role2, accessToken);
//        executePost(user_role_save, userRole1, accessToken);
//        executePost(user_role_save, userRole2, accessToken);
//        executePost(user_role_save, userRole3, accessToken);
        execute(post("/user/role"), userRole1, accessToken);
        execute(get("/user/1"), null, accessToken);
        execute(get("/user/role/resource/1"), null, accessToken);
    }


}
