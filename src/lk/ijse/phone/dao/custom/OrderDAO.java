package lk.ijse.phone.dao.custom;

import lk.ijse.phone.dao.CrudDAO;
import lk.ijse.phone.dto.OrderDTO;
import lk.ijse.phone.entity.Orders;
import org.apache.hadoop.hive.metastore.api.Order;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Orders,String> {
    String generateOrderId() throws SQLException, ClassNotFoundException;
    int calculateDailyOrders(String date) throws SQLException,ClassNotFoundException;
}
