package lk.ijse.phone.dto;

public class PlaceOrderTmDTO {
    private String OrderId;
    private String itemCode;
    private int qty;
    private double unitPrice;

    private String itemName;

    public PlaceOrderTmDTO(String OrderId, String itemCode, int qty, double unitPrice, double total) {
        this.setOrderId(OrderId);
        this.setItemCode(itemCode);
        this.setQty(qty);
        this.setUnitPrice(unitPrice);
        this.setTotal(total);
    }
    public PlaceOrderTmDTO(String OrderId, String itemCode, int qty, double unitPrice, double total, String itemName) {
        this.setOrderId(OrderId);
        this.setItemCode(itemCode);
        this.setQty(qty);
        this.setUnitPrice(unitPrice);
        this.setTotal(total);
        this.setItemName(itemName);
    }

    public PlaceOrderTmDTO() {
    }

    private double total;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        this.OrderId = orderId;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
