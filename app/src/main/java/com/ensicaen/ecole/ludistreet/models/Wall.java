package com.ensicaen.ecole.ludistreet.models;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import gl.Color;

public class Wall {

    private String uuid;
    private String name;

    private int resX;
    private int resY;
    private String drawing;
    private Color[][] drawingArray;
    private double latitude;
    private double longitude;
    private List<Beacon> beacons;
    private boolean locked;

    public Wall(int resX, int resY) {
        this.locked = false;
        this.resX = resX;
        this.resY = resY;

        initBlankDrawing();
    }

    public void initBlankDrawing() {
        drawingArray = new Color[this.resX][];
        for (int i = 0; i < this.resX; i++) {
            drawingArray[i] = new Color[this.resY];
        }

        for (int i = 0; i < this.resX; i++) {
            for (int j = 0; j < this.resY; j++) {
                drawingArray[i][j] = new Color(1.0f, 1.0f, 1.0f, 0.9f);
            }
        }
    }

    public void loadDrawingFromString() throws JSONException {
        Gson gson = new Gson();
        Color[][] dataMap = gson.fromJson(drawing, Color[][].class);
        int x = 0;
        int y;
        for (Color[] i : dataMap) {
            y = 0;

            for (Color j : i) {
                drawingArray[x][y] = j;
                y++;
            }
            x++;
        }
    }

    public void updateDrawingString() throws JSONException {
        Gson gson = new Gson();
        drawing = gson.toJson(drawingArray);
    }

    public void setColorPixel(int x, int y, Color c){
        drawingArray[x][y] = c;
    }

    public Color getPixel(int x, int y){
        return drawingArray[x][y];
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResX() {
        return resX;
    }

    public void setResX(int resX) {
        this.resX = resX;
    }

    public int getResY() {
        return resY;
    }

    public void setResY(int resY) {
        this.resY = resY;
    }

    public Color[][] getDrawingArray() {
        return drawingArray;
    }

    public void setDrawingArray(Color[][] drawingArray) {
        this.drawingArray = drawingArray;
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

    public List<Beacon> getBeacons() {
        return beacons;
    }

    public void setBeacons(List<Beacon> beacons) {
        this.beacons = beacons;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getDrawing() {
        return drawing;
    }

    public void setDrawing(String drawing) {
        this.drawing = drawing;
    }
}
