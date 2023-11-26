package JPanels;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.border.EmptyBorder;

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
		
		JLabel lblDescripcion = new JLabel("DESCRIPCI\u00D3N :");
		lblDescripcion.setForeground(SystemColor.info);
		lblDescripcion.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDescripcion.setBounds(461, 56, 132, 23);
		add(lblDescripcion);
		
		JPanel panelDescripcion = new JPanel();
		panelDescripcion.setOpaque(false);
		panelDescripcion.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelDescripcion.setToolTipText("");
		panelDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panelDescripcion.setBounds(461, 88, 429, 67);
		add(panelDescripcion);
		panelDescripcion.setLayout(null);
		
		JLabel lblPaquete = new JLabel("PAQUETE : ");
		lblPaquete.setForeground(SystemColor.info);
		lblPaquete.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblPaquete.setBounds(461, 22, 101, 23);
		add(lblPaquete);
		
		JLabel lblCampoPaquete = new JLabel("");
		lblCampoPaquete.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCampoPaquete.setBounds(572, 22, 34, 23);
		add(lblCampoPaquete);
		
		JLabel lblDiasDuracion = new JLabel("D\u00CDAS-DURACI\u00D3N :");
		lblDiasDuracion.setForeground(SystemColor.info);
		lblDiasDuracion.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDiasDuracion.setBounds(461, 178, 152, 23);
		add(lblDiasDuracion);
		
		JLabel lblCampoDiasDuracion = new JLabel("");
		lblCampoDiasDuracion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCampoDiasDuracion.setBounds(623, 178, 77, 23);
		add(lblCampoDiasDuracion);
		
		JLabel lblCosto = new JLabel("COSTO :");
		lblCosto.setForeground(SystemColor.info);
		lblCosto.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCosto.setBounds(818, 178, 72, 23);
		add(lblCosto);
		
		JLabel lblCampoCosto = new JLabel("");
		lblCampoCosto.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCampoCosto.setBounds(900, 178, 72, 23);
		add(lblCampoCosto);
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(PanelPaqueteTuristico.class.getResource("/images/Nuevo Presentaci\u00F3n de Microsoft PowerPoint (2) - copia (3).jpg")));
		lblImagen.setBounds(0, 0, 990, 216);
		add(lblImagen);

	}
}
