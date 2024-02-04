package com.ssafy.bestalgo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordEncoder {
    private static final Logger logger = LoggerFactory.getLogger(PasswordEncoder.class);
    private static MessageDigest messageDigest;

    static {
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            logger.error("Cannot initialize MessageDigest");
            System.exit(-1);
        }
    }

    private PasswordEncoder() {
    }

    public static String encode(String text) {
        messageDigest.update(text.getBytes());
        return bytesToHex(messageDigest.digest());
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexStringBuilder = new StringBuilder(2 * bytes.length);

        for (byte b : bytes) {
            hexStringBuilder.append(byteToHex(b));
        }

        return hexStringBuilder.toString();
    }

    private static String byteToHex(byte b) {
        char[] hexChars = new char[2];
        hexChars[0] = Character.forDigit((b >> 4) & 0xF, 16);
        hexChars[1] = Character.forDigit(b & 0xF, 16);
        return new String(hexChars);
    }
}
