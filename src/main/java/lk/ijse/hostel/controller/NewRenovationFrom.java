package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lk.ijse.hostel.dto.ReservationDto;
import lk.ijse.hostel.dto.StudentDto;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.projection.ReservationIdProjection;
import lk.ijse.hostel.projection.RoomIdProjection;
import lk.ijse.hostel.projection.StudentIdProjection;
import lk.ijse.hostel.service.ReservationService;
import lk.ijse.hostel.service.RoomService;
import lk.ijse.hostel.service.StudentService;
import lk.ijse.hostel.service.impl.ReservationServiceImpl;
import lk.ijse.hostel.service.impl.RoomServiceImpl;
import lk.ijse.hostel.service.impl.StudentServiceImpl;
import lk.ijse.hostel.util.DateTimeUtil;
import lk.ijse.hostel.util.Navigation;

import java.net.URL;
import java.time.LocalDate;
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
    List<ReservationDto> list=new ArrayList<>();
    ReservationDto reservationDto;
    StudentDto studentDto;
    private static String res_id;
    ReservationService reservationService;

    public void addOnAction(ActionEvent actionEvent) {
        studentDto = studentService.get((String) cmbStudentId.getValue());
        reservationDto = new ReservationDto();
        reservationDto.setDate(LocalDate.parse(DateTimeUtil.getDateNow()));
        reservationDto.setStudent(studentDto.toEntity());
        reservationDto.setRoom(roomService.get((String) txtRoomType.getValue()));
        reservationDto.setStatus(getStatus());
        reservationDto.setRes_id(getNextId());
        System.out.println(reservationDto.getStatus());
        System.out.println(reservationDto.getRes_id());
        list.add(reservationDto);
        System.out.println(list.size());

    }

    private String getNextId() {
        if (res_id==null){
            res_id=id();
            return res_id;
        }else {
            String[] split = res_id.split("A00");
            int idIndex = Integer.parseInt(split[1]);
            idIndex++;
            res_id= "A00"+idIndex;
            return res_id;
        }
    }

    private String id() {
        reservationService= ReservationServiceImpl.getInstance();
        List<ReservationIdProjection> allId = reservationService.getAllIdsByOrder();
        String lastId = null;
        System.out.println(allId);
        for (int i = 0; i < allId.size(); i++) {
            lastId = allId.get(i).getRes_id();
        }
        try {
            String[] e00s = lastId.split("A00");
            int idIndex = Integer.parseInt(e00s[1]);
            idIndex++;
            System.out.println(idIndex);
            return "A00" + idIndex;
        } catch (Exception e) {
            return "A001";
        }
    }

    private String getStatus() {
    if (Double.parseDouble(txtRoomKeyMoney.getText())==Double.parseDouble(txtPayment.getText())){
        return "Full Payed";
    }else if (Double.parseDouble(txtRoomKeyMoney.getText())>Double.parseDouble(txtPayment.getText())){
        double p= Double.parseDouble(txtRoomKeyMoney.getText())-Double.parseDouble(txtPayment.getText());
        return "pay -"+p+" "+txtPayment.getText()+" payed";
    }
    return null;
    }

    public void btnDonOnAction(ActionEvent actionEvent) {
        boolean isAllAdded=false;
        int i=0;
        for (ReservationDto dto:list) {
            System.out.println(i);
            i++;
            reservationService= ReservationServiceImpl.getInstance();
           isAllAdded= reservationService.save(dto);
           if (!isAllAdded){
               break;
           }
        }
        if (isAllAdded){
            new Alert(Alert.AlertType.CONFIRMATION,"Ok").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Something Wong").show();
        }
    }

    public void cmbRoomTypeOnAction(ActionEvent actionEvent) {
        roomService = RoomServiceImpl.getInstance();
        Room room = roomService.get((String) txtRoomType.getValue());
        txtRoomId.setText(room.getType());
        txtRoomKeyMoney.setText(room.getKeyMoney());
        txtARoom.setText(String.valueOf(room.getQty()));

    }

    public void txtStudentIdOnAction(ActionEvent actionEvent) {
        studentService = StudentServiceImpl.getInstants();
        StudentDto dto = studentService.get(String.valueOf(cmbStudentId.getValue()));
        txtName.setText(dto.getFist_name() + " " + dto.getLast_name());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAllIdsStudent();
        setAllRoomType();
    }

    private void setAllRoomType() {
        roomService = RoomServiceImpl.getInstance();
        List<RoomIdProjection> allIds = roomService.getAllIds();
        ArrayList<String> id = new ArrayList<>();
        for (int i = 0; i < allIds.size(); i++) {
            id.add(allIds.get(i).getRoomId());
        }
        txtRoomType.getItems().addAll(id);

    }

    private void setAllIdsStudent() {
        studentService = StudentServiceImpl.getInstants();
        List<StudentIdProjection> allIdsByOrder = studentService.getAllIdsByOrder();
        ArrayList<String> id = new ArrayList<>();
        for (StudentIdProjection idProjection : allIdsByOrder) {
            id.add(idProjection.getStudent_id());
        }
        cmbStudentId.getItems().addAll(id);
    }
}
