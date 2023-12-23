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
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class PanelGerenteAsociacionEmpresa extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblServiceProvider;
	private JLabel lblTransportationProvider;
	private JLabel lblAccommodationProvider;
	private JTextField textFieldBuscadorName;
	private JPanel panelSuperior;

	/**
	 * Create the panel.
	 */
	public PanelGerenteAsociacionEmpresa() {
		setBounds(278, 63, 712, 719);
		setLayout(new BorderLayout(0, 0));
		

		panelSuperior = new JPanel();
		panelSuperior.setBackground(new Color(0, 183, 194));
		add(panelSuperior, BorderLayout.NORTH);

		lblServiceProvider = new JLabel("Service Provider");
		lblServiceProvider.setBorder(new MatteBorder(1, 1, 2, 2, (Color) new Color(0, 0, 0)));
		lblServiceProvider.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambiarSeccion(new PanelGerenteAsociacionEmpresaProveedorServicio()); // se añade la seccion del provedor de servicios
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblServiceProvider.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				definirColorLabelsSecciones();
			}
		});
		panelSuperior.setLayout(new BorderLayout(0, 0));
		lblServiceProvider.setOpaque(true);
		lblServiceProvider.setHorizontalAlignment(SwingConstants.CENTER);
		lblServiceProvider.setForeground(SystemColor.textHighlightText);
		lblServiceProvider.setFont(new Font("Arial Black", Font.PLAIN, 26));
		lblServiceProvider.setBackground(new Color(18, 95, 115));
		panelSuperior.add(lblServiceProvider, BorderLayout.WEST);

		lblTransportationProvider = new JLabel("Transportation Provider");
		lblTransportationProvider.setBorder(new MatteBorder(1, 1, 2, 2, (Color) new Color(0, 0, 0)));
		lblTransportationProvider.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambiarSeccion(new PanelGerenteAsociacionEmpresaProveedorTransporte()); // se añade la seccion del provedor de transporte
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblTransportationProvider.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				definirColorLabelsSecciones();
			}
		});
		lblTransportationProvider.setOpaque(true);
		lblTransportationProvider.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransportationProvider.setForeground(SystemColor.textHighlightText);
		lblTransportationProvider.setFont(new Font("Arial Black", Font.PLAIN, 26));
		lblTransportationProvider.setBackground(new Color(18, 95, 115));
		panelSuperior.add(lblTransportationProvider, BorderLayout.CENTER);

		lblAccommodationProvider = new JLabel("Accommodation Provider");
		lblAccommodationProvider.setBorder(new MatteBorder(1, 1, 2, 2, (Color) new Color(0, 0, 0)));
		lblAccommodationProvider.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cambiarSeccion(new PanelGerenteAsociacionEmpresaProveedorAlojamiento()); // se añade la seccion del provedor de alojamiento
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAccommodationProvider.setBackground(SystemColor.activeCaptionBorder);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				definirColorLabelsSecciones();
			}
		});
		lblAccommodationProvider.setOpaque(true);
		lblAccommodationProvider.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccommodationProvider.setForeground(SystemColor.textHighlightText);
		lblAccommodationProvider.setFont(new Font("Arial Black", Font.PLAIN, 26));
		lblAccommodationProvider.setBackground(new Color(18, 95, 115));
		panelSuperior.add(lblAccommodationProvider, BorderLayout.EAST);

		PanelGerenteAsociacionEmpresaProveedorServicio panelGerenteAsociacionEmpresaProveedorServicio = new PanelGerenteAsociacionEmpresaProveedorServicio();
		add(panelGerenteAsociacionEmpresaProveedorServicio, BorderLayout.CENTER); // se añade la seccion del provedor de servicios
		
		
		this.definirColorLabelsSecciones(); // se define el color de los labels de las secciones

	}

	public void cambiarSeccion (JPanel seccion) {
		remove(getComponentCount() - 1); // se remueve el ultimo panel (este panel va a ser una seccion)
		add(seccion, BorderLayout.CENTER); // se adiciona la seccion
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
			this.lblServiceProvider.setBackground(SystemColor.activeCaptionBorder);
		else
			this.lblServiceProvider.setBackground(new Color(18, 95, 115));
	}

	private void definirColorLabelSeccionTransportationProvider () {
		if (getComponent(getComponentCount() - 1) instanceof PanelGerenteAsociacionEmpresaProveedorTransporte)  // se esta seleccionada la seccion creacion contrato
			this.lblTransportationProvider.setBackground(SystemColor.activeCaptionBorder);
		else
			this.lblTransportationProvider.setBackground(new Color(18, 95, 115));
	}

	private void definirColorLabelSeccionAccommodationProvider () {
		if (getComponent(getComponentCount() - 1) instanceof PanelGerenteAsociacionEmpresaProveedorAlojamiento)  // se esta seleccionada la seccion creacion contrato
			this.lblAccommodationProvider.setBackground(SystemColor.activeCaptionBorder);
		else
			this.lblAccommodationProvider.setBackground(new Color(18, 95, 115));
	}
}
