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
        initWall();
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

    private void initWall() {
        for (int i = 0; i < _resX; i++) {
            for (int j = 0 ; j < _resY; j++) {
                _wall[i][j] = new Color(255, 255, 255, 200);
            }
        }
    }

    private void loadWall() {

    }
}
