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


public class RAView {
    private WallModel _model;
    private DefaultARSetup _setup;

    public RAView(WallModel model){
        _model = model;

        _setup = new DefaultARSetup() {
            @Override
            public void addObjectsTo(GL1Renderer renderer, World world, GLFactory objectFactory) {
                ArrayList<Shape> formes = new ArrayList();

                for (int i = 0; i < 8 ; i++) {
                    final Shape square = objectFactory.newSquare(new Color("white"));
                    square.setOnClickCommand(new Command() {
                        @Override
                        public boolean execute() {
                            square.setColor(new Color("black"));
                            return true;
                        }
                    });

                    formes.add(square);
                }

                for(int i = 0; i < 4; i++){
                    formes.get(i).setRotation(new Vec(0,90,0));
                    formes.get(i).setPosition(new Vec(0,2*i,0));
                    world.add(formes.get(i));
                }

                for(int i = 4; i < 8; i++){
                    formes.get(i).setRotation(new Vec(0,90,0));
                    formes.get(i).setPosition(new Vec(0,2*(i-4),2));
                    world.add(formes.get(i));
                }

                world.setMyScreenPosition(new Vec(0,2,-15));
            }
        };
    }

}
