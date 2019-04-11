package com.spring.boot.jpa.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.boot.common.jpa.presist.common.base.JpaBaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * @Author: Stsy
 * @Date: 2018/11/23 11:13
 */
@Data
@Entity
@Table(name = "sys_role")
@JsonIgnoreProperties({"users"})
public class SysRole extends JpaBaseEntity {

    @Length(min = 4, max = 32, message = "长度必须在{min}-{max}之间")
    @Column(length = 32, unique = true, nullable = false)
    private String name;
    @Length(min = 1, max = 64)
    @Column(length = 64)
    private String discription;

    @ManyToMany(mappedBy = "roles")
//    @JsonIgnoreProperties({"roles"})
    private Set<SysUser> users ;

//    @ManyToMany
//    @JoinTable(
//            name = "sys_role_resource",
//            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "id")})
//    private Set<SysResource> resources ;

    public void addUser(SysUser user) {
        users.add(user);
    }

    public void removeUser(SysUser user) {
        users.remove(user);
    }

//    public void addResource(SysResource resource) {
//        resources.add(resource);
//    }
//
//    public void removeResource(SysResource resource) {
//        resources.remove(resource);
//    }

    public SysRole() {
    }
    public SysRole(String name) {
        this.name = name;
    }


}
