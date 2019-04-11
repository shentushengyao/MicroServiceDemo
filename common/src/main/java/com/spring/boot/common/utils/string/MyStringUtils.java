package com.spring.boot.common.utils.string;


import org.apache.commons.lang3.StringUtils;

public class MyStringUtils {
    public static String ifBlank(String str, String result) {
        return StringUtils.isNotBlank(str) ? str : result;
    }
}
