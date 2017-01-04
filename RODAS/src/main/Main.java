package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Frame;

public class Main {

	private JFrame mainWindow;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.mainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		int startXPosition = 100;
		int startYPosition = 100;
		int with = 450;
		int height = 300;
		mainWindow = new JFrame();
		mainWindow.setExtendedState(Frame.MAXIMIZED_BOTH);
		mainWindow.setTitle("RODAS");
		mainWindow.setBounds(startXPosition, startYPosition, with, height);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
