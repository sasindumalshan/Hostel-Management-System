package lk.ijse.hostel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lk.ijse.hostel.dto.StudentDto;
import lk.ijse.hostel.projection.StudentIdProjection;
import lk.ijse.hostel.service.StudentService;
import lk.ijse.hostel.service.impl.StudentServiceImpl;
import lk.ijse.hostel.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StudentFromController implements Initializable {
    public Pane pane;

    private static StudentFromController controller;
    public VBox vbox;

    public StudentFromController() {
        controller=this;
    }
    public static StudentFromController getInstance(){
        return controller;
    }
    StudentService service;

    public void addOnAction(ActionEvent actionEvent) {
        Navigation.onTheTopNavigation(pane,"StudentAddFrom.fxml");
    }

    public void updateOnAction(ActionEvent actionEvent) {
        Navigation.onTheTopNavigation(pane,"StudentUpdate.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAllIdForTable();
    }

    public void loadAllIdForTable() {
        vbox.getChildren().clear();
        service= StudentServiceImpl.getInstants();
        List<StudentIdProjection> ids = service.getAllIdsByOrder();
        for (StudentIdProjection id:ids ) {
            loadDataTable(id);
        }
    }

    private void loadDataTable(StudentIdProjection id) {
        service= StudentServiceImpl.getInstants();
        StudentDto dto = service.get(id.getStudent_id());

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/StudentBarFrom.fxml"));
            Parent root = loader.load();
            StudentBarFromController controller = loader.getController();
            controller.setData(dto);
            vbox.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
