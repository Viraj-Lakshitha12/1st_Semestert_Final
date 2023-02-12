package lk.ijse.phone.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.phone.dao.custom.OrderDAO;
import lk.ijse.phone.dao.custom.impl.OrderDAOImpl;
import lk.ijse.phone.service.ServiceFactory;
import lk.ijse.phone.service.ServiceTypes;
import lk.ijse.phone.service.custom.OrderService;
import lk.ijse.phone.service.custom.PendingOrderService;
import lk.ijse.phone.service.custom.impl.OrderServiceImpl;
import lk.ijse.phone.service.custom.impl.PendingOrderServiceImpl;
import lk.ijse.phone.dto.*;
import lk.ijse.phone.util.GetOrderId;
import lk.ijse.phone.util.GetSystemUserDetails;
import lk.ijse.phone.util.Navigation;
import lk.ijse.phone.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class PendingOrderFormController {
    public AnchorPane pane;
    public Label lblLogoutName;
    public TableView tblPendingOrders;
    public TableColumn colOrderId;
    public TableColumn colItemCode;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotalprice;
    public TableColumn colDate;
    public TableColumn colCustomerId;
    public TableColumn colSalesmen;
    public TableColumn colStatus;
    public TableColumn coloOrderids;
    public TableColumn colName;
    public TableColumn combobox;
    public TextField txtSearchorders;
    public TableColumn colAction;
    private ObservableList<PendingOrderTmDTO> observableList = FXCollections.observableArrayList();
    private  PendingOrderService pendingOrderService =new PendingOrderServiceImpl();
    private  OrderService orderService = new OrderServiceImpl() ;
    //        this.pendingOrderService = (PendingOrderService) ServiceFactory.getServiceFactory().getService(ServiceTypes.PENDING_ORDERS);
//        this.orderService = (OrderService) ServiceFactory.getServiceFactory().getService(ServiceTypes.ORDERS);

    public void initialize(){

        GetSystemUserDetails systemUserDetails = new GetSystemUserDetails();
        String name1 = systemUserDetails.getName();
        lblLogoutName.setText(name1);
        loadPendingOrderData();
    }
    private void loadPendingOrderData() {
        colOrderId.setCellValueFactory(new PropertyValueFactory("orderId"));
        colItemCode.setCellValueFactory(new PropertyValueFactory("date"));
        colQty.setCellValueFactory(new PropertyValueFactory("customerId"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("salesmen"));
        combobox.setCellValueFactory(new PropertyValueFactory("comboBox"));
        colAction.setCellValueFactory(new PropertyValueFactory("button"));

        try {
            ArrayList <PendingOrderTmDTO>arrayList =pendingOrderService.getAllPendingOrders();

            for (PendingOrderTmDTO o :arrayList) {
                observableList.add(o);
                for (int i = 0;i<tblPendingOrders.getItems().size();i++) {
                    PendingOrderTmDTO tm = observableList.get(i);
                    ComboBox comboBox  = tm.getComboBox();
                    comboBox.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String orderId = tm.getOrderId();
                            String date = tm.getDate();
                            String customerId = tm.getCustomerId();
                            String salesmen = tm.getSalesmen();
                            String status = "packed";
                            OrderDTO orderDTO = new OrderDTO(orderId,date,customerId,salesmen,status);
                            try {
                                boolean update =orderService.updateOrder(orderDTO);
                                if (update){
                                    new Alert(Alert.AlertType.INFORMATION,"Packed Success").show();
                                }else{
                                    new Alert(Alert.AlertType.WARNING,"Packed Fail").show();
                                }
                            } catch (SQLException | ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                }
                tblPendingOrders.setItems(observableList);
               o.getComboBox().getSelectionModel().selectFirst();
            }
            for (int i = 0;i<tblPendingOrders.getItems().size();i++) {
                PendingOrderTmDTO tm = observableList.get(i);
                Button button = tm.getButton();
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            new GetOrderId(tm.getOrderId());
                            Navigation.navigate(Routes.SHOWORDERDETAILS,pane);

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });
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

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAINFORM,pane);
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN,pane);
    }
    public void btnPendingOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PENDINGORDERS,pane);

    }

    public void btnStockOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCHSTOCK,pane);
    }

    public void btnHrOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.HR,pane);
    }
    public void txtKeypressedOnAction(KeyEvent keyEvent) {
        FilteredList<PendingOrderTmDTO> filteredData = new FilteredList<>(observableList, b -> true);
        txtSearchorders.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (item.getOrderId().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (item.getCustomerId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(item.getCustomerId()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }  else if (String.valueOf(item.getSalesmen()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }   else
                    return false; // Does not match.
            });
        });
        SortedList<PendingOrderTmDTO> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblPendingOrders.comparatorProperty());
        tblPendingOrders.setItems(sortedData);

    }
}
