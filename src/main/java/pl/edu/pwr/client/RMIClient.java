package pl.edu.pwr.client;

import pl.edu.pwr.model.User;
import pl.edu.pwr.shared.IAuth;
import pl.edu.pwr.shared.ISecuredService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    private String host;
    private Registry registry;

    private Boolean logged;
    private IAuth auth = null;

    public RMIClient(String hostname) throws RemoteException {
        host = hostname;
        registry = LocateRegistry.getRegistry(host);
        logged = false;
    }

    public void login(String username, char[] password) {
        try {
            auth = (IAuth) registry.lookup("AuthService");

            if (auth.login(username, password)) {
                System.out.println("Successful login");
                logged = true;
                User authenticatedUser = auth.getAuthenticatedUser();
                if (authenticatedUser != null) {
                    System.out.println("user role :" + authenticatedUser.getRole());
                }
            } else {
                System.out.println("Invalid credentials");
            }
        } catch (RemoteException | NotBoundException e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }
    }

    public void logout() {
        try {
            if (auth != null && logged) {
                auth.logout();
                System.out.println("Successful logout");
                logged = false;
            } else {
                System.out.println("You are not logged in");
            }
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }

    }

    public void accessSecuredMethod() {
        try {
            ISecuredService service = (ISecuredService) registry.lookup("SecuredService");
            String result = service.securedMethod();
            System.out.println(result);
        } catch (RemoteException | NotBoundException e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }
    }


}
