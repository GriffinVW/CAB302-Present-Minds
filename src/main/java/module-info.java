module com.example.newplan {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.newplan to javafx.fxml;
    exports com.example.newplan;
}