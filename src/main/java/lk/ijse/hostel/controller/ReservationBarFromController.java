package lk.ijse.hostel.controller;

import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import lk.ijse.hostel.dto.ReservationDto;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;

public class ReservationBarFromController {
    public Text txtReservationId;
    public Text txtRoomType;
    public Text txtStudentId;
    public Text txtDate;
    public Text txtStatus;

    Student student;
    Room room;

    public void btnRemoveOnAction(ActionEvent actionEvent) {
    }

    public void setData(ReservationDto dto) {

        student=dto.getStudent();
        room=dto.getRoom();
        txtDate.setText(String.valueOf(dto.getDate()));
        txtReservationId.setText(dto.getRes_id());
        txtStudentId.setText(student.getStudent_id()+" : "+student.getFist_name()+" "+student.getLast_name());
        txtRoomType.setText(room.getType());
        txtStatus.setText(dto.getStatus());
    }
}
