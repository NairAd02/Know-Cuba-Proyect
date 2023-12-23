package JFrames;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import JPanels.PanelCreacionContratoTransporteTransportModality;
import logica.CarrierContract;
import logica.HoursKilometers;
import logica.Vehicle;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.border.LineBorder;
import javax.swing.SpinnerNumberModel;

public class FrameGerenteCreacionContratoTransporteAnnadirHorasKilometers extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PanelCreacionContratoTransporteTransportModality panelCreacionContratoTransporteTransportModality;
	private FrameGerenteCreacionContratoTransporte frameGerenteCreacionContratoTransporte;
	private CarrierContract carrierContract;
	private int mouseX, mouseY;
	private JLabel lblX;
	private JLabel lblAdd;
	private JComboBox <Vehicle> comboBoxVehicles;
	private JSpinner spinnerCostKilometersRout;
	private JSpinner spinnerCostHours;
	private JSpinner spinnerCostKilometersRoutAdd;
	private JSpinner spinnerCostHoursAdd;


	public FrameGerenteCreacionContratoTransporteAnnadirHorasKilometers(PanelCreacionContratoTransporteTransportModality tm) {
		this.panelCreacionContratoTransporteTransportModality = tm;
		this.frameGerenteCreacionContratoTransporte = this.panelCreacionContratoTransporteTransportModality.getFrameGerenteCreacionContratoTransporte();
		this.carrierContract = this.panelCreacionContratoTransporteTransportModality.getCarrierContract();
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.setBackground(new Color(5, 150, 177));
		contentPane.setLayout(null);
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

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cerrarFrame(); // se cierra el frame actual
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
		lblX.setBounds(427, 0, 38, 38);
		contentPane.add(lblX);

		JLabel lblTransportationMode = new JLabel("TRANSPORTATION MODE");
		lblTransportationMode.setFont(new Font("Arial Black", Font.PLAIN, 19));
		lblTransportationMode.setBounds(27, 11, 294, 30);
		contentPane.add(lblTransportationMode);

		JLabel lblVehicle = new JLabel("VEHICLE :");
		lblVehicle.setForeground(SystemColor.info);
		lblVehicle.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblVehicle.setBounds(27, 79, 119, 23);
		contentPane.add(lblVehicle);

		comboBoxVehicles = new JComboBox <Vehicle>();
		comboBoxVehicles.setBounds(279, 79, 161, 22);
		contentPane.add(comboBoxVehicles);

		JLabel lblCostKilometersRout = new JLabel("COST KILOMETERS ROUT :");
		lblCostKilometersRout.setForeground(SystemColor.info);
		lblCostKilometersRout.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostKilometersRout.setBounds(27, 113, 249, 23);
		contentPane.add(lblCostKilometersRout);

		JLabel lblCostHours = new JLabel("COST HOURS :");
		lblCostHours.setForeground(SystemColor.info);
		lblCostHours.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostHours.setBounds(27, 147, 175, 23);
		contentPane.add(lblCostHours);

		spinnerCostHours = new JSpinner();
		spinnerCostHours.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerCostHours.setBounds(340, 148, 100, 20);
		contentPane.add(spinnerCostHours);

		JLabel lblCostKilometersRoutAdd = new JLabel("COST KILOMETERS ROUT ADD :");
		lblCostKilometersRoutAdd.setForeground(SystemColor.info);
		lblCostKilometersRoutAdd.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostKilometersRoutAdd.setBounds(27, 181, 278, 23);
		contentPane.add(lblCostKilometersRoutAdd);

		spinnerCostKilometersRoutAdd = new JSpinner();
		spinnerCostKilometersRoutAdd.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerCostKilometersRoutAdd.setBounds(340, 182, 100, 20);
		contentPane.add(spinnerCostKilometersRoutAdd);

		lblAdd = new JLabel("ADD");
		lblAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					addHoursKilometers();
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
		lblAdd.setBounds(115, 270, 235, 35);
		contentPane.add(lblAdd);

		spinnerCostKilometersRout = new JSpinner();
		spinnerCostKilometersRout.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerCostKilometersRout.setBounds(340, 114, 100, 20);
		contentPane.add(spinnerCostKilometersRout);

		JLabel lblCostHoursAdd = new JLabel("COST HOURS ADD :");
		lblCostHoursAdd.setForeground(SystemColor.info);
		lblCostHoursAdd.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblCostHoursAdd.setBounds(27, 215, 175, 23);
		contentPane.add(lblCostHoursAdd);

		spinnerCostHoursAdd = new JSpinner();
		spinnerCostHoursAdd.setModel(new SpinnerNumberModel(Double.valueOf(1), Double.valueOf(1), null, Double.valueOf(1)));
		spinnerCostHoursAdd.setBounds(340, 216, 100, 20);
		contentPane.add(spinnerCostHoursAdd);

		JLabel lblNewLabel = new JLabel("( Hours Kilometers ) ");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel.setBounds(73, 35, 173, 30);
		contentPane.add(lblNewLabel);

		this.llenarComboBoxVehicles();
	}

	private void llenarComboBoxVehicles () {
		ArrayList<Vehicle> vehicles = this.carrierContract.getVehiclesProvider(); // se obtienen los vehiculos del provedor transporte del contrato
		// Se añaden al comboBox
		for (Vehicle vehicle : vehicles) { 
			comboBoxVehicles.addItem(vehicle);
		}
	}


	private void addHoursKilometers () throws SQLException {
		if (this.carrierContract.getId() != -1) // si es disito de -1 se añade la modalidad en la base de datos y en la logica del negocio
			this.carrierContract.addModality(new HoursKilometers(this.carrierContract, (Vehicle) this.comboBoxVehicles.getSelectedItem(), 
					(Double)	this.spinnerCostKilometersRout.getValue(), (Double) this.spinnerCostHours.getValue(), (Double) this.spinnerCostKilometersRoutAdd.getValue(), (Double) this.spinnerCostHoursAdd.getValue()));
		else // si es igual a -1 se añade la modalidad en la base de datos y en la logica del negocio
			this.carrierContract.addModalityLogic(new HoursKilometers((Vehicle) this.comboBoxVehicles.getSelectedItem(), 
					(Double)	this.spinnerCostKilometersRout.getValue(), (Double) this.spinnerCostHours.getValue(), (Double) this.spinnerCostKilometersRoutAdd.getValue(), (Double) this.spinnerCostHoursAdd.getValue()));

		this.panelCreacionContratoTransporteTransportModality.actualizarTablaModalitys(); // se actualiza la informacion de la tablas de las modalidades del contrato


	}

	private void cerrarFrame () {
		this.frameGerenteCreacionContratoTransporte.setEnabled(true); // se vuelve a habilitar el frame
		dispose(); // se cierra el frame actual
	}
}
