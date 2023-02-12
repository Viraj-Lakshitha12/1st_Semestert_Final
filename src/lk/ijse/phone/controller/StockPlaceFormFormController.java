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
import lk.ijse.phone.dao.custom.PlaceDAO;
import lk.ijse.phone.dao.custom.impl.PlaceDAOImpl;
import lk.ijse.phone.dto.RackDetailsDTO;
import lk.ijse.phone.service.ServiceFactory;
import lk.ijse.phone.service.ServiceTypes;
import lk.ijse.phone.service.custom.PlaceService;
import lk.ijse.phone.service.custom.impl.PlaceServiceImpl;
import lk.ijse.phone.util.GetSystemUserDetails;
import lk.ijse.phone.util.Navigation;
import lk.ijse.phone.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockPlaceFormFormController {
    public AnchorPane pane;
    public Label lblLogoutName;
    public TextField txtSearchplace;
    public TableView tblView;
    public TableColumn colRacknumber;
    public TableColumn colitemname;
    public TableColumn colItemCode;
    private ObservableList observableList = FXCollections.observableArrayList();
    private final PlaceService placeService = (PlaceService) ServiceFactory.getServiceFactory().getService(ServiceTypes.PLACES);

    public void initialize(){
        GetSystemUserDetails systemUserDetails = new GetSystemUserDetails();
        String name1 = systemUserDetails.getName();
        lblLogoutName.setText(name1);
        loadRackDetails();
    }

    private void loadRackDetails() {
        colItemCode.setCellValueFactory(new PropertyValueFactory("itemCode"));
        colRacknumber.setCellValueFactory(new PropertyValueFactory("rackNumber"));
        colitemname.setCellValueFactory(new PropertyValueFactory("itemName"));
        try {
            ArrayList<RackDetailsDTO> arrayList = placeService.loadAllPlaces();
            for (RackDetailsDTO r:arrayList) {
                observableList.add(r);
                tblView.setItems(observableList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnSearchStockOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCHSTOCK,pane);
    }
    public void btnStockAddOnAction(ActionEvent actionEvent) throws IOException {
      Navigation.navigate(Routes.SHOWSTOCK,pane);
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

    public void btnBillOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SALESBILLING,pane);

    }

    public void btnAvaOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.AVA,pane);
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

    public void txtkeyPressed(KeyEvent keyEvent) {
        FilteredList<RackDetailsDTO> filteredData = new FilteredList<>(observableList, b -> true);
        txtSearchplace.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(rackDetails -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (rackDetails.getRackNumber().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (rackDetails.getItemCode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }else if (rackDetails.getItemName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else
                    return false; // Does not match.
            });
        });
        SortedList<RackDetailsDTO> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblView.comparatorProperty());
        tblView.setItems(sortedData);

    }

    public void btnAddPlaceOnAction(ActionEvent actionEvent) throws IOException {
    Navigation.navigate(Routes.ADDPLACES,pane);
    }
}
