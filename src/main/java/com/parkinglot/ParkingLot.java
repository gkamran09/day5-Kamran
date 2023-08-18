package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private Map<ParkingTicket, Car> ticketCarMap;

    public ParkingLot(){
        this.ticketCarMap =new HashMap<>();
    }
    public ParkingTicket park (Car car){
        ParkingTicket ticket = new ParkingTicket();
        ticketCarMap.put(ticket, car);
        return ticket;
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
