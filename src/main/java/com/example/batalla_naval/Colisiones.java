package com.example.batalla_naval;

import java.util.Random;

public class Colisiones {

    public static void detectarColision(Barcos barco) {
        double EjeX = barco.getImagen().getLayoutX();
        double EjeY = barco.getImagen().getLayoutY();

        if (barco.getNombre().equals("destructor")) {
            Random random = new Random();
            int randomNumber = random.nextInt(10) + 1;
            if (randomNumber <= 5) {
                if (EjeX < 0 || EjeX > 960) {
                    barco.setDireccion(180 + barco.getDireccion());
                }
                if (EjeY < 0 || EjeY > 700) {
                    barco.setDireccion(180 + barco.getDireccion());
                }
            } else {
                if (EjeX < 0 || EjeX > 960) {
                    barco.setDireccion(-180 + barco.getDireccion());
                }
                if (EjeY < 0 || EjeY > 700) {
                    barco.setDireccion(-barco.getDireccion());
                }
            }
        } else {
            if (barco.getNombre().equals("submarino")) {
                Random random = new Random();
                int randomNumber = random.nextInt(10) + 1;
                if (randomNumber >= 5) {
                    if (EjeX < 0 || EjeX > 960) {
                        barco.setDireccion(180 + barco.getDireccion());
                    }
                    if (EjeY < 0 || EjeY > 700) {
                        barco.setDireccion(180 + barco.getDireccion());
                    }
                } else {
                    if (EjeX < 0 || EjeX > 960) {
                        barco.setDireccion(180 - barco.getDireccion());
                    }
                    if (EjeY < 0 || EjeY > 700) {
                        barco.setDireccion(-barco.getDireccion());
                    }
                }
            } else {
                if (barco.getNombre().equals("lancha")) {
                    Random random = new Random();
                    int randomNumber = random.nextInt(10) + 1;
                    if (randomNumber <= 5) {
                        if (EjeX < 0 || EjeX > 960) {
                            barco.setDireccion(180 + barco.getDireccion());
                        }
                        if (EjeY < 0 || EjeY > 700) {
                            barco.setDireccion(180 + barco.getDireccion());
                        }
                    } else {
                        if (EjeX < 0 || EjeX > 960) {
                            barco.setDireccion(180 - barco.getDireccion());
                        }
                        if (EjeY < 0 || EjeY > 700) {
                            barco.setDireccion(-barco.getDireccion());
                        }
                    }
                } else {
                    Random random = new Random();
                    int randomNumber = random.nextInt(10) + 1;
                    if (randomNumber <= 5) {
                        if (EjeX < 0 || EjeX > 960) {
                            barco.setDireccion(180 + barco.getDireccion());
                        }
                        if (EjeY < 0 || EjeY > 700) {
                            barco.setDireccion(180 + barco.getDireccion());
                        }
                    } else {
                        if (EjeX < 0 || EjeX > 960) {
                            barco.setDireccion(180 - barco.getDireccion());
                        }
                        if (EjeY < 0 || EjeY > 700) {
                            barco.setDireccion(-barco.getDireccion());
                        }
                    }

                }

            }

        }

    }

}
