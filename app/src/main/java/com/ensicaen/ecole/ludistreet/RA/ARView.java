package com.ensicaen.ecole.ludistreet.RA;

import com.ensicaen.ecole.ludistreet.model.WallModel;

import java.util.ArrayList;

import commands.Command;
import gl.Color;
import gl.GL1Renderer;
import gl.GLFactory;
import gl.scenegraph.Shape;
import system.DefaultARSetup;
import util.Vec;
import worldData.World;


public class ARView {
    private WallModel _model;
    private Color _color = new Color(0.0f, 0.0f, 0.0f, 0.8f);
    private WallSetup _setup;

    public ARView(WallModel model){
        _model = model;
        _setup = new WallSetup(_color, new Vec(0,2,-50), model);
    }

    public void setColor(Color c) {
        _color = c;
    }

    public WallSetup getSetup() {
        return _setup;
    }

}
