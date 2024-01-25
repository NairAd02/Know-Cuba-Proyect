package JPanels;

import java.awt.Color;
import javax.swing.JPanel;
import logica.AccommodationModality;
import logica.Controller;
import logica.Modality;
import logica.TouristPackage;
import modelosTablas.ModeloTablaAccommodationModality;
import utils.AusentFilter;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import JFrames.FrameInformacionPaquete;
import dao.HotelModalityDAO;
import dao.MealPlanDAO;
import dao.TypeOfRoomDAO;
import javax.swing.JComboBox;
import logica.TypeOfRoom;
import logica.MealPlan;
import logica.HotelModality;
import javax.swing.JSpinner;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.MatteBorder;

public class PanelDisennadorPaqueteTuristicoModalidadAlojamientoAnnadir extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableAvailableAccommodation;
	private JTable tableAssignedAccommodations;
	private JLabel lblAnnadir;
	private JLabel lblDenegar;
	private FrameInformacionPaquete frameInformacionPaquete;
	private TouristPackage touristPackage;
	private int mouseX, mouseY;
	private JComboBox<TypeOfRoom> comboBoxTypeOfRoomAvaible;
	private JComboBox<MealPlan> comboBoxMealPlanAvaible;
	private JComboBox<HotelModality> comboBoxHotelModalityAvaible;
	private JSpinner spinnerCantDaysAccommodationMinAvaible;
	private JSpinner spinnerCantDaysAccommodationMaxAvaible;
	private JSpinner spinnerPriceMinAvaible;
	private JSpinner spinnerPriceMaxAvaible;
	private JComboBox<TypeOfRoom> comboBoxTypeOfRoomAssigned;
	private JComboBox<MealPlan> comboBoxMealPlanAssigned;
	private JComboBox<HotelModality> comboBoxHotelModalityAssigned;
	private JSpinner spinnerCantDaysAccommodationMinAssigned;
	private JSpinner spinnerCantDaysAccommodationMaxAssigned;
	private JSpinner spinnerPriceMinAssigned;
	private JSpinner spinnerPriceMaxAssigned;
	private JLabel lblAtras;
	private JLabel lblRestoreFiltersAvaible;
	private JLabel lblRestoreFiltersAssigned;
	private boolean isRestoreFiltersAvaivle, isRestoreFiltersAssigned;


	public PanelDisennadorPaqueteTuristicoModalidadAlojamientoAnnadir(FrameInformacionPaquete f) {
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
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
				tableAssignedAccommodations.clearSelection();
				tableAvailableAccommodation.clearSelection();
				actualizarEstadosBotones();
			}
		});
		this.isRestoreFiltersAssigned = false;
		this.isRestoreFiltersAvaivle = false;
		this.frameInformacionPaquete = f;
		this.touristPackage = this.frameInformacionPaquete.getTouristPackage();

		setBounds(0, 0, 971, 591);

		setBackground(new Color(18, 95, 115));



		setLayout(null);

		JLabel lblAvailableAccommodation = new JLabel("AVAILABLE ACCOMMODATION");
		lblAvailableAccommodation.setForeground(SystemColor.textHighlightText);
		lblAvailableAccommodation.setFont(new Font("Arial Black", Font.PLAIN, 16));
		setBackground(new Color(18, 95, 115));
		lblAvailableAccommodation.setBounds(10, 55, 275, 20);
		add(lblAvailableAccommodation);

		lblAnnadir = new JLabel("");
		lblAnnadir.setIcon(new ImageIcon(PanelDisennadorPaqueteTuristicoModalidadAlojamientoAnnadir.class.getResource("/images/Plus.png")));
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
		lblAnnadir.setOpaque(true);
		lblAnnadir.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(new Color(18, 95, 115));
		lblAnnadir.setBounds(908, 85, 53, 43);
		add(lblAnnadir);

		JLabel lblAssignedAccommodations = new JLabel("ASSIGNED ACCOMMODATIONS");
		lblAssignedAccommodations.setForeground(SystemColor.textHighlightText);
		lblAssignedAccommodations.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAssignedAccommodations.setBounds(10, 318, 275, 19);
		add(lblAssignedAccommodations);

		lblDenegar = new JLabel("");
		lblDenegar.setIcon(new ImageIcon(PanelDisennadorPaqueteTuristicoModalidadAlojamientoAnnadir.class.getResource("/images/Trash.png")));
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
		lblDenegar.setOpaque(true);
		lblDenegar.setHorizontalAlignment(SwingConstants.CENTER);
		lblDenegar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDenegar.setBackground(new Color(18, 95, 115));
		lblDenegar.setBounds(908, 353, 53, 43);
		add(lblDenegar);

		JPanel panelAvailableAccommodation = new JPanel();
		panelAvailableAccommodation.setBounds(10, 144, 951, 173);
		add(panelAvailableAccommodation);
		panelAvailableAccommodation.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelAvailableAccommodation.add(scrollPane, BorderLayout.CENTER);

		tableAvailableAccommodation = new JTable();
		tableAvailableAccommodation.setRowHeight(30);
		tableAvailableAccommodation.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableAvailableAccommodation.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		tableAvailableAccommodation.getTableHeader().setForeground(Color.black);
		tableAvailableAccommodation.getTableHeader().setBackground(SystemColor.black);
		tableAvailableAccommodation.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadosBotones();
			}
		});
		tableAvailableAccommodation.setModel(new ModeloTablaAccommodationModality());
		scrollPane.setViewportView(tableAvailableAccommodation);

		JPanel panelAssignedAccommodations = new JPanel();
		panelAssignedAccommodations.setOpaque(false);
		panelAssignedAccommodations.setBounds(10, 407, 951, 173);
		add(panelAssignedAccommodations);
		panelAssignedAccommodations.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panelAssignedAccommodations.add(scrollPane_1, BorderLayout.CENTER);

		tableAssignedAccommodations = new JTable();
		tableAssignedAccommodations.setRowHeight(30);
		tableAssignedAccommodations.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableAssignedAccommodations.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		tableAssignedAccommodations.getTableHeader().setForeground(Color.black);
		tableAssignedAccommodations.getTableHeader().setBackground(SystemColor.black);
		tableAssignedAccommodations.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadosBotones();
			}
		});
		tableAssignedAccommodations.setModel(new ModeloTablaAccommodationModality());
		scrollPane_1.setViewportView(tableAssignedAccommodations);

		JLabel lblNewLabel_1 = new JLabel("Type Of Room:");
		lblNewLabel_1.setForeground(SystemColor.textHighlightText);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 85, 133, 19);
		add(lblNewLabel_1);

		comboBoxTypeOfRoomAvaible = new JComboBox<TypeOfRoom>();
		comboBoxTypeOfRoomAssigned = new JComboBox<TypeOfRoom>();
		// Se llenan los comboBox creados
		try {
			this.llenarComboBoxsTypeOfRooms();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		comboBoxTypeOfRoomAvaible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isRestoreFiltersAvaivle)
					actualizarTablaAviableAccommodations(); // Se actualiza la informacion de la tabla de modalidades disponibles
			}
		});
		comboBoxTypeOfRoomAvaible.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxTypeOfRoomAvaible.setBounds(10, 111, 133, 22);
		add(comboBoxTypeOfRoomAvaible);


		comboBoxTypeOfRoomAssigned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedAccommodations(); // Se actualiza la informacion de la tabla de modalidades asignadas
			}
		});
		comboBoxTypeOfRoomAssigned.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxTypeOfRoomAssigned.setBounds(10, 374, 133, 22);
		add(comboBoxTypeOfRoomAssigned);

		JLabel lblMealPlan_1 = new JLabel("Meal Plan:");
		lblMealPlan_1.setForeground(SystemColor.textHighlightText);
		lblMealPlan_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMealPlan_1.setBounds(167, 87, 103, 14);
		add(lblMealPlan_1);

		comboBoxMealPlanAvaible = new JComboBox<MealPlan>();
		comboBoxMealPlanAssigned = new JComboBox<MealPlan>();

		// Se llenan los comboBox creados
		try {
			this.llenarComboBoxsMealPlans();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		comboBoxMealPlanAvaible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isRestoreFiltersAvaivle)
					actualizarTablaAviableAccommodations(); // Se actualiza la informacion de la tabla de modalidades disponibles
			}
		});
		comboBoxMealPlanAvaible.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxMealPlanAvaible.setBounds(167, 111, 103, 22);
		add(comboBoxMealPlanAvaible);


		comboBoxMealPlanAssigned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedAccommodations(); // Se actualiza la informacion de la tabla de modalidades asignadas
			}
		});
		comboBoxMealPlanAssigned.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxMealPlanAssigned.setBounds(167, 374, 103, 22);
		add(comboBoxMealPlanAssigned);

		JLabel lblHotelModality_1 = new JLabel("Hotel Modality:");
		lblHotelModality_1.setForeground(SystemColor.textHighlightText);
		lblHotelModality_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblHotelModality_1.setBounds(295, 85, 133, 19);
		add(lblHotelModality_1);

		comboBoxHotelModalityAvaible = new JComboBox<HotelModality>();
		comboBoxHotelModalityAssigned = new JComboBox<HotelModality>();
		// Se llenan los comboBox creados
		try {
			this.llenarComboBoxHotelModality();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		comboBoxHotelModalityAvaible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isRestoreFiltersAvaivle)
					actualizarTablaAviableAccommodations(); // Se actualiza la informacion de la tabla de modalidades disponibles
			}
		});
		comboBoxHotelModalityAvaible.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxHotelModalityAvaible.setBounds(295, 111, 133, 22);
		add(comboBoxHotelModalityAvaible);


		comboBoxHotelModalityAssigned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedAccommodations(); // Se actualiza la informacion de la tabla de modalidades asignadas
			}
		});
		comboBoxHotelModalityAssigned.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxHotelModalityAssigned.setBounds(295, 374, 133, 22);
		add(comboBoxHotelModalityAssigned);

		JLabel lblCantDaysAccommodation_1 = new JLabel("Days Accommodation:");
		lblCantDaysAccommodation_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantDaysAccommodation_1.setForeground(SystemColor.textHighlightText);
		lblCantDaysAccommodation_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCantDaysAccommodation_1.setBounds(438, 80, 205, 22);
		add(lblCantDaysAccommodation_1);

		spinnerCantDaysAccommodationMinAvaible = new JSpinner();
		spinnerCantDaysAccommodationMinAvaible.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		spinnerCantDaysAccommodationMinAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaivle)
					actualizarTablaAviableAccommodations(); // Se actualiza la informacion de la tabla de modalidades disponibles
			}
		});
		spinnerCantDaysAccommodationMinAvaible.setFont(new Font("Dialog", Font.PLAIN, 14));
		spinnerCantDaysAccommodationMinAvaible.setBounds(473, 113, 53, 20);
		add(spinnerCantDaysAccommodationMinAvaible);

		JLabel lblMin_2 = new JLabel("Min:");
		lblMin_2.setForeground(SystemColor.textHighlightText);
		lblMin_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_2.setBounds(438, 116, 30, 14);
		add(lblMin_2);

		JLabel lblMax_2 = new JLabel("Max:");
		lblMax_2.setForeground(SystemColor.textHighlightText);
		lblMax_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_2.setBounds(531, 115, 40, 14);
		add(lblMax_2);

		spinnerCantDaysAccommodationMaxAvaible = new JSpinner();
		spinnerCantDaysAccommodationMaxAvaible.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		spinnerCantDaysAccommodationMaxAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaivle)
					actualizarTablaAviableAccommodations(); // Se actualiza la informacion de la tabla de modalidades disponibles
			}
		});
		spinnerCantDaysAccommodationMaxAvaible.setFont(new Font("Dialog", Font.PLAIN, 14));
		spinnerCantDaysAccommodationMaxAvaible.setBounds(568, 113, 53, 20);
		add(spinnerCantDaysAccommodationMaxAvaible);

		JLabel lblMin_1_1 = new JLabel("Min:");
		lblMin_1_1.setForeground(SystemColor.textHighlightText);
		lblMin_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_1_1.setBounds(631, 115, 30, 14);
		add(lblMin_1_1);

		spinnerPriceMinAvaible = new JSpinner();
		spinnerPriceMinAvaible.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMinAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaivle)
					actualizarTablaAviableAccommodations(); // Se actualiza la informacion de la tabla de modalidades disponibles
			}
		});
		spinnerPriceMinAvaible.setFont(new Font("Dialog", Font.PLAIN, 14));
		spinnerPriceMinAvaible.setBounds(671, 112, 53, 20);
		add(spinnerPriceMinAvaible);

		spinnerPriceMaxAvaible = new JSpinner();
		spinnerPriceMaxAvaible.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMaxAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaivle)
					actualizarTablaAviableAccommodations(); // Se actualiza la informacion de la tabla de modalidades disponibles
			}
		});
		spinnerPriceMaxAvaible.setFont(new Font("Dialog", Font.PLAIN, 14));
		spinnerPriceMaxAvaible.setBounds(769, 112, 53, 20);
		add(spinnerPriceMaxAvaible);

		JLabel lblMax_1_1 = new JLabel("Max:");
		lblMax_1_1.setForeground(SystemColor.textHighlightText);
		lblMax_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_1_1.setBounds(730, 112, 40, 14);
		add(lblMax_1_1);

		JLabel lblPrice_1 = new JLabel("Plan Price:");
		lblPrice_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice_1.setForeground(SystemColor.textHighlightText);
		lblPrice_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPrice_1.setBounds(653, 79, 92, 30);
		add(lblPrice_1);



		JLabel lblNewLabel_1_1 = new JLabel("Type Of Room:");
		lblNewLabel_1_1.setForeground(SystemColor.textHighlightText);
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(10, 348, 133, 19);
		add(lblNewLabel_1_1);

		JLabel lblMealPlan_1_1 = new JLabel("Meal Plan:");
		lblMealPlan_1_1.setForeground(SystemColor.textHighlightText);
		lblMealPlan_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMealPlan_1_1.setBounds(167, 350, 103, 14);
		add(lblMealPlan_1_1);


		JLabel lblHotelModality_1_1 = new JLabel("Hotel Modality:");
		lblHotelModality_1_1.setForeground(SystemColor.textHighlightText);
		lblHotelModality_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblHotelModality_1_1.setBounds(295, 348, 133, 19);
		add(lblHotelModality_1_1);

		JLabel lblCantDaysAccommodation_1_1 = new JLabel("Days Accommodation:");
		lblCantDaysAccommodation_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantDaysAccommodation_1_1.setForeground(SystemColor.textHighlightText);
		lblCantDaysAccommodation_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCantDaysAccommodation_1_1.setBounds(438, 343, 205, 22);
		add(lblCantDaysAccommodation_1_1);

		JLabel lblMin_2_1 = new JLabel("Min:");
		lblMin_2_1.setForeground(SystemColor.textHighlightText);
		lblMin_2_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_2_1.setBounds(438, 379, 30, 14);
		add(lblMin_2_1);

		spinnerCantDaysAccommodationMinAssigned = new JSpinner();
		spinnerCantDaysAccommodationMinAssigned.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		spinnerCantDaysAccommodationMinAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedAccommodations(); // Se actualiza la informacion de la tabla de modalidades asignadas
			}
		});
		spinnerCantDaysAccommodationMinAssigned.setFont(new Font("Dialog", Font.PLAIN, 14));
		spinnerCantDaysAccommodationMinAssigned.setBounds(473, 376, 53, 20);
		add(spinnerCantDaysAccommodationMinAssigned);

		JLabel lblMax_2_1 = new JLabel("Max:");
		lblMax_2_1.setForeground(SystemColor.textHighlightText);
		lblMax_2_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_2_1.setBounds(531, 378, 40, 14);
		add(lblMax_2_1);

		spinnerCantDaysAccommodationMaxAssigned = new JSpinner();
		spinnerCantDaysAccommodationMaxAssigned.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		spinnerCantDaysAccommodationMaxAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedAccommodations(); // Se actualiza la informacion de la tabla de modalidades asignadas
			}
		});
		spinnerCantDaysAccommodationMaxAssigned.setFont(new Font("Dialog", Font.PLAIN, 14));
		spinnerCantDaysAccommodationMaxAssigned.setBounds(568, 376, 53, 20);
		add(spinnerCantDaysAccommodationMaxAssigned);

		JLabel lblMin_1_1_1 = new JLabel("Min:");
		lblMin_1_1_1.setForeground(SystemColor.textHighlightText);
		lblMin_1_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_1_1_1.setBounds(631, 378, 30, 14);
		add(lblMin_1_1_1);

		JLabel lblPrice_1_1 = new JLabel("Plan Price:");
		lblPrice_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice_1_1.setForeground(SystemColor.textHighlightText);
		lblPrice_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPrice_1_1.setBounds(653, 342, 92, 30);
		add(lblPrice_1_1);

		spinnerPriceMinAssigned = new JSpinner();
		spinnerPriceMinAssigned.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMinAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedAccommodations(); // Se actualiza la informacion de la tabla de modalidades asignadas
			}
		});
		spinnerPriceMinAssigned.setFont(new Font("Dialog", Font.PLAIN, 14));
		spinnerPriceMinAssigned.setBounds(671, 375, 53, 20);
		add(spinnerPriceMinAssigned);

		JLabel lblMax_1_1_1 = new JLabel("Max:");
		lblMax_1_1_1.setForeground(SystemColor.textHighlightText);
		lblMax_1_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_1_1_1.setBounds(730, 375, 40, 14);
		add(lblMax_1_1_1);

		spinnerPriceMaxAssigned = new JSpinner();
		spinnerPriceMaxAssigned.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMaxAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedAccommodations(); // Se actualiza la informacion de la tabla de modalidades asignadas
			}
		});
		spinnerPriceMaxAssigned.setFont(new Font("Dialog", Font.PLAIN, 14));
		spinnerPriceMaxAssigned.setBounds(769, 375, 53, 20);
		add(spinnerPriceMaxAssigned);

		lblAtras = new JLabel("");
		lblAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				frameInformacionPaquete.cambioDePanel(frameInformacionPaquete.getPanelInformacionPaquetes()); // se cambia de panel
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblAtras.setIcon(new ImageIcon(PanelDisennadorPaqueteTuristicoModalidadAlojamientoAnnadir.class.getResource("/images/Circled Right.png")));
		lblAtras.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtras.setForeground(Color.BLACK);
		lblAtras.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblAtras.setBackground(SystemColor.menu);
		lblAtras.setBounds(10, 11, 53, 43);
		add(lblAtras);
		
		lblRestoreFiltersAvaible = new JLabel("Restore Filters");
		lblRestoreFiltersAvaible.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				restoreFiltersAvaible();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		lblRestoreFiltersAvaible.setForeground(SystemColor.textHighlightText);
		lblRestoreFiltersAvaible.setFont(new Font("Dialog", Font.BOLD, 16));
		lblRestoreFiltersAvaible.setBounds(771, 68, 114, 20);
		add(lblRestoreFiltersAvaible);
		
		lblRestoreFiltersAssigned = new JLabel("Restore Filters");
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
		lblRestoreFiltersAssigned.setForeground(SystemColor.textHighlightText);
		lblRestoreFiltersAssigned.setFont(new Font("Dialog", Font.BOLD, 16));
		lblRestoreFiltersAssigned.setBounds(769, 333, 114, 20);
		add(lblRestoreFiltersAssigned);
		
		JLabel lblAccommodationSeccion = new JLabel("ACCOMMODATION SECCION");
		lblAccommodationSeccion.setForeground(SystemColor.textHighlightText);
		lblAccommodationSeccion.setFont(new Font("Dialog", Font.BOLD, 24));
		lblAccommodationSeccion.setBounds(318, 11, 335, 20);
		add(lblAccommodationSeccion);

		this.actualizarTablas(); // se actualiza la informacion de las dos tablas
		this.actualizarEstadosBotones();
	}

	// Metodos para llenar comboBox

	private void llenarComboBoxsTypeOfRooms () throws SQLException {
		ArrayList<TypeOfRoom> typesOfRooms = (ArrayList<TypeOfRoom>) TypeOfRoomDAO.getInstancie().selectAll(); // se obtienen los tipos de habitacion del sistema
		this.comboBoxTypeOfRoomAvaible.addItem(new TypeOfRoom("All"));
		this.comboBoxTypeOfRoomAssigned.addItem(new TypeOfRoom("All"));
		for (TypeOfRoom typeOfRoom : typesOfRooms) {
			this.comboBoxTypeOfRoomAvaible.addItem(typeOfRoom);
			this.comboBoxTypeOfRoomAssigned.addItem(typeOfRoom);
		}
	}

	private void llenarComboBoxsMealPlans () throws SQLException {
		ArrayList<MealPlan> mealPlans = (ArrayList<MealPlan>) MealPlanDAO.getInstancie().selectAll(); // se obtienen los planes alimenticios del hotel

		this.comboBoxMealPlanAvaible.addItem(new MealPlan("All"));
		this.comboBoxMealPlanAssigned.addItem(new MealPlan("All"));
		for (MealPlan mealPlan : mealPlans) {
			this.comboBoxMealPlanAvaible.addItem(mealPlan);
			this.comboBoxMealPlanAssigned.addItem(mealPlan);
		}
	}

	private void llenarComboBoxHotelModality () throws SQLException {
		ArrayList<HotelModality> hotelsModalitys = (ArrayList<HotelModality>) HotelModalityDAO.getInstancie().selectAll(); // se obtienen las modalidades hoteleras del hotel seleccionado en el contrato de alojamiento

		this.comboBoxHotelModalityAvaible.addItem(new HotelModality("All"));
		this.comboBoxHotelModalityAssigned.addItem(new HotelModality("All"));
		for (HotelModality hotelModality : hotelsModalitys) {
			this.comboBoxHotelModalityAvaible.addItem(hotelModality);
			this.comboBoxHotelModalityAssigned.addItem(hotelModality);
		}
	}

	// Fin de Metodos para llenar comboBox

	// Metodos de actualizacion de la tabla  tableAviavleAccommodations
	private void actualizarTablaAviableAccommodations () {	
		this.actualizarTablaAviableAccommodations(Controller.getInstancie().getTouristAgency().getAccommodationModalitys(this.definirTypeOfRoomAvaible(), this.definirMealPlanAvaible(), 
				this.definirHotelModalityAvaible(), (Double) this.spinnerPriceMinAvaible.getValue(), (Double) this.spinnerPriceMaxAvaible.getValue(), 
				(Integer) this.spinnerCantDaysAccommodationMinAvaible.getValue(), (Integer) this.spinnerCantDaysAccommodationMaxAvaible.getValue())); 
	}

	private TypeOfRoom definirTypeOfRoomAvaible () { // Metodo para definir el tipo de habitacion escogido en el filtro
		TypeOfRoom typeOfRoom = (TypeOfRoom) this.comboBoxTypeOfRoomAvaible.getSelectedItem(); // se obtiene el filtro seleccionado

		if (typeOfRoom.getName().equalsIgnoreCase(AusentFilter.entintyField)) // si se seleccionó el filtro all
			typeOfRoom = null;


		return typeOfRoom;
	}

	private MealPlan definirMealPlanAvaible () { // Metodo para definir el plan alimenticio escogido en el filtro
		MealPlan mealPlan = (MealPlan) this.comboBoxMealPlanAvaible.getSelectedItem(); // se obtiene el filtro seleccionado

		if (mealPlan.getName().equalsIgnoreCase(AusentFilter.entintyField)) // si se seleccionó el filtro all
			mealPlan = null;


		return mealPlan;
	}

	private HotelModality definirHotelModalityAvaible () { // Metodo para definir la modalidad de hotel escogida en el filtro
		HotelModality hotelModality = (HotelModality) this.comboBoxHotelModalityAvaible.getSelectedItem(); // se obtiene el filtro seleccionado

		if (hotelModality.getName().equalsIgnoreCase(AusentFilter.entintyField)) // si se seleccionó el filtro all
			hotelModality = null;


		return hotelModality;
	}

	private TypeOfRoom definirTypeOfRoomAssigned () { // Metodo para definir el tipo de habitacion escogido en el filtro
		TypeOfRoom typeOfRoom = (TypeOfRoom) this.comboBoxTypeOfRoomAssigned.getSelectedItem(); // se obtiene el filtro seleccionado

		if (typeOfRoom.getName().equalsIgnoreCase(AusentFilter.entintyField)) // si se seleccionó el filtro all
			typeOfRoom = null;


		return typeOfRoom;
	}

	private MealPlan definirMealPlanAssigned () { // Metodo para definir el plan alimenticio escogido en el filtro
		MealPlan mealPlan = (MealPlan) this.comboBoxMealPlanAssigned.getSelectedItem(); // se obtiene el filtro seleccionado

		if (mealPlan.getName().equalsIgnoreCase(AusentFilter.entintyField)) // si se seleccionó el filtro all
			mealPlan = null;


		return mealPlan;
	}

	private HotelModality definirHotelModalityAssigned () { // Metodo para definir la modalidad de hotel escogida en el filtro
		HotelModality hotelModality = (HotelModality) this.comboBoxHotelModalityAssigned.getSelectedItem(); // se obtiene el filtro seleccionado

		if (hotelModality.getName().equalsIgnoreCase(AusentFilter.entintyField)) // si se seleccionó el filtro all
			hotelModality = null;


		return hotelModality;
	}


	private void actualizarTablaAviableAccommodations(ArrayList<Modality> modalitys){
		reiniciarTable(this.tableAvailableAccommodation);


		for (Modality mod : modalitys) {
			if (!touristPackage.isContainsModality(Modality.accommodationModality, mod))
				((ModeloTablaAccommodationModality) tableAvailableAccommodation.getModel()).addElement((AccommodationModality) mod); // se obtienen todas las modalidades del alojamiento de la agencia turistica
		}
	}

	// Fin de Metodos de actualizacion de la tabla  tableAviavleAccommodations


	private void actualizarTablaAssignedAccommodations () {	
		this.actualizarTablaAssignedAccommodations(touristPackage.getAccommodationModalitys(this.definirTypeOfRoomAssigned(), this.definirMealPlanAssigned(), 
				this.definirHotelModalityAssigned(), (Double) this.spinnerPriceMinAssigned.getValue(), (Double) this.spinnerPriceMaxAssigned.getValue(), 
				(Integer) this.spinnerCantDaysAccommodationMinAssigned.getValue(), (Integer) this.spinnerCantDaysAccommodationMaxAssigned.getValue())); // se obtienen todas las modalidades del alojamiento pertenecientes al paquete turistico
	}

	// Metodos de actualizacion de la tabla  tableAssignedAccommodations
	private void actualizarTablaAssignedAccommodations(ArrayList<Modality> modalitys){
		reiniciarTable(this.tableAssignedAccommodations);


		for (Modality mod : modalitys) {
			((ModeloTablaAccommodationModality) tableAssignedAccommodations.getModel()).addElement((AccommodationModality) mod);
		}
	}

	// Fin de Metodos de actualizacion de la tabla  tableAssignedAccommodations



	private void reiniciarTable(JTable table){

		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaAccommodationModality) table.getModel()).deleteElement(i);
		}
	}


	// Operaciones

	private void asign () throws SQLException  { // Metodo para asignar las modalidades seleccionadas al paquete
		int [] rows = this.tableAvailableAccommodation.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.touristPackage.getId() != -1) 
				this.touristPackage.addModality(((ModeloTablaAccommodationModality) this.tableAvailableAccommodation.getModel()).getElement(rows[i])); // se asigna a las base de datos y a la logica del negocio
			else
				this.touristPackage.addModalityLogic(((ModeloTablaAccommodationModality) this.tableAvailableAccommodation.getModel()).getElement(rows[i])); // se asigna solo en la logica del negocio
		}

		this.actualizarTablas(); // se actualiza la informacion de las dos tablas
	}

	private void actualizarTablas () {
		this.actualizarTablaAssignedAccommodations();
		this.actualizarTablaAviableAccommodations();
	}

	private void deny () throws SQLException { // Metodo para desasignar las modalidades seleccionadas del paquete
		int [] rows = this.tableAssignedAccommodations.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.touristPackage.getId() != -1) 
				this.touristPackage.deleteModality(((ModeloTablaAccommodationModality) this.tableAssignedAccommodations.getModel()).getElement(rows[i])); // se elimina a las base de datos y a la logica del negocio
			else
				this.touristPackage.deleteModalityLogic(((ModeloTablaAccommodationModality) this.tableAssignedAccommodations.getModel()).getElement(rows[i])); // se elimina solo en la logica del negocio
		}

		this.actualizarTablas(); // se actualiza la informacion de las dos tablas
	}
	
	// Metodos para la restauracion de los filtros
	
	private void restoreFiltersAvaible () { // Metodo para restaurar los filtros de la tabla de Avaivle
		this.isRestoreFiltersAvaivle = true; // se indica que los filtros para van ha ser restaurados para evitar llamadas innecesarias al metodo actualizar
		this.comboBoxTypeOfRoomAvaible.setSelectedIndex(0);
		this.comboBoxMealPlanAvaible.setSelectedIndex(0);
		this.comboBoxHotelModalityAvaible.setSelectedIndex(0);
		this.spinnerCantDaysAccommodationMinAvaible.setValue(0);
		this.spinnerCantDaysAccommodationMaxAvaible.setValue(0);
		this.spinnerPriceMinAvaible.setValue(0.0);
		this.spinnerPriceMaxAvaible.setValue(0.0);
		this.isRestoreFiltersAvaivle = false; // se indica que terminó la restauracion de los filtros
		this.actualizarTablaAviableAccommodations(); // se actualiza la información de la tabla de las modalidades
	}
	
	private void restoreFiltersAssigned () { // Metodo para restaurar los filtros de la tabla de Assigned
		this.isRestoreFiltersAssigned = true; // se indica que los filtros para van ha ser restaurados para evitar llamadas innecesarias al metodo actualizar
		this.comboBoxTypeOfRoomAssigned.setSelectedIndex(0);
		this.comboBoxMealPlanAssigned.setSelectedIndex(0);
		this.comboBoxHotelModalityAssigned.setSelectedIndex(0);
		this.spinnerCantDaysAccommodationMinAssigned.setValue(0);
		this.spinnerCantDaysAccommodationMaxAssigned.setValue(0);
		this.spinnerPriceMinAssigned.setValue(0.0);
		this.spinnerPriceMaxAssigned.setValue(0.0);
		this.isRestoreFiltersAssigned = false; // se indica que terminó la restauracion de los filtros
		this.actualizarTablaAssignedAccommodations(); // se actualiza la información de la tabla de las modalidades
	}
	
	// Fin de Metodos para la restauracion de los filtros

	// Fin de Operaciones


	private void actualizarEstadosBotones () {
		this.estadoLblAsign();
		this.estadoLblDeny();
	}

	private void estadoLblAsign () {
		if (tableAvailableAccommodation.getSelectedRowCount() != 0)
			lblAnnadir.setEnabled(true);
		else
			lblAnnadir.setEnabled(false);
	}

	private void estadoLblDeny () {
		if (tableAssignedAccommodations.getSelectedRowCount() != 0)
			lblDenegar.setEnabled(true);
		else
			lblDenegar.setEnabled(false);
	}
}
