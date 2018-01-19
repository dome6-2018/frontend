package com.ensicaen.ecole.ludistreet.models;

import java.util.Date;

/**
 * Created by Thibaud on 16/01/2018.
 */

public class Beacon {

    private String uuid;
    private Date addedAt;
    private Date updatedAt;
    private int minor;
    private int major;

    public Beacon(String UUID, Date addedAt, Date updatedAt) {
        this.uuid = UUID;
        this.addedAt = addedAt;
        this.updatedAt = updatedAt;
    }

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String UUID) {
        this.uuid = UUID;
    }

    public Date getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Date addedAt) {
        this.addedAt = addedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }
}
