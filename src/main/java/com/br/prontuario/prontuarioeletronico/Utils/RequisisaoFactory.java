package com.br.prontuario.prontuarioeletronico.Utils;


import com.br.prontuario.prontuarioeletronico.Model.CepDTO;
import com.br.prontuario.prontuarioeletronico.Model.LoginDTO;
import com.br.prontuario.prontuarioeletronico.Model.User;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

public class RequisisaoFactory {
    public static String webService = "http://localhost:8080/";
    static int codigoSucesso = 200;

    String res = "";

    public static  User login(LoginDTO login){
        Gson gson = new Gson();
        try {
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.
                    create(mediaType, gson.toJson(login));
            Request request = new Request.Builder()
                    .url(webService+"auth/login")
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("User-Agent", "insomnia/2023.5.8")
                    .build();

            Response response = client.newCall(request).execute();
            if(response.isSuccessful()){
                User user = gson.fromJson(response.body().string(), User.class);
                return user;
            }else{
                return null;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static CepDTO getStreet(String cep){
        Gson gson = new Gson();
        try{
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://brasilapi.com.br/api/cep/v2/"+cep)
                    .get()
                    .addHeader("User-Agent", "insomnia/2023.5.8")
                    .build();

            Response response = client.newCall(request).execute();
            CepDTO c = gson.fromJson(response.body().string(), CepDTO.class);
            return c;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
