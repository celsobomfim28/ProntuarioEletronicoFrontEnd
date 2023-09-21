package com.br.prontuario.prontuarioeletronico.Controllers;

import com.br.prontuario.prontuarioeletronico.LoginApplication;
import com.br.prontuario.prontuarioeletronico.Model.LoginDTO;
import com.br.prontuario.prontuarioeletronico.Model.User;
import com.br.prontuario.prontuarioeletronico.Utils.FxDialogs;
import com.br.prontuario.prontuarioeletronico.Utils.RequisisaoFactory;
import com.br.prontuario.prontuarioeletronico.Utils.Util;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField tf_user;
    @FXML
    private TextField tf_password;

    @FXML
    protected void OnClickButtonLogin(){
        LoginDTO loginDTO = new LoginDTO(tf_user.getText(), tf_password.getText());
        User login = RequisisaoFactory.login(loginDTO);
        if(login != null){
            LoginApplication.loadScene("home.fxml",
                    "Home", Util.stageGlobal);
        }

    }

}