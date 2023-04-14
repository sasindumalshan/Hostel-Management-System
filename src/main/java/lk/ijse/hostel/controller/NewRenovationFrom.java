package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lk.ijse.hostel.dto.StudentDto;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.projection.RoomIdProjection;
import lk.ijse.hostel.projection.StudentIdProjection;
import lk.ijse.hostel.service.RoomService;
import lk.ijse.hostel.service.StudentService;
import lk.ijse.hostel.service.impl.RoomServiceImpl;
import lk.ijse.hostel.service.impl.StudentServiceImpl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewRenovationFrom implements Initializable {
    public JFXComboBox txtRoomType;
    public JFXComboBox cmbStudentId;
    public JFXTextField txtPayment;
    public VBox vbox;
    public Text txtRoomId;
    public Text txtName;
    public Text txtARoom;
    public Text txtRoomKeyMoney;
    StudentService studentService;
    RoomService roomService;

    public void addOnAction(ActionEvent actionEvent) {

    }

    public void btnDonOnAction(ActionEvent actionEvent) {

    }

    public void cmbRoomTypeOnAction(ActionEvent actionEvent) {
        roomService=RoomServiceImpl.getInstance();
        Room room = roomService.get((String) txtRoomType.getValue());
        txtRoomId.setText(room.getType());
        txtRoomKeyMoney.setText(room.getKeyMoney());
        txtARoom.setText(String.valueOf(room.getQty()));

    }

    public void txtStudentIdOnAction(ActionEvent actionEvent) {
        studentService=StudentServiceImpl.getInstants();
        StudentDto dto = studentService.get(String.valueOf(cmbStudentId.getValue()));
        txtName.setText(dto.getFist_name()+" "+dto.getLast_name());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAllIdsStudent();
        setAllRoomType();
    }

    private void setAllRoomType() {
        roomService= RoomServiceImpl.getInstance();
        List<RoomIdProjection> allIds = roomService.getAllIds();
        ArrayList<String>id=new ArrayList<>();
        for (int i = 0; i < allIds.size(); i++) {
            id.add(allIds.get(i).getRoomId());
        }
        txtRoomType.getItems().addAll(id);

    }

    private void setAllIdsStudent() {
        studentService= StudentServiceImpl.getInstants();
        List<StudentIdProjection> allIdsByOrder = studentService.getAllIdsByOrder();
        ArrayList<String>id=new ArrayList<>();
        for (StudentIdProjection idProjection:allIdsByOrder) {
            id.add(idProjection.getStudent_id());
        }
        cmbStudentId.getItems().addAll(id);
    }
}
