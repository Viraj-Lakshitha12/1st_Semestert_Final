package lk.ijse.phone.dao.custom.impl;

import lk.ijse.phone.dao.custom.EmployeeDAO;
import lk.ijse.phone.db.DBConnection;
import lk.ijse.phone.dto.EmployeeAttendanceDTO;
import lk.ijse.phone.dto.EmployeeDTO;
import lk.ijse.phone.entity.Employee;
import lk.ijse.phone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public boolean save(Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO employee Values(?,?,?,?)",
                employee.getEmployeeId(),
                employee.getEmployeeName(),
                employee.getAddress(),
                employee.getSalary()
        );
    }

    @Override
    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        boolean i =  CrudUtil.execute("UPDATE employee SET  name=?,address=?,salary=? WHERE employeeId = ?",
                employee.getEmployeeName(),
                employee.getAddress(),
                employee.getSalary(),
                employee.getEmployeeId()
        );
        return  i;
    }

    @Override
    public boolean deleteByPk(String eId) throws SQLException, ClassNotFoundException {
        return  DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From employee where employeeId='"+eId+"'")>0;
    }

    @Override
    public Optional<Employee> findByPk(String eid) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM employee WHERE employeeId = ?", eid);
        if(rst.next()){
            Employee employee = new Employee(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDouble(4));
            return Optional.of(employee);
        }
        return Optional.empty();
    }
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  CrudUtil.execute("SELECT * FROM employee ");
        ArrayList<Employee> arrayList = new ArrayList<>();
        while (rst.next()){
            arrayList.add(new Employee(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDouble(4)));
        }
        return arrayList;
    }

    @Override
    public ArrayList<String> loadAllEmployeeNames() throws SQLException, ClassNotFoundException {
        ResultSet rst =  CrudUtil.execute("SELECT name FROM employee ");
        ArrayList<String> arrayList = new ArrayList<>();
        while (rst.next()){
            arrayList.add(rst.getString("name"));
        }
        return arrayList;
    }
    public  int getEmployeeAttendanceCount(String date) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM EmployeeAttendance WHERE date =?",date);
        int count = 0;
        ArrayList arrayList = new ArrayList<>();
        while (rst.next()){
            count++;
            arrayList.add(new EmployeeAttendanceDTO(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getString(4),rst.getString(5),rst.getString(6)));
        }
        return count;
    }
}
