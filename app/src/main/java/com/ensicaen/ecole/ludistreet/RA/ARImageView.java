package com.ensicaen.ecole.ludistreet.RA;

import android.content.Context;

import com.ensicaen.ecole.ludistreet.model.WallModel;

import gl.Color;

/**
 * Created by jorand on 18/01/2018.
 */

public class ARImageView {
    private ARImageSetup arImageSetup;

    public ARImageView(Context ctx, int id){
        arImageSetup = new ARImageSetup(ctx, id);
    }

    public ARImageSetup getSetup() {
        return arImageSetup;
    }
}
