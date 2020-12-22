package auth;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DB {
    private static DB instance;
    private static final String dbPath;

    static {
        dbPath = "src/auth/database.txt";
    }

    private DB() {
    }

    public static DB getInstance() {
        if (instance == null)
            instance = new DB();

        return instance;
    }

    private static class User {
        String fullName;
        String username;
        String email;
        String password;

        public User(String fullName, String username, String email, String password) {
            this.fullName = fullName;
            this.username = username;
            this.email = email;
            this.password = password;
        }

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

    private static class UserService {
        public static void saveUser(User user) throws IOException {
            StringJoiner sj = new StringJoiner("|");

            sj.add(user.fullName).add(user.username).add(user.email).add(user.password);
            Files.write(Paths.get(dbPath), (sj.toString() + '\n').getBytes(), StandardOpenOption.APPEND);
        }

        public static boolean checkUser(User user) throws IOException {
            List<String> data = Files.readAllLines(Paths.get(dbPath));

            for (String line : data) {
                String[] info = line.split("\\|");
                if (user.username.equals(info[1]) && user.password.equals(info[3]))
                    return true;
            }

            return false;
        }
    }

    public boolean validateName(String fullName) {
        Pattern pattern = Pattern.compile("^[A-Z][a-z]+ [A-Z][a-z]+$");
        Matcher matcher = pattern.matcher(fullName);

        return matcher.matches();
    }

    public boolean validateUsername(String username) throws IOException {
        Pattern pattern = Pattern.compile("^.{10,}$");
        Matcher matcher = pattern.matcher(username);

        if (matcher.matches()) {
            List<String> data = Files.readAllLines(Paths.get(dbPath));

            for (String line : data) {
                if (username.equals(line.split("\\|")[1])) {
                    System.out.println("This Username Already Exists");
                    return false;
                }
            }
            return true;
        }

        System.out.println("You Entered Username Incorrectly!");
        return false;
    }

    public boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=(.*[A-Z]){2})(?=(.*\\d){3})[a-zA-Z\\d]{8,}$");
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

    public boolean register(String fullName, String username, String email, String password) {
        User newUser = new User(fullName, username, email, md5(password));

        try {
            UserService.saveUser(newUser);
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public boolean login(String username, String password) throws IOException {
        User user = new User(username, md5(password));

        return UserService.checkUser(user);
    }

    private static String md5(String input) {
        String md5 = null;
        if(null == input) return null;
        try {
            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");
            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());
            //Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }
}
