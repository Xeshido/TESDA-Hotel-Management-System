

package com.app.repository;

import com.app.model.Type;
import com.app.model.User;


public interface UserRepository {
    public void createAccount(User user);
    public void displayAccount(User user);
    public void displayAccountUsingInnerJoin(User user, Type type);
    public void updateAccount(User user);
    public void deleteAccount(User user);
    public void releasedAccount(User user);
    public void retrieveAccount(User user);
    public void loginAccount(User user);
}
