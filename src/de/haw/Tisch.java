package de.haw;

import java.util.ArrayList;

public class Tisch {
    /* DEMO MODE */
    private final int FLUSS = 500;

    ArrayList<Raucher> raucher = new ArrayList<>();
    ArrayList<Agent> agenten = new ArrayList<>();
    ArrayList<Zutat> aktuelleZutaten = new ArrayList<>();

    /* false: Agenten sind dran, true: Raucher sind dran */
    boolean runde = false;

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

    public void starteRauchen() {

    }

    public synchronized void begutachteTisch() {

        System.out.println(String.format(SmokerUtil.ANSI_BLUE + "\n%s guckt sich den Tisch an..." + SmokerUtil.ANSI_RESET, Thread.currentThread()));

        if (Thread.currentThread() instanceof Agent) {
            if (getAktuelleZutaten().isEmpty()) {
                legeZutatenHin((Agent) Thread.currentThread());

                /* DEMO MODE */
                try {
                    wait(FLUSS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } else if (Thread.currentThread() instanceof Raucher) {
            if (!getAktuelleZutaten().isEmpty() && ((Raucher) Thread.currentThread()).kannRauchen(getAktuelleZutaten())) {
                leereTisch((Raucher) Thread.currentThread());

                /* DEMO MODE */
                try {
                    wait(FLUSS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public synchronized ArrayList<Zutat> getAktuelleZutaten() {
        return aktuelleZutaten;
    }

    public synchronized void legeZutatenHin(Agent agent) {
        ArrayList<Zutat> zutaten = SmokerUtil.getZutatenlisteGemischt();

        System.out.println(String.format("%s legt %s und %s auf den Tisch.", agent, zutaten.get(0), zutaten.get(1)));

        aktuelleZutaten.add(zutaten.get(0));
        aktuelleZutaten.add(zutaten.get(1));
    }

    public synchronized void leereTisch(Raucher raucher) {
        ArrayList<Zutat> zutaten = getAktuelleZutaten();

        System.out.println(String.format("%s nimmt %s und %s vom Tisch.", raucher, zutaten.get(0), zutaten.get(1)));
        System.out.println(String.format(SmokerUtil.ANSI_RED + "%s raucht..." + SmokerUtil.ANSI_RESET, raucher));

        aktuelleZutaten.clear();
    }
}
