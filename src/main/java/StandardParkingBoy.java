import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;

import java.util.List;

public class StandardParkingBoy {
    private final List<ParkingLot> parkingLotList;

    public StandardParkingBoy(List<ParkingLot> parkingLotList){
        this.parkingLotList = parkingLotList;
    }
    public ParkingTicket park(Car car) {
        for (ParkingLot parkingLot : parkingLotList) {
            if (parkingLot.getAvailableCapacity() > 0) {
                return parkingLot.park(car);
            }
        }
        return null;
    }
}
