package com.ensicaen.ecole.ludistreet.model;

import gl.Color;

public class WallModel{

    private int _resX;
    private int _resY;
    private Color[][] _wall;

    public WallModel(int resX, int resY) {
        _resX = resX;
        _resY = resY;
        _wall = new Color[resX][];
        for (int i = 0; i < resX; i++) {
            _wall[i] = new Color[resY];
        }
    }

    public void setColorPixel(int x, int y, Color c){
        _wall[x][y] = c;
    }

    public Color getPixel(int x, int y){
        return _wall[x][y];
    }


    public int getResX(){
        return _resX;
    }

    public int getResY(){
        return _resY;
    }

}
