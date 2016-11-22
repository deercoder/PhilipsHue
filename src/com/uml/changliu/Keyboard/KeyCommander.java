/**
 * Created by Chang Liu(chang_liu@student.uml.edu) on 11/19/2014
 * This is keyboard event/mouse event class, for mocking user input, creating input events.
 * 
 */


package com.uml.changliu.Keyboard;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;




/**
 * 
Symbolic Name             Meaning

VK_0 ... VK_9             0 ... 9    
VK_A ... VK_Z             A ... Z    
VK_ENTER                  Enter    
VK_SPACE                  Space    
VK_TAB                    Tabulator    
VK_ESCAPE                 Escape    
VK_BACK_SPACE             Back    
VK_F1 ... VK_F12          F1 ... F12    
VK_HOME, VK_END           Home, End    
VK_INSERT, VK_DELETE      Insert, Delete    
VK_PAGE_UP, VK_PAGE_DOWN  Page Up, Page Down    
VK_DOWN, VK_UP            Cursor down, Cursor up    
VK_LEFT, VK_RIGHT         Cursor left, Cursor right
 *
 */
public class KeyCommander {

	private Robot robot;

	public KeyCommander() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			System.out.println("Error when constructing Robot!");
			e.printStackTrace();
		}
	}
	
	public void sendLeftKeyEvent() {
		robot.keyPress(KeyEvent.VK_LEFT);
		robot.keyRelease(KeyEvent.VK_LEFT);
	}
	
	public void sendRightKeyEvent() {
		robot.keyPress(KeyEvent.VK_RIGHT);
		robot.keyRelease(KeyEvent.VK_RIGHT);
	}
	
	public void sentUpKeyEvent() {
		robot.keyPress(KeyEvent.VK_UP);
		robot.keyRelease(KeyEvent.VK_UP);
	}
	
	public void sentDownKeyEvent() {
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
	}
	
	public void sentFullDisplayEvent() {
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_F5);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_F5);
	}
	
	
	public void sendExitEvent() {
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}
	
	public void sendMouseClickEvent() {
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	
}
