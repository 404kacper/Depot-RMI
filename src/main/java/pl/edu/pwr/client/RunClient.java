package pl.edu.pwr.client;

import java.rmi.RemoteException;
import java.util.Scanner;

public class RunClient {

    public static void main(String[] args) {
        RMIClient client = null;
        try {
            client = new RMIClient("localhost");
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }

        // TODO to co mi przyszło do głowy dla klienta:
        //  - hasło po wprowadzeniu inputu odrazu powinno byc hashowane i przechowywane jako tablica bytow

        boolean keepLooping = true;
        String username;
        String password;

        System.out.println("--- User menu ---");
        while (keepLooping) {
            int choice;
            System.out.println("Wybierz operacje: ");
            System.out.println("1 - zaloguj uzytkownika");
            System.out.println("2 - wyloguj uzytkownika");
            System.out.println("3 - uzyj bezpiecznej metody");

            Scanner sc1 = new Scanner(System.in);
            choice = Integer.parseInt(sc1.nextLine());

            switch (choice) {
                case (1):
                    System.out.println("Wprowadz nazwe uzytkownika");
                    username = sc1.nextLine();
                    System.out.println("Wprowadz haslo");
                    password = sc1.nextLine();
                    client.login(username, password.toCharArray());
                    break;
                case(2):
                    client.logout();
                    break;
                case(3):
                    client.accessSecuredMethod();
                    break;
            }
        }
    }
}
