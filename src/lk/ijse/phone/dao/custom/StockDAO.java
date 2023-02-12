package lk.ijse.phone.dao.custom;

import lk.ijse.phone.dao.CrudDAO;
import lk.ijse.phone.dto.ItemDTO;
import lk.ijse.phone.dto.OrderDetailsDTO;
import lk.ijse.phone.entity.Item;
import lk.ijse.phone.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockDAO extends CrudDAO<Item,String> {
   ArrayList<String> loadAllItemNames() throws SQLException, ClassNotFoundException;
   Item searchItemByItemName(String itemName) throws SQLException, ClassNotFoundException;
   Item searchItemByItemCode(String itemName) throws SQLException, ClassNotFoundException;
   boolean updateStock(ArrayList<OrderDetails> arrayList) throws SQLException, ClassNotFoundException;
}
