package taxiservice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alre
 */
public class TaxiService {

    private List<TaxiDriver> availableDrivers = new ArrayList<>();

    public ClientAnswer callTaxi(TaxiOrder taxiOrder) {
        for (TaxiDriver taxiDriver : availableDrivers) {
            if (taxiDriver.getDistrict().equals(taxiOrder.getDistrict())) {
                return ClientAnswer.SUCCESS;
            }
        }

        return ClientAnswer.NO_CARS;
    }

    public void registerDriver(TaxiDriver taxiDriver) {
        availableDrivers.add(taxiDriver);
    }
}
