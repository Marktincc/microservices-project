package com.example.commonutils.security.encryptPassword;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    //Encode a password
    public static String encrypt(String password) {
        return encoder.encode(password);
    }

    //Check if a password matches the encoded password
    public static boolean matches(String password, String encodedPassword) {
        return encoder.matches(password, encodedPassword);
    }
}
