package lk.ijse.phone.entity;

public class Payment {
    private String paymentId;
    private String orderId;
    private double total;
    private double totalBalance;

    public Payment() {
    }

    public Payment(String paymentId, String orderId, double total, double totalBalance) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.total = total;
        this.totalBalance = totalBalance;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", total=" + total +
                ", totalBalance=" + totalBalance +
                '}';
    }
}
