package auth;

import utils.Utils;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DB {
    private static DB instance;
    private static final String dbPath;
    private static final HashMap<String, User> userCache;

    static {
        dbPath = "src/auth/database.txt";
        userCache = new HashMap<>();
    }

    private DB() { }

    // No need for a thread-safe singleton, as there are no multiple thread use cases in this program
    public static DB getInstance() {
        if (instance == null)
            instance = new DB();

        return instance;
    }

    private static class User {
        private String fullName;
        private String username;
        private String email;
        private String password;

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return Objects.equals(username, user.username) && Objects.equals(password, user.password);
        }

        @Override
        public int hashCode() {
            return Objects.hash(username, password);
        }
    }

    private static class UserService {
        public static void saveUser(User user) throws IOException {
            StringJoiner sj = new StringJoiner("|");

            sj.add(user.fullName).add(user.username).add(user.email).add(user.password);
            Files.write(Paths.get(dbPath), (sj.toString() + '\n').getBytes(), StandardOpenOption.APPEND);

            userCache.put(user.username, user);
        }

        public static boolean checkUser(User user) throws IOException {
            if (userCache.containsValue(user))
                return true;

            List<String> data = Files.readAllLines(Paths.get(dbPath));

            for (String line : data) {
                String[] info = line.split("\\|");
                userCache.put(info[1], new User(info[0], info[1], info[2], info[3]));
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
            if (userCache.containsKey(username)) {
                System.out.println("This Username Already Exists");
                return false;
            }

            // In case if the database file is manually edited
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
        User newUser = new User(fullName, username, email, Utils.md5(password));

        try {
            UserService.saveUser(newUser);
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public boolean login(String username, String password) throws IOException {
        User user = new User(username, Utils.md5(password));

        return UserService.checkUser(user);
    }
}
