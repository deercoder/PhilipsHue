package com.uml.philips.light;

public class LightControlGetRequest {

	public static String USER_ID = "VeHdsMjs9ECEagi9uYGT8D2TdhRUKDsCeMcISGHB";
	public static String BASE_PATH = "http://192.168.1.100";
	public static String QUERY_LIGHT_STATE = "";
	
	public String getLightState() {
		return BASE_PATH + "/api/" + USER_ID + "lights";
	}
	
	public String getFirstLightState() {
		return BASE_PATH + "/api/" + USER_ID + "/lights/1";

	}
	
	public String getSecondLightState() {
		return BASE_PATH + "/api/" + USER_ID + "/lights/2";
	}
		
}
