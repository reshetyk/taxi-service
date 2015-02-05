package taxiservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author alre
 */
public class TaxiService {
    private List<TaxiDriver> taxiDrivers = new ArrayList<>();
//    private List<TaxiOrder> availableOrders = new ArrayList<>();

    public Future<ClientAnswer> callTaxi(final TaxiOrder taxiOrder) {
        final Callable<ClientAnswer> callable = new Callable<ClientAnswer>() {
            @Override
            public ClientAnswer call() throws Exception {
                ClientAnswer clientAnswer = new ClientAnswer(ClientAnswer.Result.NO_CARS);

                for (int i = 0; i <= 5; i++) {

                    for (TaxiDriver driver : taxiDrivers) {
                        if (isAcceptedOrder(driver, taxiOrder)) {
                            makeClientAnswer(taxiOrder, clientAnswer, driver);
                            return clientAnswer;
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }

                return clientAnswer;

            }

        };
        ExecutorService executor = Executors.newSingleThreadExecutor();
        return executor.submit(callable);
    }
    private void makeClientAnswer(TaxiOrder taxiOrder, ClientAnswer clientAnswer, TaxiDriver taxiDriver) {
        clientAnswer.setResult(ClientAnswer.Result.SUCCESS);
        clientAnswer.setCarNumber(taxiDriver.getCarNumber());
        clientAnswer.setTimeArrival(defineTimeArrival(taxiDriver, taxiOrder));
        clientAnswer.setFare(defineFare(taxiDriver, taxiOrder));
    }

    private int defineFare(TaxiDriver taxiDriver, TaxiOrder taxiOrder) {
        return 50;
    }

    private int defineTimeArrival(TaxiDriver taxiDriver, TaxiOrder taxiOrder) {
        return 10;
    }

    public void registerDriver(TaxiDriver taxiDriver) {
        taxiDrivers.add(taxiDriver);
    }


    public DriverAnswer acceptOrder(TaxiDriver driver, TaxiOrder taxiOrder) {
        if (taxiOrder.getTaxiDriver() != null) {
            return DriverAnswer.ALREADY_TAKEN;
        }
        driver.setTaxiOrder(taxiOrder);
        taxiOrder.setTaxiDriver(driver);
        return DriverAnswer.ACCEPTED;
    }

    public boolean isAcceptedOrder (TaxiDriver driver, TaxiOrder order) {
        if (driver.getTaxiOrder() == null || order.getTaxiDriver() == null)
            return false;

        if (driver.getTaxiOrder().equals(order) && order.getTaxiDriver().equals(driver))
            return true;

        return false;
    }
}
