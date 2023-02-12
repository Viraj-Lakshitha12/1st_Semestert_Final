package lk.ijse.phone.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import lk.ijse.phone.dao.custom.PendingOrderDAO;
import lk.ijse.phone.db.DBConnection;
import lk.ijse.phone.dto.PendingOrderTmDTO;
import lk.ijse.phone.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PendingOrderDAOImpl implements PendingOrderDAO {
    @Override
    public ArrayList<PendingOrderTmDTO> getAllPendingOrders() throws SQLException, ClassNotFoundException {
        ArrayList<PendingOrderTmDTO> arrayList = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM orders");
        while (rst.next()){
            arrayList.add(new PendingOrderTmDTO(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),"",new ComboBox(FXCollections.observableArrayList(rst.getString(5),"Packed")), new Button("Show Details")));
        }
        return  arrayList;
    }
}
