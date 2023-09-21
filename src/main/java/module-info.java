module com.br.prontuario.prontuarioeletronico {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires com.google.gson;
    requires java.desktop;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    requires jakarta.ws.rs;
    requires okhttp3;
    requires MaterialFX;


    opens com.br.prontuario.prontuarioeletronico to javafx.fxml;
    exports com.br.prontuario.prontuarioeletronico;
    exports com.br.prontuario.prontuarioeletronico.Controllers;
    exports com.br.prontuario.prontuarioeletronico.Model to com.google.gson;
    opens com.br.prontuario.prontuarioeletronico.Controllers to javafx.fxml;
}