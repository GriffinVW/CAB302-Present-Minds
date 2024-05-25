module com.example.newplan {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.kordamp.bootstrapfx.core;
    requires java.datatransfer;
    requires java.desktop;
    //requires java.desktop;
    //requires java.desktop;

    opens com.example.newplan to javafx.fxml;
    exports com.example.newplan;

    exports com.example.newplan.UIController;
    opens com.example.newplan.UIController to javafx.fxml;

    exports com.example.newplan.model;
    opens com.example.newplan.model to javafx.fxml;

}