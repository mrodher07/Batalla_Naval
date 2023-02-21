package com.example.batalla_naval;

import javafx.fxml.FXML;
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
    private AnchorPane anchorUSA;
    @FXML
    private AnchorPane anchorES;
    @FXML
    private AnchorPane globalMark;

    Barcos lanchaES;
    Barcos acorazadoES;
    Barcos destructorES;
    Barcos submarinoES;

    Barcos lanchaUSA;
    Barcos acorazadoUSA;
    Barcos destructorUSA;
    Barcos submarinoUSA;

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

        //Lancha España
        ImageView imagenLancha = new ImageView();
        imagenLancha.setImage(new Image(getClass().getResourceAsStream("imagenes/LanchaES.png")));
        posicionES(imagenLancha, posicionBarco.remove(0));
        control.aniadirBarco(lanchaES = new Barcos("lancha", "ES", imagenLancha, control.getBarcos(), ventana));

        ImageView imagenAcorazado = new ImageView();
        imagenAcorazado.setImage(new Image(getClass().getResourceAsStream("imagenes/AcorazadoES.png")));
        posicionES(imagenAcorazado, posicionBarco.remove(0));
        control.aniadirBarco(acorazadoES = new Barcos("acorazado", "ES", imagenAcorazado, control.getBarcos(), ventana));

        ventana.getChildren().addAll(lanchaES.getImagen(), acorazadoES.getImagen());

        control.finDeJuego();

    }

    public void posicionES(ImageView imagen, int num){
        if(num==1){
            imagen.setLayoutX(28);
            imagen.setLayoutY(371);
        }
        if(num==2){
            imagen.setLayoutX(28);
            imagen.setLayoutY(75);
        }
        if(num==3){
            imagen.setLayoutX(28);
            imagen.setLayoutY(149);
        }
        if(num==1){
            imagen.setLayoutX(28);
            imagen.setLayoutY(575);
        }
    }

}