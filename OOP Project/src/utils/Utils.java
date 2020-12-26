package utils;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Utils {
    public static boolean checkInput(int input, int maxInput) {
        return input > 0 && input <= maxInput;
    }

    public static String md5(String input) {
        String md5 = null;
        if (null == input) return null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes(), 0, input.length());
            md5 = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }

    public static ArrayList<String> readLines(String path) throws IOException {
        return (ArrayList<String>) Files.readAllLines(Paths.get(path));
    }
}
