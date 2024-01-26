package JPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import logica.Controller;
import logica.EstablishedRoute;
import logica.Modality;
import logica.TouristPackage;
import modelosTablas.ModeloTablaTransportModalityCostKilometers;
import modelosTablas.ModeloTablaTransportModalityEstablishedRoute;
import utils.Operations;

import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;

public class PanelPackageDisignerContenedorEstablishedRoute extends JPanel implements PanelTransportModalityTouristDesignerOperations {

	private static final long serialVersionUID = 1L;
	private JTable tableEstablishedRouteAvaible;
	private JTable tableEstablishedRouteAssigned;
	private JLabel lblAnnadir;
	private JLabel lblDenegar;
	private JSpinner spinnerCostGoingMinAvaible;
	private JSpinner spinnerCostGoingMaxAvaible;
	private JSpinner spinnerCostGoingMinAssigned;
	private JSpinner spinnerCostLapMinAvaible;
	private JSpinner spinnerCostLapMaxAvaible;
	private JSpinner spinnerPriceMinAvaible;
	private JSpinner spinnerPriceMaxAvaible;
	private JSpinner spinnerCostGoingMaxAssigned;
	private JSpinner spinnerCostLapMinAssigned;
	private JSpinner spinnerCostLapMaxAssigned;
	private JSpinner spinnerPriceMinAssigned;
	private JSpinner spinnerPriceMaxAssigned;
	private PanelDisennadorPaqueteTuristicoModalidadTransporteAnnadir panelDisennadorPaqueteTuristicoModalidadTransporteAnnadir;
	private TouristPackage touristPackage;
	private boolean isRestoreFiltersAvaible, isRestoreFiltersAssigned;
	private JLabel lblRestoreFiltersAssigned;
	private JLabel lblRestoreFiltersAvaible;

	/**
	 * Create the panel.
	 */
	public PanelPackageDisignerContenedorEstablishedRoute(PanelDisennadorPaqueteTuristicoModalidadTransporteAnnadir p) {
		setBorder(new MatteBorder(0, 3, 3, 3, (Color) new Color(0, 0, 0)));
		this.isRestoreFiltersAvaible = false;
		this.isRestoreFiltersAssigned = false;
		this.panelDisennadorPaqueteTuristicoModalidadTransporteAnnadir = p;
		this.touristPackage = this.panelDisennadorPaqueteTuristicoModalidadTransporteAnnadir.getTouristPackage();
		setBounds(0, 55, 971, 536);
		setLayout(null);
		setBackground(new Color(18, 95, 115));
		JLabel lblAvailableTransportation = new JLabel("AVAILABLE TRANSPORTATION");
		lblAvailableTransportation.setForeground(SystemColor.textHighlightText);
		lblAvailableTransportation.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAvailableTransportation.setBounds(10, 0, 275, 20);
		add(lblAvailableTransportation);

		lblAnnadir = new JLabel("");
		lblAnnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (lblAnnadir.isEnabled()) {
					try {
						asign();
						actualizarEstadosBotones();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblAnnadir.setIcon(new ImageIcon(PanelPackageDisignerContenedorCostKilometers.class.getResource("/images/Plus.png")));
		lblAnnadir.setOpaque(true);
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(new Color(18, 95, 115));
		lblAnnadir.setBounds(908, 30, 53, 43);
		add(lblAnnadir);

		JPanel panelAvailableTransportation = new JPanel();
		panelAvailableTransportation.setBounds(10, 89, 951, 173);
		add(panelAvailableTransportation);
		panelAvailableTransportation.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelAvailableTransportation.add(scrollPane, BorderLayout.CENTER);

		tableEstablishedRouteAvaible = new JTable();
		tableEstablishedRouteAvaible.setRowHeight(30);
		tableEstablishedRouteAvaible.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableEstablishedRouteAvaible.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		tableEstablishedRouteAvaible.getTableHeader().setForeground(Color.black);
		tableEstablishedRouteAvaible.getTableHeader().setBackground(SystemColor.black);
		tableEstablishedRouteAvaible.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadosBotones();
			}
		});
		tableEstablishedRouteAvaible.setModel(new ModeloTablaTransportModalityEstablishedRoute());
		scrollPane.setViewportView(tableEstablishedRouteAvaible);

