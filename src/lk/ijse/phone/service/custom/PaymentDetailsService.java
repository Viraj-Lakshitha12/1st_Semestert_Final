package lk.ijse.phone.service.custom;

import lk.ijse.phone.dto.PaymentDetailsDTO;
import lk.ijse.phone.entity.PaymentDetails;
import lk.ijse.phone.service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentDetailsService extends SuperService {
    boolean updatePaymentDetails(PaymentDetailsDTO paymentDetailDTO) throws SQLException, ClassNotFoundException;
    double getTodayIncome(String date)throws SQLException, ClassNotFoundException;
    boolean savePaymentDetails(PaymentDetailsDTO paymentDetailsDTO)throws SQLException, ClassNotFoundException;
    ArrayList<PaymentDetailsDTO> getAllPaymentDetailsBYCID(String cId) throws SQLException, ClassNotFoundException;
}
