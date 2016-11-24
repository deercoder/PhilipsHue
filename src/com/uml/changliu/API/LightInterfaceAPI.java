/**
 * Created by Chang Liu(chang_liu@student.uml.edu) on 11/23/2016
 * This is the API for other modules to use
 * 
 */
package com.uml.changliu.API;

import java.awt.Color;
import java.io.IOException;

import org.json.JSONObject;

import com.uml.changliu.Credential.Credential;
import com.uml.changliu.Parser.LightStateParser;
import com.uml.changliu.RestApi.GetRequest;
import com.uml.changliu.RestApi.PostRequest;
import com.uml.changliu.RestApi.PutRequest;
import com.uml.changliu.Utility.RGB2HSL;
import com.uml.philips.Light.LightControlGetRequest;
import com.uml.philips.Light.LightControlPostRequest;
import com.uml.philips.Light.LightControlPutRequest;

public class LightInterfaceAPI {

	private GetRequest mGetRequest;
	private PostRequest mPostRequest;
	private PutRequest mPutRequest;
	
	private Credential.LightBridgeSettings mCredential;
	
	
	public LightInterfaceAPI() {
		mGetRequest = new GetRequest();
		mPostRequest = new PostRequest();
		mPutRequest = new PutRequest();
		mCredential = new Credential.LightBridgeSettings();
	}
	
	public LightInterfaceAPI(String host, String uid) {
		mGetRequest = new GetRequest();
		mPostRequest = new PostRequest();
		mPutRequest = new PutRequest();
		mCredential = new Credential.LightBridgeSettings(host, uid);
	}
	
	/**
	 * Test the Get request, it's used to query the FIRST light status
	 */
	public void inqueryFirstLightStatus() {
		String response;
		try {
			response = mGetRequest.get(LightControlGetRequest.getFirstLightState());
			System.out.println("Response of inquirying first light state:\n" + response);
			JSONObject jb = new JSONObject(response);
			LightStateParser p = new LightStateParser(jb);
		    System.out.println("Brightness for first light = " + p.getBrightnessValue());
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	/**
	 * Test the Get request, it's used to query the SECOND light status
	 */
	public void inquerySecondLightStatus() {
		String response;
		try {
			response = mGetRequest.get(LightControlGetRequest.getSecondLightState());
			System.out.println("Response of inquirying second light state:\n" + response);
			JSONObject jb = new JSONObject(response);
			LightStateParser p = new LightStateParser(jb);
		    System.out.println("Brightness for second light = " + p.getBrightnessValue());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This function is used for reqeust a valid user ID(token), used as authorized user to operate the light
	 * 
	 * Useful for ALL lights, as this user id is for the bridge and will be concatenated with with lights id(1, 2, ...)
	 * 
	 * MUST be used combining the action of pressing the button on the bridge, otherwise it will not succeed
	 */
	public void createLightUserId() {
	    LightControlPostRequest control = new LightControlPostRequest();
	    String startUrl =  control.getUserIdRequest();
	    String body = control.getUserIdBody();
	    
	    System.out.println(startUrl);
	    System.out.println(body);
	    String response;
	    
		try {
			response = mPostRequest.post(startUrl, body);
		    System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param brightValue(0~255), indicates the lightness.
	 */
	public void setFirstLightness(int brightValue) {
		LightControlPutRequest control = new LightControlPutRequest();
		String startUrl = control.getFirstLightState();
		String bodyParameter = control.getLightBrightnessBody(brightValue);
		String response;

		try {
			response = mPutRequest.put(startUrl, bodyParameter);
			System.out.println("Response of set first light: " + response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param brightValue(0~255), indicates the lightness.
	 */
	public void setSecondLightness(int brightValue) {
		LightControlPutRequest control = new LightControlPutRequest();
		String startUrl = control.getSecondLightState();
		String bodyParameter = control.getLightBrightnessBody(brightValue);
		String response;

		try {
			response = mPutRequest.put(startUrl, bodyParameter);
			System.out.println("Response of set second light: " + response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/***
	 * This function is used for turning off the first light
	 */
	public  void setFirstLightOff() {
	    LightControlPutRequest control = new LightControlPutRequest();
	    String startUrl =  control.getFirstLightState();
	    String body = control.getLightTurnOffBody();
	    
	    String response;
	    
		try {
			response = mPutRequest.put(startUrl, body);
		    System.out.println("Response of turning first light off: " + response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	/***
	 * This function is used for turning off the first light
	 */
	public void setSecondLightOff() {
	    LightControlPutRequest control = new LightControlPutRequest();
	    String startUrl =  control.getSecondLightState();
	    String body = control.getLightTurnOffBody();

	    String response;
	    
		try {
			response = mPutRequest.put(startUrl, body);
		    System.out.println("Response of turning second light off: " + response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function is used for setting the color of the first light
	 */
	public void setFirstLightColor(Color color) {
	    LightControlPutRequest control = new LightControlPutRequest();
	    String startUrl =  control.getFirstLightState();
	    String body = null;
	    String response;
	    
		try {
			RGB2HSL rh = new RGB2HSL();
			float hsl[] = null;
			hsl = rh.fromRGB(color, hsl);
			System.out.println("First HSL:" + hsl[0] +  "," + hsl[1] + "," + hsl[2]);
			
			int PhilipsHueValue = (int)(hsl[0] * 65535);
			int PhilipsSaturation = (int)(hsl[1] * 255);
			int PhilipsBrightness = (int)(hsl[2] * 255);
			System.out.println("First Philips HSL: " + PhilipsHueValue + ", " + PhilipsSaturation + ", " + PhilipsBrightness);
			
			body = control.getLightColorBody(PhilipsSaturation, PhilipsBrightness, PhilipsHueValue);
			response = mPutRequest.put(startUrl, body);
		    System.out.println("Request for SET color(first light): " + startUrl);
		    System.out.println("Response of SET color(first light): " + response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function is used for setting the color of the second light
	 */
	public void setSecondLightColor(Color color) {
	    LightControlPutRequest control = new LightControlPutRequest();
	    String startUrl =  control.getSecondLightState();
	    String body = null;
	    String response;
	    
		try {

			RGB2HSL rh = new RGB2HSL();
			float hsl[] = null;
			hsl = rh.fromRGB(color, hsl);
			System.out.println("Second HSL: " + hsl[0] +  "," + hsl[1] + "," + hsl[2]);
			
			int PhilipsHueValue = (int)(hsl[0] * 65535);
			int PhilipsSaturation = (int)(hsl[1] * 255);
			int PhilipsBrightness = (int)(hsl[2] * 255);
			System.out.println("Second Philips HSL: " + PhilipsHueValue + ", " + PhilipsSaturation + ", " + PhilipsBrightness);
			
			body = control.getLightColorBody(PhilipsSaturation, PhilipsBrightness, PhilipsHueValue);
			response = mPutRequest.put(startUrl, body);
			System.out.println("Request for SET color(second light): " + startUrl);
		    System.out.println("Response of SET color(second light): " + response);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
}
