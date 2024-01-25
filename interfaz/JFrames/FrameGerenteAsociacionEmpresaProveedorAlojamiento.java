package JFrames;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

import JPanels.PanelGerenteAsociacionEmpresaProveedorAlojamiento;
import logica.Controller;
import logica.Hotel;
import logica.HotelModality;
import logica.MealPlan;
import logica.TypeOfRoom;
import modelosTablas.ModelOperations;
import modelosTablas.ModeloTablaHotelModality;
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
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import javax.swing.SpinnerNumberModel;
import java.awt.Cursor;

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
	private int mouseX, mouseY;
	private JTextField textFieldPhone;
	private JTextField textFieldFax;
	private JTextField textFieldEmail;
	private JTextField textFieldLocationHotel;
	private JPanel panelTableHotelModality;
	private JTable tableHotelModality;
	private JLabel lblDeleteHotelModality;
	private JLabel lblAddHotelModality;
	private JSpinner spinnerCantRooms;
	private JSpinner spinnerCantFloors;
	private JSpinner spinnerDistanceNearestCity;
	private JSpinner spinnerDistanceAirport;
	private JLabel lblFechaDeConstruccion;
	private JDateChooser dateChooserDateBuild;
	private JLabel lblRestore;


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

	public JPanel getPanelTableHotelModality() {
		return panelTableHotelModality;
	}

	public JTable getTableHotelModality() {
		return tableHotelModality;
	}

	public void setTableHotelModality(JTable tableHotelModality) {
		this.tableHotelModality = tableHotelModality;
	}

	public void setPanelTableHotelModality(JPanel panelTableHotelModality) {
		this.panelTableHotelModality = panelTableHotelModality;
	}


	public FrameGerenteAsociacionEmpresaProveedorAlojamiento(PanelGerenteAsociacionEmpresaProveedorAlojamiento pa, Hotel h) {
		this.hotel = h;
		this.panelGerenteAsociacionEmpresaProveedorAlojamiento = pa;
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();

				setLocation(x - mouseX, y - mouseY);
			}
		});
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				clearSeleccionTables();
				actualizarEstadoButtons(); // se actualiza el estado de los botones
			}
		});
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.setBackground(new Color(18, 95, 115));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		JLabel lblAccommodationProvider = new JLabel("ACCOMMODATION PROVIDER");
		lblAccommodationProvider.setForeground(SystemColor.textHighlightText);
		lblAccommodationProvider.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblAccommodationProvider.setBounds(27, 11, 341, 30);
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
				lblX.setForeground(SystemColor.textHighlightText);
			}
		});
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(SystemColor.textHighlightText);
		lblX.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblX.setBackground(SystemColor.menu);
		lblX.setBounds(606, 0, 38, 38);
		contentPane.add(lblX);


		this.addSeccionName();
		this.addSeccionProvince();
		this.addSeccionAddres();
		this.addSeccionCategory();
		this.addSeccionHotelChain();


		setBounds(100, 100, 644, 759);
		setLocationRelativeTo(null);


		JLabel lblRoomTypes = new JLabel("ROOM TYPES");
		lblRoomTypes.setForeground(SystemColor.textHighlightText);
		lblRoomTypes.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblRoomTypes.setBounds(65, 352, 118, 23);
		contentPane.add(lblRoomTypes);


		JPanel panelRoomTypes = new JPanel();
		panelRoomTypes.setBounds(52, 386, 243, 113);
		contentPane.add(panelRoomTypes);
		panelRoomTypes.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelRoomTypes.add(scrollPane, BorderLayout.CENTER);

		tableRoomTypes = new JTable();
		tableRoomTypes.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtons(); // se actualiza el estado de los botones
			}
		});
		tableRoomTypes.setModel(new ModeloTablaTypeOfRoom());
		scrollPane.setViewportView(tableRoomTypes);

		lblAnnadirTypeOfRoom = new JLabel("ADD");
		lblAnnadirTypeOfRoom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		lblAnnadirTypeOfRoom.setBounds(193, 355, 38, 20);
		contentPane.add(lblAnnadirTypeOfRoom);

		lblDeleteTypeOfRoom = new JLabel("DELETE");
		lblDeleteTypeOfRoom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		lblDeleteTypeOfRoom.setBounds(238, 355, 57, 20);
		contentPane.add(lblDeleteTypeOfRoom);


		JPanel panelMealPlan = new JPanel();
		panelMealPlan.setBounds(347, 386, 243, 113);
		contentPane.add(panelMealPlan);
		panelMealPlan.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panelMealPlan.add(scrollPane_1, BorderLayout.CENTER);

		tableMealPlan = new JTable();
		tableMealPlan.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtons(); // se actualiza el estado de los botones
			}
		});
		tableMealPlan.setModel(new ModeloTablaMealPlan());
		scrollPane_1.setViewportView(tableMealPlan);

		JLabel lblMealPlan = new JLabel("MEAL PLAN");
		lblMealPlan.setForeground(SystemColor.textHighlightText);
		lblMealPlan.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblMealPlan.setBounds(351, 352, 118, 23);
		contentPane.add(lblMealPlan);

		lblAddMealPlan = new JLabel("ADD");
		lblAddMealPlan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		lblAddMealPlan.setBounds(485, 355, 38, 20);
		contentPane.add(lblAddMealPlan);

		lblDeleteMealPlan = new JLabel("DELETE");
		lblDeleteMealPlan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		lblDeleteMealPlan.setBounds(533, 355, 57, 20);
		contentPane.add(lblDeleteMealPlan);

		this.addButtonADD();

		if (this.hotel.getId() != -1) // Update
			this.actualizarTablas();
		else
			this.actualizarEstadoButtons();

	}

	public void restoreInformation() { // Metodo restuarar la informacion del hotel antes de cualquier modificacion
		// Actualizar datos del hotel
		try {
			this.restoreMealsPlansTypesOfRoomsHotelModalities();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		this.definirCampos();
		this.actualizarTablas();
	}

	private void restoreMealsPlansTypesOfRoomsHotelModalities () throws SQLException {
		ConnectionDataBase.roolback(); // se cancelan las operaciones realizadas sobre la base de datos
		hotel.actualizarDatos(); // se actualizan los datos del hotel para evitar inconsistencias
	}

	public void actualizarTablas() {
		this.actualizarTablaTypesOfRooms(); // se actualiza la tabla de tipos de habitacion
		this.actualizarTablaMealPlans(); // se actualiza la tabla de planes alimenticios
		this.actualizarTablaHotelModality(); // Se actualiza la tabla de modalidades de hotel
	}

	private void addSeccionName() {
		JLabel lblName = new JLabel("Name :");
		lblName.setForeground(SystemColor.textHighlightText);
		lblName.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblName.setBounds(27, 66, 80, 23);
		contentPane.add(lblName);

		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(109, 70, 197, 20);
		contentPane.add(textFieldName);


	}

	private void addSeccionProvince() {
		JLabel lblProvince = new JLabel("Province :");
		lblProvince.setForeground(SystemColor.textHighlightText);
		lblProvince.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblProvince.setBounds(27, 105, 109, 23);
		contentPane.add(lblProvince);

		textFieldProvince = new JTextField();
		textFieldProvince.setColumns(10);
		textFieldProvince.setBounds(148, 101, 158, 20);
		contentPane.add(textFieldProvince);
	}

	private void addSeccionAddres() {
		JLabel lblDireccion = new JLabel("Direction :");
		lblDireccion.setForeground(SystemColor.textHighlightText);
		lblDireccion.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDireccion.setBounds(27, 222, 109, 23);
		contentPane.add(lblDireccion);

		textFieldDireccion = new JTextField();
		textFieldDireccion.setBounds(160, 226, 146, 20);
		contentPane.add(textFieldDireccion);
		textFieldDireccion.setColumns(10);

	}

	private void addSeccionHotelChain() {
		JLabel lblHotelChail = new JLabel("Hotel Chain :");
		lblHotelChail.setForeground(SystemColor.textHighlightText);
		lblHotelChail.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblHotelChail.setBounds(27, 144, 158, 23);
		contentPane.add(lblHotelChail);

		textFieldHotelChain = new JTextField();
		textFieldHotelChain.setBounds(174, 148, 132, 20);
		contentPane.add(textFieldHotelChain);
		textFieldHotelChain.setColumns(10);
	}

	private void addSeccionCategory() {
		JLabel lblHotelCategory = new JLabel("Hotel Category :");
		lblHotelCategory.setForeground(SystemColor.textHighlightText);
		lblHotelCategory.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblHotelCategory.setBounds(27, 183, 158, 23);
		contentPane.add(lblHotelCategory);

		spinnerCategory = new JSpinner();
		spinnerCategory.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerCategory.setBounds(249, 187, 57, 20);
		contentPane.add(spinnerCategory);

	}

	private void crearFrameNotificacion (String mensaje) {
		FrameAdvertencia frameAdvertencia = new FrameAdvertencia(mensaje);
		frameAdvertencia.setVisible(true);
	}

	private void addButtonADD() {
		String nameButton = "";

		if (this.hotel.getId() == -1)
			nameButton = "ADD";
		else
			nameButton = "UPDATE";
		JLabel lblAdd = new JLabel("ADD");
		lblAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (hotel.getId() == -1) {
					if (verificarCampos()) {
						try {
							addAccommodationProvider();
							ConnectionDataBase.commit(); // se confirman las transacciones realizadas
							crearFrameNotificacion("Se ha añadido correctamente el proveedor de alojamiento: " + textFieldName.getText());
							panelGerenteAsociacionEmpresaProveedorAlojamiento.actualizarTablaAccommodationProviders(); // se actualiza la informacion de la tabla de provedores
							cerrarFrame(); // se cierra el frame actual
						} catch (SQLException e1) {
							try {
								ConnectionDataBase.roolback(); // se cancelan las transacciones realizadas
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} // se cancelan las transacciones realizadas
							e1.printStackTrace();
						}
					}
				} else {
					if (verificarCampos()) {
						try {
							updateAccommodationProvider(); // se actualiza la informacion del proveedor de transporte
							ConnectionDataBase.commit(); // se confirman las transacciones realizadas
							crearFrameNotificacion("Ha sido actualizada correcatamente la información del proveedor de alojamiento");
							panelGerenteAsociacionEmpresaProveedorAlojamiento.actualizarTablaAccommodationProviders(); // se actualiza la informacion de la tabla de provedores
							cerrarFrame();
						} catch (SQLException e1) {
							try {
								ConnectionDataBase.roolback(); // se cancelan las transacciones realizadas
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							e1.printStackTrace();
						}

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
		lblAdd.setBounds(223, 702, 197, 35);
		contentPane.add(lblAdd);

		JLabel lblTelefono = new JLabel("Phone :");
		lblTelefono.setForeground(SystemColor.textHighlightText);
		lblTelefono.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblTelefono.setBounds(332, 65, 97, 23);
		contentPane.add(lblTelefono);

		textFieldPhone = new JTextField();
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(417, 70, 197, 20);
		contentPane.add(textFieldPhone);

		JLabel lblFax = new JLabel("Fax :");
		lblFax.setForeground(SystemColor.textHighlightText);
		lblFax.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblFax.setBounds(332, 104, 57, 23);
		contentPane.add(lblFax);

		textFieldFax = new JTextField();
		textFieldFax.setColumns(10);
		textFieldFax.setBounds(417, 109, 197, 20);
		contentPane.add(textFieldFax);

		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setForeground(SystemColor.textHighlightText);
		lblEmail.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblEmail.setBounds(332, 142, 66, 23);
		contentPane.add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(417, 148, 197, 20);
		contentPane.add(textFieldEmail);

		JLabel lblCantidadDeHabitaciones = new JLabel("Number of Rooms :");
		lblCantidadDeHabitaciones.setForeground(SystemColor.textHighlightText);
		lblCantidadDeHabitaciones.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCantidadDeHabitaciones.setBounds(332, 181, 181, 23);
		contentPane.add(lblCantidadDeHabitaciones);

		spinnerCantRooms = new JSpinner();
		spinnerCantRooms.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinnerCantRooms.setBounds(557, 187, 57, 20);
		contentPane.add(spinnerCantRooms);

		JLabel lblCantPisos = new JLabel("Number of Floors :");
		lblCantPisos.setForeground(SystemColor.textHighlightText);
		lblCantPisos.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCantPisos.setBounds(332, 219, 168, 23);
		contentPane.add(lblCantPisos);

		spinnerCantFloors = new JSpinner();
		spinnerCantFloors.setBounds(557, 226, 57, 20);
		contentPane.add(spinnerCantFloors);

		JLabel lblLocalizacion = new JLabel("Location :");
		lblLocalizacion.setForeground(SystemColor.textHighlightText);
		lblLocalizacion.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblLocalizacion.setBounds(27, 256, 132, 23);
		contentPane.add(lblLocalizacion);

		textFieldLocationHotel = new JTextField();
		textFieldLocationHotel.setColumns(10);
		textFieldLocationHotel.setBounds(160, 260, 146, 20);
		contentPane.add(textFieldLocationHotel);

		JLabel lblDistCiudadCercana = new JLabel("Nearby City Dist :");
		lblDistCiudadCercana.setForeground(SystemColor.textHighlightText);
		lblDistCiudadCercana.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDistCiudadCercana.setBounds(332, 256, 190, 23);
		contentPane.add(lblDistCiudadCercana);

		spinnerDistanceNearestCity = new JSpinner();
		spinnerDistanceNearestCity.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerDistanceNearestCity.setBounds(557, 260, 57, 20);
		contentPane.add(spinnerDistanceNearestCity);

		JLabel lblDistAeropuerto = new JLabel("Airport Dist :");
		lblDistAeropuerto.setForeground(SystemColor.textHighlightText);
		lblDistAeropuerto.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblDistAeropuerto.setBounds(332, 302, 151, 23);
		contentPane.add(lblDistAeropuerto);

		spinnerDistanceAirport = new JSpinner();
		spinnerDistanceAirport.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerDistanceAirport.setBounds(557, 306, 57, 20);
		contentPane.add(spinnerDistanceAirport);

		panelTableHotelModality = new JPanel();
		panelTableHotelModality.setBounds(174, 550, 295, 113);
		contentPane.add(panelTableHotelModality);
		panelTableHotelModality.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelTableHotelModality.add(scrollPane, BorderLayout.CENTER);

		tableHotelModality = new JTable();
		tableHotelModality.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				actualizarEstadoButtons(); // se actualiza el estado de los botones
			}
		});
		tableHotelModality.setModel(new ModeloTablaHotelModality());
		scrollPane.setViewportView(tableHotelModality);

		JLabel lblModalidadesHotel = new JLabel("HOTEL MODALITIES");
		lblModalidadesHotel.setForeground(SystemColor.textHighlightText);
		lblModalidadesHotel.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblModalidadesHotel.setBounds(174, 516, 178, 23);
		contentPane.add(lblModalidadesHotel);

		lblAddHotelModality = new JLabel("ADD");
		lblAddHotelModality.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAddHotelModality.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameGerenteAsociacionEmpresaProveedorAlojamientoAnnadirHotelModality frameAddHotelModality = new FrameGerenteAsociacionEmpresaProveedorAlojamientoAnnadirHotelModality(FrameGerenteAsociacionEmpresaProveedorAlojamiento.this);
				frameAddHotelModality.setVisible(true);
				setEnabled(false); // se inhabilita el frame actual
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		lblAddHotelModality.setOpaque(true);
		lblAddHotelModality.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddHotelModality.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAddHotelModality.setBackground(SystemColor.info);
		lblAddHotelModality.setBounds(367, 516, 38, 20);
		contentPane.add(lblAddHotelModality);

		lblDeleteHotelModality = new JLabel("DELETE");
		lblDeleteHotelModality.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblDeleteHotelModality.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (lblDeleteHotelModality.isEnabled()) {
					try {
						deleteElementsTableHotelModality(); // se eliminan los elemtos seleccionados de la tabla hotel modality
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
		lblDeleteHotelModality.setOpaque(true);
		lblDeleteHotelModality.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteHotelModality.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblDeleteHotelModality.setBackground(SystemColor.info);
		lblDeleteHotelModality.setBounds(412, 516, 57, 20);
		contentPane.add(lblDeleteHotelModality);

		lblFechaDeConstruccion = new JLabel("Construction Date :");
		lblFechaDeConstruccion.setForeground(SystemColor.textHighlightText);
		lblFechaDeConstruccion.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblFechaDeConstruccion.setBounds(27, 302, 190, 23);
		contentPane.add(lblFechaDeConstruccion);

		dateChooserDateBuild = new JDateChooser();
		dateChooserDateBuild.setBounds(204, 302, 102, 20);
		contentPane.add(dateChooserDateBuild);


		this.definirComponentes();
		this.definirCampos();
	}

	private void definirComponentes () {
		if (this.hotel.getId() != -1) // Update
			this.addLblRestore();
	}

	private void addLblRestore () {
		lblRestore = new JLabel("Restore");
		lblRestore.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				restoreInformation();
			}
		});
		lblRestore.setForeground(SystemColor.textHighlightText);
		lblRestore.setFont(new Font("Dialog", Font.BOLD, 21));
		lblRestore.setBounds(413, 11, 109, 30);
		contentPane.add(lblRestore);
	}

	private void definirCampos() {
		if (this.hotel.getId() != -1) { // Update
			this.textFieldName.setText(this.hotel.getName());
			this.textFieldProvince.setText(this.hotel.getProvince());
			this.textFieldDireccion.setText(this.hotel.getAddress());
			this.textFieldPhone.setText(String.valueOf(this.hotel.getPhone()));
			this.textFieldFax.setText(this.hotel.getFax());
			this.textFieldEmail.setText(this.hotel.getEmail());
			this.textFieldLocationHotel.setText(this.hotel.getLocationHotel());
			this.textFieldHotelChain.setText(this.hotel.getHotelChain());
			this.spinnerCategory.setValue(this.hotel.getHotelCategory());
			this.spinnerCantFloors.setValue(this.hotel.getCantFloors());
			this.spinnerCantRooms.setValue(this.hotel.getCantRooms());
			this.spinnerDistanceAirport.setValue(this.hotel.getDistanceAirport());
			this.spinnerDistanceNearestCity.setValue(this.hotel.getDistanceNearestCity());
			this.dateChooserDateBuild.setDate(Date.from(this.hotel.getDateBuild().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}
	}

	public void actualizarTablaTypesOfRooms() { // actualizar tabla de tipos de habitaciones
		this.actualizarTablaTypesOfRooms(hotel.getTypesOfRooms()); // se obtienen los tipos de habitaciones del proveedor de alojamiento
		this.actualizarEstadoButtons(); // se actualiza el estado de los botones
	}

	public void actualizarTablaMealPlans() { // actualizar tabla de planes alimenticios
		this.actualizarTablaMealPlans(hotel.getMealsPlans()); // se obtienen los tipos de habitaciones del proveedor de alojamiento
		this.actualizarEstadoButtons(); // se actualiza el estado de los botones
	}

	public void actualizarTablaHotelModality() { // actualizar tabla de modalidades de hotel
		this.actualizarTablaHotelModality(this.hotel.getHotelsModalitys()); // se obtienen las modalidades de hotel del proveedor de alojamiento
		this.actualizarEstadoButtons(); // se actualiza el estado de los botones
	}

	private void actualizarTablaHotelModality(ArrayList<HotelModality> hotelsModalitys) {
		reiniciarTable(this.tableHotelModality);


		for (HotelModality hotelModality : hotelsModalitys) {
			((ModeloTablaHotelModality) tableHotelModality.getModel()).addElement(hotelModality);
		}
	}


	private void actualizarTablaTypesOfRooms(ArrayList<TypeOfRoom> typesOfRoom) { // Actualizar tabla tipo de habitacion
		reiniciarTable(this.tableRoomTypes);


		for (TypeOfRoom type : typesOfRoom) {
			((ModeloTablaTypeOfRoom) tableRoomTypes.getModel()).addElement(type);
		}
	}

	private void actualizarTablaMealPlans(ArrayList<MealPlan> mealPlans) { // Actualizar tabla tipo de planes alimenticios
		reiniciarTable(this.tableMealPlan);


		for (MealPlan meal : mealPlans) {
			((ModeloTablaMealPlan) tableMealPlan.getModel()).addElement(meal);
		}
	}

	private void deleteElementsTableTypesOfRoom() throws SQLException { // Eliminar los elementos de la tabla de tipos de habitaciones
		int[] rows = tableRoomTypes.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (hotel.getId() == -1)
				hotel.deleteTypeOfRoomLogic(((ModeloTablaTypeOfRoom) tableRoomTypes.getModel()).getElement(rows[i])); // se elimina solo el tipo de plan de la logica del negocio
			else
				hotel.deleteTypeOfRoom(((ModeloTablaTypeOfRoom) tableRoomTypes.getModel()).getElement(rows[i])); // se elimina de el tipo de plan de la logica del negocio y de la base de datos
		}


		this.actualizarTablaTypesOfRooms();
	}

	private void deleteElementsTableMealPlans() throws SQLException { // Eliminar los elementos de la tabla de los planes alimenticios
		int[] rows = tableMealPlan.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (hotel.getId() == -1)
				hotel.deleteMealPlanLogic(((ModeloTablaMealPlan) tableMealPlan.getModel()).getElement(rows[i])); // se elimina solo el tipo de plan de la logica del negocio
			else
				hotel.deleteMealPlan(((ModeloTablaMealPlan) tableMealPlan.getModel()).getElement(rows[i])); // se elimina de el tipo de plan de la logica del negocio y de la base de datos
		}


		this.actualizarTablaMealPlans();
	}

	private void deleteElementsTableHotelModality() throws SQLException { // Eliminar los elementos de la tabla de tipos de habitaciones
		int[] rows = this.tableHotelModality.getSelectedRows();

		for (int i = 0; i < rows.length; i++) {
			if (hotel.getId() == -1)
				hotel.deleteHotelModalityLogic(((ModeloTablaHotelModality) this.tableHotelModality.getModel()).getElement(rows[i])); // se elimina la modalidad de hotel solo de la logica del negocio
			else
				hotel.deleteHotelModality(((ModeloTablaHotelModality) this.tableHotelModality.getModel()).getElement(rows[i])); // se elimina elimina la modalidad de la logica del negocio y de la base de datos
		}


		this.actualizarTablaHotelModality();
	}

	private void reiniciarTable(JTable table) {

		for (int i = 0; table.getRowCount() != 0; i++) {
			i = 0;
			((ModelOperations<?>) table.getModel()).deleteElement(i);
		}
	}

	private boolean verificarCampos() {
		return (!this.textFieldName.getText().equalsIgnoreCase("") && !this.textFieldProvince.getText().equalsIgnoreCase("") && !textFieldHotelChain.getText().equalsIgnoreCase("")
				&& !textFieldDireccion.getText().equalsIgnoreCase("") && this.hotel.cantTypeOfRoom() != 0 &&
				this.hotel.cantMealPlan() != 0 && this.hotel.cantHotelModality() != 0);
	}


	private void addAccommodationProvider() throws SQLException {
		Controller.getInstancie().getTouristAgency().addProvider(new Hotel(textFieldName.getText(), textFieldProvince.getText(), textFieldHotelChain.getText(), (Integer) spinnerCategory.getValue(),
				textFieldDireccion.getText(), Integer.valueOf(this.textFieldPhone.getText()), this.textFieldFax.getText(),
				this.textFieldEmail.getText(), (Integer) this.spinnerCantRooms.getValue(), (Integer) this.spinnerCantFloors.getValue(),
				this.textFieldLocationHotel.getText(), (Double) this.spinnerDistanceNearestCity.getValue(), (Double) this.spinnerDistanceAirport.getValue(),
				this.dateChooserDateBuild.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), this.hotel.getTypesOfRooms(), this.hotel.getMealsPlans(), this.hotel.getHotelsModalitys())); // se inserta el provedor de alojamientos a nivel de logica y de base de datos


	}

	private void updateAccommodationProvider() throws NumberFormatException, SQLException {
		Controller.getInstancie().getTouristAgency().updateHotel(this.hotel, textFieldName.getText(), textFieldProvince.getText(), textFieldHotelChain.getText(), (Integer) spinnerCategory.getValue(),
				textFieldDireccion.getText(), Integer.valueOf(this.textFieldPhone.getText()), this.textFieldFax.getText(),
				this.textFieldEmail.getText(), (Integer) this.spinnerCantRooms.getValue(), (Integer) this.spinnerCantFloors.getValue(),
				this.textFieldLocationHotel.getText(), (Double) this.spinnerDistanceNearestCity.getValue(), (Double) this.spinnerDistanceAirport.getValue(),
				this.dateChooserDateBuild.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	}

	private void cerrarFrame() {
		FramePrincipal.getIntancie().setEnabled(true); // se vuelve a habilitar el frame principal
		dispose(); // se cierra este frame
	}

	private void actualizarEstadoButtons () {

		// Estado boton eliminar modalidades de hotel
		if (this.tableHotelModality.getSelectedRowCount() != 0)
			this.lblDeleteHotelModality.setEnabled(true);
		else
			this.lblDeleteHotelModality.setEnabled(false);

		// Estado boton eliminar planes alimenticios
		if (this.tableMealPlan.getSelectedRowCount() != 0)
			this.lblDeleteMealPlan.setEnabled(true);
		else
			this.lblDeleteMealPlan.setEnabled(false);

		// Estado boton eliminar tipos de habitaciones
		if (this.tableRoomTypes.getSelectedRowCount() != 0)
			this.lblDeleteTypeOfRoom.setEnabled(true);
		else
			this.lblDeleteTypeOfRoom.setEnabled(false);

	}

	private void clearSeleccionTables () {
		this.tableHotelModality.clearSelection();
		this.tableMealPlan.clearSelection();
		this.tableRoomTypes.clearSelection();
	}
}
