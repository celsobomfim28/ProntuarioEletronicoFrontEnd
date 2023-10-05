package com.br.prontuario.prontuarioeletronico.Controllers;

import com.br.prontuario.prontuarioeletronico.Utils.RequisisaoFactory;
import com.br.prontuario.prontuarioeletronico.Utils.TextFieldFormatter;
import com.br.prontuario.prontuarioeletronico.Utils.Util;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class PacienteCadControll implements Initializable {
    @FXML Label lb_idade;
    @FXML Label lb_cpf;
    @FXML Label lb_telefone;
    @FXML Label lb_email;
    @FXML Label lb_cidade;

    @FXML MFXButton bt_salvar;

    @FXML TextField tf_nome;
    @FXML TextField tf_cns;
    @FXML TextField tf_email;
    @FXML TextField tf_celular;
    @FXML TextField tf_tel_fixo;
    @FXML TextField tf_outro_tel;
    @FXML TextField tf_rg;
    @FXML TextField tf_emissor;
    @FXML TextField tf_estado_civil;
    @FXML TextField tf_passaport;
    @FXML TextField tf_nome_mae;
    @FXML TextField tf_numero;
    @FXML TextField tf_complemento;
    @FXML TextField tf_natural;
    @FXML TextField tf_cpf;
    @FXML TextField tf_cep;
    @FXML TextField tf_rua;
    @FXML TextField tf_estado;
    @FXML TextField tf_cidade;
    @FXML TextField tf_bairro;

    @FXML ComboBox<String> cb_sexo;
    @FXML ComboBox<String> cb_genero;
    @FXML ComboBox<String> cb_tipo_sangue;

    @FXML CheckBox cb_no_cpf;
    @FXML CheckBox cb_hipertenso;
    @FXML CheckBox cb_diabetico;
    @FXML CheckBox cb_gestante;
    @FXML CheckBox cb_estrangeiro;

    @FXML
    DatePicker tf_data;

    @FXML TextArea ta_obs_gerais;
    @FXML TextArea ta_obs_clinicas;
    @FXML TextArea ta_alergias;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        fieldsValidator();

        populateCombobox();

        StringConverter<LocalDate> stringConverter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate object) {
                if (object != null) {
                    return dateFormatter.format(object);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        tf_data.setConverter(stringConverter);
        tf_data.focusedProperty().addListener((observableValue, oldPropertyvalue, newPropertyValue) -> {
            if (!newPropertyValue){
                lb_idade.setText(String.valueOf(Util.calcularIdade(
                        Util.toStringDate(tf_data.getValue())))+" Ano(s)");
                lb_idade.setVisible(true);
            }
        });

        tf_cidade.focusedProperty().addListener((observableValue, oldPropertyvalue, newPropertyValue) -> {
            if (!newPropertyValue) {
                lb_cidade.setText(tf_cidade.getText());
                lb_cidade.setVisible(true);
            }
        });

        setTextLabel(tf_cpf, lb_cpf, tf_celular, lb_telefone);

        tf_email.focusedProperty().addListener((observableValue, oldPropertyvalue, newPropertyValue) -> {
            if (!newPropertyValue) {
                if(Util.validarEmail(tf_email.getText())){
                    lb_email.setText(tf_email.getText());
                    lb_email.setVisible(true);
                }else{
                    Util.alerta(Alert.AlertType.ERROR, "Email inválido");
                    tf_email.setText("");
                }
            }
        });

        tf_cep.focusedProperty().addListener((observableValue, oldPropertyvalue, newPropertyValue) -> {
            if (!newPropertyValue)
            {
                var street = RequisisaoFactory.getStreet(tf_cep.getText());
                if(street != null){
                    tf_bairro.setText(street.neighborhood());
                    tf_cidade.setText(street.city());
                    lb_cidade.setText(tf_cidade.getText());
                    lb_cidade.setVisible(true);
                    tf_rua.setText(street.street());
                    tf_estado.setText(street.state());
                }
            }
        });

        cb_no_cpf.setOnAction(actionEvent -> {
            if (cb_no_cpf.isSelected()){
                tf_cpf.setText("");
                tf_cpf.setDisable(true);
            }else {
                tf_cpf.setDisable(false);
            }
        });

        cb_estrangeiro.setOnAction(actionEvent -> {
            if(cb_estrangeiro.isSelected()){
                tf_passaport.setText("");
                tf_passaport.setDisable(true);
            }else {
                tf_passaport.setDisable(false);
            }
        });


        tf_cns.setOnKeyReleased(event -> {
            TextFieldFormatter formatter = new TextFieldFormatter();
            formatter.formatter(tf_cns, "0123456789", "###.####.####.####");
        });
        tf_tel_fixo.setOnKeyReleased(event -> {
            TextFieldFormatter formatter = new TextFieldFormatter();
            formatter.formatter(tf_tel_fixo, "0123456789", "(##)####-####");
        });
        tf_celular.setOnKeyReleased(event -> {
            TextFieldFormatter formatter = new TextFieldFormatter();
            formatter.formatter(tf_celular, "0123456789", "(##)#####-####");
        });
        tf_outro_tel.setOnKeyReleased(event -> {
            TextFieldFormatter formatter = new TextFieldFormatter();
            formatter.formatter(tf_outro_tel, "0123456789", "(##)#####-####");
        });
        tf_cpf.setOnKeyReleased(event -> {
            TextFieldFormatter formatter = new TextFieldFormatter();
            formatter.formatter(tf_cpf, "0123456789", "###.###.###-##");
        });
        tf_cep.setOnKeyReleased(event -> {
            TextFieldFormatter formatter = new TextFieldFormatter();
            formatter.formatter(tf_cep, "0123456789", "#####-###");
        });
    }

    private void populateCombobox() {
        String [] sexo = {"Masculino", "Feminino"};
        cb_sexo.setItems(FXCollections.observableArrayList(sexo));

        String [] genero = {"Cisgênero", "Transgênero", "Não Binário", "Agênero"};
        cb_genero.setItems(FXCollections.observableArrayList(genero));

        String [] tipo = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-" };
        cb_tipo_sangue.setItems(FXCollections.observableArrayList(tipo));
    }

    private void setTextLabel(TextField tfCpf, Label lbCpf, TextField tfCelular,
                              Label lbTelefone) {
        tfCpf.focusedProperty().addListener((observableValue, old, newProp) ->{
            if(!newProp){

                if(Util.validarCPF(tfCpf.getText())){
                    lbCpf.setText(tfCpf.getText());
                    lbCpf.setVisible(true);
                }else{
                    if(!tfCpf.isDisable()){
                        Util.alerta(Alert.AlertType.ERROR, "CPF inválido");
                        tfCpf.setText("");
                    }
                }
            }
        });
        tfCelular.focusedProperty().addListener((observableValue, old, newProp) ->{
            if(!newProp){
                lbTelefone.setText(tfCelular.getText());
                lbTelefone.setVisible(true);
            }
        });
    }



    private void fieldsValidator() {
        ValidationSupport validationSupport = new ValidationSupport();

        validationSupport.registerValidator(tf_nome,
                Validator.createEmptyValidator("Esse campo é obrigatório"));
        validationSupport.registerValidator(tf_cns,
                Validator.createEmptyValidator("Esse campo é obrigatório"));
        validationSupport.registerValidator(tf_data,
                Validator.createEmptyValidator("Esse campo é obrigatório"));
        validationSupport.registerValidator(tf_cpf,
                Validator.createEmptyValidator("Esse campo é obrigatório"));
        validationSupport.registerValidator(tf_estado_civil,
                Validator.createEmptyValidator("Esse campo é obrigatório"));
        validationSupport.registerValidator(tf_rua,
                Validator.createEmptyValidator("Esse campo é obrigatório"));
        validationSupport.registerValidator(tf_cidade,
                Validator.createEmptyValidator("Esse campo é obrigatório"));
        validationSupport.registerValidator(tf_estado,
                Validator.createEmptyValidator("Esse campo é obrigatório"));
        validationSupport.registerValidator(tf_natural,
                Validator.createEmptyValidator("Esse campo é obrigatório"));
        validationSupport.registerValidator(tf_data,
                Validator.createEmptyValidator("Esse campo é obrigatório"));

        if(!tf_rg.getText().isEmpty()){
            validationSupport.registerValidator(tf_emissor,
                    Validator.createEmptyValidator("Esse campo é obrigatório"));
        }
        if(cb_estrangeiro.isSelected()){
            validationSupport.registerValidator(tf_passaport,
                    Validator.createEmptyValidator("Esse campo é obrigatório"));
        }

        if(!cb_no_cpf.isSelected()){
            validationSupport.registerValidator(tf_cpf,
                    Validator.createEmptyValidator("Esse campo é obrigatório"));
        }


    }
}
