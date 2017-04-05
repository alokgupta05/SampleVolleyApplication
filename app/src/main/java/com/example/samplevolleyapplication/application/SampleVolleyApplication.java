package com.example.samplevolleyapplication.application;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.OkHttpClient;

/**
 * Created by Megha on 04-04-2017.
 */

public class SampleVolleyApplication extends Application {

    private static Context context;
    private static RequestQueue mRequestQueue;
    public static Context getAppContext(){
            return  context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
    }

    /**
     * @return The Volley Request queue, the queue will be created if it is null
     */
    public static RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time

        if (mRequestQueue == null) {

            mRequestQueue = Volley.newRequestQueue(
                    context, new OkHttpStack(new OkHttpClient()));
        }

        return mRequestQueue;
    }
}
