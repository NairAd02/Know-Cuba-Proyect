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
import logica.HoursKilometers;
import logica.Modality;
import logica.TouristPackage;
import modelosTablas.ModeloTablaTransportModalityCostKilometers;
import modelosTablas.ModeloTablaTransportModalityHoursKilometers;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;

public class PanelPackageDisignerContenedorHoursKilometers extends JPanel implements PanelTransportModalityTouristDesignerOperations {

	private static final long serialVersionUID = 1L;
	private JTable tableHoursKilometersAvaible;
	private JTable tableHoursKilometersAssigned;
	private JLabel lblAnnadir;
	private JLabel lblDenegar;
	private JLabel lblRestoreFiltersAssigned;
	private JLabel lblRestoreFiltersAvaible;
	private PanelDisennadorPaqueteTuristicoModalidadTransporteAnnadir panelDisennadorPaqueteTuristicoModalidadTransporteAnnadir;
	private TouristPackage touristPackage;
	private JSpinner spinnerCostKilometersRoutMinAvaible;
	private JSpinner spinnerCostKilometersRoutMaxAvaible;
	private JSpinner spinnerCostHoursMinAvaible;
	private JSpinner spinnerCostHoursMaxAvaible;
	private JSpinner spinnerCostKilometersRoutAdditionalsMaxAvaible;
	private JSpinner spinnerCostKilometersRoutAdditionalsMinAvaible;
	private JSpinner spinnerCostHoursAdditionalsMinAvaible;
	private JSpinner spinnerCostHoursAdditionalsMaxAvaible;
	private JSpinner spinnerPriceMinAvaible;
	private JSpinner spinnerPriceMaxAvaible;
	private JSpinner spinnerCostKilometersRoutMinAssigned;
	private JSpinner spinnerCostKilometersRoutAdditionalsMinAssigned;
	private JSpinner spinnerCostHoursMaxAssigned;
	private JSpinner spinnerCostHoursMinAssigned;
	private JSpinner spinnerCostKilometersRoutMaxAssigned;
	private JSpinner spinnerCostKilometersRoutAdditionalsMaxAssigned;
	private JSpinner spinnerCostHoursAdditionalsMinAssigned;
	private JSpinner spinnerCostHoursAdditionalsMaxAssigned;
	private JSpinner spinnerPriceMinAssigned;
	private JSpinner spinnerPriceMaxAssigned;
	private boolean isRestoreFiltersAvaible, isRestoreFiltersAssigned;

