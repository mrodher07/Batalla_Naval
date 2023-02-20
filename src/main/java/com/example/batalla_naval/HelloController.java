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

    ControlDeJuego control;

    List<Integer> posicionBarco = new ArrayList<>();

    public void initialize(){
        Image fondo = new Image(getClass().getResourceAsStream("imagenes/fondo.jpg"));
        ImageView back = new ImageView(fondo);
        ventana.setBackground(new Background(new BackgroundImage(back.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
               BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        creacionBarcos();
    }

    public void creacionBarcos(){
        posicionBarco.add(1);
        posicionBarco.add(2);
        posicionBarco.add(3);
        posicionBarco.add(4);

        Collections.shuffle(posicionBarco);
        control = new ControlDeJuego();

        //Destructor Azul
        /*ImageView imagenDestructor = new ImageView();
        imagenDestructor.setImage(new Image(getClass().getResourceAsStream("")));*/

        //Lancha Azul
        ImageView imagenLancha = new ImageView();
        imagenLancha.setImage(new Image(getClass().getResourceAsStream("imagenes/Lancha")));
        //posicionBarco(imagenLancha, posicionBarco.remove(0));
        control.aniadirBarco(lanchaA = new Barcos("lancha", "Azul", imagenLancha, control.getBarcos(), ventana));

    }

    public void posicionA(ImageView imagen, int num){

    }

}