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

        //Acorazado España
        ImageView imagenAcorazado = new ImageView();
        imagenAcorazado.setImage(new Image(getClass().getResourceAsStream("imagenes/AcorazadoES.png")));
        posicionES(imagenAcorazado, posicionBarco.remove(0));
        control.aniadirBarco(acorazadoES = new Barcos("acorazado", "ES", imagenAcorazado, control.getBarcos(), ventana));

        //Destructor España
        ImageView imagenDestructor = new ImageView();
        imagenDestructor.setImage(new Image(getClass().getResourceAsStream("imagenes/DestructorES.png")));
        posicionES(imagenAcorazado,posicionBarco.remove(0));
        control.aniadirBarco(destructorES = new Barcos("destructor", "ES", imagenDestructor, control.getBarcos(), ventana));

        //Submarino España
        ImageView imagenSubmarino = new ImageView();
        imagenSubmarino.setImage(new Image(getClass().getResourceAsStream("imagenes/SubmarinoES.png")));
        posicionES(imagenSubmarino, posicionBarco.remove(0));
        control.aniadirBarco(submarinoES = new Barcos("submarino", "ES", imagenSubmarino, control.getBarcos(), ventana));

        posicionBarco.add(1);
        posicionBarco.add(2);
        posicionBarco.add(3);
        posicionBarco.add(4);
        Collections.shuffle(posicionBarco);

        //Lancha USA
        ImageView imagenLancha2 = new ImageView();
        imagenLancha2.setImage(new Image(getClass().getResourceAsStream("imagenes/LanchaUSA.png")));
        posicionUSA(imagenLancha2, posicionBarco.remove(0));
        control.aniadirBarco(lanchaUSA = new Barcos("lancha", "USA", imagenLancha2, control.getBarcos(), ventana));

        //Acorazado USA
        ImageView imagenAcorazado2 = new ImageView();
        imagenAcorazado2.setImage(new Image(getClass().getResourceAsStream("imagenes/AcorazadoUSA.png")));
        posicionUSA(imagenAcorazado2, posicionBarco.remove(0));
        control.aniadirBarco(acorazadoUSA = new Barcos("acorazado", "USA", imagenAcorazado2, control.getBarcos(), ventana));

        //Destructor USA
        ImageView imagenDestructor2 = new ImageView();
        imagenDestructor2.setImage(new Image(getClass().getResourceAsStream("imagenes/DestructorUSA.png")));
        posicionUSA(imagenAcorazado2,posicionBarco.remove(0));
        control.aniadirBarco(destructorUSA = new Barcos("destructor", "USA", imagenDestructor2, control.getBarcos(), ventana));

        //Submarino USA
        ImageView imagenSubmarino2 = new ImageView();
        imagenSubmarino2.setImage(new Image(getClass().getResourceAsStream("imagenes/SubmarinoUSA.png")));
        posicionUSA(imagenSubmarino2, posicionBarco.remove(0));
        control.aniadirBarco(submarinoUSA = new Barcos("submarino", "USA", imagenSubmarino2, control.getBarcos(), ventana));


        ventana.getChildren().addAll(lanchaES.getImagen(), acorazadoES.getImagen(), destructorES.getImagen(), submarinoES.getImagen(),
                                    lanchaUSA.getImagen(), acorazadoUSA.getImagen(), destructorUSA.getImagen(), submarinoUSA.getImagen());



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
        if(num==4){
            imagen.setLayoutX(28);
            imagen.setLayoutY(575);
        }
    }

    public void posicionUSA(ImageView imagen, int num) {

        if (num == 1) {
            imagen.setLayoutX(882);
            imagen.setLayoutY(371);
        }

        if (num == 2) {
            imagen.setLayoutX(876);
            imagen.setLayoutY(75);
        }

        if (num == 3) {
            imagen.setLayoutX(876);
            imagen.setLayoutY(147);
        }

        if (num == 4) {
            imagen.setLayoutX(876);
            imagen.setLayoutY(575);
        }

    }

}