package de.haw;

public class Main {

    public static void main(String[] args) {
        /* LAUFZEIT */
        final int DAUER = 10000;

	    int anzahlRaucher = 3;
	    int anzahlAgenten = 2;

        Tisch tisch = new Tisch(anzahlRaucher,anzahlAgenten);

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

        System.out.println(String.format(SmokerUtil.ANSI_RED + "\nDie Raucherpause ist vorbei." + SmokerUtil.ANSI_RESET));
        /* Threads beenden */
        for (Agent a : tisch.agenten) {
            a.interrupt();
        }
        for (Raucher r : tisch.raucher) {
            r.interrupt();
        }
        for (Agent a : tisch.agenten) {
            try {
                a.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        for (Raucher r : tisch.raucher) {
            try {
                r.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(String.format(SmokerUtil.ANSI_RED + "\nDer Tisch ist leer, bis zum nächsten Mal!" + SmokerUtil.ANSI_RESET));
    }
}
