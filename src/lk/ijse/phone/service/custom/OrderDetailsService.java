package lk.ijse.phone.service.custom;

import lk.ijse.phone.entity.OrderDetails;
import lk.ijse.phone.service.SuperService;
import lk.ijse.phone.dto.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsService extends SuperService {
    ArrayList<OrderDetailsDTO> getAllOrderDetails(String orderId) throws SQLException, ClassNotFoundException;
    boolean saveOrderDetails(ArrayList<OrderDetailsDTO> arrayList) throws SQLException, ClassNotFoundException;
}
