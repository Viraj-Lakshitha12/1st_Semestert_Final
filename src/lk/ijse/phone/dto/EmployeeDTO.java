package lk.ijse.phone.dto;

public class EmployeeDTO {
    private String employeeId;
    private String name;
    private String address;
    private double salary;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String employeeId, String name, String address, double salary) {
        this.setEmployeeId(employeeId);
        this.setName(name);
        this.setAddress(address);
        this.setSalary(salary);
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

