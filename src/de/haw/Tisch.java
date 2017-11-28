package de.haw;

import java.util.ArrayList;

public class Tisch {

    ArrayList<Raucher> raucher = new ArrayList<>();
    ArrayList<Agent> agenten = new ArrayList<>();
    ArrayList<Zutat> aktuelleZutaten = new ArrayList<>();

    public Tisch(int anzahlRaucher, int anzahlAgenten) {
        ArrayList<Zutat> zutaten = SmokerUtil.getZutatenlisteGemischt();

        for (int i = 0; i < anzahlRaucher; i++) {

            if (zutaten.isEmpty()) {
                zutaten = SmokerUtil.getZutatenlisteGemischt();
            }

            raucher.add(new Raucher("Raucher" + i, zutaten.get(0), this));
            zutaten.remove(0);
        }
        for (int i = 0; i < anzahlAgenten; i++) {
            agenten.add(new Agent("Agent" + i, this));
        }
    }

    public synchronized ArrayList<Zutat> getAktuelleZutaten() {
        return aktuelleZutaten;
    }

    public synchronized void legeZutatenHin(Agent agent) {

        System.out.println(String.format(SmokerUtil.ANSI_BLUE + "\n%s guckt sich den Tisch an..." + SmokerUtil.ANSI_RESET, Thread.currentThread()));

        while (!getAktuelleZutaten().isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        ArrayList<Zutat> zutaten = SmokerUtil.getZutatenlisteGemischt();

        System.out.println(String.format("%s legt %s und %s auf den Tisch.", agent, zutaten.get(0), zutaten.get(1)));

        aktuelleZutaten.add(zutaten.get(0));
        aktuelleZutaten.add(zutaten.get(1));

        notifyAll();
    }

    public synchronized void leereTisch(Raucher raucher) {

        System.out.println(String.format(SmokerUtil.ANSI_BLUE + "\n%s guckt sich den Tisch an..." + SmokerUtil.ANSI_RESET, Thread.currentThread()));

        while (getAktuelleZutaten().isEmpty() || !((Raucher) Thread.currentThread()).kannRauchen(getAktuelleZutaten())) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        ArrayList<Zutat> zutaten = getAktuelleZutaten();

        System.out.println(String.format("%s nimmt %s und %s vom Tisch.", raucher, zutaten.get(0), zutaten.get(1)));
        System.out.println(String.format(SmokerUtil.ANSI_RED + "%s raucht..." + SmokerUtil.ANSI_RESET, raucher));

        aktuelleZutaten.clear();

        try {
            Thread.currentThread().sleep(SmokerUtil.getZeitRauchen());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(String.format(SmokerUtil.ANSI_RED + "%s ist fertig mit dem Rauchen." + SmokerUtil.ANSI_RESET, Thread.currentThread()));

        notifyAll();
    }
}
