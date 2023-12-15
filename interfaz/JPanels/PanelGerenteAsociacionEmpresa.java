package JPanels;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class PanelGerenteAsociacionEmpresa extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblServiceProvider;
	private JLabel lblTransportationProvider;
	private JLabel lblAccommodationProvider;
	private JTextField textFieldBuscadorName;

	/**
	 * Create the panel.
	 */
	public PanelGerenteAsociacionEmpresa() {
		setLayout(null);
		setBounds(278, 63, 712, 719);
		

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(0, 0, 712, 41);
		add(panel_3);

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

		lblServiceProvider = new JLabel("Service Provider");
		lblServiceProvider.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambiarSeccion(new PanelGerenteAsociacionEmpresaProveedorServicio()); // se añade la seccion del provedor de servicios
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblServiceProvider.setBackground(SystemColor.inactiveCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				definirColorLabelsSecciones();
			}
		});
		lblServiceProvider.setOpaque(true);
		lblServiceProvider.setHorizontalAlignment(SwingConstants.CENTER);
		lblServiceProvider.setForeground(SystemColor.info);
		lblServiceProvider.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblServiceProvider.setBackground(new Color(18, 95, 115));
		lblServiceProvider.setBounds(0, 0, 236, 41);
		panel_3.add(lblServiceProvider);

		lblTransportationProvider = new JLabel("Transportation Provider");
		lblTransportationProvider.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambiarSeccion(new PanelGerenteAsociacionEmpresaProveedorTransporte()); // se añade la seccion del provedor de transporte
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblTransportationProvider.setBackground(SystemColor.inactiveCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				definirColorLabelsSecciones();
			}
		});
		lblTransportationProvider.setOpaque(true);
		lblTransportationProvider.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransportationProvider.setForeground(SystemColor.info);
		lblTransportationProvider.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblTransportationProvider.setBackground(new Color(18, 95, 115));
		lblTransportationProvider.setBounds(235, 0, 250, 41);
		panel_3.add(lblTransportationProvider);

		lblAccommodationProvider = new JLabel("Accommodation Provider");
		lblAccommodationProvider.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambiarSeccion(new PanelGerenteAsociacionEmpresaProveedorAlojamiento()); // se añade la seccion del provedor de alojamiento
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAccommodationProvider.setBackground(SystemColor.inactiveCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				definirColorLabelsSecciones();
			}
		});
		lblAccommodationProvider.setOpaque(true);
		lblAccommodationProvider.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccommodationProvider.setForeground(SystemColor.info);
		lblAccommodationProvider.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblAccommodationProvider.setBackground(new Color(18, 95, 115));
		lblAccommodationProvider.setBounds(483, 0, 229, 41);
		panel_3.add(lblAccommodationProvider);

		PanelGerenteAsociacionEmpresaProveedorServicio panelGerenteAsociacionEmpresaProveedorServicio = new PanelGerenteAsociacionEmpresaProveedorServicio();
		add(panelGerenteAsociacionEmpresaProveedorServicio); // se añade la seccion del provedor de servicios
		
		
		this.definirColorLabelsSecciones(); // se define el color de los labels de las secciones

	}

	public void cambiarSeccion (JPanel seccion) {
		remove(getComponentCount() - 1); // se remueve el ultimo panel (este panel va a ser una seccion)
		add(seccion); // se adiciona la seccion
		this.repintarPanel(); // se repinta el frame
		this.definirColorLabelsSecciones(); // se define el color de los labels de las secciones
	}

	public void repintarPanel () {
		repaint();
		revalidate();
	}

	public void definirColorLabelsSecciones () {
		this.definirColorLabelSeccionServiceProvider();
		this.definirColorLabelSeccionTransportationProvider();
		this.definirColorLabelSeccionAccommodationProvider();
		this.repintarPanel();
	}

	private void definirColorLabelSeccionServiceProvider () {
		if (getComponent(getComponentCount() - 1) instanceof PanelGerenteAsociacionEmpresaProveedorServicio) // se esta seleccionada la seccion asociacion empresa
			this.lblServiceProvider.setBackground(SystemColor.inactiveCaptionBorder);
		else
			this.lblServiceProvider.setBackground(new Color(18, 95, 115));
	}

	private void definirColorLabelSeccionTransportationProvider () {
		if (getComponent(getComponentCount() - 1) instanceof PanelGerenteAsociacionEmpresaProveedorTransporte)  // se esta seleccionada la seccion creacion contrato
			this.lblTransportationProvider.setBackground(SystemColor.inactiveCaptionBorder);
		else
			this.lblTransportationProvider.setBackground(new Color(18, 95, 115));
	}

	private void definirColorLabelSeccionAccommodationProvider () {
		if (getComponent(getComponentCount() - 1) instanceof PanelGerenteAsociacionEmpresaProveedorAlojamiento)  // se esta seleccionada la seccion creacion contrato
			this.lblAccommodationProvider.setBackground(SystemColor.inactiveCaptionBorder);
		else
			this.lblAccommodationProvider.setBackground(new Color(18, 95, 115));
	}
}
