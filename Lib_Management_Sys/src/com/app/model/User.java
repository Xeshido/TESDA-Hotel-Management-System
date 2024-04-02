
package com.app.model;


public class User {
    private int id;
    private String name;
    private String password;
    private String fname;
    private String level;
    
    public User(){
        
    }
    
    public User(int id, String name, String password, String fname, String level){
        this.id = id;
        this.name = name;
        this.password = password;
        this.fname = fname;
        this.level = level;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setPassword(String password){
        this.password = password; 
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setFname(String fname){
        this.fname = fname;
    }
    
    public String getFname(){
        return fname;
    }
    
    public void setLevel(String level){
        this.level = level;
    }
    
    public String getLevel(){
        return level;
    }
}
