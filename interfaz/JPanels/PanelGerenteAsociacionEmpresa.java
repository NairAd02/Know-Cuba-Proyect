package JPanels;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;

public class PanelGerenteAsociacionEmpresa extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelGerenteAsociacionEmpresa() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 712, 719);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(0, 0, 712, 41);
		panel.add(panel_3);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1.setForeground(Color.BLACK);
		separator_1_1.setBackground(Color.BLACK);
		separator_1_1.setBounds(234, 0, 2, 41);
		panel_3.add(separator_1_1);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1_1.setForeground(Color.BLACK);
		separator_1_1_1.setBackground(Color.BLACK);
		separator_1_1_1.setBounds(483, 0, 2, 41);
		panel_3.add(separator_1_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Service Provider");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(SystemColor.info);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblNewLabel_1.setBackground(new Color(18, 95, 115));
		lblNewLabel_1.setBounds(0, 0, 236, 41);
		panel_3.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Transportation Provider");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(SystemColor.info);
		lblNewLabel_2.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblNewLabel_2.setBackground(new Color(18, 95, 115));
		lblNewLabel_2.setBounds(235, 0, 250, 41);
		panel_3.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Accommodation Provider");
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(SystemColor.info);
		lblNewLabel_3.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblNewLabel_3.setBackground(new Color(18, 95, 115));
		lblNewLabel_3.setBounds(483, 0, 229, 41);
		panel_3.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 41, 712, 678);
		panel.add(panel_1);
		panel_1.setLayout(null);

	}
}
