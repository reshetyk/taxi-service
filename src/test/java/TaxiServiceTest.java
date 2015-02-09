import org.junit.Test;
import taxiservice.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author alre
 */
public class TaxiServiceTest {

    /**
     * USER STORY:
     * <p/>
     * As a client of a taxi service I want to order a taxi and receive an answer with one of the result:
     * <p/>
     * - SUCCESS - if one free driver accepted the order (in this case an answer also contains car number, arrival time and fare)
     * <p/>
     * - NO CARS - if no one driver accepted the order during 5 sec
     */


    /**
     * Check SUCCESS result if one free driver accepted the order (in this case an answer also contains car number, arrival time and fare)
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void clientCallsTaxiAndReceivesPositiveAnswer() throws ExecutionException, InterruptedException {
        TaxiService taxiService = new TaxiService();
        Future<ClientAnswer> futureAnswer = taxiService.callTaxi(new TaxiOrder(District.DISTRICT_1, "b.Shevchenko 91/45"));
        taxiService.registerDriver(new TaxiDriver(District.DISTRICT_1, "AE01234BB"));
        ClientAnswer answer = futureAnswer.get();

        assertEquals(ClientAnswer.Result.SUCCESS, answer.getResult());
        assertEquals("AE01234BB", answer.getCarNumber());
        assertEquals(10, answer.getTimeArrival());
        assertEquals(50, answer.getFare());
    }

    /**
     * Check NO_CARS result if no one driver accepted the order during 5 sec
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void clientCallsTaxiAndReceivesNoCarsAnswer() throws ExecutionException, InterruptedException {
        TaxiService taxiService = new TaxiService();
        Future<ClientAnswer> futureAnswer = taxiService.callTaxi(new TaxiOrder(District.DISTRICT_2, "b.Shevchenko 91/45"));
        taxiService.registerDriver(new TaxiDriver(District.DISTRICT_2, "AE01234BB"));
        ClientAnswer answer = futureAnswer.get();

        assertEquals(ClientAnswer.Result.NO_CARS, answer.getResult());
        assertNull(answer.getCarNumber());
        assertEquals(0, answer.getTimeArrival());
        assertEquals(0, answer.getFare());
    }

    @Test
    public void clientCallsTaxiAndReceivesAllOperatorsBusyAnswer() {

    }

    /**
     * USER STORY:
     *
     * As a DRIVER of a taxi service I want to accept an order and receive an answer with one of the result:
     * ACCEPTED - if driver successfully took the order
     * ORDER_ALREADY_TAKEN - if the order already taken by another driver
     */

    @Test
    public void driverAcceptsOrderAndReceivesAcceptedAnswer() {
        TaxiService taxiService = new TaxiService();

        TaxiDriver driver = new TaxiDriver(District.DISTRICT_1, "AE1123BB");
        taxiService.registerDriver(driver);

        TaxiOrder taxiOrder = new TaxiOrder(District.DISTRICT_1, "b.Shevchenko 91/45");
        taxiService.callTaxi(taxiOrder);

        DriverAnswer driverAnswer = taxiService.acceptOrder(driver, taxiOrder);

        assertEquals(DriverAnswer.ACCEPTED, driverAnswer);
        assertEquals(taxiOrder, driver.getTaxiOrder());
    }

    @Test
    public void driverAcceptsOrderAndReceiveOrderAlreadyTakenAnswer() {
        TaxiService taxiService = new TaxiService();

        TaxiDriver driver = new TaxiDriver(District.DISTRICT_1, "AE1123BB");
        taxiService.registerDriver(driver);

        TaxiDriver driver2 = new TaxiDriver(District.DISTRICT_2, "BB2233AE");
        taxiService.registerDriver(driver2);

        TaxiOrder taxiOrder = new TaxiOrder(District.DISTRICT_1, "b.Shevchenko 91/45");
        taxiService.callTaxi(taxiOrder);

        DriverAnswer driverAnswer = taxiService.acceptOrder(driver, taxiOrder);

        assertEquals(DriverAnswer.ACCEPTED, driverAnswer);
        assertEquals(taxiOrder, driver.getTaxiOrder());

        DriverAnswer driverAnswer2 = taxiService.acceptOrder(driver, taxiOrder);
        assertEquals(DriverAnswer.ALREADY_TAKEN, driverAnswer2);
    }

    @Test
    public void driverFulfilledOrder() {

    }

    @Test
    public void driverRegisterHimself() {

    }

    @Test
    public void driverDeregisterHimself() {

    }
}
