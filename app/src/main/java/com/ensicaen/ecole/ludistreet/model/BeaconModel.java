package com.ensicaen.ecole.ludistreet.model;

import java.util.Date;

/**
 * Created by Thibaud on 16/01/2018.
 */

public class BeaconModel {

    private String UUID;
    private Date addedAt;
    private Date updatedAt;

    public BeaconModel(String UUID, Date addedAt, Date updatedAt) {
        this.UUID = UUID;
        this.addedAt = addedAt;
        this.updatedAt = updatedAt;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
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
}
