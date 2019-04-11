package com.spring.boot.jpa.entity;


import com.spring.boot.common.jpa.presist.common.base.JpaBaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

/**
 * @Author: Stsy
 * @Date: 2018/11/23 11:13
 */

@Data
@Entity
@Table(name = "sys_user")
//@JsonIgnoreProperties({"roles"})
public class SysUser extends JpaBaseEntity {
    public interface Adult{}
    public interface Children{}
    public interface Default{}

    @Length(groups = {Default.class},min = 4, max = 32, message = "用户名长度必须在{min}-{max}之间")
    @Column(length = 32,nullable = false, unique = true)
    private String username;
    @Length(groups = {Default.class},min = 4, max = 32, message = "密码长度必须在{min}-{max}之间")
    @Column(length = 64,nullable = false)
    private String password;
    @Length(groups = {Default.class},min = 4, max = 32, message = "昵称长度必须在{min}-{max}之间")
    @Column(length = 32,nullable = false)
    private String name;
    @Min(groups = {Children.class}, message = "小孩子年龄必须在{value}岁以上", value = 1)
    @Max(groups = {Children.class}, message = "小孩子年龄必须在{value}岁以下", value = 17)
    @Min(groups = {Adult.class}, message = "成人年龄必须在{value}岁以上", value = 18)
    @Max(groups = {Adult.class}, message = "成人年龄必须在{value}岁以下", value = 100)
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private boolean sex;

    @ManyToMany
    @JoinTable(
            name = "sys_user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
//    @JsonIgnoreProperties({"users"})
    private Set<SysRole> roles ;

    public SysUser() {
    }

    public SysUser(String username, String password, String name, int age, boolean sex) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public void addRole(SysRole role) {
        this.roles.add(role);
    }

    public void removeRole(SysRole role) {
        if(this.roles.contains(role)){
            this.roles.remove(role);
        }
    }

}
