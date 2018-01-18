package com.ensicaen.ecole.ludistreet.beacon;

import java.util.Set;

import system.Setup;

/**
 * Created by jorand on 17/01/2018.
 */

public class ThreadBeacon extends Thread {
    private int x;      // distance par rapport a la largeur du mur
    private int y;      // distance par rapport a la hauteur du mur
    private int z;      // distance utilisateur / mur
    private Setup _setup = null;
    private final static double threshold = 0.5;

    private double distanceJ = 0, distanceR = 0, distanceB = 0, lastdistanceJ, lastdistanceB, lastdistanceR;

    public ThreadBeacon() {
    }

    public void run() {
        double deltaJ, deltaB, deltaR;
        while (true) {
            try {
                Thread.sleep(1000);
                deltaB = distanceB - lastdistanceB;
                deltaJ = distanceJ - lastdistanceJ;
                deltaR = distanceR - lastdistanceR;

                if (_setup != null) {
                    if (deltaJ < threshold && deltaR > threshold) {
                        _setup.getCamera().changeNewPosition(1, 0, 0);
                    }
                    if (deltaJ > threshold && deltaR > threshold) {
                        _setup.getCamera().changeNewPosition(-1, 0, 0);
                    }

                    if (deltaB < threshold) {
                        _setup.getCamera().changeNewPosition(0, 0, 1);
                    } else if (deltaB > threshold) {
                        _setup.getCamera().changeNewPosition(0, 0, -1);
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public void setSetup(Setup set) {
        _setup = set;
    }

    public void setDistanceJ(double dist) {
        lastdistanceJ = distanceJ;
        distanceJ = dist;
    }
    public void setDistanceR(double dist) {
        lastdistanceR = distanceR;
        distanceR = dist;
    }
    public void setDistanceB(double dist) {
        lastdistanceB = distanceB;
        distanceB = dist;
    }
}
