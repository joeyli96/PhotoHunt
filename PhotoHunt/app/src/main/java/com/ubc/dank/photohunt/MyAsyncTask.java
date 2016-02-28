package com.ubc.dank.photohunt;

/**
 * Created by Joey on 2/28/16.
 */
public class MyAsyncTask extends ClarifaiAsyncTask {
    public interface AsyncResponse {
       public void processFinish(Boolean output);
    }

    public AsyncResponse delegate = null;

    public MyAsyncTask(AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    public void onPostExecute(Boolean result) {
        delegate.processFinish(result);
    }
}
