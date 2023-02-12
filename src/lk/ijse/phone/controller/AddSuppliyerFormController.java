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
import lk.ijse.phone.service.ServiceFactory;
import lk.ijse.phone.service.ServiceTypes;
import lk.ijse.phone.service.custom.SupplierService;
import lk.ijse.phone.dto.SupplierDTO;
import lk.ijse.phone.util.GetSystemUserDetails;
import lk.ijse.phone.util.Navigation;
import lk.ijse.phone.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class
AddSuppliyerFormController {
    public Label lblLogoutName;
    public AnchorPane pane;
    public JFXTextField txtContact;
    public JFXTextField txtSupplierId;
    public JFXTextField txtSupplierName;
    public JFXTextField txtSupplierAddress;
    public JFXTextField supplierBrand;
    public TableColumn colSupplierID;
    public TableColumn colSupplierName;
    public TableColumn colBrand;
    public TableColumn colContact;
    public TableColumn colAddress;
    public TableView tblVIew;
    public TableView tblViews;
    public TableColumn colSid;
    public TextField txtSupplier;
    private Pattern id;
    private Pattern name;
    private Pattern address;
    private Pattern brand;
    private Pattern contactNo;
    private ObservableList observableList = FXCollections.observableArrayList();
    private final SupplierService supplierService = (SupplierService) ServiceFactory.getServiceFactory().getService(ServiceTypes.SUPPLIER);

    public void initialize(){
        GetSystemUserDetails systemUserDetails = new GetSystemUserDetails();
        String name1 = systemUserDetails.getName();
        lblLogoutName.setText(name1);
        loadAllData();
        pattern();
    }

    private void pattern() {
        id = Pattern.compile("^[S][0-9]{3,}$");
        name = Pattern.compile("^[a-zA-Z]{3,}$");
        brand = Pattern.compile("^[a-zA-Z]{3,}$");
        address =  Pattern.compile("^[A-Za-z0-9]{3,}$");
        contactNo = Pattern.compile("^[0-9]{10}$");
    }

    private void loadAllData() {
        colSid.setCellValueFactory(new PropertyValueFactory("sid"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory("name"));
        colBrand.setCellValueFactory(new PropertyValueFactory("brand"));
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colContact.setCellValueFactory(new PropertyValueFactory("ContactNo"));

        try {
            ArrayList<SupplierDTO> arrayList =supplierService.getAllSuppliers();
            for (SupplierDTO s:arrayList) {
                observableList.add(s);
                tblViews.setItems(observableList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAINFORM,pane);
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) {
    }

    public void btnAvaOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.AVA,pane);
    }

    public void btnBillOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SALESBILLING,pane);
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDCUSTOMERS,pane);
    }

    public void btnOrdOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PENDINGORDERS,pane);
    }

    public void btnHrOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.HR,pane);
    }
    public void btnAddSupplierOnAction(ActionEvent actionEvent) {

        boolean isUserIdMatched = id.matcher(txtSupplierId.getText()).matches();
        boolean isUserNameMatched = name.matcher(txtSupplierName.getText()).matches();
        boolean isUserBrandMatched = brand.matcher(supplierBrand.getText()).matches();
        boolean isUserContactMatched = contactNo.matcher(txtContact.getText()).matches();
        boolean isUserAddressMatched = address.matcher(txtSupplierAddress.getText()).matches();

        if (isUserIdMatched){
            if (isUserNameMatched){
                if (isUserBrandMatched){
                    if (isUserContactMatched){
                        if (isUserAddressMatched){

                            String sid = txtSupplierId.getText();
                            String name = txtSupplierName.getText();
                            String brand = supplierBrand.getText();
                            String address = txtSupplierAddress.getText();
                            String No = txtContact.getText();

                            if (txtSupplierName.getText().isEmpty() | supplierBrand.getText().isEmpty() | txtSupplierAddress.getText().isEmpty() | txtSupplierAddress.getText().isEmpty() | txtSupplierId.getText().isEmpty()) {
                                new Alert(Alert.AlertType.INFORMATION, "Please Enter All Details").show();
                            } else {
                                try {
                                    boolean added = supplierService.saveSupplier(new SupplierDTO(sid, name, brand, address, No));
                                    if (added) {
                                        new Alert(Alert.AlertType.INFORMATION, "Supplier Added Success").show();
                                        Navigation.navigate(Routes.SUPPLIYER,pane);
                                    } else {
                                        new Alert(Alert.AlertType.INFORMATION, "Supplier Added Fail").show();
                                    }
                                } catch (SQLException | ClassNotFoundException|IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }else{
                            txtSupplierAddress.setFocusColor(Paint.valueOf("Red"));
                            txtSupplierAddress.requestFocus();
                        }
                    }else{
                        txtContact.setFocusColor(Paint.valueOf("Red"));
                        txtContact.requestFocus();
                    }
                }else{
                    supplierBrand.setFocusColor(Paint.valueOf("Red"));
                    supplierBrand.requestFocus();
                }
            }else{
                txtSupplierName.setFocusColor(Paint.valueOf("Red"));
                txtSupplierName.requestFocus();
            }
        }else{
            txtSupplierId.setFocusColor(Paint.valueOf("Red"));
            txtSupplierId.requestFocus();
        }
    }
    public void btnSearchSupplierOnAction(ActionEvent actionEvent) {
        txtSearchSupplierOnAction(actionEvent);
    }
    public void btnUpdateSupplierOnAction(ActionEvent actionEvent) {
        boolean isUserIdMatched = id.matcher(txtSupplierId.getText()).matches();
        boolean isUserNameMatched = name.matcher(txtSupplierName.getText()).matches();
        boolean isUserBrandMatched = brand.matcher(supplierBrand.getText()).matches();
        boolean isUserContactMatched = contactNo.matcher(txtContact.getText()).matches();
        boolean isUserAddressMatched = address.matcher(txtSupplierAddress.getText()).matches();

        if (isUserIdMatched){
            if (isUserNameMatched){
                if (isUserBrandMatched){
                    if (isUserContactMatched){
                        if (isUserAddressMatched){

                            String sid = txtSupplierId.getText();
                            String name = txtSupplierName.getText();
                            String brand = supplierBrand.getText();
                            String address = txtSupplierAddress.getText();
                            String No = txtContact.getText();

                            try {
                                boolean isUpdate = supplierService.updateSupplier(new SupplierDTO(sid, name, brand, address, No));
                                if (isUpdate) {
                                    new Alert(Alert.AlertType.INFORMATION, "Update Success").show();
                                    Navigation.navigate(Routes.SUPPLIYER, pane);
                                } else {
                                    new Alert(Alert.AlertType.WARNING, "Update Fail").show();
                                }
                            } catch (SQLException | ClassNotFoundException | IOException e) {
                                throw new RuntimeException(e);
                            }
                        }else{
                            txtSupplierAddress.setFocusColor(Paint.valueOf("Red"));
                            txtSupplierAddress.requestFocus();
                        }
                    }else{
                        txtContact.setFocusColor(Paint.valueOf("Red"));
                        txtContact.requestFocus();
                    }
                }else{
                    supplierBrand.setFocusColor(Paint.valueOf("Red"));
                    supplierBrand.requestFocus();
                }
            }else{
                txtSupplierName.setFocusColor(Paint.valueOf("Red"));
                txtSupplierName.requestFocus();
            }
        }else{
            txtSupplierId.setFocusColor(Paint.valueOf("Red"));
            txtSupplierId.requestFocus();
        }
    }
    public void btnDeleteSupplierOnAction(ActionEvent actionEvent) {
        try {
            boolean b = supplierService.deleteSupplier(txtSupplierId.getText());
            if (b) {
                new Alert(Alert.AlertType.WARNING, "Supplier Delete Success").show();
                Navigation.navigate(Routes.SUPPLIYER, pane);
            } else {
                new Alert(Alert.AlertType.WARNING, "Supplier Delete Fail").show();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void txtSearchSupplierOnAction(ActionEvent actionEvent) {
        try {
            Optional<SupplierDTO> supplier = supplierService.searchSupplier(txtSupplierId.getText());
            if (!supplier.isPresent()){
                new Alert(Alert.AlertType.WARNING,"Supplier Not Found").show();
               Navigation.navigate(Routes.SUPPLIYER,pane);
            }else{
               txtSupplierName.setText(supplier.get().getName());
               supplierBrand.setText(supplier.get().getBrand());
               txtSupplierAddress.setText(supplier.get().getAddress());
               txtContact.setText(supplier.get().getContactNo());
            }
        } catch (SQLException | ClassNotFoundException |IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SUPPLIYER,pane);
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SUPPLIYER,pane);
    }

    public void txtKeyPressed(KeyEvent keyEvent) {
        FilteredList<SupplierDTO> filteredData = new FilteredList<>(observableList, b -> true);
        txtSupplier.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(supplier -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (supplier.getSid().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (supplier.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }else if (supplier.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (supplier.getContactNo().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }else
                    return false; // Does not match.
            });
        });
        SortedList<SupplierDTO> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblViews.comparatorProperty());
        tblViews.setItems(sortedData);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN,pane);
    }

    public void btnAddPlaces(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDPLACES,pane);
    }
    public void btnStockArrievd(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDSTOCKARRIEVD,pane);
    }
}
