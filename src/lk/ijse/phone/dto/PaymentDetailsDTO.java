package lk.ijse.phone.dto;

public class PaymentDetailsDTO {
    private String orderId;
    private String customerId;
    private double balance;
    private String date;
    private String time;

    public PaymentDetailsDTO(String orderId, String customerId, double balance, String date, String time) {
        this.setOrderId(orderId);
        this.setCustomerId(customerId);
        this.setBalance(balance);
        this.setDate(date);
        this.setTime(time);
    }

    public PaymentDetailsDTO(double balance) {
        this.setBalance(balance);
    }
    public PaymentDetailsDTO() {
    }

    public PaymentDetailsDTO(String orderId, String customerId, double balance) {
        this.setOrderId(orderId);
        this.setCustomerId(customerId);
        this.setBalance(balance);
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
}