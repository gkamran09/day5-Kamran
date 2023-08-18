package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private static final int DEFAULT_CAPACITY = 10;
    private Map<ParkingTicket, Car> ticketCarMap;
    private final int capacity;

    public ParkingLot() {
        capacity = DEFAULT_CAPACITY;
        this.ticketCarMap = new HashMap<>();
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.ticketCarMap = new HashMap<>();
    }

    public ParkingTicket park(Car car) {
        if (ticketCarMap.size() < capacity) {
            ParkingTicket ticket = new ParkingTicket();
            ticketCarMap.put(ticket, car);
            return ticket;
        } else {
            return null;
        }
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car fetchedCar = ticketCarMap.get(parkingTicket);
        if (fetchedCar != null) {
            ticketCarMap.remove(parkingTicket); // Remove the fetched car from the map
            return fetchedCar;
        } else {
            return null;
        }
    }
}
