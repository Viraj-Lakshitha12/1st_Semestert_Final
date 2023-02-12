package lk.ijse.phone.service.custom.impl;

import lk.ijse.phone.dao.DaoFactory;
import lk.ijse.phone.dao.DAOTypes;
import lk.ijse.phone.dao.custom.OrderDAO;
import lk.ijse.phone.service.custom.OrderService;
import lk.ijse.phone.dto.OrderDTO;
import lk.ijse.phone.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private  final OrderDAO orderDAO = (OrderDAO) DaoFactory.getDaoFactory().getDAO(DAOTypes.ORDERS);
    private final Convertor convertor = new Convertor();
    @Override
    public boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        return orderDAO.save(convertor.toOrders(orderDTO));
    }

    @Override
    public boolean updateOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        return orderDAO.update(convertor.toOrders(orderDTO));
    }

    @Override
    public ArrayList<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException {
        return (ArrayList<OrderDTO>) orderDAO.getAll().stream().map(orders -> convertor.fromOrder(orders)).collect(Collectors.toList());
    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateOrderId();
    }

    @Override
    public int getTodayOrderCount(String date) throws SQLException, ClassNotFoundException {
        return orderDAO.calculateDailyOrders(date);
    }

}
