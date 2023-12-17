package JFrames;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import JPanels.PanelCreacionContratoAlojamientoAccommodationModality;
import logica.AccommodationContract;
import logica.AccommodationModality;
import logica.MealPlan;
import logica.TypeOfRoom;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.border.LineBorder;
import javax.swing.SpinnerNumberModel;

public class FrameGerenteCreacionContratoAlojamientoAnnadir extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PanelCreacionContratoAlojamientoAccommodationModality panelCreacionContratoAlojamientoAccommodationModality;
	private FrameGerenteCreacionContratoAlojamiento frameGerenteCreacionContratoAlojamiento;
	private AccommodationContract accommodationContract;
	private JComboBox <TypeOfRoom> comboBoxTypeOfRoom;
	private JComboBox <MealPlan> comboBoxMealPlan;
	private JSpinner spinnerCantDays;
	private JSpinner spinnerPrice;
	private JLabel lblAdd;
	private int mouseX, mouseY;
	private JLabel lblX;


	public FrameGerenteCreacionContratoAlojamientoAnnadir(PanelCreacionContratoAlojamientoAccommodationModality am) {
		this.panelCreacionContratoAlojamientoAccommodationModality = am;
		this.accommodationContract = this.panelCreacionContratoAlojamientoAccommodationModality.getAccommodationContract();
		this.frameGerenteCreacionContratoAlojamiento = this.panelCreacionContratoAlojamientoAccommodationModality.getFrameGerenteCreacionContratoAlojamiento();
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 305);
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
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
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
		lblX.setBounds(412, 0, 38, 38);
		contentPane.add(lblX);

		JLabel lblRoomType = new JLabel("ROOM TYPE :");
		lblRoomType.setForeground(SystemColor.info);
		lblRoomType.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblRoomType.setBounds(72, 68, 119, 23);
		contentPane.add(lblRoomType);

		JLabel lblMealPlan = new JLabel("MEAL PLAN :");
		lblMealPlan.setForeground(SystemColor.info);
		lblMealPlan.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblMealPlan.setBounds(72, 102, 119, 23);
		contentPane.add(lblMealPlan);

		JLabel lblNumberOfDays = new JLabel("NUMBER OF DAYS :");
		lblNumberOfDays.setForeground(SystemColor.info);
		lblNumberOfDays.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNumberOfDays.setBounds(72, 144, 175, 23);
		contentPane.add(lblNumberOfDays);

		JLabel lblPrice = new JLabel("PRICE :");
		lblPrice.setForeground(SystemColor.info);
		lblPrice.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblPrice.setBounds(72, 186, 119, 23);
		contentPane.add(lblPrice);

		lblAdd = new JLabel("ADD");
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					addAccommodationModality();
					cerrarFrame();
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
		lblAdd.setOpaque(true);
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(new Font("Arial Black", Font.PLAIN, 11));
		lblAdd.setBackground(SystemColor.info);
		lblAdd.setBounds(107, 243, 235, 35);
		contentPane.add(lblAdd);

		JLabel lblTransportationMode = new JLabel("ACCOMMODATION MODE");
		lblTransportationMode.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblTransportationMode.setBounds(27, 11, 294, 30);
		contentPane.add(lblTransportationMode);

		comboBoxTypeOfRoom = new JComboBox <TypeOfRoom>();
		comboBoxTypeOfRoom.setBounds(218, 68, 161, 22);
		contentPane.add(comboBoxTypeOfRoom);

		comboBoxMealPlan = new JComboBox <MealPlan>();
		comboBoxMealPlan.setBounds(218, 102, 161, 22);
		contentPane.add(comboBoxMealPlan);

		spinnerCantDays = new JSpinner();
		spinnerCantDays.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinnerCantDays.setBounds(279, 145, 100, 20);
		contentPane.add(spinnerCantDays);

		spinnerPrice = new JSpinner();
		spinnerPrice.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerPrice.setBounds(279, 187, 100, 20);
		contentPane.add(spinnerPrice);

		this.llenarComboBoxTypeOfRoom();
		this.llenarComboBoxMealPlan();
	}

	private void llenarComboBoxTypeOfRoom () {
		ArrayList<TypeOfRoom> typesOfRooms = this.accommodationContract.getTypesOfRoomsHotel(); // se obtienen los tipos de habitacion del hotel

		for (TypeOfRoom typeOfRoom : typesOfRooms) {
			this.comboBoxTypeOfRoom.addItem(typeOfRoom);
		}
	}

	private void llenarComboBoxMealPlan () {
		ArrayList<MealPlan> mealPlans = this.accommodationContract.getMealPlansHotel(); // se obtienen los planes alimenticios del hotel

		for (MealPlan mealPlan : mealPlans) {
			this.comboBoxMealPlan.addItem(mealPlan);
		}
	}

	private void addAccommodationModality () throws SQLException {
		if (this.accommodationContract.getId() != -1) { // si es distinto de -1 se trata de un objeto real, entonces se adiciona a la logica del negocio a la base de datos
			this.accommodationContract.addModality(new AccommodationModality(accommodationContract, (TypeOfRoom) comboBoxTypeOfRoom.getSelectedItem(), (MealPlan) comboBoxMealPlan.getSelectedItem(), 
					(Integer) spinnerCantDays.getValue(), (Double) spinnerPrice.getValue()));
		}
		else { // se forma contraria se almacena solo en la logica del negocio
			this.accommodationContract.addModalityLogic(new AccommodationModality((TypeOfRoom) comboBoxTypeOfRoom.getSelectedItem(), (MealPlan) comboBoxMealPlan.getSelectedItem(), 
					(Integer) spinnerCantDays.getValue(), (Double) spinnerPrice.getValue()));
		}

		panelCreacionContratoAlojamientoAccommodationModality.actualizarTablaModalitys(); // se actualiza la informacion de las modalidades en la tabla de modalidades
	}

	private void cerrarFrame () {
		frameGerenteCreacionContratoAlojamiento.setEnabled(true); // se vuelve a habilitar el frame
		dispose(); // se cierra el frame actual
	}

}
