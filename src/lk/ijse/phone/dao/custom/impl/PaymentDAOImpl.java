package lk.ijse.phone.dao.custom.impl;

import lk.ijse.phone.dao.custom.PaymentDAO;
import lk.ijse.phone.db.DBConnection;
import lk.ijse.phone.dto.PaymentDetailsDTO;
import lk.ijse.phone.entity.Payment;
import lk.ijse.phone.service.custom.PaymentDetailsService;
import lk.ijse.phone.service.custom.impl.PaymentDetailsServiceImpl;
import lk.ijse.phone.dto.PaymentDTO;
import lk.ijse.phone.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDAOImpl implements PaymentDAO {
    private final PaymentDetailsService paymentDetailsService = new PaymentDetailsServiceImpl();
    @Override
    public String generatePaymentId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery(" Select paymentId from  payment order by paymentId desc limit 1");
        return rst.next() ? rst.getString("paymentId"):null;
    }
    @Override
    public boolean savePayment(Payment payment) throws SQLException, ClassNotFoundException {
        boolean i = CrudUtil.execute("INSERT INTO payment VALUES(?,?,?,?) ",
                payment.getPaymentId(),
                payment.getOrderId(),
                payment.getTotal(),
                payment.getTotalBalance()
        );
        return i;
    }

    @Override
    public double getIncome(String date) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("select * from paymentdetails where date = ?");
        preparedStatement.setObject(1,date);
        ResultSet rst = preparedStatement.executeQuery();
        double income = 0;
        while (rst.next()){
            PaymentDetailsDTO p =new PaymentDetailsDTO(rst.getString(1),rst.getString(2),rst.getDouble(3),
                    rst.getString(4),rst.getString(5));
            income +=p.getBalance();
        }
        return income;
    }
}

