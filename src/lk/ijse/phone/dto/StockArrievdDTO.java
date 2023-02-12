package lk.ijse.phone.dto;

public class StockArrievdDTO {
    private String dateOfArrrievd;
    private String itemCode;
    private String itemName;
    private int qty;
    private String supplierId;
    private String supplierName;

    public StockArrievdDTO(String dateOfArrrievd, String itemCode, String itemName, int qty, String supplierId, String supplierName) {
        this.setDateOfArrrievd(dateOfArrrievd);
        this.setItemCode(itemCode);
        this.setItemName(itemName);
        this.setQty(qty);
        this.setSupplierId(supplierId);
        this.setSupplierName(supplierName);
    }

    public StockArrievdDTO() {
    }

    public String getDateOfArrrievd() {
        return dateOfArrrievd;
    }

    public void setDateOfArrrievd(String dateOfArrrievd) {
        this.dateOfArrrievd = dateOfArrrievd;
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
}
