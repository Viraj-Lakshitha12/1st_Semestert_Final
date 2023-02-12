package lk.ijse.phone.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.phone.service.ServiceFactory;
import lk.ijse.phone.service.ServiceTypes;
import lk.ijse.phone.service.custom.StockArrivedService;
import lk.ijse.phone.dto.StockArrievdDTO;
import lk.ijse.phone.util.GetSystemUserDetails;
import lk.ijse.phone.util.Navigation;
import lk.ijse.phone.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class AddStockArrievdFromController {
    @FXML
    private AnchorPane pane;
    @FXML
    private Label lblLogoutName;
    @FXML
    private JFXTextField txtDateofArrievd;
    @FXML
    private JFXTextField txtItemCode;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private JFXTextField txtItemname;
    @FXML
    private TableView tblView;
    @FXML
    private TableColumn colDateOfArrievd;
    @FXML
    private TableColumn colItemCode;
    @FXML
    private TableColumn colItemname;
    @FXML
    private TableColumn colQty;
    @FXML
    private TableColumn colSupplierd;
    @FXML
    private TableColumn colSupplierName;
    @FXML
    private TextField txtSearchStockArrievd;
    @FXML
    private JFXTextField txtSupplierId;
    @FXML
    private JFXTextField txtSupplierName;
    private Pattern dateOfArrrievd;
    private Pattern itemCode;
    private Pattern itemName;
    private Pattern qty;
    private Pattern supplierId;
    private Pattern supplierName;

    private ObservableList observableList = FXCollections.observableArrayList();
    private final StockArrivedService stockArrivedService = (StockArrivedService) ServiceFactory.getServiceFactory().getService(ServiceTypes.STOCK_ARRIVED);


    public void initialize(){
        GetSystemUserDetails systemUserDetails = new GetSystemUserDetails();
        String name1 = systemUserDetails.getName();
        lblLogoutName.setText(name1);
        loadArrievdDetails();
        pattern();
    }

    private void pattern() {
        dateOfArrrievd = Pattern.compile("\\d{4}-[01]\\d-[0-3]\\d");
        itemCode = Pattern.compile("^[i][0-9]{3,}$");
        itemName = Pattern.compile("^[A-Za-z0-9]{3,}$");
        qty = Pattern.compile("^[0-9]{1,}$");
        supplierId = Pattern.compile("^[S][0-9]{2,}$");
        supplierName = Pattern.compile("^[A-Za-z]{3,}$");
    }

    private void loadArrievdDetails() {
        colDateOfArrievd.setCellValueFactory(new PropertyValueFactory("dateOfArrrievd"));
        colItemCode.setCellValueFactory(new PropertyValueFactory("itemCode"));
        colItemname.setCellValueFactory(new PropertyValueFactory("itemName"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colSupplierd.setCellValueFactory(new PropertyValueFactory("supplierId"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory("supplierName"));
        try {
            ArrayList<StockArrievdDTO> details = stockArrivedService.GetAllStockArrivedData();
            for (StockArrievdDTO s:details) {
                observableList.add(s);
                tblView.setItems(observableList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnHrOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.HR,pane);
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDCUSTOMERS,pane);
    }

    public void btnStockOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDSTOCK,pane);
    }

    public void btnSuppliyersOnAciton(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SUPPLIYER,pane);
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEE,pane);
    }

    public void btnAddPlaces(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDPLACES,pane);
    }
    public void btnAddStockArrievd(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDSTOCKARRIEVD,pane);
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
    public void txtDateofArrievdOnAction(ActionEvent actionEvent) {
        try {
            Optional<StockArrievdDTO> stockArrived = stockArrivedService.searchStockArrived(txtDateofArrievd.getText());
            if (!stockArrived.isPresent()){
                new Alert(Alert.AlertType.WARNING,"Not Found").show();
            }else{
                txtItemCode.setText(stockArrived.get().getItemCode());
                txtItemname.setText(stockArrived.get().getItemName());
                txtQty.setText(String.valueOf(stockArrived.get().getQty()));
                txtSupplierId.setText(stockArrived.get().getSupplierId());
                txtSupplierName.setText(stockArrived.get().getSupplierName());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnAddStockArrievdOnAction(ActionEvent actionEvent) {
        boolean isUserDateMatched = dateOfArrrievd.matcher(txtDateofArrievd.getText()).matches();
        boolean isUserItemCodeMatched = itemCode.matcher(txtItemCode.getText()).matches();
        boolean isUserItemNameMatched = itemName.matcher(txtItemname.getText()).matches();
        boolean isUserQtyMatched = qty.matcher(txtQty.getText()).matches();
        boolean isUserSupplierIdMatched = supplierId.matcher(txtSupplierId.getText()).matches();
        boolean isUserSupplierNameMatched = supplierName.matcher(txtSupplierName.getText()).matches();

        if (isUserDateMatched){
            if (isUserItemCodeMatched){
                if (isUserItemNameMatched){
                    if (isUserQtyMatched){
                        if (isUserSupplierIdMatched){
                            if (isUserSupplierNameMatched){

                                String date = txtDateofArrievd.getText();
                                String itemCode = txtItemCode.getText();
                                String name = txtItemname.getText();
                                int qty = Integer.parseInt(txtQty.getText());
                                String supplierId = txtSupplierId.getText();
                                String supplierName = txtSupplierName.getText();
                                try {
                                    boolean b = stockArrivedService.saveStockArrived(new StockArrievdDTO(date, itemCode, name, qty, supplierId, supplierName));
                                    if (b){
                                        new Alert(Alert.AlertType.INFORMATION,"Added Success").show();
                                        Navigation.navigate(Routes.ADDSTOCKARRIEVD,pane);
                                    }else{
                                        new Alert(Alert.AlertType.WARNING,"Added Fail").show();
                                    }
                                } catch (SQLException | ClassNotFoundException | IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }else{
                                txtSupplierName.setFocusColor(Paint.valueOf("Red"));
                                txtSupplierName.requestFocus();
                            }
                        }else{
                            txtSupplierId.setFocusColor(Paint.valueOf("Red"));
                            txtSupplierId.requestFocus();
                        }
                    }else{
                        txtQty.setFocusColor(Paint.valueOf("Red"));
                        txtQty.requestFocus();
                    }
                }else{
                    txtItemname.setFocusColor(Paint.valueOf("Red"));
                    txtItemname.requestFocus();
                }
            }else{
                txtItemname.setFocusColor(Paint.valueOf("Red"));
                txtItemname.requestFocus();
            }
        }else {
            txtDateofArrievd.setFocusColor(Paint.valueOf("Red"));
            txtDateofArrievd.requestFocus();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        txtDateofArrievdOnAction(actionEvent);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        boolean isUserDateMatched = dateOfArrrievd.matcher(txtDateofArrievd.getText()).matches();
        boolean isUserItemCodeMatched = itemCode.matcher(txtItemCode.getText()).matches();
        boolean isUserItemNameMatched = itemName.matcher(txtItemname.getText()).matches();
        boolean isUserQtyMatched = qty.matcher(txtQty.getText()).matches();
        boolean isUserSupplierIdMatched = supplierId.matcher(txtSupplierId.getText()).matches();
        boolean isUserSupplierNameMatched = supplierName.matcher(txtSupplierName.getText()).matches();

        if (isUserDateMatched){
            if (isUserItemCodeMatched){
                if (isUserItemNameMatched){
                    if (isUserQtyMatched){
                        if (isUserSupplierIdMatched){
                            if (isUserSupplierNameMatched){

                                String date = txtDateofArrievd.getText();
                                String itemCode = txtItemCode.getText();
                                String name = txtItemname.getText();
                                int qty = Integer.parseInt(txtQty.getText());
                                String supplierId = txtSupplierId.getText();
                                String supplierName = txtSupplierName.getText();
                                try {
                                    boolean b = stockArrivedService.updateStockArrived(new StockArrievdDTO(date, itemCode, name, qty, supplierId, supplierName));
                                    if (b){
                                        new Alert(Alert.AlertType.INFORMATION,"Update Success").show();
                                        Navigation.navigate(Routes.ADDSTOCKARRIEVD,pane);
                                    }else{
                                        new Alert(Alert.AlertType.WARNING,"Update Fail").show();
                                    }
                                } catch (SQLException | ClassNotFoundException |IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }else{
                                txtSupplierName.setFocusColor(Paint.valueOf("Red"));
                                txtSupplierName.requestFocus();
                            }
                        }else{
                            txtSupplierId.setFocusColor(Paint.valueOf("Red"));
                            txtSupplierId.requestFocus();
                        }
                    }else{
                        txtQty.setFocusColor(Paint.valueOf("Red"));
                        txtQty.requestFocus();
                    }
                }else{
                    txtItemname.setFocusColor(Paint.valueOf("Red"));
                    txtItemname.requestFocus();
                }
            }else{
                txtItemname.setFocusColor(Paint.valueOf("Red"));
                txtItemname.requestFocus();
            }
        }else {
            txtDateofArrievd.setFocusColor(Paint.valueOf("Red"));
            txtDateofArrievd.requestFocus();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            boolean b = stockArrivedService.deleteStockArrived(txtDateofArrievd.getText());
            if (b){
                new Alert(Alert.AlertType.INFORMATION,"Delete Success").show();
                Navigation.navigate(Routes.ADDSTOCKARRIEVD,pane);
            }else{
                new Alert(Alert.AlertType.WARNING,"Delete Fail").show();
            }
        } catch (SQLException | ClassNotFoundException| IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN,pane);
    }

    public void btnClearOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDSTOCKARRIEVD,pane);
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDSTOCKARRIEVD,pane);
    }

    public void txtkeyPressed(KeyEvent keyEvent) {
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
                }else if (stockArrievd.getItemName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (stockArrievd.getSupplierId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }else if (stockArrievd.getSupplierName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }else if (String.valueOf(stockArrievd.getQty()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }else
                    return false; // Does not match.
            });
        });
        SortedList<StockArrievdDTO> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblView.comparatorProperty());
        tblView.setItems(sortedData);
    }
}
