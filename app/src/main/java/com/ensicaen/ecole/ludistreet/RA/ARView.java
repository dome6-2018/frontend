package com.ensicaen.ecole.ludistreet.RA;

import com.ensicaen.ecole.ludistreet.model.WallModel;

import gl.Color;
import util.Vec;


public class ARView {
    private WallModel _model;
    private Color _color = new Color(0.0f, 0.0f, 0.0f, 0.8f);
    private WallSetup _setup;

    public ARView(WallModel model){
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
