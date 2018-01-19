package com.ensicaen.ecole.ludistreet.ar;

import android.app.Activity;
import android.widget.Toast;

import com.ensicaen.ecole.ludistreet.models.Wall;
import com.ensicaen.ecole.ludistreet.rest.WallsRestClient;
import com.loopj.android.http.TextHttpResponseHandler;

import actions.Action;
import actions.ActionMoveCameraBuffered;
import actions.ActionRotateCameraBuffered;
import actions.ActionRotateCameraBuffered3;
import actions.ActionRotateCameraBuffered4;
import actions.ActionRotateCameraUnbuffered;
import actions.ActionRotateCameraUnbuffered2;
import actions.ActionUseCameraAngles2;
import commands.Command;
import cz.msebera.android.httpclient.Header;
import geo.GeoObj;
import gl.Color;
import gl.CustomGLSurfaceView;
import gl.GL1Renderer;
import gl.GLCamera;
import gl.GLFactory;
import gl.scenegraph.MeshComponent;
import gl.scenegraph.Shape;
import gui.GuiSetup;
import system.EventManager;
import system.Setup;
import util.Log;
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
    private Action rotActionUnB;
    private Action rotActionUnB2;
    private Wall wall;
    private Color color;

    public WallSetup(Color color, Wall wall) {
        this.wall = wall;
        this.color = color;
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

        MeshComponent compasrose = new Shape();

        int x = wall.getResX();
        int y = wall.getResY();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                final MeshComponent square = objectFactory.newSquare(wall.getPixel(i,j));
                final int finalI = i;
                final int finalJ = j;

                if (!wall.isLocked()) {
                    square.setOnClickCommand(new Command() {
                        @Override
                        public boolean execute() {
                            square.setColor(color);
                            wall.setColorPixel(finalI, finalJ, color);

                            WallsRestClient wallsRestClient = new WallsRestClient();
                            wallsRestClient.patchWallDrawing(wall.getUuid(), wall, new TextHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, String res) {
                                }

                                @Override
                                public void onFailure(int statusCode, Header[] headers, String res, Throwable throwable) {
                                }

                                @Override
                                public boolean getUseSynchronousMode() {
                                    return false;
                                }

                                @Override
                                public void setUseSynchronousMode(boolean useSynchronousMode) {
                                }
                            });

                            return true;
                        }
                    });
                }

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
        worldUpdater.addObjectToUpdateCycle(rotActionUnB);
        worldUpdater.addObjectToUpdateCycle(rotActionUnB2);
    }

    @Override
    public void _e2_addElementsToGuiSetup(GuiSetup guiSetup, Activity activity) {
    }

    public Wall getWall() {
        return wall;
    }

}
