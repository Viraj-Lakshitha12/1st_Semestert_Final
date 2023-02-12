package lk.ijse.phone.dao.custom.impl;

import lk.ijse.phone.dao.custom.StockArrievdDAO;
import lk.ijse.phone.db.DBConnection;
import lk.ijse.phone.dto.StockArrievdDTO;
import lk.ijse.phone.entity.StockArrived;
import lk.ijse.phone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class StockArrivedDAOImpl implements StockArrievdDAO {
    @Override
    public boolean save(StockArrived stockArrived) throws SQLException, ClassNotFoundException {
        return  CrudUtil.execute("INSERT INTO stockarrievd VALUES (?,?,?,?,?,?)",
                stockArrived.getDateOfArrived(),
                stockArrived.getItemCode(),
                stockArrived.getItemName(),
                stockArrived.getQty(),
                stockArrived.getSupplierId(),
                stockArrived.getSupplierName()
        );
    }

    @Override
    public boolean update(StockArrived stockArrived) throws SQLException, ClassNotFoundException {
        boolean i =CrudUtil.execute("UPDATE  stockarrievd  SET itemCode=?,itemName=?, qty=?,supplierId=?,supplierName=? WHERE dateOfArrrievd = ?",
                stockArrived.getItemCode(),
                stockArrived.getItemName(),
                stockArrived.getQty(),
                stockArrived.getSupplierId(),
                stockArrived.getSupplierName(),
                stockArrived.getDateOfArrived()
        );
        return i;
    }
    @Override
    public boolean deleteByPk(String date) throws SQLException, ClassNotFoundException {
        return  DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From stockarrievd where dateOfArrrievd='"+date+"'")>0;
    }
    @Override
    public Optional<StockArrived> findByPk(String date) throws SQLException, ClassNotFoundException {
        ResultSet rst =CrudUtil.execute("SELECT * FROM stockarrievd WHERE dateOfArrrievd = ?",date);
        while (rst.next()){
            StockArrived stockArrived = new StockArrived(rst.getString(1),rst.getString(2),rst.getString(3),rst.getInt(4),rst.getString(5),rst.getString(6));
            return Optional.of(stockArrived);
        }
        return Optional.empty();
    }

    @Override
    public ArrayList<StockArrived> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM stockarrievd ");
        ArrayList<StockArrived> arrayList = new ArrayList<>();
        while (rst.next()){
            arrayList.add(new StockArrived(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getInt(4),rst.getString(5),rst.getString(6)));
        }
        return arrayList;
    }
}
