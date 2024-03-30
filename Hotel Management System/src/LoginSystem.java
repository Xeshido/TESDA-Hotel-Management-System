import java.util.ArrayList;
import java.util.List;

public class LoginSystem {
    private List<User> users;

    public LoginSystem() {
        users = new ArrayList<>();
    }

    public void addUser(String username, String password) {
        users.add(new User(username, password));
    }

    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
