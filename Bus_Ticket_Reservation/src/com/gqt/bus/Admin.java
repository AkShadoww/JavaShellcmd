package com.gqt.bus;

import java.util.*;

public class Admin {
    private final String USERNAME = "admin";
    private final String PASSWORD = "admin123";
    Scanner sc = new Scanner(System.in);

    public boolean login() {
        System.out.print("Enter admin username: ");
        String inputUser = sc.nextLine();
        System.out.print("Enter admin password: ");
        String inputPass = sc.nextLine();

        if (inputUser.equals(USERNAME) && inputPass.equals(PASSWORD)) {
            System.out.println("\nAdmin login successful.");
            return true;
        } else {
            System.out.println("\nIncorrect credentials. Access denied.");
            return false;
        }
    }

    public void adminMenu(List<Bus> busList, int[] busIdCounter) {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("[1] Add Bus");
            System.out.println("[2] View All Buses");
            System.out.println("[3] Back");
            System.out.print("\nChoose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addBus(busList, busIdCounter);
                    break;
                case 2:
                    viewBuses(busList);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public void addBus(List<Bus> busList, int[] busIdCounter) {
        System.out.print("Enter source: ");
        String source = sc.nextLine();
        System.out.print("Enter destination: ");
        String destination = sc.nextLine();
        System.out.print("Enter current location: ");
        String currentLocation = sc.nextLine();
        System.out.print("Enter date (dd-mm-yyyy): ");
        String date = sc.nextLine();
        System.out.print("Enter departure time (hh:mm AM/PM): ");
        String departureTime = sc.nextLine();
        System.out.print("Enter arrival time (hh:mm AM/PM): ");
        String arrivalTime = sc.nextLine();
        System.out.print("Enter reaching time (hh:mm AM/PM): ");
        String reachingTime = sc.nextLine();

        Bus bus = new Bus(busIdCounter[0]++, source, destination, currentLocation, date,
                departureTime, arrivalTime, reachingTime);
        busList.add(bus);

        System.out.println("\nBus added successfully!\n");
    }

    public void viewBuses(List<Bus> busList) {
        if (busList.isEmpty()) {
            System.out.println("\nNo buses available.\n");
            return;
        }

        for (Bus bus : busList) {
            System.out.println("------------------------------------------------");
            System.out.println(bus);
        }
    }
}
