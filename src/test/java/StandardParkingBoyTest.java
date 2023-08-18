import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class StandardParkingBoyTest {

    @Test
    void should_park_to_first_parking_lot_when_park_given_a_standard_parking_boy_and_two_parking_lots_and_a_car(){
        //Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List <ParkingLot> parkingLots = List.of(firstParkingLot);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        Car car = new Car();

        //When
        ParkingTicket parkingTicket = standardParkingBoy.park(car);

        assertNotNull(parkingTicket);
        assertEquals(9,firstParkingLot.getAvailableCapacity());
        assertEquals(10,secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_park_in_second_parking_lot_when_first_is_full_given_standard_parking_boy_two_parking_lots_and_car() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        Car car = new Car();

        // Fill the first parking lot
        firstParkingLot.park(new Car());

        // When
        ParkingTicket parkingTicket = standardParkingBoy.park(car);

        // Then
        assertNotNull(parkingTicket);
        assertEquals(0, firstParkingLot.getAvailableCapacity());
        assertEquals(9, secondParkingLot.getAvailableCapacity());
    }

    @Test
    void should_return_correct_car_with_each_ticket_when_fetch_given_standard_parking_boy_two_parked_cars_and_tickets() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);

        Car car1 = new Car();
        Car car2 = new Car();

        ParkingTicket ticket1 = standardParkingBoy.park(car1);
        ParkingTicket ticket2 = standardParkingBoy.park(car2);

        // When
        Car fetchedCar1 = standardParkingBoy.fetch(ticket1);
        Car fetchedCar2 = standardParkingBoy.fetch(ticket2);

        // Then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }


    @Test
    void should_throw_unrecognized_ticket_exception_when_fetch_given_unrecognized_ticket() {
        // Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);

        ParkingTicket unrecognizedTicket = new ParkingTicket(); // Create an unrecognized ticket

        // When
        //Then
        assertThrows(UnrecognizedTicketException.class, () -> {
            standardParkingBoy.fetch(unrecognizedTicket);
        });
    }
}
