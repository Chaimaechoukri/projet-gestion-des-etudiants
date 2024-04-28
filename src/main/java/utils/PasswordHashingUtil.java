package utils;


public class PasswordHashingUtil {

    public static String hashPassword(String password) {
        return password;
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        return password.equals(hashedPassword);
    }
}
