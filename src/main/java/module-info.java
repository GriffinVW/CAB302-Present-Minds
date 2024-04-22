module com.example.cab302 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.cab302 to javafx.fxml;
    exports com.example.cab302;
    exports com.example.cab302.Controller;
    opens com.example.cab302.Controller to javafx.fxml;
}