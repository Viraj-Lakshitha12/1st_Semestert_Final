package lk.ijse.phone.dao.custom.impl;

import lk.ijse.phone.dao.custom.OrderDAO;
import lk.ijse.phone.db.DBConnection;
import lk.ijse.phone.dto.OrderDetailsDTO;
import lk.ijse.phone.dto.PaymentDetailsDTO;
import lk.ijse.phone.entity.OrderDetails;
import lk.ijse.phone.entity.Orders;

import lk.ijse.phone.service.custom.OrderDetailsService;
import lk.ijse.phone.service.custom.PaymentDetailsService;
import lk.ijse.phone.service.custom.StockService;
import lk.ijse.phone.service.custom.impl.OrderDetailsServiceImpl;
import lk.ijse.phone.service.custom.impl.PaymentDetailsServiceImpl;
import lk.ijse.phone.service.custom.impl.StockServiceImpl;
import lk.ijse.phone.dto.OrderDTO;
import lk.ijse.phone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class OrderDAOImpl implements OrderDAO {
    private final OrderDetailsService orderDetailsService = new OrderDetailsServiceImpl();
    private final StockService stockService = new StockServiceImpl();
    private final PaymentDetailsService paymentDetailsService = new PaymentDetailsServiceImpl();

    @Override
    public boolean save(Orders orders) throws SQLException, ClassNotFoundException {
        boolean addOrder = CrudUtil.execute("INSERT INTO orders VALUES (?,?,?,?,?)",
                    orders.getOrderId(),
                    orders.getDate(),
                    orders.getCustomerId(),
                    orders.getSalesmen(),
                    orders.getStatus());
        return addOrder;
    }


    @Override
    public boolean update(Orders pendingOrderTmDTO) throws SQLException, ClassNotFoundException {
        boolean i =CrudUtil.execute("UPDATE orders SET  date=? , customerId=?, salesmen=?,status=? WHERE orderId=?",
                pendingOrderTmDTO.getDate(),
                pendingOrderTmDTO.getCustomerId(),
                pendingOrderTmDTO.getSalesmen(),
                pendingOrderTmDTO.getStatus(),
                pendingOrderTmDTO.getOrderId()
        );
        return i;
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<Orders> findByPk(String pk) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM orders");
        ArrayList<Orders> arrayList = new ArrayList<>();
        while (rst.next()){
            arrayList.add(new Orders(rst.getString("orderId"),rst.getString("date"),rst.getString("customerId"),rst.getString("salesmen"),rst.getString("status")));
        }
        return arrayList;
    }

    @Override
    public String generateOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DBConnection.getInstance().getConnection().prepareStatement(" Select orderId from  orders order by orderId desc limit 1").executeQuery();
        return rst.next() ? rst.getString("orderId"):null;

    }
    @Override
    public  int calculateDailyOrders(String date) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT orderId FROM Orders WHERE date = ?",date);
        int OrderCount = 0;
        while (rst.next()){
            OrderCount++;
        }
        return OrderCount;
    }

}
