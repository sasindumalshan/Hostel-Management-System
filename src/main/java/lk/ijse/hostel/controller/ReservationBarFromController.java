package lk.ijse.hostel.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import lk.ijse.hostel.dto.ReservationDto;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.service.ReservationService;
import lk.ijse.hostel.service.impl.ReservationServiceImpl;
import lk.ijse.hostel.util.Navigation;

import java.io.IOException;

public class ReservationBarFromController {
    public Text txtReservationId;
    public Text txtRoomType;
    public Text txtStudentId;
    public Text txtDate;
    public Text txtStatus;
    Student student;
    Room room;
    private ReservationService reservationService;
    private ReservationDto reservationDto;

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        reservationService = ReservationServiceImpl.getInstance();
        reservationDto=reservationService.get(txtReservationId.getText());
        if (reservationService.remove(reservationDto)) {
            new Alert(Alert.AlertType.CONFIRMATION,"Remove ").show();
            ReservationFromController.getInstance().loadDataForTable();
        } else {
            new Alert(Alert.AlertType.WARNING,"something Wrong").show();

        }
    }

    public void setData(ReservationDto dto) {

        student = dto.getStudent();
        room = dto.getRoom();
        txtDate.setText(String.valueOf(dto.getDate()));
        txtReservationId.setText(dto.getRes_id());
//        txtStudentId.setText(student.getStudent_id() + " : " + student.getFist_name() + " " + student.getLast_name());
//        txtRoomType.setText(room.getType());
        txtStatus.setText(dto.getStatus());
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        ReservationUpdateFromController.setRes_id(txtReservationId.getText());
        try {
            Navigation.popupNavigation("ReservationUpdateFrom.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
