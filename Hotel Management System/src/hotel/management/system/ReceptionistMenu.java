/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.management.system;

import java.util.Scanner;

public class ReceptionistMenu implements Menu {
    private Receptionist receptionist;
    private Scanner scanner = new Scanner(System.in);

    public ReceptionistMenu(Receptionist receptionist) {
        this.receptionist = receptionist;
    }

    @Override
    public void displayMenu() {
        System.out.println("==============================================");
        System.out.println("|                                            |");
        System.out.println("|   WELCOME RECEPTIONIST SOMETHING SOMETHING|");
        System.out.println("|                                            |");
        System.out.println("==============================================");

        boolean exit = false;
        while (!exit) {
            System.out.println("\nCustomer Action");
            System.out.println("[1] Check In");
            System.out.println("[2] Check Out");
            System.out.println("[3] Modify Records");
            System.out.println("[4] Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    checkIn();
                    break;
                case 2:
                    checkOut();
                    break;
                case 3:
                    modifyRecords();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    private void checkIn() {
        System.out.println("\n==============");
        System.out.println("|   CHECK IN  |");
        System.out.println("==============");

        boolean returnToMenu = false;
        while (!returnToMenu) {
            System.out.println("[1] Book a Customer");
            System.out.println("[2] Extend Booking");
            System.out.println("[3] Return to Customer Action");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    bookCustomer();
                    break;
                case 2:
                    extendBooking();
                    break;
                case 3:
                    returnToMenu = true;
                    System.out.println("Returning to Customer Action menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }

    private void bookCustomer() {
        System.out.println("\n======================");
        System.out.println("|   GUEST INFORMATION|");
        System.out.println("======================");

        // Collect guest information
        // Implementation of collecting guest information goes here

        System.out.println("Booking successful!");
    }

    private void extendBooking() {
        System.out.println("\n======================");
        System.out.println("|   EXTEND BOOKING    |");
        System.out.println("======================");

        // Implement logic for extending booking
        // Implementation goes here

        System.out.println("Booking extended successfully!");
    }

    private void checkOut() {
        System.out.println("\n===============");
        System.out.println("|  CHECK OUT  |");
        System.out.println("===============");

        // Implement logic for check-out
        // Implementation goes here

        System.out.println("Check-out successful!");
    }

    private void modifyRecords() {
        System.out.println("\n================================");
        System.out.println("|                                |");
        System.out.println("|   MODIFICATION MENU SOMETHING  |");
        System.out.println("|                                |");
        System.out.println("================================");

        boolean returnToMenu = false;
        while (!returnToMenu) {
            System.out.println("[1] Update Customer Information");
            System.out.println("[2] Update Room Information");
            System.out.println("[3] Return to Customer Action");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    updateCustomerInformation();
                    break;
                case 2:
                    updateRoomInformation();
                    break;
                case 3:
                    returnToMenu = true;
                    System.out.println("Returning to Customer Action menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }

    private void updateCustomerInformation() {
        System.out.println("\n===============================");
        System.out.println("|   UPDATE CUSTOMER INFORMATION|");
        System.out.println("===============================");

        // Implement logic for updating customer information
        // Implementation goes here

        System.out.println("Customer information updated successfully!");
    }

    private void updateRoomInformation() {
        System.out.println("\n==========================");
        System.out.println("|   UPDATE ROOM INFORMATION|");
        System.out.println("==========================");

        // Implement logic for updating room information
        // Implementation goes here

        System.out.println("Room information updated successfully!");
    }
}

