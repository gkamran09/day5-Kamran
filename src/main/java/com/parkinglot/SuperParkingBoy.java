package com.parkinglot;

import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.List;

public class SuperParkingBoy {
    private List<ParkingLot> parkingLots;

    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        ParkingLot bestParkingLot = null;
        double maxRate = -1.0;

        for (ParkingLot parkingLot : parkingLots) {
            double rate = (double) parkingLot.getAvailableCapacity() / parkingLot.getTotalCapacity();
            if (rate > maxRate) {
                maxRate = rate;
                bestParkingLot = parkingLot;
            }
        }

        if (bestParkingLot != null) {
            return bestParkingLot.park(car);
        }

        return null;
    }

    public ParkingLot getParkingLotWithHighestRate() {
        ParkingLot bestParkingLot = null;
        double maxRate = -1.0;  // Initialize with a negative value

        for (ParkingLot parkingLot : parkingLots) {
            double rate = (double) parkingLot.getAvailableCapacity() / parkingLot.getTotalCapacity();
            if (rate > maxRate) {
                maxRate = rate;
                bestParkingLot = parkingLot;
            }
        }

        return bestParkingLot;
    }

    public Car fetch(ParkingTicket ticket) {
        for (ParkingLot parkingLot : parkingLots) {
            try {
                Car car = parkingLot.fetch(ticket);
                return car;
            } catch (UnrecognizedTicketException ignored) {
            }
        }
        throw new UnrecognizedTicketException();
    }
}
