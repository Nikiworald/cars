package com.haemimont.cars.core.tools;

import java.time.LocalDateTime;

public class Token {
    public static boolean checkIfTokenIsValid(String token) {
        boolean check = false;
        if (token != null) {
            String tokenValues[] = token.split("--");
            try {
                int tokenTime = Integer.parseInt(tokenValues[2]);
                int currentTimeInHours = LocalDateTime.now().getHour();
                int currentTimeInMinutes = LocalDateTime.now().getMinute();
                int timeSum = currentTimeInHours + currentTimeInMinutes;
                if (tokenTime > timeSum) {
                    check = true;
                }
            } catch (ArrayIndexOutOfBoundsException ar) {
                check = false;
            }
        }
        return check;
    }
}
