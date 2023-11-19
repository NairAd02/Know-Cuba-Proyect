package JPanels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class PanelPaqueteTuristicoAnimacion extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelPaqueteTuristicoAnimacion() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PanelPaqueteTuristicoAnimacion.class.getResource("/images/animaci\u00F3n.gif")));
		lblNewLabel.setBounds(0, 0, 992, 217);
		add(lblNewLabel);

	}
}
