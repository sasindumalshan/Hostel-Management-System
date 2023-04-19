package lk.ijse.hostel.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.hostel.dto.UserDto;
import lk.ijse.hostel.service.UserService;
import lk.ijse.hostel.service.impl.UserServiceImpl;
import lk.ijse.hostel.util.Navigation;

import java.io.IOException;

public class LoginFromController {
    public TextField txtUserName;
    public TextField txtPassword;
    UserDto userDto;
    private UserService service;


    public void loginOnAction(ActionEvent actionEvent) {
        service = UserServiceImpl.getInstance();
        userDto=new UserDto();
        userDto.setUser_name(txtUserName.getText());
        userDto.setPassword(txtPassword.getText());
        try {
            if (service.checkUserDetails(userDto)) {
                Navigation.switchNavigation("DashBord.fxml",actionEvent);
            } else {
                new Alert(Alert.AlertType.WARNING,"Something wrong").show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void createAccountOnAction(ActionEvent actionEvent) {

    }
}
