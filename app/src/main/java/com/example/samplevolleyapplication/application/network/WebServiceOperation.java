package com.example.samplevolleyapplication.application.network;

import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.samplevolleyapplication.application.SampleVolleyApplication;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public abstract class WebServiceOperation<T> implements Response.Listener<String>,Response.ErrorListener{
	
	private static final String TAG = "VolleyAddToRequest";
	protected GsonRequest mWebRequest;
	

	
	 public WebServiceOperation(int method, String url,Class<T> clazz ,Map<String, String> headers,String body) {
		   if(headers!=null){ headers.putAll(getHeaders());}else{getHeaders();}
		   mWebRequest = new GsonRequest(method, AppConfig.getBaseUrl() + url,clazz, headers,body!=null ? body.getBytes():null,this,this);
	 }
	
	 public WebServiceOperation(String url,Class<T> clazz, Map<String, String> headers,String body){
	       this(Request.Method.GET,url,clazz,headers,body);
	 }

     public WebServiceOperation(int method,String url,String body) {
         this(method,url,null,null,body);
     }
     
     public WebServiceOperation(int method,String url,Map<String, String> headers){
         this(method,url,null,headers,null);
     }

     public WebServiceOperation(String url,Class<T> clazz) {
         this(Request.Method.GET,url,clazz,null,null);
     }
     public WebServiceOperation(String url,Class<T> clazz,Map<String, String> headers){
         this(Request.Method.GET,url,clazz,headers,null);
     }

     public Map<String, String> getHeaders(){
    	 Map<String, String> headers = new HashMap<String, String>();
    	 headers.put("Content-Type","application/json");
 		 headers.put("Accept", "application/json");
 	     return headers;	
     }


	   public void addToRequestQueue(String tag) {
		   if(NetworkUtil.isOnline()){
		       // set the default tag if tag is empty
			   mWebRequest.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
	
		       VolleyLog.d("Adding request to queue: %s", mWebRequest.getUrl());
	
		       SampleVolleyApplication.getRequestQueue().add(mWebRequest);
		   }else{
			   //onErrorResult(new TEException(TEException.TEExceptionCode.NoInternetConnexion));
		   }
	   }


	   public void addToRequestQueue() {
	       // set the default tag if tag is empty
		   mWebRequest.setTag(TAG);

		   SampleVolleyApplication.getRequestQueue().add(mWebRequest);
	   }

	   /**
	    * Cancels all pending requests by the specified TAG, it is important
	    * to specify a TAG so that the pending/ongoing requests can be cancelled.
	    * 
	    * @param tag
	    */
	   public void cancelPendingRequests(Object tag) {
	       if (SampleVolleyApplication.getRequestQueue() != null) {
			   SampleVolleyApplication.getRequestQueue().cancelAll(tag);
	       }
	   }

	@Override
	public void onResponse(String response) {
		onServerResult(response);
	}

	@Override
	public void onErrorResponse(VolleyError error) {
		TEException exception = null;
		if(error.networkResponse!=null){
			String jsonString = null;
			try {
				jsonString = new String(error.networkResponse.data, HttpHeaderParser.parseCharset(error.networkResponse.headers));
				exception = new TEException(error.networkResponse.statusCode, jsonString, mWebRequest.getTag())  ;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		if(exception==null){
			exception = new TEException(TEException.TEExceptionCode.ServerNotResponding,
                    exception);
		}
		onErrorResult(exception);
	}
	
	public abstract void onServerResult(String response);
	public abstract void onErrorResult(TEException exception);


}
