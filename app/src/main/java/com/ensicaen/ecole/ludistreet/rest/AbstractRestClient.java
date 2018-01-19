package com.ensicaen.ecole.ludistreet.rest;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Classe de base pour les classes RestClient
 */
public class AbstractRestClient {

    /**
     * Transforme un object en JSON
     *
     * @param obj objet à convertir
     * @return JSON en sortie représentant l'objet
     */
    protected StringEntity getEntity(Object obj) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        StringEntity entity = null;

        try {
            entity = new StringEntity(json);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return entity;
    }

}
