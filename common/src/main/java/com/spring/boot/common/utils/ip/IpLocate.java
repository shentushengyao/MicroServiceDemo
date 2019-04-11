package com.spring.boot.common.utils.ip;


import lombok.Data;

import java.io.Serializable;

/**
 * @author Exrickx
 */
@Data
public class IpLocate implements Serializable {

    private String retCode;

    private City result;
}

