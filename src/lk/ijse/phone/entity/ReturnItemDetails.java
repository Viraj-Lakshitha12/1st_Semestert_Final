package lk.ijse.phone.entity;

public class ReturnItemDetails {
    private String orderId;
    private String date;
    private String time;

    public ReturnItemDetails() {
    }

    public ReturnItemDetails(String orderId, String date, String time) {
        this.orderId = orderId;
        this.date = date;
        this.time = time;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ReturnItemDetails{" +
                "orderId='" + orderId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
