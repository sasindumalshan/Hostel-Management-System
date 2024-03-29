package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.hostel.dto.UserDto;
import lk.ijse.hostel.projection.UserIdProjection;
import lk.ijse.hostel.service.UserService;
import lk.ijse.hostel.service.impl.UserServiceImpl;
import lk.ijse.hostel.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginFromController implements Initializable {
    public TextField txtUserName;
//    public TextField txtPasswordPField;
    public TextField txtPasswordComfrom;
    public JFXButton btnCreateAccount;
    public ImageView imgCloseEye;
    public ImageView imgEye;
    public JFXPasswordField txtPasswordPField;
    public JFXTextField txtPasswordField;
    UserDto userDto;
    private UserService service;


    public void loginOnAction(ActionEvent actionEvent) {
        service = UserServiceImpl.getInstance();
        userDto = new UserDto();
        userDto.setUser_name(txtUserName.getText());
        userDto.setPassword(txtPasswordPField.getText());
        try {
            if (service.checkUserDetails(userDto)) {
                Navigation.switchNavigation("DashBord.fxml", actionEvent);
            } else {
                new Alert(Alert.AlertType.WARNING, "Something wrong").show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void createAccountOnAction(ActionEvent actionEvent) {
        btnCreateAccount.setDisable(true);
        service = UserServiceImpl.getInstance();
        userDto = new UserDto();
        if (service.tableIsEmplty()) {
            btnCreateAccount.setDisable(false);
            userDto.setUser_name(txtUserName.getText());
            userDto.setPassword(txtPasswordPField.getText());
            userDto.setUser_id(id());
            try {
                if (service.add(userDto)) {
                    Navigation.switchNavigation("DashBord.fxml", actionEvent);
                } else {
                    new Alert(Alert.AlertType.WARNING, "Something wrong").show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            userDto.setUser_id("U001");
            userDto.setPassword(txtPasswordComfrom.getText());
            txtPasswordComfrom.setVisible(true);

        }
    }

    private String id() {
        service = UserServiceImpl.getInstance();

        List<UserIdProjection> allId = service.getAllIdsByOrder();
        String lastId = null;
        for (int i = 0; i < allId.size(); i++) {
            lastId = allId.get(i).getId();
        }
        try {
            String[] e00s = lastId.split("U00");
            int idIndex = Integer.parseInt(e00s[1]);
            idIndex++;
            System.out.println(idIndex);
            return "U00" + idIndex;
        } catch (Exception e) {
            return "U001";
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtPasswordComfrom.setVisible(false);
        txtPasswordField.setVisible(false);
        txtPasswordPField.setVisible(true);
        imgCloseEye.setVisible(true);
        imgEye.setVisible(false);
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

        if (service.checkPassword(userDto)) {
            btnCreateAccount.setDisable(false);
            userDto.setUser_name(txtUserName.getText());
            userDto.setPassword(txtPasswordPField.getText());
            userDto.setUser_id(id());
            try {
                if (service.add(userDto)) {
                    Navigation.switchNavigation("DashBord.fxml", actionEvent);
                } else {
                    new Alert(Alert.AlertType.WARNING, "Something wrong").show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, " wrong password ... ! ").show();
        }
    }

    String VOTxt;

    public void loginMouseClickEyeOpen(MouseEvent mouseEvent) {
        VOTxt=txtPasswordField.getText();
        System.out.println("1  : "+txtPasswordPField.getText());
        System.out.println(VOTxt);
        txtPasswordPField.setVisible(false);
        txtPasswordField.setVisible(true);
        imgCloseEye.setVisible(true);
        imgEye.setVisible(false);
        txtPasswordPField.setText(VOTxt);
    }

    public void loginMouseClickEyeClose(MouseEvent mouseEvent) {
        VOTxt=txtPasswordPField.getText();
        System.out.println(VOTxt);
        txtPasswordPField.setVisible(true);
        txtPasswordField.setVisible(false);
        imgCloseEye.setVisible(false);
        imgEye.setVisible(true);
        txtPasswordField.setText(VOTxt);
    }
}
