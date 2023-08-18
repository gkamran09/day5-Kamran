package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedTicketException;

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
        if (ticketCarMap.size() >= capacity) {
            throw new NoAvailablePositionException();
        } else {
            ParkingTicket ticket = new ParkingTicket();
            ticketCarMap.put(ticket, car);
            return ticket;
        }
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car fetchedCar = ticketCarMap.get(parkingTicket);
        if (fetchedCar == null) {
            throw new UnrecognizedTicketException();
        } else {
            ticketCarMap.remove(parkingTicket);
            return fetchedCar;
        }
    }
}
