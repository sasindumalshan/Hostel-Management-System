package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import lk.ijse.hostel.dto.StudentDto;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.service.RoomService;
import lk.ijse.hostel.service.StudentService;
import lk.ijse.hostel.service.impl.RoomServiceImpl;
import lk.ijse.hostel.service.impl.StudentServiceImpl;
import lk.ijse.hostel.util.Navigation;

public class StudentBarFromController {
    public JFXButton btnRemove;
    public Text txtStudent_id;
    public Text txtName;
    public Text txtAddress;
    public Text txtContactNo;
    public Text txtBrithDay;
    public Text txtGender;

    StudentService studentService;

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        studentService = StudentServiceImpl.getInstants();

        if ( studentService.remove(studentService.get(txtStudent_id.getText()))) {
           new Alert(Alert.AlertType.CONFIRMATION,"Ok").show();
           Navigation.close(StudentFromController.getInstance().pane);
           StudentFromController.getInstance().loadAllIdForTable();
       }else {
           new Alert(Alert.AlertType.WARNING,"Something wrong").show();
       }
    }

    public void setData(StudentDto dto) {
        txtName.setText(dto.getFist_name() + " " + dto.getLast_name());
        txtStudent_id.setText(dto.getStudent_id());
        txtAddress.setText(dto.getCity() + " " + dto.getStreet() + " " + dto.getLane());
        txtContactNo.setText(dto.getContact_no());
        txtBrithDay.setText(String.valueOf(dto.getBirthday()));
        txtGender.setText(dto.getGender());
    }
}
