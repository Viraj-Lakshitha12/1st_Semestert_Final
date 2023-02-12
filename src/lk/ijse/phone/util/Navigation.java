package lk.ijse.phone.util;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage)Navigation.pane.getScene().getWindow();

        switch (route) {
            case MAINFORM:
                window.setTitle("Main Form");
                initUI("MainForm.fxml");
                break;
            case AVA:
                window.setTitle("Available Manage");
                initUI("AvaForm.fxml");
                break;
            case PENDINGORDERS:
                window.setTitle("Pending Orders");
                initUI("PendingOrderForm.fxml");
                break;
            case PACKEDORDERS:
                window.setTitle("Pack Orders");
                initUI("PackedOrderForm.fxml");
                break;

            case SALESBILLING:
                window.setTitle("Sales Billing");
                initUI("SalesBillingForm.fxml");
                break;
            case RETURNITEM:
                window.setTitle("Return Item");
                initUI("ReturnItemForm.fxml");
                break;
            case ORDERS:
                window.setTitle("Orders");
                initUI("OrdersForm.fxml");
                break;
            case PAYMENT:
                window.setTitle("Payment");
                initUI("PaymentForm.fxml");
                break;
            case ADDCUSTOMERS:
                window.setTitle("ADD CUSTOMERS");
                initUI("AddCustomerForm.fxml");
                break;
            case ADDSTOCK:
                window.setTitle("ADD STOCK");
                initUI("AddStockForm.fxml");
                break;
            case SUPPLIYER:
                window.setTitle("ADD SUPPLIER");
                initUI("AddSupplierForm.fxml");
                break;
            case EMPLOYEE:
                window.setTitle("ADD EMPLOYEE");
                initUI("AddEmployeeForm.fxml");
                break;
            case SEARCHSTOCK:
                window.setTitle("Search Stock");
                initUI("SearchStockForm.fxml");
                break;
            case STOCKPLACE:
                window.setTitle("Stock Place Details");
                initUI("StockPlaceForm.fxml");
                break;
            case STOCKARRIEVD:
                window.setTitle("Stock Arrievd Details ");
                initUI("StockArrivedDetailsForm.fxml");
                break;
            case CREATESYSTEMUSERS:
                window.setTitle("Create Account ");
                initUI("CreateSystemUserAccount.fxml");
                break;
            case LOGIN:
                window.setTitle("login Form ");
                initUI("LogInFormAdmin.fxml");
                break;
            case SHOWSTOCK:
                window.setTitle("STOCK");
                initUI("ShowStockForm.fxml");
                break;
            case HR:
                window.setTitle("HR");
                initUI("HrForm.fxml");
                break;
            case ADDPLACES:
                window.setTitle("Add Places");
                initUI("AddPlacesForm.fxml");
                break;
            case ADDSTOCKARRIEVD:
                window.setTitle("Add Places");
                initUI("AddStockArrievdForm.fxml");
                break;
            case INCOMEREPORT:
                window.setTitle("income Report");
                initUI("ReportForm.fxml");
                break;
            case SHOWORDERDETAILS:
                window.setTitle("Order Details");
                initUI("OrderDetailsShowForm.fxml");
                break;
            case MAINFORMEMPLOYEE:
                window.setTitle("Employee MainForm");
                initUI("MainFormEmployee.fxml");
                break;
            case ADDSYSTEMUSERS:
                window.setTitle("System users");
                initUI("AddSystemUserForm.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "UI Not Found!").show();
        }
    }
    public static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/phone/view/" + location)));
    }
}