	/**
	 * Create the panel.
	 */
	public PanelPackageDisignerContenedorHoursKilometers(PanelDisennadorPaqueteTuristicoModalidadTransporteAnnadir p) {
		setBorder(new MatteBorder(0, 3, 3, 3, (Color) new Color(0, 0, 0)));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				clearSelectionTable();
				actualizarEstadosBotones();
			}
		});
		this.isRestoreFiltersAssigned = false;
		this.isRestoreFiltersAvaible = false;
		this.panelDisennadorPaqueteTuristicoModalidadTransporteAnnadir = p;
		touristPackage = this.panelDisennadorPaqueteTuristicoModalidadTransporteAnnadir.getTouristPackage();
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

		tableHoursKilometersAvaible = new JTable();
		tableHoursKilometersAvaible.setRowHeight(30);
		tableHoursKilometersAvaible.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableHoursKilometersAvaible.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		tableHoursKilometersAvaible.getTableHeader().setForeground(Color.black);
		tableHoursKilometersAvaible.getTableHeader().setBackground(SystemColor.black);
		tableHoursKilometersAvaible.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadosBotones();
			}
		});
		tableHoursKilometersAvaible.setModel(new ModeloTablaTransportModalityHoursKilometers());
		scrollPane.setViewportView(tableHoursKilometersAvaible);

		JPanel panelAssignedTransports = new JPanel();
		panelAssignedTransports.setOpaque(false);
		panelAssignedTransports.setBounds(10, 352, 951, 173);
		add(panelAssignedTransports);
		panelAssignedTransports.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panelAssignedTransports.add(scrollPane_1, BorderLayout.CENTER);

		tableHoursKilometersAssigned = new JTable();
		tableHoursKilometersAssigned.setRowHeight(30);
		tableHoursKilometersAssigned.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableHoursKilometersAssigned.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		tableHoursKilometersAssigned.getTableHeader().setForeground(Color.black);
		tableHoursKilometersAssigned .getTableHeader().setBackground(SystemColor.black);
		tableHoursKilometersAssigned.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadosBotones();
			}
		});
		tableHoursKilometersAssigned.setModel(new ModeloTablaTransportModalityHoursKilometers());
		scrollPane_1.setViewportView(tableHoursKilometersAssigned);



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

		JLabel lblNewLabel = new JLabel("Cost Kilometers Rout:");
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 21, 216, 20);
		add(lblNewLabel);

		JLabel lblMin_3 = new JLabel("Min:");
		lblMin_3.setForeground(SystemColor.textHighlightText);
		lblMin_3.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_3.setBounds(70, 49, 30, 14);
		add(lblMin_3);

		JLabel lblMax_3 = new JLabel("Max:");
		lblMax_3.setForeground(SystemColor.textHighlightText);
		lblMax_3.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_3.setBounds(70, 71, 40, 14);
		add(lblMax_3);

		spinnerCostKilometersRoutMinAvaible = new JSpinner();
		spinnerCostKilometersRoutMinAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableHoursKilometers(); // se actualiza la tabla de las modalidades disponibles
			}
		});
		spinnerCostKilometersRoutMinAvaible.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostKilometersRoutMinAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersRoutMinAvaible.setBounds(105, 46, 53, 20);
		add(spinnerCostKilometersRoutMinAvaible);

		spinnerCostKilometersRoutMaxAvaible = new JSpinner();
		spinnerCostKilometersRoutMaxAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableHoursKilometers(); // se actualiza la tabla de las modalidades disponibles		
			}
		});
		spinnerCostKilometersRoutMaxAvaible.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostKilometersRoutMaxAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersRoutMaxAvaible.setBounds(105, 68, 53, 20);
		add(spinnerCostKilometersRoutMaxAvaible);

		JLabel lblMealPlan = new JLabel("Cost Hours:");
		lblMealPlan.setForeground(SystemColor.textHighlightText);
		lblMealPlan.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMealPlan.setBounds(211, 18, 112, 27);
		add(lblMealPlan);

		JLabel lblMin_2 = new JLabel("Min:");
		lblMin_2.setForeground(SystemColor.textHighlightText);
		lblMin_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_2.setBounds(221, 49, 30, 14);
		add(lblMin_2);

		JLabel lblMax_2 = new JLabel("Max:");
		lblMax_2.setForeground(SystemColor.textHighlightText);
		lblMax_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_2.setBounds(221, 71, 40, 14);
		add(lblMax_2);

		spinnerCostHoursMinAvaible = new JSpinner();
		spinnerCostHoursMinAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableHoursKilometers(); // se actualiza la tabla de las modalidades disponibles
			}
		});
		spinnerCostHoursMinAvaible.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostHoursMinAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostHoursMinAvaible.setBounds(256, 46, 53, 20);
		add(spinnerCostHoursMinAvaible);

		spinnerCostHoursMaxAvaible = new JSpinner();
		spinnerCostHoursMaxAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableHoursKilometers(); // se actualiza la tabla de las modalidades disponibles
			}
		});
		spinnerCostHoursMaxAvaible.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostHoursMaxAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostHoursMaxAvaible.setBounds(256, 68, 53, 20);
		add(spinnerCostHoursMaxAvaible);

		JLabel lblMin = new JLabel("Min:");
		lblMin.setForeground(SystemColor.textHighlightText);
		lblMin.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin.setBounds(384, 49, 30, 14);
		add(lblMin);

		JLabel lblMax = new JLabel("Max:");
		lblMax.setForeground(SystemColor.textHighlightText);
		lblMax.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax.setBounds(384, 71, 40, 14);
		add(lblMax);

		JLabel lblCantDaysAccommodation = new JLabel("Cost Kilometers Rout Addi:");
		lblCantDaysAccommodation.setForeground(SystemColor.textHighlightText);
		lblCantDaysAccommodation.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCantDaysAccommodation.setBounds(319, 16, 252, 30);
		add(lblCantDaysAccommodation);

		spinnerCostKilometersRoutAdditionalsMinAvaible = new JSpinner();
		spinnerCostKilometersRoutAdditionalsMinAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableHoursKilometers(); // se actualiza la tabla de las modalidades disponibles
			}
		});
		spinnerCostKilometersRoutAdditionalsMinAvaible.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostKilometersRoutAdditionalsMinAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersRoutAdditionalsMinAvaible.setBounds(419, 46, 53, 20);
		add(spinnerCostKilometersRoutAdditionalsMinAvaible);

		spinnerCostKilometersRoutAdditionalsMaxAvaible = new JSpinner();
		spinnerCostKilometersRoutAdditionalsMaxAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableHoursKilometers(); // se actualiza la tabla de las modalidades disponibles
			}
		});
		spinnerCostKilometersRoutAdditionalsMaxAvaible.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostKilometersRoutAdditionalsMaxAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersRoutAdditionalsMaxAvaible.setBounds(419, 68, 53, 20);
		add(spinnerCostKilometersRoutAdditionalsMaxAvaible);

		JLabel lblCostHoursAddi = new JLabel("Cost Hours Addi:");
		lblCostHoursAddi.setHorizontalAlignment(SwingConstants.LEFT);
		lblCostHoursAddi.setForeground(SystemColor.textHighlightText);
		lblCostHoursAddi.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCostHoursAddi.setBounds(565, 21, 157, 20);
		add(lblCostHoursAddi);

		JLabel lblMax_1_1 = new JLabel("Min:");
		lblMax_1_1.setForeground(SystemColor.textHighlightText);
		lblMax_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_1_1.setBounds(589, 49, 40, 14);
		add(lblMax_1_1);

		JLabel lblMin_1_1 = new JLabel("Max:");
		lblMin_1_1.setForeground(SystemColor.textHighlightText);
		lblMin_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_1_1.setBounds(589, 71, 40, 14);
		add(lblMin_1_1);

		spinnerCostHoursAdditionalsMinAvaible = new JSpinner();
		spinnerCostHoursAdditionalsMinAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableHoursKilometers(); // se actualiza la tabla de las modalidades disponibles
			}
		});
		spinnerCostHoursAdditionalsMinAvaible.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostHoursAdditionalsMinAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostHoursAdditionalsMinAvaible.setBounds(628, 46, 53, 20);
		add(spinnerCostHoursAdditionalsMinAvaible);

		spinnerCostHoursAdditionalsMaxAvaible = new JSpinner();
		spinnerCostHoursAdditionalsMaxAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableHoursKilometers(); // se actualiza la tabla de las modalidades disponibles
			}
		});
		spinnerCostHoursAdditionalsMaxAvaible.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostHoursAdditionalsMaxAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostHoursAdditionalsMaxAvaible.setBounds(629, 68, 53, 20);
		add(spinnerCostHoursAdditionalsMaxAvaible);

		JLabel lblMax_1 = new JLabel("Min:");
		lblMax_1.setForeground(SystemColor.textHighlightText);
		lblMax_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_1.setBounds(732, 49, 40, 14);
		add(lblMax_1);

		JLabel lblMin_1 = new JLabel("Max:");
		lblMin_1.setForeground(SystemColor.textHighlightText);
		lblMin_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_1.setBounds(732, 71, 40, 14);
		add(lblMin_1);

		JLabel lblPrice = new JLabel("Plan Price:");
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setForeground(SystemColor.textHighlightText);
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPrice.setBounds(732, 21, 103, 20);
		add(lblPrice);

		spinnerPriceMinAvaible = new JSpinner();
		spinnerPriceMinAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableHoursKilometers(); // se actualiza la tabla de las modalidades disponibles
			}
		});
		spinnerPriceMinAvaible.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMinAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMinAvaible.setBounds(771, 46, 53, 20);
		add(spinnerPriceMinAvaible);

		spinnerPriceMaxAvaible = new JSpinner();
		spinnerPriceMaxAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableHoursKilometers(); // se actualiza la tabla de las modalidades disponibles
			}
		});
		spinnerPriceMaxAvaible.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMaxAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMaxAvaible.setBounds(772, 68, 53, 20);
		add(spinnerPriceMaxAvaible);

		JLabel lblAssignedTransports = new JLabel("ASSIGNED TRANSPORTS");
		lblAssignedTransports.setForeground(SystemColor.textHighlightText);
		lblAssignedTransports.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAssignedTransports.setBounds(10, 262, 275, 19);
		add(lblAssignedTransports);

		JLabel lblNewLabel_1 = new JLabel("Cost Kilometers Rout:");
		lblNewLabel_1.setForeground(SystemColor.textHighlightText);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 285, 216, 20);
		add(lblNewLabel_1);

		JLabel lblMin_3_1 = new JLabel("Min:");
		lblMin_3_1.setForeground(SystemColor.textHighlightText);
		lblMin_3_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_3_1.setBounds(80, 313, 30, 14);
		add(lblMin_3_1);

		JLabel lblMax_3_1 = new JLabel("Max:");
		lblMax_3_1.setForeground(SystemColor.textHighlightText);
		lblMax_3_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_3_1.setBounds(80, 334, 40, 14);
		add(lblMax_3_1);

		spinnerCostKilometersRoutMaxAssigned = new JSpinner();
		spinnerCostKilometersRoutMaxAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedHoursKilometers(); // se actualiza la tabla de las modalidades asignadas
			}
		});
		spinnerCostKilometersRoutMaxAssigned.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostKilometersRoutMaxAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersRoutMaxAssigned.setBounds(115, 331, 53, 20);
		add(spinnerCostKilometersRoutMaxAssigned);

		spinnerCostKilometersRoutMinAssigned = new JSpinner();
		spinnerCostKilometersRoutMinAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedHoursKilometers(); // se actualiza la tabla de las modalidades asignadas
			}
		});
		spinnerCostKilometersRoutMinAssigned.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostKilometersRoutMinAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersRoutMinAssigned.setBounds(115, 310, 53, 20);
		add(spinnerCostKilometersRoutMinAssigned);

		JLabel lblMealPlan_1 = new JLabel("Cost Hours:");
		lblMealPlan_1.setForeground(SystemColor.textHighlightText);
		lblMealPlan_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMealPlan_1.setBounds(211, 282, 112, 27);
		add(lblMealPlan_1);

		JLabel lblMin_2_1 = new JLabel("Min:");
		lblMin_2_1.setForeground(SystemColor.textHighlightText);
		lblMin_2_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_2_1.setBounds(221, 313, 30, 14);
		add(lblMin_2_1);

		spinnerCostHoursMinAssigned = new JSpinner();
		spinnerCostHoursMinAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedHoursKilometers(); // se actualiza la tabla de las modalidades asignadas
			}
		});
		spinnerCostHoursMinAssigned.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostHoursMinAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostHoursMinAssigned.setBounds(256, 310, 53, 20);
		add(spinnerCostHoursMinAssigned);

		spinnerCostHoursMaxAssigned = new JSpinner();
		spinnerCostHoursMaxAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedHoursKilometers(); // se actualiza la tabla de las modalidades asignadas
			}
		});
		spinnerCostHoursMaxAssigned.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostHoursMaxAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostHoursMaxAssigned.setBounds(256, 331, 53, 20);
		add(spinnerCostHoursMaxAssigned);

		JLabel lblMax_2_1 = new JLabel("Max:");
		lblMax_2_1.setForeground(SystemColor.textHighlightText);
		lblMax_2_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_2_1.setBounds(221, 334, 40, 14);
		add(lblMax_2_1);

		JLabel lblCantDaysAccommodation_1 = new JLabel("Cost Kilometers Rout Addi:");
		lblCantDaysAccommodation_1.setForeground(SystemColor.textHighlightText);
		lblCantDaysAccommodation_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCantDaysAccommodation_1.setBounds(319, 280, 252, 30);
		add(lblCantDaysAccommodation_1);

		JLabel lblMin_4 = new JLabel("Min:");
		lblMin_4.setForeground(SystemColor.textHighlightText);
		lblMin_4.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_4.setBounds(384, 313, 30, 14);
		add(lblMin_4);

		spinnerCostKilometersRoutAdditionalsMinAssigned = new JSpinner();
		spinnerCostKilometersRoutAdditionalsMinAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedHoursKilometers(); // se actualiza la tabla de las modalidades asignadas
			}
		});
		spinnerCostKilometersRoutAdditionalsMinAssigned.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostKilometersRoutAdditionalsMinAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersRoutAdditionalsMinAssigned.setBounds(419, 310, 53, 20);
		add(spinnerCostKilometersRoutAdditionalsMinAssigned);

		spinnerCostKilometersRoutAdditionalsMaxAssigned = new JSpinner();
		spinnerCostKilometersRoutAdditionalsMaxAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedHoursKilometers(); // se actualiza la tabla de las modalidades asignadas
			}
		});
		spinnerCostKilometersRoutAdditionalsMaxAssigned.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostKilometersRoutAdditionalsMaxAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostKilometersRoutAdditionalsMaxAssigned.setBounds(419, 331, 53, 20);
		add(spinnerCostKilometersRoutAdditionalsMaxAssigned);

		JLabel lblMax_4 = new JLabel("Max:");
		lblMax_4.setForeground(SystemColor.textHighlightText);
		lblMax_4.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_4.setBounds(384, 334, 40, 14);
		add(lblMax_4);

		JLabel lblCostHoursAddi_1 = new JLabel("Cost Hours Addi:");
		lblCostHoursAddi_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCostHoursAddi_1.setForeground(SystemColor.textHighlightText);
		lblCostHoursAddi_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCostHoursAddi_1.setBounds(565, 285, 157, 20);
		add(lblCostHoursAddi_1);

		JLabel lblMax_1_1_1 = new JLabel("Min:");
		lblMax_1_1_1.setForeground(SystemColor.textHighlightText);
		lblMax_1_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_1_1_1.setBounds(589, 313, 40, 14);
		add(lblMax_1_1_1);

		spinnerCostHoursAdditionalsMinAssigned = new JSpinner();
		spinnerCostHoursAdditionalsMinAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedHoursKilometers(); // se actualiza la tabla de las modalidades asignadas
			}
		});
		spinnerCostHoursAdditionalsMinAssigned.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostHoursAdditionalsMinAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostHoursAdditionalsMinAssigned.setBounds(628, 310, 53, 20);
		add(spinnerCostHoursAdditionalsMinAssigned);

		spinnerCostHoursAdditionalsMaxAssigned = new JSpinner();
		spinnerCostHoursAdditionalsMaxAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedHoursKilometers(); // se actualiza la tabla de las modalidades asignadas
			}
		});
		spinnerCostHoursAdditionalsMaxAssigned.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCostHoursAdditionalsMaxAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCostHoursAdditionalsMaxAssigned.setBounds(629, 331, 53, 20);
		add(spinnerCostHoursAdditionalsMaxAssigned);

		JLabel lblMin_1_1_1 = new JLabel("Max:");
		lblMin_1_1_1.setForeground(SystemColor.textHighlightText);
		lblMin_1_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_1_1_1.setBounds(589, 334, 40, 14);
		add(lblMin_1_1_1);

		JLabel lblPrice_1 = new JLabel("Plan Price:");
		lblPrice_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice_1.setForeground(SystemColor.textHighlightText);
		lblPrice_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPrice_1.setBounds(732, 285, 103, 20);
		add(lblPrice_1);

		JLabel lblMax_1_2 = new JLabel("Min:");
		lblMax_1_2.setForeground(SystemColor.textHighlightText);
		lblMax_1_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_1_2.setBounds(732, 313, 40, 14);
		add(lblMax_1_2);

		spinnerPriceMinAssigned = new JSpinner();
		spinnerPriceMinAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedHoursKilometers(); // se actualiza la tabla de las modalidades asignadas
			}
		});
		spinnerPriceMinAssigned.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMinAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMinAssigned.setBounds(771, 310, 53, 20);
		add(spinnerPriceMinAssigned);

		spinnerPriceMaxAssigned = new JSpinner();
		spinnerPriceMaxAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedHoursKilometers(); // se actualiza la tabla de las modalidades asignadas
			}
		});
		spinnerPriceMaxAssigned.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMaxAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMaxAssigned.setBounds(772, 331, 53, 20);
		add(spinnerPriceMaxAssigned);

		JLabel lblMin_1_2 = new JLabel("Max:");
		lblMin_1_2.setForeground(SystemColor.textHighlightText);
		lblMin_1_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_1_2.setBounds(732, 334, 40, 14);
		add(lblMin_1_2);

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
		lblRestoreFiltersAvaible.setIcon(new ImageIcon(PanelPackageDisignerContenedorHoursKilometers.class.getResource("/images/Restart.png")));
		lblRestoreFiltersAvaible.setOpaque(true);
		lblRestoreFiltersAvaible.setHorizontalAlignment(SwingConstants.CENTER);
		lblRestoreFiltersAvaible.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblRestoreFiltersAvaible.setBackground(new Color(18, 95, 115));
		lblRestoreFiltersAvaible.setBounds(855, 30, 53, 43);
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
		lblRestoreFiltersAssigned.setIcon(new ImageIcon(PanelPackageDisignerContenedorHoursKilometers.class.getResource("/images/Restart.png")));
		lblRestoreFiltersAssigned.setOpaque(true);
		lblRestoreFiltersAssigned.setHorizontalAlignment(SwingConstants.CENTER);
		lblRestoreFiltersAssigned.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblRestoreFiltersAssigned.setBackground(new Color(18, 95, 115));
		lblRestoreFiltersAssigned.setBounds(855, 298, 53, 43);
		add(lblRestoreFiltersAssigned);

		this.actualizarEstadosBotones();
	}

	// Metodos de actualizacion de la tabla  tableAviavleAccommodations
	private void actualizarTablaAviableHoursKilometers () {	
		this.actualizarTablaAviableHoursKilometers(Controller.getInstancie().getTouristAgency().getTransportModalityHoursKilometers((Double) this.spinnerCostKilometersRoutMinAvaible.getValue(), (Double) this.spinnerCostKilometersRoutMaxAvaible.getValue(), 
				(Double) this.spinnerCostHoursMinAvaible.getValue(), (Double) this.spinnerCostHoursMaxAvaible.getValue(), (Double) this.spinnerCostKilometersRoutAdditionalsMinAvaible.getValue(), 
				(Double) this.spinnerCostKilometersRoutAdditionalsMaxAvaible.getValue(), (Double) this.spinnerCostHoursAdditionalsMinAvaible.getValue(), (Double) this.spinnerCostHoursAdditionalsMaxAvaible.getValue(), 
				(Double) this.spinnerPriceMinAvaible.getValue(), (Double) this.spinnerPriceMaxAvaible.getValue()));
	}


	private void actualizarTablaAviableHoursKilometers(ArrayList<Modality> modalitys){
		reiniciarTable(this.tableHoursKilometersAvaible);


		for (Modality mod : modalitys) {
			if (!touristPackage.isContainsModality(Modality.hoursKilometers, mod))
				((ModeloTablaTransportModalityHoursKilometers) this.tableHoursKilometersAvaible.getModel()).addElement((HoursKilometers) mod); 
		}
	}

	// Fin de Metodos de actualizacion de la tabla  tableAviavleAccommodations


	private void actualizarTablaAssignedHoursKilometers () {	
		this.actualizarTablaAssignedHoursKilometers(this.touristPackage.getTransportModalitysHoursKilometers((Double) this.spinnerCostKilometersRoutMinAssigned.getValue(), (Double) this.spinnerCostKilometersRoutMaxAssigned.getValue(), 
				(Double) this.spinnerCostHoursMinAssigned.getValue(), (Double) this.spinnerCostHoursMaxAssigned.getValue(), (Double) this.spinnerCostKilometersRoutAdditionalsMinAssigned.getValue(), 
				(Double) this.spinnerCostKilometersRoutAdditionalsMaxAssigned.getValue(), (Double) this.spinnerCostHoursAdditionalsMinAssigned.getValue(), (Double) this.spinnerCostHoursAdditionalsMaxAssigned.getValue(), 
				(Double) this.spinnerPriceMinAssigned.getValue(), (Double) this.spinnerPriceMaxAssigned.getValue()));
	}

	// Metodos de actualizacion de la tabla  tableAssignedAccommodations
	private void actualizarTablaAssignedHoursKilometers(ArrayList<Modality> modalitys){
		reiniciarTable(this.tableHoursKilometersAssigned);


		for (Modality mod : modalitys) {
			((ModeloTablaTransportModalityHoursKilometers) this.tableHoursKilometersAssigned.getModel()).addElement((HoursKilometers) mod);
		}
	}

	// Fin de Metodos de actualizacion de la tabla  tableAssignedAccommodations



	private void reiniciarTable(JTable table){

		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaTransportModalityHoursKilometers) table.getModel()).deleteElement(i);
		}
	}


	// Operaciones

	private void asign () throws SQLException  { // Metodo para asignar las modalidades seleccionadas al paquete
		int [] rows = this.tableHoursKilometersAvaible.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.touristPackage.getId() != -1) 
				this.touristPackage.addModality(((ModeloTablaTransportModalityHoursKilometers) this.tableHoursKilometersAvaible.getModel()).getElement(rows[i])); // se asigna a las base de datos y a la logica del negocio
			else
				this.touristPackage.addModalityLogic(((ModeloTablaTransportModalityHoursKilometers) this.tableHoursKilometersAvaible.getModel()).getElement(rows[i])); // se asigna solo en la logica del negocio
		}

		this.actualizarTablas(); // se actualiza la informacion de las dos tablas
	}

	public void actualizarTablas () {
		this.actualizarTablaAviableHoursKilometers();
		this.actualizarTablaAssignedHoursKilometers();
		this.actualizarEstadosBotones();
	}

	private void deny () throws SQLException { // Metodo para desasignar las modalidades seleccionadas del paquete
		int [] rows = this.tableHoursKilometersAssigned.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.touristPackage.getId() != -1) 
				this.touristPackage.deleteModality(((ModeloTablaTransportModalityHoursKilometers) this.tableHoursKilometersAssigned.getModel()).getElement(rows[i])); // se elimina a las base de datos y a la logica del negocio
			else
				this.touristPackage.deleteModalityLogic(((ModeloTablaTransportModalityHoursKilometers) this.tableHoursKilometersAssigned.getModel()).getElement(rows[i])); // se elimina solo en la logica del negocio
		}

		this.actualizarTablas(); // se actualiza la informacion de las dos tablas
	}


	// Metodos para la restauracion de los Filtros

	private void restoreFiltersAviable () { // Metodo para restaurar los filtros de la tabla de modalidades disponibles
		this.isRestoreFiltersAvaible = true; // se indica que va a empezar una restauracion de los filtros
		this.spinnerPriceMaxAvaible.setValue(0.0);
		this.spinnerPriceMinAvaible.setValue(0.0);
		this.spinnerCostHoursAdditionalsMaxAvaible.setValue(0.0);
		this.spinnerCostHoursAdditionalsMinAvaible.setValue(0.0);
		this.spinnerCostHoursMaxAvaible.setValue(0.0);
		this.spinnerCostHoursMinAvaible.setValue(0.0);
		this.spinnerCostKilometersRoutMaxAvaible.setValue(0.0);
		this.spinnerCostKilometersRoutMinAvaible.setValue(0.0);
		this.spinnerCostKilometersRoutAdditionalsMaxAvaible.setValue(0.0);
		this.spinnerCostKilometersRoutAdditionalsMinAvaible.setValue(0.0);
		this.actualizarTablaAviableHoursKilometers(); // se actualiza la tabla de las modalidades disponibles
		this.isRestoreFiltersAvaible = false; // se indica que se terminó la restauracion de los filtros
	}

	private void restoreFiltersAssigned () { // Metodo para restaurar los filtros de la tabla de modalidades asignadas
		this.isRestoreFiltersAssigned = true; // se indica que va a empezar una restauracion de los filtros
		this.spinnerPriceMaxAssigned.setValue(0.0);
		this.spinnerPriceMinAssigned.setValue(0.0);
		this.spinnerCostHoursAdditionalsMaxAssigned.setValue(0.0);
		this.spinnerCostHoursAdditionalsMinAssigned.setValue(0.0);
		this.spinnerCostHoursMaxAssigned.setValue(0.0);
		this.spinnerCostHoursMinAssigned.setValue(0.0);
		this.spinnerCostKilometersRoutMaxAssigned.setValue(0.0);
		this.spinnerCostKilometersRoutMinAssigned.setValue(0.0);
		this.spinnerCostKilometersRoutAdditionalsMaxAssigned.setValue(0.0);
		this.spinnerCostKilometersRoutAdditionalsMinAssigned.setValue(0.0);
		this.actualizarTablaAssignedHoursKilometers(); // se actualiza la tabla de las modalidades disponibles
		this.isRestoreFiltersAssigned = false; // se indica que se terminó la restauracion de los filtros
	}

	// Fin de Metodos para la restauracion de los Filtros

	// Fin de Operaciones


	public void actualizarEstadosBotones () {
		this.estadoLblAsign();
		this.estadoLblDeny();
	}

	private void estadoLblAsign () {
		if (tableHoursKilometersAvaible.getSelectedRowCount() != 0)
			lblAnnadir.setEnabled(true);
		else
			lblAnnadir.setEnabled(false);
	}

	private void estadoLblDeny () {
		if (tableHoursKilometersAssigned.getSelectedRowCount() != 0)
			lblDenegar.setEnabled(true);
		else
			lblDenegar.setEnabled(false);
	}



	@Override
	public void clearSelectionTable() {
		this.tableHoursKilometersAssigned.clearSelection();
		this.tableHoursKilometersAvaible.clearSelection();
	}
}
