module com.example.cab302 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.newplan to javafx.fxml;
    exports com.example.newplan;
    exports com.example.newplan.Controller;
    opens com.example.newplan.Controller to javafx.fxml;
}