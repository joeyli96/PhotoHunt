package com.ubc.dank.photohunt;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.clarifai.api.RecognitionResult;

/**
 * Created by Zeyad on 2/27/2016.
 */
public class ClarifaiAsyncTask extends AsyncTask<Void, Void, Boolean > {

    public AsyncResponse delagate = null;
    private Bitmap bitmap;
    private String tag;

    ClarifaiAsyncTask(Bitmap bitmap, String tag)
    {
        this.bitmap = bitmap;
        this.tag = tag;
    }

    public interface AsyncResponse
    {
        void processFinished(Boolean answer);
    }

    @Override protected  Boolean doInBackground(Void... params) {
        RecognitionResult result = ClarifaiApi.recognizeBitmap(bitmap);
        return ClarifaiApi.checkTag(result, tag);

    }
    @Override protected void onPostExecute(Boolean result) {
        delagate.processFinished(result);
    }
}
