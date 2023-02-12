package lk.ijse.phone.service.custom;

import lk.ijse.phone.service.SuperService;
import lk.ijse.phone.dto.PaymentDTO;

import java.sql.SQLException;

public interface PaymentService extends SuperService {
    String GeneratePaymentID() throws SQLException, ClassNotFoundException;
    boolean addPayment(PaymentDTO paymentDTO) throws SQLException,ClassNotFoundException;

    double Income(String day) throws SQLException, ClassNotFoundException;

}
