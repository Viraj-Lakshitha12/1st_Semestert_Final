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
import lk.ijse.phone.service.ServiceFactory;
import lk.ijse.phone.service.ServiceTypes;
import lk.ijse.phone.service.custom.SystemUserService;
import lk.ijse.phone.dto.SystemUsersDTO;
import lk.ijse.phone.util.Navigation;
import lk.ijse.phone.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class AddSystemUserFormController {
    public AnchorPane pane;
    public Label lblLogoutName;
    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerContactNo;
    public TableView tblView;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colEmail;
    public TableColumn colUserRank;
    public TableColumn colPassword;
    public TextField txtSearchSystemUrsers;
    public JFXTextField txtPassword;
    public JFXTextField txtUrserid;
    public JFXTextField txtName;
    public JFXTextField txtRank;
    public JFXTextField txtEmail;
    private ObservableList observableList = FXCollections.observableArrayList();
    private final SystemUserService systemUserService = (SystemUserService) ServiceFactory.getServiceFactory().getService(ServiceTypes.SYSTEM_USERS);

    public void initialize(){
        loadTableData();
    }
    private void loadTableData() {
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colUserRank.setCellValueFactory(new PropertyValueFactory("rank"));
        colPassword.setCellValueFactory(new PropertyValueFactory("password"));
        try {
            ArrayList<SystemUsersDTO> arrayList = systemUserService.getAllSystemUsers();
            for (SystemUsersDTO s:arrayList) {
                observableList.add(s);
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

    public void btnAddPlaces(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDPLACES,pane);
    }
    public void btnAddStockArrievd(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDSTOCK,pane);
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
    public void btnSearchStock(ActionEvent actionEvent) {
        txtSearchCustomerOnAction(actionEvent);
    }
    public void txtSearchCustomerOnAction(ActionEvent actionEvent) {
        try {
            Optional<SystemUsersDTO> systemUsers = systemUserService.searchSystemUsers(txtUrserid.getText());
            if (!systemUsers.isPresent()){
                new Alert(Alert.AlertType.WARNING,"System User Not Found").show();
            }else{
                txtUrserid.setText(systemUsers.get().getId());
                txtName.setText(systemUsers.get().getName());
                txtEmail.setText(systemUsers.get().getEmail());
                txtRank.setText(systemUsers.get().getRank());
                txtPassword.setText(systemUsers.get().getPassword());
            }
        } catch (SQLException |ClassNotFoundException  e) {
            throw new RuntimeException(e);
        }
    }
    public void btnAddSystemUrsersOnAction(ActionEvent actionEvent) {
        String id = txtUrserid.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String rank = txtRank.getText();
        String password = txtPassword.getText();
        try {
            boolean added = systemUserService.saveSystemUsers(new SystemUsersDTO(id,name,email,rank,password));
            if (added){
                new Alert(Alert.AlertType.INFORMATION,"Added Success").show();
                Navigation.navigate(Routes.ADDSYSTEMUSERS,pane);
            }else{
                new Alert(Alert.AlertType.WARNING,"Added Fail").show();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws RuntimeException {
       txtSearchCustomerOnAction(actionEvent);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtUrserid.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String rank = txtRank.getText();
        String password = txtPassword.getText();
        try {
            boolean update = systemUserService.updateSystemUsers(new SystemUsersDTO(id, name, email, rank, password));
            if (update) {
                new Alert(Alert.AlertType.INFORMATION, "Update  Success").show();
                Navigation.navigate(Routes.ADDSYSTEMUSERS, pane);
            } else {
                new Alert(Alert.AlertType.WARNING, "Update Fail").show();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            boolean delete = systemUserService.deleteSystemUsers(txtUrserid.getText());
            if (delete) {
                new Alert(Alert.AlertType.INFORMATION, "Delete  Success").show();
                Navigation.navigate(Routes.ADDSYSTEMUSERS, pane);
            } else {
                new Alert(Alert.AlertType.WARNING, "Delete Fail").show();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN,pane);
    }

    public void btnClearOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDSYSTEMUSERS,pane);
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDSYSTEMUSERS,pane);
    }

    public void txtKeyPressed(KeyEvent keyEvent) {
        FilteredList<SystemUsersDTO> filteredData = new FilteredList<>(observableList, b -> true);
        txtUrserid.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(stockArrievd -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (stockArrievd.getId().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (stockArrievd.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }else if (stockArrievd.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (stockArrievd.getRank().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }else if (stockArrievd.getPassword().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }else
                    return false; // Does not match.
            });
        });
        SortedList<SystemUsersDTO> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblView.comparatorProperty());
        tblView.setItems(sortedData);
    }
}
