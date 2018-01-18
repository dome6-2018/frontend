package com.ensicaen.ecole.ludistreet.beacon;

import system.Setup;

/**
 * Created by jorand on 17/01/2018.
 */

public class ThreadBeacon extends Thread {
    private int x;      // distance par rapport a la largeur du mur
    private int y;      // distance par rapport a la hauteur du mur
    private int z;      // distance utilisateur / mur
    private Setup _setup;

    public ThreadBeacon(Setup setup) {
        _setup = setup;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                x++;
                _setup.getCamera().changeNewPosition(1,1,1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
