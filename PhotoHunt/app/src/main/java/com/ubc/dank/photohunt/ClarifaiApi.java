package com.ubc.dank.photohunt;

import android.graphics.Bitmap;
import android.util.Log;

import com.clarifai.api.ClarifaiClient;
import com.clarifai.api.RecognitionRequest;
import com.clarifai.api.RecognitionResult;
import com.clarifai.api.Tag;
import com.clarifai.api.exception.ClarifaiException;

import java.io.ByteArrayOutputStream;

/**
 * Created by Zeyad on 2/27/2016.
 */


public class ClarifaiApi {

   static private final ClarifaiClient api = new ClarifaiClient(Credentials.CLIENT_ID, Credentials.CLIENT_SECRET);

    public static RecognitionResult recognizeBitmap(Bitmap bitmap)
    {
        try
        {
            //optional
            Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 1000, 1000 * bitmap.getHeight() / bitmap.getWidth(), true);
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();

            //Converting to JPEG
            scaled.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            byte[] jpeg = outStream.toByteArray();

            //send JPEG to clarifai and return the result
            return api.recognize(new RecognitionRequest(jpeg)).get(0);
        }
        catch (ClarifaiException e) {
            return null;
        }
    }

    public static Boolean checkTag(RecognitionResult result , String tag) {
        Log.i("GOAL TAG", tag);
        if (result != null) {
            if (result.getStatusCode() == RecognitionResult.StatusCode.OK) {
                // Display the list of tags in the UI.
                for (Tag resultTag : result.getTags()) {
                    Log.i("RESULTING TAG", resultTag.getName());
                    if (tag.contains(resultTag.getName()))
                        return true;
                }
                return false ;
            }
        }
        return true;
    }
}