package pl.edu.pwr.model;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class User implements Serializable {
    private static int classCounter;

    private final int ID;
    private final String username;
    private final byte[] passwordHash;
    //private final Set<String> roles; // this could be used if the user had multiple roles
    private final String role;


    public User(String username, String password, String role) {
        ID = classCounter;
        this.username = username;
        this.passwordHash = hashPassword(password);
        this.role = role;
        classCounter++;
    }

    public String getUsername() {
        return username;
    }

    public boolean verifyPassword(char[] password) {
        byte[] passwordBytes = hashPassword(new String(password));
        return Arrays.equals(passwordHash, passwordBytes);
    }

    public String getRole() {
        return role;
    }

    private static byte[] hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
