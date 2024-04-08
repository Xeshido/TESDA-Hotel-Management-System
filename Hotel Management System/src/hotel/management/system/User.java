
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

