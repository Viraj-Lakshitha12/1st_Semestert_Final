package lk.ijse.phone.service.custom.impl;

import lk.ijse.phone.dao.DaoFactory;
import lk.ijse.phone.dao.DAOTypes;
import lk.ijse.phone.dao.custom.PaymentDAO;
import lk.ijse.phone.db.DBConnection;
import lk.ijse.phone.dto.PaymentDetailsDTO;
import lk.ijse.phone.service.custom.PaymentService;
import lk.ijse.phone.dto.PaymentDTO;
import lk.ijse.phone.service.util.Convertor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentServiceImpl implements PaymentService {
    private final PaymentDAO paymentDAO = (PaymentDAO) DaoFactory.getDaoFactory().getDAO(DAOTypes.PAYMENT);
    private final Convertor convertor = new Convertor();
    @Override
    public String GeneratePaymentID() throws SQLException, ClassNotFoundException {
        return paymentDAO.generatePaymentId();
    }

    @Override
    public boolean addPayment(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return paymentDAO.savePayment(convertor.toPayment(paymentDTO));
    }

    @Override
    public double Income(String day) throws SQLException, ClassNotFoundException {
        return paymentDAO.getIncome(day);
    }

}
