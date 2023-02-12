package lk.ijse.phone.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.phone.db.DBConnection;

import lk.ijse.phone.service.custom.*;
import lk.ijse.phone.service.custom.impl.*;
import lk.ijse.phone.dto.*;
import lk.ijse.phone.util.GetSystemUserDetails;
import lk.ijse.phone.util.Navigation;
import lk.ijse.phone.util.Routes;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

public class SalesBillingController {

    public Label lblLogoutName;
    public AnchorPane pane;
    public TextField txtCustomer;
    public Label lblName;
    public Label lblAddress;
    public Label lblContactNo;
    public Label lblOrderId;
    public TextField txtSearchReturn;
    public JFXTextField txtQty;
    public TableColumn tblOrderId;
    public TableColumn tblitemCode;
    public TableColumn tblQty;
    public TableColumn tblUnitPrice;
    public TableColumn tblTotal;
    public Label lblItemCode;
    public JFXComboBox cmbSelectItemNames;
    public Label lblCustomerName;
    public Label lblItemCode2;
    public JFXComboBox cbmSalesmen;
    public Label lblDate;
    public Label lblTime;
    public Label lblQtyOnHand;
    public Label lblUnitPrice;

    public TableView tblView;
    public TextField txtSearchitem;
    public TableColumn tblQty1;
    public TableColumn colItemname;
    public Label lblItemName;
    public Label lblNetTotal;
    private ObservableList<PlaceOrderTmDTO> obList = FXCollections.observableArrayList();
    private final EmployeeService employeeService = new EmployeeServiceImpl();// (EmployeeService) ServiceFactory.getServiceFactory().getService(ServiceTypes.EMPLOYEE);
    private final StockService stockService = new StockServiceImpl();//(StockService) ServiceFactory.getServiceFactory().getService(ServiceTypes.STOCK);
    private final CustomerService customerService = new CustomerServiceImpl();//(CustomerService) ServiceFactory.getServiceFactory().getService(ServiceTypes.CUSTOMER);
    private final OrderService orderService = new OrderServiceImpl();// (OrderService) ServiceFactory.getServiceFactory().getService(ServiceTypes.ORDERS);
    private final OrderDetailsService orderDetailsService = new OrderDetailsServiceImpl();
    private final PaymentDetailsService paymentDetailsService = new PaymentDetailsServiceImpl();

