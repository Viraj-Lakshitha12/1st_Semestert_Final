package lk.ijse.phone.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.phone.dto.ItemDTO;
import lk.ijse.phone.service.ServiceFactory;
import lk.ijse.phone.service.ServiceTypes;
import lk.ijse.phone.service.custom.StockService;
import lk.ijse.phone.util.GetSystemUserDetails;
import lk.ijse.phone.util.Navigation;
import lk.ijse.phone.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class AvaFormController {
    public AnchorPane pane;
    public Label lblLogoutName;
    public JFXTextField txtItemCode;
    public JFXTextField txtDescription;
    public TableView tblCheckItem;
    public TableColumn colDr;
    public TableColumn colPrice;
    public TableColumn colQtyOnHand;
    public ComboBox<String> cmbItemName;
    public TableColumn colqtyOnHand;
    public TableColumn colUnitPrice;
    public TableColumn colItemName;
    public TableView tbl2View;
    public TableColumn tblItemCode;
    public TableColumn tblItemname;
    public TableColumn tblqtyOnHand;
    public TableColumn tblUnitPrice;
    public TextField txtSearch;

    private Pattern ItemCode;

    private ObservableList oblist = FXCollections.observableArrayList();
    private ObservableList observableList = FXCollections.observableArrayList();
    private final StockService stockService = (StockService) ServiceFactory.getServiceFactory().getService(ServiceTypes.STOCK);


    public void initialize(){
        GetSystemUserDetails systemUserDetails = new GetSystemUserDetails();
        String name1 = systemUserDetails.getName();
        lblLogoutName.setText(name1);
        ItemCode = Pattern.compile("^[i][0-9]{3,}$");
        loadItemNames();
        loadItemDetails();


    }

    private void loadItemDetails() {
        try {
            tblItemCode.setCellValueFactory(new PropertyValueFactory("itemCode"));
            tblItemname.setCellValueFactory(new PropertyValueFactory("itemName"));
            tblqtyOnHand.setCellValueFactory(new PropertyValueFactory("qtyOnHand"));
            tblUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
            ArrayList <ItemDTO>arrayList =stockService.getAllStockData();
            for (ItemDTO i:arrayList) {
                observableList.add(i);
                tbl2View.setItems(observableList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadItemNames() {
        colItemName.setCellValueFactory(new PropertyValueFactory("itemName"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        colqtyOnHand.setCellValueFactory(new PropertyValueFactory("qtyOnHand"));
        try {
            ArrayList<String> arrayList = stockService.getAllItemNames();
            for (String i :arrayList) {
              oblist.add(i);
                cmbItemName.setItems(oblist);
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

    public void btnLogoutOnAction(ActionEvent actionEvent) {
    }
    public void btnCheckAvailabilityOnAction(ActionEvent actionEvent) {
        boolean isUserNameMatched = ItemCode.matcher(txtItemCode.getText()).matches();
        if (isUserNameMatched){
        }else {
            txtItemCode.setFocusColor(Paint.valueOf("Red"));
            txtDescription.clear();
            txtItemCode.requestFocus();
        }
        try {
            Optional<ItemDTO> item = stockService.searchStock(txtItemCode.getText());
            if (item.isPresent()){
                ObservableList<ItemDTO> observableList = FXCollections.observableArrayList();
                observableList.add(new ItemDTO(item.get().getItemCode(),item.get().getItemName(),item.get().getQtyOnHand(),item.get().getUnitPrice()));
                tblCheckItem.setItems(observableList);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnStockOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCHSTOCK,pane);
    }
    public void cmbOnAction(ActionEvent actionEvent) {
        try {
            ItemDTO itemDTO = stockService.searchItemByItemName(cmbItemName.getSelectionModel().getSelectedItem().toString());
            txtItemCode.setText(itemDTO.getItemCode());
            txtDescription.setText(itemDTO.getItemName());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void txtItemCodeOnAction(ActionEvent actionEvent) {
        try {
            boolean isUserNameMatched = ItemCode.matcher(txtItemCode.getText()).matches();
            if (isUserNameMatched){
                Optional<ItemDTO> item = stockService.searchStock(txtItemCode.getText());
                txtDescription.setText(item.get().getItemName());
            }else {
                txtItemCode.setFocusColor(Paint.valueOf("Red"));
                txtDescription.clear();
                txtItemCode.requestFocus();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnHrOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.HR,pane);
    }
    public void txtKeyPresed(KeyEvent keyEvent) {
        FilteredList<ItemDTO> filteredData = new FilteredList<>(observableList, b -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (item.getItemCode().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (item.getItemName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(item.getQtyOnHand()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }  else if (String.valueOf(item.getUnitPrice()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }   else
                    return false; // Does not match.
            });
        });
        SortedList<ItemDTO> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tbl2View.comparatorProperty());
        tbl2View.setItems(sortedData);

    }
}
