package taxiservice;

/**
 * @author alre
 */
public class ClientAnswer {
    public enum Result {
        SUCCESS, NO_CARS
    }
    private Result result;
    private int fare;
    private String carNumber;
    private int timeArrival;

    public ClientAnswer(Result result, Integer fare, String carNumber, int timeArrival) {
        this.result = result;
        this.fare = fare;
        this.carNumber = carNumber;
        this.timeArrival = timeArrival;
    }

    public ClientAnswer(Result result) {
        this.result = result;
    }

    public ClientAnswer() {
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public int getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(int timeArrival) {
        this.timeArrival = timeArrival;
    }
}
