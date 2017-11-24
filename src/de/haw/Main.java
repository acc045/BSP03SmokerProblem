package de.haw;

public class Main {

    public static void main(String[] args) {
        /* LAUFZEIT */
        final int DAUER = 3000;

	    int anzahlRaucher = 3;
	    int anzahlAgenten = 2;

        Tisch tisch = new Tisch(3,2);

        for (Agent a : tisch.agenten) {
            System.out.println(String.format("%s setzt sich an den Tisch.", a));
            a.start();
        }
        for (Raucher r : tisch.raucher) {
            System.out.println(String.format("%s setzt sich an den Tisch.", r));
            r.start();
        }

        try {
         /* Main-Thread Sekunden anhalten */
            Thread.sleep(DAUER);
        } catch (InterruptedException e) {
            // nichts
        }

        /* Threads beenden */
        for (Agent a : tisch.agenten) {
            a.interrupt();
        }
        for (Raucher r : tisch.raucher) {
            r.interrupt();
        }
    }
}
