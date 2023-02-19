package com.example.batalla_naval;

import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

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
            imagen.setFitHeight(70);
            imagen.setFitWidth(70);
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
