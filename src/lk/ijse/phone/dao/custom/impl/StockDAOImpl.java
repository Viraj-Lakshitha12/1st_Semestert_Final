package lk.ijse.phone.dao.custom.impl;

import lk.ijse.phone.dao.custom.StockDAO;
import lk.ijse.phone.db.DBConnection;
import lk.ijse.phone.dto.ItemDTO;
import lk.ijse.phone.dto.OrderDetailsDTO;
import lk.ijse.phone.entity.Item;
import lk.ijse.phone.entity.OrderDetails;
import lk.ijse.phone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class StockDAOImpl implements StockDAO {
    @Override
    public boolean save(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE item SET qtyOnHand = qtyOnHand + ? WHERE itemCode = ?", item.getQtyOnHand(), item.getItemCode()
        );
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        boolean i = CrudUtil.execute("UPDATE item  SET itemName=?,qtyOnHand = qtyOnHand + ?, unitPrice = ? WHERE itemCode = ? ",
                item.getItemName(),
                item.getQtyOnHand(),
                item.getUnitPrice(),
                item.getItemCode()
        );
        return i;
    }

    @Override
    public boolean deleteByPk(String itemCode) throws SQLException, ClassNotFoundException {
        return  DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From item where itemCode='"+itemCode+"'")>0;

    }

    @Override
    public Optional<Item> findByPk(String itemCode) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM item WHERE itemCode = ?", itemCode);
        if (rst.next()){
            Item item = new Item(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4));
            return  Optional.of(item);
        }
        return Optional.empty();
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM item");
        ArrayList<Item> arrayList = new ArrayList<>();
        while (rst.next()){
            arrayList.add(new Item(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4)));
        }
        return arrayList;
    }

    @Override
    public ArrayList<String> loadAllItemNames() throws SQLException, ClassNotFoundException {
        ArrayList<String> arrayList = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT itemName FROM item");
        while (rst.next()){
            arrayList.add(rst.getString("itemName"));

        }
        return arrayList;
    }

    @Override
    public Item searchItemByItemName(String items) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM item WHERE itemName = ?", items);

        if (rst.next()){
            Item item = new Item(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4));
            return item;
        }
        return null;
    }

    @Override
    public boolean updateStock(ArrayList<OrderDetails> orderDetailDTOS) throws SQLException, ClassNotFoundException {
        for (OrderDetails c : orderDetailDTOS) {
            if (!updateQty(new Item(c.getItemCode(),c.getItemName(),c.getQty(), c.getUnitPrice()))) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(Item item) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE item SET qtyOnHand = qtyOnHand - ? WHERE itemCode = ?";
        return CrudUtil.execute(sql, item.getQtyOnHand(), item.getItemCode());
    }
    @Override
    public Item searchItemByItemCode(String itemCode) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM item WHERE itemCode = ?", itemCode);
        if (rst.next()){
            Item item = new Item(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4));
            return item;
        }
        return null;
    }
}
