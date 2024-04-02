
package com.app.view;

import com.app.controller.UserController;
import com.app.model.User;
import java.util.Scanner;


public class ReleasedAccount {
    public static void main(String[] args) {
        UserController u = new UserController();
        Scanner sc = new Scanner(System.in);
        User user = new User();
        
        System.out.print("Enter User ID that you want to released: ");
        user.setId(sc.nextInt());
        
        u.releasedAccount(user);
    }
}
