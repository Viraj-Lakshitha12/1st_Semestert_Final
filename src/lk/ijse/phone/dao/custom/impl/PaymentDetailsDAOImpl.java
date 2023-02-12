package lk.ijse.phone.dao.custom.impl;

import lk.ijse.phone.dao.custom.PaymentDetailsDAO;
import lk.ijse.phone.dto.PaymentDetailsDTO;
import lk.ijse.phone.entity.PaymentDetails;
import lk.ijse.phone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDetailsDAOImpl implements PaymentDetailsDAO {
    @Override
    public  boolean updatePaymentDetails(PaymentDetails paymentDetails) throws SQLException, ClassNotFoundException {
        boolean i =CrudUtil.execute("UPDATE paymentDetails SET  balance = ? WHERE orderId =?",
                paymentDetails.getBalance(),
                paymentDetails.getOrderId()
        );
        return i;
    }
    @Override
        public  double calculateDailyIncome(String date) throws SQLException, ClassNotFoundException {
            ResultSet rst = CrudUtil.execute("SELECT balance FROM paymentdetails WHERE  date=? ", date);
            double income=0;
            while (rst.next()){
                income += rst.getDouble("balance");
            }
        return income;
    }

    @Override
    public ArrayList<PaymentDetails> getAllPaymentDetails(String cId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM paymentdetails WHERE customerId=?",cId);
        ArrayList<PaymentDetails> arrayList = new ArrayList<>();
        while (rst.next()){
            arrayList.add(new PaymentDetails(rst.getString(1),rst.getString(2),rst.getDouble(3),rst.getString(4),rst.getString(5)));
        }
        return arrayList;
    }

    @Override
    public  boolean addPaymentDetails(PaymentDetails paymentDetails) throws SQLException, ClassNotFoundException {
        return   CrudUtil.execute("INSERT INTO paymentDetails VALUES(?,?,?,?,?)",
                paymentDetails.getOrderId(),
                paymentDetails.getCustomerId(),
                paymentDetails.getBalance(),
                paymentDetails.getDate(),
                paymentDetails.getTime()
        );
    }
}
