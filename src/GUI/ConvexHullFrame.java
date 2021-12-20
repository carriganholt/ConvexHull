package GUI;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ConvexHullFrame extends JFrame {
	
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConvexHullFrame frame = new ConvexHullFrame();
					frame.setTitle("Convex Hull Drawer");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ConvexHullFrame() {
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1200, 700);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		ConvexHullPanel panel = new ConvexHullPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		getContentPane().add(panel, gbc_panel);
		
		JButton btnDrawBorder = new JButton("Draw Border");
		btnDrawBorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.drawConvexHull();
			}
		});
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.clearField();
			}
		});
		
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.gridheight = 2;
		gbc_btnClear.insets = new Insets(20, 20, 20, 0);
		gbc_btnClear.gridx = 1;
		gbc_btnClear.gridy = 2;
		getContentPane().add(btnClear, gbc_btnClear);
		GridBagConstraints gbc_btnDrawBorder = new GridBagConstraints();
		gbc_btnDrawBorder.gridheight = 2;
		gbc_btnDrawBorder.insets = new Insets(20, 0, 20, 20);
		gbc_btnDrawBorder.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDrawBorder.gridx = 3;
		gbc_btnDrawBorder.gridy = 2;
		getContentPane().add(btnDrawBorder, gbc_btnDrawBorder);
		
		String instructions = "Click the field to place points, then press the button to draw a border around the points.";
		JLabel lblInstructions = new JLabel(instructions);
		GridBagConstraints gbc_lblInstructions = new GridBagConstraints();
		gbc_lblInstructions.gridheight = 2;
		gbc_lblInstructions.insets = new Insets(20, 0, 20, 0);
		gbc_lblInstructions.gridx = 2;
		gbc_lblInstructions.gridy = 2;
		getContentPane().add(lblInstructions, gbc_lblInstructions);
	}

}
