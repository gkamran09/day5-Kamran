package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SmartParkingBoyTest {

    @Test
    void should_park_car_in_first_parking_lot_when_both_lots_have_available_space_given_smart_parking_boy() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        // When
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        // Then
        assertNotNull(parkingTicket);
        assertEquals(car, firstParkingLot.fetch(parkingTicket));
    }

}
