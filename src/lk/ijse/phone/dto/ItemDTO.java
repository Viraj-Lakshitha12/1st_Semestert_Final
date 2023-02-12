package lk.ijse.phone.dto;

public class ItemDTO {
    private String itemCode;
    private String itemName;
    private int qtyOnHand;
    private double unitPrice;


    public ItemDTO() {
    }
    public ItemDTO(String itemName, double unitPrice, int qtyOnHand) {
        this.setItemName(itemName);
        this.setQtyOnHand(qtyOnHand);
        this.setUnitPrice(unitPrice);
    }


    public ItemDTO(int qtyOnHand){
        this.setQtyOnHand(qtyOnHand);
    }
    public ItemDTO(String itemCode, String itemName, int qtyOnHand, double unitPrice) {
        this.setItemCode(itemCode);
        this.setItemName(itemName);
        this.setQtyOnHand(qtyOnHand);
        this.setUnitPrice(unitPrice);
    }

    public ItemDTO(String itemCode, int qty) {
        this.setItemCode(itemCode);
        this.setQtyOnHand(qtyOnHand);
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
}
