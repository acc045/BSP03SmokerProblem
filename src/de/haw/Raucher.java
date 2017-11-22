package de.haw;

public class Raucher extends Thread{

    private String name;
    private Tisch.Zutat zutat;

    public Raucher(String name, Tisch.Zutat zutat) {
        this.name = name;
        this.zutat = zutat;
    }

    @Override
    public void run() {
        super.run();
    }

    private void nimmZutaten() {

    }

    private void rauche() {

    }
}
