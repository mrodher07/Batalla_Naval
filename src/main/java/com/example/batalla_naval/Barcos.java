package com.example.batalla_naval;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Barcos {

    //private MediaPlayer mediaPlayer;
    private Timeline movimiento;
    private ArrayList<Barcos> listaBarcos;
    private int sonar;
    private int potenciaDisparo;
    private long tiempoRecarga;
    private String nombre;
    private int vida;
    private int velocidad;
    private ImageView imagen;
    private double direccion;
    private String equipo;
    private boolean modoDisparo;
    private AnchorPane fondo;
    private long tiempoUltimoDisparo = 0;

    public Barcos(String nombre, String equipo, ImageView imagen, ArrayList<Barcos> listaBarcos, AnchorPane ventana){
        //Parámetros de barco
        this.nombre = nombre;
        this.equipo = equipo;
        this.imagen = imagen;
        this.listaBarcos = listaBarcos;
        this.fondo = ventana;
        this.modoDisparo = false;
        this.direccion = 45;

        //Según el tipo de barco le damos sus características
        if (nombre.contains("acorazado")) {
            vida = 120;
            velocidad = 8;
            sonar = 204;
            potenciaDisparo = 80;
            tiempoRecarga = 8000;
            imagen.setFitHeight(90);
            imagen.setFitWidth(90);
        } else if (nombre.contains("lancha")) {
            vida = 10;
            velocidad = 15;
            sonar = 75;
            potenciaDisparo = 20;
            tiempoRecarga = 2000;
            imagen.setFitHeight(30);
            imagen.setFitWidth(30);
        } else if (nombre.contains("destructor")) {
            vida = 80;
            //5
            velocidad = 10;
            sonar = 153;
            potenciaDisparo = 50;
            tiempoRecarga = 6000;
            imagen.setFitHeight(40);
            imagen.setFitWidth(50);
        } else if (nombre.contains("submarino")) {
            vida = 30;
            //2
            velocidad = 7;
            sonar = 102;
            potenciaDisparo = 60;
            tiempoRecarga = 4000;
            imagen.setFitHeight(40);
            imagen.setFitWidth(40);
        }

        movimiento = new Timeline(new KeyFrame(Duration.seconds(0.05), e -> {
            if(!modoDisparo){
                detectarBarcosEnemigos();
                detectarParedes();
                movimientoBarco();
            }
            pararBarcoMuerto();
        }));
        movimiento.setCycleCount(Timeline.INDEFINITE);
        movimiento.play();
    }

    public synchronized void movimientoBarco(){
        double ejeX = this.getImagen().getLayoutX();
        double ejeY = this.getImagen().getLayoutY();
        double velocidadBarco = this.getVelocidad();
        double direccionBarco = Math.toRadians(getDireccion());
        ejeX += velocidadBarco * Math.cos(direccionBarco);
        ejeY += velocidadBarco * Math.sin(direccionBarco);
        this.getImagen().setLayoutX(ejeX);
        this.getImagen().setLayoutY(ejeY);
        this.getImagen().setRotate(this.getDireccion());
    }

    public synchronized void detectarBarcosEnemigos(){
        if(recargandoBala() || getVida() <=0){
            return;
        }

        for(Barcos barco: listaBarcos){
            if(barco == this){
                continue;
            }
            double distancia = Math.sqrt(Math.pow(barco.getImagen().getLayoutX() - this.getImagen().getLayoutX(), 2) +
                    Math.pow(barco.getImagen().getLayoutY() - this.getImagen().getLayoutY(), 2));

            if(barco.getNombre().contains("submarino")){
                distancia -= 50;
            }

            if(distancia < getSonar() && this.getEquipo() != barco.getEquipo() && barco.getVida() > 0){
                pararBarcos(this, barco);
                tiempoUltimoDisparo = System.currentTimeMillis();
                int disparar = this.disparo();
                barco.setVida(barco.getVida()-disparar);
                //cargarSonidoCanon();
                balaCañonMovimiento(this, barco);
                break;
            }
        }
    }

    private void balaCañonMovimiento(Barcos barco1, Barcos barco2) {

        ImageView bala = new ImageView(new Image(getClass().getResourceAsStream("imagenes/Bala.png")));
        fondo.getChildren().add(bala);
        bala.setFitHeight(25);
        bala.setFitWidth(25);

        double barco1X = barco1.getImagen().getBoundsInParent().getMinX() + barco1.getImagen().getBoundsInParent().getWidth() / 2;
        double barco1Y = barco1.getImagen().getBoundsInParent().getMinY() + barco1.getImagen().getBoundsInParent().getHeight() / 2;

        if(barco1.getNombre().equals("lancha") || barco1.getNombre().equals("destructor")){
            barco1X -= 6;
            barco1Y -= 6;
        }

        double barco2X = barco2.getImagen().getBoundsInParent().getMinX() + barco2.getImagen().getBoundsInParent().getWidth() / 2;
        double barco2Y = barco2.getImagen().getBoundsInParent().getMinY() + barco2.getImagen().getBoundsInParent().getHeight() / 2;


        if (barco2.getNombre().equals("lancha") || barco2.getNombre().equals("destructor")) {
            barco2X -= 6;
            barco2Y -= 6;
        }

        Timeline animacion = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(bala.xProperty(), barco1X),
                        new KeyValue(bala.yProperty(), barco1Y)),
                new KeyFrame(Duration.seconds(0.15), new KeyValue(bala.xProperty(), barco2X),
                        new KeyValue(bala.yProperty(), barco2Y))
        );

        animacion.setOnFinished(e->{
            int ultimoIndex = fondo.getChildren().size() - 1;
            fondo.getChildren().remove(ultimoIndex);
            barco1.setModoDisparo(false);
            barco2.setModoDisparo(false);
            pasarABarcoMuerto(barco2);
        });

        animacion.play();

    }

    public synchronized int disparo(){
        Random random = new Random();
        int numRandom = random.nextInt(101);
        if(numRandom < 25){
            return 0;
        } else if (numRandom < 50) {
            return potenciaDisparo / 2;
        }else{
            return potenciaDisparo;
        }
    }

    public synchronized void pararBarcos(Barcos barco1, Barcos barco2){
        barco1.setModoDisparo(true);
        barco2.setModoDisparo(true);
    }

    public synchronized boolean recargandoBala(){
        long tiempoActual = System.currentTimeMillis();
        return tiempoActual < tiempoUltimoDisparo + tiempoRecarga;
    }

    public synchronized void pararBarcoMuerto(){
        if(this.getVida() <= 0){
            movimiento.stop();
            this.vida = 0;
        }
    }

    public synchronized void pasarABarcoMuerto(Barcos barco){
        if(barco.getVida() <= 0){
            if (barco.getNombre().equals("acorazado")) {
                barco.imagen.setFitHeight(90);
                barco.imagen.setFitWidth(90);
            }

            if (barco.getNombre().equals("lancha")) {
                barco.imagen.setFitHeight(30);
                barco.imagen.setFitWidth(30);
            }

            if (barco.getNombre().equals("submarino")) {
                barco.imagen.setFitHeight(40);
                barco.imagen.setFitWidth(40);
            }

            if (barco.getNombre().equals("destructor")) {
                barco.imagen.setFitHeight(40);
                barco.imagen.setFitWidth(50);
            }

            barco.movimiento.stop();

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    barco.fondo.getChildren().remove(barco.getImagen());
                }
            }));
            timeline.play();
            barco.vida = 0;
        }
    }

    public synchronized void setModoDisparo(boolean modoDisparo) {
        this.modoDisparo = modoDisparo;
    }
    public void setDireccion(double direccionBarco) {
        this.direccion = direccionBarco;
    }
    public double getDireccion() {
        return direccion;
    }
    public String getEquipo() {
        return equipo;
    }
    public ImageView getImagen() {
        return imagen;
    }
    public String getNombre() {
        return nombre;
    }
    public void setVida(int vida) {
        this.vida = vida;
    }
    public int getVida() {
        return vida;
    }
    public int getVelocidad() {
        return velocidad;
    }

    public int getSonar() {
        return sonar;
    }
    public synchronized void detectarParedes() {
        Colisiones.detectarColision(this);
    }



}
