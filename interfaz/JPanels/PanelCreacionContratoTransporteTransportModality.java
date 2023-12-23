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
import modelosTablas.ModeloTablaTransportModalityCostKilometers;
import modelosTablas.ModeloTablaTransportModalityEstablishedRoute;
import modelosTablas.ModeloTablaTransportModalityHoursKilometers;

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
	private JLabel lblTitleTable;

	/**
	 * Create the panel.
	 */


	public PanelCreacionContratoTransporteTransportModality(FrameGerenteCreacionContratoTransporte ct) {

		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.frameGerenteCreacionContratoTransporte = ct;
		this.carrierContract = this.frameGerenteCreacionContratoTransporte.getCarrierContract();
		setLayout(null);
		setBackground(new Color(5, 150, 177));
		setBounds(0, 0, 700, 512);
		JPanel panelTable = new JPanel();
		panelTable.setBounds(10, 84, 680, 359);
		add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);

		tableSeasonTransportModality = new JTable();
		tableSeasonTransportModality.setModel(new ModeloTablaTransportModalityCostKilometers());
		scrollPane.setViewportView(tableSeasonTransportModality);

		JLabel lbTansportModality = new JLabel("TRANSPORT MODALITIES");
		lbTansportModality.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lbTansportModality.setBounds(28, 11, 293, 30);
		add(lbTansportModality);

		lblAnnadir = new JLabel("ADD");
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
		lblAnnadir.setBackground(SystemColor.info);
		lblAnnadir.setBounds(535, 32, 155, 20);
		add(lblAnnadir);

		lblEliminar = new JLabel("DELETE");
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
		lblEliminar.setBackground(SystemColor.info);
		lblEliminar.setBounds(535, 53, 155, 20);
		add(lblEliminar);

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
		lblAtras.setIcon(new ImageIcon(PanelGerenteCreacionContratoAlojamientoTemporada.class.getResource("/images/flecha.png")));
		lblAtras.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtras.setForeground(Color.BLACK);
		lblAtras.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblAtras.setBackground(SystemColor.menu);
		lblAtras.setBounds(304, 454, 91, 38);
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
		labelFlechaSiguiente.setIcon(new ImageIcon(PanelCreacionContratoTransporteTransportModality.class.getResource("/images/flecha_derecha.png")));
		labelFlechaSiguiente.setFont(new Font("Arial Black", Font.PLAIN, 11));
		labelFlechaSiguiente.setBounds(327, 53, 99, 20);
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
		labelFlechaAterior.setIcon(new ImageIcon(PanelCreacionContratoTransporteTransportModality.class.getResource("/images/flecha_izquierda.png")));
		labelFlechaAterior.setFont(new Font("Arial Black", Font.PLAIN, 11));
		labelFlechaAterior.setBounds(194, 53, 99, 20);
		add(labelFlechaAterior);

		lblTitleTable = new JLabel("COST OF KILOMETERS");
		lblTitleTable.setFont(new Font("Arial Black", Font.PLAIN, 12));
		lblTitleTable.setBounds(330, 16, 204, 20);
		add(lblTitleTable);

		this.actualizarTablaModalitys();
		this.inicializarPilas();
		this.actualizarEstadosFlechas();
		this.actualizarTextotTitleTable();
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
			texto = "( Cost of Kilometers )";

		else if (this.tableSeasonTransportModality.getModel() instanceof ModeloTablaTransportModalityHoursKilometers)
			texto = "( Hours Kilometers )";

		else if (this.tableSeasonTransportModality.getModel() instanceof ModeloTablaTransportModalityEstablishedRoute)
			texto = "( Established Route )";

		lblTitleTable.setText(texto); // se establece el nuevo texto


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

	private void actualizarTablaModalitysHoursKilometers (ArrayList<HoursKilometers> modalitysHoursKilometers) {
		reiniciarTable(this.tableSeasonTransportModality);

		for (HoursKilometers modality : modalitysHoursKilometers) {
			((ModeloTablaTransportModalityHoursKilometers) this.tableSeasonTransportModality.getModel()).addElement(modality);
		}
	}

	private void actualizarTablaModalitysCostKilometers (ArrayList<CostKilometers> modalitysCostKilometers) {
		reiniciarTable(this.tableSeasonTransportModality);

		for (CostKilometers modality : modalitysCostKilometers) {
			((ModeloTablaTransportModalityCostKilometers) this.tableSeasonTransportModality.getModel()).addElement(modality);
		}
	}

	private void actualizarTablaModalitysEstablishedRoute (ArrayList<EstablishedRoute> modalitysEstablishedRoute) {
		reiniciarTable(this.tableSeasonTransportModality);

		for (EstablishedRoute modality : modalitysEstablishedRoute) {
			((ModeloTablaTransportModalityEstablishedRoute) this.tableSeasonTransportModality.getModel()).addElement(modality);
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
				carrierContract.deleteModalityLogic(((ModeloTablaTransportModalityCostKilometers) tableSeasonTransportModality.getModel()).deleteElement(rows[i] - i)); // se elimina solo la modalidad de la logica del negocio
			else
				carrierContract.deleteModalityLogic(((ModeloTablaTransportModalityCostKilometers) tableSeasonTransportModality.getModel()).deleteElement(rows[i] - i)); // se elimina la modalidad de la logica del negocio y de la base de datos

		}
	}

	private void deleteElementsTableHoursKilometers () throws SQLException {
		int [] rows = this.tableSeasonTransportModality.getSelectedRows();


		for (int i = 0; i < rows.length; i++) {

			if (carrierContract.getId() == -1)
				carrierContract.deleteModalityLogic(((ModeloTablaTransportModalityHoursKilometers) tableSeasonTransportModality.getModel()).deleteElement(rows[i] - i)); // se elimina solo la modalidad de la logica del negocio
			else
				carrierContract.deleteModalityLogic(((ModeloTablaTransportModalityHoursKilometers) tableSeasonTransportModality.getModel()).deleteElement(rows[i] - i)); // se elimina la modalidad de la logica del negocio y de la base de datos

		}
	}

	private void deleteElementsTableEstablishedRoute () throws SQLException {
		int [] rows = this.tableSeasonTransportModality.getSelectedRows();


		for (int i = 0; i < rows.length; i++) {

			if (carrierContract.getId() == -1)
				carrierContract.deleteModalityLogic(((ModeloTablaTransportModalityEstablishedRoute) tableSeasonTransportModality.getModel()).deleteElement(rows[i] - i)); // se elimina solo la modalidad de la logica del negocio
			else
				carrierContract.deleteModalityLogic(((ModeloTablaTransportModalityEstablishedRoute) tableSeasonTransportModality.getModel()).deleteElement(rows[i] - i)); // se elimina la modalidad de la logica del negocio y de la base de datos

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
