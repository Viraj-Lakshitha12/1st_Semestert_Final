package lk.ijse.phone.dao.custom;

import lk.ijse.phone.dao.SuperDAO;
import lk.ijse.phone.dto.PendingOrderTmDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PendingOrderDAO extends SuperDAO {
    ArrayList<PendingOrderTmDTO> getAllPendingOrders() throws SQLException, ClassNotFoundException;
}
