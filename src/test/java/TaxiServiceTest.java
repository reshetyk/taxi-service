import org.junit.Test;
import taxiservice.*;

import static org.junit.Assert.assertEquals;

/**
 * @author alre
 */
public class TaxiServiceTest {

    /**
     * USER STORY:
     *
     * As a CLIENT of a taxi service I want to order a taxi and receive an answer with one of the result:
     * SUCCESS - if there are cars available in specific district
     * NO CARS - if there are no cars available in specific district
     * OPERATORS ARE BUSY - if all call center operators are busy at the moment
     */


    @Test
    public void clientCallsTaxiAndReceivePositiveAnswer () {
        TaxiService taxiService = new TaxiService();

        taxiService.registerDriver(new TaxiDriver(District.DISTRICT_1));

        ClientAnswer answer = taxiService.callTaxi(new TaxiOrder (District.DISTRICT_1));

        assertEquals(ClientAnswer.SUCCESS, answer);
    }

    @Test
    public void clientCallsTaxiAndReceiveNoCarsAnswer () {
        TaxiService taxiService = new TaxiService();

        taxiService.registerDriver(new TaxiDriver(District.DISTRICT_1));

        ClientAnswer answer = taxiService.callTaxi(new TaxiOrder (District.DISTRICT_2));

        assertEquals(ClientAnswer.NO_CARS, answer);
    }

    @Test
    public void clientCallsTaxiAndReceiveAllOperatorsBusyAnswer () {

    }

    /**
     * USER STORY:
     *
     * As a DRIVER of a taxi service I want to accept an order and receive an answer with one of the result:
     * ACCEPTED - if driver is located in specific district
     * ORDER_ALREADY_TAKEN - if the order already taken by another driver
     */

    @Test
    public void driverAcceptsOrderAndReceiveAcceptedAnswer () {

    }

    @Test
    public void driverAcceptsOrderAndReceiveOrderAlreadyTakenAnswer () {

    }

    @Test
    public void driverFulfilledOrder () {

    }

    @Test
    public void driverRegisterHimself () {

    }

    @Test
    public void driverDeregisterHimself () {

    }
}
