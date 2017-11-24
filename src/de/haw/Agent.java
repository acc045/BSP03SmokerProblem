package de.haw;

import java.util.ArrayList;

public class Agent extends Thread{

    private String name;
    private Tisch tisch;

    public Agent(String name, Tisch tisch) {
        this.name = name;
        this.tisch = tisch;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            tisch.begutachteTisch();
        }

        System.out.println(String.format(SmokerUtil.ANSI_GREEN + "\n%s verlässt den Tisch. (interrupt)" + SmokerUtil.ANSI_RESET, this));
    }

    @Override
    public String toString() {
        return name;
    }
}
