package lk.ijse.phone.entity;

public class StockArrived {
    private String dateOfArrived;
    private String itemCode;
    private String itemName;
    private int qty;
    private String supplierId;
    private String supplierName;

    public StockArrived(String dateOfArrived, String itemCode, String itemName, int qty, String supplierId, String supplierName) {
        this.dateOfArrived = dateOfArrived;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.qty = qty;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
    }

    public StockArrived() {
    }

    public String getDateOfArrived() {
        return dateOfArrived;
    }

    public void setDateOfArrived(String dateOfArrived) {
        this.dateOfArrived = dateOfArrived;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return "StockArrived{" +
                "dateOfArrived='" + dateOfArrived + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", qty=" + qty +
                ", supplierId='" + supplierId + '\'' +
                ", supplierName='" + supplierName + '\'' +
                '}';
    }
}
