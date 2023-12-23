package JFrames;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import logica.Controller;
import logica.CostKilometers;
import logica.EstablishedRoute;
import logica.HoursKilometers;
import logica.Modality;
import logica.TouristPackage;
import modelosTablas.ModeloTablaTransportModalityCostKilometers;
import modelosTablas.ModeloTablaTransportModalityEstablishedRoute;
import modelosTablas.ModeloTablaTransportModalityHoursKilometers;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

public class FrameDisennadorPaqueteTuristicoModalidadTransporteAnnadir extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableAvailableTransportation;
	private JTable tableAssignedTransports;
	private int mouseX, mouseY;
	private FrameInformacionPaquete frameInformacionPaquete;
	private TouristPackage touristPackage;
	private JLabel lblAnnadir;
	private JLabel lblDenegar;
	private JLabel lblConfirm;
	private JLabel labelFlechaAterior;
	private JLabel labelFlechaSiguiente;
	private JLabel lblX;
	private JPanel panel;
	private Deque<DefaultTableModel> previusModelsAssignedTransports;
	private Deque<DefaultTableModel> nextsModelsAssignedTransports;
	private Deque<DefaultTableModel> previusModelsAviableTransports;
	private Deque<DefaultTableModel> nextsModelsAviableTransports;
	private JLabel lblTitleTable;


	public FrameDisennadorPaqueteTuristicoModalidadTransporteAnnadir(FrameInformacionPaquete f) {
		this.frameInformacionPaquete = f;
		this.touristPackage = this.frameInformacionPaquete.getTouristPackage();
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x= e.getXOnScreen();
				int y= e.getYOnScreen();

				setLocation(x - mouseX , y - mouseY );
			}
		});
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				tableAssignedTransports.clearSelection();
				tableAvailableTransportation.clearSelection();

			}
		});

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setLayout(null);
		panel.setBackground(new Color(5, 150, 177));
		panel.setBackground(new Color(5, 150, 177));
		panel.setBounds(0, 0, 600, 360);
		contentPane.add(panel);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cerrarFrame(); // se cierra el frame actual
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblX.setForeground(SystemColor.red);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setForeground(SystemColor.black);
			}
		});
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(562, 0, 38, 38);
		panel.add(lblX);

		JLabel lblAvailableTransportation = new JLabel("AVAILABLE TRANSPORTATION");
		lblAvailableTransportation.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAvailableTransportation.setBounds(25, 11, 275, 19);
		panel.add(lblAvailableTransportation);

		lblAnnadir = new JLabel("ASIGN");
		lblAnnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					asing();
					actualizarEstadosBotones();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
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
		lblAnnadir.setBackground(SystemColor.info);
		lblAnnadir.setBounds(435, 40, 155, 20);
		panel.add(lblAnnadir);

		JLabel lblAssignedTransports = new JLabel("ASSIGNED TRANSPORTS");
		lblAssignedTransports.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAssignedTransports.setBounds(25, 173, 275, 19);
		panel.add(lblAssignedTransports);

		lblDenegar = new JLabel("DENY");
		lblDenegar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					deny();
					actualizarEstadosBotones();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		lblDenegar.setOpaque(true);
		lblDenegar.setHorizontalAlignment(SwingConstants.CENTER);
		lblDenegar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDenegar.setBackground(SystemColor.info);
		lblDenegar.setBounds(435, 183, 155, 20);
		panel.add(lblDenegar);

		lblConfirm = new JLabel("CERRAR");
		lblConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cerrarFrame(); // se cierra el frame actual
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblConfirm.setOpaque(true);
		lblConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirm.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblConfirm.setBackground(SystemColor.info);
		lblConfirm.setBounds(182, 311, 235, 35);
		panel.add(lblConfirm);

		labelFlechaAterior = new JLabel("");
		labelFlechaAterior.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (labelFlechaAterior.isEnabled()) {
					previusModel();
					actualizarEstadosFlechas();
					actualizarTablas();
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
		labelFlechaAterior.setIcon(new ImageIcon(FrameDisennadorPaqueteTuristicoModalidadTransporteAnnadir.class.getResource("/images/flecha_izquierda.png")));
		labelFlechaAterior.setFont(new Font("Arial Black", Font.PLAIN, 11));
		labelFlechaAterior.setBounds(123, 44, 99, 12);
		panel.add(labelFlechaAterior);

		labelFlechaSiguiente = new JLabel("");
		labelFlechaSiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (labelFlechaSiguiente.isEnabled()) {
					nextModel();
					actualizarEstadosFlechas();
					actualizarTablas();
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
		labelFlechaSiguiente.setIcon(new ImageIcon(FrameDisennadorPaqueteTuristicoModalidadTransporteAnnadir.class.getResource("/images/flecha_derecha.png")));
		labelFlechaSiguiente.setFont(new Font("Arial Black", Font.PLAIN, 11));
		labelFlechaSiguiente.setBounds(257, 44, 99, 12);
		panel.add(labelFlechaSiguiente);

		JPanel panelAvailableTransportation = new JPanel();
		panelAvailableTransportation.setBounds(10, 71, 580, 86);
		panel.add(panelAvailableTransportation);
		panelAvailableTransportation.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelAvailableTransportation.add(scrollPane, BorderLayout.CENTER);

		tableAvailableTransportation = new JTable();
		tableAvailableTransportation.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadosBotones();
			}
		});
		tableAvailableTransportation.setModel(new ModeloTablaTransportModalityCostKilometers());
		scrollPane.setViewportView(tableAvailableTransportation);

		JPanel panelAssignedTransports = new JPanel();
		panelAssignedTransports.setOpaque(false);
		panelAssignedTransports.setBounds(10, 214, 580, 86);
		panel.add(panelAssignedTransports);
		panelAssignedTransports.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panelAssignedTransports.add(scrollPane_1, BorderLayout.CENTER);

		tableAssignedTransports = new JTable();
		tableAssignedTransports.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadosBotones();
			}
		});
		tableAssignedTransports.setModel(new ModeloTablaTransportModalityCostKilometers());
		scrollPane_1.setViewportView(tableAssignedTransports);

		lblTitleTable = new JLabel("( Hours Kilometers ) ");
		lblTitleTable.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblTitleTable.setBounds(308, 5, 173, 30);
		panel.add(lblTitleTable);

		this.actualizarTablas(); // se actualiza la informacion de las tablas
		this.actualizarEstadosBotones();
		this.inicializarPilas();
		this.actualizarEstadosFlechas();
		this.actualizarTextotTitleTable();
	}

	private void actualizarTablas () {
		this.actualizarTablaAssignedTransportModality ();
		this.actualizarTablaAviableTransporModality ();
	}

	private void actualizarTablaAssignedTransportModality () {
		if (this.tableAssignedTransports.getModel() instanceof ModeloTablaTransportModalityCostKilometers)
			this.actualizarTablaAssignedCostKilometers();

		else if (this.tableAssignedTransports.getModel() instanceof ModeloTablaTransportModalityHoursKilometers)
			this.actualizarTablaAssignedHoursKilometers();

		else if (this.tableAssignedTransports.getModel() instanceof ModeloTablaTransportModalityEstablishedRoute)
			this.actualizarTablaAssignedEstablishedRoute();
	}

	private void actualizarTablaAviableTransporModality () {
		if (this.tableAvailableTransportation.getModel() instanceof ModeloTablaTransportModalityCostKilometers)
			this.actualizarTablaAviableCostKilometers();

		else if (this.tableAvailableTransportation.getModel() instanceof ModeloTablaTransportModalityHoursKilometers)
			this.actualizarTablaAviableHoursKilometers();

		else if (this.tableAvailableTransportation.getModel() instanceof ModeloTablaTransportModalityEstablishedRoute)
			this.actualizarTablaAviableEstablishedRoute();

	}

	// Metodos de acutalizacion del modeloCostKilometers

	// Metodos de actualizacion del modelo  tableAviavleTransporModality
	private void actualizarTablaAviableCostKilometers () {	
		this.actualizarTablaAviableCostKilometers(Controller.getInstancie().getTouristAgency().getTransportModalityCostKilometers());  // se obtienen todas las modalidades del alojamiento de la agencia turistica
	}


	private void actualizarTablaAviableCostKilometers(ArrayList<Modality> modalitys){
		reiniciarTable(this.tableAvailableTransportation);


		for (Modality mod : modalitys) {
			if (!touristPackage.isContainsModality(Modality.costKilometers, mod))
				((ModeloTablaTransportModalityCostKilometers) tableAvailableTransportation.getModel()).addElement((CostKilometers) mod); 
		}
	}

	// Fin de Metodos de actualizacion de la tabla  tableAviavleTransporModality


	private void actualizarTablaAssignedCostKilometers () {	
		this.actualizarTablaAssignedCostKilometers(touristPackage.getModalitys(Modality.costKilometers)); // se obtienen todas las modalidades de transporte de tipo costKilometers pertenecientes al paquete turistico
	}

	// Metodos de actualizacion de la tabla  tableAssignedTransporModality
	private void actualizarTablaAssignedCostKilometers(ArrayList<Modality> modalitys){
		reiniciarTable(this.tableAssignedTransports);


		for (Modality mod : modalitys) {
			((ModeloTablaTransportModalityCostKilometers) tableAssignedTransports.getModel()).addElement((CostKilometers) mod);
		}
	}

	// Fin de Metodos de actualizacion de la tabla  tableAssignedTransporModality

	// Fin de Metodos de acutalizacion del modeloCostKilometers

	// Metodos de acutalizacion del modeloHoursKilometers

	// Metodos de actualizacion del modelo  tableAviavleTransporModality
	private void actualizarTablaAviableHoursKilometers () {	
		this.actualizarTablaAviableHoursKilometers(Controller.getInstancie().getTouristAgency().getTransportModalityHoursKilometers());  // se obtienen todas las modalidades de transporte de tipo hours de la agencia turistica
	}


	private void actualizarTablaAviableHoursKilometers(ArrayList<Modality> modalitys){
		reiniciarTable(this.tableAvailableTransportation);


		for (Modality mod : modalitys) {
			if (!touristPackage.isContainsModality(Modality.hoursKilometers, mod))
				((ModeloTablaTransportModalityHoursKilometers) tableAvailableTransportation.getModel()).addElement((HoursKilometers) mod); 
		}
	}

	// Fin de Metodos de actualizacion de la tabla  tableAviavleTransporModality


	private void actualizarTablaAssignedHoursKilometers () {	
		this.actualizarTablaAssignedHoursKilometers(touristPackage.getModalitys(Modality.hoursKilometers)); // se obtienen todas las modalidades de transporte tipo hours pertenecientes al paquete turistico
	}

	// Metodos de actualizacion de la tabla  tableAssignedTransporModality
	private void actualizarTablaAssignedHoursKilometers(ArrayList<Modality> modalitys){
		reiniciarTable(this.tableAssignedTransports);


		for (Modality mod : modalitys) {
			((ModeloTablaTransportModalityHoursKilometers) tableAssignedTransports.getModel()).addElement((HoursKilometers) mod);
		}
	}

	// Fin de Metodos de actualizacion de la tabla  tableAssignedTransporModality

	// Fin de Metodos de acutalizacion del modeloHoursKilometers


	// Metodos de acutalizacion del modeloEstablishedRoute

	// Metodos de actualizacion del modelo  tableAviavleTransporModality
	private void actualizarTablaAviableEstablishedRoute () {	
		this.actualizarTablaAviableEstablishedRoute(Controller.getInstancie().getTouristAgency().getTransportModalityEstablishedRoute());  // se obtienen todas las modalidades de transporte de tipo hours de la agencia turistica
	}


	private void actualizarTablaAviableEstablishedRoute(ArrayList<Modality> modalitys){
		reiniciarTable(this.tableAvailableTransportation);


		for (Modality mod : modalitys) {
			if (!touristPackage.isContainsModality(Modality.establishedRoute, mod))
				((ModeloTablaTransportModalityEstablishedRoute) tableAvailableTransportation.getModel()).addElement((EstablishedRoute) mod); 
		}
	}

	// Fin de Metodos de actualizacion de la tabla  tableAviavleTransporModality


	private void actualizarTablaAssignedEstablishedRoute () {	
		this.actualizarTablaAssignedEstablishedRoute(touristPackage.getModalitys(Modality.establishedRoute)); // se obtienen todas las modalidades de transporte tipo recorridos establecidos pertenecientes al paquete turistico
	}

	// Metodos de actualizacion de la tabla  tableAssignedTransporModality
	private void actualizarTablaAssignedEstablishedRoute(ArrayList<Modality> modalitys){
		reiniciarTable(this.tableAssignedTransports);


		for (Modality mod : modalitys) {
			((ModeloTablaTransportModalityEstablishedRoute) tableAssignedTransports.getModel()).addElement((EstablishedRoute) mod);
		}
	}

	// Fin de Metodos de actualizacion de la tabla  tableAssignedTransporModality

	// Fin de Metodos de acutalizacion del modeloEstablishedRoute


	
	private void reiniciarTable(JTable table){

		if (table.getModel() instanceof ModeloTablaTransportModalityCostKilometers)
			this.reiniciarTableCostKilometers(table);
		
		else if (table.getModel() instanceof ModeloTablaTransportModalityHoursKilometers)
			this.reiniciarTableHoursKilometers(table);
		
		else if (table.getModel() instanceof ModeloTablaTransportModalityEstablishedRoute)
			this.reiniciarTableEstablishedRoute(table);
	}

	private void reiniciarTableHoursKilometers (JTable table) {
		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaTransportModalityHoursKilometers) table.getModel()).deleteElement(i);
		}
	}

	private void reiniciarTableCostKilometers (JTable table) {
		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaTransportModalityCostKilometers) table.getModel()).deleteElement(i);
		}
	}
	
	private void reiniciarTableEstablishedRoute (JTable table) {
		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaTransportModalityEstablishedRoute) table.getModel()).deleteElement(i);
		}
	}


	// Operaciones

	private void asing () throws SQLException {
		if (this.tableAvailableTransportation.getModel() instanceof ModeloTablaTransportModalityCostKilometers)
			this.asignCostKilometers();

		else if (this.tableAvailableTransportation.getModel() instanceof ModeloTablaTransportModalityHoursKilometers)
			this.asignHoursKilometers();

		else if (this.tableAvailableTransportation.getModel() instanceof ModeloTablaTransportModalityEstablishedRoute)
			this.asignEstablishedRoute();
	}

	private void deny () throws SQLException {
		if (this.tableAssignedTransports.getModel() instanceof ModeloTablaTransportModalityCostKilometers)
			this.denyCostKilometers();

		else if (this.tableAssignedTransports.getModel() instanceof ModeloTablaTransportModalityHoursKilometers)
			this.denyHoursKilometers();

		else if (this.tableAssignedTransports.getModel() instanceof ModeloTablaTransportModalityEstablishedRoute)
			this.denyEstablishedRoute();
	}

	// Operaciones Modelo CostKilometers

	private void asignCostKilometers () throws SQLException  { // Metodo para asignar las modalidades tipo costKilometers seleccionadas al paquete
		int [] rows = this.tableAvailableTransportation.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.touristPackage.getId() != -1) 
				this.touristPackage.addModality(((ModeloTablaTransportModalityCostKilometers) this.tableAvailableTransportation.getModel()).getElement(rows[i])); // se asigna a las base de datos y a la logica del negocio
			else
				this.touristPackage.addModalityLogic(((ModeloTablaTransportModalityCostKilometers) this.tableAvailableTransportation.getModel()).getElement(rows[i])); // se asigna solo en la logica del negocio
		}

		this.actualizarTablas(); // se actualiza la informacion de las dos tablas
	}



	private void denyCostKilometers () throws SQLException { // Metodo para desasignar las modalidades tipo costKilometers seleccionadas del paquete
		int [] rows = this.tableAssignedTransports.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.touristPackage.getId() != -1) 
				this.touristPackage.deleteModality(((ModeloTablaTransportModalityCostKilometers) this.tableAssignedTransports.getModel()).getElement(rows[i])); // se elimina a las base de datos y a la logica del negocio
			else
				this.touristPackage.deleteModalityLogic(((ModeloTablaTransportModalityCostKilometers) this.tableAssignedTransports.getModel()).getElement(rows[i])); // se elimina solo en la logica del negocio
		}

		this.actualizarTablas(); // se actualiza la informacion de las dos tablas
	}

	// Fin de Operaciones Modelo CostKilometers

	// Operaciones Modelo HoursKilometers

	private void asignHoursKilometers () throws SQLException  { // Metodo para asignar las modalidades tipo costKilometers seleccionadas al paquete
		int [] rows = this.tableAvailableTransportation.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.touristPackage.getId() != -1) 
				this.touristPackage.addModality(((ModeloTablaTransportModalityHoursKilometers) this.tableAvailableTransportation.getModel()).getElement(rows[i])); // se asigna a las base de datos y a la logica del negocio
			else
				this.touristPackage.addModalityLogic(((ModeloTablaTransportModalityHoursKilometers) this.tableAvailableTransportation.getModel()).getElement(rows[i])); // se asigna solo en la logica del negocio
		}

		this.actualizarTablas(); // se actualiza la informacion de las dos tablas
	}


	private void denyHoursKilometers () throws SQLException { // Metodo para desasignar las modalidades tipo costKilometers seleccionadas del paquete
		int [] rows = this.tableAssignedTransports.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.touristPackage.getId() != -1) 
				this.touristPackage.deleteModality(((ModeloTablaTransportModalityHoursKilometers) this.tableAssignedTransports.getModel()).getElement(rows[i])); // se elimina a las base de datos y a la logica del negocio
			else
				this.touristPackage.deleteModalityLogic(((ModeloTablaTransportModalityHoursKilometers) this.tableAssignedTransports.getModel()).getElement(rows[i])); // se elimina solo en la logica del negocio
		}

		this.actualizarTablas(); // se actualiza la informacion de las dos tablas
	}

	// Fin de Operaciones Modelo HoursKilometers

	// Operaciones Modelo EstablishedRoute

	private void asignEstablishedRoute () throws SQLException  { // Metodo para asignar las modalidades tipo costKilometers seleccionadas al paquete
		int [] rows = this.tableAvailableTransportation.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.touristPackage.getId() != -1) 
				this.touristPackage.addModality(((ModeloTablaTransportModalityEstablishedRoute) this.tableAvailableTransportation.getModel()).getElement(rows[i])); // se asigna a las base de datos y a la logica del negocio
			else
				this.touristPackage.addModalityLogic(((ModeloTablaTransportModalityEstablishedRoute) this.tableAvailableTransportation.getModel()).getElement(rows[i])); // se asigna solo en la logica del negocio
		}

		this.actualizarTablas(); // se actualiza la informacion de las dos tablas
	}


	private void denyEstablishedRoute () throws SQLException { // Metodo para desasignar las modalidades tipo costKilometers seleccionadas del paquete
		int [] rows = this.tableAssignedTransports.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.touristPackage.getId() != -1) 
				this.touristPackage.deleteModality(((ModeloTablaTransportModalityEstablishedRoute) this.tableAssignedTransports.getModel()).getElement(rows[i])); // se elimina a las base de datos y a la logica del negocio
			else
				this.touristPackage.deleteModalityLogic(((ModeloTablaTransportModalityEstablishedRoute) this.tableAssignedTransports.getModel()).getElement(rows[i])); // se elimina solo en la logica del negocio
		}

		this.actualizarTablas(); // se actualiza la informacion de las dos tablas
	}

	// Fin de Operaciones Modelo EstablishedRoute

	// Fin de Operaciones

	// Operaciones Flechas

	private void actualizarTextotTitleTable () {
		String texto = "";


		if (this.tableAssignedTransports.getModel() instanceof ModeloTablaTransportModalityCostKilometers)
			texto = "( Cost of Kilometers )";

		else if (this.tableAssignedTransports.getModel() instanceof ModeloTablaTransportModalityHoursKilometers)
			texto = "( Hours Kilometers )";

		else if (this.tableAssignedTransports.getModel() instanceof ModeloTablaTransportModalityEstablishedRoute)
			texto = "( Established Route )";

		lblTitleTable.setText(texto); // se establece el nuevo texto


	}

	private void inicializarPilas () {
		this.inicializarPilasAssignedTransports();
		this.inicializarPilasAviableTransports();
	}

	private void inicializarPilasAssignedTransports () {
		this.previusModelsAssignedTransports = new LinkedList<DefaultTableModel>();
		this.nextsModelsAssignedTransports = new LinkedList<DefaultTableModel>();
		this.nextsModelsAssignedTransports.push(new ModeloTablaTransportModalityEstablishedRoute()); // se añade a la pila de siguientes un modelo tipo EstablishedRoute para la tabla assing
		this.nextsModelsAssignedTransports.push(new ModeloTablaTransportModalityHoursKilometers()); // se añade a la pila de siguientes un modelo tipo HoursKilometers para la tabla aviable

	}

	private void inicializarPilasAviableTransports () {
		this.previusModelsAviableTransports = new LinkedList<DefaultTableModel>();
		this.nextsModelsAviableTransports = new LinkedList<DefaultTableModel>();
		this.nextsModelsAviableTransports.push(new ModeloTablaTransportModalityEstablishedRoute()); // se añade a la pila de siguientes un modelo tipo EstablishedRoute para la tabla assing
		this.nextsModelsAviableTransports.push(new ModeloTablaTransportModalityHoursKilometers()); // se añade a la pila de siguientes un modelo tipo HoursKilometers para la tabla aviable
	}


	private void actualizarEstadosFlechas () {
		if (this.previusModelsAssignedTransports.isEmpty()) 
			this.labelFlechaAterior.setEnabled(false);
		else
			this.labelFlechaAterior.setEnabled(true);

		if (this.nextsModelsAssignedTransports.isEmpty())
			this.labelFlechaSiguiente.setEnabled(false);
		else
			this.labelFlechaSiguiente.setEnabled(true);

	}

	private void nextModel () { // Para la flecha Next
		this.nextModelAssignedTransports();
		this.nextModelAviableTransports();
	}

	private void previusModel () { // Para la flecha previus
		this.previusModelAssignedTransports();
		this.previusModelAviableTransports();
	}

	// Metodos Pila para la Tabla Assigned

	private void nextModelAssignedTransports () {
		DefaultTableModel model = this.nextsModelsAssignedTransports.pop(); // se obtiene el modelo siguiente
		this.previusModelsAssignedTransports.push((DefaultTableModel) tableAssignedTransports.getModel()); // el modelo antes asignado se convierte en el modelo anterior
		this.tableAssignedTransports.setModel(model); // se asigna el nuevo modelo a la tabla	
	}

	private void previusModelAssignedTransports () {
		DefaultTableModel model = this.previusModelsAssignedTransports.pop(); // se obtiene el modelo anterior
		this.nextsModelsAssignedTransports.push((DefaultTableModel) tableAssignedTransports.getModel()); // el modelo antes asignado se convierte en el modelo siguiente
		this.tableAssignedTransports.setModel(model); // se asigna el nuevo modelo a la tabla

	}

	// Fin de Metodos Pila para la Tabla Assigned

	// Metodos Pila para la Tabla Aviable
	private void nextModelAviableTransports () {
		DefaultTableModel model = this.nextsModelsAviableTransports.pop(); // se obtiene el modelo siguiente
		this.previusModelsAviableTransports.push((DefaultTableModel) tableAvailableTransportation.getModel()); // el modelo antes asignado se convierte en el modelo anterior
		this.tableAvailableTransportation.setModel(model); // se asigna el nuevo modelo a la tabla	
	}

	private void previusModelAviableTransports () {
		DefaultTableModel model = this.previusModelsAviableTransports.pop(); // se obtiene el modelo anterior
		this.nextsModelsAviableTransports.push((DefaultTableModel) tableAvailableTransportation.getModel()); // el modelo antes asignado se convierte en el modelo siguiente
		this.tableAvailableTransportation.setModel(model); // se asigna el nuevo modelo a la tabla

	}

	// Fin de Metodos Pila para la Tabla Aviable


	// Fin de Operaciones Flechas



	private void cerrarFrame () {
		frameInformacionPaquete.setEnabled(true);
		dispose();
	}

	private void actualizarEstadosBotones () {
		this.estadoLblAsign();
		this.estadoLblDeny();
	}

	private void estadoLblAsign () {
		if (tableAvailableTransportation.getSelectedRowCount() != 0)
			lblAnnadir.setEnabled(true);
		else
			lblAnnadir.setEnabled(false);
	}

	private void estadoLblDeny () {
		if (tableAssignedTransports.getSelectedRowCount() != 0)
			lblDenegar.setEnabled(true);
		else
			lblDenegar.setEnabled(false);
	}

}
