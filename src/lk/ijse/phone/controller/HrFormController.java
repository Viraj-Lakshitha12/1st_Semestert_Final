package lk.ijse.phone.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.phone.db.DBConnection;
import lk.ijse.phone.service.ServiceFactory;
import lk.ijse.phone.service.ServiceTypes;
import lk.ijse.phone.service.custom.EmployeeService;
import lk.ijse.phone.service.custom.HrService;
import lk.ijse.phone.dto.EmployeeDTO;
import lk.ijse.phone.dto.EmployeeAttendanceDTO;
import lk.ijse.phone.util.GetSystemUserDetails;
import lk.ijse.phone.util.Navigation;
import lk.ijse.phone.util.Routes;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class HrFormController {
    public Label lblLogoutName;
    public AnchorPane pane;
    public Stage pane2;
    public PieChart pie;
    public PieChart pieChart;
    public TextField txtEmployeeId;
    public Label lblName;
    public Label lblRole;
    public JFXDatePicker lblDate;
    public JFXTimePicker lblInTime;
    public JFXTimePicker lblOutTime;
    public ComboBox cbmAction;
    public TableView tblView;
    public TableColumn colCustomerId;
    public TableColumn colCustomername;
    public TableColumn colRole;
    public TableColumn colInTime;
    public TableColumn colOutTime;
    public TextField txtSerch;
    public TableColumn colStatus;
    public TableColumn colDate;

    private ObservableList observableList = FXCollections.observableArrayList();
    private final HrService hrService = (HrService) ServiceFactory.getServiceFactory().getService(ServiceTypes.HR);
    private final EmployeeService employeeService = (EmployeeService) ServiceFactory.getServiceFactory().getService(ServiceTypes.EMPLOYEE);

    public void initialize(){
        GetSystemUserDetails systemUserDetails = new GetSystemUserDetails();
        String name1 = systemUserDetails.getName();
        lblLogoutName.setText(name1);
        loadComboboxData();
        setPieCharDeta();
        loadTableData();

    }

    private void loadTableData() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory("employeeId"));
        colCustomername.setCellValueFactory(new PropertyValueFactory("employeename"));
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colInTime.setCellValueFactory(new PropertyValueFactory("inTime"));
        colOutTime.setCellValueFactory(new PropertyValueFactory("outTime"));
        colStatus.setCellValueFactory(new PropertyValueFactory("status"));
        try {
            ArrayList<EmployeeAttendanceDTO> arrayList =hrService.getAllEmployee();
            for (EmployeeAttendanceDTO e : arrayList) {
                observableList.add(e);
                tblView.setItems(observableList);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setPieCharDeta() {
        String date = String.valueOf(LocalDate.now());
        try {
            int attendanceCount =employeeService.getEmployeeAttendanceCount(date);
            ObservableList<PieChart.Data> observableList = FXCollections.observableArrayList();
            PieChart.Data absent = new PieChart.Data("Absent",15-attendanceCount );
            PieChart.Data present = new PieChart.Data("Present", attendanceCount);
            observableList.addAll(absent,present);
            pieChart.setData(observableList);
            pieChart.setTitle("Attendance Summary");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private void loadComboboxData() {
        cbmAction.setItems(FXCollections.observableArrayList("Present","Absent"));
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
        Navigation.navigate(Routes.SEARCHSTOCK,pane);
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAINFORM,pane);
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN,pane);
    }
    public void btngenOnAction(ActionEvent actionEvent) {
        HashMap<String, Object> hm = new HashMap<>();
        InputStream inputStream = this.getClass().getResourceAsStream("/lk/ijse/phone/reports/Blank_A4.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, DBConnection.getInstance().getConnection());
//            JasperPrintManager.printReport(jasperPrint,true);
            JasperViewer.viewReport(jasperPrint);
        } catch (JRException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtKeyPressed(KeyEvent keyEvent) {
        FilteredList<EmployeeAttendanceDTO> filteredData = new FilteredList<>(observableList, b -> true);
        txtSerch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (item.getEmployeeId().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (item.getEmployeename().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (item.getDate().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (item.getInTime().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }  else if (item.getOutTime().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }   else if (item.getStatus().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }   else
                    return false; // Does not match.
            });
        });
        SortedList<EmployeeAttendanceDTO> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblView.comparatorProperty());
        tblView.setItems(sortedData);

    }
    public void btnAddeddAttendanceOnAction(ActionEvent actionEvent) {
        String eid = txtEmployeeId.getText();
        String name = lblName.getText();
        String date = String.valueOf(lblDate.getValue());
        String intime = String.valueOf(lblInTime.getValue());
        String outTime = String.valueOf(lblOutTime.getValue());
        String status = cbmAction.getSelectionModel().getSelectedItem().toString();
        try {
            boolean added = hrService.saveEmployeeAttendance(new EmployeeAttendanceDTO(eid, name, date, intime, outTime, status));
            if (added){
                new Alert(Alert.AlertType.INFORMATION,"Added Success").show();
                btnRefreshOnAction(actionEvent);
            }else {
                new Alert(Alert.AlertType.WARNING,"Added Fail").show();
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void txtEmployeeIdOnAction(ActionEvent actionEvent) {
        try {
            Optional<EmployeeDTO> byPk =employeeService.searchEmployee(txtEmployeeId.getText());
            if (!byPk.isPresent()){
                new Alert(Alert.AlertType.WARNING,"Employee Not Found").show();
            }else{
                lblName.setText(byPk.get().getName());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.HR,pane);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
//        int selectedIndex = tblView.getSelectionModel().getSelectedIndex();
//        EmployeeAttendance tm = (EmployeeAttendance) observableList.get(selectedIndex);
//        txtEmployeeId.setText(tm.getEmployeeId());
//        lblName.setText(tm.getEmployeename());
//        lblDate.setValue(LocalDate.parse(tm.getDate()));

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String eid = txtEmployeeId.getText();
        String name = lblName.getText();
        String date = String.valueOf(lblDate.getValue());
        String intime = String.valueOf(lblInTime.getValue());
        String outTime = String.valueOf(lblOutTime.getValue());
        String status = cbmAction.getSelectionModel().getSelectedItem().toString();

        try {
            boolean update =hrService.updateEmployeeAttendance(new EmployeeAttendanceDTO(eid,name,date,intime,outTime,status));
            if (update){
                new Alert(Alert.AlertType.INFORMATION,"Update Success").show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Update Fail").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
