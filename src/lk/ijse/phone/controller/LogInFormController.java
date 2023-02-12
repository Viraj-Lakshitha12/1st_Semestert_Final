package lk.ijse.phone.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.phone.service.ServiceFactory;
import lk.ijse.phone.service.ServiceTypes;
import lk.ijse.phone.service.custom.SystemUserService;
import lk.ijse.phone.service.custom.impl.SystemUserServiceImpl;
import lk.ijse.phone.dto.SystemUsersDTO;
import lk.ijse.phone.util.GetSystemUserDetails;
import lk.ijse.phone.util.Navigation;
import lk.ijse.phone.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.regex.Pattern;

public class LogInFormController {
    public JFXPasswordField txtPassword;
    public JFXTextField txtId;
    public AnchorPane pane;
    public JFXComboBox cbmSelectRank;
    public Label lblCreateAccount;

    private Pattern id;
    private Pattern password;
    private final SystemUserService systemUserService = (SystemUserService) ServiceFactory.getServiceFactory().getService(ServiceTypes.SYSTEM_USERS);

    public void initialize(){
        pattern();
        txtId.requestFocus();
        loadRank();
    }
    private void pattern() {
        id = Pattern.compile("^[S][0-9]{3,}$");
        password = Pattern.compile("^[A-Za-z0-9]{3,}$");
    }
    private void loadRank() {
        cbmSelectRank.setItems(FXCollections.observableArrayList("admin","employee"));

    }
    public void btnLogInOnAction(ActionEvent actionEvent) {
        boolean isUserIdMatched = id.matcher(txtId.getText()).matches();
        boolean isUserPassword = password.matcher(txtPassword.getText()).matches();
        if (isUserIdMatched){
            if (isUserPassword){
                try {
                    if (txtId.getText().isEmpty()|txtPassword.getText().isEmpty()|cbmSelectRank.getSelectionModel().getSelectedItem().toString().isEmpty()){
                        new Alert(Alert.AlertType.WARNING,"Enter All Data").show();
                    }else {
                        String id = txtId.getText();
                        String pass = txtPassword.getText();

                        Optional<SystemUsersDTO> systemUsers = systemUserService.searchSystemUsers(id);
                        if (!systemUsers.isPresent()) {
                            new Alert(Alert.AlertType.WARNING, "Wrong ID..Try Again").show();
                        } else {
                            if (pass.equals(systemUsers.get().getPassword())) {
                                String ranks = cbmSelectRank.getSelectionModel().getSelectedItem().toString();
                                   if(ranks.equals(systemUsers.get().getRank())) {
                                       Navigation.navigate(Routes.MAINFORM, pane);
                                       GetSystemUserDetails getSystemUserDetails = new GetSystemUserDetails(systemUsers.get().getId(), systemUsers.get().getName(), systemUsers.get().getEmail(), systemUsers.get().getRank(), systemUsers.get().getPassword());
                                   }else{
                                       new Alert(Alert.AlertType.WARNING, "Enter Correct Rank..Try Again").show();
                                   }
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Wrong Password..Try Again").show();
                            }
                        }
                    }
                        } catch (SQLException | IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }else{
                txtPassword.setFocusColor(Paint.valueOf("Red"));
                txtPassword.requestFocus();
            }
        }else{
            txtId.setFocusColor(Paint.valueOf("Red"));
            txtId.requestFocus();
        }
    }
    public void btnCreateAccountOnAction(ActionEvent actionEvent) throws IOException {
        if (txtId.getText().isEmpty() | txtPassword.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Enter All Details").show();
        } else {
            String id = txtId.getText();
            String password = txtPassword.getText();
            String rank = cbmSelectRank.getSelectionModel().getSelectedItem().toString();
            try {
                Optional<SystemUsersDTO> systemUsers = systemUserService.searchSystemUsers(id);
                if (password.equals(systemUsers.get().getPassword())) {
                    if (rank.equals(systemUsers.get().getRank())) {
                        Navigation.navigate(Routes.ADDSYSTEMUSERS, pane);
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Enter Correct Rank").show();
                    }

                } else {
                    new Alert(Alert.AlertType.WARNING, "Wrong Password").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
