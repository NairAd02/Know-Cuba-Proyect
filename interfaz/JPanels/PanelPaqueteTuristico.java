package JPanels;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.border.EmptyBorder;

import logica.TouristPackage;

public class PanelPaqueteTuristico extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TouristPackage touristPackage;
	private JLabel lblCampoDiasDuracion;
	private JLabel lblName;
	private JLabel lblCampoName;

	public PanelPaqueteTuristico(TouristPackage t) {
		this.touristPackage = t;
		setLayout(null);
		setBackground(new Color(4, 179, 208));

		lblName = new JLabel("Name :");
		lblName.setForeground(SystemColor.info);
		lblName.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblName.setBounds(461, 56, 72, 23);
		add(lblName);

		JLabel lblPaquete = new JLabel("PAQUETE : ");
		lblPaquete.setForeground(SystemColor.info);
		lblPaquete.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblPaquete.setBounds(461, 22, 101, 23);
		add(lblPaquete);

		JLabel lblCampoPaquete = new JLabel(String.valueOf(this.touristPackage.getId()));
		lblCampoPaquete.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCampoPaquete.setBounds(572, 22, 34, 23);
		add(lblCampoPaquete);

		lblCampoDiasDuracion = new JLabel("");
		lblCampoDiasDuracion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCampoDiasDuracion.setBounds(623, 178, 77, 23);
		add(lblCampoDiasDuracion);

		JLabel lblCosto = new JLabel("COSTO :");
		lblCosto.setForeground(SystemColor.info);
		lblCosto.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCosto.setBounds(461, 178, 72, 23);
		add(lblCosto);

		JLabel lblCampoCosto = new JLabel(String.valueOf(this.touristPackage.costo()));
		lblCampoCosto.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCampoCosto.setBounds(543, 178, 72, 23);
		add(lblCampoCosto);
		lblCampoName = new JLabel(this.touristPackage.getName());
		lblCampoName.setForeground(Color.BLACK);
		lblCampoName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCampoName.setBounds(532, 56, 280, 23);
		add(lblCampoName);
		JLabel lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(PanelPaqueteTuristico.class.getResource("/images/Nuevo Presentaci\u00F3n de Microsoft PowerPoint (2) - copia (3).jpg")));
		lblImagen.setBounds(0, 0, 915, 243);
		add(lblImagen);

		setSize(425, 231);


	}


}
