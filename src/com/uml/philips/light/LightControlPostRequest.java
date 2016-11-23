/**
 * Created by Chang Liu(chang_liu@student.uml.edu) on 11/19/2016
 * This is for Post Request for Hue Lights
 * 
 */
package com.uml.philips.Light;

public class LightControlPostRequest {

	public static String USER_ID = "VeHdsMjs9ECEagi9uYGT8D2TdhRUKDsCeMcISGHB";
	public static String BASE_PATH = "http://192.168.1.100";
	public static String QUERY_LIGHT_STATE = "";

	
	
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
