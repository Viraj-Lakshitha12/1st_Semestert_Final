package lk.ijse.phone.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class showStockFormFormController {
    public Label lblLogoutName;
    public AnchorPane pane;
    public TableColumn colitemCode;
    public TableColumn colitemName;
    public TableColumn colqtyOnHand;
    public TableColumn colUnitPrice;
    public TableView tblView;

    private final StockService stockService = (StockService) ServiceFactory.getServiceFactory().getService(ServiceTypes.STOCK);

    public void initialize(){
        GetSystemUserDetails systemUserDetails = new GetSystemUserDetails();
        String name1 = systemUserDetails.getName();
        lblLogoutName.setText(name1);
        loadStock();
    }

    private void loadStock() {
        colitemCode.setCellValueFactory(new PropertyValueFactory("itemCode"));
        colitemName.setCellValueFactory(new PropertyValueFactory("itemName"));
        colqtyOnHand.setCellValueFactory(new PropertyValueFactory("qtyOnHand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        try {
            ArrayList<ItemDTO> arrayList =stockService.getAllStockData();
            ObservableList observableList = tblView.getItems();
            for (ItemDTO i : arrayList) {
                observableList.add(i);
                tblView.setItems(observableList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnSearchStockOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCHSTOCK,pane);
    }

    public void btnStockOnAction(ActionEvent actionEvent) {
    }
    public void btnStockArrievdersOnAciton(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STOCKARRIEVD,pane);
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
}
