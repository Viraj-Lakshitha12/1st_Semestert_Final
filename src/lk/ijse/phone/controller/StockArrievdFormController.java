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
import lk.ijse.phone.service.custom.StockArrivedService;
import lk.ijse.phone.service.custom.impl.StockArrivedServiceImpl;
import lk.ijse.phone.dto.StockArrievdDTO;
import lk.ijse.phone.util.GetSystemUserDetails;
import lk.ijse.phone.util.Navigation;
import lk.ijse.phone.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockArrievdFormController {
    public AnchorPane pane;
    public Label lblLogoutName;
    public TableView tblView;
    public TableColumn colDate;
    public TableColumn colItemCode;
    public TableColumn colItemname;
    public TableColumn colQty;
    public TableColumn colSupplierId;
    public TableColumn colSuppliername;
    private  final ObservableList observableList = FXCollections.observableArrayList();
    public TextField txtSearchStockArrievd;
    private final StockArrivedService stockArrivedService = (StockArrivedService) ServiceFactory.getServiceFactory().getService(ServiceTypes.STOCK_ARRIVED);

    public void  initialize(){
        GetSystemUserDetails systemUserDetails = new GetSystemUserDetails();
        String name1 = systemUserDetails.getName();
        lblLogoutName.setText(name1);
        loadStockArrievdDetails();
    }

    private void loadStockArrievdDetails() {
        colDate.setCellValueFactory(new PropertyValueFactory("dateOfArrrievd"));
        colItemCode.setCellValueFactory(new PropertyValueFactory("itemCode"));
        colItemname.setCellValueFactory(new PropertyValueFactory("itemName"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory("supplierId"));
        colSuppliername.setCellValueFactory(new PropertyValueFactory("supplierName"));

        try {
            ArrayList<StockArrievdDTO> arrayList = stockArrivedService.GetAllStockArrivedData();
            for (StockArrievdDTO s : arrayList) {
                observableList.add(s);
                tblView.setItems(observableList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSearchStockOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCHSTOCK, pane);
    }
    public void btnStockPalce(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STOCKPLACE,pane);
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

    public void btnHrOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.HR,pane);
    }

    public void btnStockOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCHSTOCK,pane);
    }
    public void btnStockArrievdersOnAciton(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STOCKARRIEVD,pane);
    }
    public void txtPressedKey(KeyEvent keyEvent) {
        FilteredList<StockArrievdDTO> filteredData = new FilteredList<>(observableList, b -> true);
        txtSearchStockArrievd.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(stockArrievd -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (stockArrievd.getDateOfArrrievd().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (stockArrievd.getItemCode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (stockArrievd.getItemName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }  else if (stockArrievd.getSupplierId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }  else if (stockArrievd.getSupplierName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }  else if (String.valueOf(stockArrievd.getQty()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }   else
                    return false; // Does not match.
            });
        });
        SortedList<StockArrievdDTO> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblView.comparatorProperty());
        tblView.setItems(sortedData);

    }
    public void btnStockAddOnActions(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SHOWSTOCK,pane);
    }
}
