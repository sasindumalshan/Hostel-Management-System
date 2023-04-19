package lk.ijse.hostel.controller;

import javafx.event.ActionEvent;
import lk.ijse.hostel.util.Navigation;

import java.io.IOException;

public class DashBordController {

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
}


