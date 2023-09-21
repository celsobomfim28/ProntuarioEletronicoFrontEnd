package com.br.prontuario.prontuarioeletronico.Utils;

import com.br.prontuario.prontuarioeletronico.LoginApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.net.URL;

public class FxmlLoader {
    private Pane view;

    public Pane getPage(String filename){
        try{
            URL fileUrl = LoginApplication.class.getResource(filename);
            if(fileUrl == null){
                throw new FileNotFoundException("File not found!!");
            }
            view = FXMLLoader.load(fileUrl);
        }catch (Exception e){
            System.out.println("No page "+filename+" please check fxmlLoader");
        }
        return view;
    }
}
