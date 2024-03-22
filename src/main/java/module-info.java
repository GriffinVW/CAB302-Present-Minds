module com.example.cab302 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.cab302 to javafx.fxml;
    exports com.example.cab302;
}