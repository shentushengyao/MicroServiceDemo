package com.spring.boot.jpa.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.boot.common.jpa.presist.common.base.JpaBaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: Stsy
 * @Date: 2018/11/23 11:13
 */

@Data
@Entity
@Table(name = "sys_resource")
@JsonIgnoreProperties({"roles"})
public class SysResource extends JpaBaseEntity {

    @Column(length = 64, unique = true, nullable = false)
    private String url;
    @Column(length = 64)
    private String discription;

//    @ManyToMany(mappedBy = "resources")
//    private Set<SysRole> roles ;
//
//    public void addRole(SysRole role) {
//        roles.add(role);
//        role.getResources().add(this);
//    }
//
//    public void removeRole(SysRole role) {
//        roles.remove(role);
//        role.getResources().remove(this);
//    }
    public SysResource() {
    }
    public SysResource(String url) {
        this.url = url;
    }


}
