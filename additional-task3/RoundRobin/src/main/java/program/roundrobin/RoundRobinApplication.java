package program.roundrobin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RoundRobinApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        RoundRobin roundRobin = new RoundRobin();

        FXMLLoader fxmlLoader = new FXMLLoader(RoundRobinApplication.class.getResource("program-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 720);
        stage.setTitle("RoundRobin");
        stage.setScene(scene);
        stage.setMinHeight(720);
        stage.setMinWidth(720);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}