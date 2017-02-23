package main;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.Frame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import input.ReadFemModel;

import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame mainWindow;
	private JTextField statusBar;
	private JMenu menuLF;
	private final ButtonGroup lfButtonGroup = new ButtonGroup();

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
		initializeLF();
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
		
		JMenuBar menuBar = new JMenuBar();
		mainWindow.setJMenuBar(menuBar);
		
		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		JMenuItem menuFileOpen = new JMenuItem("Open...");
		menuFileOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileopen = new JFileChooser();
				FileNameExtensionFilter femFileFilter = new FileNameExtensionFilter("RODAS model (*.FEM)", "FEM");
				fileopen.setFileFilter(femFileFilter);				// default file filter
				int rOpen = fileopen.showOpenDialog(mainWindow);
				switch (rOpen) {
				case JFileChooser.APPROVE_OPTION :
					String inFile = fileopen.getSelectedFile().getPath();
					if(fileopen.getFileFilter().equals(femFileFilter)) {
						ReadFemModel.ReadFemModelFile(inFile);
					}
					break;
				case JFileChooser.CANCEL_OPTION :
					break;
				case JFileChooser.ERROR_OPTION :
					break;
			}
			}
		});
		menuFileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		menuFile.add(menuFileOpen);
		
		JMenuItem menuFileSave = new JMenuItem("Save");
		menuFileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menuFile.add(menuFileSave);
		
		JMenuItem menuFileSaveAs = new JMenuItem("Save As...");
		menuFile.add(menuFileSaveAs);
		
		menuLF = new JMenu("L&F");
		menuBar.add(menuLF);
		
		JMenu menuList = new JMenu("List");
		menuBar.add(menuList);
		
		JMenuItem menuListNodes = new JMenuItem("Nodes");
		menuListNodes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ListNodesDialog.main(null);
				ListNodesDialog2.main(null);
			}
		});
		menuList.add(menuListNodes);
		
		JMenuItem menuListRods = new JMenuItem("Rods");
		menuListRods.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListRodsDialog.main(null);
			}
		});
		menuList.add(menuListRods);
		
		JMenuItem menuListSections = new JMenuItem("Sections");
		menuListSections.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListSectionsDialog.main(null);
			}
		});
		menuList.add(menuListSections);
		
		JMenuItem menuListMaterials = new JMenuItem("Materials");
		menuListMaterials.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListMaterialsDialog.main(null);
			}
		});
		menuList.add(menuListMaterials);
		
		JMenu menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);
		
		JMenuItem menuHelpAbout = new JMenuItem("About");
		menuHelpAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutDialog.main(null);
			}
		});
		menuHelp.add(menuHelpAbout);
		
		JPanel bottomPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) bottomPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		mainWindow.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		statusBar = new JTextField();
		statusBar.setEditable(false);
		statusBar.setColumns(30);
		bottomPanel.add(statusBar);
	}
	
	private void initializeLF() {
		final UIManager.LookAndFeelInfo[] lfinfo = UIManager.getInstalledLookAndFeels();
		for (int i = 0; i < lfinfo.length; i++) {
			final LookAndFeelInfo lfName = lfinfo[i];
			JRadioButtonMenuItem lfitem = new JRadioButtonMenuItem(lfinfo[i].getName());
			lfitem.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						String lookAndFeel = lfName.getClassName();
						try {
							UIManager.setLookAndFeel(lookAndFeel);
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (InstantiationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IllegalAccessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (UnsupportedLookAndFeelException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					};
				}
				
				
			});
			//lfitem.setSelected(true);
			menuLF.add(lfitem);
			lfButtonGroup.add(lfitem);
		}
		
		UIManager.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				SwingUtilities.updateComponentTreeUI(mainWindow);
			}
		});
		
		// setup System L&F as default
		try {
			String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(lookAndFeel);
			for (int i = 0; i < lfinfo.length; i++) {
				if (lfinfo[i].getClassName() == lookAndFeel) ;
				
			}
			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
