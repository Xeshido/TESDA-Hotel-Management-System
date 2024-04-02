
package com.app.view;

import com.app.controller.UserController;
import com.app.model.User;
import java.util.Scanner;


public class LoginAccount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserController uc = new UserController();
        
        User user = new User();
        System.out.print("Enter username: ");
        user.setName(sc.nextLine());
        System.out.print("Enter password: ");
        user.setPassword(sc.nextLine());
        
        uc.loginAccount(user);
    }
}
