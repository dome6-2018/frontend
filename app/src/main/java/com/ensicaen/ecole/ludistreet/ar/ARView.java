package com.ensicaen.ecole.ludistreet.ar;

import com.ensicaen.ecole.ludistreet.models.Wall;

import gl.Color;

public class ARView {
    private Wall wall;
    private Color color = new Color(0.0f, 0.0f, 0.0f, 0.8f);
    private WallSetup setup;

    public ARView(Wall wall){
        this.wall = wall;
        setup = new WallSetup(color, wall);
    }

    public void setColor(Color c) {
        color = c;
    }

    public WallSetup getSetup() {
        return setup;
    }

}
