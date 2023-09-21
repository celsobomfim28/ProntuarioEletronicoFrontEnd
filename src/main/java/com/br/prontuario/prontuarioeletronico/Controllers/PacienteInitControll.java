package com.br.prontuario.prontuarioeletronico.Controllers;

import com.br.prontuario.prontuarioeletronico.Utils.FxmlLoader;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class PacienteInitControll {

    @FXML
    private void cadastrar_paciente(){
        FxmlLoader fxmlLoader = new FxmlLoader();
        Pane centro = fxmlLoader.getPage("paciente-cad.fxml");
        HomeController.borderPane.setCenter(centro);
    }
}
