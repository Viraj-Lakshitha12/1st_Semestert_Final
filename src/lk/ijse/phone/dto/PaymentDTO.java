package lk.ijse.phone.dto;

import java.util.ArrayList;

public class PaymentDTO {
    private String paymentId;
    private String orderid;
    private double total;
    private double totalBalance;

    public PaymentDTO(String paymentId, String orderid, double total, double totalBalance, ArrayList<PaymentDetailsDTO> arrayList) {
        this.setPaymentId(paymentId);
        this.setOrderid(orderid);
        this.setTotal(total);
        this.setTotalBalance(totalBalance);
        this.setArrayList(arrayList);
    }

    public PaymentDTO() {
    }

    private ArrayList<PaymentDetailsDTO> arrayList;

    public PaymentDTO(String paymentid, String orderId, double price, double total) {
        this.setPaymentId(paymentid);
        this.setOrderid(orderId);
        this.setTotal(price);
        this.setTotalBalance(total);
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
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

    public ArrayList<PaymentDetailsDTO> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<PaymentDetailsDTO> arrayList) {
        this.arrayList = arrayList;
    }
}

