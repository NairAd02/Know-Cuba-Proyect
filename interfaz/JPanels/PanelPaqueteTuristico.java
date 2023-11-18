package JPanels;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.SystemColor;

public class PanelPaqueteTuristico extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelPaqueteTuristico() {
		setLayout(null);
		setBackground(new Color(4, 179, 208));
		
		JLabel lblNewLabel_1 = new JLabel("T\u00EDtulo");
		lblNewLabel_1.setForeground(SystemColor.info);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(461, 34, 60, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setForeground(SystemColor.info);
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNewLabel.setBounds(461, 75, 46, 14);
		add(lblNewLabel);
		
		JLabel label = new JLabel("New label");
		label.setForeground(SystemColor.info);
		label.setFont(new Font("Arial Black", Font.PLAIN, 16));
		label.setBounds(461, 113, 46, 14);
		add(label);
		
		JLabel label_1 = new JLabel("New label");
		label_1.setForeground(SystemColor.info);
		label_1.setFont(new Font("Arial Black", Font.PLAIN, 16));
		label_1.setBounds(694, 34, 46, 14);
		add(label_1);
		
		JLabel label_2 = new JLabel("New label");
		label_2.setForeground(SystemColor.info);
		label_2.setFont(new Font("Arial Black", Font.PLAIN, 16));
		label_2.setBounds(694, 75, 110, 14);
		add(label_2);
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(PanelPaqueteTuristico.class.getResource("/images/Nuevo Presentaci\u00F3n de Microsoft PowerPoint (2) - copia.jpg")));
		lblImagen.setBounds(0, 0, 992, 216);
		add(lblImagen);

	}
}
