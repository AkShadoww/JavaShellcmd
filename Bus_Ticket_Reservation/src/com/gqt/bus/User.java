package com.gqt.bus;

import java.util.*;

public class User {
    Scanner sc = new Scanner(System.in);

    List<String> reservations = new ArrayList<>();	
    
    public void userMenu(List<Bus> busList) {
        while (true) {
            System.out.println("\n--- User Menu ---");
            System.out.println("[1] View Available Buses");
            System.out.println("[2] Reserve Ticket");
            System.out.println("[3] Cancel Ticket");
            System.out.println("[4] Back");
            System.out.print("\nChoose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    searchBuses(busList);
                    break;
                case 2:
                    reserveTicket(busList);
                    break;
                case 3: 
                	cancelTicket(busList);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("\nInvalid choice!\n");
            }
        }
    }

    public void searchBuses(List<Bus> busList) {
        System.out.print("Enter date (dd-mm-yyyy): ");
        String date = sc.nextLine();

        boolean found = false;
        for (Bus bus : busList) {
            if (bus.date.equals(date)) {
                System.out.println("---------------------------------------------------------");
                System.out.println(bus);
                found = true;
            }
        }

        if (!found) {
            System.out.println("\nNo buses found for the given criteria.");
        }
    }

    public void reserveTicket(List<Bus> busList) {
        System.out.print("Enter Bus ID to reserve ticket: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Bus bus : busList) {
            if (bus.busId == id) {
                int available = bus.totalSeats - bus.bookedSeats;
                System.out.println("Available seats: " + available);

                System.out.print("Enter number of seats to book: ");
                int count = sc.nextInt();
                sc.nextLine();

                if (count <= 0 || count > available) {
                    System.out.println("\nInvalid number of seats.");
                    return;
                }

                List<Integer> seatNumbers = bus.reserveSeat(count);
                for (int seat : seatNumbers) {
                    reservations.add(bus.busId + "-" + seat); 
                }

                System.out.println("\nTicket(s) reserved successfully!\n");
                System.out.println("----------------------------\n"
                					+"Bus Info:");
                System.out.println(bus);
                System.out.println("Your Seat Number(s): " + seatNumbers);
                return;
            }
        }

        System.out.println("\nBus ID not found.");
    }

    
    public void cancelTicket(List<Bus> busList) {
        if (reservations.isEmpty()) {
            System.out.println("You have no active reservations.");
            return;
        }

        System.out.println("\n--- Your Reservations ---");
        for (int i = 0; i < reservations.size(); i++) {
            System.out.println((i + 1) + ". " + reservations.get(i));
        }

        System.out.print("Enter reservation number to cancel: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice < 1 || choice > reservations.size()) {
            System.out.println("\nInvalid selection.");
            return;
        }

        String reservation = reservations.remove(choice - 1);
        String[] parts = reservation.split("-");
        int busId = Integer.parseInt(parts[0]);
        int seatNumber = Integer.parseInt(parts[1]);

        for (Bus bus : busList) {
            if (bus.busId == busId) {
                bus.cancelSeat(); 
                System.out.println("\nTicket canceled successfully for Bus ID " + busId + ", Seat No. " + seatNumber);
                return;
            }
        }

        System.out.println("\nBus not found !! Cancellation failed.");
    }
    
    


}
