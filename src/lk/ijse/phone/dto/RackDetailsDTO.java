package lk.ijse.phone.dto;

public class RackDetailsDTO {
    private String rackNumber;
    private String itemCode;
    private String itemName;

    public RackDetailsDTO(String rackNumber, String itemCode, String itemName) {
        this.setRackNumber(rackNumber);
        this.setItemCode(itemCode);
        this.setItemName(itemName);
    }

    public RackDetailsDTO() {
    }

    public String getRackNumber() {
        return rackNumber;
    }

    public void setRackNumber(String rackNumber) {
        this.rackNumber = rackNumber;
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
}
