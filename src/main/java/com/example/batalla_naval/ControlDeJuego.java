package com.example.batalla_naval;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class ControlDeJuego {
    //MediaPlayer mediaPlayer;
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
        ganador = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
            int barcosR = 0;
            int barcosA = 0;

            for(Barcos barco: listaBarcos){
                if (barco.getVida()>0){
                    if(barco.getEquipo().equals("Rojo")){
                        barcosR++;
                    }
                    if (barco.getEquipo().equals("Azul")){
                        barcosA++;
                    }
                }
            }
            if(barcosR == 0 && barcosA >=1){
                nombreGanador = "Azul";
                mostrarGanador(nombreGanador);
                ganador.stop();
            }
            if(barcosA == 0 && barcosR >=1){
                nombreGanador = "Rojo";
                mostrarGanador(nombreGanador);
                ganador.stop();
            }
        }));
        ganador.setCycleCount(Timeline.INDEFINITE);
        ganador.play();
    }

    public void mostrarGanador(String nombreGanador){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("imagenes/iconoApp.png").toString()));

        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(0.5));
        pauseTransition.setOnFinished(event -> {
            Platform.runLater(() -> {
                dialogGanador = alert.getDialogPane();
                if (nombreGanador.equals("Azul")) {
                    dialogGanador.getStylesheets().add(this.getClass().getResource("CSS/ganadorAzul.css").toString());
                    ImageView imageView = new ImageView(new Image(this.getClass().getResource("imagenes/imagenPremio.png").toString()));
                    imageView.setFitHeight(70);
                    imageView.setFitWidth(80);
                    dialogGanador.setGraphic(imageView);
                    /*Media pick = new Media(this.getClass().getResource("musica/cancionVictoria.mp3").toString());
                    mediaPlayer= new MediaPlayer(pick);
                    mediaPlayer.play();*/
                    alert.setTitle("Victoria del Equipo " + nombreGanador);


                } else {
                    dialogGanador.getStylesheets().add(this.getClass().getResource("CSS/ganadorRojo.css").toString());
                    dialogGanador.getStyleClass().add("dialog");
                    ImageView imageView = new ImageView(new Image(this.getClass().getResource("imagenes/imagenPremio.png").toString()));
                    imageView.setFitHeight(70);
                    imageView.setFitWidth(80);
                    dialogGanador.setGraphic(imageView);
                    /*Media pick = new Media(this.getClass().getResource("musica/cancionVictoria.mp3").toString());
                    mediaPlayer= new MediaPlayer(pick);
                    mediaPlayer.play();*/
                    alert.setTitle("Victoria del Equipo " + nombreGanador);
                }

                //mediaPlayer2.stop();
                dialogGanador.getStyleClass().add("dialog");
                alert.setContentText("¡¡¡El Equipo "+nombreGanador+" ha ganado!!!");
                //Inicio inicio = new Inicio();
                /*alert.showAndWait().ifPresent(response -> {
                    try{
                        mediaPlayer.stop();
                        inicio.start(new Stage());
                    }catch (IOException e){
                        throw new RuntimeException(e);
                    }
                });*/
            });
        });
        pauseTransition.play();
    }

}
