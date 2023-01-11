package pl.edu.pwr.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISecuredService extends Remote {
    public String securedMethod() throws RemoteException;
}
