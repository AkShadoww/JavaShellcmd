package com.gqt.bus;

import java.util.ArrayList;
import java.util.List;

public class Bus {
    int busId;
    String source;
    String destination;
    String currentLocation;
    String date;
    String departureTime;
    String arrivalTime;
    String reachingTime;
    int totalSeats = 40;
    int bookedSeats = 0;

    public Bus(int busId, String source, String destination, String currentLocation,
               String date, String departureTime, String arrivalTime, String reachingTime) {
        this.busId = busId;
        this.source = source;
        this.destination = destination;
        this.currentLocation = currentLocation;
        this.date = date;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.reachingTime = reachingTime;
    }

    public boolean isSeatAvailable() {
        return bookedSeats < totalSeats;
    }

    public int reserveSeat() {
        if (isSeatAvailable()) {
            bookedSeats++;
            return bookedSeats; 
        }
        return -1; 
    }
    
    public List<Integer> reserveSeat(int count) {
        List<Integer> seatNumbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (bookedSeats < totalSeats) {
                bookedSeats++;
                seatNumbers.add(bookedSeats);
            } else {
                break; 
            }
        }
        return seatNumbers;
    }


    @Override
    public String toString() {
        return "Bus ID: " + busId +
                "\nSource: " + source +
                "\nDestination: " + destination +
                "\nDate: " + date +
                "\nCurrent Location: " + currentLocation +
                "\nDeparture Time: " + departureTime +
                "\nArrival Time: " + arrivalTime +
                "\nReaching Time: " + reachingTime +
                "\nAvailable Seats: " + (totalSeats - bookedSeats);
    }
    
    public void cancelSeat() {
        if (bookedSeats > 0) {
            bookedSeats--;
        }
    }

}

