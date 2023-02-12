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
import lk.ijse.phone.service.custom.CustomerService;
import lk.ijse.phone.dto.CustomerDTO;
import lk.ijse.phone.util.GetSystemUserDetails;
import lk.ijse.phone.util.Navigation;
import lk.ijse.phone.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class AddCustomerFormController {
    public AnchorPane pane;
    public Label lblLogoutName;
    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerContactNo;
    public TableColumn colCustomerName;
    public TableColumn colCID;
    public TableColumn colContact;
    public TableColumn colAddress;
    public TableView<CustomerDTO> tblView;
    public TextField txtSeachCustomer;
    public JFXTextField txtDateofArrievd;
    public JFXTextField txtItemCode;
    public JFXTextField txtQty;
    public JFXTextField txtItemname;
    private Pattern id;
    private Pattern name;
    private Pattern address;
    private Pattern contactNo;
    private ObservableList observableList = FXCollections.observableArrayList();
    private final CustomerService customerService = (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceTypes.CUSTOMER);

    public void initialize(){
        GetSystemUserDetails systemUserDetails = new GetSystemUserDetails();
        String name1 = systemUserDetails.getName();
        lblLogoutName.setText(name1);
        loadAllCustomerData();
        pattern();
    }
    private void pattern() {
        id = Pattern.compile("^[C][0-9]{3,}$");
        name = Pattern.compile("^[A-Za-z0-9]{4,}$");
        address =  Pattern.compile("^[A-Za-z0-9]{3,}$");
        contactNo = Pattern.compile("^[0-9]{10}$");
    }
    private void loadAllCustomerData() {
        colCID.setCellValueFactory(new PropertyValueFactory("customerId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory("customerName"));
        colContact.setCellValueFactory(new PropertyValueFactory("phoneNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        try {
            ArrayList<CustomerDTO> arrayList =  customerService.getAllCustomers();
            for (CustomerDTO c:arrayList) {
                observableList.add(c);
                tblView.setItems(observableList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
        Navigation.navigate(Routes.ADDSTOCK,pane);
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDCUSTOMERS,pane);
    }

    public void btnSuppliyersOnAciton(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SUPPLIYER,pane);
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEE,pane);
    }
    public void btnAddCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        boolean isUseridMatched = id.matcher(txtCustomerId.getText()).matches();
        boolean isUserName = name.matcher(txtCustomerName.getText()).matches();
        boolean isUserAddresss = address.matcher(txtCustomerAddress.getText()).matches();
        boolean isUserContact = contactNo.matcher(txtCustomerContactNo.getText()).matches();

        if (isUseridMatched){
            if (isUserName){
                if (isUserAddresss){
                    if (isUserContact){
                        String name = txtCustomerName.getText();
                        String id = txtCustomerId.getText();
                        String address = txtCustomerAddress.getText();
                        String contactNo = txtCustomerContactNo.getText();

                        if (txtCustomerName.getText().isEmpty() | txtCustomerId.getText().isEmpty() | txtCustomerAddress.getText().isEmpty() | txtCustomerContactNo.getText().isEmpty()) {
                            new Alert(Alert.AlertType.INFORMATION, "Please Enter All Details").show();
                            txtCustomerName.setFocusColor(Paint.valueOf("Red"));
                            txtCustomerName.requestFocus();
                        } else {
                            try {
                                 if (customerService.saveCustomer(new CustomerDTO(id, name, address, contactNo))){
                                     new Alert(Alert.AlertType.CONFIRMATION, "Successfully Added Customer !").show();
                                     Navigation.navigate(Routes.ADDCUSTOMERS,pane);
                                 }else{
                                     new Alert(Alert.AlertType.ERROR, "Failed to Added the Customer !").show();
                                 }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }else{
                        txtCustomerContactNo.setFocusColor(Paint.valueOf("Red"));
                        txtCustomerContactNo.requestFocus();
                    }
                }else{
                    txtCustomerAddress.setFocusColor(Paint.valueOf("Red"));
                    txtCustomerAddress.requestFocus();
                }
            }else{
                txtCustomerName.setFocusColor(Paint.valueOf("Red"));
                txtCustomerName.requestFocus();
            }
        }else{
            txtCustomerId.setFocusColor(Paint.valueOf("Red"));
            txtCustomerId.requestFocus();
        }
    }
    public void txtSearchCustomerOnAction(ActionEvent actionEvent) {
         String id = txtCustomerId.getText();
         try {
             Optional<CustomerDTO> customer = customerService.searchCustomer(id);
             if(!customer.isPresent()){
                 new Alert(Alert.AlertType.WARNING,"Customer Not Found").show();
             }else {
                 txtCustomerName.setText(customer.get().getCustomerName());
                 txtCustomerAddress.setText(customer.get().getAddress());
                 txtCustomerContactNo.setText(customer.get().getPhoneNumber());
             }
         } catch (SQLException  e) {
             throw new RuntimeException(e);
         } catch (ClassNotFoundException e) {
             throw new RuntimeException(e);
         }
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        boolean isUseridMatched = id.matcher(txtCustomerId.getText()).matches();
        boolean isUserName = name.matcher(txtCustomerName.getText()).matches();
        boolean isUserAddresss = address.matcher(txtCustomerAddress.getText()).matches();
        boolean isUserContact = contactNo.matcher(txtCustomerContactNo.getText()).matches();

        if (isUseridMatched){
            if (isUserName){
                if (isUserAddresss){
                    if (isUserContact){
                        String customerId = txtCustomerId.getText();
                        String name = txtCustomerName.getText();
                        String address = txtCustomerAddress.getText();
                        String contactNo = txtCustomerContactNo.getText();
                        try {
                            if(customerService.updateCustomer(new CustomerDTO(customerId, name, address, contactNo))){
                                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Updated !").show();
                                Navigation.navigate(Routes.ADDCUSTOMERS,pane);
                            }else{
                                new Alert(Alert.AlertType.ERROR,"Failed to Update the Customer !").show();
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else{
                        txtCustomerContactNo.setFocusColor(Paint.valueOf("Red"));
                        txtCustomerContactNo.requestFocus();
                    }
                }else{
                    txtCustomerAddress.setFocusColor(Paint.valueOf("Red"));
                    txtCustomerAddress.requestFocus();
                }
            }else{
                txtCustomerName.setFocusColor(Paint.valueOf("Red"));
                txtCustomerName.requestFocus();
            }
        }else{
            txtCustomerId.setFocusColor(Paint.valueOf("Red"));
            txtCustomerId.requestFocus();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = txtCustomerId.getText();
        try {
           if (customerService.deleteCustomer(customerId)){
               new Alert(Alert.AlertType.CONFIRMATION, "Successfully Delete !").show();
               Navigation.navigate(Routes.ADDCUSTOMERS,pane);
           }else{
               new Alert(Alert.AlertType.ERROR,"Failed to Delete the Customer !").show();
           }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        txtSearchCustomerOnAction(actionEvent);
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDCUSTOMERS,pane);
    }

    public void txtkeyPressed(KeyEvent keyEvent) {
        FilteredList<CustomerDTO> filteredData = new FilteredList<>(observableList, b -> true);
        txtSeachCustomer.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(customerDetails -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (customerDetails.getCustomerId().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (customerDetails.getCustomerName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }else if (customerDetails.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (customerDetails.getPhoneNumber().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }else
                    return false; // Does not match.
            });
        });
        SortedList<CustomerDTO> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblView.comparatorProperty());
        tblView.setItems(sortedData);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN,pane);
    }

    public void btnAddPlaces(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDPLACES,pane);
    }
    public void btnAddStockArrievd(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDSTOCKARRIEVD,pane);
    }
    public void btnSearchStock(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCHSTOCK,pane);
    }
    public void btnAddSystemUrsersOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDSYSTEMUSERS,pane);
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }
}
