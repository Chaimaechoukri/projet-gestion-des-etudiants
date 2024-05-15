module com.example.javafxtesting {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires java.desktop;
    opens model to javafx.fxml, javafx.base;
    opens com.example.javafxtesting to javafx.fxml;
    exports com.example.javafxtesting;
    exports controller;
    opens controller to javafx.fxml;
}