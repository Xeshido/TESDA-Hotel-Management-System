
package com.app.model;


public class Type {
    private String level;
    private String desc;

    public Type(){
        
        
    }

    public Type(String level, String desc) {
        this.level = level;
        this.desc = desc;
    }
    
   

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    
    
    
    
    
}
