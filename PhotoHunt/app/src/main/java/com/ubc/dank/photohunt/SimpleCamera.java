package com.ubc.dank.photohunt;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by NWHacks on 2016-02-27.
 */
public class SimpleCamera extends SurfaceView implements SurfaceHolder.Callback2{
    private SurfaceHolder mHolder;
    private Camera mCamera;

    public SimpleCamera(Context context, Camera camera) {
        super(context);
        mCamera = camera;

        mHolder = getHolder();
        mHolder.addCallback(this);

        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceRedrawNeeded(SurfaceHolder holder) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d("CAMERA ERROR", "ERROR SETTING CAMERA PREVIEW");
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //Do nothing if no surface
        if(mHolder.getSurface()== null) {
            return;
        }

        try {
            mCamera.stopPreview();
        } catch (Exception e) {
            Log.d("CAMERA ERROR:", "ERROR SETTING CAMERA PREVIEW");
        }

        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        } catch (Exception e) {
            Log.d("CAMERA ERROR:", "ERROR STARTING CAMERA PREVIEW");
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
