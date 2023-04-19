package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lk.ijse.hostel.dto.UserDto;
import lk.ijse.hostel.service.UserService;
import lk.ijse.hostel.service.impl.UserServiceImpl;

public class SettingFromController {
    public JFXTextField txtUseId;
    public JFXTextField txtQTY;
    public JFXTextField txtPassword;
    public TableView tbl;
    public TableColumn tblUserId;
    public TableColumn tblPassword;
    public TableColumn tblUseName;

    UserDto userDto;
    private UserService service;

    public void btnDeleteOnAction(ActionEvent actionEvent) {

    }

    public void SearchId(ActionEvent actionEvent) {
        service = UserServiceImpl.getInstance();
       userDto= service.get(txtUseId.getText());
       txtUseId.setText(userDto.getUser_id());
       txtPassword.setText(userDto.getPassword());
       txtQTY.setText(userDto.getUser_name());
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }
}
