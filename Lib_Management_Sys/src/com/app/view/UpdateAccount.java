
package com.app.view;

import com.app.controller.UserController;
import com.app.model.User;
import java.util.Scanner;


public class UpdateAccount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserController u = new UserController();
        User user = new User();
        System.out.print("Enter User ID that you want to update: ");
        user.setId(sc.nextInt());
        sc.nextLine();
        System.out.print("Enter new User Fname: ");
        user.setFname(sc.nextLine());
        
        u.updateAccount(user);
        
    }
}
