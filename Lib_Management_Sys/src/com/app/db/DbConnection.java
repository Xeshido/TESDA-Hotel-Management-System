
package com.app.db;

import com.app.model.QueryConstant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;





public abstract class DbConnection implements QueryConstant{
    

    public Connection con;
    public PreparedStatement prep;
    public Statement state;
    public ResultSet result;
    
    
    public void connect(){
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    
}
