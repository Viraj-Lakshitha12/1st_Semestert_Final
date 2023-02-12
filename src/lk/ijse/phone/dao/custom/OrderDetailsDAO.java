package lk.ijse.phone.dao.custom;

import lk.ijse.phone.dao.SuperDAO;
import lk.ijse.phone.dto.OrderDetailsDTO;
import lk.ijse.phone.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsDAO extends SuperDAO {
    ArrayList<OrderDetails> getAllOrderDetails(String orderId) throws ClassNotFoundException , SQLException;
    boolean saveOrderDetails(ArrayList<OrderDetails> arrayList) throws SQLException, ClassNotFoundException;
}
