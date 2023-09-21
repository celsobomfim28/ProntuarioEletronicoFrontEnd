package com.br.prontuario.prontuarioeletronico.Controllers;

import com.br.prontuario.prontuarioeletronico.Utils.RequisisaoFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.awt.*;

public class PacienteCadControll {

    @FXML
    TextField tf_cpf;
    @FXML
    TextField tf_cep;
    @FXML
    TextField tf_rua;
    @FXML
    TextField tf_estado;
    @FXML
    TextField tf_cidade;
    @FXML
    TextField tf_bairro;

    @FXML
    private void initialize()
    {
        tf_cep.focusedProperty().addListener((observableValue, oldPropertyvalue, newPropertyValue) -> {
            if (!newPropertyValue)
            {
                var street = RequisisaoFactory.getStreet(tf_cep.getText());
                if(street != null){
                    tf_bairro.setText(street.neighborhood());
                    tf_cidade.setText(street.city());
                    tf_rua.setText(street.street());
                    tf_estado.setText(street.state());
                }
            }
        });
    }
}
