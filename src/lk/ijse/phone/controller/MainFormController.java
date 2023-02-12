package lk.ijse.phone.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.phone.service.custom.EmployeeService;
import lk.ijse.phone.service.custom.OrderService;
import lk.ijse.phone.service.custom.PaymentDetailsService;
import lk.ijse.phone.service.custom.impl.EmployeeServiceImpl;
import lk.ijse.phone.service.custom.impl.OrderServiceImpl;
import lk.ijse.phone.service.custom.impl.PaymentDetailsServiceImpl;
import lk.ijse.phone.util.GetSystemUserDetails;
import lk.ijse.phone.util.Navigation;
import lk.ijse.phone.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class MainFormController {
    public Label lblLogoutName;
    public AnchorPane pane;
    public Label lblDailyIncome;
    public Label lblTodayOrders;
    public BarChart barChart;
    public Label lblAtciveEmployee;
    public Label lblAbEmployee;
    public JFXButton btnAdd;
    private double income=0;
    private int ordersCount=0;
    private final EmployeeService employeeService =new EmployeeServiceImpl();//(EmployeeService) ServiceFactory.getServiceFactory().getService(ServiceTypes.EMPLOYEE);
    private final OrderService orderService = new OrderServiceImpl();//(OrderService) ServiceFactory.getServiceFactory().getService(ServiceTypes.ORDERS);
    private PaymentDetailsService paymentDetailsService = new PaymentDetailsServiceImpl();//(PaymentDetailsService) ServiceFactory.getServiceFactory().getService(ServiceTypes.PAYMENT_DETAILS);

    public  void initialize(){
        calcualteDailyIncome();
        calcualteDailyOrders();
        CalculateActiveEmployee();

    }
    private void CalculateActiveEmployee() {
        String date = String.valueOf(LocalDate.now());
        int empAttendanceCount = 0;
        try {
            empAttendanceCount = employeeService.getEmployeeAttendanceCount(date);
            lblAtciveEmployee.setText(String.valueOf(empAttendanceCount));
            lblAbEmployee.setText(String.valueOf(15-empAttendanceCount));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        XYChart.Series series4= new XYChart.Series();
        series4.setName("Active Employee");
        series4.getData().add(new XYChart.Data(date,empAttendanceCount));
        barChart.getData().addAll(series4);

        XYChart.Series series3= new XYChart.Series();
        series3.setName("Absent Employees");
        series3.getData().add(new XYChart.Data(date,15-empAttendanceCount));
        barChart.getData().addAll(series3);
    }

    private void calcualteDailyOrders() {
        String date = String.valueOf(LocalDate.now());
        try {
            ordersCount = orderService.getTodayOrderCount(date);
            lblTodayOrders.setText(String.valueOf(ordersCount));

            XYChart.Series series2= new XYChart.Series();
            series2.setName("Today Orders");
            series2.getData().add(new XYChart.Data(date,ordersCount));
            barChart.getData().addAll(series2);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void calcualteDailyIncome() {
        String date = String.valueOf(LocalDate.now());
        try {
            income = paymentDetailsService.getTodayIncome(date);
            lblDailyIncome.setText(String.valueOf(income+"$"));

            XYChart.Series series1= new XYChart.Series();
            series1.setName("Today Income*1000");
           // series1.
            series1.getData().add(new XYChart.Data(date,income/1000));
            barChart.getData().addAll(series1);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN,pane);
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

    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        GetSystemUserDetails getSystemUserDetails = new GetSystemUserDetails();
        String rank = getSystemUserDetails.getRank();
        if (rank.equals("admin")){
            Navigation.navigate(Routes.ADDCUSTOMERS,pane);
        }else{
            new Alert(Alert.AlertType.WARNING,"Cant Access").show();
        }
    }


    public void btnStockOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCHSTOCK,pane);
    }

    public void btnOrdOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PENDINGORDERS,pane);
    }

    public void btnHrOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.HR,pane);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN,pane);
    }
}
