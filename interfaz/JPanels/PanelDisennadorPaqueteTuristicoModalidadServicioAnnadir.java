package JPanels;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import logica.Controller;
import logica.Modality;
import logica.ServiceModality;
import logica.TouristPackage;
import modelosTablas.ModeloTablaAccommodationModality;
import modelosTablas.ModeloTablaServiceModality;
import utils.AusentFilter;
import utils.Operations;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import JFrames.FrameInformacionPaquete;
import dao.ActivityDAO;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import logica.Activity;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.MatteBorder;

public class PanelDisennadorPaqueteTuristicoModalidadServicioAnnadir extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableAvailableServices;
	private JTable tableAssignedServices;
	private JLabel lblAnnadir;
	private JLabel lblDenegar;
	private FrameInformacionPaquete frameInformacionPaquete;
	private TouristPackage touristPackage;
	private JSpinner spinnerPriceMaxAvaible;
	private JSpinner spinnerPriceMinAvaible;
	private JDateChooser dateChooserReleaseDateMaxAvaible;
	private JDateChooser dateChooserReleaseDateMinAvaible;
	private JComboBox<Activity> comboBoxActivitiesAvaible;
	private JComboBox<Activity> comboBoxActivitiesAssigned;
	private JDateChooser dateChooserReleaseDateMinAssigned;
	private JDateChooser dateChooserReleaseDateMaxAssigned;
	private JSpinner spinnerPriceMinAssigned;
	private JSpinner spinnerPriceMaxAssigned;
	private JLabel lblRestoreFiltersAssigned;
	private JLabel lblRestoreFiltersAvaible;
	private boolean isRestoreFiltersAvaible, isRestoreFiltersAssigned;
	private int mouseX, mouseY;


	public PanelDisennadorPaqueteTuristicoModalidadServicioAnnadir(FrameInformacionPaquete f) {
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
				tableAssignedServices.clearSelection();
				tableAvailableServices.clearSelection();
				actualizarEstadosBotones();
			}
		});
		this.isRestoreFiltersAvaible = false;
		this.isRestoreFiltersAssigned = false;
		this.frameInformacionPaquete = f;
		this.touristPackage = this.frameInformacionPaquete.getTouristPackage();

		setBounds(0, 0, 971, 591);
		setLayout(null);
		setBackground(new Color(18, 95, 115));

		JLabel lblAvailableServices = new JLabel("AVAILABLE SERVICES");
		lblAvailableServices.setForeground(SystemColor.textHighlightText);
		lblAvailableServices.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAvailableServices.setBounds(10, 55, 275, 20);
		add(lblAvailableServices);

		lblAnnadir = new JLabel("ASIGN");
		lblAnnadir.setIcon(new ImageIcon(PanelDisennadorPaqueteTuristicoModalidadServicioAnnadir.class.getResource("/images/Plus.png")));
		lblAnnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					asign(); // se ejecuta la operacion de asignar modalidad al paquete turistico
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
		lblAnnadir.setHorizontalAlignment(SwingConstants.LEFT);
		lblAnnadir.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir.setBackground(new Color(18, 95, 115));
		lblAnnadir.setBounds(908, 85, 53, 43);
		add(lblAnnadir);

		JLabel lblAssignedServices = new JLabel("ASSIGNED SERVICES");
		lblAssignedServices.setForeground(SystemColor.textHighlightText);
		lblAssignedServices.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblAssignedServices.setBounds(10, 318, 275, 19);
		add(lblAssignedServices);

		lblDenegar = new JLabel("DENY");
		lblDenegar.setIcon(new ImageIcon(PanelDisennadorPaqueteTuristicoModalidadServicioAnnadir.class.getResource("/images/Trash.png")));
		lblDenegar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					deny(); // se ejecuta la operacion de desasignar modalidad del paquete turistico
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
		lblDenegar.setHorizontalAlignment(SwingConstants.LEFT);
		lblDenegar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDenegar.setBackground(new Color(18, 95, 115));
		lblDenegar.setBounds(908, 353, 53, 43);
		add(lblDenegar);

		JPanel panelAvailableServices = new JPanel();
		panelAvailableServices.setBounds(10, 144, 951, 173);
		add(panelAvailableServices);
		panelAvailableServices.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelAvailableServices.add(scrollPane, BorderLayout.CENTER);

		tableAvailableServices = new JTable();
		tableAvailableServices.setRowHeight(30);
		tableAvailableServices.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableAvailableServices.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		tableAvailableServices.getTableHeader().setForeground(Color.black);
		tableAvailableServices.getTableHeader().setBackground(SystemColor.black);
		tableAvailableServices.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadosBotones(); 
			}
		});
		tableAvailableServices.setModel(new ModeloTablaServiceModality());
		scrollPane.setViewportView(tableAvailableServices);

		JPanel panelAssignedServices = new JPanel();
		panelAssignedServices.setOpaque(false);
		panelAssignedServices.setBounds(10, 407, 951, 173);
		add(panelAssignedServices);
		panelAssignedServices.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panelAssignedServices.add(scrollPane_1, BorderLayout.CENTER);

		tableAssignedServices = new JTable();
		tableAssignedServices.setRowHeight(30);
		tableAssignedServices.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableAssignedServices.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		tableAssignedServices.getTableHeader().setForeground(Color.black);
		tableAssignedServices.getTableHeader().setBackground(SystemColor.black);
		tableAssignedServices.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadosBotones();
			}
		});
		tableAssignedServices.setModel(new ModeloTablaServiceModality());
		scrollPane_1.setViewportView(tableAssignedServices);

		JLabel lblServiceSeccion = new JLabel("SERVICE SECCION");
		lblServiceSeccion.setForeground(SystemColor.textHighlightText);
		lblServiceSeccion.setFont(new Font("Dialog", Font.BOLD, 24));
		lblServiceSeccion.setBounds(370, 11, 231, 20);
		add(lblServiceSeccion);

		JLabel lblAtras = new JLabel("");
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
		lblAtras.setIcon(new ImageIcon(PanelDisennadorPaqueteTuristicoModalidadServicioAnnadir.class.getResource("/images/Circled Right.png")));
		lblAtras.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtras.setForeground(Color.BLACK);
		lblAtras.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblAtras.setBackground(SystemColor.menu);
		lblAtras.setBounds(10, 11, 53, 43);
		add(lblAtras);

		comboBoxActivitiesAvaible = new JComboBox<Activity>();
		comboBoxActivitiesAssigned = new JComboBox<Activity>();

		try {
			llenarComboBoxActivities(); // se llena el comboBox de las actividades con las actividades registradas en el sistema
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

		comboBoxActivitiesAvaible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableServicies(); // se actualiza la informacion de la tabla de las modalidades de disponibles
			}
		});
		comboBoxActivitiesAvaible.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxActivitiesAvaible.setBounds(69, 111, 133, 22);
		add(comboBoxActivitiesAvaible);


		comboBoxActivitiesAssigned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedServicies(); // se actualiza la informacion de la tabla de las modalidades de asignadas
			}
		});
		comboBoxActivitiesAssigned.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxActivitiesAssigned.setBounds(69, 374, 133, 22);
		add(comboBoxActivitiesAssigned);

		JLabel lblActivity = new JLabel("Activity:");
		lblActivity.setHorizontalAlignment(SwingConstants.CENTER);
		lblActivity.setForeground(SystemColor.textHighlightText);
		lblActivity.setFont(new Font("Dialog", Font.BOLD, 18));
		lblActivity.setBounds(69, 85, 133, 19);
		add(lblActivity);

		JLabel lblReleaseDate = new JLabel("Release Date:");
		lblReleaseDate.setForeground(SystemColor.textHighlightText);
		lblReleaseDate.setFont(new Font("Dialog", Font.BOLD, 18));
		lblReleaseDate.setBounds(416, 83, 133, 22);
		add(lblReleaseDate);

		JLabel lblMin = new JLabel("Min:");
		lblMin.setForeground(SystemColor.textHighlightText);
		lblMin.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin.setBounds(305, 116, 30, 14);
		add(lblMin);

		dateChooserReleaseDateMinAvaible = new JDateChooser();
		dateChooserReleaseDateMaxAvaible = new JDateChooser();

		this.definirFechasAvaible(); // Se definen las fechas determinadas por la duracion del paquete turistico

		dateChooserReleaseDateMinAvaible.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if (e.getPropertyName().equals("date") && !isRestoreFiltersAvaible)
					actualizarTablaAviableServicies(); // se actualiza la informacion de la tabla de las modalidades de disponibles
				dateChooserReleaseDateMaxAvaible.setMinSelectableDate(Date.from(touristPackage.getStartDate().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
			}
		});
		dateChooserReleaseDateMinAvaible.setFont(new Font("Dialog", Font.PLAIN, 13));
		dateChooserReleaseDateMinAvaible.setBorder(new LineBorder(new Color(0, 0, 0)));
		dateChooserReleaseDateMinAvaible.setBounds(345, 111, 111, 22);
		dateChooserReleaseDateMinAvaible.setMinSelectableDate(Date.from(this.touristPackage.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		add(dateChooserReleaseDateMinAvaible);

		JLabel lblMax = new JLabel("Max:");
		lblMax.setForeground(SystemColor.textHighlightText);
		lblMax.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax.setBounds(462, 116, 40, 14);
		add(lblMax);


		dateChooserReleaseDateMaxAvaible.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if (e.getPropertyName().equals("date") && !isRestoreFiltersAvaible)
					actualizarTablaAviableServicies(); // se actualiza la informacion de la tabla de las modalidades de disponibles
				dateChooserReleaseDateMinAvaible.setMaxSelectableDate(Date.from(touristPackage.getTerminationDate().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
			}
		});
		dateChooserReleaseDateMaxAvaible.setFont(new Font("Dialog", Font.PLAIN, 13));
		dateChooserReleaseDateMaxAvaible.setBorder(new LineBorder(new Color(0, 0, 0)));
		dateChooserReleaseDateMaxAvaible.setBounds(506, 111, 111, 22);
		dateChooserReleaseDateMaxAvaible.setMaxSelectableDate(Date.from(this.touristPackage.getTerminationDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		add(dateChooserReleaseDateMaxAvaible);

		JLabel lblMin_1 = new JLabel("Min:");
		lblMin_1.setForeground(SystemColor.textHighlightText);
		lblMin_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_1.setBounds(657, 112, 30, 14);
		add(lblMin_1);

		spinnerPriceMinAvaible = new JSpinner();
		Operations.crearJSpinnerNumericoDouble(spinnerPriceMinAvaible, new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMinAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableServicies(); // se actualiza la informacion de la tabla de las modalidades de disponibles
			}
		});
		spinnerPriceMinAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMinAvaible.setBounds(697, 112, 53, 20);
		add(spinnerPriceMinAvaible);

		JLabel lblPrice = new JLabel("Plan Price:");
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setForeground(SystemColor.textHighlightText);
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPrice.setBounds(708, 77, 103, 30);
		add(lblPrice);

		JLabel lblMax_1 = new JLabel("Max:");
		lblMax_1.setForeground(SystemColor.textHighlightText);
		lblMax_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_1.setBounds(760, 112, 40, 14);
		add(lblMax_1);

		spinnerPriceMaxAvaible = new JSpinner();
		Operations.crearJSpinnerNumericoDouble(spinnerPriceMaxAvaible, new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMaxAvaible.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAvaible)
					actualizarTablaAviableServicies(); // se actualiza la informacion de la tabla de las modalidades de disponibles
			}
		});
		spinnerPriceMaxAvaible.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMaxAvaible.setBounds(799, 112, 53, 20);
		add(spinnerPriceMaxAvaible);



		JLabel lblActivity_1 = new JLabel("Activity:");
		lblActivity_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblActivity_1.setForeground(SystemColor.textHighlightText);
		lblActivity_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblActivity_1.setBounds(69, 348, 133, 19);
		add(lblActivity_1);

		JLabel lblReleaseDate_1 = new JLabel("Release Date:");
		lblReleaseDate_1.setForeground(SystemColor.textHighlightText);
		lblReleaseDate_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblReleaseDate_1.setBounds(416, 346, 133, 22);
		add(lblReleaseDate_1);

		JLabel lblMin_2 = new JLabel("Min:");
		lblMin_2.setForeground(SystemColor.textHighlightText);
		lblMin_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_2.setBounds(305, 379, 30, 14);
		add(lblMin_2);

		dateChooserReleaseDateMinAssigned = new JDateChooser();
		dateChooserReleaseDateMaxAssigned = new JDateChooser();

		this.definirFechasAssigned(); // Se definen las fechas determinadas por la duracion del paquete turistico

		dateChooserReleaseDateMinAssigned.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if (e.getPropertyName().equals("date") && !isRestoreFiltersAssigned)
					actualizarTablaAssignedServicies(); // se actualiza la informacion de la tabla de las modalidades de asignadas
				dateChooserReleaseDateMaxAssigned.setMinSelectableDate(Date.from(touristPackage.getStartDate().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
			}
		});
		dateChooserReleaseDateMinAssigned.setFont(new Font("Dialog", Font.PLAIN, 13));
		dateChooserReleaseDateMinAssigned.setBorder(new LineBorder(new Color(0, 0, 0)));
		dateChooserReleaseDateMinAssigned.setBounds(345, 374, 111, 22);
		dateChooserReleaseDateMinAssigned.setMinSelectableDate(Date.from(this.touristPackage.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		add(dateChooserReleaseDateMinAssigned);

		JLabel lblMax_2 = new JLabel("Max:");
		lblMax_2.setForeground(SystemColor.textHighlightText);
		lblMax_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_2.setBounds(462, 379, 40, 14);
		add(lblMax_2);


		dateChooserReleaseDateMaxAssigned.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if (e.getPropertyName().equals("date") && !isRestoreFiltersAssigned)
					actualizarTablaAssignedServicies(); // se actualiza la informacion de la tabla de las modalidades de asignadas
				dateChooserReleaseDateMinAssigned.setMaxSelectableDate(Date.from(touristPackage.getTerminationDate().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
			}
		});
		dateChooserReleaseDateMaxAssigned.setFont(new Font("Dialog", Font.PLAIN, 13));
		dateChooserReleaseDateMaxAssigned.setBorder(new LineBorder(new Color(0, 0, 0)));
		dateChooserReleaseDateMaxAssigned.setBounds(506, 374, 111, 22);
		dateChooserReleaseDateMaxAssigned.setMaxSelectableDate(Date.from(this.touristPackage.getTerminationDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		add(dateChooserReleaseDateMaxAssigned);

		JLabel lblMin_1_1 = new JLabel("Min:");
		lblMin_1_1.setForeground(SystemColor.textHighlightText);
		lblMin_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_1_1.setBounds(657, 375, 30, 14);
		add(lblMin_1_1);

		spinnerPriceMinAssigned = new JSpinner();
		Operations.crearJSpinnerNumericoDouble(spinnerPriceMinAssigned, new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMinAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedServicies(); // se actualiza la informacion de la tabla de las modalidades de asignadas
			}
		});
		spinnerPriceMinAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMinAssigned.setBounds(697, 375, 53, 20);
		add(spinnerPriceMinAssigned);

		JLabel lblPrice_1 = new JLabel("Plan Price:");
		lblPrice_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice_1.setForeground(SystemColor.textHighlightText);
		lblPrice_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPrice_1.setBounds(708, 340, 103, 30);
		add(lblPrice_1);

		JLabel lblMax_1_1 = new JLabel("Max:");
		lblMax_1_1.setForeground(SystemColor.textHighlightText);
		lblMax_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_1_1.setBounds(760, 375, 40, 14);
		add(lblMax_1_1);

		spinnerPriceMaxAssigned = new JSpinner();
		Operations.crearJSpinnerNumericoDouble(spinnerPriceMaxAssigned, new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMaxAssigned.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestoreFiltersAssigned)
					actualizarTablaAssignedServicies(); // se actualiza la informacion de la tabla de las modalidades de asignadas
			}
		});
		spinnerPriceMaxAssigned.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMaxAssigned.setBounds(799, 375, 53, 20);
		add(spinnerPriceMaxAssigned);

		lblRestoreFiltersAvaible = new JLabel("Restore Filters");
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
		lblRestoreFiltersAvaible.setForeground(SystemColor.textHighlightText);
		lblRestoreFiltersAvaible.setFont(new Font("Dialog", Font.BOLD, 16));
		lblRestoreFiltersAvaible.setBounds(799, 61, 114, 20);
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
		lblRestoreFiltersAssigned.setBounds(799, 323, 114, 20);
		add(lblRestoreFiltersAssigned);
		this.actualizarTablas(); // se actualiza la informacion de las tablas
		this.actualizarEstadosBotones();
	}



	// Metodos de actualizacion de la tabla  tableAviavleAccommodations
	private void actualizarTablaAviableServicies () {	
		this.actualizarTablaAviableServicies(Controller.getInstancie().getTouristAgency().getServiceModalitys(this.definirActivitySeleccionadaAvaivle(), (this.dateChooserReleaseDateMinAvaible.getDate() != null) ? this.dateChooserReleaseDateMinAvaible.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null,
				(this.dateChooserReleaseDateMaxAvaible.getDate() != null) ? this.dateChooserReleaseDateMaxAvaible.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null, (Double) this.spinnerPriceMinAvaible.getValue(),
						(Double) this.spinnerPriceMaxAvaible.getValue())); // se obtienen las modalidades de servicio disponibles en la agencia
	}


	private void actualizarTablaAviableServicies(ArrayList<Modality> modalitys){
		reiniciarTable(this.tableAvailableServices);


		for (Modality mod : modalitys) {
			if (!touristPackage.isContainsModality(Modality.serviceModality, mod))
				((ModeloTablaServiceModality) tableAvailableServices.getModel()).addElement((ServiceModality) mod); // se obtienen todas las modalidades del alojamiento de la agencia turistica
		}
	}

	// Fin de Metodos de actualizacion de la tabla  tableAviavleAccommodations


	private void actualizarTablaAssignedServicies () {	
		this.actualizarTablaAssignedServicies(touristPackage.getServicesModalitys(this.definirActivitySeleccionadaAssigned(), (this.dateChooserReleaseDateMinAssigned.getDate() != null) ? this.dateChooserReleaseDateMinAssigned.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null,
				(this.dateChooserReleaseDateMaxAssigned.getDate() != null) ? this.dateChooserReleaseDateMaxAssigned.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null, (Double) this.spinnerPriceMinAssigned.getValue(),
						(Double) this.spinnerPriceMaxAssigned.getValue())); // se obtienen todas las modalidades del alojamiento pertenecientes al paquete turistico
	}

	// Metodos de actualizacion de la tabla  tableAssignedAccommodations
	private void actualizarTablaAssignedServicies(ArrayList<Modality> modalitys){
		reiniciarTable(this.tableAssignedServices);


		for (Modality mod : modalitys) {
			((ModeloTablaServiceModality) tableAssignedServices.getModel()).addElement((ServiceModality) mod);
		}
	}

	// Fin de Metodos de actualizacion de la tabla  tableAssignedAccommodations



	private void reiniciarTable(JTable table){

		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaServiceModality) table.getModel()).deleteElement(i);
		}
	}


	// Operaciones

	private void asign () throws SQLException  { // Metodo para asignar las modalidades seleccionadas al paquete
		int [] rows = this.tableAvailableServices.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.touristPackage.getId() != -1) 
				this.touristPackage.addModality(((ModeloTablaServiceModality) this.tableAvailableServices.getModel()).getElement(rows[i])); // se asigna a las base de datos y a la logica del negocio
			else
				this.touristPackage.addModalityLogic(((ModeloTablaServiceModality) this.tableAvailableServices.getModel()).getElement(rows[i])); // se asigna solo en la logica del negocio
		}

		this.actualizarTablas(); // se actualiza la informacion de las dos tablas
	}

	private void actualizarTablas () {
		this.actualizarTablaAssignedServicies();
		this.actualizarTablaAviableServicies();
	}

	private void deny () throws SQLException { // Metodo para desasignar las modalidades seleccionadas del paquete
		int [] rows = this.tableAssignedServices.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (this.touristPackage.getId() != -1) 
				this.touristPackage.deleteModality(((ModeloTablaServiceModality) this.tableAssignedServices.getModel()).getElement(rows[i])); // se elimina a las base de datos y a la logica del negocio
			else
				this.touristPackage.deleteModalityLogic(((ModeloTablaServiceModality) this.tableAssignedServices.getModel()).getElement(rows[i])); // se elimina solo en la logica del negocio
		}

		this.actualizarTablas(); // se actualiza la informacion de las dos tablas
	}

	// Metodos para llenar comboBoxs

	private void llenarComboBoxActivities() throws SQLException { // Metodo para establecer la información que será mostrada en el comboBox activities
		ArrayList<Activity> activities = (ArrayList<Activity>) ActivityDAO.getInstancie().selectAll(); // se obtienen todas las actvididades registradas en el sistema

		comboBoxActivitiesAvaible.addItem(new Activity("All"));
		comboBoxActivitiesAssigned.addItem(new Activity("All"));
		for (Activity activity : activities) {
			comboBoxActivitiesAvaible.addItem(activity);
			comboBoxActivitiesAssigned.addItem(activity);
		}
	}

	// Fin de Metodos para llenar comboBoxs

	private Activity definirActivitySeleccionadaAvaivle() { // Metodo para definir la actividad seleccionada en el filtro de las actividades
		Activity activity = (Activity) this.comboBoxActivitiesAvaible.getSelectedItem();

		if (activity.getName().equalsIgnoreCase(AusentFilter.entintyField)) // si es igual a All
			activity = null;


		return activity;

	}

	private Activity definirActivitySeleccionadaAssigned() { // Metodo para definir la actividad seleccionada en el filtro de las actividades
		Activity activity = (Activity) this.comboBoxActivitiesAssigned.getSelectedItem();

		if (activity.getName().equalsIgnoreCase(AusentFilter.entintyField)) // si es igual a All
			activity = null;


		return activity;

	}

	private void definirFechasAvaible () { // Metodo para definir las fechas determinadas por la duracion del paquete turistico
		this.dateChooserReleaseDateMinAvaible.setDate(Date.from(this.touristPackage.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		this.dateChooserReleaseDateMaxAvaible.setDate(Date.from(this.touristPackage.getTerminationDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
	}

	private void definirFechasAssigned () { // Metodo para definir las fechas determinadas por la duracion del paquete turistico
		this.dateChooserReleaseDateMinAssigned.setDate(Date.from(this.touristPackage.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		this.dateChooserReleaseDateMaxAssigned.setDate(Date.from(this.touristPackage.getTerminationDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
	}

	// Metodos para la restauracion de los Filtros

	private void restoreFiltersAviable () { // Metodo para restaurar los filtros de la tabla de modalidades disponibles
		this.isRestoreFiltersAvaible = true; // se indica que va a empezar una restauracion de los filtros
		this.comboBoxActivitiesAvaible.setSelectedIndex(0);
		// se restuara el filtro del precio
		this.spinnerPriceMaxAvaible.setValue(0.0);
		this.spinnerPriceMinAvaible.setValue(0.0);
		// se restaura el filtro de la fecha de realizacion
		this.definirFechasAvaible();
		this.actualizarTablaAviableServicies();
		this.isRestoreFiltersAvaible = false; // se indica que se terminó la restauracion de los filtros
	}

	private void restoreFiltersAssigned () { // Metodo para restaurar los filtros de la tabla de modalidades asignadas
		this.isRestoreFiltersAssigned = true; // se indica que va a empezar una restauracion de los filtros
		this.comboBoxActivitiesAssigned.setSelectedIndex(0);
		// se restuara el filtro del precio
		this.spinnerPriceMaxAssigned.setValue(0.0);
		this.spinnerPriceMinAssigned.setValue(0.0);
		// se restaura el filtro de la fecha de realizacion
		this.definirFechasAssigned();
		this.actualizarTablaAssignedServicies();
		this.isRestoreFiltersAssigned = false; // se indica que se terminó la restauracion de los filtros
	}

	// Fin de Metodos para la restauracion de los Filtros

	// Fin de Operaciones


	private void actualizarEstadosBotones () {
		this.estadoLblAsign();
		this.estadoLblDeny();
	}

	private void estadoLblAsign () {
		if (tableAvailableServices.getSelectedRowCount() != 0)
			lblAnnadir.setEnabled(true);
		else
			lblAnnadir.setEnabled(false);
	}

	private void estadoLblDeny () {
		if (tableAssignedServices.getSelectedRowCount() != 0)
			lblDenegar.setEnabled(true);
		else
			lblDenegar.setEnabled(false);
	}
}
