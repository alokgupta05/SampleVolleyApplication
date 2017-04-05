package com.example.samplevolleyapplication.application.network;

/**
 * Created by Megha on 06-03-2016.
 */
public class TEException {
    public TEException(int statusCode, String jsonString, Object tag) {
    }

    public TEException(int serverNotResponding, TEException exception) {

    }

    public static class TEExceptionCode {
        public static int ServerNotResponding;
    }
}
