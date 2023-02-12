package lk.ijse.phone.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.phone.db.DBConnection;

import lk.ijse.phone.service.custom.PaymentService;
import lk.ijse.phone.service.custom.impl.PaymentServiceImpl;
import lk.ijse.phone.util.GetSystemUserDetails;
import lk.ijse.phone.util.Navigation;
import lk.ijse.phone.util.Routes;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;

public class ReportFormController {
    public AnchorPane pane;
    public Label lblLogoutName;
    public PieChart pieChart;
    public Label lblTodayIncome;
    public Label lblYesterdayInCome;
    private final PaymentService paymentService = new PaymentServiceImpl();

    public void initialize(){
        GetSystemUserDetails getSystemUserDetails = new GetSystemUserDetails();
        lblLogoutName.setText(getSystemUserDetails.getName());
        getIncome();
    }
    private void getIncome() {
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        cal.add(Calendar.DATE, -1);
        String yesterday = dateFormat.format(cal.getTime());
        String date = LocalDate.now().toString();
        try {
            double Yincome =  paymentService.Income(yesterday);
            double TIncome =paymentService.Income(date);

            lblTodayIncome.setText(String.valueOf(TIncome));
            lblYesterdayInCome.setText(String.valueOf(Yincome));

            ObservableList<PieChart.Data> observableList = FXCollections.observableArrayList();
            PieChart.Data yesterdayIncome = new PieChart.Data("Yesterday",Yincome );
              PieChart.Data todayIncome= new PieChart.Data("Today",TIncome );
            observableList.addAll(yesterdayIncome,todayIncome);
            pieChart.setData(observableList);


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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

    public void IncomeReportOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.INCOMEREPORT,pane);
    }

    public void btnStockOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEARCHSTOCK,pane);
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAINFORM,pane);
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN,pane);
    }

    public void btnPrintTodayIncomeOnAction(ActionEvent actionEvent) {
        HashMap<String, Object> hm = new HashMap<>();
        InputStream inputStream = this.getClass().getResourceAsStream("/lk/ijse/phone/reports/TodayIncome.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, DBConnection.getInstance().getConnection());
//            JasperPrintManager.printReport(jasperPrint,true);
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnPrintYesterDayInComeOnAction(ActionEvent actionEvent) {
        HashMap<String, Object> hm = new HashMap<>();
        InputStream inputStream = this.getClass().getResourceAsStream("/lk/ijse/phone/reports/YesterdayIncome.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, DBConnection.getInstance().getConnection());
//            JasperPrintManager.printReport(jasperPrint,true);
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
