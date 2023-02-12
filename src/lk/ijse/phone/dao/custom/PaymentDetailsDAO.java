package lk.ijse.phone.dao.custom;

import lk.ijse.phone.dao.SuperDAO;
import lk.ijse.phone.dto.PaymentDetailsDTO;
import lk.ijse.phone.entity.PaymentDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentDetailsDAO extends SuperDAO {
    boolean updatePaymentDetails(PaymentDetails paymentDetails)throws SQLException,ClassNotFoundException;

    boolean addPaymentDetails(PaymentDetails paymentDetails) throws SQLException, ClassNotFoundException ;

    double calculateDailyIncome(String date) throws SQLException, ClassNotFoundException;

    ArrayList<PaymentDetails> getAllPaymentDetails(String cId) throws SQLException, ClassNotFoundException;
}
