package hotel.management.system;

import java.sql.SQLException;
import java.sql.Date;

public class Function extends DBConnection {

    //FOR CUSTOMER TABLE
    //READ
    public void displayCustomer() {
        String query = "SELECT * FROM tbl_customer";

        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(query);
            System.out.println("ID   Name            Number         Email                      Age");
            System.out.println("-------------------------------------------------------------------");
            while (result.next()) {
                int id = result.getInt("CustomerID");
                String customerName = result.getString("CustomerName");
                String cellNum = result.getString("CellNum");
                String email = result.getString("Email");
                int age = result.getInt("Age");

                System.out.printf("%-5d%-15s%-14s%-28s%-5d%n", id, customerName, cellNum, email, age);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving hotel data: " + e.getMessage());
        }
    }

    //CREATE
    public void addCustomer(int CustomerID, String CustomerName, String CellNum, String Email, int Age) {
        String query = "INSERT INTO tbl_customer (CustomerID, CustomerName, CellNum, Email, Age) "
                + "VALUES (?,?,?,?,?)";

        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setInt(1, CustomerID);
            prep.setString(2, CustomerName);
            prep.setString(3, CellNum);
            prep.setString(4, Email);
            prep.setInt(5, Age);
            prep.executeUpdate();
            con.close();
            System.out.println("");
            System.out.println("Customer " + CustomerName + " added successfully.");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //UPDATE
    public void updateCustomer(int CustomerID, String CustomerName, String CellNum, String Email, int Age) {
        String query = "UPDATE tbl_customer SET CustomerName = ?, CellNum = ?, Email = ?, Age = ? WHERE CustomerID = ?";

        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setString(1, CustomerName);
            prep.setString(2, CellNum);
            prep.setString(3, Email);
            prep.setInt(4, Age);
            prep.setInt(5, CustomerID);
            prep.executeUpdate();
            System.out.println("");
            System.out.println("Customer " + CustomerName + " updated successfully.");
            displayCustomer();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //DELETE
    public void deleteCustomer(int CustomerID) {
        String query = "DELETE from tbl_customer WHERE CustomerID = ?";

        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setInt(1, CustomerID);
            prep.executeUpdate();
            System.out.println("");
            System.out.println("Customer " + CustomerID + " deleted successfully.");
            displayCustomer();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    
    //FOR SERVICE PRICE
    //READ
    public void displayServicePrice() {
    String query = "SELECT * FROM tbl_serviceprice";

    try {
        connect();
        state = con.createStatement();
        result = state.executeQuery(query);
        System.out.println("");
        System.out.println("");
        System.out.println("Service Type       Service Price     Service Description");
        System.out.println("---------------------------------------------------------");
        while (result.next()) {
            String serviceType = result.getString("ServiceType");
            int servicePrice = result.getInt("ServicePrice");
            String serviceDescription = result.getString("ServiceDescription");
            
            // Print formatted output with fixed-width columns
            System.out.printf("%-20s %-20s %-50s%n", serviceType, servicePrice, serviceDescription);
        }
    } catch (SQLException e) {
        System.out.println("Error retrieving service price data: " + e.getMessage());
    }
}

    //CREATE
    public void addServicePrice(String ServiceType, int ServicePrice, String ServiceDescription){
        String query = "INSERT INTO tbl_serviceprice (ServiceType, ServicePrice, ServiceDescription) "
                + "VALUES (?,?,?)";

        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setString(1, ServiceType);
            prep.setInt(2, ServicePrice);
            prep.setString(3, ServiceDescription);
            prep.executeUpdate();
            con.close();
            System.out.println("");
            System.out.println("Service " + ServiceType + " " +  ServicePrice + " updated successfully.");
            displayServicePrice();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    //UPDATE
    public void updateServicePrice(String ServiceType, int ServicePrice, String ServiceDescription){
        String query = "UPDATE tbl_serviceprice SET ServiceType = ?, ServicePrice = ?, ServiceDescription = ?";

        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setString(1, ServiceType);
            prep.setInt(2, ServicePrice);
            prep.setString(3, ServiceDescription);
            prep.executeUpdate();
            System.out.println("");
            System.out.println("Service " + ServiceType + ServicePrice + " updated successfully.");
            displayServicePrice();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    //DELETE
    public void updateServicePrice(String ServiceType){
        String query = "DELETE from tbl_servicerice WHERE ServiceType = ?";

        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setString(1, ServiceType);
            prep.executeUpdate();
            System.out.println("");
            System.out.println("Service " + ServiceType + " deleted successfully.");
            displayServicePrice();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    
    
    
    //FOR ROOMS
    //READ
    public void displayRoomUsingInnerJoin() {
    String query = "SELECT * FROM tbl_rooms";

    try {
        connect();
        state = con.createStatement();
        result = state.executeQuery(query);
        System.out.println("");
        System.out.println("");
        System.out.println("Room ID\t\tRoom Type\t\tRoom Status\t\tService ID");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        while (result.next()) {
            int roomId = result.getInt("RoomID");
            String roomType = result.getString("RoomType");
            String roomStatus = result.getString("RoomStatus");
            int serviceId = result.getInt("ServiceID");

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
                    + roomStatus + roomStatusSpacesBuilder.toString() + serviceId);
        }
    } catch (SQLException e) {
        System.out.println("Error retrieving room data: " + e.getMessage());
    }
}

    //CREATE
   public void addRoom(int RoomID, String RoomType, String RoomStatus, int ServiceID) {
    String query = "INSERT INTO `tbl_rooms` (`RoomID`, `RoomType`, `RoomStatus`, `ServiceID`) VALUES (?, ?, ?, ?);";

    try {
        connect();
        prep = con.prepareStatement(query);
        prep.setInt(1, RoomID);
        prep.setString(2, RoomType);
        prep.setString(3, RoomStatus);
        prep.setInt(4, ServiceID);
        prep.executeUpdate();
        con.close();
        System.out.println("");
        System.out.println("Room " + RoomID + " added successfully.");
        displayRoomUsingInnerJoin();
    } catch (SQLException e) {
        System.out.println(e);
    }
}

    //UPDATE
    public void updateRoom(int RoomID, String RoomType, String RoomStatus, String RoomDescription) {
        String query = "UPDATE from tbl_rooms WHERE RoomID = ?";

        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setString(1, RoomType);
            prep.setString(2, RoomStatus);
            prep.setString(3, RoomDescription);
            prep.setInt(4, RoomID);
            prep.executeUpdate();
            System.out.println("");
            System.out.println("Room " + RoomType + " updated successfully.");
            displayRoomUsingInnerJoin();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    //DELETE
    public void deleteRoom(int RoomID) {
        String query = "DELETE from tbl_rooms WHERE RoomID = ?";

        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setInt(1, RoomID);
            prep.executeUpdate();
            System.out.println("");
            System.out.println("Room " + RoomID + " deleted successfully.");
            displayRoomUsingInnerJoin();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    
    //FOR SERVICES
    //READ, 
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
            int serviceId = result.getInt("ServiceID");
            String serviceType = result.getString("ServiceType");

            System.out.println(serviceId + "\t\t" + serviceType);
        }
    } catch (SQLException e) {
        System.out.println("Error retrieving service data: " + e.getMessage());
    }
}

    //CREATE
    public void addService(int ServiceID, String ServiceType) {
    String query = "INSERT INTO tbl_services (ServiceID, ServiceType,) "
                + "VALUES (NULL, ?)";

    try {
        connect();
        prep = con.prepareStatement(query);
        prep.setInt(1, ServiceID);
        prep.setString(2, ServiceType);
        prep.executeUpdate();
        con.close();
        System.out.println("");
        System.out.println("Service " + ServiceType + " added successfully with ID: " + ServiceID);
        displayServicesUsingInnerJoin();
    } catch (SQLException e) {
        System.out.println("Error adding service: " + e.getMessage());
    }
}

    //UPDATE
    public void updateService(int ServiceID) {
        String query = "UPDATE from tbl_services WHERE ServiceID = ?";

        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setInt(1, ServiceID);
            prep.executeUpdate();
            System.out.println("");
            System.out.println("Service " + ServiceID + " updated successfully.");
            displayServicesUsingInnerJoin();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    //DELETE
    public void deleteService(int ServiceID) {
        String query = "DELETE from tbl_services WHERE ServiceID = ?";

        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setInt(1, ServiceID);
            prep.executeUpdate();
            System.out.println("");
            System.out.println("Room " + ServiceID + " deleted successfully.");
            displayRoomUsingInnerJoin();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    
    //FOR REGISTRATION
    //READ
    public void displayRegistrationUsingInnerJoin() {
        String query = "SELECT * FROM tbl_customer "
                + "INNER JOIN tbl_registration ON tbl_customer.CustomerID = tbl_registration.CustomerID "
                + "INNER JOIN tbl_rooms ON tbl_registration.RoomID = tbl_rooms.RoomID";

        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(query);
            System.out.println("");
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
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving registration data: " + e.getMessage());
        }
    }

    //CREATE
    public void addRegistration(int RegistrationID, int CustomerID, int RoomID, Date CheckInDate, String PeriodOfStay, Date CheckOutDate) {
        String query = "INSERT INTO tbl_registration (RegistrationID, CustomerID, RoomID, CheckInDate, PeriodOfStay, CheckOutDate) VALUES (?, ?, ?, ?, ?, ?)";


        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setInt(1, RegistrationID);
            prep.setInt(2, CustomerID);
            prep.setInt(3, RoomID);
            prep.setDate(4, CheckInDate);
            prep.setString(5, PeriodOfStay);
            prep.setDate(6, CheckOutDate);
            prep.executeUpdate();
            con.close();
            System.out.println("");
            System.out.println("Registration # " + RegistrationID + " added successfully");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //UPDATE
    public void updateRegistration(int RegistrationID, int CustomerID, int RoomID, Date CheckInDate, String PeriodOfStay, Date CheckOutDate) {
        String query = "UPDATE tbl_registration SET RegistrationID = ?, CustomerID = ?, RoomID = ?, CheckInDate = ?, PeriodOfStay = ?, CheckOutDate = ?";

        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setInt(1, RegistrationID);
            prep.setInt(2, CustomerID);
            prep.setInt(3, RoomID);
            prep.setDate(4, CheckInDate);
            prep.setString(5, PeriodOfStay);
            prep.setDate(6, CheckOutDate);
            prep.executeUpdate();
            System.out.println("");
            System.out.println("Registration " + RegistrationID + " updated successfully.");
            displayRegistrationUsingInnerJoin();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //DELETE
    public void deleteRegistration(int RegistrationID) {
        String query = "DELETE from tbl_registration WHERE RegistrationID = ?";

        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setInt(1, RegistrationID);
            prep.executeUpdate();
            System.out.println("");
            System.out.println("Registration " + RegistrationID + " deleted successfully.");
            displayRegistrationUsingInnerJoin();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    
    
    //FOR BILLINGS
    //READ
    public void displayBillingsUsingInnerJoin() {
        String query = "SELECT * FROM tbl_registration "
                + "INNER JOIN tbl_billings ON tbl_registration.RegistrationID = tbl_billings.RegistrationID";

        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(query);
            System.out.println("");
            System.out.println("");
            System.out.println("Bill ID\t\tRegistration Id\t\tBill Type\t\tBill Date\t\t\tBill Amount\t\tPayment Date\t\t\tPayment Status\t\tPayment Method");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while (result.next()) {
                int billId = result.getInt("BillID");
                int registrationId = result.getInt("RegistrationID");
                String billType = result.getString("BillType");
                Date billDate = result.getDate("BillDate");
                String billAmount = result.getString("BillAmount");
                Date paymentDate = result.getDate("PaymentDate");
                String paymentStatus = result.getString("PaymentStatus");
                String paymentMethod = result.getString("PaymentMethod");
                System.out.println(billId + "\t\t" + registrationId + "\t\t\t" + billType + "\t\t\t" + billDate + "\t\t\t" + billAmount + "\t\t" + paymentDate + "\t\t\t" + paymentStatus + "\t\t\t" + paymentMethod);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving billings data: " + e.getMessage());
        }
    }

    //CREATE
    public void addBilling(int BillID, int RegistrationID, String BillType, Date BillDate, String BillAmount, Date PaymentDate, String PaymentStatus, String PaymentMethod) {
        String query = "INSERT INTO tbl_billings (BillID, RegistrationID, BillType, BillDate, BillAmount, PaymentDate, PaymentStatus, PaymentMethod) "
                + "VALUES (?,?,?,?,?,?,?,?)";

        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setInt(1, BillID);
            prep.setInt(2, RegistrationID);
            prep.setString(3, BillType);
            prep.setDate(4, BillDate);
            prep.setString(5, BillAmount);
            prep.setDate(6, PaymentDate);
            prep.setString(7, PaymentStatus);
            prep.setString(8, PaymentMethod);
            prep.executeUpdate();
            con.close();
            System.out.println("");
            System.out.println("Bill " + BillID + " added successfully.");
            displayBillingsUsingInnerJoin();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //UPDATE
    public void updateBilling(int BillID, int RegistrationID, String BillType, Date BillDate, String BillAmount, Date PaymentDate, String PaymentStatus, String PaymentMethod) {
        String query = "UPDATE tbl_billings SET BillID = ?, RegistrationID = ?, BillType = ?, BillDate = ?, BillAmount = ?, PaymentDate = ?, PaymentStatus = ?, PaymentMethod = ?";

        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setInt(1, BillID);
            prep.setInt(2, RegistrationID);
            prep.setString(3, BillType);
            prep.setDate(4, BillDate);
            prep.setString(5, BillAmount);
            prep.setDate(6, PaymentDate);
            prep.setString(7, PaymentStatus);
            prep.setString(8, PaymentMethod);
            prep.executeUpdate();
            System.out.println("");
            System.out.println("Bill " + BillID + " updated successfully.");
            displayBillingsUsingInnerJoin();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //DELETE
    public void deleteBilling(int BillID) {
        String query = "DELETE from tbl_billings WHERE BillID = ?";

        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setInt(1, BillID);
            prep.executeUpdate();
            System.out.println("");
            System.out.println("Bill # " + BillID + " deleted successfully.");
            displayBillingsUsingInnerJoin();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    
    
    
    //FOR ROOM PRICE
    //READ
    public void displayRoomPrice() {
    String query = "SELECT * FROM tbl_roomprice";

    try {
        connect();
        state = con.createStatement();
        result = state.executeQuery(query);
        System.out.println("");
        System.out.println("");
        System.out.println("Room Type       Room Price      Room Description");
        System.out.println("---------------------------------------------------------");
        while (result.next()) {
            String roomType = result.getString("RoomType");
            int roomPrice = result.getInt("RoomPrice");
            String roomDescription = result.getString("RoomDescription");
            
            // Print formatted output with fixed-width columns
            System.out.printf("%-15s %-15s %-30s%n", roomType, roomPrice, roomDescription);
        }

    } catch (SQLException e) {
        System.out.println("Error retrieving room data: " + e.getMessage());
    }
}

    //CREATE
    public void addroomPrice(String RoomType, int RoomPrice, String RoomDescription) {
        String query = "INSERT INTO tbl_roomprice (RoomType, RoomPrice, RoomDescription) "
                + "VALUES (?,?,?)";

        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setString(1, RoomType);
            prep.setInt(2, RoomPrice);
            prep.setString(3, RoomDescription);
            prep.executeUpdate();
            con.close();
            System.out.println("");
            System.out.println("Room " + RoomType + RoomPrice + " updated successfully.");
            displayRoomPrice();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //UPDATE
    public void updateRoomPrice(String RoomType, int RoomPrice, String RoomDescription) {
        String query = "UPDATE tbl_roomprice SET RoomType = ?, RoomPrice = ?, RoomDescription = ?";

        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setString(1, RoomType);
            prep.setInt(2, RoomPrice);
            prep.setString(3, RoomDescription);
            prep.executeUpdate();
            System.out.println("");
            System.out.println("Room " + RoomType + RoomPrice + " updated successfully.");
            displayRoomPrice();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //DELETE
    public void deleteRoomPrice(String RoomType) {
        String query = "DELETE from tbl_roomprice WHERE RoomType = ?";

        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setString(1, RoomType);
            prep.executeUpdate();
            System.out.println("");
            System.out.println("Room " + RoomType + " deleted successfully.");
            displayRoomPrice();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private String spaces(int roomTypeSpaces) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
