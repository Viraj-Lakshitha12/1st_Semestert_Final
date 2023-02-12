package lk.ijse.phone.service.custom;

import lk.ijse.phone.service.SuperService;
import lk.ijse.phone.dto.PendingOrderTmDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PendingOrderService extends SuperService {
    ArrayList<PendingOrderTmDTO> getAllPendingOrders() throws SQLException, ClassNotFoundException;
}
