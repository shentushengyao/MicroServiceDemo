package com.oauth.sys.persist.model;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class SysUserValidation extends SysUser {

    public interface Default{}
    public interface Adult{}
    public interface Children{}


    @Size(min = 4, max = 32, message = "用户名长度必须在{min}-{max}之间",groups = {Default.class})
    private String username;
    @Length(min = 4, max = 32, message = "密码长度必须在{min}-{max}之间",groups = {Default.class})
    private String password;
    @Length(min = 4, max = 32, message = "昵称长度必须在{min}-{max}之间",groups = {Default.class})
    private String name;
    @Min( message = "小孩子年龄必须在{value}岁以上", value = 1,groups = {Children.class})
    @Max(message = "小孩子年龄必须在{value}岁以下", value = 17,groups = {Children.class})
    @Min( message = "成人年龄必须在{value}岁以上", value = 18,groups = {Adult.class})
    @Max( message = "成人年龄必须在{value}岁以下", value = 100,groups = {Adult.class})
    private Integer age;
}