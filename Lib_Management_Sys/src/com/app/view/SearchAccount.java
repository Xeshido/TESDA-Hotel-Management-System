/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.view;

import com.app.controller.UserController;
import com.app.model.User;
import java.util.Scanner;

/**
 *
 * @author Mybelle
 */
public class SearchAccount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserController uc = new UserController();
        User user = new User();
        System.out.print("Search username: ");
        user.setId(sc.nextInt());
        uc.searchAccount(user);
    }
}
