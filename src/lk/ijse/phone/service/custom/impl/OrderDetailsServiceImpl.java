package lk.ijse.phone.service.custom.impl;

import lk.ijse.phone.dao.DAOTypes;
import lk.ijse.phone.dao.DaoFactory;
import lk.ijse.phone.dao.custom.OrderDetailsDAO;
import lk.ijse.phone.dao.custom.impl.OrderDetailsDAOImpl;
import lk.ijse.phone.dto.OrderDetailsDTO;
import lk.ijse.phone.entity.OrderDetails;
import lk.ijse.phone.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class OrderDetailsServiceImpl implements lk.ijse.phone.service.custom.OrderDetailsService {
    private  final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DaoFactory.getDaoFactory().getDAO(DAOTypes.ORDER_DETAILS);
    private final Convertor convertor = new Convertor();
    @Override
    public ArrayList<OrderDetailsDTO> getAllOrderDetails(String orderId) throws SQLException, ClassNotFoundException {
        return (ArrayList<OrderDetailsDTO>) orderDetailsDAO.getAllOrderDetails(orderId).stream().map(orderDetails -> convertor.fromOrderDetails(orderDetails)).collect(Collectors.toList());
    }
    @Override
    public boolean saveOrderDetails(ArrayList<OrderDetailsDTO> arrayList) throws SQLException, ClassNotFoundException {
       ArrayList<OrderDetails> orderDetails = new ArrayList();
        for (OrderDetailsDTO orderDetailsDTO : arrayList) {
            orderDetails.add(new OrderDetails(orderDetailsDTO.getOrderId(),orderDetailsDTO.getItemCode(),orderDetailsDTO.getQty(),orderDetailsDTO.getUnitPrice(),orderDetailsDTO.getPrice()));
        }
        return orderDetailsDAO.saveOrderDetails(orderDetails);
    }

}
