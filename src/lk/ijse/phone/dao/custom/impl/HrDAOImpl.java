package lk.ijse.phone.dao.custom.impl;

import lk.ijse.phone.dao.custom.HrDAO;
import lk.ijse.phone.dto.EmployeeAttendanceDTO;
import lk.ijse.phone.entity.EmployeeAttendance;
import lk.ijse.phone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class HrDAOImpl implements HrDAO {
    @Override
    public boolean save(EmployeeAttendance employeeAttendance) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO employeeAttendance VALUES(?,?,?,?,?,?)",
                employeeAttendance.getEmployeeId(),
                employeeAttendance.getEmployeeName(),
                employeeAttendance.getDate(),
                employeeAttendance.getInTime(),
                employeeAttendance.getOutTime(),
                employeeAttendance.getStatus()
        );
    }

    @Override
    public boolean update(EmployeeAttendance employeeAttendance) throws SQLException, ClassNotFoundException {
        boolean i = CrudUtil.execute("UPDATE EmployeeAttendance SET employeeName = ?, date = ? inTime = ? , outTime= ?," +
                        "status=? WHERE employeeId =? ",
                employeeAttendance.getEmployeeName(),
                employeeAttendance.getDate(),
                employeeAttendance.getInTime(),
                employeeAttendance.getOutTime(),
                employeeAttendance.getStatus(),
                employeeAttendance.getEmployeeId()
        );
        return i;
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<EmployeeAttendance> findByPk(String pk) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public ArrayList<EmployeeAttendance> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM EmployeeAttendance ");
        ArrayList<EmployeeAttendance> arrayList = new ArrayList<>();
        while (rst.next()){
            arrayList.add(new EmployeeAttendance(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getString(4),rst.getString(5),rst.getString(6)));
        }
        return arrayList;
    }

    @Override
    public int SearchAttendanceCount(String date) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM EmployeeAttendance WHERE date =?",date);
        int count = 0;
        ArrayList arrayList = new ArrayList<>();
        while (rst.next()){
            count++;
            arrayList.add(new EmployeeAttendance(rst.getString(1),rst.getString(2),rst.getString(3), rst.getString(4),rst.getString(5),rst.getString(6)));
        }
        return count;
    }
}
