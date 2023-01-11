package pl.edu.pwr.shared;

import pl.edu.pwr.model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAuth extends Remote {
    public boolean login(String username, char[] password) throws RemoteException;
    public void logout() throws RemoteException;
    User getAuthenticatedUser() throws RemoteException;
}
