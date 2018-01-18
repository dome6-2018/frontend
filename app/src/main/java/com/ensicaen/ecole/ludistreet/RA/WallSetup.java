package com.ensicaen.ecole.ludistreet.RA;

import android.app.Activity;

import com.ensicaen.ecole.ludistreet.model.WallModel;

import java.util.ArrayList;

import actions.Action;
import actions.ActionBufferedCameraAR;
import actions.ActionMoveCameraBuffered;
import actions.ActionRotateCameraBuffered;
import actions.ActionRotateCameraBuffered3;
import actions.ActionRotateCameraBuffered4;
import actions.ActionRotateCameraBufferedDebug;
import actions.ActionRotateCameraBufferedDirect;
import actions.ActionRotateCameraUnbuffered;
import actions.ActionRotateCameraUnbuffered2;
import actions.ActionUseCameraAngles2;
import commands.Command;
import de.rwth.setups.SensorTestSetup;
import geo.GeoObj;
import gl.Color;
import gl.CustomGLSurfaceView;
import gl.GL1Renderer;
import gl.GLCamera;
import gl.GLFactory;
import gl.animations.AnimationRotate;
import gl.scenegraph.MeshComponent;
import gl.scenegraph.Shape;
import gui.GuiSetup;
import system.ErrorHandler;
import system.EventManager;
import system.Setup;
import util.Vec;
import worldData.SystemUpdater;
import worldData.World;

/**
 * Created by jorand on 18/01/2018.
 */

public class WallSetup extends Setup {
    private GLCamera camera;
    private World world;
    private Action rotActionB1;
    private Action rotActionB3;
    private Action rotActionB4;
    private Action rotActionDebug;
    private Action rotActionUnB;
    private Action rotActionUnB2;
    private Action rotActionB2;
    private WallModel _model;
    private Color _color;


    public WallSetup(Color col, WallModel mod) {
        _model = mod;
        _color = col;
    }


    @Override
    public GLCamera getCamera() {
        return camera;
    }

    @Override
    public void _a_initFieldsIfNecessary() {
        camera = new GLCamera();
        rotActionB1 = new ActionRotateCameraBuffered(camera);
        rotActionB2 = new ActionRotateCameraBufferedDirect(camera);
        rotActionB3 = new ActionRotateCameraBuffered3(camera);
        rotActionB4 = new ActionRotateCameraBuffered4(camera);
        new ActionRotateCameraBufferedDebug(camera);
        rotActionUnB = new ActionRotateCameraUnbuffered(camera);
        rotActionUnB2 = new ActionRotateCameraUnbuffered2(camera);
    }

    @Override
    public void _b_addWorldsToRenderer(GL1Renderer renderer,
                                       GLFactory objectFactory, GeoObj currentPosition) {

        world = new World(camera);

        MeshComponent compasrose = new Shape();

        int x = _model.getResX();
        int y = _model.getResY();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                final MeshComponent square = objectFactory.newSquare(_model.getPixel(i,j));
                final int finalI = i;
                final int finalJ = j;
                square.setOnClickCommand(new Command() {
                    @Override
                    public boolean execute() {
                        square.setColor(_color);
                        _model.setColorPixel(finalI, finalJ, _color);
                        return true;
                    }
                });
                square.setRotation(new Vec(0, 90, 0));
                square.setPosition(new Vec(50, 2 * j, 2*i));
                compasrose.addChild(square);
            }
        }

        currentPosition.setComp(compasrose);
        world.add(currentPosition);

        renderer.addRenderElement(world);
    }


    @Override
    public void _c_addActionsToEvents(EventManager eventManager,
                                      CustomGLSurfaceView arView, SystemUpdater updater) {
        //arView.addOnTouchMoveAction(new ActionBufferedCameraAR(camera));
        eventManager.addOnOrientationChangedAction(rotActionB1);
        eventManager.addOnTrackballAction(new ActionMoveCameraBuffered(camera,
                5, 25));

        eventManager
                .addOnOrientationChangedAction(new ActionUseCameraAngles2() {

                    @Override
                    public void onAnglesUpdated(float pitch, float roll,
                                                float compassAzimuth) {
                    /*
                     * the angles could be used in some way here..
                     */
                    }
                });

    }


    @Override
    public void _d_addElementsToUpdateThread(SystemUpdater worldUpdater) {
        worldUpdater.addObjectToUpdateCycle(world);
        worldUpdater.addObjectToUpdateCycle(rotActionB1);
        worldUpdater.addObjectToUpdateCycle(rotActionB3);
        worldUpdater.addObjectToUpdateCycle(rotActionB4);
        worldUpdater.addObjectToUpdateCycle(rotActionDebug);
        worldUpdater.addObjectToUpdateCycle(rotActionUnB);
        worldUpdater.addObjectToUpdateCycle(rotActionUnB2);
    }

    @Override
    public void _e2_addElementsToGuiSetup(GuiSetup guiSetup, Activity activity) {
    }

    private class myRotateAction extends Command {

        private Action myAction;

        public myRotateAction(Action a) {
            myAction = a;
        }

        @Override
        public boolean execute() {
            EventManager.getInstance().getOnOrientationChangedAction().clear();
            EventManager.getInstance().getOnOrientationChangedAction()
                    .add(myAction);
            return true;
        }

    }

}
