package main;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class AES {
    private static SecretKeySpec secretKey;

    public static void setKey(String inputKey) {
        byte[] key;
        MessageDigest messageDigest;
        try {
            key = inputKey.getBytes(StandardCharsets.UTF_8);
            messageDigest = MessageDigest.getInstance("SHA-1");
            key = messageDigest.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public static String encrypt(String string, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(string.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return null;
    }

    public static String decrypt(String string, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(string)));
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return null;
    }

//    public static void main(String... args) {
//        final String secretKey = "x7z99kvb6lU";
//
//        //String originalString = "***8103***P***59771273***";
//        String originalString = "test";
//
//        String encryptedString = AES.encrypt(originalString, secretKey) ;
//        String decryptedString = AES.decrypt(encryptedString, secretKey) ;
//
//        System.out.println(originalString);
//        System.out.println(encryptedString);
//        System.out.println(decryptedString);
//    }
}