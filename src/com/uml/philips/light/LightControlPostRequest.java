/**
 * Created by Chang Liu(chang_liu@student.uml.edu) on 11/19/2016
 * This is for Post Request for Hue Lights
 * 
 */
package com.uml.philips.Light;

import com.uml.changliu.Credential.Credential;

public class LightControlPostRequest {

	public static String USER_ID = Credential.LightBridgeSettings.getUserID();
	public static String BASE_PATH = Credential.LightBridgeSettings.getHost();
	
	
	public String getUserIdRequest() {
		return BASE_PATH + "/api";
	}
	
	public String getUserIdBody() {
		return "{" + "\"" +  "devicetype" + "\"" + ":" + "\"" + "my_hue_app#iphone peter" + "\"" + "}";
	}
	
	/***
	 *  set the user id, otherwise it's unauthorized
	 */
	public void setUserId(String userid) {
		USER_ID = userid;
	}
	
	
}
