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

public class AddStockFormController {
    public AnchorPane pane;
    public Label lblLogoutName;
    public JFXTextField txtEmployeeId;
    public JFXTextField txtItemCode;
    public JFXTextField txtItemName;
    public JFXTextField txtQty;
    public JFXTextField txtUnitPrice;
    public Label txt;
    public TableView tblView;
    public TableColumn colItemCode;
    public TableColumn colitemName;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TextField txtSearch;
    private Pattern itemCode;
    private Pattern itemName;
    private Pattern qty;
    private Pattern unitPrice;

    private ObservableList observableList = FXCollections.observableArrayList();

    private final StockService stockService = (StockService) ServiceFactory.getServiceFactory().getService(ServiceTypes.STOCK);


    public void initialize(){
        GetSystemUserDetails systemUserDetails = new GetSystemUserDetails();
        String name1 = systemUserDetails.getName();
        lblLogoutName.setText(name1);
        loadItemDetails();
        pattern();
    }

    private void pattern() {
        itemCode = Pattern.compile("^[i][0-9]{3,}$");
        itemName = Pattern.compile("^[a-z]{3,}$");
        qty =  Pattern.compile("^[0-9]{1,}$");
        unitPrice = Pattern.compile("^[0-9]{1,}$");

    }

    private void loadItemDetails() {
        colItemCode.setCellValueFactory(new PropertyValueFactory("itemCode"));
        colitemName.setCellValueFactory(new PropertyValueFactory("itemName"));
        colQty.setCellValueFactory(new PropertyValueFactory("qtyOnHand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        try {
            ArrayList<ItemDTO> arrayList = stockService.getAllStockData();
            for (ItemDTO i :arrayList) {
                observableList.add(i);
                tblView.setItems(observableList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDCUSTOMERS,pane);
    }

    public void btnStockOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCHSTOCK,pane);
    }

    public void btnSuppliyersOnAciton(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SUPPLIYER,pane);
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEE,pane);
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
        Navigation.navigate(Routes.ADDCUSTOMERS,pane);
    }

    public void btnHrOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.HR,pane);
    }
    public void btnStockOnActions(ActionEvent actionEvent) {
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN,pane);
    }

    public void btnClearOnAction(ActionEvent actionEvent) throws IOException {
         Navigation.navigate(Routes.ADDSTOCK,pane);
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDSTOCK,pane);
    }
    public void btnAddStockOnAction(ActionEvent actionEvent) {

        boolean isUserItemCodeMatched = itemCode.matcher(txtItemCode.getText()).matches();
        boolean isUserItemNameMatched = itemName.matcher(txtItemName.getText()).matches();
        boolean isUserQtyMatched = qty.matcher(txtQty.getText()).matches();
        boolean isUserUnitPriceMatched = unitPrice.matcher(txtUnitPrice.getText()).matches();

        if (isUserItemCodeMatched){
            if (isUserItemNameMatched){
                if(isUserQtyMatched){
                    if (isUserUnitPriceMatched){

                        String itemCode = txtItemCode.getText();
                        String itemname = txtItemName.getText();
                        int qty = Integer.parseInt(txtQty.getText());
                        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
                        try {
                            boolean  update =stockService.saveStock(new ItemDTO(itemCode,itemname,qty,unitPrice));
                            if (update){
                                new Alert(Alert.AlertType.INFORMATION,"Save Success ").show();
                                Navigation.navigate(Routes.ADDSTOCK,pane);
                            }else{
                                new Alert(Alert.AlertType.WARNING,"Save Fail ").show();
                            }
                        } catch (SQLException | ClassNotFoundException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else{
                        txtUnitPrice.setFocusColor(Paint.valueOf("Red"));
                        txtUnitPrice.requestFocus();
                    }
                }else{
                    txtQty.setFocusColor(Paint.valueOf("Red"));
                    txtQty.requestFocus();
                }
            }else{
                txtItemName.setFocusColor(Paint.valueOf("Red"));
                txtItemName.requestFocus();
            }
        }else{
            txtItemCode.setFocusColor(Paint.valueOf("Red"));
            txtItemCode.requestFocus();
        }
    }
    public void txtSearchStockOnAction(ActionEvent actionEvent){
        try {
            Optional<ItemDTO> item = stockService.searchStock(txtItemCode.getText());
            if (!item.isPresent()){
                new Alert(Alert.AlertType.WARNING," Item Not Found ").show();
            }else{
                txtItemName.setText(item.get().getItemName());
                txtQty.setText(String.valueOf(item.get().getQtyOnHand()));
                txtUnitPrice.setText(String.valueOf(item.get().getUnitPrice()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtKeyPressed(KeyEvent keyEvent) {
        FilteredList<ItemDTO> filteredData = new FilteredList<>(observableList, b -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
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
        sortedData.comparatorProperty().bind(tblView.comparatorProperty());
        tblView.setItems(sortedData);

    }

    public void btnAddPlaces(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDPLACES,pane);
    }

    public void btnStockArrievd(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDSTOCKARRIEVD,pane);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        txtSearchStockOnAction(actionEvent);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean isUserItemCodeMatched = itemCode.matcher(txtItemCode.getText()).matches();
        boolean isUserItemNameMatched = itemName.matcher(txtItemName.getText()).matches();
        boolean isUserQtyMatched = qty.matcher(txtQty.getText()).matches();
        boolean isUserUnitPriceMatched = unitPrice.matcher(txtUnitPrice.getText()).matches();

        if (isUserItemCodeMatched){
            if (isUserItemNameMatched){
                if(isUserQtyMatched){
                    if (isUserUnitPriceMatched){

                        String itemCode = txtItemCode.getText();
                        String itemname = txtItemName.getText();
                        int qty = Integer.parseInt(txtQty.getText());
                        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
                        try {
                            boolean updateStock =stockService.updateStock(new ItemDTO(itemCode,itemname,qty,unitPrice));
                            if (updateStock){
                                new Alert(Alert.AlertType.INFORMATION,"update Success ").show();
                                Navigation.navigate(Routes.ADDSTOCK,pane);
                            }else {
                                new Alert(Alert.AlertType.WARNING,"update Fail ").show();
                            }
                        } catch (SQLException | ClassNotFoundException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else{
                        txtUnitPrice.setFocusColor(Paint.valueOf("Red"));
                        txtUnitPrice.requestFocus();
                    }
                }else{
                    txtQty.setFocusColor(Paint.valueOf("Red"));
                    txtQty.requestFocus();
                }
            }else{
                txtItemName.setFocusColor(Paint.valueOf("Red"));
                txtItemName.requestFocus();
            }
        }else{
            txtItemCode.setFocusColor(Paint.valueOf("Red"));
            txtItemCode.requestFocus();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            boolean delete = stockService.deleteStock( txtItemCode.getText());
            if (delete){
                new Alert(Alert.AlertType.INFORMATION,"Delete Success ").show();
                Navigation.navigate(Routes.ADDSTOCK,pane);
            }else{
                new Alert(Alert.AlertType.WARNING,"Delete Fail ").show();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
