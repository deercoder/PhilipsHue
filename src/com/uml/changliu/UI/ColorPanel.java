package com.uml.changliu.UI;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.uml.changliu.API.LightInterfaceAPI;


/**
 * 
 * For color chooser, the shortcut to select the light is `Space` button, not `Enter` button, that's to say that
 * once we used the arrow button to select each pixel, actually it's not selected, so if we use getColor() function
 * for the specific light, then it should be our previous selected light.
 * 
 * To fix the above issue, we need to make sure that after navigating the color patches, the color is selected, then
 * we should have several options: 1) find shortcuts like `Enter` or `Space` to select the color. 2) find the
 * location of the selected patch, move the mouse to the patch, and then mock the mouse-click event. 3) store
 * the mapping of each patch with the corresponding location index, then visit the array to fetch color.
 * 
 * All these three solutions are plausible at first glance, but it requires some impelmentation, the simplest one
 * is just as I have said, use the `Space` button, this is inspired by the deom here: 
 * 
 * http://marc.info/?l=openjdk-swing-dev&m=135796973104220
 * 
 * I find the post and come across the hitKey, then find the `Space` button is the final shortcut to the selection.
 * 
 * @author cliu
 *
 */
public class ColorPanel extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton1;
	private JButton btnNewButton2;
	private JColorChooser colorChooser1;
	private JColorChooser colorChooser2;
	private JButton btnGetcolor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ColorPanel frame = new ColorPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ColorPanel() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 963, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNewButton1 = new JButton("Light Strip1");
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (colorChooser1 != null) {
					colorChooser1.requestFocus();
					Robot rb;
					try {
						int x = (int)colorChooser1.getLocation().getX() + colorChooser1.getWidth() / 2 + (int)ColorPanel.this.getLocation().getX();
						int y = (int)colorChooser1.getLocation().getY() + colorChooser1.getHeight() / 2 + (int)ColorPanel.this.getLocation().getY();
						rb = new Robot();
						rb.mouseMove(x, y);
						rb.mousePress(InputEvent.BUTTON1_MASK);
						rb.mouseRelease(InputEvent.BUTTON1_MASK);
					} catch (AWTException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton1.setBounds(179, 46, 117, 29);
		contentPane.add(btnNewButton1);
		
		btnNewButton2 = new JButton("Light Strip2");
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (colorChooser2 != null) {
					colorChooser2.requestFocus();
					Robot rb;
					try {
						int x = (int)colorChooser2.getLocation().getX() + colorChooser2.getWidth() / 2 + (int)ColorPanel.this.getLocation().getX();
						int y = (int)colorChooser2.getLocation().getY() + colorChooser2.getHeight() / 2 + (int)ColorPanel.this.getLocation().getY();
						rb = new Robot();
						rb.mouseMove(x,  y);
						rb.mousePress(InputEvent.BUTTON1_MASK);
						rb.mouseRelease(InputEvent.BUTTON1_MASK);
					} catch (AWTException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton2.setBounds(673, 46, 117, 29);
		contentPane.add(btnNewButton2);
		
		colorChooser1 = new JColorChooser();
		colorChooser1.setBounds(38, 105, 432, 264);
		contentPane.add(colorChooser1);
		
		colorChooser2 = new JColorChooser();
		colorChooser2.setBounds(494, 104, 462, 264);
		contentPane.add(colorChooser2);
	
		  // Test code for color, verify if we can get the correct value of color for the selected patch
		btnGetcolor = new JButton("setLightsColor[Debug]");
		btnGetcolor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(getFirstLightColor().toString());
				Color a = getFirstLightColor();
				Color b = getSecondLightColor();
				LightInterfaceAPI api = new LightInterfaceAPI();
				api.setFirstLightColor(a);
				api.setSecondLightColor(b);
			}
		});
		btnGetcolor.setBounds(391, 46, 175, 29);
		contentPane.add(btnGetcolor);
		
	}
	
	public Color getFirstLightColor() {
		if (colorChooser1 != null) {
			return colorChooser1.getColor();
		}
		else {
			return null;
		}
	}
	
	public Color getSecondLightColor() {
		if (colorChooser2 != null) {
			return colorChooser2.getColor();
		}
		else {
			return null;
		}
	}
	
	
}
