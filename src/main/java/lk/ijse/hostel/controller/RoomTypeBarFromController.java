package lk.ijse.hostel.controller;

import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import lk.ijse.hostel.entity.Room;

public class RoomTypeBarFromController {
    public Text txtRoomTypeId;
    public Text txtARoomCount;
    public Text txtRRoomCount;

    public void setData(Room room) {
        txtRoomTypeId.setText(room.getType());
        txtARoomCount.setText(String.valueOf(room.getQty()));
    }
}
