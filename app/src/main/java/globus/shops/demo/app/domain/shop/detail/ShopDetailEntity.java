package globus.shops.demo.app.domain.shop.detail;

public class ShopDetailEntity {

    private int id;
    private String title;
    private String address;
    private int number;
    private double profit;
    private double latitude;
    private double longitude;
    private long openDate;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getOpenDate() {
        return openDate;
    }

    public void setOpenDate(long openDate) {
        this.openDate = openDate;
    }

    public Status getStatus() {
        switch (status) {
            case 1:
                return Status.OPEN_SOON;
            case 2:
                return Status.OPENING_CLOSED;
            case 3:
                return Status.REPAIR_CLOSED;
            default:
                throw new RuntimeException("Unknown status");
        }
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setStatus(Status status) {
        this.status = status.getValue();
    }
}
