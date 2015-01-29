package taxiservice;

/**
 * @author alre
 */
public class TaxiOrder {
    private District district;

    public TaxiOrder(District district) {
        this.district = district;
    }

    public District getDistrict() {
        return district;
    }
}
