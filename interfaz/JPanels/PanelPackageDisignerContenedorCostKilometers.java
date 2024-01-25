package JPanels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.SwingConstants;
import logica.Controller;
import logica.CostKilometers;
import logica.Modality;
import logica.TouristPackage;
import modelosTablas.ModeloTablaTransportModalityCostKilometers;
import java.awt.BorderLayout;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.border.MatteBorder;

public class PanelPackageDisignerContenedorCostKilometers extends JPanel implements PanelTransportModalityTouristDesignerOperations{

	private static final long serialVersionUID = 1L;
	private JTable tableCostKilometersAvaible;
	private JTable tableCostKilometerAssigned;
	private JLabel lblAnnadir;
	private JLabel lblDenegar;
	private JSpinner spinnerCostKilometersGoingMinAvaible;
	private PanelDisennadorPaqueteTuristicoModalidadTransporteAnnadir panelDisennadorPaqueteTuristicoModalidadTransporteAnnadir;
	private TouristPackage touristPackage;
	private boolean isRestoreFiltersAvaible, isRestoreFiltersAssigned;
	private JSpinner spinnerCostKilometersGoingMaxAvaible;
	private JSpinner spinnerCostKilometersLapMinAvaible;
	private JSpinner spinnerCostKilometersLapMaxAvaible;
	private JSpinner spinnerCostHoursWaitMinAvaible;
	private JSpinner spinnerCostHoursWaitMaxAvaible;
	private JSpinner spinnerPriceMinAvaible;
	private JSpinner spinnerPriceMaxAvaible;
	private JSpinner spinnerCostKilometersGoingMaxAssigned;
	private JSpinner spinnerCostKilometersGoingMinAssigned;
	private JSpinner spinnerCostKilometersLapMinAssigned;
	private JSpinner spinnerCostKilometersLapMaxAssigned;
	private JSpinner spinnerCostHoursWaitMinAssigned;
	private JSpinner spinnerCostHoursWaitMaxAssigned;
	private JSpinner spinnerPriceMinAssigned;
	private JSpinner spinnerPriceMaxAssigned;
	private JLabel lblRestoreFiltersAvaible;
	private JLabel lblRestoreFilterAssigned;

