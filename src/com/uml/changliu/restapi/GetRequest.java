/**
 * Created by Chang Liu(chang_liu@student.uml.edu) on 11/19/2014
 * This is GET Request
 * 
 */
package com.uml.changliu.restapi;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetRequest {
  OkHttpClient mClient;

  public GetRequest() {
	  mClient = new OkHttpClient();
  }
  
  
  public String get(String url) throws IOException {
    Request request = new Request.Builder()
        .url(url)
        .build();

    try (Response response = mClient.newCall(request).execute()) {
      return response.body().string();
    }
  }
}