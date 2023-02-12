package lk.ijse.phone.entity;

import lk.ijse.phone.dto.OrderDetailsDTO;
import lk.ijse.phone.dto.PaymentDetailsDTO;

import java.util.ArrayList;

public class Orders {
    private String orderId;
    private String date;
    private String customerId;
    private String salesmen;
    private String status;

    private ArrayList<OrderDetailsDTO> arrayList;

    public Orders() {
    }

    private PaymentDetailsDTO paymentDetailsDTO;

    public Orders(String orderId, String date, String customerId, String salesmen, String status) {
        this.orderId = orderId;
        this.date = date;
        this.customerId = customerId;
        this.salesmen = salesmen;
        this.status = status;

    }


    public Orders(String orderId, String date, String customerId, String salesmen, String status, ArrayList<OrderDetailsDTO> arrayList, PaymentDetailsDTO paymentDetailsDTO) {
        this.orderId = orderId;
        this.date = date;
        this.customerId = customerId;
        this.salesmen = salesmen;
        this.status = status;
        this.arrayList = arrayList;
        this.paymentDetailsDTO = paymentDetailsDTO;
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSalesmen() {
        return salesmen;
    }

    public void setSalesmen(String salesmen) {
        this.salesmen = salesmen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<OrderDetailsDTO> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<OrderDetailsDTO> arrayList) {
        this.arrayList = arrayList;
    }

    public PaymentDetailsDTO getPaymentDetailsDTO() {
        return paymentDetailsDTO;
    }

    public void setPaymentDetailsDTO(PaymentDetailsDTO paymentDetailsDTO) {
        this.paymentDetailsDTO = paymentDetailsDTO;
    }
}


