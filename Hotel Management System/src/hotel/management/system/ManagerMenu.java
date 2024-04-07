
package hotel.management.system;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ManagerMenu extends DBConnection implements Menu {
    private Manager manager;
    private Scanner scanner = new Scanner(System.in);
    Login screen = new Login();

    public ManagerMenu(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void displayMenu() {
        System.out.println(" ==============================================");
        System.out.println("|                                              |");
        System.out.println("|   WELCOME MANAGER SOMETHING SOMETHING   |");
        System.out.println("|                                              |");
        System.out.println(" ==============================================");
        Login screen = new Login();
        boolean exit = false;
        while (!exit) {
            System.out.println("\nCustomer Action");
            System.out.println("[1] Check In");
            System.out.println("[2] Check Out");
            System.out.println("[3] Modify Records");
            System.out.println("[4] Add Rooms");
            System.out.println("[5] Remove Rooms");
            System.out.println("[6] Return to Login");
            System.out.println("[7] Exit");
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
                    addRooms();
                    break;
                case 5:
                    removeRooms();
                    break;
                case 6:
                    System.out.println("Returning to Login Screen...");
                    screen.loginID();
                    break;
                case 7:
                    exit = true;
                    System.out.println("System Exiting....");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
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
            System.out.println("[2] Extend Booking");
            System.out.println("[3] Return to Customer Action");
            System.out.print(">> ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    bookCustomer();
                    break;
                case 2:
                    //extendBooking();
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

    
    public void displayRoomUsingInnerJoin() {
        String query = "SELECT * FROM tbl_rooms";

        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(query);
            System.out.println("");
            System.out.println("");
            System.out.println("Room ID\t\tRoom Type\t\tRoom Status\t\t");
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            while (result.next()) {
                int roomId = result.getInt("RoomID");
                String roomType = result.getString("RoomType");
                String roomStatus = result.getString("RoomStatus");
                

                int roomTypeSpaces = 20 - roomType.length();
                int roomStatusSpaces = 25 - roomStatus.length();

                StringBuilder roomTypeSpacesBuilder = new StringBuilder();
                for (int i = 0; i < roomTypeSpaces; i++) {
                    roomTypeSpacesBuilder.append(" ");
                }
                StringBuilder roomStatusSpacesBuilder = new StringBuilder();
                for (int i = 0; i < roomStatusSpaces; i++) {
                    roomStatusSpacesBuilder.append(" ");
                }

                System.out.println(roomId + "\t\t" + roomType + roomTypeSpacesBuilder.toString()
                        + roomStatus + roomStatusSpacesBuilder.toString());
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving room data: " + e.getMessage());
        }
    }
    
    private void addRooms() {
        System.out.println(" ======================");
        System.out.println("|     ADD ROOMS        |");
        System.out.println(" ======================");

        try {
            connect();
            // Display Room Types
            System.out.println("Select Room Type:");
            System.out.println("[1] Deluxe");
            System.out.println("[2] Economy");
            System.out.println("[3] Executive");
            System.out.println("[4] Standard");
            System.out.println("[5] Suite");
            System.out.print(">> ");
            int roomTypeChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            String RoomType = "";
            String RoomStatus = "Available"; // Assuming new rooms are available
            int ServiceID = 0;
            switch (roomTypeChoice) {
                case 1:
                    RoomType = "Deluxe";
                    break;
                case 2:
                    RoomType = "Economy";
                    break;
                case 3:
                    RoomType = "Executive";
                    break;
                case 4:
                    RoomType = "Standard";
                    break;
                case 5:
                    RoomType = "Suite";
                    break;
                default:
                    System.out.println("Invalid choice.");
                    return;
            }
            
            
        // Prepare the INSERT statement
        String query = "INSERT INTO `tbl_rooms` (`RoomType`, `RoomStatus`) VALUES (?, ?)";
        prep = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS); // Specify RETURN_GENERATED_KEYS
        prep.setString(1, RoomType);
        prep.setString(2, RoomStatus);

        // Execute the query
        prep.executeUpdate();

        ResultSet generatedKeys = prep.getGeneratedKeys();
        if (generatedKeys.next()) {
            int RoomID = generatedKeys.getInt(1);
            System.out.println("");
            System.out.println("Room " + RoomType + " with RoomID " + RoomID + " added successfully.");
        }

            con.close();
            
        displayRoomUsingInnerJoin();
        } catch (SQLException e) {
            System.err.println("Error adding room: " + e.getMessage());
        }
        addServiceAndAssociateToRoom();
    }

    public void displayServicesUsingInnerJoin() {
        String query = "SELECT tbl_services.ServiceID, tbl_services.ServiceType FROM tbl_serviceprice "
                + "INNER JOIN tbl_services ON tbl_serviceprice.ServiceType = tbl_services.ServiceType";

        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(query);
            System.out.println("");
            System.out.println("");
            System.out.println("Service ID\tService Type");
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            while (result.next()) {
                int ServiceId = result.getInt("ServiceID");
                String serviceType = result.getString("ServiceType");

                System.out.println(ServiceId + "\t\t" + serviceType);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving service data: " + e.getMessage());
        }
        
    }
    
    private void addServiceAndAssociateToRoom() {
        System.out.println(" ======================");
        System.out.println("|     ADD SERVICE       |");
        System.out.println(" ======================");
        // Display available services and let the user choose one
        System.out.println("Select Service Type:");
        System.out.println("[1] Dining");
        System.out.println("[2] HouseKeeping");
        System.out.println("[3] Laundry");
        System.out.println("[4] Massage & Spa");
        System.out.print(">> ");
        int serviceChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        String ServiceType = "";
        switch (serviceChoice) {
            case 1:
                ServiceType = "Dining";
                break;
            case 2:
                ServiceType = "HouseKeeping";
                break;
            case 3:
                ServiceType = "Laundry";
                break;
            case 4:
                ServiceType = "Massage & Spa";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        try {
            connect();

            // Prepare the INSERT statement for services
            String insertServiceQuery = "INSERT INTO tbl_services (ServiceType) VALUES (?)";
            prep = con.prepareStatement(insertServiceQuery, new String[]{"ServiceID"}); // Specify the column name for generated keys
            prep.setString(1, ServiceType);
            prep.setString(1, ServiceType);
            int rowsAffected = prep.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = prep.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int ServiceID = generatedKeys.getInt(1);
                    System.out.println("Service " + ServiceType + " (ID: " + ServiceID + ") added successfully.");

                    // Associate the service with a room
                    System.out.print("Enter RoomID to associate with the service: ");
                    int RoomID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    String updateRoomQuery = "UPDATE `tbl_rooms` SET `ServiceID` = ? WHERE `RoomID` = ?";
                    prep = con.prepareStatement(updateRoomQuery);
                    prep.setInt(1, ServiceID);
                    prep.setInt(2, RoomID);

                    int rowsUpdated = prep.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("Service associated with the room successfully.");
                    } else {
                        System.out.println("Failed to associate service with the room.");
                    }
                } else {
                    System.out.println("Failed to retrieve ServiceID for " + ServiceType);
                }
            }

            con.close();
            displayServicesUsingInnerJoin(); // Display updated services
        } catch (SQLException e) {
            System.err.println("Error adding service and associating with the room: " + e.getMessage());
        }
    }

    private void removeRooms() {
    System.out.print("Enter RoomID to check out: ");
        int RoomID = scanner.nextInt();
        scanner.nextLine(); //Consume newline character
        
        String query = "DELETE FROM tbl_rooms WHERE RoomID = ?";
        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setInt(1, RoomID);
            
            int rowsDeleted = prep.executeUpdate();
            if (rowsDeleted > 0){
                System.out.println("Remove room successful for RoomID: " + RoomID);
            } else {
                System.out.println("RoomID: " + RoomID + " not found.");
            }
            
            con.close();
        } catch (SQLException e){
            e.printStackTrace(); //prints stack trace for better diagnosis of error
        }
    }
}
    
