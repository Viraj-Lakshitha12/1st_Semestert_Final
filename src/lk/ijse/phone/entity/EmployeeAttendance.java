package lk.ijse.phone.entity;

public class EmployeeAttendance {
    private String employeeId;
    private String employeeName;
    private String date;
    private String inTime;
    private String outTime;
    private String status;

    public EmployeeAttendance() {
    }

    public EmployeeAttendance(String employeeId, String employeeName, String date, String inTime, String outTime, String status) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.date = date;
        this.inTime = inTime;
        this.outTime = outTime;
        this.status = status;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EmployeeAttendance{" +
                "employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", date='" + date + '\'' +
                ", inTime='" + inTime + '\'' +
                ", outTime='" + outTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
