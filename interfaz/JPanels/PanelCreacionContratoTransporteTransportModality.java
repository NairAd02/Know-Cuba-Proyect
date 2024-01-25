package JPanels;



import java.awt.Color;

import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Deque;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import JFrames.FrameDecisor;
import JFrames.FrameGerenteCreacionContratoTransporte;
import JFrames.FrameGerenteCreacionContratoTransporteAnnadirCostoKilometers;
import JFrames.FrameGerenteCreacionContratoTransporteAnnadirHorasKilometers;
import JFrames.FrameGerenteCreacionContratoTransporteAnnadirRecorridoEstablecido;
import JFrames.FramePrincipal;
import logica.CarrierContract;
import logica.Controller;
import logica.CostKilometers;
import logica.EstablishedRoute;
import logica.HoursKilometers;
import utils.Semaphore;

import java.awt.FlowLayout;
import javax.swing.border.MatteBorder;

public class PanelCreacionContratoTransporteTransportModality extends JPanel {

	private static final long serialVersionUID = 1L;
	private FrameGerenteCreacionContratoTransporte frameGerenteCreacionContratoTransporte;
	private CarrierContract carrierContract;
	private JLabel lblAnnadir;
	private JLabel lblEliminar;
	private JLabel lblAtras;
	private JLabel labelFlechaAterior;
	private JLabel labelFlechaSiguiente;
	private Deque<PanelTransportModalityOperations> previusModels;
	private Deque<PanelTransportModalityOperations> nextsModels;
	private PanelTransportModalityOperations panelContenedorTablas;
	private JLabel lblTitleTable;
	private JPanel panelBotones;
	private boolean isRestoreFilters;
	private JLabel lblUpdate;
	private JLabel lblRestoreFilters;


	private class Eliminar extends Thread { // Hilo para la eliminacion

