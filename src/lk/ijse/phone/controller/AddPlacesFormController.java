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
import lk.ijse.phone.service.custom.PlaceService;
import lk.ijse.phone.dto.RackDetailsDTO;
import lk.ijse.phone.util.GetSystemUserDetails;
import lk.ijse.phone.util.Navigation;
import lk.ijse.phone.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class AddPlacesFormController {
    public JFXTextField txtCustomerContactNo;
    public TableView tblView;
    public TableColumn colRackNumber;
    public TableColumn colItemCode;
    public TableColumn colItemname;
    public TextField txtSeachCustomer;
    public JFXTextField txtRackNumber;
    public JFXTextField txtItemCode;
    public JFXTextField txtItemname;
    public AnchorPane pane;
    public Label lblLogoutName;
    private Pattern rackNumber;
    private Pattern itemCode;
    private Pattern itemName;
    private ObservableList observableList = FXCollections.observableArrayList();
    private final PlaceService placeService = (PlaceService) ServiceFactory.getServiceFactory().getService(ServiceTypes.PLACES);

    public void initialize(){
        GetSystemUserDetails systemUserDetails = new GetSystemUserDetails();
        String name1 = systemUserDetails.getName();
        lblLogoutName.setText(name1);
        loadRackDetails();
        pattern();
    }

    private void pattern() {
        rackNumber = Pattern.compile("^[A-Za-z]{1}[0-9]{2}$");
        itemCode = Pattern.compile("^[i][0-9]{3,}$");
        itemName =  Pattern.compile("^[A-Za-z0-9]{3,}$");
    }
    private void loadRackDetails() {
        colRackNumber.setCellValueFactory(new PropertyValueFactory("rackNumber"));
        colItemname.setCellValueFactory(new PropertyValueFactory("itemName"));
        colItemCode.setCellValueFactory(new PropertyValueFactory("itemCode"));
        try {
            ArrayList<RackDetailsDTO> arrayList =  placeService.loadAllPlaces();
            for (RackDetailsDTO r:arrayList) {
                observableList.add(r);
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

    public void btnAddPlacesOnAction(ActionEvent actionEvent) throws IOException {
        boolean isUserRackNumberMatched = rackNumber.matcher(txtRackNumber.getText()).matches();
        boolean isUserItemCodeMatched = itemCode.matcher(txtItemCode.getText()).matches();
        boolean isUserItemNameMatched = itemName.matcher(txtItemname.getText()).matches();

        if (isUserRackNumberMatched){
            if (isUserItemCodeMatched){
                if (isUserItemNameMatched){

                    String rackNumber = txtRackNumber.getText();
                    String itemCode = txtItemCode.getText();
                    String itemName = txtItemname.getText();
                    try {
                        if (placeService.savePlace(new RackDetailsDTO(rackNumber,itemCode,itemName))){
                            new Alert(Alert.AlertType.INFORMATION,"Added Success").show();
                            Navigation.navigate(Routes.ADDPLACES,pane);
                        }else{
                            new Alert(Alert.AlertType.INFORMATION,"Added Success").show();
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                }else {
                    txtItemname.setFocusColor(Paint.valueOf("Red"));
                    txtItemname.requestFocus();
                }
            }else{
                txtItemCode.setFocusColor(Paint.valueOf("Red"));
                txtItemCode.requestFocus();
            }
        }else{
            txtRackNumber.setFocusColor(Paint.valueOf("Red"));
            txtRackNumber.requestFocus();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        txtRackNumberSearchOnAction(actionEvent);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean isUserRackNumberMatched = rackNumber.matcher(txtRackNumber.getText()).matches();
        boolean isUserItemCodeMatched = itemCode.matcher(txtItemCode.getText()).matches();
        boolean isUserItemNameMatched = itemName.matcher(txtItemname.getText()).matches();

        if (isUserRackNumberMatched){
            if (isUserItemCodeMatched){
                if (isUserItemNameMatched){

                    String rackNumber = txtRackNumber.getText();
                    String itemCode = txtItemCode.getText();
                    String itemname = txtItemname.getText();
                    try {
                        if (placeService.updatePlace(new RackDetailsDTO(rackNumber,itemCode,itemname))){
                            new Alert(Alert.AlertType.INFORMATION,"Update Success").show();
                            Navigation.navigate(Routes.ADDPLACES,pane);
                        }else{
                            new Alert(Alert.AlertType.WARNING,"Update Fail").show();
                        }
                    } catch (SQLException | ClassNotFoundException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    txtItemname.setFocusColor(Paint.valueOf("Red"));
                    txtItemname.requestFocus();
                }
            }else{
                txtItemCode.setFocusColor(Paint.valueOf("Red"));
                txtItemCode.requestFocus();
            }
        }else{
            txtRackNumber.setFocusColor(Paint.valueOf("Red"));
            txtRackNumber.requestFocus();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            if (placeService.deletePlace(txtRackNumber.getText())){
                new Alert(Alert.AlertType.INFORMATION,"Delete Success").show();
                Navigation.navigate(Routes.ADDPLACES,pane);
            }else{
                new Alert(Alert.AlertType.WARNING,"Delete Fail").show();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN,pane);
    }

    public void btnClearOnAction(ActionEvent actionEvent) throws IOException {
       Navigation.navigate(Routes.ADDPLACES,pane);
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDPLACES,pane);
    }

    public void txtkeyPressed(KeyEvent keyEvent) {
        FilteredList<RackDetailsDTO> filteredData = new FilteredList<>(observableList, b -> true);
        txtSeachCustomer.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(rackDetails -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (rackDetails.getItemCode().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (rackDetails.getItemName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (rackDetails.getRackNumber().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }   else
                    return false; // Does not match.
            });
        });
        SortedList<RackDetailsDTO> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblView.comparatorProperty());
        tblView.setItems(sortedData);


    }
    public void txtRackNumberSearchOnAction(ActionEvent actionEvent) {
        try {
            Optional<RackDetailsDTO> rackDetails = placeService.searchPlace(txtRackNumber.getText());
            if (!rackDetails.isPresent()){
                new Alert(Alert.AlertType.WARNING,"Rack Number Not Found").show();
            }else{
                txtItemCode.setText(rackDetails.get().getItemCode());
                txtItemname.setText(rackDetails.get().getItemName());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnAddStoclArrived(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADDSTOCKARRIEVD,pane);
    }
}

