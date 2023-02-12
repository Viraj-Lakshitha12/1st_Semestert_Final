package lk.ijse.phone.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.phone.util.Navigation;
import lk.ijse.phone.util.Routes;

import javax.swing.text.NavigationFilter;
import java.io.IOException;

public class CreateSystemUserFormController {
    public AnchorPane pane;

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN,pane);
    }
}
