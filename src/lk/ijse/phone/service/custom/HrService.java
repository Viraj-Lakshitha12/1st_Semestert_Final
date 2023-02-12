package lk.ijse.phone.service.custom;

import lk.ijse.phone.service.SuperService;
import lk.ijse.phone.dto.EmployeeAttendanceDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface HrService extends SuperService {
    ArrayList<EmployeeAttendanceDTO> getAllEmployee() throws SQLException, ClassNotFoundException;
    boolean saveEmployeeAttendance(EmployeeAttendanceDTO employeeAttendanceDTO) throws SQLException, ClassNotFoundException;
    boolean updateEmployeeAttendance(EmployeeAttendanceDTO employeeAttendanceDTO) throws SQLException,ClassNotFoundException;
}
