import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {

        launch(args);


    }



    @Override
    public void start(Stage stage) throws IOException {
        Parent parent =  FXMLLoader.load(getClass().getResource("/view/LoginFrom.fxml"));
        stage.setScene(new Scene(parent));
        stage.setTitle("Hostel");
        stage.centerOnScreen();
        stage.show();
        boolean []booleans={true};

    }
}
