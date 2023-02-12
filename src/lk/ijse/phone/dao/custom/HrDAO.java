package lk.ijse.phone.dao.custom;

import lk.ijse.phone.dao.CrudDAO;
import lk.ijse.phone.dto.EmployeeAttendanceDTO;
import lk.ijse.phone.entity.EmployeeAttendance;

import java.sql.SQLException;

public interface HrDAO extends CrudDAO<EmployeeAttendance,String> {
    int SearchAttendanceCount(String date) throws SQLException, ClassNotFoundException;
}
