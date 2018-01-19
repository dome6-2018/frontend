package com.ensicaen.ecole.ludistreet.ar;

import java.util.ArrayList;
import java.util.Collections;

import system.Setup;

/**
 * Created by jorand on 17/01/2018.
 */
public class ThreadBeacon extends Thread {
    private int x;      // distance par rapport a la largeur du mur
    private int y;      // distance par rapport a la hauteur du mur
    private int z;      // distance utilisateur / mur
    private Setup setup = null;
    private final static double threshold = 0.5;


    // J droite / R gauche / B profondeur
    private double distanceJ = 0, distanceR = 0, distanceB = 0, lastdistanceJ, lastdistanceB, lastdistanceR;

    ArrayList<Double> lastDistances = new ArrayList<>();
    ArrayList<Double> distances = new ArrayList<>();    // index 0 : gauche du mur / 1 : droite / 2 : profondeur

    public ThreadBeacon() {
    }

    public void run() {
        double deltaDroite, deltaProfondeur, deltaGauche;
        while (true) {
            try {
                Thread.sleep(1000);
                deltaProfondeur = distances.get(2) - lastDistances.get(2);
                deltaDroite = distances.get(1) - lastDistances.get(1);
                deltaGauche = distances.get(0) - lastDistances.get(0);

                if (setup != null) {

                    if (Math.abs(deltaDroite) > threshold && Math.abs(deltaGauche) > threshold) {
                        if (deltaDroite < 0 && deltaGauche > 0) { // joueur va vers la droite
                            setup.getCamera().changeNewPosition(0, 0, 0);
                        } else if (deltaDroite > 0 && deltaGauche < 0) { // joueur va vers la gauche
                            setup.getCamera().changeNewPosition(0, 0, 0);
                        }
                    }
/*
                    if(Math.abs(deltaProfondeur) > threshold){
                        if(deltaProfondeur < 0){ // joueur va vers l'avant
                            setup.getCamera().changeNewPosition(0,0,-10);
                        }else{
                            setup.getCamera().changeNewPosition(0,0,10);
                        }
                    }
                    */
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void setSetup(Setup setup) {
        this.setup = setup;
    }

    public void setDistances(ArrayList<Double> dist) {
        Collections.copy(lastDistances, distances);
        Collections.copy(distances, dist);
    }
}
