package lk.ijse.phone.dao.custom;

import lk.ijse.phone.dao.SuperDAO;
import lk.ijse.phone.dto.PaymentDTO;
import lk.ijse.phone.entity.Payment;

import java.sql.SQLException;

public interface PaymentDAO extends SuperDAO {
    String generatePaymentId() throws SQLException, ClassNotFoundException;
    boolean savePayment(Payment payment)throws SQLException,ClassNotFoundException;
    double getIncome(String date) throws SQLException, ClassNotFoundException;

}
