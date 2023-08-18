import com.parkinglot.Car;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.exception.NoAvailablePositionException;


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
        throw new NoAvailablePositionException();
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLotList.stream()
                .map(parkingLot -> parkingLot.fetch(parkingTicket))
                .filter(car -> car != null)
                .findFirst()
                .orElse(null);
    }
}
