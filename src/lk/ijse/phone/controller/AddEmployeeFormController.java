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
import lk.ijse.phone.service.custom.EmployeeService;
import lk.ijse.phone.dto.EmployeeDTO;
import lk.ijse.phone.util.GetSystemUserDetails;
import lk.ijse.phone.util.Navigation;
import lk.ijse.phone.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class AddEmployeeFormController {
    public AnchorPane pane;
    public Label lblLogoutName;
    public JFXTextField txtEmployeeId;
    public JFXTextField txtEmployeeName;
    public JFXTextField txtEmployeeAddress;

    public JFXTextField txtEmployeeSalary;
    public TableColumn colEID;
    public TableColumn colEmployeeName;
    public TableColumn colEmployeeAddress;
    public TableColumn colEmployeeSalary;
    public TableView tblView;
    public TextField txtSearch;
    private Pattern id;
    private Pattern name;
    private Pattern address;
    private Pattern salary;
    private  ObservableList observableList = FXCollections.observableArrayList();
    private final EmployeeService employeeService = (EmployeeService) ServiceFactory.getServiceFactory().getService(ServiceTypes.EMPLOYEE);

    public void initialize(){
        GetSystemUserDetails systemUserDetails = new GetSystemUserDetails();
        String name1 = systemUserDetails.getName();
        lblLogoutName.setText(name1);
        loadAllData();
        pattern();
    }
    private void pattern() {
        id = Pattern.compile("^[E][0-9]{3,}$");
        name = Pattern.compile("^[a-z0-9]{4,}$");
        address =  Pattern.compile("^[a-z0-9]{3,}$");
        salary = Pattern.compile("^[0-9]{3,}$");
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

    public void btnOrdOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PENDINGORDERS,pane);
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDCUSTOMERS,pane);
    }

    public void btnHrOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.HR,pane);
    }
    private void loadAllData() {
        colEID.setCellValueFactory(new PropertyValueFactory("employeeId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory("name"));
        colEmployeeAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colEmployeeSalary.setCellValueFactory(new PropertyValueFactory("salary"));
        try {
            ArrayList<EmployeeDTO> arrayList =  employeeService.loadAllEmployeeData();
            for (EmployeeDTO e : arrayList) {
                observableList.add(e);
                tblView.setItems(observableList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void txtSearchEmployeeOnAction(ActionEvent actionEvent) {
      String empid = txtEmployeeId.getText();
        try {
            Optional<EmployeeDTO> employee = employeeService.searchEmployee(empid);
            if (!employee.isPresent()){
                new Alert(Alert.AlertType.INFORMATION,"Employee Not Found").show();
            }else{
                txtEmployeeName.setText(employee.get().getName());
                txtEmployeeAddress.setText(employee.get().getAddress());
                txtEmployeeSalary.setText(String.valueOf(employee.get().getSalary()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnAddEmployeeOnAction(ActionEvent actionEvent) {
        boolean isUseridMatched = id.matcher(txtEmployeeId.getText()).matches();
        boolean isUserNameMatched = name.matcher(txtEmployeeName.getText()).matches();
        boolean isUserAddressMatched = address.matcher(txtEmployeeAddress.getText()).matches();
        boolean isUserSalaryMatched = salary.matcher(txtEmployeeSalary.getText()).matches();

        if (isUseridMatched){
            if (isUserNameMatched){
                if (isUserAddressMatched){
                    if (isUserSalaryMatched){

                        String eId = txtEmployeeId.getText();
                        String name = txtEmployeeName.getText();
                        String address = txtEmployeeAddress.getText();
                        double salary = Double.parseDouble(txtEmployeeSalary.getText());
                        if (txtEmployeeId.getText().isEmpty() | txtEmployeeName.getText().isEmpty() | txtEmployeeAddress.getText().isEmpty() | txtEmployeeSalary.getText().isEmpty() | txtEmployeeName.getText().isEmpty()) {
                            new Alert(Alert.AlertType.INFORMATION, "Please Enter All Details").show();
                        } else {
                            try {
                                if (employeeService.saveEmployee(new EmployeeDTO(eId, name, address, salary))) {
                                    new Alert(Alert.AlertType.INFORMATION, "Added Success").show();
                                    Navigation.navigate(Routes.EMPLOYEE,pane);
                                } else {
                                    new Alert(Alert.AlertType.WARNING, "Added Fail").show();
                                }
                            } catch (SQLException | ClassNotFoundException |IOException e ) {
                                throw new RuntimeException(e);
                            }
                        }
                    }else{
                        txtEmployeeSalary.setFocusColor(Paint.valueOf("Red"));
                        txtEmployeeSalary.requestFocus();
                    }
                }else{
                    txtEmployeeAddress.setFocusColor(Paint.valueOf("Red"));
                    txtEmployeeAddress.requestFocus();
                }
            }else{
                txtEmployeeName.setFocusColor(Paint.valueOf("Red"));
                txtEmployeeName.requestFocus();
            }
        }else{
            txtEmployeeId.setFocusColor(Paint.valueOf("Red"));
            txtEmployeeId.requestFocus();
        }
    }
    public void btnSearchEmployeeOnAction(ActionEvent actionEvent) {
        txtSearchEmployeeOnAction(actionEvent);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        boolean isUseridMatched = id.matcher(txtEmployeeId.getText()).matches();
        boolean isUserNameMatched = name.matcher(txtEmployeeName.getText()).matches();
        boolean isUserAddressMatched = address.matcher(txtEmployeeAddress.getText()).matches();
        boolean isUserSalaryMatched = salary.matcher(txtEmployeeSalary.getText()).matches();

        if (isUseridMatched){
            if (isUserNameMatched){
                if (isUserAddressMatched){
                    if (isUserSalaryMatched){

                        String eId = txtEmployeeId.getText();
                        String name = txtEmployeeName.getText();
                        String address = txtEmployeeAddress.getText();
                        double salary = Double.parseDouble(txtEmployeeSalary.getText());

                        EmployeeDTO employeeDTO = new EmployeeDTO(eId, name, address, salary);
                        try {
                            if (employeeService.updateEmployee(employeeDTO)) {
                                new Alert(Alert.AlertType.INFORMATION, "Update Success").show();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Update Fail").show();
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }else{
                        txtEmployeeSalary.setFocusColor(Paint.valueOf("Red"));
                        txtEmployeeSalary.requestFocus();
                    }
                }else{
                    txtEmployeeAddress.setFocusColor(Paint.valueOf("Red"));
                    txtEmployeeAddress.requestFocus();
                }
            }else{
                txtEmployeeName.setFocusColor(Paint.valueOf("Red"));
                txtEmployeeName.requestFocus();
            }
        }else{
            txtEmployeeId.setFocusColor(Paint.valueOf("Red"));
            txtEmployeeId.requestFocus();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            if (employeeService.deleteEmployee(txtEmployeeId.getText())){
                new Alert(Alert.AlertType.INFORMATION,"Delete Success").show();
                Navigation.navigate(Routes.EMPLOYEE,pane);
            }else{
                new Alert(Alert.AlertType.WARNING,"Delete Fail").show();
            }
        } catch (SQLException | ClassNotFoundException |IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEE,pane);
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEE,pane);
    }
    public void btnStockOnActions(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDSTOCK,pane);
    }

    public void txtkeyPressed(KeyEvent keyEvent) {

        FilteredList<EmployeeDTO> filteredData = new FilteredList<>(observableList, b -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employee -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (employee.getEmployeeId().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (employee.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (employee.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(employee.getSalary()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }   else
                    return false; // Does not match.
            });
        });
        SortedList<EmployeeDTO> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblView.comparatorProperty());
        tblView.setItems(sortedData);

    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN,pane);
    }

    public void btnAddPlaces(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDPLACES,pane);
    }
    public void btnStockArrived(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDSTOCKARRIEVD,pane);
    }
}