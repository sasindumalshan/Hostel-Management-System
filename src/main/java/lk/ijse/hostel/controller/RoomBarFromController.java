package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;

public class RoomBarFromController {
    public Text txtRoomId;
    public Text txtRoomQty;
    public Text textMoney;
    public Text txtRoomType;
    public JFXButton btnRemove;

    public void setData(String id, String qty, String price, String type) {
        txtRoomId.setText(id);
        txtRoomQty.setText(qty);
        textMoney.setText(price);
        txtRoomType.setText(type);
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {

    }
}
