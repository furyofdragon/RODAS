package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class AboutDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AboutDialog dialog = new AboutDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AboutDialog() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AboutDialog.class.getResource("/javax/swing/plaf/metal/icons/ocean/question.png")));
		setTitle("About RODAS");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		FlowLayout fl_contentPanel = new FlowLayout();
		contentPanel.setLayout(fl_contentPanel);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lbLogo = new JLabel("");
			lbLogo.setIcon(new ImageIcon(AboutDialog.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
			contentPanel.add(lbLogo);
		}
		{
			JTextArea txtAboutProgram = new JTextArea();
			txtAboutProgram.setEditable(false);
			txtAboutProgram.setWrapStyleWord(true);
			txtAboutProgram.setBackground(getContentPane().getBackground());
			txtAboutProgram.setFont(new Font("Monospaced", Font.PLAIN, 12));
			txtAboutProgram.setText("RODAS is FEA software.\r\n\r\n\u00A9 Copyright furyofdragon, 2017.");
			contentPanel.add(txtAboutProgram);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
