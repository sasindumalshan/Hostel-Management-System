package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hostel.dto.UserDto;
import lk.ijse.hostel.dto.tm.UserTm;
import lk.ijse.hostel.projection.UserIdProjection;
import lk.ijse.hostel.service.UserService;
import lk.ijse.hostel.service.impl.UserServiceImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SettingFromController implements Initializable {
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
        userDto = new UserDto();
        userDto.setPassword(txtPassword.getText());
        userDto.setUser_id(txtUseId.getText());
        userDto.setUser_name(txtQTY.getText());

        service = UserServiceImpl.getInstance();
        if (service.remove(userDto)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Ok").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "error").show();
        }
    }

    public void SearchId(ActionEvent actionEvent) {
        service = UserServiceImpl.getInstance();
        userDto = service.get(txtUseId.getText());
        txtUseId.setText(userDto.getUser_id());
        txtPassword.setText(userDto.getPassword());
        txtQTY.setText(userDto.getUser_name());
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        userDto = new UserDto();
        userDto.setPassword(txtPassword.getText());
        userDto.setUser_id(txtUseId.getText());
        userDto.setUser_name(txtQTY.getText());

        service = UserServiceImpl.getInstance();
        if (service.update(userDto)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "error").show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableProperty();
        loadAllIds();
    }

    private void loadAllIds() {
        tms.clear();
        tbl.getItems().clear();
        service=UserServiceImpl.getInstance();
        List<UserIdProjection> allIdsByOrder = service.getAllIdsByOrder();
        for (int i = 0; i < allIdsByOrder.size(); i++) {
            loadData(allIdsByOrder.get(i));
        }
    }

    private void loadData(UserIdProjection userIdProjection) {
        service=UserServiceImpl.getInstance();
        UserDto userDto = service.get(userIdProjection.getId());
        UserTm tm=new UserTm();
        tm.setPassword(userDto.getPassword());
        tm.setUseName(userDto.getUser_name());
        tm.setUser_id(userDto.getUser_id());
        tms.add(tm);
    }

    ObservableList<UserTm> tms= FXCollections.observableArrayList();
    private void setTableProperty() {
        tblPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        tblUseName.setCellValueFactory(new PropertyValueFactory<>("useName"));
        tblUserId.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        tbl.setItems(tms);
    }
}
