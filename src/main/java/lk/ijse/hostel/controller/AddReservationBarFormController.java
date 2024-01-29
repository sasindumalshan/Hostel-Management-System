package lk.ijse.hostel.controller;

import javafx.scene.text.Text;
import lk.ijse.hostel.dto.ReservationDto;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;

public class AddReservationBarFormController {
    public Text txtRoomType;
    public Text txtStudentName;
    public Text txtKeMony;
    public Text txtQty;


    public void setData(ReservationDto reservationDto) {
        System.out.println("check");
        Room room=reservationDto.getRoom();
        Student student = reservationDto.getStudent();
        txtRoomType.setText(room.getType());
        txtStudentName.setText(student.getFist_name()+" "+student.getLast_name());
        txtKeMony.setText(room.getKeyMoney());
        txtQty.setText("");
    }
}