	/**
	 * Create the panel.
	 */
	public PanelPackageDisignerContenedorCostKilometers(PanelDisennadorPaqueteTuristicoModalidadTransporteAnnadir p) {
		setBorder(new MatteBorder(0, 3, 3, 3, (Color) new Color(0, 0, 0)));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				clearSelectionTable();
				actualizarEstadosBotones();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		this.isRestoreFiltersAssigned = false;
		this.isRestoreFiltersAvaible = false;
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

		tableCostKilometersAvaible = new JTable();
		tableCostKilometersAvaible.setRowHeight(30);
		tableCostKilometersAvaible.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableCostKilometersAvaible.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		tableCostKilometersAvaible.getTableHeader().setForeground(Color.black);
		tableCostKilometersAvaible.getTableHeader().setBackground(SystemColor.black);
		tableCostKilometersAvaible.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadosBotones();
			}
		});
		tableCostKilometersAvaible.setModel(new ModeloTablaTransportModalityCostKilometers());
		scrollPane.setViewportView(tableCostKilometersAvaible);

		JPanel panelAssignedTransports = new JPanel();
		panelAssignedTransports.setOpaque(false);
		panelAssignedTransports.setBounds(10, 352, 951, 173);
		add(panelAssignedTransports);
		panelAssignedTransports.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panelAssignedTransports.add(scrollPane_1, BorderLayout.CENTER);

		tableCostKilometerAssigned = new JTable();
		tableCostKilometerAssigned.setRowHeight(30);
		tableCostKilometerAssigned.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableCostKilometerAssigned.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		tableCostKilometerAssigned.getTableHeader().setForeground(Color.black);
		tableCostKilometerAssigned.getTableHeader().setBackground(SystemColor.black);
		tableCostKilometerAssigned.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadosBotones();
			}
		});
		tableCostKilometerAssigned.setModel(new ModeloTablaTransportModalityCostKilometers());
		scrollPane_1.setViewportView(tableCostKilometerAssigned);

		JLabel lblNewLabel = new JLabel("Cost Kilometers Going:");
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 22, 216, 27);
		add(lblNewLabel);

		JLabel lblMin_3 = new JLabel("Min:");
		lblMin_3.setForeground(SystemColor.textHighlightText);
		lblMin_3.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_3.setBounds(20, 57, 30, 14);
		add(lblMin_3);

		spinnerCostKilometersGoingMinAvaible = new JSpinner();
		spinnerCostKilometersGoingMinAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableCostKilometers(); // se actualiza la informacion de la tabla de las modalidades disponibles
			}
		});
		spinnerCostKilometersGoingMinAvaible.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostKilometersGoingMinAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersGoingMinAvaible.setBounds(55, 54, 53, 20);
		add(spinnerCostKilometersGoingMinAvaible);

		JLabel lblMax_3 = new JLabel("Max:");
		lblMax_3.setForeground(SystemColor.textHighlightText);
		lblMax_3.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_3.setBounds(118, 57, 40, 14);
		add(lblMax_3);

		spinnerCostKilometersGoingMaxAvaible = new JSpinner();
		spinnerCostKilometersGoingMaxAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableCostKilometers(); // se actualiza la informacion de la tabla de las modalidades disponibles
			}
		});
		spinnerCostKilometersGoingMaxAvaible.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostKilometersGoingMaxAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersGoingMaxAvaible.setBounds(153, 54, 53, 20);
		add(spinnerCostKilometersGoingMaxAvaible);

		JLabel lblMealPlan = new JLabel("Cost Kilometers Lap:");
		lblMealPlan.setForeground(SystemColor.textHighlightText);
		lblMealPlan.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMealPlan.setBounds(244, 22, 200, 27);
		add(lblMealPlan);

		JLabel lblMin_2 = new JLabel("Min:");
		lblMin_2.setForeground(SystemColor.textHighlightText);
		lblMin_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_2.setBounds(254, 57, 30, 14);
		add(lblMin_2);

		spinnerCostKilometersLapMinAvaible = new JSpinner();
		spinnerCostKilometersLapMinAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableCostKilometers(); // se actualiza la informacion de la tabla de las modalidades disponibles
			}
		});
		spinnerCostKilometersLapMinAvaible.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostKilometersLapMinAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersLapMinAvaible.setBounds(289, 54, 53, 20);
		add(spinnerCostKilometersLapMinAvaible);

		JLabel lblMax_2 = new JLabel("Max:");
		lblMax_2.setForeground(SystemColor.textHighlightText);
		lblMax_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_2.setBounds(352, 57, 40, 14);
		add(lblMax_2);

		spinnerCostKilometersLapMaxAvaible = new JSpinner();
		spinnerCostKilometersLapMaxAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableCostKilometers(); // se actualiza la informacion de la tabla de las modalidades disponibles
			}
		});
		spinnerCostKilometersLapMaxAvaible.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostKilometersLapMaxAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersLapMaxAvaible.setBounds(387, 54, 53, 20);
		add(spinnerCostKilometersLapMaxAvaible);

		JLabel lblMin = new JLabel("Min:");
		lblMin.setForeground(SystemColor.textHighlightText);
		lblMin.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin.setBounds(452, 57, 30, 14);
		add(lblMin);

		spinnerCostHoursWaitMinAvaible = new JSpinner();
		spinnerCostHoursWaitMinAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableCostKilometers(); // se actualiza la informacion de la tabla de las modalidades disponibles
			}
		});
		spinnerCostHoursWaitMinAvaible.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostHoursWaitMinAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostHoursWaitMinAvaible.setBounds(487, 54, 53, 20);
		add(spinnerCostHoursWaitMinAvaible);

		JLabel lblCantDaysAccommodation = new JLabel("Cost Hours wait:");
		lblCantDaysAccommodation.setForeground(SystemColor.textHighlightText);
		lblCantDaysAccommodation.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCantDaysAccommodation.setBounds(487, 20, 150, 30);
		add(lblCantDaysAccommodation);

		JLabel lblMax = new JLabel("Max:");
		lblMax.setForeground(SystemColor.textHighlightText);
		lblMax.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax.setBounds(550, 57, 40, 14);
		add(lblMax);

		spinnerCostHoursWaitMaxAvaible = new JSpinner();
		spinnerCostHoursWaitMaxAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableCostKilometers(); // se actualiza la informacion de la tabla de las modalidades disponibles
			}
		});
		spinnerCostHoursWaitMaxAvaible.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostHoursWaitMaxAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostHoursWaitMaxAvaible.setBounds(585, 54, 53, 20);
		add(spinnerCostHoursWaitMaxAvaible);

		JLabel lblMin_1 = new JLabel("Min:");
		lblMin_1.setForeground(SystemColor.textHighlightText);
		lblMin_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_1.setBounds(648, 57, 30, 14);
		add(lblMin_1);

		spinnerPriceMinAvaible = new JSpinner();
		spinnerPriceMinAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableCostKilometers(); // se actualiza la informacion de la tabla de las modalidades disponibles
			}
		});
		spinnerPriceMinAvaible.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMinAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMinAvaible.setBounds(688, 54, 53, 20);
		add(spinnerPriceMinAvaible);

		JLabel lblPrice = new JLabel("Plan Price:");
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setForeground(SystemColor.textHighlightText);
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPrice.setBounds(699, 20, 103, 30);
		add(lblPrice);

		JLabel lblMax_1 = new JLabel("Max:");
		lblMax_1.setForeground(SystemColor.textHighlightText);
		lblMax_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_1.setBounds(751, 57, 40, 14);
		add(lblMax_1);

		spinnerPriceMaxAvaible = new JSpinner();
		spinnerPriceMaxAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableCostKilometers(); // se actualiza la informacion de la tabla de las modalidades disponibles
			}
		});
		spinnerPriceMaxAvaible.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMaxAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMaxAvaible.setBounds(790, 54, 53, 20);
		add(spinnerPriceMaxAvaible);

		JLabel lblAssignedTransports = new JLabel("ASSIGNED TRANSPORTS");
		lblAssignedTransports.setForeground(SystemColor.textHighlightText);
		lblAssignedTransports.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAssignedTransports.setBounds(10, 263, 275, 19);
		add(lblAssignedTransports);

		JLabel lblNewLabel_1 = new JLabel("Cost Kilometers Going:");
		lblNewLabel_1.setForeground(SystemColor.textHighlightText);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 285, 216, 27);
		add(lblNewLabel_1);

		JLabel lblMin_3_1 = new JLabel("Min:");
		lblMin_3_1.setForeground(SystemColor.textHighlightText);
		lblMin_3_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_3_1.setBounds(20, 320, 30, 14);
		add(lblMin_3_1);

		spinnerCostKilometersGoingMinAssigned = new JSpinner();
		spinnerCostKilometersGoingMinAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedCostKilometers(); // se actualiza la informacion de la tabla de las modalidades asignados
			}
		});
		spinnerCostKilometersGoingMinAssigned.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostKilometersGoingMinAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersGoingMinAssigned.setBounds(55, 317, 53, 20);
		add(spinnerCostKilometersGoingMinAssigned);

		JLabel lblMax_3_1 = new JLabel("Max:");
		lblMax_3_1.setForeground(SystemColor.textHighlightText);
		lblMax_3_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_3_1.setBounds(118, 320, 40, 14);
		add(lblMax_3_1);

		spinnerCostKilometersGoingMaxAssigned = new JSpinner();
		spinnerCostKilometersGoingMaxAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedCostKilometers(); // se actualiza la informacion de la tabla de las modalidades asignados
			}
		});
		spinnerCostKilometersGoingMaxAssigned.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostKilometersGoingMaxAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersGoingMaxAssigned.setBounds(153, 317, 53, 20);
		add(spinnerCostKilometersGoingMaxAssigned);

		JLabel lblMin_2_1 = new JLabel("Min:");
		lblMin_2_1.setForeground(SystemColor.textHighlightText);
		lblMin_2_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_2_1.setBounds(254, 320, 30, 14);
		add(lblMin_2_1);

		JLabel lblMealPlan_1 = new JLabel("Cost Kilometers Lap:");
		lblMealPlan_1.setForeground(SystemColor.textHighlightText);
		lblMealPlan_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMealPlan_1.setBounds(244, 285, 200, 27);
		add(lblMealPlan_1);

		spinnerCostKilometersLapMinAssigned = new JSpinner();
		spinnerCostKilometersLapMinAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedCostKilometers(); // se actualiza la informacion de la tabla de las modalidades asignados
			}
		});
		spinnerCostKilometersLapMinAssigned.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostKilometersLapMinAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersLapMinAssigned.setBounds(289, 317, 53, 20);
		add(spinnerCostKilometersLapMinAssigned);

		JLabel lblMax_2_1 = new JLabel("Max:");
		lblMax_2_1.setForeground(SystemColor.textHighlightText);
		lblMax_2_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_2_1.setBounds(352, 320, 40, 14);
		add(lblMax_2_1);

		spinnerCostKilometersLapMaxAssigned = new JSpinner();
		spinnerCostKilometersLapMaxAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedCostKilometers(); // se actualiza la informacion de la tabla de las modalidades asignados
			}
		});
		spinnerCostKilometersLapMaxAssigned.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostKilometersLapMaxAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersLapMaxAssigned.setBounds(387, 317, 53, 20);
		add(spinnerCostKilometersLapMaxAssigned);

		JLabel lblMin_4 = new JLabel("Min:");
		lblMin_4.setForeground(SystemColor.textHighlightText);
		lblMin_4.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_4.setBounds(452, 320, 30, 14);
		add(lblMin_4);

		spinnerCostHoursWaitMinAssigned = new JSpinner();
		spinnerCostHoursWaitMinAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedCostKilometers(); // se actualiza la informacion de la tabla de las modalidades asignados
			}
		});
		spinnerCostHoursWaitMinAssigned.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostHoursWaitMinAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostHoursWaitMinAssigned.setBounds(487, 317, 53, 20);
		add(spinnerCostHoursWaitMinAssigned);

		JLabel lblCantDaysAccommodation_1 = new JLabel("Cost Hours wait:");
		lblCantDaysAccommodation_1.setForeground(SystemColor.textHighlightText);
		lblCantDaysAccommodation_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCantDaysAccommodation_1.setBounds(487, 283, 150, 30);
		add(lblCantDaysAccommodation_1);

		JLabel lblMax_4 = new JLabel("Max:");
		lblMax_4.setForeground(SystemColor.textHighlightText);
		lblMax_4.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_4.setBounds(550, 320, 40, 14);
		add(lblMax_4);

		JLabel lblMin_1_1 = new JLabel("Min:");
		lblMin_1_1.setForeground(SystemColor.textHighlightText);
		lblMin_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_1_1.setBounds(648, 320, 30, 14);
		add(lblMin_1_1);

		JLabel lblPrice_1 = new JLabel("Plan Price:");
		lblPrice_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice_1.setForeground(SystemColor.textHighlightText);
		lblPrice_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPrice_1.setBounds(699, 283, 103, 30);
		add(lblPrice_1);

		spinnerPriceMinAssigned = new JSpinner();
		spinnerPriceMinAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedCostKilometers(); // se actualiza la informacion de la tabla de las modalidades asignados
			}
		});
		spinnerPriceMinAssigned.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMinAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMinAssigned.setBounds(688, 317, 53, 20);
		add(spinnerPriceMinAssigned);

		JLabel lblMax_1_1 = new JLabel("Max:");
		lblMax_1_1.setForeground(SystemColor.textHighlightText);
		lblMax_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_1_1.setBounds(751, 320, 40, 14);
		add(lblMax_1_1);

		spinnerPriceMaxAssigned = new JSpinner();
		spinnerPriceMaxAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedCostKilometers(); // se actualiza la informacion de la tabla de las modalidades asignados
			}
		});
		spinnerPriceMaxAssigned.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMaxAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMaxAssigned.setBounds(790, 317, 53, 20);
		add(spinnerPriceMaxAssigned);

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

		spinnerCostHoursWaitMaxAssigned = new JSpinner();
		spinnerCostHoursWaitMaxAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedCostKilometers(); // se actualiza la informacion de la tabla de las modalidades asignados
			}
		});
		spinnerCostHoursWaitMaxAssigned.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostHoursWaitMaxAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostHoursWaitMaxAssigned.setBounds(585, 317, 53, 20);
		add(spinnerCostHoursWaitMaxAssigned);

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
		lblRestoreFiltersAvaible.setIcon(new ImageIcon(PanelPackageDisignerContenedorCostKilometers.class.getResource("/images/Restart.png")));
		lblRestoreFiltersAvaible.setOpaque(true);
		lblRestoreFiltersAvaible.setHorizontalAlignment(SwingConstants.CENTER);
		lblRestoreFiltersAvaible.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblRestoreFiltersAvaible.setBackground(new Color(18, 95, 115));
		lblRestoreFiltersAvaible.setBounds(853, 30, 53, 43);
		add(lblRestoreFiltersAvaible);

		lblRestoreFilterAssigned = new JLabel("");
		lblRestoreFilterAssigned.addMouseListener(new MouseAdapter() {
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
		lblRestoreFilterAssigned.setIcon(new ImageIcon(PanelPackageDisignerContenedorCostKilometers.class.getResource("/images/Restart.png")));
		lblRestoreFilterAssigned.setOpaque(true);
		lblRestoreFilterAssigned.setHorizontalAlignment(SwingConstants.CENTER);
		lblRestoreFilterAssigned.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblRestoreFilterAssigned.setBackground(new Color(18, 95, 115));
		lblRestoreFilterAssigned.setBounds(853, 298, 53, 43);
		add(lblRestoreFilterAssigned);

		this.actualizarEstadosBotones();
	}


	// Metodos de actualizacion de la tabla  tableAviavleAccommodations
	private void actualizarTablaAviableCostKilometers () {	
		this.actualizarTablaAviableCostKilometers(Controller.getInstancie().getTouristAgency().getTransportModalityCostKilometers((Double) this.spinnerCostKilometersGoingMinAvaible.getValue(), (Double) this.spinnerCostKilometersGoingMaxAvaible.getValue(), 
				(Double) this.spinnerCostKilometersLapMinAvaible.getValue(), (Double) this.spinnerCostKilometersLapMaxAvaible.getValue(), (Double) this.spinnerCostHoursWaitMinAvaible.getValue(), 
				(Double) this.spinnerCostHoursWaitMaxAvaible.getValue(), (Double) this.spinnerPriceMinAvaible.getValue(), (Double) this.spinnerPriceMaxAvaible.getValue()));
	}


	private void actualizarTablaAviableCostKilometers(ArrayList<Modality> modalitys){
		reiniciarTable(this.tableCostKilometersAvaible);


		for (Modality mod : modalitys) {
			if (!touristPackage.isContainsModality(Modality.costKilometers, mod))
				((ModeloTablaTransportModalityCostKilometers) this.tableCostKilometersAvaible.getModel()).addElement((CostKilometers) mod); 
		}
	}

	// Fin de Metodos de actualizacion de la tabla  tableAviavleAccommodations


	private void actualizarTablaAssignedCostKilometers () {	
		this.actualizarTablaAssignedCostKilometers(this.touristPackage.getTransportModalitysCostKilometers((Double) this.spinnerCostKilometersGoingMinAssigned.getValue(), (Double) this.spinnerCostKilometersGoingMaxAssigned.getValue(), 
				(Double) this.spinnerCostKilometersLapMinAssigned.getValue(), (Double) this.spinnerCostKilometersLapMaxAssigned.getValue(), (Double) this.spinnerCostHoursWaitMinAssigned.getValue(), 
				(Double) this.spinnerCostHoursWaitMaxAssigned.getValue(), (Double) this.spinnerPriceMinAssigned.getValue(), (Double) this.spinnerPriceMaxAssigned.getValue()));
	}

	// Metodos de actualizacion de la tabla  tableAssignedAccommodations
	private void actualizarTablaAssignedCostKilometers(ArrayList<Modality> modalitys){
		reiniciarTable(this.tableCostKilometerAssigned);


		for (Modality mod : modalitys) {
			((ModeloTablaTransportModalityCostKilometers) this.tableCostKilometerAssigned.getModel()).addElement((CostKilometers) mod);
		}
	}

	// Fin de Metodos de actualizacion de la tabla  tableAssignedAccommodations



	private void reiniciarTable(JTable table){

		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaTransportModalityCostKilometers) table.getModel()).deleteElement(i);
		}
	}


	// Operaciones

	private void asign () throws SQLException  { // Metodo para asignar las modalidades seleccionadas al paquete
		int [] rows = this.tableCostKilometersAvaible.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.touristPackage.getId() != -1) 
				this.touristPackage.addModality(((ModeloTablaTransportModalityCostKilometers) this.tableCostKilometersAvaible.getModel()).getElement(rows[i])); // se asigna a las base de datos y a la logica del negocio
			else
				this.touristPackage.addModalityLogic(((ModeloTablaTransportModalityCostKilometers) this.tableCostKilometersAvaible.getModel()).getElement(rows[i])); // se asigna solo en la logica del negocio
		}

		this.actualizarTablas(); // se actualiza la informacion de las dos tablas
	}

	public void actualizarTablas () {
		this.actualizarTablaAviableCostKilometers();
		this.actualizarTablaAssignedCostKilometers();
		this.actualizarEstadosBotones();
	}

	private void deny () throws SQLException { // Metodo para desasignar las modalidades seleccionadas del paquete
		int [] rows = this.tableCostKilometerAssigned.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.touristPackage.getId() != -1) 
				this.touristPackage.deleteModality(((ModeloTablaTransportModalityCostKilometers) this.tableCostKilometerAssigned.getModel()).getElement(rows[i])); // se elimina a las base de datos y a la logica del negocio
			else
				this.touristPackage.deleteModalityLogic(((ModeloTablaTransportModalityCostKilometers) this.tableCostKilometerAssigned.getModel()).getElement(rows[i])); // se elimina solo en la logica del negocio
		}

		this.actualizarTablas(); // se actualiza la informacion de las dos tablas
	}


	// Metodos para la restauracion de los Filtros

	private void restoreFiltersAviable () { // Metodo para restaurar los filtros de la tabla de modalidades disponibles
		this.isRestoreFiltersAvaible = true; // se indica que va a empezar una restauracion de los filtros
		this.spinnerCostHoursWaitMaxAvaible.setValue(0.0);
		this.spinnerCostHoursWaitMinAvaible.setValue(0.0);
		this.spinnerPriceMaxAvaible.setValue(0.0);
		this.spinnerPriceMinAvaible.setValue(0.0);
		this.spinnerCostKilometersGoingMaxAvaible.setValue(0.0);
		this.spinnerCostKilometersGoingMinAvaible.setValue(0.0);
		this.spinnerCostKilometersLapMaxAvaible.setValue(0.0);
		this.spinnerCostKilometersLapMinAvaible.setValue(0.0);
		this.actualizarTablaAviableCostKilometers(); // se actualiza la tabla de las modalidades disponibles
		this.isRestoreFiltersAvaible = false; // se indica que se terminó la restauracion de los filtros
	}

	private void restoreFiltersAssigned () { // Metodo para restaurar los filtros de la tabla de modalidades asignadas
		this.isRestoreFiltersAssigned = true; // se indica que va a empezar una restauracion de los filtros
		this.spinnerCostHoursWaitMaxAssigned.setValue(0.0);
		this.spinnerCostHoursWaitMinAssigned.setValue(0.0);
		this.spinnerPriceMaxAssigned.setValue(0.0);
		this.spinnerPriceMinAssigned.setValue(0.0);
		this.spinnerCostKilometersGoingMaxAssigned.setValue(0.0);
		this.spinnerCostKilometersGoingMinAssigned.setValue(0.0);
		this.spinnerCostKilometersLapMaxAssigned.setValue(0.0);
		this.spinnerCostKilometersLapMinAssigned.setValue(0.0);
		this.actualizarTablaAssignedCostKilometers(); // se actualiza la tabla de las modalidades disponibles
		this.isRestoreFiltersAssigned = false; // se indica que se terminó la restauracion de los filtros
	}

	// Fin de Metodos para la restauracion de los Filtros

	// Fin de Operaciones


	public void actualizarEstadosBotones () {
		this.estadoLblAsign();
		this.estadoLblDeny();
	}

	private void estadoLblAsign () {
		if (tableCostKilometersAvaible.getSelectedRowCount() != 0)
			lblAnnadir.setEnabled(true);
		else
			lblAnnadir.setEnabled(false);
	}

	private void estadoLblDeny () {
		if (tableCostKilometerAssigned.getSelectedRowCount() != 0)
			lblDenegar.setEnabled(true);
		else
			lblDenegar.setEnabled(false);
	}



	@Override
	public void clearSelectionTable() {
		this.tableCostKilometerAssigned.clearSelection();
		this.tableCostKilometersAvaible.clearSelection();
	}
}
