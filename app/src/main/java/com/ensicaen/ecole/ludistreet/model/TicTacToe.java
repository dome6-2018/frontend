package com.ensicaen.ecole.ludistreet.model;

import gl.Color;

/**
 * Created by jorand on 18/01/2018.
 */

public class TicTacToe extends Wall {

    public TicTacToe() {
        super(11, 11);

        this.setLocked(true);
        for (int i = 0; i < 11; i++) {
            setColorPixel(3, i, new Color("black"));
            setColorPixel(7, i, new Color("black"));
            setColorPixel(i, 3, new Color("black"));
            setColorPixel(i, 7, new Color("black"));
        }
    }

}
