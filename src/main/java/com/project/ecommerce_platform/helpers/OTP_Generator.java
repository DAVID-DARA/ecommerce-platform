package com.project.ecommerce_platform.helpers;

import java.util.Random;

public class OTP_Generator {
    public static int generateToken() {
        Random random = new Random();
        return 100000 + random.nextInt(900000); // Generate a random number between 100000 and 999999
    }
}
