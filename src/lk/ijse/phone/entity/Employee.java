package lk.ijse.phone.entity;

public class Employee {
    private String employeeId;
    private String employeeName;
    private String address;
    private double salary;

    public Employee(String employeeId, String employeeName, String address, double salary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.address = address;
        this.salary = salary;
    }

    public Employee() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                '}';
    }
}
