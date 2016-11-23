/**
 * Created by Chang Liu(chang_liu@student.uml.edu) on 11/19/2016
 * This is for Test Cases for ALL classes and Functions.
 * 
 */
package test;

import java.awt.Color;
import java.io.IOException;

import org.json.JSONObject;

import com.uml.changliu.Parser.LightStateParser;
import com.uml.changliu.RestApi.GetRequest;
import com.uml.changliu.RestApi.PostRequest;
import com.uml.changliu.RestApi.PutRequest;
import com.uml.changliu.Utility.RGB2HSL;
import com.uml.philips.Light.LightControlPostRequest;
import com.uml.philips.Light.LightControlPutRequest;

public class TestCase {

	/**
	 * Test the Get request, it's used to query the light status
	 */
	public static void testGetRequest() {
	    String response;
		try {
			GetRequest example = new GetRequest();
			response = example.get("http://192.168.1.100/api/VeHdsMjs9ECEagi9uYGT8D2TdhRUKDsCeMcISGHB/lights/2");
			JSONObject jb = new JSONObject(response);
			LightStateParser p = new LightStateParser(jb);
		    System.out.println(p.getBrightnessValue());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This function is the process to set the light for second strip(device 2 according to the query)
	 */
	public static void testSetLightness() {
	    PutRequest example3 = new PutRequest();
	    LightControlPutRequest aaa = new LightControlPutRequest();
	    String startUrl =  aaa.getSecondLightState();
	    String bodyParameter = aaa.getLightBrightnessBody(234);
	    
	    System.out.println(startUrl);
	    System.out.println(bodyParameter);
	    
	    
	    String response;
		try {
			response = example3.put(startUrl, bodyParameter);
		    System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This function is used for reqeust a valid user ID(token), used as authorized user to operate the light
	 * 
	 * MUST be used combining the action of pressing the button on the bridge, otherwise it will not succeed
	 */
	public static void testRequestUserId() {
	    PostRequest example4 = new PostRequest();
	    LightControlPostRequest aaa = new LightControlPostRequest();
	    String startUrl =  aaa.getUserIdRequest();
	    String body = aaa.getUserIdBody();
	    
	    System.out.println(startUrl);
	    System.out.println(body);
	    String response;
	    
		try {
			response = example4.post(startUrl, body);
		    System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 
	 * This function is used for turning off the second light
	 * 
	 */
	public static void testTurnOffLight() {
	    PutRequest example4 = new PutRequest();
	    LightControlPutRequest aaa = new LightControlPutRequest();
	    String startUrl =  aaa.getSecondLightState();
	    String body = aaa.getLightTurnOffBody();
	    
	    System.out.println(startUrl);
	    System.out.println(body);
	    String response;
	    
		try {
			response = example4.put(startUrl, body);
		    System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This function is used for setting the color of the light
	 */
	public static void testSetLightColor() {
	    PutRequest example4 = new PutRequest();
	    LightControlPutRequest aaa = new LightControlPutRequest();
	    String startUrl =  aaa.getSecondLightState();
	    String body = aaa.getLightColorBody(254, 254, 10000);
	    
	    System.out.println(startUrl);
	    System.out.println(body);
	    String response;
	    
		try {
/*			for(int i = 0; i < 65535; i = i + 5000) {
				body = aaa.getLightColorBody(254, 254, i);
				response = example4.put(startUrl, body);
			    System.out.println(response);
			    Thread.sleep(2000);
			}*/
			
			RGB2HSL rh = new RGB2HSL();
			float hsl[] = null;
			hsl = rh.fromRGB(new Color(128, 128, 128), hsl);
			System.out.println(hsl[0] +  "," + hsl[1] + "," + hsl[2]);
			
			int PhilipsHueValue = (int)(hsl[0] * 65535);
			int PhilipsSaturation = (int)(hsl[1] * 255);
			int PhilipsBrightness = (int)(hsl[2] * 255);
			System.out.println(PhilipsHueValue + ", " + PhilipsSaturation + ", " + PhilipsBrightness);
			
			body = aaa.getLightColorBody(PhilipsSaturation, PhilipsBrightness, PhilipsHueValue);
			response = example4.put(startUrl, body);
		    System.out.println(response);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test main Entry
	 * @param args
	 */
	public static void main(String args[]) {
		testSetLightColor();
		testGetRequest();
		//testPostRequest();
		//testSetLightness();
		//testRequestUserId();
		//testTurnOffLight();
	}
	
	
}
