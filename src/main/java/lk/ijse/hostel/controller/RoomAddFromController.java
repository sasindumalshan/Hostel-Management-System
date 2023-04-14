package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.hostel.dto.RoomDto;
import lk.ijse.hostel.service.RoomService;
import lk.ijse.hostel.service.impl.RoomServiceImpl;
import lk.ijse.hostel.util.FactoryConfiguration;
import lk.ijse.hostel.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RoomAddFromController {

    public JFXTextField txtRoomType;
    public JFXTextField txtQTY;
    public JFXTextField txtPrice;

    RoomDto roomDto = new RoomDto();
    RoomService roomService;

    public void btnDonOnAction(ActionEvent actionEvent) {
        roomService=RoomServiceImpl.getInstance();
        RoomFromController.getInstance().pane.getChildren().clear();
        roomDto.setRoomTypeId("R002");
        roomDto.setQty(10);
        roomDto.setKeyMoney("100000");
        roomDto.setType("ac");
       roomService.saveRoom(roomDto);


    }

    public void closeOnMouseCick(MouseEvent mouseEvent) {
        RoomFromController.getInstance().pane.getChildren().clear();
    }

}
