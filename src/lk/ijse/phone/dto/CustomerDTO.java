package lk.ijse.phone.dto;

public class CustomerDTO {
    private String customerId;
    private String customerName;
    private String address;
    private String phoneNumber;
    public CustomerDTO(String customerId, String customerName, String address, String phoneNumber) {
        this.setCustomerId(customerId);
        this.setCustomerName(customerName);
        this.setAddress(address);
        this.setPhoneNumber(phoneNumber);
    }

    public CustomerDTO() {
    }



    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
