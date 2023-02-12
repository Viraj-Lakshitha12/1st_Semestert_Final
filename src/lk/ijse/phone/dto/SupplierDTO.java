package lk.ijse.phone.dto;

public class SupplierDTO {
    private String sid;
    private String name;
    private String brand;
    private String address;
    private String ContactNo;

    public SupplierDTO(String sid, String name, String brand, String address, String contactNo) {
        this.setSid(sid);
        this.setName(name);
        this.setBrand(brand);
        this.setAddress(address);
        setContactNo(contactNo);
    }

    public SupplierDTO() {
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }
}
