package pl.edu.pwr.server;

import pl.edu.pwr.server.authentication.AuthImpl;
import pl.edu.pwr.server.secured.SecuredServiceImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RunServer {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);

            // TODO to co mi przyszło do głowy dla serwera:
            //  - od klienta powinien przychodzic hash hasła a nie string hasła
            //  - mozna dodac powiadomienie na serwerze za kazdym razem jak uzytkownik sie polaczy i zaloguje(wtedy który - wyswietlac id)
            //  Ogólne:
            //  - dodać taski do gradla zeby odpalało serwer i klienta z tej zakładki na prawo
            //  - największy problem gradla to póki co znalezienie repozytorium java.rmi w maven central

            // Create auth remote object
            AuthImpl auth = new AuthImpl();
            SecuredServiceImpl service = new SecuredServiceImpl(auth);

            // Register remote objects
            registry.rebind("AuthService", auth);

            registry.rebind("SecuredService", service);

            System.out.println("--- Server started ---");

        } catch (RemoteException e) {
            System.err.println("Server exception: " + e);
            e.printStackTrace();
        }
    }
}
