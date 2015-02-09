package taxiservice;

/**
 * @author alre
 */
public class TaxiDriver {
    private District currentLocation;
    private String carNumber;
    private TaxiOrder taxiOrder;

    public TaxiDriver(District currentLocation, String carNumber) {
        this.currentLocation = currentLocation;
        this.carNumber = carNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setTaxiOrder(TaxiOrder taxiOrder) {
        this.taxiOrder = taxiOrder;
    }

    public TaxiOrder getTaxiOrder() {
        return taxiOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxiDriver that = (TaxiDriver) o;

        if (carNumber != null ? !carNumber.equals(that.carNumber) : that.carNumber != null) return false;
        if (currentLocation != that.currentLocation) return false;
        if (taxiOrder != null ? !taxiOrder.equals(that.taxiOrder) : that.taxiOrder != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = currentLocation != null ? currentLocation.hashCode() : 0;
        result = 31 * result + (carNumber != null ? carNumber.hashCode() : 0);
        result = 31 * result + (taxiOrder != null ? taxiOrder.hashCode() : 0);
        return result;
    }
}
