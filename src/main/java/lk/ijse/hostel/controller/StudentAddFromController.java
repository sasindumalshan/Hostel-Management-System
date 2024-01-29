package lk.ijse.hostel.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.hostel.dto.StudentDto;
import lk.ijse.hostel.projection.StudentIdProjection;
import lk.ijse.hostel.service.StudentService;
import lk.ijse.hostel.service.impl.StudentServiceImpl;
import lk.ijse.hostel.util.Navigation;
import lk.ijse.hostel.util.RegexUtil;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StudentAddFromController  implements Initializable {
    public JFXTextField txtFistName;
    public JFXTextField txtLastName;
    public JFXTextField txtCity;
    public JFXTextField txtStreet;
    public JFXTextField txtLane;
    public JFXTextField txtContact;
    public JFXComboBox cmbGender;
    public DatePicker dpBirthDay;
    public JFXButton btnDone;

    StudentDto studentDto = new StudentDto();

    StudentService studentService;

    public void btnDonOnAction(ActionEvent actionEvent) {
        studentDto.setStudent_id(id());
        studentDto.setFist_name(txtFistName.getText());
        studentDto.setLast_name(txtLastName.getText());
        studentDto.setCity(txtCity.getText());
        studentDto.setStreet(txtStreet.getText());
        studentDto.setLane(txtLane.getText());
        studentDto.setContact_no(txtContact.getText());
        studentDto.setGender((String) cmbGender.getValue());
        studentDto.setBirthday(LocalDate.parse(getdate()));

        studentService= StudentServiceImpl.getInstants();
        String s = studentService.saveStudent(studentDto);
        if (s!=null){
            new Alert(Alert.AlertType.CONFIRMATION,"Ok").show();
            StudentFromController.getInstance().loadAllIdForTable();
            Navigation.close(StudentFromController.getInstance().pane);
        }else {
            new Alert(Alert.AlertType.ERROR,"Something Wong").show();
        }
    }

    private String id() {
        studentService=StudentServiceImpl.getInstants();

        List<StudentIdProjection> allId = studentService.getAllIdsByOrder();
            String lastId = null;
            for (int i = 0; i < allId.size(); i++) {
                lastId = allId.get(i).getStudent_id();
                System.out.println(allId.get(i));
            }
            try {
                String[] e00s = lastId.split("S00");
                int idIndex = Integer.parseInt(e00s[1]);
                idIndex++;
                System.out.println(idIndex);
                return "S00" + idIndex;
            } catch (Exception e) {
                return "S001";
            }
    }

    private String getdate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date = dpBirthDay.getValue();
        if (date != null) {
            return formatter.format(date);
        } else {
            return null;
        }

    }

    public void closeOnMouseCick(MouseEvent mouseEvent) {
        Navigation.close(StudentFromController.getInstance().pane);
    }

    public void fistName(KeyEvent keyEvent) {
        RegexUtil.regex(btnDone,txtFistName,txtFistName.getText(),"^([A-Z a-z]{5,40})$","-fx-text-fill: black");

    }

    public void lastName(KeyEvent keyEvent) {
        RegexUtil.regex(btnDone,txtLastName,txtLastName.getText(),"^([A-Z a-z]{5,40})$","-fx-text-fill: black");

    }

    public void city(KeyEvent keyEvent) {
        RegexUtil.regex(btnDone,txtCity,txtCity.getText(),"^([A-Z a-z]{5,40})$","-fx-text-fill: black");

    }

    public void streeet(KeyEvent keyEvent) {
        RegexUtil.regex(btnDone,txtStreet,txtStreet.getText(),"^([A-Z a-z]{5,40})$","-fx-text-fill: black");

    }

    public void lane(KeyEvent keyEvent) {
        RegexUtil.regex(btnDone,txtLane,txtLane.getText(),"^([A-Z a-z]{5,40})$","-fx-text-fill: black");

    }

    public void contact(KeyEvent keyEvent) {
        RegexUtil.regex(btnDone,txtContact,txtContact.getText(),"^(?:7|0|(?:\\+94))[0-9]{9,10}$","-fx-text-fill: black");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValueComboBox();
    }
    private void setValueComboBox() {
        cmbGender.getItems().add("Male");
        cmbGender.getItems().add("Female");
        cmbGender.getItems().add("Other");
    }
}
