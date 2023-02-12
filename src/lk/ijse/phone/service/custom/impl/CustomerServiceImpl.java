package lk.ijse.phone.service.custom.impl;

import lk.ijse.phone.dao.DaoFactory;
import lk.ijse.phone.dao.DAOTypes;
import lk.ijse.phone.dao.custom.CustomerDAO;
import lk.ijse.phone.dto.CustomerDTO;
import lk.ijse.phone.entity.Customer;
import lk.ijse.phone.service.custom.CustomerService;
import lk.ijse.phone.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerDAO customerDAO = (CustomerDAO) DaoFactory.getDaoFactory().getDAO(DAOTypes.CUSTOMER);
    private final Convertor convertor = new Convertor();
    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
       return customerDAO.save(convertor.toCustomer(customerDTO));
    }
    @Override
    public Optional<CustomerDTO> searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Optional<Customer> optionalCustomer = customerDAO.findByPk(id);
        if (optionalCustomer.isPresent()) {
            return Optional.ofNullable(convertor.fromCustomer(optionalCustomer.get()));
        }else {
            return Optional.empty();
        }
    }
    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(convertor.toCustomer(customerDTO));
    }
    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.deleteByPk(id);
    }
    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        return (ArrayList<CustomerDTO>) customerDAO.getAll().stream().map(customer -> convertor.fromCustomer(customer)).collect(Collectors.toList());
    }
}
