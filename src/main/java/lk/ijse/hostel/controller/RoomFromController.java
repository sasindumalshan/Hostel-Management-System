package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lk.ijse.hostel.dto.RoomDto;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.projection.RoomIdProjection;
import lk.ijse.hostel.service.ReservationService;
import lk.ijse.hostel.service.RoomService;
import lk.ijse.hostel.service.impl.ReservationServiceImpl;
import lk.ijse.hostel.service.impl.RoomServiceImpl;
import lk.ijse.hostel.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RoomFromController implements Initializable {
    private static RoomFromController controller;
    public VBox vbox;
    public Pane pane;
    public JFXTextField txtSearch;
    public Text txtRoomCount;
    public Text txtReservationCount;
    RoomDto roomDto = new RoomDto();
    RoomService roomService;
    ReservationService reservationService;
    public RoomFromController() {
        controller = this;
    }

    public static RoomFromController getInstance() {
        return controller;
    }
//    RoomService service= new RoomServiceImpl();

    public void addOnAction(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(RoomFromController.class.getResource("/view/RoomAddFrom.fxml"));
            Parent root = loader.load();
            pane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateOnAction(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(RoomFromController.class.getResource("/view/RoomUpdateFrom.fxml"));
            Parent root = loader.load();
            pane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataDashBord();
        getAllIds();
    }

    private void loadDataDashBord() {
        roomService=RoomServiceImpl.getInstance();
        txtRoomCount.setText(roomService.getAllRoomCount());

        reservationService= ReservationServiceImpl.getInstance();
        txtReservationCount.setText(reservationService.getAllReservationCount());

    }

    private void getAllIds() {

        roomService = RoomServiceImpl.getInstance();
        List<RoomIdProjection> allIds = roomService.getAllIds();
        for (int i = 0; i < allIds.size(); i++) {
            getSearchData(allIds.get(i).getRoomId());
        }
    }

    private void getSearchData(String roomId) {
        roomService = RoomServiceImpl.getInstance();
        Room room = roomService.get(roomId);
        setDataBar(room.getRoomTypeId(), String.valueOf(room.getQty()), room.getKeyMoney(), room.getType());
    }

    private void setDataBar(String id, String qty, String price, String type) {
        try {
            FXMLLoader loader = new FXMLLoader(RoomBarFromController.class.getResource("/view/RoomBarFrorm.fxml"));
            Parent root = loader.load();
            RoomBarFromController controller = loader.getController();
            controller.setData(id, qty, price, type);
            vbox.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backOnAction(ActionEvent actionEvent) {
        try {
            Navigation.switchNavigation("DashBord.fxml", actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchOnAction(KeyEvent keyEvent) {
        vbox.getChildren().clear();
        roomService=RoomServiceImpl.getInstance();
        List<RoomIdProjection> ids=roomService.getSearchData(txtSearch.getText());
        System.out.println(ids);
        for (int i = 0; i < ids.size(); i++) {
            getSearchData(ids.get(i).getRoomId());
        }
    }
}
