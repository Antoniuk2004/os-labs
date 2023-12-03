module program.roundrobin {
    requires javafx.controls;
    requires javafx.fxml;


    opens program.roundrobin to javafx.fxml;
    exports program.roundrobin;
}