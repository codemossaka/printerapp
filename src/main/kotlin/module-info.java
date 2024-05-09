module ru.atmosphere.printer {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.web;
    requires jdk.jsobject;
    requires java.net.http;
    requires java.desktop;

    opens ru.atmosphere.printer to javafx.fxml;
    exports ru.atmosphere.printer;
}