		JPanel panelAssignedTransports = new JPanel();
		panelAssignedTransports.setOpaque(false);
		panelAssignedTransports.setBounds(10, 352, 951, 173);
		add(panelAssignedTransports);
		panelAssignedTransports.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panelAssignedTransports.add(scrollPane_1, BorderLayout.CENTER);

		tableEstablishedRouteAssigned = new JTable();
		tableEstablishedRouteAssigned.setRowHeight(30);
		tableEstablishedRouteAssigned.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableEstablishedRouteAssigned.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		tableEstablishedRouteAssigned.getTableHeader().setForeground(Color.black);
		tableEstablishedRouteAssigned.getTableHeader().setBackground(SystemColor.black);
		tableEstablishedRouteAssigned.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadosBotones();
			}
		});
		tableEstablishedRouteAssigned.setModel(new ModeloTablaTransportModalityEstablishedRoute());
		scrollPane_1.setViewportView(tableEstablishedRouteAssigned);



		lblDenegar = new JLabel("");
		lblDenegar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (lblDenegar.isEnabled()) {
					try {
						deny();
						actualizarEstadosBotones();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblDenegar.setIcon(new ImageIcon(PanelPackageDisignerContenedorCostKilometers.class.getResource("/images/Trash.png")));
		lblDenegar.setOpaque(true);
		lblDenegar.setHorizontalAlignment(SwingConstants.CENTER);
		lblDenegar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDenegar.setBackground(new Color(18, 95, 115));
		lblDenegar.setBounds(908, 298, 53, 43);
		add(lblDenegar);

		JLabel lblMealPlan = new JLabel("Cost Going:");
		lblMealPlan.setForeground(SystemColor.textHighlightText);
		lblMealPlan.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMealPlan.setBounds(137, 28, 112, 27);
		add(lblMealPlan);

		JLabel lblMin_2 = new JLabel("Min:");
		lblMin_2.setForeground(SystemColor.textHighlightText);
		lblMin_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_2.setBounds(95, 61, 30, 14);
		add(lblMin_2);

		spinnerCostGoingMinAvaible = new JSpinner();
		spinnerCostGoingMinAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableEstablishedRoute(); // se actualiza la tabla de las modalidades disponibles
			}
		});
		Operations.crearJSpinnerNumericoDouble(spinnerCostGoingMinAvaible, new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostGoingMinAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostGoingMinAvaible.setBounds(130, 58, 53, 20);
		add(spinnerCostGoingMinAvaible);

		JLabel lblMax_2 = new JLabel("Max:");
		lblMax_2.setForeground(SystemColor.textHighlightText);
		lblMax_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_2.setBounds(209, 61, 40, 14);
		add(lblMax_2);

		spinnerCostGoingMaxAvaible = new JSpinner();
		spinnerCostGoingMaxAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableEstablishedRoute(); // se actualiza la tabla de las modalidades disponibles
			}
		});
		Operations.crearJSpinnerNumericoDouble(spinnerCostGoingMaxAvaible, new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostGoingMaxAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostGoingMaxAvaible.setBounds(244, 58, 53, 20);
		add(spinnerCostGoingMaxAvaible);

		JLabel lblCantDaysAccommodation = new JLabel("Cost Lap:");
		lblCantDaysAccommodation.setForeground(SystemColor.textHighlightText);
		lblCantDaysAccommodation.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCantDaysAccommodation.setBounds(405, 28, 95, 27);
		add(lblCantDaysAccommodation);

		JLabel lblMin = new JLabel("Min:");
		lblMin.setForeground(SystemColor.textHighlightText);
		lblMin.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin.setBounds(365, 61, 30, 14);
		add(lblMin);

		spinnerCostLapMinAvaible = new JSpinner();
		spinnerCostLapMinAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableEstablishedRoute(); // se actualiza la tabla de las modalidades disponibles
			}
		});
		Operations.crearJSpinnerNumericoDouble(spinnerCostLapMinAvaible, new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostLapMinAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostLapMinAvaible.setBounds(400, 58, 53, 20);
		add(spinnerCostLapMinAvaible);

		JLabel lblMax = new JLabel("Max:");
		lblMax.setForeground(SystemColor.textHighlightText);
		lblMax.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax.setBounds(463, 61, 40, 14);
		add(lblMax);

		spinnerCostLapMaxAvaible = new JSpinner();
		spinnerCostLapMaxAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableEstablishedRoute(); // se actualiza la tabla de las modalidades disponibles
			}
		});
		Operations.crearJSpinnerNumericoDouble(spinnerCostLapMaxAvaible, new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostLapMaxAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostLapMaxAvaible.setBounds(498, 58, 53, 20);
		add(spinnerCostLapMaxAvaible);

		JLabel lblPrice = new JLabel("Plan Price:");
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setForeground(SystemColor.textHighlightText);
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPrice.setBounds(678, 31, 103, 20);
		add(lblPrice);

		JLabel lblMax_1 = new JLabel("Min:");
		lblMax_1.setForeground(SystemColor.textHighlightText);
		lblMax_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_1.setBounds(629, 61, 40, 14);
		add(lblMax_1);

		spinnerPriceMinAvaible = new JSpinner();
		spinnerPriceMinAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableEstablishedRoute(); // se actualiza la tabla de las modalidades disponibles
			}
		});
		Operations.crearJSpinnerNumericoDouble(spinnerPriceMinAvaible, new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMinAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMinAvaible.setBounds(668, 58, 53, 20);
		add(spinnerPriceMinAvaible);

		JLabel lblMin_1 = new JLabel("Max:");
		lblMin_1.setForeground(SystemColor.textHighlightText);
		lblMin_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_1.setBounds(731, 61, 40, 14);
		add(lblMin_1);

		spinnerPriceMaxAvaible = new JSpinner();
		spinnerPriceMaxAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableEstablishedRoute(); // se actualiza la tabla de las modalidades disponibles
			}
		});
		Operations.crearJSpinnerNumericoDouble(spinnerPriceMaxAvaible, new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMaxAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMaxAvaible.setBounds(771, 58, 53, 20);
		add(spinnerPriceMaxAvaible);

		JLabel lblMealPlan_1 = new JLabel("Cost Going:");
		lblMealPlan_1.setForeground(SystemColor.textHighlightText);
		lblMealPlan_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMealPlan_1.setBounds(137, 291, 112, 27);
		add(lblMealPlan_1);

		JLabel lblMin_2_1 = new JLabel("Min:");
		lblMin_2_1.setForeground(SystemColor.textHighlightText);
		lblMin_2_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_2_1.setBounds(95, 324, 30, 14);
		add(lblMin_2_1);

		spinnerCostGoingMinAssigned = new JSpinner();
		spinnerCostGoingMinAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedEstablishedRoute(); // se actualiza la tabla de las modalidades asignados
			}
		});
		Operations.crearJSpinnerNumericoDouble(spinnerCostGoingMinAssigned, new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostGoingMinAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostGoingMinAssigned.setBounds(130, 321, 53, 20);
		add(spinnerCostGoingMinAssigned);

		JLabel lblMax_2_1 = new JLabel("Max:");
		lblMax_2_1.setForeground(SystemColor.textHighlightText);
		lblMax_2_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_2_1.setBounds(209, 324, 40, 14);
		add(lblMax_2_1);

		spinnerCostGoingMaxAssigned = new JSpinner();
		spinnerCostGoingMaxAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedEstablishedRoute(); // se actualiza la tabla de las modalidades asignados
			}
		});
		Operations.crearJSpinnerNumericoDouble(spinnerCostGoingMaxAssigned, new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostGoingMaxAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostGoingMaxAssigned.setBounds(244, 321, 53, 20);
		add(spinnerCostGoingMaxAssigned);

		JLabel lblCantDaysAccommodation_1 = new JLabel("Cost Lap:");
		lblCantDaysAccommodation_1.setForeground(SystemColor.textHighlightText);
		lblCantDaysAccommodation_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCantDaysAccommodation_1.setBounds(405, 291, 95, 27);
		add(lblCantDaysAccommodation_1);

		JLabel lblMin_3 = new JLabel("Min:");
		lblMin_3.setForeground(SystemColor.textHighlightText);
		lblMin_3.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_3.setBounds(365, 324, 30, 14);
		add(lblMin_3);

		spinnerCostLapMinAssigned = new JSpinner();
		spinnerCostLapMinAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedEstablishedRoute(); // se actualiza la tabla de las modalidades asignados
			}
		});
		Operations.crearJSpinnerNumericoDouble(spinnerCostLapMinAssigned, new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostLapMinAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostLapMinAssigned.setBounds(400, 321, 53, 20);
		add(spinnerCostLapMinAssigned);

		JLabel lblMax_3 = new JLabel("Max:");
		lblMax_3.setForeground(SystemColor.textHighlightText);
		lblMax_3.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_3.setBounds(463, 324, 40, 14);
		add(lblMax_3);

		spinnerCostLapMaxAssigned = new JSpinner();
		spinnerCostLapMaxAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedEstablishedRoute(); // se actualiza la tabla de las modalidades asignados
			}
		});
		Operations.crearJSpinnerNumericoDouble(spinnerCostLapMaxAssigned, new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostLapMaxAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostLapMaxAssigned.setBounds(498, 321, 53, 20);
		add(spinnerCostLapMaxAssigned);

		JLabel lblPrice_1 = new JLabel("Plan Price:");
		lblPrice_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice_1.setForeground(SystemColor.textHighlightText);
		lblPrice_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPrice_1.setBounds(678, 294, 103, 20);
		add(lblPrice_1);

		JLabel lblMax_1_1 = new JLabel("Min:");
		lblMax_1_1.setForeground(SystemColor.textHighlightText);
		lblMax_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_1_1.setBounds(629, 324, 40, 14);
		add(lblMax_1_1);

		spinnerPriceMinAssigned = new JSpinner();
		spinnerPriceMinAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedEstablishedRoute(); // se actualiza la tabla de las modalidades asignados
			}
		});
		Operations.crearJSpinnerNumericoDouble(spinnerPriceMinAssigned, new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMinAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMinAssigned.setBounds(668, 321, 53, 20);
		add(spinnerPriceMinAssigned);

		JLabel lblMin_1_1 = new JLabel("Max:");
		lblMin_1_1.setForeground(SystemColor.textHighlightText);
		lblMin_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_1_1.setBounds(731, 324, 40, 14);
		add(lblMin_1_1);

		spinnerPriceMaxAssigned = new JSpinner();
		spinnerPriceMaxAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedEstablishedRoute(); // se actualiza la tabla de las modalidades asignados
			}
		});
		Operations.crearJSpinnerNumericoDouble(spinnerPriceMaxAssigned, new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMaxAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMaxAssigned.setBounds(771, 321, 53, 20);
		add(spinnerPriceMaxAssigned);

		JLabel lblAssignedTransports = new JLabel("ASSIGNED TRANSPORTS");
		lblAssignedTransports.setForeground(SystemColor.textHighlightText);
		lblAssignedTransports.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAssignedTransports.setBounds(10, 261, 275, 19);
		add(lblAssignedTransports);

		lblRestoreFiltersAvaible = new JLabel("");
		lblRestoreFiltersAvaible.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				restoreFiltersAviable();
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblRestoreFiltersAvaible.setIcon(new ImageIcon(PanelPackageDisignerContenedorEstablishedRoute.class.getResource("/images/Restart.png")));
		lblRestoreFiltersAvaible.setOpaque(true);
		lblRestoreFiltersAvaible.setHorizontalAlignment(SwingConstants.CENTER);
		lblRestoreFiltersAvaible.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblRestoreFiltersAvaible.setBackground(new Color(18, 95, 115));
		lblRestoreFiltersAvaible.setBounds(857, 30, 53, 43);
		add(lblRestoreFiltersAvaible);

		lblRestoreFiltersAssigned = new JLabel("");
		lblRestoreFiltersAssigned.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				restoreFiltersAssigned();
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblRestoreFiltersAssigned.setIcon(new ImageIcon(PanelPackageDisignerContenedorEstablishedRoute.class.getResource("/images/Restart.png")));
		lblRestoreFiltersAssigned.setOpaque(true);
		lblRestoreFiltersAssigned.setHorizontalAlignment(SwingConstants.CENTER);
		lblRestoreFiltersAssigned.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblRestoreFiltersAssigned.setBackground(new Color(18, 95, 115));
		lblRestoreFiltersAssigned.setBounds(857, 298, 53, 43);
		add(lblRestoreFiltersAssigned);

		
	}

	// Metodos de actualizacion de la tabla  tableAviavleAccommodations
	private void actualizarTablaAviableEstablishedRoute () {	
		this.actualizarTablaAviableEstablishedRoute(Controller.getInstancie().getTouristAgency().getTransportModalityEstablishedRoute((Double)  this.spinnerCostGoingMinAvaible.getValue(), (Double) this.spinnerCostGoingMaxAvaible.getValue(), 
				(Double) this.spinnerCostLapMinAvaible.getValue(), (Double) this.spinnerCostLapMaxAvaible.getValue(), (Double) this.spinnerPriceMinAvaible.getValue(), (Double) this.spinnerPriceMaxAvaible.getValue()));
	}


	private void actualizarTablaAviableEstablishedRoute(ArrayList<Modality> modalitys){
		reiniciarTable(this.tableEstablishedRouteAvaible);


		for (Modality mod : modalitys) {
			if (!touristPackage.isContainsModality(Modality.establishedRoute, mod))
				((ModeloTablaTransportModalityEstablishedRoute) this.tableEstablishedRouteAvaible.getModel()).addElement((EstablishedRoute) mod); 
		}
	}

	// Fin de Metodos de actualizacion de la tabla  tableAviavleAccommodations


	private void actualizarTablaAssignedEstablishedRoute () {	
		this.actualizarTablaAssignedEstablishedRoute(this.touristPackage.getTransportModalitysEstablishedRoute((Double)  this.spinnerCostGoingMinAssigned.getValue(), (Double) this.spinnerCostGoingMaxAssigned.getValue(), 
				(Double) this.spinnerCostLapMinAssigned.getValue(), (Double) this.spinnerCostLapMaxAssigned.getValue(), (Double) this.spinnerPriceMinAssigned.getValue(), (Double) this.spinnerPriceMaxAssigned.getValue()));
	}

	// Metodos de actualizacion de la tabla  tableAssignedAccommodations
	private void actualizarTablaAssignedEstablishedRoute(ArrayList<Modality> modalitys){
		reiniciarTable(this.tableEstablishedRouteAssigned);


		for (Modality mod : modalitys) {
			((ModeloTablaTransportModalityEstablishedRoute) this.tableEstablishedRouteAssigned.getModel()).addElement((EstablishedRoute) mod);
		}
	}

	// Fin de Metodos de actualizacion de la tabla  tableAssignedAccommodations



	private void reiniciarTable(JTable table){

		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaTransportModalityEstablishedRoute) table.getModel()).deleteElement(i);
		}
	}


	// Operaciones

	private void asign () throws SQLException  { // Metodo para asignar las modalidades seleccionadas al paquete
		int [] rows = this.tableEstablishedRouteAvaible.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.touristPackage.getId() != -1) 
				this.touristPackage.addModality(((ModeloTablaTransportModalityEstablishedRoute) this.tableEstablishedRouteAvaible.getModel()).getElement(rows[i])); // se asigna a las base de datos y a la logica del negocio
			else
				this.touristPackage.addModalityLogic(((ModeloTablaTransportModalityEstablishedRoute) this.tableEstablishedRouteAvaible.getModel()).getElement(rows[i])); // se asigna solo en la logica del negocio
		}

		this.actualizarTablas(); // se actualiza la informacion de las dos tablas
	}

	public void actualizarTablas () {
		this.actualizarTablaAssignedEstablishedRoute();
		this.actualizarTablaAviableEstablishedRoute();
		this.actualizarEstadosBotones();
	}

	private void deny () throws SQLException { // Metodo para desasignar las modalidades seleccionadas del paquete
		int [] rows = this.tableEstablishedRouteAssigned.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.touristPackage.getId() != -1) 
				this.touristPackage.deleteModality(((ModeloTablaTransportModalityEstablishedRoute) this.tableEstablishedRouteAssigned.getModel()).getElement(rows[i])); // se elimina a las base de datos y a la logica del negocio
			else
				this.touristPackage.deleteModalityLogic(((ModeloTablaTransportModalityEstablishedRoute) this.tableEstablishedRouteAssigned.getModel()).getElement(rows[i])); // se elimina solo en la logica del negocio
		}

		this.actualizarTablas(); // se actualiza la informacion de las dos tablas
	}


	// Metodos para la restauracion de los Filtros

	private void restoreFiltersAviable () { // Metodo para restaurar los filtros de la tabla de modalidades disponibles
		this.isRestoreFiltersAvaible = true; // se indica que va a empezar una restauracion de los filtros
		this.spinnerCostGoingMaxAvaible.setValue(0.0);
		this.spinnerCostGoingMinAvaible.setValue(0.0);
		this.spinnerPriceMaxAvaible.setValue(0.0);
		this.spinnerPriceMinAvaible.setValue(0.0);
		this.spinnerCostLapMaxAvaible.setValue(0.0);
		this.spinnerCostLapMinAvaible.setValue(0.0);
		this.actualizarTablaAviableEstablishedRoute(); // se actualiza la tabla de las modalidades disponibles
		this.isRestoreFiltersAvaible = false; // se indica que se terminó la restauracion de los filtros
	}

	private void restoreFiltersAssigned () { // Metodo para restaurar los filtros de la tabla de modalidades asignadas
		this.isRestoreFiltersAssigned = true; // se indica que va a empezar una restauracion de los filtros
		this.spinnerCostGoingMaxAssigned.setValue(0.0);
		this.spinnerCostGoingMinAssigned.setValue(0.0);
		this.spinnerPriceMaxAssigned.setValue(0.0);
		this.spinnerPriceMinAssigned.setValue(0.0);
		this.spinnerCostLapMaxAssigned.setValue(0.0);
		this.spinnerCostLapMinAssigned.setValue(0.0);
		this.actualizarTablaAssignedEstablishedRoute(); // se actualiza la tabla de las modalidades disponibles
		this.isRestoreFiltersAssigned = false; // se indica que se terminó la restauracion de los filtros
	}

	// Fin de Metodos para la restauracion de los Filtros

	// Fin de Operaciones


	public void actualizarEstadosBotones () {
		this.estadoLblAsign();
		this.estadoLblDeny();
	}

	private void estadoLblAsign () {
		if (tableEstablishedRouteAvaible.getSelectedRowCount() != 0)
			lblAnnadir.setEnabled(true);
		else
			lblAnnadir.setEnabled(false);
	}

	private void estadoLblDeny () {
		if (tableEstablishedRouteAssigned.getSelectedRowCount() != 0)
			lblDenegar.setEnabled(true);
		else
			lblDenegar.setEnabled(false);
	}



	@Override
	public void clearSelectionTable() {
		this.tableEstablishedRouteAssigned.clearSelection();
		this.tableEstablishedRouteAvaible.clearSelection();
	}
}
