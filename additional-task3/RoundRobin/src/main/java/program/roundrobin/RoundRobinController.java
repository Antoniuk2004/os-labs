package program.roundrobin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.IOException;

public class RoundRobinController {
    public GridPane coresContainer;
    public ScrollPane scrollPane;

    @FXML
    protected void onAddProcessButtonClick(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        String processMenuPath = "/program/roundrobin/process-menu.fxml";
        fxmlLoader.setLocation(getClass().getResource(processMenuPath));
        try {
            DialogPane processDialogPane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onAddCoreButtonClick() {
        int numOfColumns = coresContainer.getColumnCount();
        Label newLabel = new Label("CPU %d".formatted(numOfColumns + 1));
        newLabel.setPrefWidth(100);
        newLabel.setAlignment(Pos.CENTER);

        String imagePath = "/program/roundrobin/images/cpuIcon.png";
        ImageView imageView = new ImageView(getClass().getResource(imagePath).toExternalForm());
        imageView.setFitWidth(65);
        imageView.setFitHeight(65);

        coresContainer.add(newLabel, numOfColumns, 0);
        coresContainer.add(imageView, numOfColumns, 1);
        coresContainer.addColumn(numOfColumns);

        GridPane.setHalignment(newLabel, HPos.CENTER);
        GridPane.setHalignment(imageView, HPos.CENTER);

        double width = coresContainer.getWidth();
        coresContainer.setPrefWidth(width + 100);
    }
}