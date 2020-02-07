package main;
import java.security.SecureRandom;


public enum  FingerAbGenerator {
    instance;

    public String fingerAbCreater(){

         final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
         final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
         final String NUMBER = "0123456789";

         final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
         SecureRandom random = new SecureRandom();


            int length = 30;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

            // 0-62 (exclusive), random returns 0-61
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);



        }

        return sb.toString();
    }
}
