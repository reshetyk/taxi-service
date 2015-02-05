package taxiservice;

/**
 * @author alre
 */
public class TaxiOrder {
    private TaxiDriver taxiDriver;
    private District district;
    private String address;

    public TaxiOrder(District district, String address) {
        this.district = district;
        this.address = address;
    }

    public District getDistrict() {
        return district;
    }

    public void setTaxiDriver(TaxiDriver taxiDriver) {
        this.taxiDriver = taxiDriver;
    }

    public TaxiDriver getTaxiDriver() {
        return taxiDriver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxiOrder taxiOrder = (TaxiOrder) o;

        if (address != null ? !address.equals(taxiOrder.address) : taxiOrder.address != null) return false;
        if (district != taxiOrder.district) return false;
        if (taxiDriver != null ? !taxiDriver.equals(taxiOrder.taxiDriver) : taxiOrder.taxiDriver != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taxiDriver != null ? taxiDriver.hashCode() : 0;
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

}
