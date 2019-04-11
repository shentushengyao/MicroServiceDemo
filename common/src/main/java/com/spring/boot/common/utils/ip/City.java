package com.spring.boot.common.utils.ip;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Exrickx
 */
@Data
public class City implements Serializable {

    String country;

    String province;

    String city;
}
