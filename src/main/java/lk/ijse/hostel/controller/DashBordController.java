package lk.ijse.hostel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import lk.ijse.hostel.service.ReservationService;
import lk.ijse.hostel.service.RoomService;
import lk.ijse.hostel.service.StudentService;
import lk.ijse.hostel.service.impl.ReservationServiceImpl;
import lk.ijse.hostel.service.impl.RoomServiceImpl;
import lk.ijse.hostel.service.impl.StudentServiceImpl;
import lk.ijse.hostel.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class DashBordController implements Initializable {

    public Text txtHour;
    public Text txtMini;
    public Text txtDate;
    public Text txtReusedRoom;
    public Text txtAllRooms;
    public Text txtAllEmployees;

    private RoomService roomService;
    private StudentService studentService;
    private ReservationService reservationService;

    public void btnStudentOnAction(ActionEvent actionEvent) {
        try {
            Navigation.switchNavigation("StudentFrom.fxml",actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnRoomOnAction(ActionEvent actionEvent) {
        try {
            Navigation.switchNavigation("RoomFrom.fxml",actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnResuvationOnAction(ActionEvent actionEvent) {
        try {
            Navigation.switchNavigation("ResuvationFrom.fxml",actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnSettingOnAction(ActionEvent actionEvent) {
        try {
            Navigation.popupNavigation("SettingFrom.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDashBordData();

    }

    private void setDashBordData() {
        setDate();
        setTime();
        setAllRoom();
        setAllStudent();
        setAllReservation();
    }

    private void setAllReservation() {
        reservationService= ReservationServiceImpl.getInstance();
        txtReusedRoom.setText(reservationService.getAllReservationCount());
    }

    private void setAllStudent() {
        studentService= StudentServiceImpl.getInstants();
        txtAllEmployees.setText(studentService.getAllStudentCount());

    }

    private void setAllRoom() {
        roomService= RoomServiceImpl.getInstance();
        txtAllRooms.setText(roomService.getAllRoomCount());


    }

    private void setTime() {
        Thread thread=new Thread(() -> {
            while (true) {
                SimpleDateFormat format1=new SimpleDateFormat("hh");
                txtHour.setText(format1.format(new Date()));
                SimpleDateFormat format2=new SimpleDateFormat("mm");
                txtMini.setText(format2.format(new Date()));
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();


    }

    private void setDate() {
        SimpleDateFormat format=new SimpleDateFormat("EEE, MMM d,");
        txtDate.setText(format.format(new Date()));
    }
}


