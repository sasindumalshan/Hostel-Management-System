package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.hostel.dto.RoomDto;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.service.RoomService;
import lk.ijse.hostel.service.impl.RoomServiceImpl;
import lk.ijse.hostel.util.Navigation;
import lk.ijse.hostel.util.RegexUtil;

public class RoomUpdateFromController {
    public JFXTextField txtRoomType;
    public JFXTextField txtQTY;
    public JFXTextField txtPrice;
    public JFXTextField txtRoomId;
    public JFXButton btnDone;

    RoomService roomService;
    RoomDto roomDto;

    public void btnDonOnAction(ActionEvent actionEvent) {
        roomDto = new RoomDto();
        roomDto.setRoomTypeId(txtRoomId.getText());
        roomDto.setType(txtRoomType.getText());
        roomDto.setQty(Integer.parseInt(txtQTY.getText()));
        roomDto.setKeyMoney(txtPrice.getText());
        roomService = RoomServiceImpl.getInstance();
        if ( roomService.update(roomDto)) {
            new Alert(Alert.AlertType.CONFIRMATION,"Ok").show();
            Navigation.close(RoomFromController.getInstance().pane);
            StudentFromController.getInstance().loadAllIdForTable();
        }else {
            new Alert(Alert.AlertType.WARNING,"Something wrong").show();
        }

    }

    public void closeOnMouseCick(MouseEvent mouseEvent) {
        RoomFromController.getInstance().pane.getChildren().clear();
    }

    public void SearchId(ActionEvent actionEvent) {
        roomService = RoomServiceImpl.getInstance();
        Room room = roomService.get(txtRoomId.getText());

        txtRoomId.setEditable(false);
        txtRoomType.setText(room.getType());
        txtPrice.setText(room.getKeyMoney());
        txtQTY.setText(String.valueOf(room.getQty()));

    }

    public void roomType(KeyEvent keyEvent) {
        RegexUtil.regex(btnDone,txtRoomType,txtRoomType.getText(),"^([A-Z a-z]{5,40})$","-fx-text-fill: black");
    }

    public void qty(KeyEvent keyEvent) {
        RegexUtil.regex(btnDone,txtQTY,txtQTY.getText(),"^[1-9][0-9]*$","-fx-text-fill: black");
    }

    public void price(KeyEvent keyEvent) {
        RegexUtil.regex(btnDone,txtPrice,txtPrice.getText(),"^[0-9]+([\\,\\.][0-9]+)?$","-fx-text-fill: black");

    }
}
