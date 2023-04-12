package com.haemimont.cars.core.tools;


import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class PasswordManager {
    public static String generateEncryptedPassword(String password) {
        return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }

}
