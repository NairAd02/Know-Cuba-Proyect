package JPanels;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class PanelGerenteAsociacionEmpresaProveedorTransporte extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelGerenteAsociacionEmpresaProveedorTransporte() {
		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PanelGerenteAsociacionEmpresaProveedorTransporte.class.getResource("/images/Imagen3.jpg")));
		lblNewLabel.setBounds(0, 0, 712, 678);
		add(lblNewLabel);

	}

}
