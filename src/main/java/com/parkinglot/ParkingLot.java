package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private final Map<ParkingTicket, Car> ticketCarMap;
    private final int capacity;

    public ParkingLot(int capacity){
        this.capacity = capacity;
        this.ticketCarMap =new HashMap<>();
    }
    public ParkingTicket park (Car car){
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
        if (fetchedCar != null){
            ticketCarMap.remove(parkingTicket);
            return fetchedCar;
        }
        else {
            return null;
        }
    }
}
