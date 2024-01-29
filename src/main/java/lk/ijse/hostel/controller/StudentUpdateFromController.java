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
import lk.ijse.hostel.service.StudentService;
import lk.ijse.hostel.service.impl.StudentServiceImpl;
import lk.ijse.hostel.util.Navigation;
import lk.ijse.hostel.util.RegexUtil;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class StudentUpdateFromController implements Initializable {
    public JFXTextField txtFistName;
    public JFXTextField txtLastName;
    public JFXTextField txtCity;
    public JFXTextField txtStreet;
    public JFXTextField txtLane;
    public JFXComboBox cmbGender;
    public JFXTextField txtContact;

    public DatePicker dpBirthDay;
    public JFXTextField txtStudentId;
    public JFXButton btnDone;

    StudentService service;
    StudentDto studentDto;

    public void btnDonOnAction(ActionEvent actionEvent) {
        studentDto=new StudentDto();
        studentDto.setStudent_id(txtStudentId.getText());
        studentDto.setFist_name(txtFistName.getText());
        studentDto.setLast_name(txtLastName.getText());
        studentDto.setCity(txtCity.getText());
        studentDto.setStreet(txtStreet.getText());
        studentDto.setLane(txtLane.getText());
        studentDto.setContact_no(txtContact.getText());
        studentDto.setGender((String) cmbGender.getValue());
        studentDto.setBirthday(LocalDate.parse(getDate()));

        service= StudentServiceImpl.getInstants();


        if ( service.updateStudent(studentDto)) {
            new Alert(Alert.AlertType.CONFIRMATION,"Ok").show();
            Navigation.close(StudentFromController.getInstance().pane);
            StudentFromController.getInstance().loadAllIdForTable();
        }else {
            new Alert(Alert.AlertType.WARNING,"Something wrong").show();
        }
    }

    private String getDate() {
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

    public void SearchId(ActionEvent actionEvent) {
        service= StudentServiceImpl.getInstants();
       studentDto= service.get(txtStudentId.getText());
        System.out.println(studentDto);
       txtFistName.setText(studentDto.getFist_name());
       txtLastName.setText(studentDto.getLast_name());
       txtContact.setText(studentDto.getContact_no());
       txtCity.setText(studentDto.getCity());
       txtStreet.setText(studentDto.getStreet());
       txtLane.setText(studentDto.getLane());
       cmbGender.getSelectionModel().select(studentDto.getGender());
       dpBirthDay.setValue(studentDto.getBirthday());

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

    public void fistName(KeyEvent keyEvent) {
        RegexUtil.regex(btnDone,txtFistName,txtFistName.getText(),"^([A-Z a-z]{5,40})$","-fx-text-fill: black");

    }

    public void lastName(KeyEvent keyEvent) {
        RegexUtil.regex(btnDone,txtLastName,txtLastName.getText(),"^([A-Z a-z]{5,40})$","-fx-text-fill: black");

    }

    public void city(KeyEvent keyEvent) {
        RegexUtil.regex(btnDone,txtCity,txtCity.getText(),"^([A-Z a-z]{5,40})$","-fx-text-fill: black");

    }

    public void street(KeyEvent keyEvent) {
        RegexUtil.regex(btnDone,txtStreet,txtStreet.getText(),"^([A-Z a-z]{5,40})$","-fx-text-fill: black");

    }

    public void lane(KeyEvent keyEvent) {
        RegexUtil.regex(btnDone,txtLane,txtLane.getText(),"^([A-Z a-z]{5,40})$","-fx-text-fill: black");

    }

    public void contact(KeyEvent keyEvent) {
        RegexUtil.regex(btnDone,txtContact,txtContact.getText(),"^(?:7|0|(?:\\+94))[0-9]{9,10}$","-fx-text-fill: black");

    }
}
