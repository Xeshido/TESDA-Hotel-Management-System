
package hotel.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public abstract class DBConnection {
    
    private final static String URL = "jdbc:mysql://localhost:3306/db_hotel_management_system";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "";
    private final static String DRIVER = "com.mysql.jdbc.Driver";
    public Connection con;
    public Statement state;
    public ResultSet result;
    public PreparedStatement prep;
    
    public void connect() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        Main main  = new Main();
        main.connect();
    }
    
    
    
}
