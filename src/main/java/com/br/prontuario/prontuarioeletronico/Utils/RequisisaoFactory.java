package com.br.prontuario.prontuarioeletronico.Utils;


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
            User user = gson.fromJson(response.body().string(), User.class);
            return user;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
