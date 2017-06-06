package com.hzg.txq;

import android.util.Log;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class FastJsonRequest extends Request<JSONObject> {


	private Listener<JSONObject> mListener;


	protected Map<String, String> sendHeader = new HashMap<String, String>(1);

	protected static final String PROTOCOL_CHARSET = "utf-8";

	// private Listener<JSONObject> mListener;
	public interface OnHeadHandlerListener {
		void onHeadHandler(Map<String, String> map, String encode);
	}

	private OnHeadHandlerListener mOnheadHandlerListener;

	public OnHeadHandlerListener getOnheadHandlerListener() {
		return mOnheadHandlerListener;
	}

	public void setOnheadHandlerListener(OnHeadHandlerListener mOnheadHandlerListener) {
		this.mOnheadHandlerListener = mOnheadHandlerListener;
	}

	public FastJsonRequest(int method, String url, Listener<JSONObject> listener, ErrorListener errorListener) {
		super(method, url, errorListener);
		this.mListener = listener;
		Log.d("volley", " url=" + url);
	}

	public FastJsonRequest(int method, String url, Listener<JSONObject> listener, ErrorListener errorListener,
			OnHeadHandlerListener headHandler) {
		super(method, url, errorListener);
		this.mListener = listener;
		Log.d("volley", "url=" + url);
		this.mOnheadHandlerListener = headHandler;
	}

	@Override
	protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
		try {
			String charSet = HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET);
			String jsonString = new String(response.data, charSet);
			if (mOnheadHandlerListener != null) {
				mOnheadHandlerListener.onHeadHandler(response.headers, charSet);
			}
			System.out.println(response.statusCode);

			return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JSONException e) {
			e.printStackTrace();
			return  Response.error(new ParseError(e));
		}
	}



	@Override
	protected void deliverResponse(JSONObject response) {
		mListener.onResponse(response);
	}


	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		return sendHeader;
	}

	protected void setSendCookie(String cookie) {
		sendHeader.put("Cookie", cookie);
	}

	@Override
	protected VolleyError parseNetworkError(VolleyError volleyError) {
		return super.parseNetworkError(volleyError);

	}
}
