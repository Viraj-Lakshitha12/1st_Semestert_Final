package lk.ijse.phone.entity;

public class Item {
    private String itemCode;
    private String itemName;
    private int qtyOnHand;
    private double unitPrice;

    public Item() {
    }

    public Item(String itemCode, String itemName, int qtyOnHand, double unitPrice) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.qtyOnHand = qtyOnHand;
        this.unitPrice = unitPrice;
    }

    public Item(String itemCode, int qty, double unitPrice) {
        this.itemCode = itemCode;
        this.qtyOnHand = qtyOnHand;
        this.unitPrice = unitPrice;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", qtyOnHand=" + qtyOnHand +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
