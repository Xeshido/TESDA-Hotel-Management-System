
package hotel.management.system;

import java.sql.SQLException;
import java.sql.Date;


public class Function extends DBConnection {

    // READ API
    public void displayHotel() {
        String query = "SELECT * FROM tbl_customer";

        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(query);
            System.out.println("ID\tName\t\t\tNumber\t\t\tEmail\t\t\t\tAge");
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            while (result.next()) {
                int id = result.getInt("CustomerID");
                String customerName = result.getString("customerName");
                String cellNum = result.getString("CellNum");
                String email = result.getString("Email");
                int age = result.getInt("Age");
                System.out.println(id + "\t" + customerName + "\t\t" + cellNum + "\t\t" + email + "\t\t" + age);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving hotel data: " + e.getMessage());
        }
    }

    public void displayRoom() {
        String query = "SELECT * FROM tbl_roomprice "
                + "INNER JOIN tbl_rooms ON tbl_roomprice.RoomType = tbl_rooms.RoomType";

        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(query);
            System.out.println("");
            System.out.println("");
            System.out.println("Room ID\t\tRoom Type\t\tRoom Status\t\tRoom Description");
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            while (result.next()) {
           int roomId = result.getInt("RoomID");
            String roomType = result.getString("RoomType");
            String roomStatus = result.getString("RoomStatus");
            String roomDesc = result.getString("RoomDescription");
            
            int roomTypeSpaces = 20 - roomType.length(); 
            int roomStatusSpaces = 20 - roomStatus.length(); 
            
            StringBuilder roomTypeSpacesBuilder = new StringBuilder();
            for (int i = 0; i < roomTypeSpaces; i++) {
                roomTypeSpacesBuilder.append(" ");
            }
            StringBuilder roomStatusSpacesBuilder = new StringBuilder();
            for (int i = 0; i < roomStatusSpaces; i++) {
                roomStatusSpacesBuilder.append(" ");
            }
            
            System.out.println(roomId + "\t\t" + roomType + roomTypeSpacesBuilder.toString() +
                    roomStatus + roomStatusSpacesBuilder.toString() + roomDesc);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving room data: " + e.getMessage());
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
            System.out.println("");
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            System.out.println("Registration ID\t\tCustomer ID\t\tRoom ID\t\tCheck-in Date\t\tPeriod of Stay\t\tCheck-out Date");
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

    public void displayServicePrice() {
        String query = "SELECT * FROM tbl_serviceprice";

        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(query);
            System.out.println("");
            System.out.println("");
            System.out.println("Service Type\t\tService Price");
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            while (result.next()) {
                String serviceType = result.getString("ServiceType");
                String servicePrice = result.getString("ServicePrice");
                int spaces = 30 - serviceType.length(); 
                StringBuilder spacesBuilder = new StringBuilder();
                for (int i = 0; i < spaces; i++) {
                    spacesBuilder.append(" ");
                }
                String spaceString = spacesBuilder.toString();
                System.out.println(serviceType + spaceString + servicePrice);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving room data: " + e.getMessage());
        }
    }

    public void displayServicesUIsingInnerJoin() {
        String query = "SELECT * FROM tbl_serviceprice "
                + "INNER JOIN tbl_services ON tbl_serviceprice.ServiceType = tbl_services.ServiceType";

        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(query);
            System.out.println("");
            System.out.println("");
            System.out.println("Service ID\t\tService Type\t\tService Description");
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            while (result.next()) {
                int serviceId = result.getInt("ServiceID");
                String serviceType = result.getString("ServiceType");
                String serviceDescription = result.getString("ServiceDescription");
                System.out.println(serviceId + "\t\t\t" + serviceType + "\t\t\t" + serviceDescription);

            }
        } catch (SQLException e) {
            System.out.println("Error retrieving room data: " + e.getMessage());
        }
    }

    public void displayBillingsUsingInnerJoin() {
        String query = "SELECT * FROM tbl_registration "
                + "INNER JOIN tbl_billings ON tbl_registration.RegistrationID = tbl_billings.RegistrationID";
        
        
        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(query);
            System.out.println("");
            System.out.println("");
            System.out.println("Bill ID\t\tRegistration Id\t\tBill Type\t\tBill Date\t\tBill Amount\t\tPayment Date\t\tPayment Status\t\tPayment Method");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while (result.next()) {
                int billId = result.getInt("BillID");
                int registrationId = result.getInt("RegistrationID");
                String billType = result.getString("BillType");
                int billDate = result.getInt("BillDate");
                String billAmount = result.getString("BillAmount");
                int paymentDate = result.getInt("PaymentDate");
                String paymentStatus = result.getString("PaymentStatus");
                String paymentMethod = result.getString("PaymentMethod");
                System.out.println(billId + "\t\t" + registrationId + "\t\t\t" + billType + "\t\t\t" + billDate + "\t\t\t" + billAmount + "\t\t" + paymentDate + "\t\t\t" + paymentStatus + "\t\t\t" + paymentMethod);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving billings data: " + e.getMessage());
        }
    }

    public void displayRoomPrice() {
        String query = "SELECT * FROM tbl_roomprice";

        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(query);
            System.out.println("");
            System.out.println("");
            System.out.println("Room Type\tRoom Price");
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            while (result.next()) {
                String roomType = result.getString("RoomType");
            String roomPrice = result.getString("RoomPrice");
            int spaces = 14 - roomType.length(); // Adjust the number 14 according to your need
            System.out.print(roomType);
            for (int i = 0; i < spaces; i++) {
                System.out.print(" ");
            }
            System.out.println("\t" + roomPrice);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving room data: " + e.getMessage());
        }
    }
}