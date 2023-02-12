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
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import lk.ijse.phone.dto.OrderDetailsDTO;
import lk.ijse.phone.service.ServiceFactory;
import lk.ijse.phone.service.ServiceTypes;
import lk.ijse.phone.service.custom.OrderDetailsService;
import lk.ijse.phone.service.custom.impl.OrderDetailsServiceImpl;
import lk.ijse.phone.util.GetOrderId;
import lk.ijse.phone.util.GetSystemUserDetails;
import lk.ijse.phone.util.Navigation;
import lk.ijse.phone.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
public class OrderDetailsShowFormController {
    public Label lblLogoutName;
    public TableColumn colOrderId;
    public TableColumn colItemCode;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colPrice;
    public AnchorPane pane;
    public TableView tbView;
    public TextField txtSeachorderDetails;
    private  ObservableList observableList = FXCollections.observableArrayList();
    private final OrderDetailsService orderDetailsService = new OrderDetailsServiceImpl();// (OrderDetailsService) ServiceFactory.getServiceFactory().getService(ServiceTypes.ORDER_DETAILS);

    public void initialize() {
        GetSystemUserDetails systemUserDetails = new GetSystemUserDetails();
        String name1 = systemUserDetails.getName();
        lblLogoutName.setText(name1);
        loadOrderDetails();
    }
    private void loadOrderDetails() {
        colOrderId.setCellValueFactory(new PropertyValueFactory("orderId"));
        colItemCode.setCellValueFactory(new PropertyValueFactory("itemCode"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colPrice.setCellValueFactory(new PropertyValueFactory("price"));

        GetOrderId getOrderId = new GetOrderId();
        String orderId = getOrderId.getOrderId();
        try {
            ArrayList<OrderDetailsDTO> arrayList = orderDetailsService.getAllOrderDetails(orderId);
            for (OrderDetailsDTO o :arrayList) {
                observableList.add(o);
                tbView.setItems(observableList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnPendingOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PENDINGORDERS,pane);
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PENDINGORDERS,pane);
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

    public void btnHrOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.HR,pane);
    }

    public void btnStockOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCHSTOCK,pane);
    }

    public void txtKeyPressed(KeyEvent keyEvent) {
        FilteredList<OrderDetailsDTO> filteredData = new FilteredList<>(observableList, b -> true);
        txtSeachorderDetails.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (item.getItemCode().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (item.getItemName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(item.getQty()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }  else if (String.valueOf(item.getUnitPrice()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }  else if (String.valueOf(item.getPrice()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }   else
                    return false; // Does not match.
            });
        });
        SortedList<OrderDetailsDTO> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tbView.comparatorProperty());
        tbView.setItems(sortedData);

    }
}
