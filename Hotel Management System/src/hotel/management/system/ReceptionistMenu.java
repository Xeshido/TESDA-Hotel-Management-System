
    package hotel.management.system;

    import java.sql.*;
    import java.sql.SQLException;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
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
            System.out.println("|        WELCOME RECEPTIONIST MABUHAY!         |");
            System.out.println("|                                              |");
            System.out.println(" ==============================================");
            Login screen = new Login();
            boolean exit = false;
            while (!exit) {
                System.out.println("\nCustomer Action");
                System.out.println("[1] Check In");
                System.out.println("[2] Check Out");
                System.out.println("[3] Modify Records");
                System.out.println("[4] Generate Customer Bill");
                System.out.println("[5] Customer Payment");
                System.out.println("[6] Log Out");
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
                        generateBilling();
                        break;
                    case 5:
                        makePayment();
                        break;
                    case 6:
                        System.out.println("Returning to Login Screen...");
                        screen.loginID();
                        break;
                    case 7:
                        exit = true;
                        System.out.println("System Exiting....");
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
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
            System.out.println("|        CUSTOMER RECORDS        |");
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
                        displayCustomer();
                        displayRoomUsingInnerJoin();
                        displayServicesUsingInnerJoin();
                        displayRegistrationUsingInnerJoin();
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
        System.out.println("|  UPDATE CUSTOMER INFORMATION  |");
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

        public void displayCustomer() {
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

        public void displayServicesUsingInnerJoin() {
            String query = "SELECT tbl_services.ServiceID, tbl_services.ServiceType FROM tbl_serviceprice "
                    + "INNER JOIN tbl_services ON tbl_serviceprice.ServiceType = tbl_services.ServiceType";

            try {
                connect();
                state = con.createStatement();
                result = state.executeQuery(query);
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

        public void generateBilling() 
        {
            String roomQuery = "SELECT tbl_registration.RegistrationID, tbl_registration.RoomID, tbl_registration.PeriodOfStay, tbl_roomprice.RoomPrice "
                    + "FROM tbl_registration "
                    + "INNER JOIN tbl_rooms ON tbl_registration.RoomID = tbl_rooms.RoomID "
                    + "INNER JOIN tbl_roomprice ON tbl_rooms.RoomType = tbl_roomprice.RoomType";

            String serviceQuery = "SELECT tbl_registration.RegistrationID, tbl_serviceprice.ServicePrice "
                + "FROM tbl_registration "
                + "LEFT JOIN tbl_customer ON tbl_registration.CustomerID = tbl_customer.CustomerID "
                + "LEFT JOIN tbl_services ON tbl_customer.CustomerID = tbl_registration.CustomerID " 
                + "LEFT JOIN tbl_serviceprice ON tbl_services.ServiceType = tbl_serviceprice.ServiceType";

            String checkBillingExistsQuery = "SELECT COUNT(*) AS count FROM tbl_billings WHERE RegistrationID = ? AND BillType = ?";
            String checkPaidQuery = "SELECT RegistrationID FROM tbl_billings WHERE RegistrationID = ? AND PaymentStatus = 'Paid'";

            PreparedStatement insertStatement = null;
            PreparedStatement checkBillingExistsStatement = null;

            try {
                connect();

                // Generate billings for room charges
                state = con.createStatement();
                result = state.executeQuery(roomQuery);
                insertStatement = con.prepareStatement("INSERT INTO tbl_billings (RegistrationID, BillType, BillDate, BillAmount, PaymentStatus) VALUES (?, ?, NOW(), ?, 'Unpaid')");
                checkBillingExistsStatement = con.prepareStatement(checkBillingExistsQuery);

                while (result.next()) {
                    int registrationID = result.getInt("RegistrationID");

                    // Check if billing already paid
                    PreparedStatement checkPaidStatement = con.prepareStatement(checkPaidQuery);
                    checkPaidStatement.setInt(1, registrationID);
                    ResultSet paidResult = checkPaidStatement.executeQuery();
                    if (!paidResult.next()) { // If not paid
                        int periodOfStay = result.getInt("PeriodOfStay");
                        double roomPrice = result.getDouble("RoomPrice");

                        // Calculate bill amount for room charges
                        double roomBillAmount = periodOfStay * roomPrice;

                        // Check if billing entry already exists for room charges
                        checkBillingExistsStatement.setInt(1, registrationID);
                        checkBillingExistsStatement.setString(2, "Room");
                        ResultSet billingExistsResult = checkBillingExistsStatement.executeQuery();
                        billingExistsResult.next();
                        int roomBillingCount = billingExistsResult.getInt("count");

                        if (roomBillingCount == 0) {
                            // Insert billing information for room charges
                            insertStatement.setInt(1, registrationID);
                            insertStatement.setString(2, "Room Tier"); // BillType
                            insertStatement.setDouble(3, roomBillAmount);
                            insertStatement.executeUpdate();
                        }
                    }
                    checkPaidStatement.close();
                }

                // Generate billings for service charges
                result = state.executeQuery(serviceQuery);
                while (result.next()) {
                    int registrationID = result.getInt("RegistrationID");

                    // Check if billing already paid
                    PreparedStatement checkPaidStatement = con.prepareStatement(checkPaidQuery);
                    checkPaidStatement.setInt(1, registrationID);
                    ResultSet paidResult = checkPaidStatement.executeQuery();
                    if (!paidResult.next()) { // If not paid
                        double servicePrice = result.getDouble("ServicePrice");

                        if (servicePrice > 0) {
                            // Check if billing entry already exists for service charges
                            checkBillingExistsStatement.setInt(1, registrationID);
                            checkBillingExistsStatement.setString(2, "Added Service");
                            ResultSet billingExistsResult = checkBillingExistsStatement.executeQuery();
                            billingExistsResult.next();
                            int serviceBillingCount = billingExistsResult.getInt("count");

                            if (serviceBillingCount == 0) {
                                // Insert billing information for service charges
                                insertStatement.setInt(1, registrationID);
                                insertStatement.setString(2, "Service"); // BillType
                                insertStatement.setDouble(3, servicePrice);
                                insertStatement.executeUpdate();
                            }
                        }
                    }
                    checkPaidStatement.close();
                }

                System.out.println("Billing generated successfully.");

            } catch (SQLException e) {
                System.out.println("Error generating billing: " + e.getMessage());
            } finally {
                try {
                    if (insertStatement != null) {
                        insertStatement.close();
                    }
                    if (checkBillingExistsStatement != null) {
                        checkBillingExistsStatement.close();
                    }
                } catch (SQLException e) {
                    System.out.println("Error closing statement: " + e.getMessage());
                }
            }
            displayBillings();
        }

        public void displayBillings() {
            PreparedStatement statement = null;
            ResultSet result = null;

            System.out.println("============================================");
            System.out.println("|                                 	       |");
            System.out.println("|                HOTEL BILLS               |");
            System.out.println("|                                 	       |");
            System.out.println("============================================");

            try {
                connect(); 
                statement = con.prepareStatement("SELECT c.CustomerID, c.CustomerName, r.RegistrationID, r.CheckInDate, r.CheckOutDate, ro.RoomType, b.BillID, b.BillType, b.BillAmount, b.PaymentStatus, b.PaymentMethod, b.PaymentDate " +
                                                 "FROM tbl_customer c " +
                                                 "JOIN tbl_registration r ON c.CustomerID = r.CustomerID " +
                                                 "JOIN tbl_rooms ro ON r.RoomID = ro.RoomID " +
                                                 "LEFT JOIN tbl_billings b ON r.RegistrationID = b.RegistrationID");
                result = statement.executeQuery();

                int currentCustomerID = -1;
                double totalBillingAmount = 0;

                while (result.next()) {
                    int customerID = result.getInt("CustomerID");
                    if (customerID != currentCustomerID) {
                        // New customer, print customer details
                        if (currentCustomerID != -1) {
                            // Print total billing amount for the previous customer
                            System.out.println("\nTotal Billing Amount for Customer: " + totalBillingAmount);
                            totalBillingAmount = 0; // Reset totalBillingAmount for the new customer
                        }
                        currentCustomerID = customerID;
                        String customerName = result.getString("CustomerName");

                        System.out.println("\nGuest Name: " + customerName);
                        System.out.println("Customer ID: " + customerID);
                        System.out.println(" =================================== ");
                    }

                    // Print registration details
                    int registrationID = result.getInt("RegistrationID");
                    Date checkInDate = result.getDate("CheckInDate");
                    Date checkOutDate = result.getDate("CheckOutDate");
                    System.out.println("Registration ID: " + registrationID);
                    System.out.println("Check-in Date: " + checkInDate);
                    System.out.println("Check-out Date: " + checkOutDate);

                    // Print room details
                    String roomType = result.getString("RoomType");
                    System.out.println("Room Type: " + roomType);

                    // Print billing details
                    int billID = result.getInt("BillID");
                    String billType = result.getString("BillType");
                    double billAmount = result.getDouble("BillAmount");
                    String paymentStatus = result.getString("PaymentStatus");
                    String paymentMethod = result.getString("PaymentMethod");
                    Date paymentDate = result.getDate("PaymentDate");
                    totalBillingAmount += billAmount; // Accumulate total billing amount
                    System.out.println("\nBilling ID: " + billID);
                    System.out.println("Billing Type: " + billType);
                    System.out.println("Total Billing Amount: " + billAmount);
                    System.out.println("Payment Status: " + paymentStatus);
                    System.out.println("Payment Method (Cash/Cashless): " + paymentMethod);
                    System.out.println("Payment Date: " + paymentDate);
                }
                // Print total billing amount for the last customer
                System.out.println("\nTotal Billing Amount for Customer: " + totalBillingAmount);
            } catch (SQLException e) {
                System.out.println("Error displaying billings: " + e.getMessage());
            } finally {
                try {
                    if (result != null) {
                        result.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    System.out.println("Error closing resources: " + e.getMessage());
                }
            }
        }

        public void makePayment() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Customer ID:");
            int customerID = scanner.nextInt();

            try {
                connect();

                // Update PaymentStatus, PaymentMethod, and PaymentDate for all billings of the customer
                String updateQuery = "UPDATE tbl_billings SET PaymentStatus = 'Paid', PaymentMethod = ?, PaymentDate = NOW() WHERE RegistrationID IN (SELECT RegistrationID FROM tbl_registration WHERE CustomerID = ?)";
                PreparedStatement updateStatement = con.prepareStatement(updateQuery);

                // Prompt user to enter payment method
                System.out.println("Enter Payment Method:");
                scanner.nextLine(); // Consume newline
                String paymentMethod = scanner.nextLine();

                // Set parameters and execute update
                updateStatement.setString(1, paymentMethod);
                updateStatement.setInt(2, customerID);
                int rowsAffected = updateStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Payment successfully processed for customer with ID: " + customerID);
                } else {
                    System.out.println("No billings found for customer with ID: " + customerID);
                }

                updateStatement.close();
            } catch (SQLException e) {
                System.out.println("Error making payment: " + e.getMessage());
            }
        }

        public void displayRegistrationUsingInnerJoin() {
            String query = "SELECT * FROM tbl_customer "
                    + "INNER JOIN tbl_registration ON tbl_customer.CustomerID = tbl_registration.CustomerID "
                    + "INNER JOIN tbl_rooms ON tbl_registration.RoomID = tbl_rooms.RoomID";

            try {
                connect();
                state = con.createStatement();
                result = state.executeQuery(query);

                System.out.println("");
                System.out.println("Registration ID\t\tCustomer ID\t\tRoom ID\t\tCheck-in Date\t\tPeriod of Stay\t\tCheck-out Date");
                System.out.println("----------------------------------------------------------------------------------------------------------------------");
                while (result.next()) {
                    int registrationId = result.getInt("RegistrationID");
                    int id = result.getInt("CustomerID");
                    int roomId = result.getInt("RoomID");
                    Date checkInDate = result.getDate("CheckInDate");
                    int periodOfStay = result.getInt("PeriodOfStay");
                    Date checkOutDate = result.getDate("CheckOutDate");
                    System.out.println(registrationId + "\t\t\t" + id + "\t\t\t" + roomId + "\t\t" + checkInDate + "\t\t" + periodOfStay + "\t\t\t" + checkOutDate);
                    System.out.println("");
                }
            } catch (SQLException e) {
                System.out.println("Error retrieving registration data: " + e.getMessage());
                System.out.println("");
            }
        }
    }


