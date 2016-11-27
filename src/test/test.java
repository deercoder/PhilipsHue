/**
 * Created by Chang Liu(chang_liu@student.uml.edu) on 11/19/2016
 * This is test class for API functions that provide to other modules to call.
 */

package test;

import java.awt.Color;
import java.io.IOException;

import com.uml.changliu.API.LightInterfaceAPI;


public class test {

	private static LightInterfaceAPI api = new LightInterfaceAPI();
	
	  public static void main(String[] args) throws IOException {

		  		api.createLightUserId();
		  
		  		// Inquiry the light status
		  		api.inqueryFirstLightStatus();
		  		api.inquerySecondLightStatus();
	    
		  		// turn on and set colors
		  		api.setFirstLightColor(new Color(128,128, 128));
		  		api.setSecondLightColor(new Color(132, 132, 132));
		  		
		  		
		  		// set brightness(0~255)
		  		api.setFirstLightness(200);
		  		api.setSecondLightness(150);
		  		
		  		// turn off
		  		api.setFirstLightOff();
		  		api.setSecondLightOff();
	  }
	
}
