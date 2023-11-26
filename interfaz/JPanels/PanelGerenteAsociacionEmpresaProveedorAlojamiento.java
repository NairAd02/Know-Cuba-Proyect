package JPanels;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class PanelGerenteAsociacionEmpresaProveedorAlojamiento extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelGerenteAsociacionEmpresaProveedorAlojamiento() {
		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorAlojamiento.class.getResource("/images/Imagen1.jpg")));
		lblNewLabel.setBounds(0, 0, 712, 678);
		add(lblNewLabel);

	}

}
