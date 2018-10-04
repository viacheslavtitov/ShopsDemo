package globus.shops.demo.app.domain.shop.detail;

public enum Status {

    OPEN_SOON(1), OPENING_CLOSED(2), REPAIR_CLOSED(3);
    private final int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
