package lk.ijse.phone.service.custom.impl;

import lk.ijse.phone.dao.DaoFactory;
import lk.ijse.phone.dao.DAOTypes;
import lk.ijse.phone.dao.custom.PendingOrderDAO;
import lk.ijse.phone.service.custom.PendingOrderService;
import lk.ijse.phone.dto.PendingOrderTmDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class PendingOrderServiceImpl implements PendingOrderService {
    private final PendingOrderDAO pendingOrderDAO = (PendingOrderDAO) DaoFactory.getDaoFactory().getDAO(DAOTypes.PENDING_ORDERS);


    @Override
    public ArrayList<PendingOrderTmDTO> getAllPendingOrders() throws SQLException, ClassNotFoundException {
        return pendingOrderDAO.getAllPendingOrders();
    }
}
