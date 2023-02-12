package lk.ijse.phone.dto;

import java.util.ArrayList;

public class OrderDTO {
    private String orderId;
    private String date;
    private String customerId;
    private String salesmen;
    private String status;

    private ArrayList<OrderDetailsDTO> arrayList;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, String date, String customerId, String salesmen, String status, ArrayList<OrderDetailsDTO> arrayList, PaymentDetailsDTO paymentDetailsDTO) {
        this.setOrderId(orderId);
        this.setDate(date);
        this.setCustomerId(customerId);
        this.setSalesmen(salesmen);
        this.setStatus(status);
        this.setArrayList(arrayList);
        this.setPaymentDetails(paymentDetailsDTO);
    }

    private PaymentDetailsDTO paymentDetailsDTO;

    public OrderDTO(String orderId, String date, String customerId, String salesmen) {
        this.setOrderId(orderId);
        this.setDate(date);
        this.setCustomerId(customerId);
        this.setSalesmen(salesmen);
    }

    public OrderDTO(String orderId, String date, String customerId, String salesmen, ArrayList<OrderDetailsDTO> orderDetailDTOS, PaymentDetailsDTO paymentDetailsDTO) {
        this.setOrderId(orderId);
        this.setDate(date);
        this.setCustomerId(customerId);
        this.setSalesmen(salesmen);
        this.setArrayList(arrayList);
        this.setPaymentDetails(paymentDetailsDTO);
    }

    public OrderDTO(String orderId, String date, String customerId, String salesmen, String status)  {
        this.setOrderId(orderId);
        this.setDate(date);
        this.setCustomerId(customerId);
        this.setSalesmen(salesmen);
        this.setStatus(status);
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

    public PaymentDetailsDTO getPaymentDetails() {
        return paymentDetailsDTO;
    }

    public void setPaymentDetails(PaymentDetailsDTO paymentDetailsDTO) {
        this.paymentDetailsDTO = paymentDetailsDTO;
    }
}


