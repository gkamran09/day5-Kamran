package com.parkinglot;

import org.junit.jupiter.api.Test;

import static java.util.concurrent.locks.LockSupport.park;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    void should_return_ticket_when_park_given_parking_lot_and_car(){
        //Given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();

        //When
        ParkingTicket parkingTicket = parkingLot.park(car);

        //Then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_car_when_fetch_given_parking_lot_and_ticket(){
        //Given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);
        //When

        Car fetchedCar = parkingLot.fetch(parkingTicket);

        //Then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_correct_cars_when_fetch_given_parking_lot_and_tickets_and_two_cars() {
        //Given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car();
        Car car2 = new Car();

        ParkingTicket parkingTicket1 = parkingLot.park(car1);
        ParkingTicket parkingTicket2 = parkingLot.park(car2);

        //When
        Car fetchedCar1 = parkingLot.fetch(parkingTicket1);
        Car fetchedCar2 = parkingLot.fetch(parkingTicket2);
        //Then

        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }

    @Test
    void should_return_null_when_fetch_given_wrong_ticket(){
        //Given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        parkingLot.park(car);

        ParkingTicket wrongTicket = new ParkingTicket();

        //When
        Car fetchCar = parkingLot.fetch(wrongTicket);

        //Then
        assertNull(fetchCar);
    }

    @Test
    void should_return_null_when_fetch_given_used_ticket(){
        // Given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        ParkingTicket ticket = parkingLot.park(car);

        // When
        parkingLot.fetch(ticket);
        Car fetchCarWithUsedTicket = parkingLot.fetch(ticket);

        // Then
        assertNull(fetchCarWithUsedTicket);
    }

    @Test
    void should_return_null_when_park_given_no_available_capacity(){
        //Given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car();
        Car car2 = new Car();

        //When
        parkingLot.park(car1);
        ParkingTicket ticket2 = parkingLot.park(car2);

        //Then
        assertNull(ticket2);
    }

}
