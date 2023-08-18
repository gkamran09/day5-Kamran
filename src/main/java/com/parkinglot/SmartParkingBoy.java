package com.parkinglot;

import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy {
    private final List<ParkingLot> parkingLotList;

    public SmartParkingBoy(List<ParkingLot> parkingLotList){
        this.parkingLotList = parkingLotList;
    }

    public ParkingTicket park(Car car) {
        ParkingLot parkingLotWithMostEmptyPositions = parkingLotList.stream()
                .max(Comparator.comparingInt(ParkingLot::getAvailableCapacity))
                .orElseThrow(NoAvailablePositionException::new);

        return parkingLotWithMostEmptyPositions.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        for (ParkingLot parkingLot : parkingLotList) {
            try {
                return parkingLot.fetch(parkingTicket);
            } catch (UnrecognizedTicketException ignored) {
            }
        }
        throw new UnrecognizedTicketException();
    }
}
