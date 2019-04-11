package com.oauth.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
        System.out.println(bcpe.encode("secret"));
    }
}
