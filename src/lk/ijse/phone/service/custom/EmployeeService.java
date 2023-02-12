package lk.ijse.phone.service.custom;

import lk.ijse.phone.service.SuperService;
import lk.ijse.phone.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface EmployeeService extends SuperService {
    boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException ,ClassNotFoundException;
    boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;
    Optional<EmployeeDTO> searchEmployee(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> loadAllEmployeeNames() throws SQLException, ClassNotFoundException;
    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;
    ArrayList<EmployeeDTO> loadAllEmployeeData() throws SQLException, ClassNotFoundException;

    int getEmployeeAttendanceCount(String date) throws SQLException,ClassNotFoundException;
}
