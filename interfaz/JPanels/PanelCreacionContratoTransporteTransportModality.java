package JPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import JFrames.FrameGerenteCreacionContratoTransporte;
import JFrames.FrameGerenteCreacionContratoTransporteAnnadirCostoKilometers;
import JFrames.FrameGerenteCreacionContratoTransporteAnnadirHorasKilometers;
import JFrames.FrameGerenteCreacionContratoTransporteAnnadirRecorridoEstablecido;
import logica.CarrierContract;
import logica.CostKilometers;
import logica.EstablishedRoute;
import logica.HoursKilometers;
import logica.Modality;
import modelosTablas.ModeloTablaTransportModalityCostKilometers;
import modelosTablas.ModeloTablaTransportModalityEstablishedRoute;
import modelosTablas.ModeloTablaTransportModalityHoursKilometers;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.FlowLayout;
import javax.swing.border.MatteBorder;

public class PanelCreacionContratoTransporteTransportModality extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableSeasonTransportModality;
	private FrameGerenteCreacionContratoTransporte frameGerenteCreacionContratoTransporte;
	private CarrierContract carrierContract;
	private JLabel lblAnnadir;
	private JLabel lblEliminar;
	private JLabel lblAtras;
	private JLabel labelFlechaAterior;
	private JLabel labelFlechaSiguiente;
	private Deque<DefaultTableModel> previusModels;
	private Deque<DefaultTableModel> nextsModels;
	private JPanel panelContenedorTablas;
	private JLabel lblTitleTable;
	private JPanel panelBotones;
	private JLabel lblNewLabel;
	private JLabel lblMealPlan;
	private JLabel lblMin;
	private JSpinner spinner;
	private JLabel lblCantDaysAccommodation;
	private JLabel lblMax;
	private JSpinner spinner_1;
	private JSpinner spinner_1_1;
	private JLabel lblPrice;
	private JSpinner spinner_2;
	private JLabel lblMax_1;
	private JLabel lblMin_1;
	private JLabel lblMin_2;
	private JSpinner spinner_3;
	private JLabel lblMax_2;
	private JSpinner spinner_4;
	private JLabel lblMin_3;
	private JSpinner spinner_5;
	private JLabel lblMax_3;
	private JSpinner spinner_6;

	/**
	 * Create the panel.
	 */


	public PanelCreacionContratoTransporteTransportModality(FrameGerenteCreacionContratoTransporte ct) {

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
					nextModel(); // se realiza la operacion de asignar el modelo siguiente
					actualizarTablaModalitys(); // se actualiza la informacion de la tabla siguiente
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
		labelFlechaSiguiente.setIcon(new ImageIcon(PanelCreacionContratoTransporteTransportModality.class.getResource("/images/Right Arrow.png")));
		labelFlechaSiguiente.setFont(new Font("Arial Black", Font.PLAIN, 11));
		labelFlechaSiguiente.setBounds(499, 57, 50, 20);
		add(labelFlechaSiguiente);

		labelFlechaAterior = new JLabel("");
		labelFlechaAterior.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (labelFlechaAterior.isEnabled()) {
					previusModel(); // realiza la operacion de asignar el modelo anterior
					actualizarTablaModalitys(); // se actualiza la informacion de la tabla anterior
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
		labelFlechaAterior.setIcon(new ImageIcon(PanelCreacionContratoTransporteTransportModality.class.getResource("/images/Left Arrow.png")));
		labelFlechaAterior.setFont(new Font("Arial Black", Font.PLAIN, 11));
		labelFlechaAterior.setBounds(291, 57, 50, 20);
		add(labelFlechaAterior);
		
		panelContenedorTablas = new JPanel();
		panelContenedorTablas.setBorder(new MatteBorder(0, 3, 3, 3, (Color) new Color(0, 0, 0)));
		panelContenedorTablas.setBackground(new Color(18, 95, 115));
		panelContenedorTablas.setBounds(0, 88, 853, 489);
		add(panelContenedorTablas);

		
		panelContenedorTablas.setLayout(null);
		
		JPanel panelTable = new JPanel();
		panelTable.setBorder(new MatteBorder(0, 3, 3, 3, (Color) new Color(0, 0, 0)));
		panelTable.setBounds(0, 78, 853, 411);
		panelContenedorTablas.add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);

		tableSeasonTransportModality = new JTable();
		tableSeasonTransportModality.setModel(new ModeloTablaTransportModalityCostKilometers());
		scrollPane.setViewportView(tableSeasonTransportModality);
		
		lblNewLabel = new JLabel("Cost Kilometers Going:");
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 13, 216, 27);
		panelContenedorTablas.add(lblNewLabel);
		
		lblMealPlan = new JLabel("Cost Kilometers Lap:");
		lblMealPlan.setForeground(SystemColor.textHighlightText);
		lblMealPlan.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMealPlan.setBounds(244, 13, 200, 27);
		panelContenedorTablas.add(lblMealPlan);
		
		lblMin = new JLabel("Min:");
		lblMin.setForeground(SystemColor.textHighlightText);
		lblMin.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin.setBounds(452, 50, 30, 14);
		panelContenedorTablas.add(lblMin);
		
		spinner = new JSpinner();
		spinner.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinner.setBounds(487, 48, 53, 20);
		panelContenedorTablas.add(spinner);
		
		lblCantDaysAccommodation = new JLabel("Cost Hours wait:");
		lblCantDaysAccommodation.setForeground(SystemColor.textHighlightText);
		lblCantDaysAccommodation.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCantDaysAccommodation.setBounds(487, 11, 150, 30);
		panelContenedorTablas.add(lblCantDaysAccommodation);
		
		lblMax = new JLabel("Max:");
		lblMax.setForeground(SystemColor.textHighlightText);
		lblMax.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax.setBounds(550, 50, 40, 14);
		panelContenedorTablas.add(lblMax);
		
		spinner_1 = new JSpinner();
		spinner_1.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinner_1.setBounds(585, 49, 53, 20);
		panelContenedorTablas.add(spinner_1);
		
		spinner_1_1 = new JSpinner();
		spinner_1_1.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinner_1_1.setBounds(688, 46, 53, 20);
		panelContenedorTablas.add(spinner_1_1);
		
		lblPrice = new JLabel("Plan Price:");
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setForeground(SystemColor.textHighlightText);
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPrice.setBounds(699, 11, 103, 30);
		panelContenedorTablas.add(lblPrice);
		
		spinner_2 = new JSpinner();
		spinner_2.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinner_2.setBounds(790, 46, 53, 20);
		panelContenedorTablas.add(spinner_2);
		
		lblMax_1 = new JLabel("Max:");
		lblMax_1.setForeground(SystemColor.textHighlightText);
		lblMax_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_1.setBounds(751, 46, 40, 14);
		panelContenedorTablas.add(lblMax_1);
		
		lblMin_1 = new JLabel("Min:");
		lblMin_1.setForeground(SystemColor.textHighlightText);
		lblMin_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_1.setBounds(648, 50, 30, 14);
		panelContenedorTablas.add(lblMin_1);
		
		lblMin_2 = new JLabel("Min:");
		lblMin_2.setForeground(SystemColor.textHighlightText);
		lblMin_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_2.setBounds(254, 48, 30, 14);
		panelContenedorTablas.add(lblMin_2);
		
		spinner_3 = new JSpinner();
		spinner_3.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinner_3.setBounds(289, 46, 53, 20);
		panelContenedorTablas.add(spinner_3);
		
		lblMax_2 = new JLabel("Max:");
		lblMax_2.setForeground(SystemColor.textHighlightText);
		lblMax_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_2.setBounds(352, 48, 40, 14);
		panelContenedorTablas.add(lblMax_2);
		
		spinner_4 = new JSpinner();
		spinner_4.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinner_4.setBounds(387, 47, 53, 20);
		panelContenedorTablas.add(spinner_4);
		
		lblMin_3 = new JLabel("Min:");
		lblMin_3.setForeground(SystemColor.textHighlightText);
		lblMin_3.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_3.setBounds(20, 48, 30, 14);
		panelContenedorTablas.add(lblMin_3);
		
		spinner_5 = new JSpinner();
		spinner_5.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinner_5.setBounds(55, 46, 53, 20);
		panelContenedorTablas.add(spinner_5);
		
		lblMax_3 = new JLabel("Max:");
		lblMax_3.setForeground(SystemColor.textHighlightText);
		lblMax_3.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_3.setBounds(118, 48, 40, 14);
		panelContenedorTablas.add(lblMax_3);
		
		spinner_6 = new JSpinner();
		spinner_6.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinner_6.setBounds(153, 47, 53, 20);
		panelContenedorTablas.add(spinner_6);
		
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
		this.actualizarTablaModalitys();
		this.inicializarPilas();
		this.actualizarEstadosFlechas();
		this.actualizarTextotTitleTable();
	}
	
	private void addButtons () {
		lblAnnadir = new JLabel("");
		lblAnnadir.setIcon(new ImageIcon(PanelCreacionContratoTransporteTransportModality.class.getResource("/images/Plus.png")));
		lblAnnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				if (tableSeasonTransportModality.getModel() instanceof ModeloTablaTransportModalityCostKilometers) {
					FrameGerenteCreacionContratoTransporteAnnadirCostoKilometers frameAddCostKilometers = new FrameGerenteCreacionContratoTransporteAnnadirCostoKilometers(PanelCreacionContratoTransporteTransportModality.this);
					frameAddCostKilometers.setVisible(true);
					frameGerenteCreacionContratoTransporte.setEnabled(false); // se inhabilita el frame
				}

				else if (tableSeasonTransportModality.getModel() instanceof ModeloTablaTransportModalityHoursKilometers) {
					FrameGerenteCreacionContratoTransporteAnnadirHorasKilometers frameAddHoursKilometers = new FrameGerenteCreacionContratoTransporteAnnadirHorasKilometers(PanelCreacionContratoTransporteTransportModality.this);
					frameAddHoursKilometers.setVisible(true);
					frameGerenteCreacionContratoTransporte.setEnabled(false); // se inhabilita el frame
				}

				else if (tableSeasonTransportModality.getModel() instanceof ModeloTablaTransportModalityEstablishedRoute) {
					FrameGerenteCreacionContratoTransporteAnnadirRecorridoEstablecido frameAddEstablishedRoute = new FrameGerenteCreacionContratoTransporteAnnadirRecorridoEstablecido(PanelCreacionContratoTransporteTransportModality.this);
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
				try {
					deleteElementsTable(); // se eliminan las temporadas seleccionadas
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


	private void actualizarTextotTitleTable () {
		String texto = "";


		if (this.tableSeasonTransportModality.getModel() instanceof ModeloTablaTransportModalityCostKilometers)
			texto = "Cost-KM";

		else if (this.tableSeasonTransportModality.getModel() instanceof ModeloTablaTransportModalityHoursKilometers)
			texto = "Hours-KM";

		else if (this.tableSeasonTransportModality.getModel() instanceof ModeloTablaTransportModalityEstablishedRoute)
			texto = "Route";
		
		lblTitleTable.setText(texto);


	}

	private void inicializarPilas () {
		this.previusModels = new LinkedList<DefaultTableModel>();
		this.nextsModels = new LinkedList<DefaultTableModel>();
		this.nextsModels.push(new ModeloTablaTransportModalityEstablishedRoute()); // se añade a la pila de siguientes un modelo tipo EstablishedRoute
		this.nextsModels.push(new ModeloTablaTransportModalityHoursKilometers()); // se añade a la pila de siguientes un modelo tipo HoursKilometers

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

	private void nextModel () {
		DefaultTableModel model = this.nextsModels.pop(); // se obtiene el modelo siguiente
		this.previusModels.push((DefaultTableModel) tableSeasonTransportModality.getModel()); // el modelo antes asignado se convierte en el modelo anterior
		this.tableSeasonTransportModality.setModel(model); // se asigna el nuevo modelo a la tabla	
	}

	private void previusModel () {
		DefaultTableModel model = this.previusModels.pop(); // se obtiene el modelo anterior
		this.nextsModels.push((DefaultTableModel) tableSeasonTransportModality.getModel()); // el modelo antes asignado se convierte en el modelo siguiente
		this.tableSeasonTransportModality.setModel(model); // se asigna el nuevo modelo a la tabla

	}

	// Fin de Metodos para el cambio de tablas




	public void actualizarTablaModalitys(){
		reiniciarTable(this.tableSeasonTransportModality);

		if (this.tableSeasonTransportModality.getModel() instanceof ModeloTablaTransportModalityCostKilometers)
			this.actualizarTablaModalitysCostKilometers(this.carrierContract.getCostKilometers()); // se añaden las modalidades tipo cost kilometers

		else if (this.tableSeasonTransportModality.getModel() instanceof ModeloTablaTransportModalityHoursKilometers)
			this.actualizarTablaModalitysHoursKilometers(this.carrierContract.getHoursKilometers()); // se añaden las modalidades tipo hours kilometers

		else if (this.tableSeasonTransportModality.getModel() instanceof ModeloTablaTransportModalityEstablishedRoute)
			this.actualizarTablaModalitysEstablishedRoute(this.carrierContract.getEstablishedRoute()); // se añaden las modalidades tipo establishedRoute

	}

	private void actualizarTablaModalitysHoursKilometers (ArrayList<Modality> modalitysHoursKilometers) {
		reiniciarTable(this.tableSeasonTransportModality);

		for (Modality modality : modalitysHoursKilometers) {
			((ModeloTablaTransportModalityHoursKilometers) this.tableSeasonTransportModality.getModel()).addElement((HoursKilometers) modality);
		}
	}

	private void actualizarTablaModalitysCostKilometers (ArrayList<Modality> modalitysCostKilometers) {
		reiniciarTable(this.tableSeasonTransportModality);

		for (Modality modality : modalitysCostKilometers) {
			((ModeloTablaTransportModalityCostKilometers) this.tableSeasonTransportModality.getModel()).addElement((CostKilometers) modality);
		}
	}

	private void actualizarTablaModalitysEstablishedRoute (ArrayList<Modality> modalitysEstablishedRoute) {
		reiniciarTable(this.tableSeasonTransportModality);

		for (Modality modality : modalitysEstablishedRoute) {
			((ModeloTablaTransportModalityEstablishedRoute) this.tableSeasonTransportModality.getModel()).addElement((EstablishedRoute) modality);
		}
	}

	private void deleteElementsTable () throws SQLException {

		if (this.tableSeasonTransportModality.getModel() instanceof ModeloTablaTransportModalityCostKilometers)
			this.deleteElementsTableCostKilometers(); // se eliminan las modalidades tipo cost kilometers

		else if (this.tableSeasonTransportModality.getModel() instanceof ModeloTablaTransportModalityHoursKilometers)
			this.deleteElementsTableHoursKilometers(); // se eliminan las modalidades tipo hours kilometers

		else if (this.tableSeasonTransportModality.getModel() instanceof ModeloTablaTransportModalityEstablishedRoute)
			this.deleteElementsTableEstablishedRoute(); // se eliminan las modalidades tipo establishedRoute


		this.actualizarTablaModalitys();
	}

	private void deleteElementsTableCostKilometers () throws SQLException {
		int [] rows = this.tableSeasonTransportModality.getSelectedRows();


		for (int i = 0; i < rows.length; i++) {

			if (carrierContract.getId() == -1)
				carrierContract.deleteModalityLogic(((ModeloTablaTransportModalityCostKilometers) tableSeasonTransportModality.getModel()).getElement(rows[i])); // se elimina solo la modalidad de la logica del negocio
			else
				carrierContract.deleteModalityLogic(((ModeloTablaTransportModalityCostKilometers) tableSeasonTransportModality.getModel()).getElement(rows[i])); // se elimina la modalidad de la logica del negocio y de la base de datos

		}
	}

	private void deleteElementsTableHoursKilometers () throws SQLException {
		int [] rows = this.tableSeasonTransportModality.getSelectedRows();


		for (int i = 0; i < rows.length; i++) {

			if (carrierContract.getId() == -1)
				carrierContract.deleteModalityLogic(((ModeloTablaTransportModalityHoursKilometers) tableSeasonTransportModality.getModel()).getElement(rows[i])); // se elimina solo la modalidad de la logica del negocio
			else
				carrierContract.deleteModalityLogic(((ModeloTablaTransportModalityHoursKilometers) tableSeasonTransportModality.getModel()).getElement(rows[i])); // se elimina la modalidad de la logica del negocio y de la base de datos

		}
	}

	private void deleteElementsTableEstablishedRoute () throws SQLException {
		int [] rows = this.tableSeasonTransportModality.getSelectedRows();


		for (int i = 0; i < rows.length; i++) {

			if (carrierContract.getId() == -1)
				carrierContract.deleteModalityLogic(((ModeloTablaTransportModalityEstablishedRoute) tableSeasonTransportModality.getModel()).getElement(rows[i])); // se elimina solo la modalidad de la logica del negocio
			else
				carrierContract.deleteModalityLogic(((ModeloTablaTransportModalityEstablishedRoute) tableSeasonTransportModality.getModel()).getElement(rows[i])); // se elimina la modalidad de la logica del negocio y de la base de datos

		}
	}

	private void reiniciarTable(JTable table){

		if (this.tableSeasonTransportModality.getModel() instanceof ModeloTablaTransportModalityCostKilometers)
			this.reiniciarTableCostKilometers(table);
		
		else if (this.tableSeasonTransportModality.getModel() instanceof ModeloTablaTransportModalityHoursKilometers)
			this.reiniciarTableHoursKilometers(table);
		
		else if (this.tableSeasonTransportModality.getModel() instanceof ModeloTablaTransportModalityEstablishedRoute)
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
}
