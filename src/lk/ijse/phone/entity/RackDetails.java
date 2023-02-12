package lk.ijse.phone.entity;

public class RackDetails {
    private String rackNumber;
    private String itemCode;
    private String itemName;

    public RackDetails() {
    }

    public RackDetails(String rackNumber, String itemCode, String itemName) {
        this.rackNumber = rackNumber;
        this.itemCode = itemCode;
        this.itemName = itemName;
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

    @Override
    public String toString() {
        return "RackDetails{" +
                "rackNumber='" + rackNumber + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}
