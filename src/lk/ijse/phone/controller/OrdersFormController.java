package lk.ijse.phone.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.phone.service.ServiceFactory;
import lk.ijse.phone.service.ServiceTypes;
import lk.ijse.phone.service.custom.OrderService;
import lk.ijse.phone.service.custom.impl.OrderServiceImpl;
import lk.ijse.phone.dto.OrderDTO;
import lk.ijse.phone.util.GetSystemUserDetails;
import lk.ijse.phone.util.Navigation;
import lk.ijse.phone.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersFormController {
    public AnchorPane pane;
    public Label lblLogoutName;
    public TableColumn colOrderID;
    public TableColumn colCustomer;
    public TableColumn colSalesMen;
    public TableColumn colBUildDate;

    public TableView<OrderDTO> tblView;

    private final ObservableList observableList = FXCollections.observableArrayList();
    public TextField txtSearchorders;
    private final OrderService orderService = new OrderServiceImpl();//(OrderService) ServiceFactory.getServiceFactory().getService(ServiceTypes.ORDERS);

    public  void initialize(){
        GetSystemUserDetails systemUserDetails = new GetSystemUserDetails();
        String name1 = systemUserDetails.getName();
        lblLogoutName.setText(name1);
        colOrderID.setCellValueFactory(new PropertyValueFactory("orderId"));
        colBUildDate.setCellValueFactory(new PropertyValueFactory("date"));
        colCustomer.setCellValueFactory(new PropertyValueFactory("customerId"));
        colSalesMen.setCellValueFactory(new PropertyValueFactory("salesmen"));
        ViewOrders();
    }
    private void ViewOrders() {
        try {
           ArrayList<OrderDTO> arrayList  =orderService.getAllOrders();
            for (OrderDTO o :arrayList) {
                observableList.add(o);
                tblView.setItems(observableList);
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

    public void btnStockOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCHSTOCK,pane);

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

    public void btnItemReturnOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RETURNITEM,pane);
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

    public void btnHrOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.HR,pane);
    }
    public void txtPressedKeyOnAction(KeyEvent keyEvent) {
        FilteredList<OrderDTO> filteredData = new FilteredList<>(observableList, b -> true);
        txtSearchorders.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(order -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (order.getOrderId().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (order.getCustomerId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (order.getSalesmen().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }  else if (order.getDate().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }   else
                    return false; // Does not match.
            });
        });
        SortedList<OrderDTO> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblView.comparatorProperty());
        tblView.setItems(sortedData);
    }
}
