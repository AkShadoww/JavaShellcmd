package com.gqt.bus;

import java.util.*;
import java.util.Scanner;

public class BusReservation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Bus> busList = new ArrayList<>();
        int[] busIdCounter = {1}; 

        Admin admin = new Admin();
        User user = new User();

        while (true) {
            System.out.println("\n==================================================================\n"
            		+ "\t WELCOME TO SRS TRAVELS "
            		+ "\n==================================================================\n");
            System.out.println("[1] Admin Login");
            System.out.println("[2] User Access");
            System.out.println("[3] Exit\n");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                
            case 1:
                    if (admin.login()) {
                        admin.adminMenu(busList, busIdCounter);
                    }
                    break;
               
                case 2:
                    System.out.println("\n--- User Details ---");
                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter your email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter your phone number: ");
                    String phone = sc.nextLine();

                    UserDetails userDetails = new UserDetails(name, email, phone);
                    userDetails.displayUserInfo();

                    user.userMenu(busList);
                    break;


                case 3:
                    System.out.println("Exiting... "
                    		+ "\n--------------------------------------------------------------\n"
                    		+ "Thank you ! Enjoy Your Journy with Us :-)");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}

