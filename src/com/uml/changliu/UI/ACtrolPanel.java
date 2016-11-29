package com.uml.changliu.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import java.awt.List;
import javax.swing.JLabel;

public class ACtrolPanel extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		ACtrolPanel frame = new ACtrolPanel();

		Runnable run = new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);			
						/// AC is on
						if (frame.getCurrentAcStatus() == true) {
							frame.setACDisplayOn();
						}
						/// AC is off
						else {
							frame.setACDisplayOff();
						}
						frame.setCurrentGestureDisplay();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		EventQueue.invokeLater(run);
		
		/// Sleep 5s for displaying the original status
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/// Test Code
		CURRENT_GESTURE[] order_seq = {CURRENT_GESTURE.TAP, CURRENT_GESTURE.LEFT, CURRENT_GESTURE.RIGHT, CURRENT_GESTURE.UP, CURRENT_GESTURE.DOWN, CURRENT_GESTURE.TAP,
				CURRENT_GESTURE.TAP, CURRENT_GESTURE.LEFT, CURRENT_GESTURE.RIGHT, CURRENT_GESTURE.LEFT, CURRENT_GESTURE.RIGHT, CURRENT_GESTURE.RIGHT,
				CURRENT_GESTURE.RIGHT, CURRENT_GESTURE.RIGHT, CURRENT_GESTURE.UP, CURRENT_GESTURE.DOWN, CURRENT_GESTURE.LEFT, CURRENT_GESTURE.LEFT,
				CURRENT_GESTURE.LEFT, CURRENT_GESTURE.DOWN, CURRENT_GESTURE.RIGHT, CURRENT_GESTURE.DOWN, CURRENT_GESTURE.DOWN, CURRENT_GESTURE.DOWN,
				CURRENT_GESTURE.DOWN, CURRENT_GESTURE.DOWN, CURRENT_GESTURE.LEFT, CURRENT_GESTURE.LEFT, CURRENT_GESTURE.LEFT};
		int i = 0;
		
		/// This will update mListRight, and it will affect UI, then the UI
		/// will display the new content, for example, here the list is empty
		/// so it will remove the item that was displayed before
		while (i < order_seq.length) {
			final int index = i;
			new Thread() {
				public void run() {
					frame.setAllState(order_seq[index]);
				}
			}.start();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			EventQueue.invokeLater(run);
			i++;
			System.out.println("i = " + index + ", gesture = " + order_seq[index]);
		}

	}

	/**
	 * Create the frame.
	 */
	public ACtrolPanel() {
		setResizable(false);
		
		/// Init list  content to be non-null
		initList();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// draw the left panel to store left list
		JPanel panel_left = new JPanel();
		panel_left.setBounds(43, 65, 225, 217);
		contentPane.add(panel_left);
		
		// add left list to the panel
		if (mListLeft != null) {
			mListLeft.setBounds(10, 28, 200, 179);
			panel_left.setLayout(null);
			panel_left.add(mListLeft);
		}
		
		JLabel lblNewLabel = new JLabel("Status:");
		lblNewLabel.setBounds(39, 6, 61, 16);
		panel_left.add(lblNewLabel);

		JPanel panel_right = new JPanel();
		panel_right.setBounds(302, 65, 192, 217);
		contentPane.add(panel_right);

		if (mListRight != null) {
			mListRight.setBounds(10, 28, 138, 179);
			panel_right.setLayout(null);
			panel_right.add(mListRight);
		}
		
		JLabel lblUsersCurrentGesture = new JLabel("User's Current Gesture is:");
		lblUsersCurrentGesture.setBounds(10, 6, 167, 16);
		panel_right.add(lblUsersCurrentGesture);
		
	}
	
	public void setACDisplayOff() {
		mListLeft.removeAll();
		mListLeft.add(AC_status + "Off");
	}
	
	public void setACDisplayOn() {
		mListLeft.removeAll();
		mListLeft.add(AC_status + "On");
		mListLeft.add(Temperature_status + temp);
		mListLeft.add(Mode_status + mode[mode_flag]);
		mListLeft.add(AirFlow_status + air_speed[air_flag]);
		mListLeft.select(current_select_item+1);
	}
	
	public void setAllState(CURRENT_GESTURE action) {
		
		/// set current gesture according to the input
		current_gesture = action;
		
		switch(action) {
		case TAP:
			switchOnOff();
			break;
		case LEFT:
			switch2LeftMode();
			break;
		case RIGHT:
			switch2RightMode();
			break;
		case UP:
			go2UpMenu();
			break;
		case DOWN:
			go2DownMenu();
			break;
		default:
			break;
		}
	}
	
	
	public void switchOnOff() {
		acIsOn = !acIsOn;
		
		/// if on, then set first menu as default highlights, init other flags to default
		if (acIsOn) {
			current_select_item = 0;
			temp = 25;
			mode_flag = 0;
			air_flag = 0;
		}
	}
	
	public void go2UpMenu() {
		if(acIsOn) {
			current_select_item = (current_select_item - 1 + 3) % 3;
		}
	}
	
	public void go2DownMenu() {
		if (acIsOn) {
			current_select_item = (current_select_item + 1) % 3;
		}
	}
	
	public void switch2LeftMode() {
		if (acIsOn) {
			/// if the current selection is TEMP, then just decrease the temperature by 1
			if (current_select_item == 0) {
				temp -= 1;
			}
			else if (current_select_item == 1) { /// if is MODE option
				mode_flag = (mode_flag - 1 + 4) % 4;
			}
			else if(current_select_item == 2) { /// if is AIR_SPEED option
				air_flag = (air_flag - 1 + 4) % 4;
			}
		}
	}
	
	public void switch2RightMode() {
		if (acIsOn) {
			/// if the current selection is TEMP, then just increase the temperature by 1
			if (current_select_item == 0) {
				temp += 1;
			}
			else if (current_select_item == 1) { /// if is MODE option
				mode_flag = (mode_flag + 1) % 4;
			}
			else if(current_select_item == 2) { /// if is AIR_SPEED option
				air_flag = (air_flag + 1) % 4;
			}
		}
	}
	
	
	public void setCurrentGestureDisplay() {
		mListRight.removeAll();
		switch(current_gesture) {
		case TAP:
			mListRight.add("TAP");
			break;
		case LEFT:
			mListRight.add("Left");
			break;
		case RIGHT:
			mListRight.add("Right");
			break;
		case UP:
			mListRight.add("Up");
			break;
		case DOWN:
			mListRight.add("Down");
			break;
		default:
			mListRight.add("");
		}
	}
	
	public void initList() {
		// init the instance
		mListLeft = new List();
		mListRight = new List();
	}
	
	public void initState() {
		current_gesture = CURRENT_GESTURE.LEFT;
	}
	
	
	public boolean getCurrentAcStatus() {
		return acIsOn;
	}
	
	
	/// default strings for tagging the content
	private String AC_status = "AC: ";
	private String Temperature_status = "Temperature: ";
	private String Mode_status = "Mode: ";
	private String AirFlow_status = "Air Flow Speed: ";
	
	/// flags for mark the current status and switches
	private boolean acIsOn = false;
	private int temp = 25;
	private int mode_flag = 0;
	private int air_flag = 0;
	private int current_select_item = 0;
	private CURRENT_GESTURE current_gesture = CURRENT_GESTURE.UNKNOW;
	
	/// default string for options
	private String mode[] = {"Cool", "Fan", "Dry", "Power Saver"};
	private String air_speed[] = {"Automatic", "High", "Medium", "Low"};
	private enum CURRENT_GESTURE
	{
		TAP, LEFT, RIGHT, UP, DOWN, UNKNOW
	};
	
	/// list components for displaying the status
	private List mListLeft;
	private List mListRight;
}
