package lk.ijse.phone.service.custom.impl;

import lk.ijse.phone.dao.DaoFactory;
import lk.ijse.phone.dao.DAOTypes;
import lk.ijse.phone.dao.custom.StockDAO;
import lk.ijse.phone.dto.CustomerDTO;
import lk.ijse.phone.dto.ItemDTO;
import lk.ijse.phone.dto.OrderDetailsDTO;
import lk.ijse.phone.entity.Item;
import lk.ijse.phone.entity.OrderDetails;
import lk.ijse.phone.service.custom.StockService;
import lk.ijse.phone.service.util.Convertor;
import org.hibernate.criterion.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class StockServiceImpl implements StockService {
    private final StockDAO stockDAO = (StockDAO) DaoFactory.getDaoFactory().getDAO(DAOTypes.STOCK);
    private final Convertor convertor = new Convertor();

    @Override
    public boolean saveStock(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return stockDAO.save(convertor.toItem(itemDTO));
    }

    @Override
    public boolean updateStock(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return stockDAO.update(convertor.toItem(itemDTO));
    }

    @Override
    public Optional<ItemDTO> searchStock(String itemCode) throws SQLException, ClassNotFoundException {
        Optional<Item> byPk = stockDAO.findByPk(itemCode);
        if (byPk.isPresent()){
            return Optional.ofNullable(convertor.fromItem(byPk.get()));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteStock(String itemCode) throws SQLException, ClassNotFoundException {
       return stockDAO.deleteByPk(itemCode);
    }

    @Override
    public ArrayList<ItemDTO> getAllStockData() throws SQLException, ClassNotFoundException {
        return (ArrayList<ItemDTO>) stockDAO.getAll().stream().map(item -> convertor.fromItem(item)).collect(Collectors.toList());
    }

    @Override
    public ArrayList<String> getAllItemNames() throws SQLException, ClassNotFoundException {
        return stockDAO.loadAllItemNames();
    }

    @Override
    public ItemDTO searchItemByItemName(String itemName) throws SQLException, ClassNotFoundException {
        Item item = stockDAO.searchItemByItemName(itemName);
        return convertor.fromItem(item);
    }

    @Override
    public boolean updateQTY(ArrayList<OrderDetailsDTO> arrayList) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetails> orderDetails = new ArrayList<>();
        for (OrderDetailsDTO orderDetailsDTO : arrayList) {
            orderDetails.add(new OrderDetails(orderDetailsDTO.getOrderId(),orderDetailsDTO.getItemCode(),orderDetailsDTO.getQty(),orderDetailsDTO.getUnitPrice(),orderDetailsDTO.getPrice(),orderDetailsDTO.getItemName()));
        }
        return stockDAO.updateStock(orderDetails);
    }

}
