/**
 * Created by Chang Liu(chang_liu@student.uml.edu) on 11/19/2014
 * This is PUT request.
 * 
 */
package com.uml.changliu.restapi;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PutRequest {

  public static final MediaType JSON
      = MediaType.parse("application/json; charset=utf-8");

  OkHttpClient mClient;

  public String put(String url, String json) throws IOException {
    RequestBody body = RequestBody.create(JSON, json);
    Request request = new Request.Builder()
        .url(url)
        .put(body)
        .build();
    try (Response response = mClient.newCall(request).execute()) {
      return response.body().string();
    }
  }

  public PutRequest() {
	  mClient  = new OkHttpClient();
  }

}