    public void initialize(){
        GetSystemUserDetails systemUserDetails = new GetSystemUserDetails();
        String name1 = systemUserDetails.getName();
        lblLogoutName.setText(name1);

        loadItemNames();
        loadSalesmen();
        setDate();
        tblOrderId.setCellValueFactory(new PropertyValueFactory("OrderId"));
        tblitemCode.setCellValueFactory(new PropertyValueFactory("itemCode"));
        tblQty.setCellValueFactory(new PropertyValueFactory("qty"));
        tblUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        tblTotal.setCellValueFactory(new PropertyValueFactory("total"));
        colItemname.setCellValueFactory(new PropertyValueFactory("itemName"));
    }
    private void setDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
        DateFormat df = new SimpleDateFormat(" HH:mm:ss");
        Date dateobj = new Date();
        lblTime.setText(df.format(dateobj));

    }
    private void loadSalesmen() {
        try {
            ArrayList<String> arrayList = employeeService.loadAllEmployeeNames();
            ObservableList observableList = FXCollections.observableArrayList();
            for (String s:arrayList) {
                observableList.add(s);
                cbmSalesmen.setItems(observableList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadItemNames() {
        try {
            ArrayList<String > arrayList = stockService.getAllItemNames();
            ObservableList oblist = FXCollections.observableArrayList();
            for (String i :arrayList) {
                oblist.add(i);
                cmbSelectItemNames.setItems(oblist);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
    public void btnSalesBilingOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SALESBILLING,pane);
    }
    public void btnOrdersOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ORDERS,pane);
    }
    public void btnPaymentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PAYMENT,pane);
    }

    public void btnStockOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCHSTOCK,pane);
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
    public void txtCustomerIdOnAction(ActionEvent actionEvent) {
        String cid = txtCustomer.getText();
        try {
            Optional<CustomerDTO> customer = customerService.searchCustomer(cid);
            if (!customer.isPresent()){
                new Alert(Alert.AlertType.WARNING,"Customer Not Fount..Re_try").show();
            }else {
                lblName.setText(customer.get().getCustomerName());
                lblAddress.setText(customer.get().getAddress());
                lblContactNo.setText(customer.get().getPhoneNumber());
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
        GenerateOrderId();
    }
    private void GenerateOrderId() {
        try{
        String lastOrderId =orderService.generateNewOrderId();
        if(lastOrderId==null) {
            lblOrderId.setText("O00001");

        }else{
            String[] split=lastOrderId.split("[O]");
            int lastDigits=Integer.parseInt(split[1]);
            lastDigits++;
            String newOrderId=String.format("O%05d", lastDigits);
            lblOrderId.setText(newOrderId);
        }
    } catch (SQLException | ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
 }
    public void btnRetryOnAction(ActionEvent actionEvent) {
        txtCustomer.clear();
        lblOrderId.setText("");

        lblName.setText("");
        lblAddress.setText("");
        lblContactNo.setText("");
        txtCustomer.requestFocus();
    }
    public void txtSearchItemOnAction(ActionEvent actionEvent) {
        String text = txtSearchReturn.getText();
        try {
            ItemDTO itemDTO = stockService.searchItemByItemName(text);
            if (itemDTO == null) {
                new Alert(Alert.AlertType.WARNING, "Item NotFound").show();
            } else {
                lblItemCode2.setText(itemDTO.getItemCode());
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public void btnSearchItem(ActionEvent actionEvent) {
        txtSearchItemOnAction(actionEvent);
    }
    public void btnAdd(ActionEvent actionEvent) {
        if (txtQty.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Enter Qty ").show();
        } else {
            String orderid = lblOrderId.getText();
            String itemCode = lblItemCode.getText();
            int qty = Integer.parseInt(txtQty.getText());
            double unitPrice = Double.parseDouble(lblUnitPrice.getText());
            double total = unitPrice * qty;
            String itemName = lblItemName.getText();
            double balance =0;

            if (!obList.isEmpty()) {
                L1:
                /* check same item has been in table. If so, update that row instead of adding new row to the table */
                for (int i = 0; i < tblView.getItems().size(); i++) {
                    if (tblitemCode.getCellData(i).equals(itemCode)) {
                        qty += (int) tblQty.getCellData(i);
                        total = unitPrice * qty;

                        obList.get(i).setQty(qty);
                        obList.get(i).setTotal(total);
                        tblView.refresh();
                        return;
                    }
                }
            }
            obList.add(new PlaceOrderTmDTO(orderid, itemCode, qty, unitPrice, total, itemName));
            for (int i = 0;i<tblView.getItems().size();i++) {
                PlaceOrderTmDTO tm = obList.get(i);
                balance += tm.getTotal();
                lblNetTotal.setText(String.valueOf(balance));
            }
            tblView.setItems(obList);
            txtQty.requestFocus();
        }
    }
    public void btnSubmitOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        String orderId = lblOrderId.getText();
        String date = lblDate.getText();
        String customerId = txtCustomer.getText();
        String salesmen = cbmSalesmen.getSelectionModel().getSelectedItem().toString();
        String itemName = txtSearchitem.getText();
        String time=lblTime.getText();
        double balance = 0.0;
        ArrayList<OrderDetailsDTO> orderDetails = new ArrayList<>();
        for (int i = 0;i<tblView.getItems().size();i++){
            PlaceOrderTmDTO tm = obList.get(i);
            balance += tm.getTotal();
            orderDetails.add(new OrderDetailsDTO(orderId,tm.getItemCode(),tm.getQty(),tm.getUnitPrice(),tm.getTotal()));
        }
        PaymentDetailsDTO paymentDetails = new PaymentDetailsDTO(orderId,customerId,balance,date,time);

        OrderDTO order = new OrderDTO(orderId,date,customerId,salesmen,"pending",orderDetails,paymentDetails);
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);

            for (int i = 0;i<tblView.getItems().size();i++) {
                PlaceOrderTmDTO tm = obList.get(i);
                balance += tm.getTotal();
                lblNetTotal.setText(String.valueOf(balance));
            }
            boolean adeddOrder =orderService.saveOrder(order);
            if (adeddOrder) {
                boolean addOrderDetails = orderDetailsService.saveOrderDetails(orderDetails);
                if (addOrderDetails){
                    boolean stokUpdate = stockService.updateQTY(orderDetails);
                    if (stokUpdate){
                        boolean addPaymentDetails = paymentDetailsService.savePaymentDetails(paymentDetails);
                        if (addPaymentDetails){
                            DBConnection.getInstance().getConnection().commit();
                            new Alert(Alert.AlertType.INFORMATION,"Added Success").show();
                            btnRefreshOnAction(actionEvent);
                        }else{
                            new Alert(Alert.AlertType.WARNING,"Added Fail").show();
                        }
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }
    public void cbmSelectItemsOnAction(ActionEvent actionEvent) {
        String itemName = cmbSelectItemNames.getSelectionModel().getSelectedItem().toString();
        txtSearchitem.setText(itemName);
        try {
            ItemDTO itemDTO = stockService.searchItemByItemName(itemName);
            lblItemCode.setText(itemDTO.getItemCode());
            lblUnitPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
            lblQtyOnHand.setText(String.valueOf(itemDTO.getQtyOnHand()));
            lblItemName.setText(itemDTO.getItemName());
            txtQty.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnRomoveOnAction(ActionEvent actionEvent) {
        PlaceOrderTmDTO tm = (PlaceOrderTmDTO) tblView.getSelectionModel().getSelectedItem();
        tblView.getItems().removeAll(tblView.getSelectionModel().getSelectedItem());
    }

    public void btnHrOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.HR,pane);
    }
    public void cmbSalesmenOnAction(ActionEvent actionEvent) {
       // String salesmen = cbmSalesmen.getSelectionModel().getSelectedItem().toString();

    }

    public void btnRefreshOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SALESBILLING,pane);
    }
    public void btnGenarateBill(ActionEvent actionEvent) {
        HashMap<String, Object> hm = new HashMap<>();
        InputStream inputStream = this.getClass().getResourceAsStream("/lk/ijse/phone/reports/TodayIncome.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, DBConnection.getInstance().getConnection());
//            JasperPrintManager.printReport(jasperPrint,true);
            JasperViewer.viewReport(jasperPrint);
        } catch (JRException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
