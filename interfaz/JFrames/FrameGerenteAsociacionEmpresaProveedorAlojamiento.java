package JFrames;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import JPanels.PanelGerenteAsociacionEmpresaProveedorAlojamiento;
import logica.Controller;
import logica.Hotel;
import logica.MealPlan;
import logica.TypeOfRoom;
import modelosTablas.ModeloTablaMealPlan;
import modelosTablas.ModeloTablaTypeOfRoom;
import utils.ConnectionDataBase;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.border.LineBorder;

public class FrameGerenteAsociacionEmpresaProveedorAlojamiento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldProvince;
	private JTextField textFieldDireccion;
	private JTextField textFieldHotelChain;
	private JTable tableRoomTypes;
	private JTable tableMealPlan;
	private Hotel hotel;
	private PanelGerenteAsociacionEmpresaProveedorAlojamiento panelGerenteAsociacionEmpresaProveedorAlojamiento;
	private JSpinner spinnerCategory;
	private JLabel lblDeleteTypeOfRoom;
	private JLabel lblAnnadirTypeOfRoom;
	private JLabel lblAddMealPlan;
	private JLabel lblDeleteMealPlan;
	private JLabel lblX;
	private int posYTablas, posYButtonADD, posYTypesTable, posYButtonsTables, largoPantalla, mouseX, mouseY;
	private JLabel lblShowPlans;



	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}


	public JTable getTableRoomTypes() {
		return tableRoomTypes;
	}

	public void setTableRoomTypes(JTable tableRoomTypes) {
		this.tableRoomTypes = tableRoomTypes;
	}

	public JTable getTableMealPlan() {
		return tableMealPlan;
	}

	public void setTableMealPlan(JTable tableMealPlan) {
		this.tableMealPlan = tableMealPlan;
	}

	public FrameGerenteAsociacionEmpresaProveedorAlojamiento(PanelGerenteAsociacionEmpresaProveedorAlojamiento pa,Hotel h) {
		this.hotel = h;
		this.panelGerenteAsociacionEmpresaProveedorAlojamiento = pa;
		setUndecorated(true);
		contentPane = new JPanel();
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
			}
		});
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.setBackground(new Color(5, 150, 177));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		JLabel lblAccommodationProvider = new JLabel("ACCOMMODATION PROVIDER");
		lblAccommodationProvider.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblAccommodationProvider.setBounds(27, 11, 323, 30);
		contentPane.add(lblAccommodationProvider);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (hotel.getId() != -1) {
					try {
						ConnectionDataBase.roolback();
						hotel.actualizarDatos(); // se actualizan los datos del hotel para evitar inconsistencias
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} // se cancelan las transacciones realizadas
				}
				cerrarFrame();
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
		lblX.setBounds(492, 0, 38, 38);
		contentPane.add(lblX);


		lblShowPlans = new JLabel("SHOW PLANS");
		lblShowPlans.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblShowPlans.setBounds(27, 54, 323, 30);
		contentPane.add(lblShowPlans);

		if (this.hotel.getId() == -1) { // se habilita la edicion
			this.addSeccionName();
			this.addSeccionProvince();
			this.addSeccionAddres();
			this.addSeccionCategory();
			this.addSeccionHotelChain();
			this.posYTablas = 319;
			this.posYTypesTable = 285;
			this.posYButtonsTables = 288;
			this.posYButtonADD = 455;
			this.largoPantalla = 515;
			lblShowPlans.setVisible(false);
		}
		else {
			this.posYTablas = 173;
			this.posYTypesTable = 139;
			this.posYButtonsTables = 142;
			this.posYButtonADD = 345;
			this.largoPantalla = 410;
		}

		setBounds(100, 100, 530, this.largoPantalla);
		setLocationRelativeTo(null);


		JLabel lblRoomTypes = new JLabel("ROOM TYPES");
		lblRoomTypes.setForeground(SystemColor.info);
		lblRoomTypes.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblRoomTypes.setBounds(27, this.posYTypesTable, 118, 23);
		contentPane.add(lblRoomTypes);



		JPanel panelRoomTypes = new JPanel();
		panelRoomTypes.setBounds(10, this.posYTablas, 243, 113);
		contentPane.add(panelRoomTypes);
		panelRoomTypes.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelRoomTypes.add(scrollPane, BorderLayout.CENTER);

		tableRoomTypes = new JTable();
		tableRoomTypes.setModel(new ModeloTablaTypeOfRoom());
		scrollPane.setViewportView(tableRoomTypes);

		lblAnnadirTypeOfRoom = new JLabel("ADD");
		lblAnnadirTypeOfRoom.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameGerenteAsociacionEmpresaProveedorAlojamientoAnnadirTipoHabitacion frameAddTypeOfRoom = new FrameGerenteAsociacionEmpresaProveedorAlojamientoAnnadirTipoHabitacion(FrameGerenteAsociacionEmpresaProveedorAlojamiento.this);
				frameAddTypeOfRoom.setVisible(true);
				setEnabled(false); // se inhabilita el frame actual
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblAnnadirTypeOfRoom.setOpaque(true);
		lblAnnadirTypeOfRoom.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnadirTypeOfRoom.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAnnadirTypeOfRoom.setBackground(SystemColor.info);
		lblAnnadirTypeOfRoom.setBounds(155, this.posYButtonsTables, 38, 20);
		contentPane.add(lblAnnadirTypeOfRoom);

		lblDeleteTypeOfRoom = new JLabel("DELETE");
		lblDeleteTypeOfRoom.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (lblDeleteTypeOfRoom.isEnabled()) {
					try {
						deleteElementsTableTypesOfRoom();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
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
		lblDeleteTypeOfRoom.setOpaque(true);
		lblDeleteTypeOfRoom.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteTypeOfRoom.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDeleteTypeOfRoom.setBackground(SystemColor.info);
		lblDeleteTypeOfRoom.setBounds(200, this.posYButtonsTables, 57, 20);
		contentPane.add(lblDeleteTypeOfRoom);



		JPanel panelMealPlan = new JPanel();
		panelMealPlan.setBounds(267, this.posYTablas, 243, 113);
		contentPane.add(panelMealPlan);
		panelMealPlan.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panelMealPlan.add(scrollPane_1, BorderLayout.CENTER);

		tableMealPlan = new JTable();
		tableMealPlan.setModel(new ModeloTablaMealPlan());
		scrollPane_1.setViewportView(tableMealPlan);

		JLabel lblMealPlan = new JLabel("MEAL PLAN");
		lblMealPlan.setForeground(SystemColor.info);
		lblMealPlan.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblMealPlan.setBounds(290, this.posYTypesTable, 118, 23);
		contentPane.add(lblMealPlan);

		lblAddMealPlan = new JLabel("ADD");
		lblAddMealPlan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrameGerenteAsociacionEmpresaProveedorAlojamientoAnnadirPlanAlimenticio frameAddMealPlan = new FrameGerenteAsociacionEmpresaProveedorAlojamientoAnnadirPlanAlimenticio(FrameGerenteAsociacionEmpresaProveedorAlojamiento.this);
				frameAddMealPlan.setVisible(true);
				setEnabled(false); // se inhabilita el frame
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblAddMealPlan.setOpaque(true);
		lblAddMealPlan.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddMealPlan.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAddMealPlan.setBackground(SystemColor.info);
		lblAddMealPlan.setBounds(418, this.posYButtonsTables, 38, 20);
		contentPane.add(lblAddMealPlan);

		lblDeleteMealPlan = new JLabel("DELETE");
		lblDeleteMealPlan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (lblDeleteMealPlan.isEnabled()) {
					try {
						deleteElementsTableMealPlans();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
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
		lblDeleteMealPlan.setOpaque(true);
		lblDeleteMealPlan.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteMealPlan.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDeleteMealPlan.setBackground(SystemColor.info);
		lblDeleteMealPlan.setBounds(463, this.posYButtonsTables, 57, 20);
		contentPane.add(lblDeleteMealPlan);

		this.addButtonADD();
		this.actualizarTablaTypesOfRooms(); // se actualiza la tabla de tipos de habitacion
		this.actualizarTablaMealPlans(); // se actualiza la tabla de planes alimenticios

	}

	private void addSeccionName () {
		JLabel lblName = new JLabel("NAME :");
		lblName.setForeground(SystemColor.info);
		lblName.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblName.setBounds(127, 65, 80, 23);
		contentPane.add(lblName);

		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(219, 66, 197, 20);
		contentPane.add(textFieldName);


	}

	private void addSeccionProvince () {
		JLabel lblProvince = new JLabel("PROVINCE :");
		lblProvince.setForeground(SystemColor.info);
		lblProvince.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblProvince.setBounds(127, 104, 109, 23);
		contentPane.add(lblProvince);

		textFieldProvince = new JTextField();
		textFieldProvince.setColumns(10);
		textFieldProvince.setBounds(258, 105, 158, 20);
		contentPane.add(textFieldProvince);
	}

	private void addSeccionAddres () {
		JLabel lblDireccion = new JLabel("DIRECCION :");
		lblDireccion.setForeground(SystemColor.info);
		lblDireccion.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDireccion.setBounds(127, 221, 109, 23);
		contentPane.add(lblDireccion);

		textFieldDireccion = new JTextField();
		textFieldDireccion.setBounds(330, 222, 86, 20);
		contentPane.add(textFieldDireccion);
		textFieldDireccion.setColumns(10);

	}

	private void addSeccionHotelChain () {
		JLabel lblHotelChail = new JLabel("HOTEL CHAIN :");
		lblHotelChail.setForeground(SystemColor.info);
		lblHotelChail.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblHotelChail.setBounds(127, 143, 138, 23);
		contentPane.add(lblHotelChail);

		textFieldHotelChain = new JTextField();
		textFieldHotelChain.setBounds(284, 144, 132, 20);
		contentPane.add(textFieldHotelChain);
		textFieldHotelChain.setColumns(10);
	}

	private void addSeccionCategory () {
		JLabel lblHotelCategory = new JLabel("HOTEL CATEGORY :");
		lblHotelCategory.setForeground(SystemColor.info);
		lblHotelCategory.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblHotelCategory.setBounds(127, 182, 181, 23);
		contentPane.add(lblHotelCategory);

		spinnerCategory = new JSpinner();
		spinnerCategory.setBounds(359, 183, 57, 20);
		contentPane.add(spinnerCategory);

	}

	private void addButtonADD () {
		String nameButton = "";

		if (this.hotel.getId() == -1)
			nameButton = "ADD";
		else
			nameButton = "CONFIRM";
		JLabel lblAdd = new JLabel(nameButton);
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (hotel.getId() == -1) {
					if (verificarCampos()) {
						try {
							addAccommodationProvider();
							ConnectionDataBase.commit(); // se confirman las transacciones realizadas
							cerrarFrame(); // se cierra el frame actual
						} catch (SQLException e1) {
							try {
								ConnectionDataBase.roolback();
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} // se cancelan las transacciones realizadas
							e1.printStackTrace();
						}
					}
				}
				else {
					if (hotel.cantMealPlan() != 0 && hotel.cantTypeOfRoom() != 0) {
						try {
							ConnectionDataBase.commit(); // se confirman las transacciones realizadas
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						cerrarFrame();
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
		lblAdd.setOpaque(true);
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAdd.setBackground(SystemColor.info);
		lblAdd.setBounds(155, this.posYButtonADD, 235, 35);
		contentPane.add(lblAdd);
	}

	public void actualizarTablaTypesOfRooms () { // actualizar tabla de tipos de habitaciones 
		this.actualizarTablaTypesOfRooms(hotel.getTypesOfRooms()); // se obtienen los tipos de habitaciones del provedor de alojamiento
	}

	public void actualizarTablaMealPlans () { // actualizar tabla de planes alimenticios
		this.actualizarTablaMealPlans(hotel.getMealsPlans()); // se obtienen los tipos de habitaciones del provedor de alojamiento
	}


	private void actualizarTablaTypesOfRooms(ArrayList<TypeOfRoom> typesOfRoom){ // Actualizar tabla tipo de habitacion
		reiniciarTable(this.tableRoomTypes);


		for (TypeOfRoom type : typesOfRoom) {
			((ModeloTablaTypeOfRoom) tableRoomTypes.getModel()).addElement(type);
		}
	}

	private void actualizarTablaMealPlans(ArrayList<MealPlan> mealPlans){ // Actualizar tabla tipo de planes alimenticios
		reiniciarTable(this.tableMealPlan);


		for (MealPlan meal : mealPlans) {
			((ModeloTablaMealPlan) tableMealPlan.getModel()).addElement(meal);
		}
	}

	private void deleteElementsTableTypesOfRoom () throws SQLException { // Eliminar los elementos de la tabla de tipos de habitaciones
		int [] rows = tableRoomTypes.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (hotel.getId() == -1)
				hotel.deleteTypeOfRoomLogic( ((ModeloTablaTypeOfRoom) tableRoomTypes.getModel()).deleteElement(rows[i] - i)); // se elimina solo el tipo de plan de la logica del negocio
			else
				hotel.deleteTypeOfRoom( ((ModeloTablaTypeOfRoom) tableRoomTypes.getModel()).deleteElement(rows[i] - i)); // se elimina de el tipo de plan de la logica del negocio y de la base de datos
		}


		this.actualizarTablaTypesOfRooms();
	}

	private void deleteElementsTableMealPlans () throws SQLException { // Eliminar los elementos de la tabla de los planes alimenticios
		int [] rows = tableMealPlan.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (hotel.getId() == -1)
				hotel.deleteMealPlanLogic(((ModeloTablaMealPlan) tableMealPlan.getModel()).deleteElement(rows[i] - i)); // se elimina solo el tipo de plan de la logica del negocio
			else
				hotel.deleteMealPlan( ((ModeloTablaMealPlan) tableMealPlan.getModel()).deleteElement(rows[i] - i)); // se elimina de el tipo de plan de la logica del negocio y de la base de datos
		}


		this.actualizarTablaTypesOfRooms();
	}

	private void reiniciarTable(JTable table){

		for(int i=0;table.getRowCount()!=0;i++){
			i=0;
			if (table.getModel() instanceof ModeloTablaTypeOfRoom)
				((ModeloTablaTypeOfRoom) table.getModel()).deleteElement(i);
			else if (table.getModel() instanceof ModeloTablaMealPlan)
				((ModeloTablaMealPlan) table.getModel()).deleteElement(i);
		}
	}

	private boolean verificarCampos () {
		return (!this.textFieldName.getText().equalsIgnoreCase("") && !this.textFieldProvince.getText().equalsIgnoreCase("") && !textFieldHotelChain.getText().equalsIgnoreCase("")
				&& !textFieldDireccion.getText().equalsIgnoreCase("") && ((ModeloTablaTypeOfRoom) tableRoomTypes.getModel()).cantElements() != 0 && 
				((ModeloTablaMealPlan) tableMealPlan.getModel()).cantElements() != 0);
	}


	private void addAccommodationProvider () throws SQLException {
		Controller.getInstancie().getTouristAgency().addProvider(new Hotel(textFieldName.getText(), textFieldProvince.getText(), textFieldHotelChain.getText(), (Integer) spinnerCategory.getValue(), 
				textFieldDireccion.getText(), hotel.getTypesOfRooms(), hotel.getMealsPlans())); // se inserta el provedor de alojamientos a nivel de logica y de base de datos
		panelGerenteAsociacionEmpresaProveedorAlojamiento.actualizarTablaAccommodationProviders(); // se actualiza la informacion de la tabla de provedores

	}

	private void cerrarFrame () {
		FramePrincipal.getIntancie().setEnabled(true); // se vuelve a habilitar el frame principal
		dispose(); // se cierra este frame
	}

}
