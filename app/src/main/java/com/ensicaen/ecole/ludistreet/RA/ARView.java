package com.ensicaen.ecole.ludistreet.RA;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.ensicaen.ecole.ludistreet.MainActivity;
import com.ensicaen.ecole.ludistreet.R;
import com.ensicaen.ecole.ludistreet.model.WallModel;

import java.util.ArrayList;

import commands.Command;
import gl.Color;
import gl.GL1Renderer;
import gl.GLFactory;
import gl.scenegraph.MeshComponent;
import gl.scenegraph.Shape;
import system.ArActivity;
import system.DefaultARSetup;
import util.Log;
import util.Vec;
import worldData.World;


public class ARView {
    private WallModel _model;
    private Color _color = new Color("black");
    private DefaultARSetup _setup;

    public ARView(WallModel model){
        _model = model;

        initSetup(new Vec(0,2,-50));
    }

    public void setColor(Color c) {
        _color = c;
    }


    public void initSetup(final Vec positionUser) {
         _setup = new DefaultARSetup() {
            @Override
            public void addObjectsTo(GL1Renderer renderer, World world, GLFactory objectFactory) {
                ArrayList<Shape> formes = new ArrayList();
                int x = _model.getResX();
                int y = _model.getResY();

                for (int i = 0; i < x; i++) {
                    for (int j = 0; j < y; j++) {
                        final Shape square = objectFactory.newSquare(_model.getPixel(i,j));
                        final int finalI = i;
                        final int finalJ = j;
                        square.setOnClickCommand(new Command() {
                            @Override
                            public boolean execute() {
                                square.setColor(_color);
                                _model.setColorPixel(finalI, finalJ, _color);
                                return true;
                            }
                        });
                        formes.add(square);
                    }
                }

                for(int i = 0; i < x; i++){
                    for (int j = 0; j < y; j++) {
                        formes.get(i*y + j).setRotation(new Vec(0, 90, 0));
                        formes.get(i*y + j).setPosition(new Vec(0, 2 * j, 2*i));
                        world.add(formes.get(i*y + j));
                    }
                }

                world.setMyScreenPosition(positionUser);
            }
        };
    }



    public DefaultARSetup getSetup() {
        return _setup;
    }

}
