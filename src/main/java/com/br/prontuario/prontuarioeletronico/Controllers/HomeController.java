package com.br.prontuario.prontuarioeletronico.Controllers;

import com.br.prontuario.prontuarioeletronico.LoginApplication;
import com.br.prontuario.prontuarioeletronico.Utils.FxmlLoader;
import com.br.prontuario.prontuarioeletronico.Utils.Util;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class HomeController {

    @FXML
    public BorderPane geral_border_pane;

    @FXML
    MFXButton bt_home;

    @FXML
    MFXButton bt_paciente;

    @FXML
    MFXButton bt_relatorio;

    public static BorderPane borderPane;

    @FXML
    public void open_pacient(){
        borderPane = geral_border_pane;
        bt_paciente.setStyle("-fx-text-fill: #D9A648; -fx-background-color:  #2793F2");
        bt_home.setStyle("-fx-text-fill: white; -fx-background-color:  #2793F2");
        FxmlLoader fxmlLoader = new FxmlLoader();
        Pane centro = fxmlLoader.getPage("paciente-init.fxml");
        geral_border_pane.setCenter(centro);
    }

    @FXML
    public void open_relatorio(){
        borderPane = geral_border_pane;
        bt_paciente.setStyle("-fx-text-fill: white; -fx-background-color:  #2793F2");
        bt_home.setStyle("-fx-text-fill: white; -fx-background-color:  #2793F2");
        bt_relatorio.setStyle("-fx-text-fill: #D9A648; -fx-background-color:  #2793F2");
        FxmlLoader fxmlLoader = new FxmlLoader();
        Pane centro = fxmlLoader.getPage("relatorio.fxml");
        geral_border_pane.setCenter(centro);
    }
    @FXML
    public void open_home(){
        LoginApplication.loadScene("home.fxml",
                "Home", Util.stageGlobal);
    }
}
