package de.haw;

import java.util.ArrayList;

public class Tisch {

    public enum Zutat{
        TABAK,
        PAPIER,
        STREICHHOLZ;
    }

    ArrayList<Raucher> raucher = new ArrayList<>();
    ArrayList<Agent> agenten = new ArrayList<>();
    ArrayList<Zutat> aktuelleZutaten = new ArrayList<>();

    public Tisch(int anzahlRaucher, int anzahlAgenten) {
        for (int i = 0; i < anzahlRaucher; i++) {
            raucher.add(new Raucher("Raucher" + i, Zutat.PAPIER));
        }
        for (int i = 0; i < anzahlAgenten; i++) {
            agenten.add(new Agent("Agent" + i));
        }
    }

    public void starteRauchen() {

    }

    public ArrayList<Zutat> getAktuelleZutaten() {
        return aktuelleZutaten;
    }
}
