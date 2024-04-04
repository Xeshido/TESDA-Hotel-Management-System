/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.management.system;

import java.util.Scanner;

public class ManagerMenu implements Menu {
    private Manager manager;
    private Scanner scanner = new Scanner(System.in);

    public ManagerMenu(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void displayMenu() {
        // Implement manager menu options
        System.out.println("Manager menu goes here");
    }
}
