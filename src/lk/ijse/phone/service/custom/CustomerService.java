package lk.ijse.phone.service.custom;

import lk.ijse.phone.dto.CustomerDTO;
import lk.ijse.phone.service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface CustomerService extends SuperService {
     boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
     Optional<CustomerDTO> searchCustomer(String id) throws SQLException, ClassNotFoundException;
     boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
     boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
     ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
}
