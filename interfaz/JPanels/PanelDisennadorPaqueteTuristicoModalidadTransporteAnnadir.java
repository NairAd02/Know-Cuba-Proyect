package JPanels;



import javax.swing.JPanel;
import JFrames.FrameInformacionPaquete;
import logica.TouristPackage;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Deque;
import java.util.LinkedList;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;


public class PanelDisennadorPaqueteTuristicoModalidadTransporteAnnadir extends JPanel {

	private static final long serialVersionUID = 1L;
	private int mouseX, mouseY;
	private FrameInformacionPaquete frameInformacionPaquete;
	private TouristPackage touristPackage;
	private Deque<PanelTransportModalityTouristDesignerOperations> previusModels;
	private Deque<PanelTransportModalityTouristDesignerOperations> nextsModels;
	private PanelTransportModalityTouristDesignerOperations panelContenedorTablas;
	private JLabel lblTitleTable;
	private JLabel labelFlechaAnterior;
	private JLabel labelFlechaSiguiente;
	private JLabel lblAtras;



	public TouristPackage getTouristPackage() {
		return touristPackage;
	}

	public void setTouristPackage(TouristPackage touristPackage) {
		this.touristPackage = touristPackage;
	}


	public PanelDisennadorPaqueteTuristicoModalidadTransporteAnnadir(FrameInformacionPaquete f) {
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		this.frameInformacionPaquete = f;
		this.touristPackage = this.frameInformacionPaquete.getTouristPackage();
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x= e.getXOnScreen();
				int y= e.getYOnScreen();

			frameInformacionPaquete.setLocation(x - mouseX , y - mouseY );
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				panelContenedorTablas.clearSelectionTable();
				panelContenedorTablas.actualizarEstadosBotones();
			}
		});
		setBounds(0, 0, 971, 591);
		setLayout(null);
		setBackground(new Color(18, 95, 115));

		JLabel lblTransportationSeccion = new JLabel("TRANSPORT SECCION");
		lblTransportationSeccion.setForeground(SystemColor.textHighlightText);
		lblTransportationSeccion.setFont(new Font("Dialog", Font.BOLD, 24));
		lblTransportationSeccion.setBounds(348, 5, 275, 20);
		add(lblTransportationSeccion);

		labelFlechaAnterior = new JLabel("");
		labelFlechaAnterior.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (labelFlechaAnterior.isEnabled()) {
					previusPanel(); // realiza la operacion de asignar el modelo anterior
					actualizarPanelTablaModality(); // se actualiza la informacion de la tabla anterior
					actualizarEstadosFlechas(); // se actualiza el estado de las flechas para evitar inconsistencias
					actualizarTextotTitleTable(); // se establece un nuevo texto para el titulo de la tabla de actuado al modelo actual
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		labelFlechaAnterior.setIcon(new ImageIcon(PanelDisennadorPaqueteTuristicoModalidadTransporteAnnadir.class.getResource("/images/Left Arrow.png")));
		labelFlechaAnterior.setFont(new Font("Arial Black", Font.PLAIN, 11));
		labelFlechaAnterior.setBounds(348, 31, 50, 20);
		add(labelFlechaAnterior);

		lblTitleTable = new JLabel("");
		lblTitleTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleTable.setForeground(SystemColor.textHighlightText);
		lblTitleTable.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblTitleTable.setBounds(428, 26, 111, 30);
		add(lblTitleTable);

		labelFlechaSiguiente = new JLabel("");
		labelFlechaSiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (labelFlechaSiguiente.isEnabled()) {
					nextPanel(); // se realiza la operacion de asignar el modelo siguiente
					actualizarPanelTablaModality(); // se actualiza la informacion de la tabla siguiente
					actualizarEstadosFlechas(); // se actualiza el estado de las flechas para evitar inconsistencias
					actualizarTextotTitleTable(); // se establece un nuevo texto para el titulo de la tabla de actuado al modelo actual
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		labelFlechaSiguiente.setIcon(new ImageIcon(PanelDisennadorPaqueteTuristicoModalidadTransporteAnnadir.class.getResource("/images/Right Arrow.png")));
		labelFlechaSiguiente.setFont(new Font("Arial Black", Font.PLAIN, 11));
		labelFlechaSiguiente.setBounds(556, 31, 50, 20);
		add(labelFlechaSiguiente);
		
		lblAtras = new JLabel("");
		lblAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				frameInformacionPaquete.cambioDePanel(frameInformacionPaquete.getPanelInformacionPaquetes()); // se ejecuta el cambio de panel
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
		});
		lblAtras.setIcon(new ImageIcon(PanelDisennadorPaqueteTuristicoModalidadTransporteAnnadir.class.getResource("/images/Circled Right.png")));
		lblAtras.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtras.setForeground(Color.BLACK);
		lblAtras.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblAtras.setBackground(SystemColor.menu);
		lblAtras.setBounds(10, 13, 53, 43);
		add(lblAtras);
		
		this.inicializarSistemaDePaneles();
	}

	private void inicializarSistemaDePaneles () {
		this.panelContenedorTablas = new PanelPackageDisignerContenedorCostKilometers(PanelDisennadorPaqueteTuristicoModalidadTransporteAnnadir.this);
		add ((Component) this.panelContenedorTablas);
		this.inicializarPilas();
		this.actualizarPanelTablaModality();
		this.actualizarEstadosFlechas();
		this.actualizarTextotTitleTable();
	}

	private void actualizarTextotTitleTable () {
		String texto = "";


		if (this.panelContenedorTablas instanceof PanelPackageDisignerContenedorCostKilometers)
			texto = "Cost-KM";

		else if (this.panelContenedorTablas instanceof PanelPackageDisignerContenedorHoursKilometers)
			texto = "Hours-KM";

		else if (this.panelContenedorTablas instanceof PanelPackageDisignerContenedorEstablishedRoute)
			texto = "Route";

		lblTitleTable.setText(texto);


	}

	private void inicializarPilas () {
		this.previusModels = new LinkedList<PanelTransportModalityTouristDesignerOperations>();
		this.nextsModels = new LinkedList<PanelTransportModalityTouristDesignerOperations>();
		this.nextsModels.push(new PanelPackageDisignerContenedorEstablishedRoute(PanelDisennadorPaqueteTuristicoModalidadTransporteAnnadir.this)); // se añade a la pila de siguientes el panel tipo EstablishedRoute
		this.nextsModels.push(new PanelPackageDisignerContenedorHoursKilometers(PanelDisennadorPaqueteTuristicoModalidadTransporteAnnadir.this)); // se añade a la pila de siguientes el panel tipo HoursKilometers
	}

	// Metodos para el cambio de tablas
	private void actualizarEstadosFlechas () {
		if (this.previusModels.isEmpty()) 
			this.labelFlechaAnterior.setEnabled(false);
		else
			this.labelFlechaAnterior.setEnabled(true);

		if (this.nextsModels.isEmpty())
			this.labelFlechaSiguiente.setEnabled(false);
		else
			this.labelFlechaSiguiente.setEnabled(true);


	}

	private void nextPanel () {
		PanelTransportModalityTouristDesignerOperations panel = this.nextsModels.pop(); // se obtiene el panel siguiente
		this.previusModels.push(panelContenedorTablas); // el panel antes asignado se convierte en el modelo anterior
		this.panelContenedorTablas = panel; // se asigna el nuevo panel
		this.panelContenedorTablas.actualizarTablas(); // se actualiza la informacion de las tablas del panel
		this.cambiaranel((JPanel) this.panelContenedorTablas);
	}

	private void previusPanel () {
		PanelTransportModalityTouristDesignerOperations panel = this.previusModels.pop(); // se obtiene el panel anterior
		this.nextsModels.push(panelContenedorTablas); // el panel antes asignado se convierte en el panel siguiente
		this.panelContenedorTablas = panel; // se asigna el nuevo panel
		this.panelContenedorTablas.actualizarTablas(); // se actualiza la informacion de las tablas del panel
		this.cambiaranel((JPanel) this.panelContenedorTablas);
	}

	private void cambiaranel (JPanel panel) {
		remove(getComponentCount() - 1); // se remueve el ulitmo componenete
		add(panel); // se añade el nuevo panel
		this.repintarPanel(); // se repinta la informacion del panel
	}

	private void repintarPanel () {
		repaint();
		revalidate();
	}

	// Fin de Metodos para el cambio de tablas

	public void actualizarPanelTablaModality () {
		this.panelContenedorTablas.actualizarTablas(); // se actualiza la informacion del panel que contiene a la tabla de las modadlidades seleccionadas
	}

	// Metodos para la actualizacion de el panel de la tabla 



}
