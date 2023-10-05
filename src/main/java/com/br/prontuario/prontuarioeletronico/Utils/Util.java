package com.br.prontuario.prontuarioeletronico.Utils;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static int calcularIdade(String dataNascimento) {
        // Formato da data de nascimento
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Obtém a data atual
        LocalDate dataAtual = LocalDate.now();

        // Converte a String de data de nascimento em um objeto LocalDate
        LocalDate dataNasc = LocalDate.parse(dataNascimento, formatoData);

        // Calcula a diferença entre a data de nascimento e a data atual
        Period periodo = Period.between(dataNasc, dataAtual);

        // Obtém a idade a partir do período
        int idade = periodo.getYears();

        return idade;
    }
    public static boolean validarCPF(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF possui 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) {
            digito1 = 0;
        }

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) {
            digito2 = 0;
        }

        // Verifica se os dígitos verificadores são iguais aos dígitos originais
        return cpf.charAt(9) == Character.forDigit(digito1, 10) && cpf.charAt(10) == Character.forDigit(digito2, 10);
    }
    public static boolean validarEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
    public static Alert alerta(Alert.AlertType tipo, String mens) {
        Alert a = new Alert(tipo);
        a.setContentText(mens);
        return a;
    }
}
