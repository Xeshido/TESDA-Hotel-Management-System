package hotel.management.system;

import java.util.Scanner;

public class Login {
    public void loginID() {
        Scanner scanner = new Scanner(System.in);

        boolean loginSuccessful = false;
        while (!loginSuccessful) {
            // Welcome screen
            System.out.println(" ==================================");
            System.out.println("|                                  |");
            System.out.println("|      ReservEASE: The Suite       |");
            System.out.println("|        Solution for Hotel        |");
            System.out.println("|            Booking               |");
            System.out.println("|                                  |");
            System.out.println("|                                  |");
            System.out.println("|          PLEASE LOG IN           |");
            System.out.println("|               ||                 |");
            System.out.println("|               ||                 |");
            System.out.println("|               ++                 |");
            System.out.println(" ==================================");

            // User input for StaffID and Password
            System.out.println("Enter StaffID:");
            String staffId = scanner.nextLine();
            System.out.println("Enter Password:");
            String password = scanner.nextLine();

            // Simulated login
            AuthenticationService authService = new AuthenticationService();
            User loggedInUser = authService.login(staffId, password);

            if (loggedInUser != null) {
                System.out.println("\nLogin successful!\n");
                loginSuccessful = true;
                // Determine user role and show respective menu
                if (loggedInUser instanceof Receptionist) {
                    ReceptionistMenu receptionistMenu = new ReceptionistMenu((Receptionist) loggedInUser);
                    receptionistMenu.displayMenu();
                } else if (loggedInUser instanceof Manager) {
                    ManagerMenu managerMenu = new ManagerMenu((Manager) loggedInUser);
                    managerMenu.displayMenu();
                }
            } else {
                System.out.println("Invalid ID or Password. Please try again.\n");
            }
        }

        scanner.close();
    }
}
