import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.SuperParkingBoy;
import com.parkinglot.exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SuperParkingBoyTest {
    @Test
    void should_park_car_in_lot_with_larger_available_position_rate() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot = new ParkingLot(15);
        ParkingLot thirdParkingLot = new ParkingLot(20);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(firstParkingLot);
        parkingLots.add(secondParkingLot);
        parkingLots.add(thirdParkingLot);

        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);
        Car car = new Car();

        // When
         superParkingBoy.park(car);

        // Then
        double rateFirst = (double) firstParkingLot.getAvailableCapacity() / firstParkingLot.getTotalCapacity();
        double rateSecond = (double) secondParkingLot.getAvailableCapacity() / secondParkingLot.getTotalCapacity();
        double rateThird = (double) thirdParkingLot.getAvailableCapacity() / thirdParkingLot.getTotalCapacity();


        if (rateFirst >= rateSecond && rateFirst >= rateThird) {
            assertEquals(rateFirst, superParkingBoy.getParkingLotWithHighestRate().getAvailablePositionRate());
        } else if (rateSecond >= rateFirst && rateSecond >= rateThird) {
            assertEquals(rateSecond, superParkingBoy.getParkingLotWithHighestRate().getAvailablePositionRate());
        } else {
            assertEquals(rateThird, superParkingBoy.getParkingLotWithHighestRate().getAvailablePositionRate());
        }
    }

    @Test
    void should_park_car_in_first_parking_lot_when_both_have_available_positions_given_super_parking_boy() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();

        firstParkingLot.park(new Car());
        new SuperParkingBoy(List.of(firstParkingLot, secondParkingLot));

        // When


        // Then
        assertEquals(9, firstParkingLot.getAvailableCapacity());
        assertEquals(10,secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_park_car_in_second_parking_lot_when_first_is_full_given_super_parking_boy() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot();

        firstParkingLot.park(new Car());
        firstParkingLot.park(new Car());
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(List.of(firstParkingLot, secondParkingLot));
        Car car = new Car();

        // When
        ParkingTicket parkingTicket = superParkingBoy.park(car);

        // Then
        assertEquals(0, firstParkingLot.getAvailableCapacity());
        assertEquals(9, secondParkingLot.getAvailableCapacity());
        assertEquals(car, secondParkingLot.fetch(parkingTicket));
    }

    @Test
    void should_return_right_car_with_each_ticket_given_super_parking_boy() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(List.of(firstParkingLot, secondParkingLot));

        Car car1 = new Car();
        Car car2 = new Car();

        ParkingTicket ticket1 = superParkingBoy.park(car1);
        ParkingTicket ticket2 = superParkingBoy.park(car2);

        // When
        Car fetchedCar1 = superParkingBoy.fetch(ticket1);
        Car fetchedCar2 = superParkingBoy.fetch(ticket2);

        // Then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }

    @Test
    void should_return_error_message_for_unrecognized_ticket_given_super_parking_boy() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot = new ParkingLot(10);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(List.of(firstParkingLot, secondParkingLot));

        Car car = new Car();
        ParkingTicket validTicket = superParkingBoy.park(car);
        ParkingTicket unrecognizedTicket = new ParkingTicket();

        // When
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class, () -> {
            superParkingBoy.fetch(unrecognizedTicket);
        });

        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_error_message_for_used_ticket_given_super_parking_boy() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot = new ParkingLot(10);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(List.of(firstParkingLot, secondParkingLot));

        Car car = new Car();
        ParkingTicket parkingTicket = superParkingBoy.park(car);


        superParkingBoy.fetch(parkingTicket);

        ParkingTicket unrecognizedTicket = new ParkingTicket();

        // When
        UnrecognizedTicketException exception = assertThrows(UnrecognizedTicketException.class, () -> {
            superParkingBoy.fetch(unrecognizedTicket);
        });

        // Then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }


}
