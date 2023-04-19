package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.hostel.dto.ReservationDto;
import lk.ijse.hostel.dto.RoomDto;
import lk.ijse.hostel.dto.StudentDto;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.projection.RoomIdProjection;
import lk.ijse.hostel.projection.StudentIdProjection;
import lk.ijse.hostel.service.ReservationService;
import lk.ijse.hostel.service.RoomService;
import lk.ijse.hostel.service.StudentService;
import lk.ijse.hostel.service.impl.ReservationServiceImpl;
import lk.ijse.hostel.service.impl.RoomServiceImpl;
import lk.ijse.hostel.service.impl.StudentServiceImpl;
import lk.ijse.hostel.util.Navigation;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationUpdateFromController implements Initializable {

    private static String res_id;
    private static String old_status;
    private static double room_key_money;
    private static double payed_balance;
    private static LocalDate date;


    public Text txtStudentName;
    public Text txtRoomType;
    public Text txtKeyMoney;
    public Text txtResId;
    public Text txtStatus;
    public JFXTextField txtPrice;
    public JFXComboBox cmbStudentIds;
    public JFXComboBox cmbRoomIds;
    public Text txtOldStatus;
    public Text txtOldStatusPrice;
    private ReservationService reservationService;
    private StudentService studentService;
    private RoomService roomService;

    private ReservationDto reservationDto;
    private StudentDto studentDto;
    private RoomDto roomDto;
    private Room room;
    private Student student;


    public static void setRes_id(String res_id) {
        ReservationUpdateFromController.res_id = res_id;
    }

    public void cmbStudentIdOnAction(ActionEvent actionEvent) {
    }

    public void cmbRoomIdOnAction(ActionEvent actionEvent) {
    }

    public void btnDoneOnAction(ActionEvent actionEvent) {
        String processStatus=null;
        if (Double.parseDouble(txtStatus.getText()) >= 0) {
            processStatus="Full Payed";
        }else {
            double p= Double.parseDouble(txtKeyMoney.getText())-Double.parseDouble(txtPrice.getText());
            processStatus= "pay -"+p+" "+txtPrice.getText()+" payed";

        }
        reservationDto = new ReservationDto();
        StudentDto studentDto = studentService.get(String.valueOf(cmbStudentIds.getValue()));
        reservationDto.setStudent(studentDto.toEntity());
        reservationDto.setRoom(roomService.get((String) cmbRoomIds.getValue()));
        reservationDto.setDate(date);
        reservationDto.setRes_id(txtResId.getText());
        reservationDto.setStatus(processStatus);

        reservationService=ReservationServiceImpl.getInstance();

        if (reservationService.update(reservationDto)){
            new Alert(Alert.AlertType.CONFIRMATION,"ok").show();
            Navigation.close(actionEvent);
            ReservationFromController.getInstance().loadDataForTable();
        }else {
            new Alert(Alert.AlertType.WARNING, "something wrong").show();
        }
    }

    public void closeOnMouseCick(MouseEvent mouseEvent) {
        Navigation.close(mouseEvent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
        setAllStudentIds();
        setAllRoomIds();

    }

    private void setAllRoomIds() {
        roomService = RoomServiceImpl.getInstance();
        List<RoomIdProjection> allIds = roomService.getAllIds();

        for (RoomIdProjection idProjection : allIds) {
            cmbRoomIds.getItems().add(idProjection.getRoomId());
        }
    }

    private void setAllStudentIds() {
        studentService = StudentServiceImpl.getInstants();
        List<StudentIdProjection> allIdsByOrder = studentService.getAllIdsByOrder();
        for (StudentIdProjection idProjection : allIdsByOrder) {
            cmbStudentIds.getItems().add(idProjection.getStudent_id());
        }


    }

    private void setData() {
        reservationService = ReservationServiceImpl.getInstance();
        reservationDto = reservationService.get(res_id);
        room = reservationDto.getRoom();
        student = reservationDto.getStudent();
        txtKeyMoney.setText(room.getKeyMoney());
        txtResId.setText(reservationDto.getRes_id());
        txtStatus.setText(reservationDto.getStatus());
        txtStudentName.setText(student.getFist_name() + " " + student.getLast_name());
        txtRoomType.setText(room.getType());
        cmbStudentIds.getSelectionModel().select(student.getStudent_id());
        cmbRoomIds.getSelectionModel().select(room.getRoomTypeId());
        old_status = reservationDto.getStatus();
        date=reservationDto.getDate();

        String status = reservationDto.getStatus();
        String[] split = status.split(" ");
        System.out.println(" split arrays : " + Arrays.toString(split));
        payed_balance = Double.parseDouble(split[1]);
        System.out.println(payed_balance);


    }

    public void StatusOnKeyReleased(KeyEvent keyEvent) {
        if (txtPrice.getText().isEmpty()) {
            txtStatus.setText(old_status);
            txtOldStatus.setVisible(false);
            txtOldStatusPrice.setVisible(false);
            txtStatus.setText(old_status);
        } else {
            txtOldStatus.setVisible(true);
            txtOldStatusPrice.setVisible(true);
            txtOldStatusPrice.setText(old_status);
            double processBalance = payed_balance + Double.parseDouble(txtPrice.getText());
            txtStatus.setText(String.valueOf(processBalance));
        }
    }
}
