/**
 * Created by Chang Liu(chang_liu@student.uml.edu) on 11/23/2016
 * This is the Credentials for the Hue Bridge, which will used for RESTful and authentication, as well as various Request
 */
package com.uml.changliu.Credential;

/**
 * Static class and interface for other modules.
 * @author Chang Liu
 *
 */
public class Credential {

	/**
	 * Static LightBridgeSettings class, which could be used in any of this project, it's depended on the routers.
	 * 
	 * @author Chang Liu
	 *
	 */
	public static class LightBridgeSettings {
		
		public LightBridgeSettings(String h, String u) {
			mHostAddress = h;
			mUserID = u;
		}
		
		public LightBridgeSettings() {
			/// empty body for default settings
		}
		
		public static String getHost() {
			return mHostAddress;
		}
		
		public void setHost(String h) {
			mHostAddress = h;
		}
		
		public void setUserID(String id) {
			mUserID = id;
		}
		
		public static String getUserID() {
			return mUserID;
		}
		
		private static String mHostAddress = "http://192.168.1.100";
		private static String mUserID = "VeHdsMjs9ECEagi9uYGT8D2TdhRUKDsCeMcISGHB";
	}
}
