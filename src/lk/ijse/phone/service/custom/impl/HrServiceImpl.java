package lk.ijse.phone.service.custom.impl;

import lk.ijse.phone.dao.DAOTypes;
import lk.ijse.phone.dao.DaoFactory;
import lk.ijse.phone.dao.custom.EmployeeDAO;
import lk.ijse.phone.dao.custom.HrDAO;
import lk.ijse.phone.dao.custom.impl.HrDAOImpl;
import lk.ijse.phone.dto.CustomerDTO;
import lk.ijse.phone.entity.EmployeeAttendance;
import lk.ijse.phone.service.custom.HrService;
import lk.ijse.phone.dto.EmployeeAttendanceDTO;
import lk.ijse.phone.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class HrServiceImpl implements HrService {
    private  final HrDAO hrDAO = (HrDAO) DaoFactory.getDaoFactory().getDAO(DAOTypes.HR);
    private final Convertor convertor = new Convertor();
    @Override
    public ArrayList<EmployeeAttendanceDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        return (ArrayList<EmployeeAttendanceDTO>) hrDAO.getAll().stream().map(employee -> convertor.fromEmployeeAttendance(employee)).collect(Collectors.toList());
    }
    @Override
    public boolean saveEmployeeAttendance(EmployeeAttendanceDTO employeeAttendanceDTO) throws SQLException, ClassNotFoundException {
        return hrDAO.save(convertor.toEmployeeAttendance(employeeAttendanceDTO));
    }
    @Override
    public boolean updateEmployeeAttendance(EmployeeAttendanceDTO employeeAttendanceDTO) throws SQLException, ClassNotFoundException {
        return  hrDAO.update(convertor.toEmployeeAttendance(employeeAttendanceDTO));
    }

}
