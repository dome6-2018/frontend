package com.ensicaen.ecole.ludistreet.RA;

import com.ensicaen.ecole.ludistreet.model.Wall;

import gl.Color;


public class ARView {
    private Wall _model;
    private Color _color = new Color(0.0f, 0.0f, 0.0f, 0.8f);
    private WallSetup _setup;

    public ARView(Wall model){
        _model = model;
        _setup = new WallSetup(_color, model);
    }

    public void setColor(Color c) {
        _color = c;
    }

    public WallSetup getSetup() {
        return _setup;
    }

}
