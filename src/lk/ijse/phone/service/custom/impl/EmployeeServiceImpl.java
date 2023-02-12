package lk.ijse.phone.service.custom.impl;

import lk.ijse.phone.dao.DaoFactory;
import lk.ijse.phone.dao.DAOTypes;
import lk.ijse.phone.dao.custom.EmployeeDAO;
import lk.ijse.phone.entity.Employee;
import lk.ijse.phone.service.custom.EmployeeService;
import lk.ijse.phone.dto.EmployeeDTO;
import lk.ijse.phone.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDAO employeeDAO = (EmployeeDAO) DaoFactory.getDaoFactory().getDAO(DAOTypes.EMPLOYEE);
    private final Convertor convertor = new Convertor();

    @Override
    public boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(convertor.toEmployee(employeeDTO));
    }
    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(convertor.toEmployee(employeeDTO));
    }
    @Override
    public Optional<EmployeeDTO> searchEmployee(String id) throws SQLException, ClassNotFoundException {
        Optional<Employee> employee = employeeDAO.findByPk(id);
        if (employee.isPresent()){
            return Optional.of(convertor.fromEmployee(employee.get()));
        }
        return Optional.empty();
    }
    @Override
    public ArrayList<String> loadAllEmployeeNames() throws SQLException, ClassNotFoundException {
        return employeeDAO.loadAllEmployeeNames();
    }
    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.deleteByPk(id);
    }
    @Override
    public ArrayList<EmployeeDTO> loadAllEmployeeData() throws SQLException, ClassNotFoundException {
        return (ArrayList<EmployeeDTO>) employeeDAO.getAll().stream().map(employee -> convertor.fromEmployee(employee)).collect(Collectors.toList());
    }
    @Override
    public int getEmployeeAttendanceCount(String date) throws SQLException, ClassNotFoundException {
        return employeeDAO.getEmployeeAttendanceCount(date);
    }

}
