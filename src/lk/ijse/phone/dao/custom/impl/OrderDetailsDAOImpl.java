package lk.ijse.phone.dao.custom.impl;

import lk.ijse.phone.dao.custom.OrderDetailsDAO;
import lk.ijse.phone.dto.OrderDetailsDTO;
import lk.ijse.phone.entity.OrderDetails;
import lk.ijse.phone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public ArrayList<OrderDetails> getAllOrderDetails(String orderId) throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM orderDetails WHERE orderid = ?", orderId);
        ArrayList<OrderDetails> arrayList = new ArrayList<>();
        while (rst.next()){
            arrayList.add(new OrderDetails(rst.getString(1),rst.getString(2),
                    rst.getInt(3),rst.getDouble(4),rst.getDouble(5)));
        }
        return arrayList;
    }
    @Override
    public boolean saveOrderDetails(ArrayList<OrderDetails>arrayList) throws SQLException, ClassNotFoundException {
        for (OrderDetails orderDetails:arrayList) {
            if (!save(orderDetails)) {
                return false;
            }
        }
        return true;
    }
    public  boolean save(OrderDetails orderDetails) throws SQLException, ClassNotFoundException {
        boolean b=   CrudUtil.execute("INSERT INTO orderDetails VALUES(?,?,?,?,?)",
                orderDetails.getOrderId(),
                orderDetails.getItemCode(),
                orderDetails.getQty(),
                orderDetails.getUnitPrice(),
                orderDetails.getPrice()
        );
        return b;
    }


}
