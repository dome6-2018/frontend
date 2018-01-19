package com.ensicaen.ecole.ludistreet.ar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import actions.Action;
import actions.ActionMoveCameraBuffered;
import actions.ActionRotateCameraBuffered;
import actions.ActionRotateCameraBuffered3;
import actions.ActionRotateCameraBuffered4;
import actions.ActionRotateCameraUnbuffered;
import actions.ActionRotateCameraUnbuffered2;
import geo.GeoObj;
import gl.CustomGLSurfaceView;
import gl.GL1Renderer;
import gl.GLCamera;
import gl.GLFactory;
import gl.scenegraph.MeshComponent;
import gui.GuiSetup;
import system.EventManager;
import system.Setup;
import util.Vec;
import worldData.SystemUpdater;
import worldData.World;

/**
 * Created by jorand on 18/01/2018.
 */
public class ARImageSetup extends Setup {
    private GLCamera camera;
    private World world;
    private Action rotActionB1;
    private Action rotActionB3;
    private Action rotActionB4;
    private Action rotActionUnB;
    private Action rotActionUnB2;
    private int id;
    private Context ctx;

    public ARImageSetup(Context ctx, int idResource) {
        id = idResource;
        this.ctx = ctx;
    }

    @Override
    public GLCamera getCamera() {
        return camera;
    }

    @Override
    public void _a_initFieldsIfNecessary() {
        camera = new GLCamera();
        rotActionB1 = new ActionRotateCameraBuffered(camera);
        rotActionB3 = new ActionRotateCameraBuffered3(camera);
        rotActionB4 = new ActionRotateCameraBuffered4(camera);
        rotActionUnB = new ActionRotateCameraUnbuffered(camera);
        rotActionUnB2 = new ActionRotateCameraUnbuffered2(camera);
    }

    @Override
    public void _b_addWorldsToRenderer(GL1Renderer renderer,
                                       GLFactory objectFactory, GeoObj currentPosition) {

        world = new World(camera);

        Bitmap b = BitmapFactory.decodeResource(ctx.getResources(), id);
        MeshComponent s = objectFactory.newTexturedSquare("expo", b);
        s.setScale(new Vec(4,4,4));
        s.setPosition(new Vec(30,0, 0));
        s.setRotation(new Vec(0,0,90));
        world.add(s);

        renderer.addRenderElement(world);
    }


    @Override
    public void _c_addActionsToEvents(EventManager eventManager,
                                      CustomGLSurfaceView arView, SystemUpdater updater) {
        //arView.addOnTouchMoveAction(new ActionBufferedCameraAR(camera));
        eventManager.addOnOrientationChangedAction(rotActionB1);
        eventManager.addOnTrackballAction(new ActionMoveCameraBuffered(camera,
                5, 25));
    }


    @Override
    public void _d_addElementsToUpdateThread(SystemUpdater worldUpdater) {
        worldUpdater.addObjectToUpdateCycle(world);
        worldUpdater.addObjectToUpdateCycle(rotActionB1);
        worldUpdater.addObjectToUpdateCycle(rotActionB3);
        worldUpdater.addObjectToUpdateCycle(rotActionB4);
        worldUpdater.addObjectToUpdateCycle(rotActionUnB);
        worldUpdater.addObjectToUpdateCycle(rotActionUnB2);
    }

    @Override
    public void _e2_addElementsToGuiSetup(GuiSetup guiSetup, Activity activity) {
    }

}
