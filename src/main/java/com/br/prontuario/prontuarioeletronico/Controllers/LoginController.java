package com.br.prontuario.prontuarioeletronico.Controllers;

import com.br.prontuario.prontuarioeletronico.LoginApplication;
import com.br.prontuario.prontuarioeletronico.Model.LoginDTO;
import com.br.prontuario.prontuarioeletronico.Model.User;
import com.br.prontuario.prontuarioeletronico.Utils.RequisisaoFactory;
import com.br.prontuario.prontuarioeletronico.Utils.Util;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginController {
    @FXML
    private TextField tf_user;
    @FXML
    private TextField tf_password;
    @FXML
    MFXButton bt_login;
    private User user;
    private Alert progressDialog;

    @FXML
    protected void OnClickButtonLogin() {

        Task<User> webServiceTask = new Task<User>() {
            @Override
            protected User call() throws Exception {
                user = RequisisaoFactory.login(new LoginDTO(tf_user.getText(), tf_password.getText()));
                return user;
            }
        };

        // Configura o que acontece após o término da tarefa
        webServiceTask.setOnSucceeded(workerStateEvent -> {
            // Verifica o resultado da tarefa
            User user = webServiceTask.getValue();
            if (user != null) {
                // Login bem-sucedido
                closeProgressDialog();
                LoginApplication.loadScene("home.fxml", "Home", Util.stageGlobal);
            } else {
                webServiceTask.cancel();
                // Login falhou
                Alert a = Util.alerta(Alert.AlertType.ERROR, "Usuário ou senha inválidos");
                a.setOnCloseRequest(dialogEvent -> {
                    closeProgressDialog();
                });
                a.show();
            }
        });


            // Configura uma ProgressBar para exibir o progresso da tarefa
        ProgressBar progress = new ProgressBar();
        progress.progressProperty().bind(webServiceTask.progressProperty());

        // Cria um diálogo modal personalizado
        createProgressDialog(progress);

        webServiceTask.setOnCancelled(workerStateEvent -> {
            closeProgressDialog();
        });

        webServiceTask.setOnFailed(workerStateEvent -> {
            closeProgressDialog();
        });
        // Inicia a tarefa em uma nova thread
        Thread thread = new Thread(webServiceTask);
        thread.setDaemon(true); // A thread será finalizada quando a aplicação for fechada
        thread.start();
    }

    private void createProgressDialog(ProgressBar progressBar) {
        progressDialog = new Alert(Alert.AlertType.NONE);
        progressDialog.getDialogPane().setContent(progressBar);
        progressDialog.getButtonTypes().add(ButtonType.CANCEL);

        // Configura o que acontece quando o diálogo é fechado
        progressDialog.setOnCloseRequest(event -> {
            closeProgressDialog();
        });

        progressDialog.show();
    }

    private void closeProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.setResult(ButtonType.CANCEL);
            progressDialog.close();
        }
    }
}