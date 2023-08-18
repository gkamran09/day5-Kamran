package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private static final int DEFAULT_CAPACITY = 10;
    private final Map<ParkingTicket, Car> ticketCarMap;
    private final int capacity;
    private boolean isFull;

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
        if (!ticketCarMap.containsKey(parkingTicket)) {
            throw new UnrecognizedTicketException();
        } else {
            return  ticketCarMap.remove(parkingTicket);
        }
    }

    private boolean isFull(){
        return ticketCarMap.size() == capacity;
    }
    public int getAvailableCapacity() {
        return capacity - ticketCarMap.size();
    }

    public boolean hasAvailableCapacity(){
        return !isFull;
    }
}
