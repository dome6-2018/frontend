package com.ensicaen.ecole.ludistreet.model;

import java.util.Date;
import java.util.List;

import android.util.Log;

import com.google.gson.Gson;

import gl.Color;

public class WallModel{

    private String code;
    private String name;

    private int _resX;
    private int _resY;
    private Color[][] _wall;

    private List<BeaconModel> beacons;

    private Date createdAt;
    private Date updatedAt;

    public WallModel(String code, String name, int _resX, int _resY, Color[][] _wall, List<BeaconModel> beacons, Date createdAt, Date updatedAt) {
        this.code = code;
        this.name = name;
        this._resX = _resX;
        this._resY = _resY;
        this._wall = _wall;
        this.beacons = beacons;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public WallModel(int resX, int resY) {
        _resX = resX;
        _resY = resY;

        _wall = new Color[_resX][];
        for (int i = 0; i < _resX; i++) {
            _wall[i] = new Color[_resY];
        }

        for (int i = 0; i < _resX; i++) {
            for (int j = 0 ; j < _resY; j++) {
                _wall[i][j] = new Color(1.0f, 1.0f, 1.0f, 0.9f);
            }
        }
    }

    public WallModel(int resX, int resY, String json) {
        _resX = resX;
        _resY = resY;

        _wall = new Color[_resX][];
        for (int i = 0; i < _resX; i++) {
            _wall[i] = new Color[_resY];
        }

        Gson gson = new Gson();
        Color[][] dataMap = gson.fromJson(json, Color[][].class);
        int x = 0;
        int y;
        for (Color[] i : dataMap) {
            y = 0;
            for (Color j : i) {
                _wall[x][y] = j;
                y++;
            }
            x++;
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

    public String getJson() {
        Gson gson = new Gson();
        String json = gson.toJson(_wall);
        return json;
    }

    private void loadWall() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color[][] get_wall() {
        return _wall;
    }

    public void set_wall(Color[][] _wall) {
        this._wall = _wall;
    }

    public List<BeaconModel> getBeacons() {
        return beacons;
    }

    public void setBeacons(List<BeaconModel> beacons) {
        this.beacons = beacons;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
