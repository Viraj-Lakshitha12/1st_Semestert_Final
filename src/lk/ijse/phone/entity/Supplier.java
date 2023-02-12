package lk.ijse.phone.entity;

public class Supplier {
    private String supplierId;
    private String name;
    private String brand;
    private String address;
    private String contact;

    public Supplier() {
    }

    public Supplier(String supplierId, String name, String brand, String address, String contact) {
        this.supplierId = supplierId;
        this.name = name;
        this.brand = brand;
        this.address = address;
        this.contact = contact;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId='" + supplierId + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
