package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lk.ijse.hostel.dto.ReservationDto;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.projection.ReservationIdProjection;
import lk.ijse.hostel.projection.RoomIdProjection;
import lk.ijse.hostel.service.ReservationService;
import lk.ijse.hostel.service.RoomService;
import lk.ijse.hostel.service.impl.ReservationServiceImpl;
import lk.ijse.hostel.service.impl.RoomServiceImpl;
import lk.ijse.hostel.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationFromController implements Initializable {
    private static ReservationFromController controller;
    public VBox vbox;
    public HBox hBox;
    public Pane pane;
    public JFXTextField txtSearch;
    public Text txtAllRooms;
    public Text txtResRoom;
    RoomService roomService;
    ReservationService reservationService;

    public ReservationFromController() {
        controller = this;
    }

    public static ReservationFromController getInstance() {
        return controller;
    }

    public void addOnAction(ActionEvent actionEvent) {
        try {
            Navigation.popupNavigation("NewResuvationFrom.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       loadDataForTable();
        loadRoomType();
        setData();
    }

    private void setData() {
        roomService=RoomServiceImpl.getInstance();
        txtAllRooms.setText(roomService.getAllRoomCount());
        reservationService=ReservationServiceImpl.getInstance();
        txtResRoom.setText(reservationService.getAllReservationCount());

    }

    public void loadDataForTable() {
        vbox.getChildren().clear();
        reservationService = ReservationServiceImpl.getInstance();
        List<ReservationIdProjection> ids = reservationService.getAllIdsByOrder();
        System.out.println("Arrays ids : "+ids.size());
        for (int i = 0; i < ids.size(); i++) {
            System.out.println("ReservationIdProjection : "+ids.get(i));
            loadData(ids.get(i));
        }
//        System.out.println(ids.size());
        for (ReservationIdProjection idProjection : ids) {
//            loadData(idProjection);
        }
    }

    private void loadData(ReservationIdProjection idProjection) {
        System.out.println(idProjection.getRes_id());
        reservationService = ReservationServiceImpl.getInstance();
       ReservationDto dto = reservationService.get(idProjection.getRes_id());
        System.out.println();
        if (dto!=null){
            System.out.println(dto.getRes_id());
//            System.out.println(dto.getStudent());
//            System.out.println(dto.getRoom());

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ReservationBarFrom.fxml"));
                Parent root = loader.load();
                ReservationBarFromController controller = loader.getController();
                controller.setData(dto);
                vbox.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
//        ReservationDto dto=new ReservationDto();

    }

    private void loadRoomType() {
        roomService = RoomServiceImpl.getInstance();
        List<RoomIdProjection> allIds = roomService.getAllIds();
        for (int i = 0; i < allIds.size(); i++) {
            loadDataForRoomType(allIds.get(i));
        }
    }

    private void loadDataForRoomType(RoomIdProjection roomIdProjection) {
        roomService = RoomServiceImpl.getInstance();
        Room room = roomService.get(roomIdProjection.getRoomId());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RoomTypeBarFrom.fxml"));
            Parent root = loader.load();
            RoomTypeBarFromController controller = loader.getController();
            controller.setData(room);
            hBox.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backOnAction(ActionEvent actionEvent) {
        try {
            Navigation.switchNavigation("DashBord.fxml",actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchOnKeyReleased(KeyEvent keyEvent) {
        vbox.getChildren().clear();
        reservationService=ReservationServiceImpl.getInstance();
        List<ReservationIdProjection>ids=reservationService.getSearchIds(txtSearch.getText());
        for (int i = 0; i < ids.size(); i++) {
            loadData(ids.get(i));
        }
    }
}
