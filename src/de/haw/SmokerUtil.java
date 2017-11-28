package de.haw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class  SmokerUtil {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";

    private static Random random = new Random();

    public static synchronized int getZeitRauchen() {
        return random.nextInt(500) * 2 + 1000;
    }

    public synchronized static ArrayList<Zutat> getZutatenlisteGemischt() {
        ArrayList<Zutat> zutaten = new ArrayList<>();
        zutaten.add(Zutat.PAPIER);
        zutaten.add(Zutat.STREICHHOLZ);
        zutaten.add(Zutat.TABAK);

        return mischen(zutaten);
    }

    public synchronized static ArrayList<Zutat> mischen(ArrayList<Zutat> zutaten) {
        Collections.shuffle(zutaten);
        return zutaten;
    }

}
