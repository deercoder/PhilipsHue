/**
 * Created by Chang Liu(chang_liu@student.uml.edu) on 11/19/2016
 * This is for Put Request for Hue Lights
 * 
 */
package com.uml.philips.Light;

public class LightControlPutRequest {
	
	public static String USER_ID = "VeHdsMjs9ECEagi9uYGT8D2TdhRUKDsCeMcISGHB";
	public static String BASE_PATH = "http://192.168.1.100";
	
	///////////////////////////////////////////////// HEAD/REQUEST URL ///////////////////////////////////
	/*
	 * set the brightness for first light( PUT url and BODY part )
	 */
	public String getFirstLightState() {
		return BASE_PATH + "/api/" + USER_ID + "/lights/1/state";
	}
	
	/*
	 *  set the brightness for first light( PUT url and BODY part )
	 */
	public String getSecondLightState() {
		return BASE_PATH + "/api/" + USER_ID + "/lights/2/state";
	}
	
	/////////////////////////////////////////////// BODY PART for DIFFERENT REQUEST ///////////////////////

	/*
	 * Set the light brightness 
	 * Example(First Light): http://<bridge ip address>/api/1028d66426293e821ecfd9ef1a0731df/lights/1/state
	 * Set brightness Body Content: {"bri":42}
	 */
	public String getLightBrightnessBody(int brightness) {
		return 	"{" + "\"" + "bri" + "\"" + ":" + brightness + "}";
	}
		
	/*
	 * Turn the light off
	 * Example(First Light): http://<bridge ip address>/api/1028d66426293e821ecfd9ef1a0731df/lights/1/state
	 * Turn off Body Content: {"on":false}
	 */
	public String getLightTurnOffBody() {
		return "{" + "\"" + "on" + "\"" + ": false" + "}";
	}
	
	/*
	 * Set the light color with saturation(0~254), brightness(0~254) and Hue(0~65535)
	 * Example(First Light): http://<bridge ip address>/api/1028d66426293e821ecfd9ef1a0731df/lights/1/state
	 * {"on":true, "sat":254, "bri":254,"hue":10000}
	 */
	public String getLightColorBody(int sat, int bri, int hue) {
		return "{" + "\"" + "on" + "\"" + ": true"  + ","
				+ "\"" + "sat" + "\"" + ":" + sat + ","
				+ "\"" + "hue" + "\"" + ":" + hue + "}";
	}

	/////////////////////////////////////////////// ENDS /////////////////////////////////////
	
	
	
	/***
	 *  set the user id, otherwise it's unauthorized
	 */
	public void setUserId(String userid) {
		USER_ID = userid;
	}
}
