
package hotel.management.system;

public class AuthenticationService {
    public User login(String staffId, String password) {
        // Simulated logic for validating login credentials
        if (staffId.equals("receptionist") && password.equals("password")) {
            return new Receptionist(staffId, password);
        } else if (staffId.equals("manager") && password.equals("password")) {
            return new Manager(staffId, password);
        }
        return null;
    }
}
