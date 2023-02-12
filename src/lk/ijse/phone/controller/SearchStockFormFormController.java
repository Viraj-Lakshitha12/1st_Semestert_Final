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
import lk.ijse.phone.dto.ItemDTO;
import lk.ijse.phone.service.ServiceFactory;
import lk.ijse.phone.service.ServiceTypes;
import lk.ijse.phone.service.custom.StockService;
import lk.ijse.phone.service.custom.impl.StockServiceImpl;
import lk.ijse.phone.util.GetSystemUserDetails;
import lk.ijse.phone.util.Navigation;
import lk.ijse.phone.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchStockFormFormController {
    public AnchorPane pane;
    public Label lblLogoutName;
    public TableView tblView;
    public TableColumn colItemCode;
    public TableColumn colItemname;
    public TableColumn colQtyOnHand;
    public TableColumn colUnitPrice;
    public TextField txtItemCode;

    private final  ObservableList observableList =FXCollections.observableArrayList();
    private final StockService stockService = (StockService) ServiceFactory.getServiceFactory().getService(ServiceTypes.STOCK);

    public void initialize(){
        GetSystemUserDetails systemUserDetails = new GetSystemUserDetails();
        String name1 = systemUserDetails.getName();
        lblLogoutName.setText(name1);
        LoadAllStock();
    }
    private void LoadAllStock() {
        colItemCode.setCellValueFactory(new PropertyValueFactory("itemCode"));
        colItemname.setCellValueFactory(new PropertyValueFactory("itemName"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory("qtyOnHand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        try {
            ArrayList<ItemDTO> arrayList =stockService.getAllStockData();
            for (ItemDTO i :arrayList) {
                observableList.add(i);
                tblView.setItems(observableList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnSearchStockOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCHSTOCK,pane);
    }

    public void btnStockOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCHSTOCK,pane);
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
    public void btnStockArrievdersOnAciton(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STOCKARRIEVD,pane);
    }
    public void btnStockPalce(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STOCKPLACE,pane);
    }
    public void btnShowStock(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SHOWSTOCK,pane);
    }
    public void txtPressdKey(KeyEvent keyEvent) {
        FilteredList<ItemDTO> filteredData = new FilteredList<>(observableList, b -> true);
        txtItemCode.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (item.getItemName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (item.getItemCode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (String.valueOf(item.getUnitPrice()).indexOf(lowerCaseFilter)!=-1)
                    return true;
                else
                    return false; // Does not match.
            });
        });
        SortedList<ItemDTO> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblView.comparatorProperty());
        tblView.setItems(sortedData);

    }
}
