/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.management.system;

public class User {
    private String staffId;
    private String password;

    public User(String staffId, String password) {
        this.staffId = staffId;
        this.password = password;
    }

    public String getStaffId() {
        return staffId;
    }

    public String getPassword() {
        return password;
    }
}

