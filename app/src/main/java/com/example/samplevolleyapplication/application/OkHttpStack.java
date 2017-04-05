package com.example.samplevolleyapplication.application;

import com.android.volley.toolbox.HurlStack;
import com.squareup.okhttp.OkHttpClient;

/**
 * Created by Megha on 05-04-2017.
 */
public class OkHttpStack extends HurlStack {
    private final OkHttpClient client;

    public OkHttpStack() {
        this(new OkHttpClient());
    }

    public OkHttpStack(OkHttpClient client) {
        if (client == null) {
            throw new NullPointerException("Client must not be null.");
        }
        this.client = client;
    }


}
