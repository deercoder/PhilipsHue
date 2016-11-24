/**
 * Created by Chang Liu(chang_liu@student.uml.edu) on 11/19/2016
 * This is for Get Request for Hue Lights
 * 
 */
package com.uml.philips.Light;

import com.uml.changliu.Credential.Credential;

public class LightControlGetRequest {

	public static String USER_ID = Credential.LightBridgeSettings.getUserID();
	public static String BASE_PATH = Credential.LightBridgeSettings.getHost();
	
	public String getLightState() {
		return BASE_PATH + "/api/" + USER_ID + "lights";
	}
	
	public static String getFirstLightState() {
		return BASE_PATH + "/api/" + USER_ID + "/lights/1";
	}
	
	public static String getSecondLightState() {
		return BASE_PATH + "/api/" + USER_ID + "/lights/2";
	}
		
}
