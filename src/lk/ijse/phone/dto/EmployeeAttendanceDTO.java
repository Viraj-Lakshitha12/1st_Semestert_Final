package lk.ijse.phone.dto;

public class EmployeeAttendanceDTO {
    private String employeeId;
    private String employeename;
    private String date;
    private String inTime;
    private String outTime;

    public EmployeeAttendanceDTO(String employeeId, String employeename, String date, String inTime, String outTime, String status) {
        this.setEmployeeId(employeeId);
        this.setEmployeename(employeename);
        this.setDate(date);
        this.setInTime(inTime);
        this.setOutTime(outTime);
        this.setStatus(status);
    }

    private String status;

    public EmployeeAttendanceDTO() {
    }


    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
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
}
