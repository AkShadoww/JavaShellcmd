package com.gqt.GUI;

import java.util.*;

public class BusData {
    public static List<Bus> buses = new ArrayList<>();

    static {
        buses.add(new Bus(1, "Delhi", "Jaipur", "28-05-2025", "08:00 AM", "12:00 PM", "12:30 PM", "Delhi"));
        buses.add(new Bus(2, "Mumbai", "Pune", "28-05-2025", "09:00 AM", "11:30 AM", "12:00 PM", "Mumbai"));
    }

    public static void addBus(Bus bus) {
        buses.add(bus);
    }

    public static List<Bus> getBusesByDate(String date) {
        List<Bus> result = new ArrayList<>();
        for (Bus b : buses) {
            if (b.date.equals(date)) result.add(b);
        }
        return result;
    }

    public static Bus getBusById(int id) {
        for (Bus b : buses) {
            if (b.busId == id) return b;
        }
        return null;
    }
}
