package lk.ijse.phone.service.custom.impl;

import lk.ijse.phone.dao.DaoFactory;
import lk.ijse.phone.dao.DAOTypes;
import lk.ijse.phone.dao.custom.PaymentDetailsDAO;
import lk.ijse.phone.dto.CustomerDTO;
import lk.ijse.phone.dto.PaymentDetailsDTO;
import lk.ijse.phone.entity.PaymentDetails;
import lk.ijse.phone.service.custom.PaymentDetailsService;
import lk.ijse.phone.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class PaymentDetailsServiceImpl implements PaymentDetailsService {
    private final PaymentDetailsDAO paymentDetailsDAO = (PaymentDetailsDAO) DaoFactory.getDaoFactory().getDAO(DAOTypes.PAYMENT_DETAILS);
    private final Convertor convertor = new Convertor();

    @Override
    public boolean updatePaymentDetails(PaymentDetailsDTO paymentDetailDTO) throws SQLException, ClassNotFoundException {
        return paymentDetailsDAO.updatePaymentDetails(convertor.toPaymentDetails(paymentDetailDTO));
    }

    @Override
    public double getTodayIncome(String date) throws SQLException, ClassNotFoundException {
        return paymentDetailsDAO.calculateDailyIncome(date);
    }

    @Override
    public boolean savePaymentDetails(PaymentDetailsDTO paymentDetailsDTO) throws SQLException, ClassNotFoundException {
        return paymentDetailsDAO.addPaymentDetails(convertor.toPaymentDetails(paymentDetailsDTO));
    }

    @Override
    public ArrayList<PaymentDetailsDTO> getAllPaymentDetailsBYCID(String cId) throws SQLException, ClassNotFoundException {
        return (ArrayList<PaymentDetailsDTO>) paymentDetailsDAO.getAllPaymentDetails(cId).stream().map(paymentDetails -> convertor.fromPaymentDetails(paymentDetails)).collect(Collectors.toList());
    }

}
