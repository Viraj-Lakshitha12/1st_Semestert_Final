package lk.ijse.phone.service.custom;

import lk.ijse.phone.service.SuperService;
import lk.ijse.phone.dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderService extends SuperService {
    boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;
    boolean updateOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;
    ArrayList<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException;
    String generateNewOrderId() throws SQLException, ClassNotFoundException;
    int getTodayOrderCount(String date)throws SQLException, ClassNotFoundException;

}
