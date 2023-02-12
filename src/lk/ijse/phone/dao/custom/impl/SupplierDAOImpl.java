package lk.ijse.phone.dao.custom.impl;

import lk.ijse.phone.dao.custom.SupplierDAO;
import lk.ijse.phone.db.DBConnection;
import lk.ijse.phone.dto.SupplierDTO;
import lk.ijse.phone.entity.Supplier;
import lk.ijse.phone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO supplier VALUES(?,?,?,?,?)",
                supplier.getSupplierId(),
                supplier.getName(),
                supplier.getBrand(),
                supplier.getAddress(),
                supplier.getContact()
        );
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        boolean i = CrudUtil.execute("UPDATE  supplier SET name=?,brand=?,address=?,contact=? WHERE supplierId = ? ",
                supplier.getName(),
                supplier.getBrand(),
                supplier.getAddress(),
                supplier.getContact(),
                supplier.getSupplierId()
        );
        return i ;
    }

    @Override
    public boolean deleteByPk(String id) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From supplier where supplierId='"+id+"'")>0;
    }

    @Override
    public Optional<Supplier> findByPk(String sid) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM supplier WHERE supplierId = ? ",sid);
        if (rst.next()) {
            Supplier supplier = new Supplier(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5));
            return Optional.of(supplier);
        }
        return Optional.empty();
    }

    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  CrudUtil.execute("SELECT * FROM supplier ");
        ArrayList<Supplier> arrayList = new ArrayList<>();
        while (rst.next()){
           arrayList.add(new Supplier(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getString(4),rst.getString(5)));
        }
        return arrayList;
    }
}
