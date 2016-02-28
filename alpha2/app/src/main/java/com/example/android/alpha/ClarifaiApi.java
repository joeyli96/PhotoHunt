package com.example.android.alpha;

import com.clarifai.api.ClarifaiClient;
import com.clarifai.api.RecognitionRequest;
import com.clarifai.api.RecognitionResult;
import com.clarifai.api.Tag;
import com.clarifai.api.exception.ClarifaiException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayInputStream;
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
            Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 320, 320 * bitmap.getHeight() / bitmap.getWidth(), true);
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

        if (result != null) {
            if (result.getStatusCode() == RecognitionResult.StatusCode.OK) {
                // Display the list of tags in the UI.
                StringBuilder b = new StringBuilder();
                for (Tag resultTag : result.getTags()) {
                    if (resultTag.getName().equals(tag))
                        return true;
                }
                return false ;
            }
        }
        return true;
    }
}