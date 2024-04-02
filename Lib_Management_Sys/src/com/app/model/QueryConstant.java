 
package com.app.model;


public interface QueryConstant {
    // Connection
    String URL = "jdbc:mysql://localhost:3306/lib_mngmnt_db";
    String USERNAME = "root";
    String PASSWORD = "";
    String DRIVER = "com.mysql.jdbc.Driver";
    
    // CRUD API Queries for USERS
    String CREATE_ACCOUNT = "Insert into tblusers (user_name, user_password, user_fname, type_level) "
                + "values (?,?,?,?)";
    String DISPLAY_ACCOUNT = "Select * from tblusers where archived = 0";
    String DISPLAY_ACCOUNT_USINGINNERJOIN = "Select * from tblusers Inner Join tbltypes "
                + "On tblusers.type_level = tbltypes.type_level "
                + "where archived = 0";
    String UPDATE_ACCOUNT = "Update tblusers set user_fname = ? where user_id = ?"; 
    String DELETE_ACCOUNT = "Delete from tblusers where user_id = ?";
    String RELEASED_ACCOUNT = "Update tblusers set archived = 1 where user_id = ?";
    String RETRIEVED_ACCOUNT = "Update tblusers set archived = 0 where user_id = ?";
    String SEARCH_ACCOUNT = "Select * from tblusers where user_id = ? and archived = 0";
    String LOGIN_ACCOUNT = "Select * from tblusers where user_name = ? and user_password = ?";
    
    // CRUD API Queries for BOOKS
    String DISPLAY_BOOKS = "Select * from tblbooks";
    String CREATE_BOOKS = "Insert into tblbooks(book_title, book_author, category_name) "
                + "values (?,?,?)";
    String UPDATE_BOOKS = "Update tblbooks set book_title = ? where book_id = ?";
    
}
