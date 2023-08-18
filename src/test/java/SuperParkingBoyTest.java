import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.SuperParkingBoy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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


}
