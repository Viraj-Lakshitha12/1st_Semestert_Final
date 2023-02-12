package lk.ijse.phone.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import lk.ijse.phone.dao.custom.CustomerDAO;
import lk.ijse.phone.dao.custom.PaymentDAO;
import lk.ijse.phone.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.phone.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.phone.db.DBConnection;
import lk.ijse.phone.dto.CustomerDTO;
import lk.ijse.phone.dto.PaymentDetailsDTO;
import lk.ijse.phone.entity.Customer;

import lk.ijse.phone.dto.PaymentDTO;
import lk.ijse.phone.service.ServiceFactory;
import lk.ijse.phone.service.ServiceTypes;
import lk.ijse.phone.service.custom.CustomerService;
import lk.ijse.phone.service.custom.PaymentDetailsService;
import lk.ijse.phone.service.custom.PaymentService;
import lk.ijse.phone.service.custom.impl.CustomerServiceImpl;
import lk.ijse.phone.service.custom.impl.PaymentDetailsServiceImpl;
import lk.ijse.phone.service.custom.impl.PaymentServiceImpl;
import lk.ijse.phone.util.GetCusId;
import lk.ijse.phone.util.GetSystemUserDetails;
import lk.ijse.phone.util.Navigation;
import lk.ijse.phone.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static lk.ijse.phone.util.Routes.PAYMENT;

public class PaymentFormController {
    public Label lblLogoutName;
    public TextField txtCustomer;
    public Label lblName;
    public Label lblAddress;
    public Label lblContactNo;
    public Label lblOrderId;
    public TextField txtSearchReturn;
    public JFXTextField txtQty;
    public TableColumn tblOrderId;
    public TableColumn tblQty;
    public TableColumn tblitemCode;
    public TableColumn tblUnitPrice;
    public TableColumn tblTotal;
    public Label lblItemCode;
    public JFXComboBox cmbSelectItemNames;
    public Label lblCustomerName;

    public AnchorPane pane;
    public Label lblPaymentId;

    public TableColumn tblPaymentId;

    public TableView tblView;
    public TableColumn colOrdedrId;
    public TableColumn colCustomerId;
    public TableView colPaymentId;
    public TableColumn colOrderId;
    public TableColumn colTotal;
    public TableColumn colTotalBalance;

    public TableView tblViewPayment;
    public TextField txtPrice;
    public Button addbtn;
    public Label lblDate;
    public Label lblTime;
    public AnchorPane pane2;

    private ObservableList<PaymentDetailsDTO> observableList = FXCollections.observableArrayList();
    private final PaymentService paymentService =new PaymentServiceImpl();// (PaymentService) ServiceFactory.getServiceFactory().getService(ServiceTypes.PAYMENT);
    private final CustomerService customerService = new CustomerServiceImpl();//(CustomerService) ServiceFactory.getServiceFactory().getService(ServiceTypes.CUSTOMER);
    private final PaymentDetailsService paymentDetailsService = new PaymentDetailsServiceImpl();

    public TableColumn colBalance;
    public void initialize(){

        GetSystemUserDetails systemUserDetails = new GetSystemUserDetails();
        String name1 = systemUserDetails.getName();
        lblLogoutName.setText(name1);

        loadDate();

        tblPaymentId.setCellValueFactory(new PropertyValueFactory("paymentId"));
        colOrderId.setCellValueFactory(new PropertyValueFactory("orderid"));
        colTotal.setCellValueFactory(new PropertyValueFactory("total"));
        colTotalBalance.setCellValueFactory(new PropertyValueFactory("totalBalance"));

    }

