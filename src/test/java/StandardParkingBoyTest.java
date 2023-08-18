import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
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
}
