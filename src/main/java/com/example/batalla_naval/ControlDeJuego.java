package com.example.batalla_naval;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class ControlDeJuego {
    String nombreGanador = "";
    Timeline ganador;
    DialogPane dialogGanador;
    ArrayList<Barcos> listaBarcos;

    public ControlDeJuego(){
        listaBarcos = new ArrayList<Barcos>();
        dialogGanador = new DialogPane();
    }

    public synchronized void aniadirBarco(Barcos barco){
        listaBarcos.add(barco);
    }
    public synchronized ArrayList<Barcos> getBarcos(){return listaBarcos;}

    public void finDeJuego(){
        ganador = new Timeline(new KeyFrame(Duration.seconds(0.5), e -> {
            int barcosES = 0;
            int barcosUSA = 0;

            for(Barcos barco: listaBarcos){
                if (barco.getVida()>0){
                    if(barco.getEquipo().equals("ES")){
                        barcosES++;
                    }
                    if (barco.getEquipo().equals("USA")){
                        barcosUSA++;
                    }
                }
            }
            if(barcosES == 0 && barcosUSA >=1){
                nombreGanador = "Estados Unidos";
                mostrarGanador(nombreGanador);
                ganador.stop();
            }
            if(barcosUSA == 0 && barcosES >=1){
                nombreGanador = "España";
                mostrarGanador(nombreGanador);
                ganador.stop();
            }
        }));
        ganador.setCycleCount(Timeline.INDEFINITE);
        ganador.play();
    }

    public void mostrarGanador(String nombreGanador){
        Alert alertaVictoria = new Alert(Alert.AlertType.INFORMATION);
        alertaVictoria.setHeaderText(null);

        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(0.5));
        pauseTransition.setOnFinished(event -> {
            Platform.runLater(() -> {
                dialogGanador = alertaVictoria.getDialogPane();
                if (nombreGanador.equals("ESA")) {
                    alertaVictoria.setTitle("Victoria del Equipo " + nombreGanador);
                } else {
                    alertaVictoria.setTitle("Victoria de " + nombreGanador);
                }

                dialogGanador.getStyleClass().add("dialog");
                alertaVictoria.setContentText("¡¡¡"+nombreGanador+" ha ganado!!!");
                alertaVictoria.showAndWait().ifPresent(response -> {
                });
            });
        });
        pauseTransition.play();
    }

}
