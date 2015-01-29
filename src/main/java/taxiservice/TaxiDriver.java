package taxiservice;

import javax.sound.midi.MidiDevice;

/**
 * @author alre
 */
public class TaxiDriver {
    private District district;

    public TaxiDriver(District district) {
        this.district = district;
    }

    public District getDistrict() {
        return district;
    }

}
