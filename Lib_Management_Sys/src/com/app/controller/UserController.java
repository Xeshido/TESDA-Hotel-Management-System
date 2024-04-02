
package com.app.controller;

import com.app.db.DbConnection;
import com.app.model.QueryConstant;
import com.app.model.Type;
import com.app.model.User;
import com.app.repository.UserRepository;


public class UserController extends DbConnection implements QueryConstant, UserRepository{
    
    //CRUD API
    //CREATE ACC
    @Override
    public void createAccount(User user){    
        try {
            connect();
            prep = con.prepareStatement(CREATE_ACCOUNT);
            prep.setString(1, user.getName());
            prep.setString(2, user.getPassword());
            prep.setString(3, user.getFname());
            prep.setString(4, user.getLevel());
            prep.executeUpdate();
            System.out.println("Account " + user.getFname() + " added successfully!");
            con.close();
            displayAccount(new User());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    //READ API
    @Override
    public void displayAccount(User user){
        
        try {
           connect();
           state = con.createStatement(); 
           result = state.executeQuery(DISPLAY_ACCOUNT);
            System.out.println("ID\t\tUSERNAME\t\tPASSWORD\t\tNAME\t\tLEVEL");
           while(result.next()){
               user.setId(result.getInt("user_id")); 
               user.setName(result.getString("user_name")); 
               user.setPassword(result.getString("user_password")); 
               user.setFname(result.getString("user_fname")); 
               user.setLevel(result.getString("type_level"));
               System.out.println(user.getId() + "\t\t" + user.getName() + "\t\t" + user.getPassword() 
                       + "\t\t" + user.getFname() + "\t\t" + user.getLevel());
           }
           
           con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Override
    public void displayAccountUsingInnerJoin(User user, Type type){
        
        try {
           connect();
           state = con.createStatement(); 
           result = state.executeQuery(DISPLAY_ACCOUNT_USINGINNERJOIN);
            System.out.println("ID\t\tUSERNAME\t\tPASSWORD\t\tNAME\t\tLEVEL\t\tDESCRIPTION");
           while(result.next()){
               user.setId(result.getInt("user_id")); 
               user.setName(result.getString("user_name")); 
               user.setPassword(result.getString("user_password")); 
               user.setFname(result.getString("user_fname")); 
               type.setLevel(result.getString("type_level"));
               type.setDesc(result.getString("type_desc"));
               System.out.println(user.getId() + "\t\t" + user.getName() + "\t\t" + 
                       user.getPassword() + "\t\t" + user.getFname() + "\t\t" + type.getLevel()
                       + "\t\t" + type.getDesc());
           }
           
           con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        
        
    }
    //UPDATE API
    @Override
    public void updateAccount(User user){

        try {
            connect();
            prep = con.prepareStatement(UPDATE_ACCOUNT);
            prep.setString(1, user.getFname());
            prep.setInt(2, user.getId());
            System.out.println("User " + user.getId() + " updated Successfully. "
                    + "New user_name is " + user.getFname());
            prep.executeUpdate();
            displayAccount(new User());
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
   
                
    }
    
    // DELETE API
    @Override
    public void deleteAccount(User user){ 
        try {
            connect();
            prep = con.prepareStatement(DELETE_ACCOUNT);
            prep.setInt(1, user.getId());
            prep.executeUpdate();
            System.out.println("User " + user.getId() + " deleted successfully!");
            displayAccount(new User());
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Override
    public void releasedAccount(User user){
        try {
            connect();
            prep = con.prepareStatement(RELEASED_ACCOUNT);
            prep.setInt(1, user.getId());
            prep.executeUpdate();
            System.out.println("Account " + user.getId() + " released from Accounts");
            displayAccount(new User());
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        
        }
    }
    
    @Override
    public void retrieveAccount(User user){
        try {
            connect();
            prep = con.prepareStatement(RETRIEVED_ACCOUNT);
            prep.setInt(1, user.getId());
            prep.executeUpdate();
            displayAccount(new User());
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void searchAccount(User user){
        try {
            connect();
            prep = con.prepareStatement(SEARCH_ACCOUNT);
            prep.setInt(1, user.getId());
            result = prep.executeQuery();
            System.out.println("ID\t\tUSERNAME\t\tPASSWORD\t\tNAME\t\tLEVEL\n");  
            while(result.next()){
                user.setId(result.getInt("user_id"));
                user.setName(result.getString("user_name"));
                user.setPassword(result.getString("user_password"));
                user.setFname(result.getString("user_fname"));
                user.setLevel(result.getString("type_level")); 
                System.out.println(user.getId() + "\t\t" + user.getName() 
                        + "\t\t" + user.getPassword() 
                        + "\t\t" + user.getFname() + "\t\t" + user.getLevel());
            }
            con.close();
                
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Override
    public void loginAccount(User user){
        try {
            connect();
            prep = con.prepareStatement(LOGIN_ACCOUNT);
            prep.setString(1, user.getName());
            prep.setString(2, user.getPassword());
            result = prep.executeQuery();
            if (result.next()) {
                System.out.println("Logged in successfully!");
            } else {
                System.out.println("Logged in failed!");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
}

