/**
 * Created by Chang Liu(chang_liu@student.uml.edu) on 11/19/2016
 * This is Light state parser, used for parsing the light's various status
 * 
 */
package com.uml.changliu.Parser;

import org.json.JSONException;
import org.json.JSONObject;

public class LightStateParser {

	/**
	 *  The example of returned status:
	 *  
	 *  {"state": {"on":true,"bri":247,"hue":65000,"sat":254,"effect":"none","xy":[0.1817,0.2224],"alert":"none","colormode":"hs","reachable":true},
	 *  "type": "Color light", "name": "LightStrips 2", "modelid": "LST001", "manufacturername": "Philips","uniqueid":"00:17:88:01:00:ce:d7:a6-0b", 
	 *  "swversion": "5.8.1.10400"}
	 *  
	 * @author cliu
	 */

	public LightStateParser(JSONObject json) {
		mStat = new State();
		this.parse(json);
	}
	
	//////////////////////////////////////////////////  PARSE, SET inner state      ////////////////////////////////////////////////////////////
	
	public void parse(JSONObject json) throws JSONException {
		setState(json.getJSONObject("state"));
		setType(json.getString("type"));
		setName(json.getString("name"));
		setModelID(json.getString("modelid"));
		setManufacturerName(json.getString("manufacturername"));
		setUniqueID(json.getString("uniqueid"));
		setSWVersion(json.getString("swversion"));
	}
	
	////////////////////////////////////////////////// 	GET Status After Parsing	////////////////////////////////////////////////////////////
	
	public boolean getOnOffStatus() {
		return mStat.getOnStatus();
	}
	public int getBrightnessValue() {
		return mStat.getBrightness();
	}
	public int getHueValue() {
		return mStat.getHue();
	}
	public int getSaturateValue() {
		return mStat.getSaturate();
	}
	public boolean getReachableStateValue() {
		return mStat.getReachableState();
	}
	
	////////////////////////////////////////////////// 	SET Status BEFORE Parsing	////////////////////////////////////////////////////////////

	public void setState(JSONObject jo) {
		mStat.setStatus(jo.getBoolean("on"));
		mStat.setBrightness(jo.getInt("bri"));
		mStat.setSat(jo.getInt("sat"));
		mStat.setHue(jo.getInt("hue"));
		mStat.setEffect(jo.getString("effect"));
		mStat.setReachable(jo.getBoolean("reachable"));
	}
	public void setType(String ty) {
		type = ty;
	}
	public void setName(String na) {
		name = na;
	}
	public void setModelID(String mi) {
		modelid = mi;
	}
	public void setManufacturerName(String mn) {
		manufacturername = mn;
	}
	public void setUniqueID(String ui) {
		uniqueid =  ui;
	}
	public void setSWVersion(String v) {
		swversion = v;
	}
	
	
	//////////////////////////////////////////////////   Inner STATE variables&class ////////////////////////////////////////////////////////////
	public class State {
		private boolean onStatus;
		private int brightness;
		private int hueValue;
		private int sat;
		private String effect;
		private String xyArray;
		private String alert;
		private String colorMode;
		private boolean reachable;
		
		public void parse(JSONObject jb) {
			
		}
		
		////////////////////////// 	GET Status After Parsing	/////////////////////
		
		public boolean getOnStatus() {
			return onStatus;
		}
		public int getBrightness() {
			return brightness;
		}
		public int getHue() {
			return hueValue;
		}
		public int getSaturate() {
			return sat;
		}
		public String getEffect() {
			return effect;
		}
		public boolean getReachableState() {
			return reachable;
		}
		
		////////////////////	SET Status After Parsing	//////////////////////
		public void setStatus(boolean s) {
			onStatus = s;
		}
		public void setBrightness(int b) {
			brightness = b;
		}
		public void setHue(int h) {
			hueValue = h;
		}
		public void setSat(int s) {
			sat = s;
		}
		public void setEffect(String eff) {
			effect = eff;
		}
		public void setReachable(boolean r) {
			reachable = r;
		}
		/// etc... for other unused information that omitted right now.....
		
	}
	
	private String type;
	private String name;
	private String modelid;
	private String manufacturername;
	private String uniqueid;
	private String swversion;
	
	private State mStat;
	
	
}
