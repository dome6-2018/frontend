package com.ensicaen.ecole.ludistreet.ar;

import android.content.Context;

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