    private void loadDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
        DateFormat df = new SimpleDateFormat(" HH:mm:ss");
        Date dateobj = new Date();
        lblTime.setText(df.format(dateobj));
    }

    public void btnSalesBilingOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SALESBILLING,pane);
    }

    public void btnOrdersOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ORDERS,pane);
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PAYMENT,pane);
    }

    public void btnReportOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.INCOMEREPORT,pane);

    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAINFORM,pane);
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN,pane);

    }

    public void btnAvaOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.AVA,pane);
    }

    public void btnBillOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SALESBILLING,pane);
    }

    public void btnOrdOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PENDINGORDERS,pane);
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        GetSystemUserDetails getSystemUserDetails = new GetSystemUserDetails();
        String rank = getSystemUserDetails.getRank();
        if (rank.equals("admin")){
            Navigation.navigate(Routes.ADDCUSTOMERS,pane);
        }else{
            new Alert(Alert.AlertType.WARNING,"Cant Access").show();
        }
    }

    public void btnStockOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCHSTOCK,pane);
    }

    public void txtCustomerIdOnAction(ActionEvent actionEvent) {
        String cid = txtCustomer.getText();
        try {
            Optional<CustomerDTO> customer = customerService.searchCustomer(cid);
            if (!customer.isPresent()){
                new Alert(Alert.AlertType.WARNING,"Payment Not Found").show();
            }else {
                lblName.setText(customer.get().getCustomerName());
                lblAddress.setText(customer.get().getAddress());
                lblContactNo.setText(customer.get().getPhoneNumber());
                GetCusId getCusId = new GetCusId();
                getCusId.setCusId(cid);

                colOrdedrId.setCellValueFactory(new PropertyValueFactory("orderId"));
                colCustomerId.setCellValueFactory(new PropertyValueFactory("customerId"));
                colBalance.setCellValueFactory(new PropertyValueFactory("balance"));
                try {
                    ArrayList<PaymentDetailsDTO> arrayList = paymentDetailsService.getAllPaymentDetailsBYCID(cid);

                    for (PaymentDetailsDTO p :arrayList) {
                        observableList.add(p);
                        tblView.setItems(observableList);
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSearchCustomerId(ActionEvent actionEvent) {
        txtCustomerIdOnAction(actionEvent);
    }

    public void btnSetCustomerOnAction(ActionEvent actionEvent) {
        lblCustomerName.setText(lblName.getText());
        GenaratePaymentId();
        for (int i = 0;i<tblView.getItems().size();i++) {
            PaymentDetailsDTO tm = observableList.get(i);
            lblOrderId.setText(tm.getOrderId());
        }
    }
    private void GenaratePaymentId() {
        try{
            String lastOrderId =paymentService.GeneratePaymentID();
            if(lastOrderId==null) {
                lblPaymentId.setText("P00001");
            }else{
                String[] split=lastOrderId.split("[P]");
                int lastDigits=Integer.parseInt(split[1]);
                lastDigits++;
                String newOrderId=String.format("P%05d", lastDigits);
                lblPaymentId.setText(newOrderId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnRetryOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PAYMENT,pane);
    }
    public void btnSubmitOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String paymentId = lblPaymentId.getText();
        String orderId=lblOrderId.getText();
        double total= Double.parseDouble(txtPrice.getText());
        String date = lblDate.getText();
        String time = lblTime.getText();
        double totalBalance=0;

       String customerId =null;


        for (int i =0;i<tblView.getItems().size();i++){
            PaymentDetailsDTO tm = observableList.get(i);
            totalBalance =tm.getBalance();
            customerId=tm.getCustomerId();
        }
        double t = totalBalance-total;
       // ArrayList <PaymentDetailsDTO> paymentDetailDTOS = new ArrayList<>();
        PaymentDetailsDTO paymentDetailsDTO = new PaymentDetailsDTO(orderId, customerId, t, date, time);

        PaymentDTO paymentDTO = new PaymentDTO(paymentId,orderId,total,totalBalance);
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean addPayment =paymentService.addPayment(paymentDTO);
            if (addPayment) {
                boolean addedPaymentDetails = paymentDetailsService.updatePaymentDetails(paymentDetailsDTO);
                if (addedPaymentDetails){
                    DBConnection.getInstance().getConnection().commit();
                    new Alert(Alert.AlertType.INFORMATION, "Success").show();
                    btnRetryOnAction(actionEvent);
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Not Updated").show();
                }
            }
            DBConnection.getInstance().getConnection().rollback();
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }finally {
                DBConnection.getInstance().getConnection().setAutoCommit(false);
        }
    }
    public void btnHrnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.HR,pane);
    }

    public void btnAddTableOnAction(ActionEvent actionEvent) {

            String paymentid = lblPaymentId.getText();
            String orderId = lblOrderId.getText();
            double price = Double.parseDouble(txtPrice.getText());
            double total = 0;
            for (int i = 0; i < tblView.getItems().size(); i++) {
                PaymentDetailsDTO tm = observableList.get(i);
                total = tm.getBalance();
            }
            total -= price;
            PaymentDTO paymentDTO = new PaymentDTO(paymentid, orderId, price, total);
            ObservableList observableList1 = tblViewPayment.getItems();
            observableList1.add(paymentDTO);
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) throws IOException {;
    Navigation.navigate(PAYMENT,pane);
    }

}
