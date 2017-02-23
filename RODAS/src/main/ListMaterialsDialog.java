package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import core.FemModel;

import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;

//public class ListMaterialsDialog extends JDialog {
public class ListMaterialsDialog extends JFrame {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListMaterialsDialog dialog = new ListMaterialsDialog();
			dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListMaterialsDialog() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListMaterialsDialog.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
		setTitle("List Materials");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		
		if (FemModel.vMaterials.isEmpty()) {
			textArea.append("No data\n");
		}
		else
		{
			textArea.append("Id E mu rho\n");
			
			for (int i = 0; i < FemModel.vMaterials.size(); i++) {
				textArea.append(Integer.toString(FemModel.vMaterials.get(i).getId()) + "   " +
								Double.toString(FemModel.vMaterials.get(i).getE()) + "   " +
								Double.toString(FemModel.vMaterials.get(i).getMu()) + "   " +
								Double.toString(FemModel.vMaterials.get(i).getRo()) + "\n");
			}
		}
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
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
