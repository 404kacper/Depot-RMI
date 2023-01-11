package pl.edu.pwr.server.authentication;

import pl.edu.pwr.model.User;
import pl.edu.pwr.shared.IAuth;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class AuthImpl extends UnicastRemoteObject implements IAuth {

    private final List<User> users = new ArrayList<>();
    private User authenticatedUser = null;

    public AuthImpl() throws RemoteException {
        users.add(new User("user1", "password1", "admin"));
        users.add(new User("user2", "password2", "user"));
    }

    @Override
    public boolean login(String username, char[] password) throws RemoteException {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.verifyPassword(password)) {
                authenticatedUser = user;
                return true;
            }
        }
        return false;
    }

    @Override
    public void logout() throws RemoteException {
        authenticatedUser = null;
    }

    @Override
    public User getAuthenticatedUser() throws RemoteException {
        return authenticatedUser;
    }
}
