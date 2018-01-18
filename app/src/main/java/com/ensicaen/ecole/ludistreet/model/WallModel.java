package com.ensicaen.ecole.ludistreet.model;

import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import gl.Color;

public class WallModel{

    private String code;
    private String name;

    private int resX;
    private int resY;
    private Color[][] drawing;
    private double latitude;
    private double longitude;
    private List<BeaconModel> beacons;
    private Date createdAt;
    private Date updatedAt;


    public WallModel(int resX, int resY) {
        this.resX = resX;
        this.resY = resY;

        drawing = new Color[this.resX][];
        for (int i = 0; i < this.resX; i++) {
            drawing[i] = new Color[this.resY];
        }

        for (int i = 0; i < this.resX; i++) {
            for (int j = 0; j < this.resY; j++) {
                drawing[i][j] = new Color(1.0f, 1.0f, 1.0f, 0.9f);
            }
        }
    }


    public void setColorPixel(int x, int y, Color c){
        drawing[x][y] = c;
    }

    public Color getPixel(int x, int y){
        return drawing[x][y];
    }




    public int getResX(){
        return resX;
    }

    public int getResY(){
        return resY;
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

    public Color[][] getDrawing() {
        return drawing;
    }

    public void setDrawing(Color[][] drawing) {
        this.drawing = drawing;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
