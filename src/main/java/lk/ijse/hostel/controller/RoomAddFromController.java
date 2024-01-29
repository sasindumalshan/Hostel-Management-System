package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.hostel.dto.RoomDto;
import lk.ijse.hostel.projection.RoomIdProjection;
import lk.ijse.hostel.projection.StudentIdProjection;
import lk.ijse.hostel.service.RoomService;
import lk.ijse.hostel.service.impl.RoomServiceImpl;
import lk.ijse.hostel.service.impl.StudentServiceImpl;
import lk.ijse.hostel.util.FactoryConfiguration;
import lk.ijse.hostel.util.RegexUtil;
import lk.ijse.hostel.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RoomAddFromController {

    public JFXTextField txtRoomType;
    public JFXTextField txtQTY;
    public JFXTextField txtPrice;
    public JFXButton btnDone;

    RoomDto roomDto = new RoomDto();
    RoomService roomService;

    public void btnDonOnAction(ActionEvent actionEvent) {
        roomService=RoomServiceImpl.getInstance();
        RoomFromController.getInstance().pane.getChildren().clear();
        roomDto.setRoomTypeId(id());
        roomDto.setQty(Integer.parseInt(txtQTY.getText()));
        roomDto.setKeyMoney(txtPrice.getText());
        roomDto.setType(txtRoomType.getText());
       roomService.saveRoom(roomDto);


    }

    private String id() {
        roomService= RoomServiceImpl.getInstance();

        List<RoomIdProjection> allId = roomService.getAllIdsByOrder();
        String lastId = null;
        for (int i = 0; i < allId.size(); i++) {
            lastId = allId.get(i).getRoomId();
            System.out.println(allId.get(i));
        }
        try {
            String[] e00s = lastId.split("R00");
            int idIndex = Integer.parseInt(e00s[1]);
            idIndex++;
            System.out.println(idIndex);
            return "R00" + idIndex;
        } catch (Exception e) {
            return "R001";
        }
    }

    public void closeOnMouseCick(MouseEvent mouseEvent) {
        RoomFromController.getInstance().pane.getChildren().clear();
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

