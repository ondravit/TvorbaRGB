package org.example;

public class Barva {
    private String nazev;
    private int red;
    private int green;
    private int blue;

    public Barva(String nazev, int red, int green, int blue) {
        this.nazev = nazev;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public String getNazev() {
        return nazev;
    }


    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

}
