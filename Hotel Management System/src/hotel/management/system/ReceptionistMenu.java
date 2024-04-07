/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.management.system;


import java.sql.SQLException;
import java.util.Scanner;

public class ReceptionistMenu extends DBConnection implements Menu {
    private Receptionist receptionist;
    private Scanner scanner = new Scanner(System.in);
    Login screen = new Login();
    public ReceptionistMenu(Receptionist receptionist) {
        this.receptionist = receptionist;
    }

    @Override
    public void displayMenu() {
        System.out.println(" ==============================================");
        System.out.println("|                                              |");
        System.out.println("|   WELCOME RECEPTIONIST SOMETHING SOMETHING   |");
        System.out.println("|                                              |");
        System.out.println(" ==============================================");
        Login screen = new Login();
        boolean exit = false;
        while (!exit) {
            System.out.println("\nCustomer Action");
            System.out.println("[1] Check In");
            System.out.println("[2] Check Out");
            System.out.println("[3] Modify Records");
            System.out.println("[4] Exit");
            System.out.print(">> ");
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
                    System.out.println("Returning to Login Screen...");
                    screen.loginID();
                    break;
                case 5:
                    exit = true;
                    System.out.println("System Exiting....");
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private void checkIn() {
        System.out.println(" ==============");
        System.out.println("|   CHECK IN   |");
        System.out.println(" ==============");

        boolean returnToMenu = false;
        while (!returnToMenu) {
            System.out.println("[1] Book a Customer");
            System.out.println("[2] Return to Customer Action");
            System.out.print(">> ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    bookCustomer();
                    break;
                case 2:
                    returnToMenu = true;
                    System.out.println("Returning to Customer Action menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }

    private void bookCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" ======================");
        System.out.println("|   GUEST INFORMATION  |");
        System.out.println(" ======================");
        String query = "INSERT INTO tbl_customer (CustomerName, CellNum, Email, Age) "
                + "VALUES (?,?,?,?)";
        // Get guest information
        try {
            connect();
            prep = con.prepareStatement(query);
           
            
            System.out.print("Guest Name: ");
            String CustomerName = scanner.nextLine();
            prep.setString(1, CustomerName);
            
            System.out.print("Contact No.: ");
            String CellNum = scanner.nextLine();
            prep.setString(2, CellNum);
            
            System.out.print("Email Address: ");
            String Email = scanner.nextLine();
            prep.setString(3, Email);
            
            System.out.print("Age: ");
            int Age = scanner.nextInt();
            prep.setInt(4, Age);
            
            int rowsInserted = prep.executeUpdate();
            if (rowsInserted > 0){
                System.out.println("\nBooking completed for " + CustomerName);
            } else {
                System.out.println("\nFailed to book " + CustomerName);
            }
            con.close();
            System.out.println("");
            System.out.println("Customer " + CustomerName + " added successfully.\n");
        } catch (SQLException e){
            System.out.println(e);
        }
        
        
    }


    
    private void checkOut() {
        System.out.println(" ===============");
        System.out.println("|   CHECK OUT   |");
        System.out.println(" ===============");

        boolean returnToMenu = false;
        while (!returnToMenu) {
            System.out.println("[1] Release a Customer");
            System.out.println("[2] Return to Customer Action");
            System.out.print(">> ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    removeCustomer();
                    break;
                case 2:
                    returnToMenu = true;
                    System.out.println("Returning to Customer Action menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 2.");
            }
        }
           
    }
    
    private void removeCustomer(){
        System.out.print("Enter CustomerID to check out: ");
        int CustomerID = scanner.nextInt();
        scanner.nextLine(); //Consume newline character
        
        String query = "DELETE FROM tbl_customer WHERE CustomerID = ?";
        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setInt(1, CustomerID);
            
            int rowsDeleted = prep.executeUpdate();
            if (rowsDeleted > 0){
                System.out.println("Check-out successful for CustomerID: " + CustomerID);
            } else {
                System.out.println("CustomerID: " + CustomerID + " not found.");
            }
            
            con.close();
        } catch (SQLException e){
            e.printStackTrace(); //prints stack trace for better diagnosis of error
        }
    }

    private void modifyRecords() {
        System.out.println(" ================================");
        System.out.println("|                                |");
        System.out.println("|   MODIFICATION MENU SOMETHING  |");
        System.out.println("|                                |");
        System.out.println(" ================================");
        
        
        
        boolean returnToMenu = false;
        while (!returnToMenu) {
            System.out.println("[1] Update Customer Information");
            System.out.println("[2] Show Records");
            System.out.println("[3] Return to Customer Action");
            System.out.print(">> ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    updateCustomerInformation();
                    break;
                case 2:
                    displayHotel();
                    break;
                case 3:
                    returnToMenu = true;
                    System.out.println("Returning to Customer Action menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 2.");
            }
        }
    }

    private void updateCustomerInformation() {
    System.out.println(" ===============================");
    System.out.println("|   UPDATE CUSTOMER INFORMATION |");
    System.out.println(" ===============================");

    try {
        connect();

        // Prompt user for CustomerID to update
        System.out.print("Enter CustomerID to update: ");
        int CustomerID = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Check if customer exists
        String checkQuery = "SELECT * FROM tbl_customer WHERE CustomerID = ?";
        prep = con.prepareStatement(checkQuery);
        prep.setInt(1, CustomerID);
        boolean customerExists = prep.executeQuery().next();

        if (!customerExists) {
            System.out.println("CustomerID " + CustomerID + " not found.");
            return;
        }
        

        // Prompt user for updated information
        System.out.print("Enter new Guest Name: ");
        String CustomerName = scanner.nextLine();
        System.out.print("Enter new Contact No.: ");
        String CellNum = scanner.nextLine();
        System.out.print("Enter new Email Address: ");
        String Email = scanner.nextLine();
        System.out.print("Enter new Age: ");
        int Age = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Prepare UPDATE statement
        String sql = "UPDATE tbl_customer SET CustomerName = ?, CellNum = ?, Email = ?, Age = ? WHERE CustomerID = ?";
        prep = con.prepareStatement(sql);
        prep.setString(1, CustomerName);
        prep.setString(2, CellNum);
        prep.setString(3, Email);
        prep.setInt(4, Age);
        prep.setInt(5, CustomerID);

        // Execute the UPDATE statement
        int rowsUpdated = prep.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Customer information updated successfully for CustomerID: " + CustomerID);
        } else {
            System.out.println("Failed to update customer information for CustomerID: " + CustomerID);
        }

        // Close resources
        prep.close();
        con.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public void displayHotel() {
        String query = "SELECT * FROM tbl_customer";

        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(query);
            System.out.println(" ===============");
            System.out.println("| CUSTOMER INFO | ======>>");
            System.out.println(" ===============");
            System.out.println("ID\tName\t\t\tNumber\t\t\tEmail\t\t\t\tAge");
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            while (result.next()) {
                int id = result.getInt("CustomerID");
                String CustomerName = result.getString("customerName");
                String CellNum = result.getString("CellNum");
                String Email = result.getString("Email");
                int Age = result.getInt("Age");
                System.out.printf("%-7d %-23s %-23s %-31s %d\n", id, CustomerName, CellNum, Email, Age);
                
            }
        System.out.println();
        } catch (SQLException e) {
            System.out.println("Error retrieving hotel data: " + e.getMessage());
        }
    }
}
    

