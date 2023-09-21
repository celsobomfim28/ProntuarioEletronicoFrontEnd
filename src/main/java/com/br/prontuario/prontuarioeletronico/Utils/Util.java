package com.br.prontuario.prontuarioeletronico.Utils;

import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
    public static Stage stageGlobal;


    public static LocalDate toLocalDate(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(data, formatter);
        return localDate;
    }
    public static String toStringDate(LocalDate data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedString = data.format(formatter);
        return formattedString;
    }
    public static String getDataHoraAtual() {
        return LocalDateTime.now().toString();
    }

    public static void showNotification(String title, String mensagem){
        Notifications.create()
                .title(title)
                .text(mensagem)
                .darkStyle()
                .show();
    }

    public static String converteJsonEmString(BufferedReader buffereReader) throws IOException {
        String resposta, jsonEmString = "";
        while ((resposta = buffereReader.readLine()) != null) {
            jsonEmString += resposta;
        }
        return jsonEmString;
    }
}
