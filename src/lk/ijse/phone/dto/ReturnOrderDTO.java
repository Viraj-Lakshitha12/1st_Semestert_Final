package lk.ijse.phone.dto;

public class ReturnOrderDTO {
    private String returnOrderId;
    private String itemName;
    private String itemCode;
    private int qty;

    public ReturnOrderDTO(String returnOrderId, String itemName, String itemCode, int qty) {
        this.setReturnOrderId(returnOrderId);
        this.setItemName(itemName);
        this.setItemCode(itemCode);
        this.setQty(qty);
    }

    public ReturnOrderDTO() {
    }


    public String getReturnOrderId() {
        return returnOrderId;
    }

    public void setReturnOrderId(String returnOrderId) {
        this.returnOrderId = returnOrderId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

}

