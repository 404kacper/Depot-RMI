package pl.edu.pwr.server.secured;

import pl.edu.pwr.model.User;
import pl.edu.pwr.shared.IAuth;
import pl.edu.pwr.shared.ISecuredService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SecuredServiceImpl extends UnicastRemoteObject implements ISecuredService {
    private final IAuth auth;

    public SecuredServiceImpl(IAuth auth) throws RemoteException {
        this.auth = auth;
    }

    @Override
    public String securedMethod() throws RemoteException {
        User authenticatedUser = auth.getAuthenticatedUser();
        if (authenticatedUser == null) {
            return "You must be logged in to access this method";
        }
        // Logic for the secured method goes here
        return "Access granted for user : " + authenticatedUser.getUsername();
    }
}
