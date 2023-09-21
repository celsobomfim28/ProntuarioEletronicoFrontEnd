package com.br.prontuario.prontuarioeletronico;

import com.br.prontuario.prontuarioeletronico.Utils.Util;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginApplication extends Application {

    public static Class thisClass;

    public LoginApplication(){
        thisClass = getClass();
    }
    @Override
    public void start(Stage stage) throws IOException {
        thisClass = getClass();
        Util.stageGlobal = stage;

        Parent root = FXMLLoader.load(LoginApplication.class.getResource("login-view.fxml"));
        Platform.setImplicitExit(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.hide();
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * Inicia outra página
     *
     * @param nameFile
     * @param titlePage
     */
    public static void loadScene(String nameFile, String titlePage, Stage stage) {

        Parent root;
        try {
            root = FXMLLoader.load(LoginApplication.class.getResource(nameFile));
            Scene scene = new Scene(root);
            stage.setTitle(titlePage);
            stage.setScene(scene);
            stage.hide();
            stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}