package lk.ijse.phone.dao.custom.impl;

import lk.ijse.phone.dao.custom.PlaceDAO;
import lk.ijse.phone.db.DBConnection;
import lk.ijse.phone.dto.RackDetailsDTO;
import lk.ijse.phone.entity.RackDetails;
import lk.ijse.phone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class PlaceDAOImpl implements PlaceDAO {

    @Override
    public boolean save(RackDetails rackDetails) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO rackdetails VALUES(?,?,?)",
                rackDetails.getRackNumber(),
                rackDetails.getItemCode(),
                rackDetails.getItemName());
    }

    @Override
    public boolean update(RackDetails rackDetails) throws SQLException, ClassNotFoundException {
        boolean i = CrudUtil.execute(" UPDATE rackdetails SET itemCode = ? , itemName = ?  WHERE rackNumber = ?",
                rackDetails.getItemCode(),
                rackDetails.getItemName(),
                rackDetails.getRackNumber()
        );
        return i;
    }

    @Override
    public boolean deleteByPk(String rackNumber) throws SQLException, ClassNotFoundException {
        return  DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From rackdetails where rackNumber='"+rackNumber+"'")>0;
    }

    @Override
    public Optional<RackDetails> findByPk(String rackNumber) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM rackdetails WHERE rackNumber = ?", rackNumber);
        if (rst.next()) {
            RackDetails rackDetails = new RackDetails(rst.getString(1), rst.getString(2), rst.getString(3));
            return Optional.of(rackDetails);
        }
        return Optional.empty();
    }

    @Override
    public ArrayList<RackDetails> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM rackdetails");
        ArrayList<RackDetails> arrayList = new ArrayList<>();
        while (rst.next()) {
            RackDetails rackNumber = new RackDetails(rst.getString("rackNumber"), rst.getString(2), rst.getString(3));
            arrayList.add(rackNumber);
        }
        return arrayList;
    }
}
