
package hotel.management.system;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Function f = new Function();
        f.displayHotel();
        f.displayRoom();
        f.displayRegistrationUsingInnerJoin();
        f.displayServicePrice();
        f.displayServicesUIsingInnerJoin();
        f.displayBillingsUsingInnerJoin();
        f.displayRoomPrice();
        
        Scanner scanner = new Scanner(System.in);
        
        // Welcome screen
        System.out.println("==================================");
        System.out.println("|                                  |");
        System.out.println("|   WELCOME TO THE LOGIN SYSTEM    |");
        System.out.println("|                                  |");
        System.out.println("==================================");

        // User input for StaffID and Password
        System.out.println("Enter StaffID:");
        String staffId = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        // Simulated login
        AuthenticationService authService = new AuthenticationService();
        User loggedInUser = authService.login(staffId, password);

        if (loggedInUser != null) {
            System.out.println("Login successful!");
            // Determine user role and show respective menu
            if (loggedInUser instanceof Receptionist) {
                ReceptionistMenu receptionistMenu = new ReceptionistMenu((Receptionist) loggedInUser);
                receptionistMenu.displayMenu();
            } else if (loggedInUser instanceof Manager) {
                ManagerMenu managerMenu = new ManagerMenu((Manager) loggedInUser);
                managerMenu.displayMenu();
            }
        } else {
            System.out.println("Invalid ID or Password. Please try again.");
        }
    
    scanner.close();
    }

    void connect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

