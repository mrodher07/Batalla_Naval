package com.example.batalla_naval;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HelloController {

    @FXML
    private AnchorPane ventana;
    @FXML
    private AnchorPane anchorAzul;
    @FXML
    private AnchorPane anchorRojo;
    @FXML
    private AnchorPane globalMark;

    Barcos lanchaR;
    Barcos acorazadoR;
    Barcos destructorR;
    Barcos submarinoR;

    Barcos lanchaA;
    Barcos acorazadoA;
    Barcos destructorA;
    Barcos submarinoA;

    List<Integer> posicionBarco = new ArrayList<>();

    public void initialize(){
        //Image fondo = new Image(getClass().getResourceAsStream("imagenes/fondo.png"));
        //ImageView back = new ImageView(fondo);
        //ventana.setBackground(new Background(new BackgroundImage(back.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
        //        BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        creacionBarcos();
    }

    public void creacionBarcos(){
        posicionBarco.add(1);
        posicionBarco.add(2);
        posicionBarco.add(3);
        posicionBarco.add(4);

        Collections.shuffle(posicionBarco);
        con
    }

}