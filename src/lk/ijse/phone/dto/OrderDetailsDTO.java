package lk.ijse.phone.dto;

public class OrderDetailsDTO {
     private String  orderId;
     private String  itemCode;
     private int   qty ;
     private double   unitPrice ;
     private String itemName;

    private double price;

    public OrderDetailsDTO() {
    }



    public OrderDetailsDTO(String orderId, String itemCode, int qty, double unitPrice, double total, String itemName) {
        this.setOrderId(orderId);
        this.setItemCode(itemCode);
        this.setQty(qty);
        this.setUnitPrice(unitPrice);
        this.setPrice(total);
        this.setItemName(itemName);
    }

    public OrderDetailsDTO(String orderId, String itemCode, int qty, double unitPrice, double total) {
        this.setOrderId(orderId);
        this.setItemCode(itemCode);
        this.setQty(qty);
        this.setUnitPrice(unitPrice);
        this.setPrice(total);
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String orderId) {
        this.itemName = itemName;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