		public void run () {
			synchronized (Semaphore.samaphore) { 
				try {
					Semaphore.samaphore.wait(); // se duerme al hilo hasta esperar la confirmacion del usuario
					if (Controller.getInstancie().isConfirmacion()) { // si el usuario dió el consentimiento de realizar la operación				
						deleteElementesPanelTablaModality(); // se eliminan las modalidades seleccionadas 
						Controller.getInstancie().setConfirmacion(false); // se establece el estado de la confirmación por defecto			
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	private void crearFrameDecisor () {
		FrameDecisor frameDecisor = new FrameDecisor(frameGerenteCreacionContratoTransporte, "Seguro que desea eliminar");
		frameDecisor.setVisible(true);
		frameGerenteCreacionContratoTransporte.setEnabled(false); // se inhabilita el frame principal
	}


	public boolean isRestoreFilters() {
		return isRestoreFilters;
	}

	public void setRestoreFilters(boolean isRestoreFilters) {
		this.isRestoreFilters = isRestoreFilters;
	}

	private void repintarPanel () {
		repaint();
		revalidate();
	}

	public PanelCreacionContratoTransporteTransportModality(FrameGerenteCreacionContratoTransporte ct) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				panelContenedorTablas.clearSelectionTable();
				actualizarEstadoButtons();
			}
		});
		isRestoreFilters = false;
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		this.frameGerenteCreacionContratoTransporte = ct;
		this.carrierContract = this.frameGerenteCreacionContratoTransporte.getCarrierContract();
		setLayout(null);
		setBackground(new Color(18, 95, 115));
		setBounds(0, 0, 853, 577);


		JLabel lbTansportModality = new JLabel("Modalidad de Transporte");
		lbTansportModality.setForeground(SystemColor.textHighlightText);
		lbTansportModality.setFont(new Font("Dialog", Font.BOLD, 26));
		lbTansportModality.setBounds(266, 24, 321, 30);
		add(lbTansportModality);



		lblAtras = new JLabel("");
		lblAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				frameGerenteCreacionContratoTransporte.cambioDePanel(frameGerenteCreacionContratoTransporte.getPanelTransportationContract());
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblAtras.setIcon(new ImageIcon(PanelCreacionContratoTransporteTransportModality.class.getResource("/images/Circled Right.png")));
		lblAtras.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtras.setForeground(Color.BLACK);
		lblAtras.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblAtras.setBackground(SystemColor.menu);
		lblAtras.setBounds(0, 34, 53, 43);
		add(lblAtras);

		labelFlechaSiguiente = new JLabel("");
		labelFlechaSiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (labelFlechaSiguiente.isEnabled()) {
					nextPanel(); // se realiza la operacion de asignar el modelo siguiente
					actualizarPanelTablaModality(); // se actualiza la informacion de la tabla siguiente
					actualizarEstadosFlechas(); // se actualiza el estado de las flechas para evitar inconsistencias
					actualizarTextotTitleTable(); // se establece un nuevo texto para el titulo de la tabla de actuado al modelo actual
					actualizarEstadoButtons();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		labelFlechaSiguiente.setIcon(new ImageIcon(PanelCreacionContratoTransporteTransportModality.class.getResource("/images/Right Arrow.png")));
		labelFlechaSiguiente.setFont(new Font("Arial Black", Font.PLAIN, 11));
		labelFlechaSiguiente.setBounds(499, 57, 50, 20);
		add(labelFlechaSiguiente);

		labelFlechaAterior = new JLabel("");
		labelFlechaAterior.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (labelFlechaAterior.isEnabled()) {
					previusPanel(); // realiza la operacion de asignar el modelo anterior
					actualizarPanelTablaModality(); // se actualiza la informacion de la tabla anterior
					actualizarEstadosFlechas(); // se actualiza el estado de las flechas para evitar inconsistencias
					actualizarTextotTitleTable(); // se establece un nuevo texto para el titulo de la tabla de actuado al modelo actual
					actualizarEstadoButtons();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		labelFlechaAterior.setIcon(new ImageIcon(PanelCreacionContratoTransporteTransportModality.class.getResource("/images/Left Arrow.png")));
		labelFlechaAterior.setFont(new Font("Arial Black", Font.PLAIN, 11));
		labelFlechaAterior.setBounds(291, 57, 50, 20);
		add(labelFlechaAterior);




		lblRestoreFilters = new JLabel("Restore Filters");
		lblRestoreFilters.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				restoreFilters();
				panelContenedorTablas.actualizarTablaModalitys(); // se actualiza la informacion de la tabla de las modalidades
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblRestoreFilters.setForeground(SystemColor.textHighlightText);
		lblRestoreFilters.setFont(new Font("Dialog", Font.BOLD, 18));
		lblRestoreFilters.setBounds(67, 52, 137, 30);
		add(lblRestoreFilters);



		lblTitleTable = new JLabel("Hora - KM");
		lblTitleTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleTable.setForeground(SystemColor.textHighlightText);
		lblTitleTable.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblTitleTable.setBounds(371, 52, 111, 30);
		add(lblTitleTable);

		panelBotones = new JPanel();
		panelBotones.setBorder(new MatteBorder(0, 0, 0, 3, (Color) new Color(0, 0, 0)));
		FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelBotones.setBackground(new Color(18, 95, 115));
		panelBotones.setBounds(664, 28, 189, 60);
		add(panelBotones);
		this.addButtons();
		this.inicializarSistemaDePaneles();
	}

	private void inicializarSistemaDePaneles () {
		this.panelContenedorTablas = new PanelCreacionContratoContenedorTablaCostKilometers(PanelCreacionContratoTransporteTransportModality.this);
		add ((Component) this.panelContenedorTablas);
		this.inicializarPilas();
		this.actualizarPanelTablaModality(); // se actualiza la informacion de las tablas del panel
		this.actualizarEstadosFlechas();
		this.actualizarTextotTitleTable();
	}

	private void addButtons () {
		lblAnnadir = new JLabel("");
		lblAnnadir.setIcon(new ImageIcon(PanelCreacionContratoTransporteTransportModality.class.getResource("/images/Plus.png")));
		lblAnnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				if (panelContenedorTablas instanceof PanelCreacionContratoContenedorTablaCostKilometers) {
					FrameGerenteCreacionContratoTransporteAnnadirCostoKilometers frameAddCostKilometers = new FrameGerenteCreacionContratoTransporteAnnadirCostoKilometers(PanelCreacionContratoTransporteTransportModality.this, new CostKilometers());
					frameAddCostKilometers.setVisible(true);
					frameGerenteCreacionContratoTransporte.setEnabled(false); // se inhabilita el frame
				}

				else if (panelContenedorTablas instanceof PanelCreacionContratoContenedorTablaHoursKilometers) {
					FrameGerenteCreacionContratoTransporteAnnadirHorasKilometers frameAddHoursKilometers = new FrameGerenteCreacionContratoTransporteAnnadirHorasKilometers(PanelCreacionContratoTransporteTransportModality.this, new HoursKilometers());
					frameAddHoursKilometers.setVisible(true);
					frameGerenteCreacionContratoTransporte.setEnabled(false); // se inhabilita el frame
				}

				else if (panelContenedorTablas instanceof PanelCreacionContratoContenedorTablaEstablishedRoute) {
					FrameGerenteCreacionContratoTransporteAnnadirRecorridoEstablecido frameAddEstablishedRoute = new FrameGerenteCreacionContratoTransporteAnnadirRecorridoEstablecido(PanelCreacionContratoTransporteTransportModality.this, new EstablishedRoute());
					frameAddEstablishedRoute.setVisible(true);
					frameGerenteCreacionContratoTransporte.setEnabled(false); // se inhabilita el frame
				}

				frameGerenteCreacionContratoTransporte.setEnabled(false); // se inhabilita el frame
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblAnnadir.setOpaque(true);
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(new Color(18, 95, 115));
		lblAnnadir.setBounds(40, 34, 155, 20);
		panelBotones.add(lblAnnadir);

		lblEliminar = new JLabel("");
		lblEliminar.setIcon(new ImageIcon(PanelCreacionContratoTransporteTransportModality.class.getResource("/images/Trash.png")));
		lblEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (lblEliminar.isEnabled()) {
					Eliminar eliminar = new Eliminar(); // se crea el nuevo hilo
					eliminar.start(); // se ejecuta el nuevo hilo
					crearFrameDecisor(); // se crea el frame decisor, donde el usuario dará su confirmación
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		lblUpdate = new JLabel("");
		lblUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (lblUpdate.isEnabled()) {
					if (panelContenedorTablas instanceof PanelCreacionContratoContenedorTablaCostKilometers) {
						FrameGerenteCreacionContratoTransporteAnnadirCostoKilometers frameAddCostKilometers = new FrameGerenteCreacionContratoTransporteAnnadirCostoKilometers(PanelCreacionContratoTransporteTransportModality.this, (CostKilometers) panelContenedorTablas.getTransportModalitySelected());
						frameAddCostKilometers.setVisible(true);
						frameGerenteCreacionContratoTransporte.setEnabled(false); // se inhabilita el frame
					}

					else if (panelContenedorTablas instanceof PanelCreacionContratoContenedorTablaHoursKilometers) {
						FrameGerenteCreacionContratoTransporteAnnadirHorasKilometers frameAddHoursKilometers = new FrameGerenteCreacionContratoTransporteAnnadirHorasKilometers(PanelCreacionContratoTransporteTransportModality.this, (HoursKilometers) panelContenedorTablas.getTransportModalitySelected());
						frameAddHoursKilometers.setVisible(true);
						frameGerenteCreacionContratoTransporte.setEnabled(false); // se inhabilita el frame
					}

					else if (panelContenedorTablas instanceof PanelCreacionContratoContenedorTablaEstablishedRoute) {
						FrameGerenteCreacionContratoTransporteAnnadirRecorridoEstablecido frameAddEstablishedRoute = new FrameGerenteCreacionContratoTransporteAnnadirRecorridoEstablecido(PanelCreacionContratoTransporteTransportModality.this, (EstablishedRoute) panelContenedorTablas.getTransportModalitySelected());
						frameAddEstablishedRoute.setVisible(true);
						frameGerenteCreacionContratoTransporte.setEnabled(false); // se inhabilita el frame
					}

					frameGerenteCreacionContratoTransporte.setEnabled(false); // se inhabilita el frame
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblUpdate.setIcon(new ImageIcon(PanelCreacionContratoTransporteTransportModality.class.getResource("/images/Edit.png")));
		lblUpdate.setOpaque(true);
		lblUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdate.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblUpdate.setBackground(new Color(18, 95, 115));
		panelBotones.add(lblUpdate);
		lblEliminar.setOpaque(true);
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblEliminar.setBackground(new Color(18, 95, 115));
		lblEliminar.setBounds(307, 11, 155, 20);
		panelBotones.add(lblEliminar);
	}


	public FrameGerenteCreacionContratoTransporte getFrameGerenteCreacionContratoTransporte() {
		return frameGerenteCreacionContratoTransporte;
	}



	public void setFrameGerenteCreacionContratoTransporte(
			FrameGerenteCreacionContratoTransporte frameGerenteCreacionContratoTransporte) {
		this.frameGerenteCreacionContratoTransporte = frameGerenteCreacionContratoTransporte;
	}



	public CarrierContract getCarrierContract() {
		return carrierContract;
	}



	public void setCarrierContract(CarrierContract carrierContract) {
		this.carrierContract = carrierContract;
	}


	// Metodos para el cambio de tablas

	private void restoreFilters () {
		this.isRestoreFilters = true; // se indica que va a empezar el proceso de restauracion de filtros
		this.panelContenedorTablas.restoreFilters();
		this.isRestoreFilters = false; // indica que terminó el proceso de restauracion de filtros
	}


	private void actualizarTextotTitleTable () {
		String texto = "";


		if (this.panelContenedorTablas instanceof PanelCreacionContratoContenedorTablaCostKilometers)
			texto = "Cost-KM";

		else if (this.panelContenedorTablas instanceof PanelCreacionContratoContenedorTablaHoursKilometers)
			texto = "Hours-KM";

		else if (this.panelContenedorTablas instanceof PanelCreacionContratoContenedorTablaEstablishedRoute)
			texto = "Route";

		lblTitleTable.setText(texto);


	}

	private void inicializarPilas () {
		this.previusModels = new LinkedList<PanelTransportModalityOperations>();
		this.nextsModels = new LinkedList<PanelTransportModalityOperations>();
		this.nextsModels.push(new PanelCreacionContratoContenedorTablaEstablishedRoute(PanelCreacionContratoTransporteTransportModality.this)); // se añade a la pila de siguientes el panel tipo EstablishedRoute
		this.nextsModels.push(new PanelCreacionContratoContenedorTablaHoursKilometers(PanelCreacionContratoTransporteTransportModality.this)); // se añade a la pila de siguientes el panel tipo HoursKilometers
	}


	private void actualizarEstadosFlechas () {
		if (this.previusModels.isEmpty()) 
			this.labelFlechaAterior.setEnabled(false);
		else
			this.labelFlechaAterior.setEnabled(true);

		if (this.nextsModels.isEmpty())
			this.labelFlechaSiguiente.setEnabled(false);
		else
			this.labelFlechaSiguiente.setEnabled(true);


	}

	private void nextPanel () {
		PanelTransportModalityOperations panel = this.nextsModels.pop(); // se obtiene el panel siguiente
		this.previusModels.push(panelContenedorTablas); // el panel antes asignado se convierte en el modelo anterior
		this.panelContenedorTablas = panel; // se asigna el nuevo panel
		this.panelContenedorTablas.actualizarTablaModalitys(); // se actualiza la informacion de las tablas del panel
		this.cambiaranel((JPanel) this.panelContenedorTablas);
	}

	private void previusPanel () {
		PanelTransportModalityOperations panel = this.previusModels.pop(); // se obtiene el panel anterior
		this.nextsModels.push(panelContenedorTablas); // el panel antes asignado se convierte en el panel siguiente
		this.panelContenedorTablas = panel; // se asigna el nuevo panel
		this.panelContenedorTablas.actualizarTablaModalitys(); // se actualiza la informacion de las tablas del panel
		this.cambiaranel((JPanel) this.panelContenedorTablas);

	}

	private void cambiaranel (JPanel panel) {
		remove(getComponentCount() - 1); // se remueve el ulitmo componenete
		add(panel); // se añade el nuevo panel
		this.repintarPanel(); // se repinta la informacion del panel
	}

	// Fin de Metodos para el cambio de tablas


	// Metodos para la actualizacion de el panel de la tabla 

	public void actualizarPanelTablaModality () {
		this.panelContenedorTablas.actualizarTablaModalitys(); // se actualiza la informacion del panel que contiene a la tabla de las modadlidades seleccionadas
	}

	private void deleteElementesPanelTablaModality ()  {
		try {
			this.panelContenedorTablas.deleteElementsTable(); // se elimina la informacion del panel que contiene a la tabla de las modadlidades seleccionadas
			FramePrincipal.mostarFrameNotificacion("Han sido eliminados correctamente las modalidades seleccionadas");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	// Fin de Metodos para la actualizacion de el panel de la tabla 

	public void actualizarEstadoButtons() {
		if (this.panelContenedorTablas.getTable().getSelectedRowCount() != 0) {
			this.lblEliminar.setEnabled(true);
			this.lblUpdate.setEnabled(true);
		}
		else {
			this.lblEliminar.setEnabled(false);
			this.lblUpdate.setEnabled(false);
		}
	}
}
