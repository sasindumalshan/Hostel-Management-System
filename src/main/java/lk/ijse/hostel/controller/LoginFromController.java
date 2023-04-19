package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.hostel.dto.UserDto;
import lk.ijse.hostel.service.UserService;
import lk.ijse.hostel.service.impl.UserServiceImpl;
import lk.ijse.hostel.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFromController implements Initializable {
    public TextField txtUserName;
    public TextField txtPassword;
    public TextField txtPasswordComfrom;
    public JFXButton btnCreateAccount;
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
        btnCreateAccount.setDisable(true);
        service = UserServiceImpl.getInstance();
        userDto=new UserDto();
        if (service.tableIsEmplty()){
            btnCreateAccount.setDisable(false);
            userDto.setUser_name(txtUserName.getText());
            userDto.setPassword(txtPassword.getText());
            userDto.setUser_id(id());
            try {
                if (service.add(userDto)){
                    Navigation.switchNavigation("DashBord.fxml",actionEvent);
                }else {
                    new Alert(Alert.AlertType.WARNING,"Something wrong").show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            userDto.setUser_id("U001");
            userDto.setPassword(txtPasswordComfrom.getText());
            txtPasswordComfrom.setVisible(true);

        }




    }

    private String id() {
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
txtPasswordComfrom.setVisible(false);
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) {
//        service = UserServiceImpl.getInstance();
//        userDto=new UserDto();
//        userDto.setUser_name(txtUserName.getText());
//        userDto.setPassword(txtPassword.getText());
//        try {
//            if (service.checkUserDetails(userDto)) {
//
//            } else {
//                new Alert(Alert.AlertType.WARNING,"Something wrong").show();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        if (service.checkPassword(userDto)){
            btnCreateAccount.setDisable(false);
            userDto.setUser_name(txtUserName.getText());
            userDto.setPassword(txtPassword.getText());
            userDto.setUser_id(id());
            try {
                if (service.add(userDto)){
                    Navigation.switchNavigation("DashBord.fxml",actionEvent);
                }else {
                    new Alert(Alert.AlertType.WARNING,"Something wrong").show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            new Alert(Alert.AlertType.WARNING," wrong password ... ! ").show();
        }
    }
}
