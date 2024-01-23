package JPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import JFrames.FrameDecisor;
import JFrames.FrameGerenteCreacionContratoAlojamiento;
import JFrames.FrameGerenteCreacionContratoAlojamientoAnnadirAccommodationModality;
import JFrames.FramePrincipal;
import logica.AccommodationContract;
import logica.AccommodationModality;
import logica.Controller;
import logica.HotelModality;
import logica.MealPlan;
import logica.Modality;
import logica.TypeOfRoom;
import modelosTablas.ModeloTablaAccommodationModality;
import utils.AusentFilter;
import utils.Semaphore;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.FlowLayout;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;


public class PanelCreacionContratoAlojamientoAccommodationModality extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable tableAccommodationModalitys;
	private JLabel lblAnnadir;
	private JLabel lblEliminar;
	private JLabel lblAtras;
	private FrameGerenteCreacionContratoAlojamiento frameGerenteCreacionContratoAlojamiento;
	private AccommodationContract accommodationContract;
	private JPanel panelBotones;
	private JComboBox <HotelModality> comboBoxHotelModality;
	private JComboBox <MealPlan> comboBoxMealPlan;
	private JComboBox <TypeOfRoom> comboBoxTypeOfRoom;
	private JSpinner spinnerCantDaysAccommodationMin;
	private JSpinner spinnerCantDaysAccommodationMax;
	private JSpinner spinnerPriceMin;
	private JSpinner spinnerPriceMax;
	private boolean isRestore;
	private int mouseX, mouseY;
	private JLabel lblUpdate;


	private class Eliminar extends Thread { // Hilo para la eliminacion

		public void run () {
			synchronized (Semaphore.samaphore) { 
				try {
					Semaphore.samaphore.wait(); // se duerme al hilo hasta esperar la confirmacion del usuario
					if (Controller.getInstancie().isConfirmacion()) { // si el usuario dió el consentimiento de realizar la operación				
						deleteSelectedElements(); // se eliminan las modalidades seleccionadas 
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
		FrameDecisor frameDecisor = new FrameDecisor(frameGerenteCreacionContratoAlojamiento, "Seguro que desea eliminar");
		frameDecisor.setVisible(true);
		frameGerenteCreacionContratoAlojamiento.setEnabled(false); // se inhabilita el frame principal
	}

	public FrameGerenteCreacionContratoAlojamiento getFrameGerenteCreacionContratoAlojamiento() {
		return frameGerenteCreacionContratoAlojamiento;
	}

	public void setFrameGerenteCreacionContratoAlojamiento(
			FrameGerenteCreacionContratoAlojamiento frameGerenteCreacionContratoAlojamiento) {
		this.frameGerenteCreacionContratoAlojamiento = frameGerenteCreacionContratoAlojamiento;
	}

	public AccommodationContract getAccommodationContract() {
		return accommodationContract;
	}

	public void setAccommodationContract(AccommodationContract accommodationContract) {
		this.accommodationContract = accommodationContract;
	}

	public PanelCreacionContratoAlojamientoAccommodationModality(FrameGerenteCreacionContratoAlojamiento ca) {
		this.isRestore = false;
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {

				int x = e.getXOnScreen();
				int y = e.getYOnScreen();

				frameGerenteCreacionContratoAlojamiento.setLocation(x - mouseX, y - mouseY);

			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				tableAccommodationModalitys.clearSelection();
				actualizarEstadoButtons(); // se actualiza el estado de los botones
			}
		});
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		this.frameGerenteCreacionContratoAlojamiento = ca;
		this.accommodationContract = this.frameGerenteCreacionContratoAlojamiento.getAccommodationContract();
		setLayout(null);
		setBackground(new Color(18, 95, 115));
		setBounds(0, 0, 853, 577);
		JPanel panelTable = new JPanel();
		panelTable.setBorder(new MatteBorder(0, 3, 3, 3, (Color) new Color(0, 0, 0)));
		panelTable.setBounds(0, 163, 853, 414);
		add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();

		panelTable.add(scrollPane, BorderLayout.CENTER);

		tableAccommodationModalitys = new JTable();
		tableAccommodationModalitys.setRowHeight(30);
		tableAccommodationModalitys.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tableAccommodationModalitys.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
		tableAccommodationModalitys.getTableHeader().setForeground(Color.black);
		tableAccommodationModalitys.getTableHeader().setBackground(SystemColor.black);
		tableAccommodationModalitys.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtons(); // se actualiza el estado de los botones 
			}
		});
		tableAccommodationModalitys.setModel(new ModeloTablaAccommodationModality());
		scrollPane.setViewportView(tableAccommodationModalitys);

		JLabel lblTitulo = new JLabel("Plan Hotelero");
		lblTitulo.setForeground(SystemColor.textHighlightText);
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 26));
		lblTitulo.setBounds(338, 24, 177, 30);
		add(lblTitulo);


		lblAtras = new JLabel("");
		lblAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				frameGerenteCreacionContratoAlojamiento.cambioDePanel(frameGerenteCreacionContratoAlojamiento.getPanelAccommodationContract());
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblAtras.setIcon(new ImageIcon(PanelCreacionContratoAlojamientoAccommodationModality.class.getResource("/images/Circled Right.png")));
		lblAtras.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtras.setForeground(Color.BLACK);
		lblAtras.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblAtras.setBackground(SystemColor.menu);
		lblAtras.setBounds(10, 11, 53, 43);
		add(lblAtras);

		JLabel lblNewLabel = new JLabel("Type Of Room:");
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 87, 133, 19);
		add(lblNewLabel);

		comboBoxTypeOfRoom = new JComboBox <TypeOfRoom>();
		this.llenarComboBoxTypeOfRoom(); // se llena el comboBox con los tipos de habitaciones del proveedor de alojamiento del contrato
		comboBoxTypeOfRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isRestore)
					actualizarTablaModalitys();
			}
		});
		comboBoxTypeOfRoom.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxTypeOfRoom.setBounds(10, 113, 133, 22);
		add(comboBoxTypeOfRoom);

		JLabel lblMealPlan = new JLabel("Meal Plan:");
		lblMealPlan.setForeground(SystemColor.textHighlightText);
		lblMealPlan.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMealPlan.setBounds(184, 87, 103, 14);
		add(lblMealPlan);

		comboBoxMealPlan = new JComboBox <MealPlan>();
		this.llenarComboBoxMealPlan(); // se llena el comboBox con los planes alimenticios del proveedor de alojamiento del contrato
		comboBoxMealPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isRestore)
					actualizarTablaModalitys();
			}
		});
		comboBoxMealPlan.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxMealPlan.setBounds(184, 113, 103, 22);
		add(comboBoxMealPlan);

		JLabel lblCantDaysAccommodation = new JLabel("Days Accommodation:");
		lblCantDaysAccommodation.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantDaysAccommodation.setForeground(SystemColor.textHighlightText);
		lblCantDaysAccommodation.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCantDaysAccommodation.setBounds(484, 87, 205, 22);
		add(lblCantDaysAccommodation);

		spinnerCantDaysAccommodationMax = new JSpinner();
		spinnerCantDaysAccommodationMax.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		spinnerCantDaysAccommodationMax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestore)
					actualizarTablaModalitys();
			}
		});
		spinnerCantDaysAccommodationMax.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCantDaysAccommodationMax.setBounds(565, 141, 53, 20);
		add(spinnerCantDaysAccommodationMax);

		spinnerCantDaysAccommodationMin = new JSpinner();
		spinnerCantDaysAccommodationMin.setModel(new SpinnerNumberModel(Integer.valueOf(0), null, null, Integer.valueOf(1)));
		spinnerCantDaysAccommodationMin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestore)
					actualizarTablaModalitys();
			}
		});
		spinnerCantDaysAccommodationMin.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerCantDaysAccommodationMin.setBounds(565, 113, 53, 20);
		add(spinnerCantDaysAccommodationMin);

		JLabel lblMin = new JLabel("Min:");
		lblMin.setForeground(SystemColor.textHighlightText);
		lblMin.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin.setBounds(530, 116, 30, 14);
		add(lblMin);

		JLabel lblMax = new JLabel("Max:");
		lblMax.setForeground(SystemColor.textHighlightText);
		lblMax.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax.setBounds(528, 143, 40, 14);
		add(lblMax);

		JLabel lblPrice = new JLabel("Plan Price:");
		lblPrice.setForeground(SystemColor.textHighlightText);
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPrice.setBounds(699, 84, 103, 30);
		add(lblPrice);

		JLabel lblMin_1 = new JLabel("Min:");
		lblMin_1.setForeground(SystemColor.textHighlightText);
		lblMin_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMin_1.setBounds(709, 116, 30, 14);
		add(lblMin_1);

		spinnerPriceMin = new JSpinner();
		spinnerPriceMin.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestore)
					actualizarTablaModalitys();
			}
		});
		spinnerPriceMin.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMin.setBounds(749, 113, 53, 20);
		add(spinnerPriceMin);

		JLabel lblMax_1 = new JLabel("Max:");
		lblMax_1.setForeground(SystemColor.textHighlightText);
		lblMax_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMax_1.setBounds(710, 141, 40, 14);
		add(lblMax_1);

		spinnerPriceMax = new JSpinner();
		spinnerPriceMax.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerPriceMax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isRestore)
					actualizarTablaModalitys();
			}
		});
		spinnerPriceMax.setFont(new Font("Dialog", Font.PLAIN, 15));
		spinnerPriceMax.setBounds(749, 141, 53, 20);
		add(spinnerPriceMax);

		panelBotones = new JPanel();
		panelBotones.setBorder(new MatteBorder(0, 0, 0, 3, (Color) new Color(0, 0, 0)));
		panelBotones.setBackground(new Color(18, 95, 115));
		FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelBotones.setBounds(664, 28, 189, 60);
		add(panelBotones);

		JLabel lblAnnadir_1 = new JLabel("");
		lblAnnadir_1.setOpaque(true);
		lblAnnadir_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadir_1.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadir_1.setBackground(new Color(18, 95, 115));
		lblAnnadir_1.setBounds(541, 462, 50, 50);
		add(lblAnnadir_1);

		JLabel lblHotelModality = new JLabel("Hotel Modality:");
		lblHotelModality.setForeground(SystemColor.textHighlightText);
		lblHotelModality.setFont(new Font("Dialog", Font.BOLD, 18));
		lblHotelModality.setBounds(322, 87, 133, 19);
		add(lblHotelModality);

		comboBoxHotelModality = new JComboBox <HotelModality>();
		this.llenarComboBoxHotelModality(); // se llena el comboBox con las modalidades de hotel del proveedor de alojamiento del contrato
		comboBoxHotelModality.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isRestore)
					actualizarTablaModalitys();
			}
		});
		comboBoxHotelModality.setFont(new Font("Dialog", Font.PLAIN, 16));
		comboBoxHotelModality.setBounds(322, 113, 133, 22);
		add(comboBoxHotelModality);

		JLabel lblRestoreFilters = new JLabel("Restore Filters");
		lblRestoreFilters.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				restoreFilters();
			}
		});
		lblRestoreFilters.setForeground(SystemColor.textHighlightText);
		lblRestoreFilters.setFont(new Font("Dialog", Font.BOLD, 18));
		lblRestoreFilters.setBounds(20, 65, 140, 14);
		add(lblRestoreFilters);

		this.addButtons();
		this.actualizarTablaModalitys(); // se actualiza la tabla modalitys con la informacion de las modalidades del contrato
	}

	public void actualizarTablaModalitys () { 	 
		this.actualizarTablaModalitys(accommodationContract.getModalitys(this.definirTypeOfRoom(), this.definirMealPlan(), this.definirHotelModality(), (Double) this.spinnerPriceMin.getValue(), 
				(Double) this.spinnerPriceMax.getValue(), (Integer) this.spinnerCantDaysAccommodationMin.getValue(), (Integer) this.spinnerCantDaysAccommodationMax.getValue())); // se obtienen las actividades del provedor de servicios
		this.actualizarEstadoButtons(); // se actualiza el estado de los botones
	}

	private TypeOfRoom definirTypeOfRoom () { // Metodo para definir el tipo de habitacion escogido en el filtro
		TypeOfRoom typeOfRoom = (TypeOfRoom) this.comboBoxTypeOfRoom.getSelectedItem(); // se obtiene el filtro seleccionado

		if (typeOfRoom.getName().equalsIgnoreCase(AusentFilter.entintyField)) // si se seleccionó el filtro all
			typeOfRoom = null;


		return typeOfRoom;
	}

	private MealPlan definirMealPlan () { // Metodo para definir el plan alimenticio escogido en el filtro
		MealPlan mealPlan = (MealPlan) this.comboBoxMealPlan.getSelectedItem(); // se obtiene el filtro seleccionado

		if (mealPlan.getName().equalsIgnoreCase(AusentFilter.entintyField)) // si se seleccionó el filtro all
			mealPlan = null;


		return mealPlan;
	}

	private HotelModality definirHotelModality () { // Metodo para definir la modalidad de hotel escogida en el filtro
		HotelModality hotelModality = (HotelModality) this.comboBoxHotelModality.getSelectedItem(); // se obtiene el filtro seleccionado

		if (hotelModality.getName().equalsIgnoreCase(AusentFilter.entintyField)) // si se seleccionó el filtro all
			hotelModality = null;


		return hotelModality;
	}

	private void restoreFilters () {
		this.isRestore = true; // se indica que los filtros para van ha ser restaurados para evitar llamadas innecesarias al metodo actualizar
		this.comboBoxTypeOfRoom.setSelectedIndex(0);
		this.comboBoxMealPlan.setSelectedIndex(0);
		this.comboBoxHotelModality.setSelectedIndex(0);
		this.spinnerCantDaysAccommodationMin.setValue(0);
		this.spinnerCantDaysAccommodationMax.setValue(0);
		this.spinnerPriceMin.setValue(0);
		this.spinnerPriceMax.setValue(0);
		this.isRestore = false; // se indica que terminó la restauracion de los filtros
		this.actualizarTablaModalitys(); // se actualiza la información de la tabla de las modalidades
	}

	private void llenarComboBoxTypeOfRoom () {
		ArrayList<TypeOfRoom> typesOfRooms = this.accommodationContract.getTypesOfRoomsHotel(); // se obtienen los tipos de habitacion del hotel
		this.comboBoxTypeOfRoom.addItem(new TypeOfRoom("All"));
		for (TypeOfRoom typeOfRoom : typesOfRooms) {
			this.comboBoxTypeOfRoom.addItem(typeOfRoom);
		}
	}

	private void llenarComboBoxMealPlan () {
		ArrayList<MealPlan> mealPlans = this.accommodationContract.getMealPlansHotel(); // se obtienen los planes alimenticios del hotel

		this.comboBoxMealPlan.addItem(new MealPlan("All"));
		for (MealPlan mealPlan : mealPlans) {
			this.comboBoxMealPlan.addItem(mealPlan);
		}
	}

	private void llenarComboBoxHotelModality () {
		ArrayList<HotelModality> hotelsModalitys = this.accommodationContract.getHotelsModalitiesHotel(); // se obtienen las modalidades hoteleras del hotel seleccionado en el contrato de alojamiento

		this.comboBoxHotelModality.addItem(new HotelModality("All"));
		for (HotelModality hotelModality : hotelsModalitys) {
			this.comboBoxHotelModality.addItem(hotelModality);
		}
	}

	private void addButtons () {
		lblAnnadir = new JLabel("");

		lblAnnadir.setIcon(new ImageIcon(PanelCreacionContratoAlojamientoAccommodationModality.class.getResource("/images/Plus.png")));
		lblAnnadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameGerenteCreacionContratoAlojamientoAnnadirAccommodationModality frameAddAccommodationModality = new FrameGerenteCreacionContratoAlojamientoAnnadirAccommodationModality(PanelCreacionContratoAlojamientoAccommodationModality.this, null);
				frameAddAccommodationModality.setVisible(true);
				frameGerenteCreacionContratoAlojamiento.setEnabled(false); // se inhabilita el frame
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
		lblAnnadir.setBounds(79, 30, 65, 45);
		panelBotones.add(lblAnnadir);

		lblEliminar = new JLabel("");
		lblEliminar.setIcon(new ImageIcon(PanelCreacionContratoAlojamientoAccommodationModality.class.getResource("/images/Trash.png")));
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
					FrameGerenteCreacionContratoAlojamientoAnnadirAccommodationModality frameAddAccommodationModality = new FrameGerenteCreacionContratoAlojamientoAnnadirAccommodationModality(PanelCreacionContratoAlojamientoAccommodationModality.this, 
							((ModeloTablaAccommodationModality)	tableAccommodationModalitys.getModel()).getElement(tableAccommodationModalitys.getSelectedRow()));
					frameAddAccommodationModality.setVisible(true);
					frameGerenteCreacionContratoAlojamiento.setEnabled(false); // se inhabilita el frame
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblUpdate.setIcon(new ImageIcon(PanelCreacionContratoAlojamientoAccommodationModality.class.getResource("/images/Edit.png")));
		lblUpdate.setOpaque(true);
		lblUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdate.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblUpdate.setBackground(new Color(18, 95, 115));
		panelBotones.add(lblUpdate);
		lblEliminar.setOpaque(true);
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblEliminar.setBackground(new Color(18, 95, 115));
		lblEliminar.setBounds(134, 30, 65, 38);
		panelBotones.add(lblEliminar);

	}


	private void actualizarTablaModalitys(ArrayList<Modality> modalitys){
		reiniciarTable(this.tableAccommodationModalitys);

		for (Modality mod : modalitys) {
			((ModeloTablaAccommodationModality) tableAccommodationModalitys.getModel()).addElement((AccommodationModality) mod);
		}
	}

	private void deleteSelectedElements () {
		try {
			this.deleteElementsTable();
			FramePrincipal.mostarFrameNotificacion("Han sido eliminados correctamente las modalidades seleccionadas");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void deleteElementsTable () throws SQLException {
		int [] rows = tableAccommodationModalitys.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (accommodationContract.getId() == -1)
				accommodationContract.deleteModalityLogic(((ModeloTablaAccommodationModality) tableAccommodationModalitys.getModel()).getElement(rows[i])); // se elimina solo la modalidad de la logica del negocio
			else
				accommodationContract.deleteModality(((ModeloTablaAccommodationModality) tableAccommodationModalitys.getModel()).getElement(rows[i])); // se elimina la modalidad de la logica del negocio y de la base de datos
		}


		this.actualizarTablaModalitys();
	}

	private void reiniciarTable(JTable table){

		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			((ModeloTablaAccommodationModality) table.getModel()).deleteElement(i);
		}
	}

	private void actualizarEstadoButtons() {
		if (this.tableAccommodationModalitys.getSelectedRowCount() != 0) {
			this.lblEliminar.setEnabled(true);
			this.lblUpdate.setEnabled(true);
		}
		else {
			this.lblEliminar.setEnabled(false);
			this.lblUpdate.setEnabled(false);
		}
	}
}
