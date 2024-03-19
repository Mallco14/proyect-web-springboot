package com.jhon.startup;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecuredPassword {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password";

        String tipadoPassword = encoder.encode(rawPassword);

        System.out.println(tipadoPassword);
    }

}
