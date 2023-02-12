package lk.ijse.phone.dto;


import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class PendingOrderTmDTO {
    private String orderId;
    private String date;
    private String customerId;
    private String salesmen;
    private String status;
    private ComboBox comboBox;

    public PendingOrderTmDTO(String orderId, String date, String customerId, String salesmen, String status, ComboBox comboBox, Button button) {
        this.setOrderId(orderId);
        this.setDate(date);
        this.setCustomerId(customerId);
        this.setSalesmen(salesmen);
        this.setStatus(status);
        this.setComboBox(comboBox);
        this.setButton(button);
    }
    public PendingOrderTmDTO(String orderId, String date, String customerId, String salesmen, String status) {
        this.setOrderId(orderId);
        this.setDate(date);
        this.setCustomerId(customerId);
        this.setSalesmen(salesmen);
        this.setStatus(status);
    }

    private Button button ;

    public PendingOrderTmDTO() {
    }

    public PendingOrderTmDTO(String orderId, String date, String customerId, String salesmen, ComboBox comboBox) {
        this.setOrderId(orderId);
        this.setDate(date);
        this.setCustomerId(customerId);
        this.setSalesmen(salesmen);
        this.setComboBox(comboBox);
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

    public ComboBox getComboBox() {
        return comboBox;
    }

    public void setComboBox(ComboBox comboBox) {
        this.comboBox = comboBox;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
