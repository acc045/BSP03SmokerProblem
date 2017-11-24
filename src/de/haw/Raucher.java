package de.haw;

import java.util.ArrayList;

public class Raucher extends Thread{

    private String name;
    private Zutat zutat;
    private Tisch tisch;

    public Raucher(String name, Zutat zutat, Tisch tisch) {
        this.name = name;
        this.zutat = zutat;
        this.tisch = tisch;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            tisch.begutachteTisch();
        }

        System.out.println(String.format(SmokerUtil.ANSI_GREEN + "\n%s verlässt den Tisch. (interrupt)" + SmokerUtil.ANSI_RESET, this));
    }

    public boolean kannRauchen(ArrayList<Zutat> zutaten) {
        if (zutaten.isEmpty()) return false;

        return !zutaten.contains(this.zutat);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, zutat);
    }
}
