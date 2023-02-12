package lk.ijse.phone.service.custom;

import lk.ijse.phone.dto.ItemDTO;
import lk.ijse.phone.dto.OrderDetailsDTO;
import lk.ijse.phone.entity.OrderDetails;
import lk.ijse.phone.service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface StockService extends SuperService {
    boolean saveStock(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    boolean updateStock(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    Optional<ItemDTO> searchStock(String itemCode) throws SQLException, ClassNotFoundException;
    boolean deleteStock(String itemCode) throws SQLException, ClassNotFoundException;
    ArrayList<ItemDTO> getAllStockData() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllItemNames() throws  SQLException,ClassNotFoundException;
    ItemDTO searchItemByItemName(String itemName) throws SQLException, ClassNotFoundException;
    boolean updateQTY(ArrayList<OrderDetailsDTO> arrayList) throws SQLException, ClassNotFoundException;

}
