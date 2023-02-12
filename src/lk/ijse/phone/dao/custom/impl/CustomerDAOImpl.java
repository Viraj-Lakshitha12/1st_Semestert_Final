package lk.ijse.phone.dao.custom.impl;

import lk.ijse.phone.dao.custom.CustomerDAO;
import lk.ijse.phone.db.DBConnection;
import lk.ijse.phone.dto.CustomerDTO;
import lk.ijse.phone.entity.Customer;
import lk.ijse.phone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO customerDetails VALUES(?,?,?,?)",
                customer.getCustomerId(),
                customer.getCustomerName(),
                customer.getAddress(),
                customer.getPhoneNumber());
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
       boolean i= CrudUtil.execute("UPDATE customerDetails SET customerName = ? , address = ? , phoneNumber = ? WHERE customerId = ?",
               customer.getCustomerName(),
               customer.getAddress(),
               customer.getPhoneNumber(),
               customer.getCustomerId());
        return i;
    }
    @Override
    public boolean deleteByPk(String id) throws SQLException, ClassNotFoundException {
       return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From customerDetails where customerId='"+id+"'")>0;
    }
    @Override
    public Optional<Customer> findByPk(String customerId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM customerdetails WHERE customerId = ?", customerId);
        if (rst.next()) {
            Customer customer = new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4));
            return Optional.of(customer);
        }
        return Optional.empty();
    }
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM customerdetails");
        ArrayList<Customer> arrayList = new ArrayList<>();
        while (rst.next()){
            Customer customer = new Customer(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4));
            arrayList.add(customer);
        }
        return arrayList;
    }
}
