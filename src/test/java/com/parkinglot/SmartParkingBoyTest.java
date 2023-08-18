package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void should_park_car_in_second_parking_lot_when_first_is_full_given_smart_parking_boy_two_parking_lots_and_car() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot(1);
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
        assertEquals(car, secondParkingLot.fetch(parkingTicket));
    }

    @Test
    void should_return_correct_cars_with_each_ticket_when_fetch_given_smart_parking_boy_two_parking_lots_and_cars() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Car car1 = new Car();
        Car car2 = new Car();

        ParkingTicket ticket1 = smartParkingBoy.park(car1);
        ParkingTicket ticket2 = smartParkingBoy.park(car2);

        // When
        Car fetchedCar1 = smartParkingBoy.fetch(ticket1);
        Car fetchedCar2 = smartParkingBoy.fetch(ticket2);

        // Then
        assertNotNull(fetchedCar1);
        assertNotNull(fetchedCar2);
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }

    @Test
    void should_throw_unrecognized_ticket_exception_when_fetch_given_unrecognized_ticket_given_Smart_Parking_Boy() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        ParkingTicket unrecognizedTicket = new ParkingTicket();

        // When and Then
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class, () -> {
            smartParkingBoy.fetch(unrecognizedTicket);
        });

        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_throw_unrecognized_ticket_exception_when_fetch_given_used_ticket_given_smart_parking_boy() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);

        smartParkingBoy.fetch(parkingTicket);

        // When and Then
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class, () -> {
            smartParkingBoy.fetch(parkingTicket);
        });

        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_throw_no_available_position_exception_when_park_given_no_available_capacity() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        smartParkingBoy.park(new Car());
        smartParkingBoy.park(new Car());
        Car car = new Car();

        // When and Then
        NoAvailablePositionException exception = assertThrows(
                NoAvailablePositionException.class,
                () -> smartParkingBoy.park(car)
        );
        assertEquals("No available position", exception.getMessage());
    }


}
