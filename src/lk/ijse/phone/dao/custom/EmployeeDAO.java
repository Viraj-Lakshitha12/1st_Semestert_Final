package lk.ijse.phone.dao.custom;

import lk.ijse.phone.dao.CrudDAO;
import lk.ijse.phone.dto.EmployeeDTO;
import lk.ijse.phone.entity.Employee;
import lk.ijse.phone.entity.EmployeeAttendance;
import lk.ijse.phone.service.custom.EmployeeService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends CrudDAO<Employee,String> {
    ArrayList<String> loadAllEmployeeNames() throws SQLException, ClassNotFoundException;
    int getEmployeeAttendanceCount(String date)throws SQLException, ClassNotFoundException;
}
