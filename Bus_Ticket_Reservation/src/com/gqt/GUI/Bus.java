package com.gqt.GUI;

public class Bus {
    public int busId;
    public String source, destination, date, depTime, arrTime, reachTime, currentLocation;
    public int totalSeats = 40;
    public int bookedSeats = 0;

    public Bus(int busId, String source, String destination, String date,
               String depTime, String arrTime, String reachTime, String currentLocation) {
        this.busId = busId;
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.depTime = depTime;
        this.arrTime = arrTime;
        this.reachTime = reachTime;
        this.currentLocation = currentLocation;
    }

    public boolean hasSeats(int count) {
        return (bookedSeats + count) <= totalSeats;
    }

    public String reserveSeats(int count) {
        StringBuilder reserved = new StringBuilder();
        for (int i = 0; i < count && bookedSeats < totalSeats; i++) {
            bookedSeats++;
            reserved.append(bookedSeats).append(" ");
        }
        return reserved.toString().trim();
    }

    public String toString() {
        return "Bus ID: " + busId + ", " + source + " -> " + destination +
               ", Date: " + date + ", Departure: " + depTime +
               ", Arrival: " + arrTime + ", Reach: " + reachTime +
               ", Location: " + currentLocation + ", Seats Left: " + (totalSeats - bookedSeats);
    }
}

