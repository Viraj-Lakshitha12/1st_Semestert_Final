package lk.ijse.phone.entity;

public class PaymentDetails {
    private String orderId;
    private String customerId;
    private double balance;
    private String date;
    private String time;

    public PaymentDetails() {
    }

    public PaymentDetails(String orderId, String customerId, double balance, String date, String time) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.balance = balance;
        this.date = date;
        this.time = time;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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
        return "PaymentDetails{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", balance=" + balance +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
